package app.techify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 255)
    @Nationalized
    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 40)
    @NotNull
    @Nationalized
    @Column(name = "role", nullable = false, length = 40)
    private String role;

    @Size(max = 255)
    @Nationalized
    @Column(name = "refresh_token")
    private String refreshToken;

    @Size(max = 50)
    @Nationalized
    @Column(name = "google_id", length = 50)
    private String googleId;

    @Size(max = 50)
    @Nationalized
    @Column(name = "facebook_id", length = 50)
    private String facebookId;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;

    @OneToMany(mappedBy = "account")
    private Set<Customer> customers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Staff> staff = new LinkedHashSet<>();

}
