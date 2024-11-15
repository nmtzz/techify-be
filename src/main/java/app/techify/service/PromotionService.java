package app.techify.service;

import app.techify.entity.Promotion;
import app.techify.repository.ProductPromotionRepository;
import app.techify.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductPromotionRepository productPromotionRepository;


    // Create a new promotion
    public Promotion createPromotion(Promotion promotion) {
        try {
            return promotionRepository.save(promotion);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create promotion: " + e.getMessage(), e);
        }
    }

    // Retrieve all promotions
    public List<Promotion> getAllPromotions() {
        try {
            return promotionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve promotions: " + e.getMessage(), e);
        }
    }

    // Retrieve a promotion by ID
    public Promotion getPromotionById(Integer id) {
        try {
            return promotionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Promotion not found with ID: " + id));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving promotion: " + e.getMessage(), e);
        }
    }

    // Update an existing promotion
    public Promotion updatePromotion(Promotion promotion) {
        try {
            if (!promotionRepository.existsById(promotion.getId())) {
                throw new RuntimeException("Promotion not found with ID: " + promotion.getId());
            }
            return promotionRepository.save(promotion);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update promotion: " + e.getMessage(), e);
        }
    }

    // Delete a promotion
    @Transactional
    public void deletePromotion(Integer id) {
        try {
            if (!promotionRepository.existsById(id)) {
                throw new RuntimeException("Promotion not found with ID: " + id);
            }

            // Xóa tất cả các bản ghi liên quan trong bảng productPromotion
            productPromotionRepository.deleteByPromotionId(id);

            // Xóa promotion
            promotionRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete promotion: " + e.getMessage(), e);
        }
    }
}