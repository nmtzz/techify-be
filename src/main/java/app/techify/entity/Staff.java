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

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {
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

    @Column(name = "dob")
    private LocalDate dob;

    @Size(max = 10)
    @Nationalized
    @Column(name = "gender", length = 10)
    private String gender;

    @Size(max = 20)
    @Nationalized
    @Column(name = "citizen_id", length = 20)
    private String citizenId;

    @Size(max = 50)
    @Nationalized
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 255)
    @Nationalized
    @Column(name = "password_hash")
    private String passwordHash;

    @Size(max = 20)
    @Nationalized
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 255)
    @Nationalized
    @Column(name = "address")
    private String address;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "role", nullable = false)
    private Boolean role = false;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "status", nullable = false)
    private Boolean status = false;

}