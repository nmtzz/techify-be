package app.techify.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Size(max = 20)
    @Nationalized
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Size(max = 50)
    @Nationalized
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 255)
    @Nationalized
    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 50)
    @Nationalized
    @Column(name = "google_id", length = 50)
    private String googleId;

    @Size(max = 50)
    @Nationalized
    @Column(name = "facebook_id", length = 50)
    private String facebookId;

    @Size(max = 20)
    @Nationalized
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 20)
    @Nationalized
    @Column(name = "alt_phone", length = 20)
    private String altPhone;

    @Size(max = 50)
    @Nationalized
    @Column(name = "province", length = 50)
    private String province;

    @Size(max = 50)
    @Nationalized
    @Column(name = "district", length = 50)
    private String district;

    @Size(max = 50)
    @Nationalized
    @Column(name = "ward", length = 50)
    private String ward;

    @Size(max = 255)
    @Nationalized
    @Column(name = "address")
    private String address;

    @Size(max = 255)
    @Nationalized
    @Column(name = "alt_address")
    private String altAddress;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;

}