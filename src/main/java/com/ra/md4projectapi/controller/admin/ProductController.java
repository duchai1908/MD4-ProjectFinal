package com.ra.md4projectapi.controller.admin;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.ProductRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/products")
public class ProductController {
    private final IProductService productService;
    @GetMapping
    public ResponseEntity<?> getAllProducts(@PageableDefault(page = 0,size = 2, sort = "id",direction = Sort.Direction.ASC) Pageable pageable,@RequestParam(defaultValue = "" ) String search) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.getProducts(pageable,search),HttpStatus.OK),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @ModelAttribute ProductRequest productRequest) throws DataExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.save(productRequest), HttpStatus.CREATED),HttpStatus.CREATED);
    }
    @GetMapping("/{proId}")
    public ResponseEntity<?> getProductById(@PathVariable Long proId) {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.findByProductId(proId),HttpStatus.OK),HttpStatus.OK);
    }
    @PutMapping("/{proId}")
    public ResponseEntity<?>  updateProduct(@Valid @ModelAttribute ProductRequest productRequest, @PathVariable Long proId) throws DataExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(productService.update(productRequest,proId),HttpStatus.OK),HttpStatus.OK);
    }
    @DeleteMapping("/{proId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long proId) {
        productService.deleteById(proId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
