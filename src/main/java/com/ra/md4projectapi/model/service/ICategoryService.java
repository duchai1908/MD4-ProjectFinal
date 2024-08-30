package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.FormCategory;
import com.ra.md4projectapi.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {
    Page<Category> getAllCategories(Pageable pageable,String search);
    boolean existByCategoryName(String categoryName);
    Category findById(Long id);
    Category save(FormCategory formCategory) throws DataExistException;
    Category update(FormCategory formCategory,Long id) throws DataExistException;
    void deleteById(Long id);
    Page<Category> findByStatus(Pageable pageable);

}
