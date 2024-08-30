package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    Page<Category> findCategoryByNameContainsIgnoreCase(String cateName, Pageable pageable);
    Page<Category> findCategoryByStatusTrue(Pageable pageable);
}
