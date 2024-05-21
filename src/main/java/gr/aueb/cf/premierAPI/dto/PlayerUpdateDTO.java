package gr.aueb.cf.premierAPI.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerUpdateDTO extends BaseDTO{
    @NotBlank(message = "First name is mandatory")
    private String firstname;

    @NotBlank(message = "Last name is mandatory")
    private String lastname;

    @Min(value = 16, message = "Age should be greater than 18")
    @Max(value = 100, message = "Age should be less than 100")
    private int age;

    private String position;

    @NotNull(message = "Team id is mandatory")
    @Min(value = 1, message = "Team id should be greater than 0")
    private int team;
}
