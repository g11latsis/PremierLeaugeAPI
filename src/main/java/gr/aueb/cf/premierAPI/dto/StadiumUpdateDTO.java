package gr.aueb.cf.premierAPI.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StadiumUpdateDTO extends BaseDTO {

        @NotBlank(message = "Stadium name is mandatory")
        private String name;

        @NotNull(message = "Stadium capacity is mandatory")
        @Min(value = 100, message = "Capacity should be greater than 100")
        @Max(value = 120000, message = "Capacity should be less than 120000")
        private int capacity;
}
