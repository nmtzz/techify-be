package app.techify.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Data
public class GetProductDto {
    private String id;
    private String name;
    private String thumbnail;
    private String brand;
    private String origin;
    private String unit;
    private BigDecimal sellPrice;
    private BigDecimal promotionPrice;
    private List<String> colors;
    private List<String> images;
    private Map<String, String> attributes;
}
