package leonardo_keler.salesflow_api_register.entity;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @NotBlank
    private String streetName;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String complement;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotNull
    private Integer number;
}
