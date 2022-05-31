package br.com.myanalista.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.myanalista.exceptions.BusinessException;
import br.com.myanalista.models.entities.ProductsEntity;
import br.com.myanalista.models.request.ProductRequestPost;
import br.com.myanalista.models.request.ProductRequestPut;
import br.com.myanalista.models.response.ProductResponse;
import br.com.myanalista.repositories.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository repository;

  @Autowired
  private ModelMapper mapper;

  @Transactional
  public ProductResponse save(ProductRequestPost productRequest) {
      ProductsEntity productEntity = new ProductsEntity();
      mapper.map(productRequest, productEntity);
      ProductsEntity productCreated = repository.save(productEntity);
      ProductResponse productResponse = new ProductResponse();
      mapper.map(productCreated, productResponse);
      return productResponse;    
  }

  @Transactional
  public ProductResponse update(ProductRequestPut contactRequest) {
    ProductsEntity productEntity = new ProductsEntity();
      mapper.map(contactRequest, productEntity);
      ProductsEntity productUpdate = repository.save(productEntity);
      ProductResponse productResponse = new ProductResponse();
      mapper.map(productUpdate, productResponse);
      return productResponse;    
  }

  @Transactional
  public String delete(Long id) throws Exception {
      Optional<ProductsEntity> product = repository.findById(id);
      if (!product.isPresent()) {
        throw new BusinessException("Product not found with id: " + id);
      }
      repository.deleteById(id);
      return "Product deleted with success";    
  }

  public ProductResponse findById(Long id){
    Optional<ProductsEntity> product = repository.findById(id);
    if(!product.isEmpty()){
      throw new BusinessException("It's not possible find product with id: " + id);
    }
    ProductResponse contactResp = new ProductResponse();
    mapper.map(product, contactResp);
    return contactResp;
  }
}
