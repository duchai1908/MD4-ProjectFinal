package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.model.dto.response.OutstandingProductResponse;
import com.ra.md4projectapi.model.dto.response.ProductDTO;
import com.ra.md4projectapi.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
    Page<Product> findProductByNameContainsIgnoreCase(String proName, Pageable pageable);
    Page<Product> findProductByCategoryId(Long categoryId, Pageable pageable);
    @Query("SELECT p FROM Product p ORDER BY p.created_at DESC")
    List<Product> findLatestProducts(Pageable pageable);
    Page<Product> findProductByStatusTrue(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% OR p.description LIKE %:keyword%")
    List<Product> findByNameOrDescriptionContaining(@Param("keyword") String search);
    @Query("SELECT new com.ra.md4projectapi.model.dto.response.ProductDTO(p,SUM(od.quantity)) FROM Product p INNER JOIN OrderDetail od ON p.id = od.product.id GROUP BY p.name ORDER BY SUM(od.quantity) DESC")
    List<ProductDTO> sellestProducts(Pageable pageable);
    @Query("SELECT new com.ra.md4projectapi.model.dto.response.OutstandingProductResponse(p,AVG(r.rating))  FROM Product p LEFT JOIN Rating r ON p.id = r.product.id GROUP BY p.name ORDER BY AVG(r.rating) DESC")
    List<OutstandingProductResponse> outstandingProducts(Pageable pageable);
}
