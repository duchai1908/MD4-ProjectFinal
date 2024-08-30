package com.ra.md4projectapi.controller.author;

import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryUserController {
    private final ICategoryService categoryService;
    @GetMapping("/list")
    public ResponseEntity<?> getProductsSold(@PageableDefault(page = 0,size = 4, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(categoryService.findByStatus(pageable), HttpStatus.OK),HttpStatus.OK);
    }
}
