package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import br.com.myanalista.configs.Utils;
import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.*;
import br.com.myanalista.models.response.CategoryMainResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.request.CategoryRequestPost;
import br.com.myanalista.models.request.CategoryRequestPut;
import br.com.myanalista.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductService serviceProduct;
    @Autowired
    private Utils utils;

    @Transactional
    public ResponseEntity<Categories> save(CategoryRequestPost categoryRequest) {
        Categories categoriesResult = null;
        if (categoryRequest.getCategory() != null) {
            Optional<Categories> result = repository.findById(categoryRequest.getCategory().getId());
            categoriesResult = result.get();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("There isn't category parent with id: " + categoryRequest.getCategory().getId());
            }
        }
        categoryRequest.setCategory(categoriesResult);
        Categories categoryEntity = new Categories();
        mapper.map(categoryRequest, categoryEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoryEntity));
    }

    @Transactional
    public ResponseEntity<Categories> update(CategoryRequestPut categoryRequest) {
        Categories categoriesResult = null;
        Optional<Categories> categorySeek = repository.findById(categoryRequest.getId());
        if (categorySeek.isEmpty()) {
            throw new EntityNotFoundException("There isn't category with id: " + categoryRequest.getId());
        }
        if (categoryRequest.getCategory() != null) {
            Optional<Categories> result = repository.findById(categoryRequest.getCategory().getId());
            categoriesResult = result.get();
            if (result.isEmpty()) {
                throw new EntityNotFoundException("There isn't category parent with id: " + categoryRequest.getCategory().getId());
            }
        }
        categoryRequest.setCategory(categoriesResult);
        Categories categoryEntity = new Categories();
        mapper.map(categoryRequest, categoryEntity);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoryEntity));
    }

    @Transactional
    public ResponseEntity<Object> delete(Long id) {
        Optional<Categories> category = repository.findById(id);
        if (!category.isPresent()) {
            throw new EntityNotFoundException("Category not found with id: " + id);
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted with success!");
    }

    public ResponseEntity<Categories> findById(Long id) {
        Optional<Categories> category = repository.findById(id);
        if (category.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find category with id: " + id);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(category.get());
    }

    public ResponseEntity<Page<Categories>> findAllWithPageSeek(String search, Pageable pageable) {
        Page<Categories> responses = repository.findByName(search, pageable);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responses);
    }

    public ResponseEntity<List<CategoryMainResponse>> listCategoryMain() {
        List<Categories> listCategories = repository.findAll();
        List<Categories> response = listCategories.stream()
                .filter(cat -> cat.getCategory() == null)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(utils.mapListIntoDtoList(response, CategoryMainResponse.class));
    }

    public void recordDataToDb() throws IOException {

        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/imported/CATEGORIAS.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // this first line will be discarted, because is the header.
            line = br.readLine();

            while (line != null) {
                int index_1 = line.indexOf(";");
                int index_2 = line.indexOf(";", index_1 + 1);

                Categories categoryGrand = firstImport(line.substring(index_2 + 1).trim());

                Categories categoryFather = secondImport(line.substring(index_1 + 1, index_2).trim(), line.substring(index_2 + 1).trim());

                Categories categorySon = secondImport(line.substring(0, index_1).trim(), line.substring(index_1 + 1, index_2).trim());

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error to read file " + e.getMessage());
        }
    }

    private Categories firstImport(String cat) {
        Optional<Categories> category = repository.findByCategoryName(cat);
        if (category.isPresent()) {
            return category.get();
        }
        return repository.save(Categories.builder().name(cat).build());
    }

    private Categories secondImport(String cat, String catParent) {
        Optional<Categories> category = repository.findByCategoryName(cat);
        if (category.isPresent()) {
            return category.get();
        }
        Optional<Categories> categoryParent = repository.findByCategoryName(catParent);
        return repository.save(Categories.builder().name(cat).category(categoryParent.get()).build());
    }
}
