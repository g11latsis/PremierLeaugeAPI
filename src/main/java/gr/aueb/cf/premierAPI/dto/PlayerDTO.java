package gr.aueb.cf.premierAPI.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDTO extends BaseDTO{

        @NotBlank(message = "First name is mandatory")
        private String firstname;

        @NotBlank(message = "Last name is mandatory")
        private String lastname;

        @Min(value = 16, message = "Age should be greater than 18")
        @Max(value = 100, message = "Age should be less than 100")
        private int age;

        private String position;

        private String team;
}
