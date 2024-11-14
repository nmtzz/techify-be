package app.techify.controller;

import app.techify.dto.GetProductDto;
import app.techify.dto.ProductDto;
import app.techify.entity.Product;
import app.techify.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@Valid @RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        try {
            productService.updateProduct(id, product);
            return ResponseEntity.ok("Updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Khong the cap nhat san pham nay");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Deleted");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Khong the xoa san pham nay");
        }
    }
    @GetMapping
    public ResponseEntity<List<GetProductDto>> getAllProducts() {
        List<GetProductDto> products = productService.getAllProductsWithDetails();
        return ResponseEntity.ok(products);
    }
}
