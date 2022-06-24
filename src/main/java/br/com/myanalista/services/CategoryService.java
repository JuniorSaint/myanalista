package br.com.myanalista.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import br.com.myanalista.models.entities.*;
import br.com.myanalista.models.request.CategoryImportFileRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
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
    public CategoryResponse save(CategoryRequestPost categoryRequest) {
        Categories categoryEntity = new Categories();
        mapper.map(categoryRequest, categoryEntity);
        Categories categoryCreated = repository.save(categoryEntity);
        CategoryResponse categoryResponse = new CategoryResponse();
        mapper.map(categoryCreated, categoryResponse);
        return categoryResponse;
    }

    @Transactional
    public CategoryResponse update(CategoryRequestPut categoryRequest) {
        Categories categoryEntity = new Categories();
        mapper.map(categoryRequest, categoryEntity);
        Categories categoryUpdate = repository.save(categoryEntity);
        CategoryResponse categoryResponse = new CategoryResponse();
        mapper.map(categoryUpdate, categoryResponse);
        return categoryResponse;
    }

    @Transactional
    public String delete(Long id) {
        Optional<Categories> category = repository.findById(id);
        if (!category.isPresent()) {
            throw new BusinessException("Category not found with id: " + id);
        }
        repository.deleteById(id);
        return "Category deleted with success";
    }

    public CategoryResponse findById(Long id) {
        Optional<Categories> category = repository.findById(id);
        if (category.isEmpty()) {
            throw new BusinessException("It's not possible find category with id: " + id);
        }
        CategoryResponse categoryResp = new CategoryResponse();
        mapper.map(category.get(), categoryResp);
        return categoryResp;
    }

    public Categories findByIdEntity(Long id) {
        Optional<Categories> category = repository.findById(id);
        return category.get();
    }


    public void recordDataToDb() throws IOException {

        String path = "/Volumes/Arquivo/SpringBoot/myanalista/src/main/java/br/com/myanalista/files/CATEGORIAS.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String line = br.readLine(); // this first line will be discarted, because is the header.
            line = br.readLine();

            while (line != null) {

                int index_1 = line.indexOf(";");
                int index_2 = line.indexOf(";", index_1 + 1);

                CategoryImportFileRequest categorySon = CategoryImportFileRequest.builder()
                        .categorySon(seekAndSave(line.substring(0, index_1).trim()))
                        .categoryFather(seekAndSave(line.substring(index_1 + 1, index_2).trim()))
                        .categoryGrand(seekAndSave(line.substring(index_2 + 1).trim()))
                        .build();
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new IOException("Error to read file " + e.getMessage());
        }
    }

    private String seekAndSave(String cat) {
        Optional<Categories> response = repository.findCategoryByName(cat);
        Categories categories = new Categories();
        if (!response.isPresent()) {
            categories.setName(cat);
            Categories responseSave =  repository.save(categories);
            return responseSave.getName();
        }
        return response.get().getName();
    }
}
