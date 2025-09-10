package leonardo_keler.salesflow_api_register.entity;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private String cpf;

    @NotNull
    private String phone;
}
