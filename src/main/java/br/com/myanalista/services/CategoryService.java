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
                int index_3 = line.indexOf(";", index_2 + 1);


                Categories categoryGrand = Categories.builder()
                        .name(line.substring(index_2 + 1).trim())
                        .build();
               Categories grand =  repository.save(categoryGrand);

                Categories categoryFather = Categories.builder()
                        .name(line.substring(index_1 + 1, index_2).trim())
                        .parent(seekAndSave(grand))
                        .build();
             Categories father =    repository.save(categoryFather);

                Categories categorySon = Categories.builder()
                        .name(line.substring(0, index_1).trim())
                        .parent(seekAndSave(father))
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
