package br.com.myanalista.services;

import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.Categories;
import br.com.myanalista.models.entities.Products;
import br.com.myanalista.models.request.ProductRequestPost;
import br.com.myanalista.models.response.ProductResponse;
import br.com.myanalista.repositories.CategoryRepository;
import br.com.myanalista.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository repositoryCategory;
    @Autowired
    private ModelMapper mapper;
    @Transactional
    public Products save(ProductRequestPost productRequest) {
        Optional<Products> product = repository.findByCodeSku(productRequest.getSku());
        if (product.isPresent()) {
            throw new EntityNotFoundException("There is product registered with this sku: " + productRequest.getSku());
        }
        Optional<Categories> categories = repositoryCategory.findById(productRequest.getCategories().getId());
        productRequest.setCategories(categories.get());
        Products productEntity = new Products();
        mapper.map(productRequest, productEntity);
        return repository.save(productEntity);
    }

    @Transactional
    public Products update(ProductRequestPost productRequestPost) {
        Optional<Products> productsResult = repository.findById(productRequestPost.getId());
        if (!productsResult.isPresent()) {
            throw new EntityNotFoundException("Product not found with id: " + productRequestPost.getId());
        }
        Products products = new Products();
        mapper.map(productRequestPost, products);
       return repository.save(products);
    }

    @Transactional
    public String delete(Long id) {
        Optional<Products> product = repository.findById(id);
        if (!product.isPresent()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        repository.deleteById(id);
        return "Product deleted with success";
    }

    public ProductResponse findBySku(Integer sku) {
        Optional<Products> product = repository.findByCodeSku(sku);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find product with Sku: " + sku);
        }
        ProductResponse productResponse = new ProductResponse();
        mapper.map(product.get(), productResponse);
        return productResponse;
    }

    public Products findById(Long id) {
        Optional<Products> product = repository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find product with id: " + id);
        }
        return product.get();
    }

    public Page<ProductResponse> findAllWithPage(Pageable pageable) {
        Page<Products> responses = repository.findAll(pageable);
        return mapEntityPageIntoDtoPage(responses, ProductResponse.class);
    }


    public void recordDataToDb() throws IOException {
        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/PRODUTOS.csv";

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

    // Generic convertion Page<Entity> to Page<Dto>
    private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
        return entities.map(objectEntity -> mapper.map(objectEntity, dtoClass));
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
