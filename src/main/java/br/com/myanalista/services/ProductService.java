package br.com.myanalista.services;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.BadRequestException;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.Categories;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.request.ProductRequestPost;
import br.com.myanalista.models.response.CategoryMainResponse;
import br.com.myanalista.models.response.ProductResponse;
import br.com.myanalista.models.response.ProductSearchResponse;
import br.com.myanalista.repositories.CategoryRepository;
import br.com.myanalista.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository repository;
    private CategoryRepository repositoryCategory;
    private ModelMapper mapper;
    private Utils utils;

    @Transactional
    public ResponseEntity<Products> save(ProductRequestPost productRequest) {
        Optional<Products> product = repository.findByCodeSku(productRequest.getSku());
        if (product.isPresent()) {
            throw new BadRequestException("There is product registered with this sku: " + productRequest.getSku());
        }
        Products productEntity = new Products();
        mapper.map(productRequest, productEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(productEntity));
    }

    @Transactional
    public ResponseEntity<Products> update(ProductRequestPost productRequestPost) {
        Optional<Products> productsResult = repository.findById(productRequestPost.getId());
        if (!productsResult.isPresent()) {
            throw new EntityNotFoundException("Product not found with id: " + productRequestPost.getId());
        }
        Products products = new Products();
        mapper.map(productRequestPost, products);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(products));
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) {
        Optional<Products> product = repository.findById(id);
        if (!product.isPresent()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted with success");
    }

    public ResponseEntity<ProductResponse> findBySku(Integer sku) {
        Optional<Products> product = repository.findByCodeSku(sku);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find product with Sku: " + sku);
        }
        ProductResponse productResponse = new ProductResponse();
        mapper.map(product.get(), productResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productResponse);
    }

    public ResponseEntity<Products> findById(Long id) {
        Optional<Products> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find product with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product.get());
    }

    public ResponseEntity<Page<ProductSearchResponse>> findAllWithPage(Pageable pageable) {
        Page<Products> responses = repository.findAll(pageable);
        return implementedFilterCategoryinList(responses);
    }

    public ResponseEntity<Page<ProductSearchResponse>> findAllWithPageSeek(String search, Pageable pageable) {
        Page<Products> responses = repository.findByActiveOrSkuOrProductDescription(search, pageable);
        return implementedFilterCategoryinList(responses);
    }

    private ResponseEntity<Page<ProductSearchResponse>> implementedFilterCategoryinList(Page<Products> products) {
        List<ProductSearchResponse> response = products.stream().map(value ->
                ProductSearchResponse.builder()
                        .id(value.getId())
                        .sku(String.valueOf(value.getSku()))
                        .productDescription(value.getProductDescription())
                        .active(value.isActive())
                        .categories(utils.mapListIntoDtoList(value.getCategories()
                                .stream()
                                .filter(cat -> cat.getCategory() == null)
                                .collect(Collectors.toList()), CategoryMainResponse.class))
                        .build()).collect(Collectors.toList());
        Page<ProductSearchResponse> page = new PageImpl<>(response);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(page);
    }


    public void recordDataToDb() throws IOException {
        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/PRODUTOS.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // this first line will be discarted, because is the header.
            line = br.readLine();
            while (line != null) {

                int index_1 = line.indexOf(";");
                int index_2 = line.indexOf(";", index_1 + 1);

                Products channelResp = Products.builder()
                        .sku(Integer.parseInt(line.substring(0, index_1).trim()))
                        .productDescription(line.substring(index_1 + 1).trim())
                        .active(true)
                        .build();

                repository.save(channelResp);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error to read file " + e.getMessage());
        }
    }

    private Categories findCategoryById(Long id) {
        if (id == null) {
            return null;
        }
        Optional<Categories> category = repositoryCategory.findById(id);
        if (category.isEmpty()) {
            return null;
        }
        return category.get();
    }
}