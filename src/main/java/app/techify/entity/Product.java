package app.techify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Nationalized
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    @Nationalized
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 255)
    @Nationalized
    @Column(name = "thumbnail")
    private String thumbnail;

    @Size(max = 50)
    @Nationalized
    @Column(name = "brand", length = 50)
    private String brand;


    @Nationalized
    @Column(name = "origin", nullable = false, length = 50)
    private String origin;

    @Nationalized
    @Column(name = "unit", nullable = false, length = 20)
    private String unit;

    @Nationalized
    @Column(name = "serial", length = 50)
    private String serial;

    @Column(name = "warranty")
    private Integer warranty;

    @Column(name = "buy_price", nullable = false)
    private BigDecimal buyPrice;

    @Column(name = "sell_price", nullable = false)
    private BigDecimal sellPrice;

    @Column(name = "tax", nullable = false)
    private Double tax;

    @Nationalized
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_id")
    private Attribute attribute;


    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<ProductPromotion> productPromotions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<Review> reviews = new LinkedHashSet<>();

}