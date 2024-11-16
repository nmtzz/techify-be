package app.techify.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class GetProductDto {
    private String id;
    private String name;
    private String category;
    private String thumbnail;
    private String brand;
    private String origin;
    private String unit;
    private String serial;
    private Integer warranty;
    private String description;
    private BigDecimal sellPrice;
    private BigDecimal promotionPrice;
    private String colors;
    private String images;
    private String attributes;
}
