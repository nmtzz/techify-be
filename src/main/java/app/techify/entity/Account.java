package app.techify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account")
public class Account {
    @Id
    @Size(max = 20)
    @Nationalized
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Size(max = 50)
    @NotNull
    @Nationalized
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Size(max = 255)
    @Nationalized
    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 20)
    @NotNull
    @Nationalized
    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Size(max = 255)
    @Nationalized
    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "last_login")
    private Instant lastLogin;

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
    private Boolean status = true;

    @OneToOne(mappedBy = "account")
    private Customer customer;

    @OneToOne(mappedBy = "account")
    private Staff staff;
}
