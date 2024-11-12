package app.techify.service;

import app.techify.dto.ProductDto;
import app.techify.entity.*;
import app.techify.repository.AttributeRepository;
import app.techify.repository.ColorRepository;
import app.techify.repository.ImageRepository;
import app.techify.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;
    private final ImageRepository imageRepository;
    private final AttributeRepository attributeRepository;
    private final CloudinaryService cloudinaryService;
    private final ObjectMapper objectMapper;

    public void createProduct(ProductDto productDto) {
        Color color = colorRepository.save(Color.builder().colorJson(productDto.getColor()).build());
        Image image = imageRepository.save(Image.builder().imageJson(productDto.getThumbnail()).build());
        Attribute attribute = attributeRepository.save(Attribute.builder().attributeJson(productDto.getAttribute()).build());
        Product product = Product.builder()
                .id(productDto.getId())
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
        productRepository.updateProductById(id, product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
