package app.techify.controller;

import app.techify.dto.ParentCategoryDto;
import app.techify.service.ParentCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/parent_category")
@RequiredArgsConstructor
public class ParentCategoryController {
    private final ParentCategoryService parentCategoryService;

    @PostMapping("")
    public ResponseEntity<String> createParentCategory(@Valid @RequestBody ParentCategoryDto parentCategoryDto) {
        parentCategoryService.createParentCategory(parentCategoryDto);
        return ResponseEntity.ok("Created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateParentCategory(@PathVariable Integer id, @Valid @RequestBody ParentCategoryDto parentCategoryDto) {
        parentCategoryService.updateParentCategory(id, parentCategoryDto);
        return ResponseEntity.ok("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParentCategory(@PathVariable Integer id) {
        parentCategoryService.deleteParentCategory(id);
        return ResponseEntity.ok("Deleted");
    }
}
