package app.techify.service;

import app.techify.dto.ParentCategoryDto;
import app.techify.entity.ParentCategory;
import app.techify.repository.ParentCategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentCategoryService {
    private final ParentCategoryRepository parentCategoryRepository;

    public void createParentCategory(ParentCategoryDto parentCategoryDto) {
        ParentCategory parentCategory = ParentCategory.builder()
                .name(parentCategoryDto.getName())
                .build();
        parentCategoryRepository.save(parentCategory);
    }

    public void updateParentCategory(Integer id, ParentCategoryDto parentCategoryDto) {
        ParentCategory parentCategory = parentCategoryRepository.findById(id).orElseThrow();
        parentCategory.setName(parentCategoryDto.getName());
        parentCategoryRepository.save(parentCategory);
    }

    public void deleteParentCategory(Integer id) {
        parentCategoryRepository.deleteById(id);
    }
}
