package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.Categories;
import br.com.myanalista.models.entities.Products;
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
    // Products entityProduct = serviceProduct.findByIdEntity(categoryRequest.getProduct().getId());
    // Categories entityCategory = findByIdEntity(categoryRequest.getCategory().getId());
    Categories categoryEntity = new Categories();
    mapper.map(categoryRequest, categoryEntity);
    // categoryEntity.setCategory(entityCategory );
    // categoryEntity.setProduct(entityProduct);
    Categories categoryCreated = repository.save(categoryEntity);
    CategoryResponse categoryResponse = new CategoryResponse();
    mapper.map(categoryCreated, categoryResponse);
    return categoryResponse;
  }

  @Transactional
  public CategoryResponse update(CategoryRequestPut categoryRequest) {
    // Products entityProduct = serviceProduct.findByIdEntity(categoryRequest.getProduct().getId());
    // Categories entityCategory = findByIdEntity(categoryRequest.getCategory().getId());
    Categories categoryEntity = new Categories();
    mapper.map(categoryRequest, categoryEntity);
    // categoryEntity.setCategory(entityCategory);
    // categoryEntity.setProduct(entityProduct);
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
}
