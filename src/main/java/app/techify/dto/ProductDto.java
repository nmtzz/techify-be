package app.techify.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.Nationalized;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ProductDto {

    @Size(max = 20, message = "Id phai nho hon 20 ky tu")
    @NotBlank(message = "Id khong duoc de trong")
    private String id;

    @NotNull(message = "Khong duoc de trong danh muc san pham")
    private Integer category;
    @Size(max = 50)
    @NotBlank(message = "Ten khong duoc de trong")
    private String name;

    private MultipartFile thumbnail;
    @Size(max = 50, message = "Ten phai nho hon 50 ky tu")
    private String brand;
    @Size(max = 50, message = "Ten phai nho hon 50 ky tu")
    @NotBlank(message = "Ten khong duoc de trong")
    private String origin;
    @Size(max = 20, message = "Ten phai nho hon 20 ky tu")
    @NotBlank(message = "Ten khong duoc de trong")
    private String unit;
    @Size(max = 50, message = "Ten phai nho hon 50 ky tu")
    private String serial;
    @NotNull(message = "Gia mua khong duoc de trong")
    private BigDecimal buyPrice;
    @NotNull(message = "Gia ban khong duoc de trong")
    private BigDecimal sellPrice;
    @NotNull(message = "Thue khong duoc de trong")
    private Double tax;


}
