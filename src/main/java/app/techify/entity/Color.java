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
@Table(name = "color")
public class Color {
    @Id
    @Column(name = "id", columnDefinition = "tinyint not null")
    private Short id;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "color_json", nullable = false)
    private String colorJson;

}