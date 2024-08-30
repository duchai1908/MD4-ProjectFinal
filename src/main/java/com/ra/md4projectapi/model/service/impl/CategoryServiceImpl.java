package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.FormCategory;
import com.ra.md4projectapi.model.entity.Category;
import com.ra.md4projectapi.model.repository.ICategoryRepository;
import com.ra.md4projectapi.model.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    @Override
    public Page<Category> getAllCategories( Pageable pageable, String search) {
        Page<Category> categoryPage;
        if(search == null || search.isEmpty()) {
            categoryPage = categoryRepository.findAll(pageable);
        }else{
            categoryPage = categoryRepository.findCategoryByNameContainsIgnoreCase(search,pageable);
        }
        return categoryPage;
    }

    @Override
    public boolean existByCategoryName(String categoryName) {
        return categoryRepository.existsByName(categoryName);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Category not found"));
    }

    @Override
    public Category save(FormCategory formCategory) throws DataExistException {
        Category category = modelMapper.map(formCategory, Category.class);
        if(existByCategoryName(formCategory.getName())) {
            throw new DataExistException("Category name is exists","name");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(FormCategory formCategory, Long id) throws DataExistException {
        Category category = findById(id);
        if(!Objects.equals(category.getName(), formCategory.getName()) && existByCategoryName(formCategory.getName())) {
            throw new DataExistException("Category name is exists","name");
        }
        category.setName(formCategory.getName());
        category.setStatus(formCategory.getStatus());
        category.setDescription(formCategory.getDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }

    @Override
    public Page<Category> findByStatus(Pageable pageable) {
        return categoryRepository.findCategoryByStatusTrue(pageable);
    }
}
