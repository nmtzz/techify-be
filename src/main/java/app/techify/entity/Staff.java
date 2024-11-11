package app.techify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

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

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Account account;

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

    @Size(max = 20)
    @Nationalized
    @Column(name = "phone", length = 20)
    private String phone;

    @Size(max = 255)
    @Nationalized
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "staff")
    private Set<Order> orders = new LinkedHashSet<>();

}