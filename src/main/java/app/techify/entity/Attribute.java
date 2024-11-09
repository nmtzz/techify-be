package app.techify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @Column(name = "id", columnDefinition = "tinyint not null")
    private Short id;

    @Size(max = 4000)
    @NotNull
    @Nationalized
    @Column(name = "attribute_json", nullable = false, length = 4000)
    private String attributeJson;

}