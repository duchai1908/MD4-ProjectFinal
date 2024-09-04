package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.ProductRequest;
import com.ra.md4projectapi.model.dto.response.OutstandingProductResponse;
import com.ra.md4projectapi.model.dto.response.ProductDTO;
import com.ra.md4projectapi.model.entity.Category;
import com.ra.md4projectapi.model.entity.Product;
import com.ra.md4projectapi.model.repository.IProductRepository;
import com.ra.md4projectapi.model.service.ICategoryService;
import com.ra.md4projectapi.model.service.IProductService;
import com.ra.md4projectapi.model.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final UploadService uploadService;
    private final ICategoryService categoryService;
    private final IProductRepository productRepository;

    //Get List Product ( Search, Pagination, Sort )
    @Override
    public Page<Product> getProducts(Pageable pageable, String search) {
        Page<Product> products;
        if(search == null || search.isEmpty()) {
            products = productRepository.findAll(pageable);
        }else{
            products = productRepository.findProductByNameContainsIgnoreCase(search,pageable);
        }
        return products;
    }

    // Add New Product
    @Override
    public Product save(ProductRequest productRequest) throws DataExistException {
        if(existsByProductName(productRequest.getName())){
            throw new DataExistException("Product name is exists","name");
        }
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .image(uploadService.uploadFileToServer(productRequest.getImage()))
                .category(categoryService.findById(productRequest.getCategory()))
                .status(true)
                .created_at(new Date())
                .updated_at(new Date())
                .build();
        return productRepository.save(product);
    }

    // Check Product Exist By Name
    @Override
    public boolean existsByProductName(String productName) {
        return productRepository.existsByName(productName);
    }

    // Find Product By Id
    @Override
    public Product findByProductId(Long productId) {
        return productRepository.findById(productId).orElseThrow(()-> new NoSuchElementException("Product not found"));
    }

    // Update Product
    @Override
    public Product update(ProductRequest productRequest, Long id) throws DataExistException {
        Product product = findByProductId(id);
        String multipartFile;
        if(!Objects.equals(productRequest.getName(), product.getName()) && existsByProductName(productRequest.getName())){
            throw new DataExistException("Product name is exists","name");
        }
        if(productRequest.getImage()!=null && productRequest.getImage().getSize() >0){
             multipartFile = uploadService.uploadFileToServer(productRequest.getImage());
        }else{
            multipartFile = product.getImage();
        }
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStock(productRequest.getStock());
        product.setCategory(categoryService.findById(productRequest.getCategory()));
        product.setUpdated_at(new Date());
        product.setStatus(productRequest.getStatus());
        product.setImage(multipartFile);
        return productRepository.save(product);
    }

    // Delete Product
    @Override
    public void deleteById(Long id) {
        Product product = findByProductId(id);
        productRepository.delete(product);
    }

    @Override
    public Page<Product> findByCategoryId(Long categoryId, Pageable pageable) {
        Page<Product> products;
        if(categoryId == null){
            products = productRepository.findAll(pageable);
        }
        Category category = categoryService.findById(categoryId);
        products = productRepository.findProductByCategoryId(categoryId,pageable);
        return products;
    }

    @Override
    public List<Product> getLatestProducts() {
        return productRepository.findLatestProducts(PageRequest.of(0,5));
    }

    @Override
    public Page<Product> findProductsSold(Pageable pageable) {
        return productRepository.findProductByStatusTrue(pageable);
    }

    @Override
    public List<Product> findProductByNameOrDescription(String search) {
        return productRepository.findByNameOrDescriptionContaining(search);
    }

    @Override
    public List<ProductDTO> findSellProduct() {
        return productRepository.sellestProducts(PageRequest.of(0,5));
    }

    @Override
    public List<OutstandingProductResponse> findOutstandingProduct() {
        return productRepository.outstandingProducts(PageRequest.of(0,5));
    }
}
