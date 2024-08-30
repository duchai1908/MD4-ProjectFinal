package com.ra.md4projectapi.controller.author;


import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductUserController {
    private final IProductService productService;

    //Get Product by CategoryId
    @GetMapping("/category/{cateId}")
    public ResponseEntity<?> getProductByCategoryId(@PageableDefault(page = 0,size = 2, sort = "id", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable Long cateId) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.findByCategoryId(cateId,pageable), HttpStatus.OK),HttpStatus.OK);
    }

    // Get New Products
    @GetMapping("/newProducts")
    public ResponseEntity<?> getNewProducts() {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.getLatestProducts(),HttpStatus.OK),HttpStatus.OK);
    }

    // List of products sold
    @GetMapping("/list")
    public ResponseEntity<?> getProductsSold(@PageableDefault(page = 0,size = 4, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.findProductsSold(pageable), HttpStatus.OK),HttpStatus.OK);
    }

    // Get Product By Name Or Description
    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam(defaultValue = "" )String search) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.findProductByNameOrDescription(search), HttpStatus.OK),HttpStatus.OK);
    }
}
