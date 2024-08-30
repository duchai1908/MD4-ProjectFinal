package com.ra.md4projectapi.controller.admin;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.FormCategory;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;
    // List
    @GetMapping
    public ResponseEntity<?> getAllCategory(@PageableDefault(page = 0,size = 2, sort = "id", direction = Sort.Direction.ASC)Pageable pageable, @RequestParam(defaultValue = "" )String search) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(categoryService.getAllCategories(pageable,search), HttpStatus.OK),HttpStatus.OK);
    }
    // Get Category By Id
    @GetMapping("/{cateId}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long cateId) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(categoryService.findById(cateId), HttpStatus.OK),HttpStatus.OK);
    }
    // Add Category
    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody FormCategory formCategory) throws DataExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(categoryService.save(formCategory), HttpStatus.CREATED),HttpStatus.CREATED);
    }
    // Update Category
    @PutMapping("/{cateId}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody FormCategory formCategory, @PathVariable Long cateId) throws DataExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(categoryService.update(formCategory,cateId), HttpStatus.OK),HttpStatus.OK);
    }
    //Delete Category
    @DeleteMapping("/{cateId}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long cateId) {
        categoryService.deleteById(cateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
