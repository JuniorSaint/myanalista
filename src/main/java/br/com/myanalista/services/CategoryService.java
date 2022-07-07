package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.myanalista.exceptions.EntityNotFoundException;
import br.com.myanalista.models.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.myanalista.models.request.CategoryRequestPost;
import br.com.myanalista.models.request.CategoryRequestPut;
import br.com.myanalista.models.response.CategoryResponse;
import br.com.myanalista.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ProductService serviceProduct;

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

    public ResponseEntity<CategoryResponse> findById(Long id) {
        Optional<Categories> category = repository.getByIdPerson(id);
        if (category.isEmpty()) {
            throw new EntityNotFoundException("It's not possible find category with id: " + id);
        }
        CategoryResponse categoryResponse = new CategoryResponse();
        mapper.map(category.get(), categoryResponse);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryResponse);

    }

    public void recordDataToDb() throws IOException {

        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/CATEGORIAS.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // this first line will be discarted, because is the header.
            line = br.readLine();

            while (line != null) {

                int index_1 = line.indexOf(";");
                int index_2 = line.indexOf(";", index_1 + 1);

                Categories categoryGrand = Categories.builder()
                        .name(line.substring(index_2 + 1).trim())
                        .build();
                Categories grand = repository.save(categoryGrand);

                Categories categoryFather = Categories.builder()
                        .name(line.substring(index_1 + 1, index_2).trim())
                        .category(seekAndSave(grand))
                        .build();
                Categories father = repository.save(categoryFather);

                Categories categorySon = Categories.builder()
                        .name(line.substring(0, index_1).trim())
                        .category(seekAndSave(father))
                        .build();
                repository.save(categorySon);

                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error to read file " + e.getMessage());
        }
    }

    private Categories seekAndSave(Categories category) {
        Categories categories = new Categories();
        if (category == null) {
            return categories;
        }
        Optional<Categories> response = repository.findById(category.getId());

        if (response.isEmpty()) {
            return categories;
        }
        return response.get();
    }
}
