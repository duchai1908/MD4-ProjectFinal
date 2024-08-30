package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.ProductRequest;
import com.ra.md4projectapi.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Page<Product> getProducts(Pageable pageable, String search);
    Product save(ProductRequest productRequest) throws DataExistException;
    boolean existsByProductName(String productName);
    Product findByProductId(Long productId);
    Product update(ProductRequest productRequest,Long id) throws DataExistException;
    void deleteById(Long id);
    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);
    List<Product> getLatestProducts();
    Page<Product> findProductsSold(Pageable pageable);
    List<Product> findProductByNameOrDescription(String search);
}
