package app.techify.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}