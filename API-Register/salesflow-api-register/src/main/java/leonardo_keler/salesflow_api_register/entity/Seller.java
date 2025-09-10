package leonardo_keler.salesflow_api_register.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@AttributeOverrides({
        @AttributeOverride(name = "email", column = @Column(unique = true)),
        @AttributeOverride(name = "cpf", column = @Column(unique = true))
})
public class Seller extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)

    @NotBlank
    private String password;

    public Seller(String name, String email, String cpf, String phone, String password) {}
}
