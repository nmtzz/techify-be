package app.techify.service;

import app.techify.dto.GetProductDto;
import app.techify.dto.ProductDto;
import app.techify.entity.*;
import app.techify.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final ImageRepository imageRepository;
    private final AttributeRepository attributeRepository;
    private final ObjectMapper objectMapper;
    private final ProductPromotionRepository productPromotionRepository;

    public void createProduct(ProductDto productDto) {
        Color color = colorRepository.save(Color.builder().colorJson(productDto.getColor()).build());
        Image image = imageRepository.save(Image.builder().imageJson(productDto.getImage()).build());
        Attribute attribute = attributeRepository.save(Attribute.builder().attributeJson(productDto.getAttribute()).build());
        Product product = Product.builder()
                .id(productDto.getProductId())
                .category(Category.builder().id(productDto.getCategory()).build())
                .name(productDto.getName())
                .thumbnail(productDto.getThumbnail())
                .brand(productDto.getBrand())
                .origin(productDto.getOrigin())
                .unit(productDto.getUnit())
                .serial(productDto.getSerial())
                .warranty(productDto.getWarranty())
                .buyPrice(productDto.getBuyPrice())
                .sellPrice(productDto.getSellPrice())
                .tax(productDto.getTax())
                .description(productDto.getDescription())
                .color(color)
                .image(image)
                .attribute(attribute)
                .build();
        productRepository.save(product);
    }

    public void updateProduct(String id, @Valid Product product) {
        Product productToUpdate = productRepository.findById(id).get();
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setName(product.getName());
        productToUpdate.setThumbnail(product.getThumbnail());
        productToUpdate.setBrand(product.getBrand());
        productToUpdate.setOrigin(product.getOrigin());
        productToUpdate.setUnit(product.getUnit());
        productToUpdate.setSerial(product.getSerial());
        productToUpdate.setWarranty(product.getWarranty());
        productToUpdate.setBuyPrice(product.getBuyPrice());
        productToUpdate.setSellPrice(product.getSellPrice());
        productToUpdate.setTax(product.getTax());
        productToUpdate.setDescription(product.getDescription());
        productRepository.save(productToUpdate);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public List<GetProductDto> getAllProductsWithDetails() {
        List<Product> products = productRepository.findAllWithDetails();
        return products.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private GetProductDto convertToDTO(Product product) {
        GetProductDto dto = new GetProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setThumbnail(product.getThumbnail());
        dto.setBrand(product.getBrand());
        dto.setOrigin(product.getOrigin());
        dto.setUnit(product.getUnit());
        dto.setSellPrice(product.getSellPrice());

        // Parse color JSON
        if (product.getColor() != null && product.getColor().getColorJson() != null) {
            try {
                dto.setColors(objectMapper.readValue(product.getColor().getColorJson(), List.class));
            } catch (JsonProcessingException e) {
                // Handle exception
            }
        }

        // Parse image JSON
        if (product.getImage() != null && product.getImage().getImageJson() != null) {
            try {
                dto.setImages(objectMapper.readValue(product.getImage().getImageJson(), List.class));
            } catch (JsonProcessingException e) {
                // Handle exception
            }
        }

        // Parse attribute JSON
        if (product.getAttribute() != null && product.getAttribute().getAttributeJson() != null) {
            try {
                dto.setAttributes(objectMapper.readValue(product.getAttribute().getAttributeJson(), Map.class));
            } catch (JsonProcessingException e) {
                // Handle exception
            }
        }

        // Calculate promotion price
        BigDecimal promotionPrice = calculatePromotionPrice(product.getId(), product.getSellPrice());
        dto.setPromotionPrice(promotionPrice);

        return dto;
    }

    private BigDecimal calculatePromotionPrice(String productId, BigDecimal sellPrice) {
        List<ProductPromotion> productPromotions = productPromotionRepository.findByProductIdWithPromotion(productId);
        BigDecimal lowestPrice = sellPrice;

        for (ProductPromotion pp : productPromotions) {
            Promotion promotion = pp.getPromotion();
            if (isPromotionActive(promotion)) {
                BigDecimal discountedPrice;
                if (promotion.getDiscountType()) {
                    // Percentage discount
                    BigDecimal discountFactor = BigDecimal.ONE.subtract(
                            BigDecimal.valueOf(promotion.getDiscountValue()).divide(BigDecimal.valueOf(100)));
                    discountedPrice = sellPrice.multiply(discountFactor);
                } else {
                    // Fixed amount discount
                    discountedPrice = sellPrice.subtract(BigDecimal.valueOf(promotion.getDiscountValue()));
                }
                discountedPrice = discountedPrice.max(BigDecimal.ZERO);
                if (discountedPrice.compareTo(lowestPrice) < 0) {
                    lowestPrice = discountedPrice;
                }
            }
        }
        return lowestPrice;
    }

    private boolean isPromotionActive(Promotion promotion) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = LocalDateTime.ofInstant(promotion.getStartDate(), ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(promotion.getEndDate(), ZoneId.systemDefault());
        return now.isAfter(startDate) && now.isBefore(endDate);
    }
}
