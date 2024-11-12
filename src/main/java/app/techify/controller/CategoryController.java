package app.techify.controller;

import app.techify.dto.CategoryDto;
import app.techify.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<String> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("Deleted");
    }
}