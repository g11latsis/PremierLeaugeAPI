package gr.aueb.cf.premierAPI.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamUpdateDTO extends BaseDTO {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Stadium ID is mandatory")
    @Min(value = 1, message = "Stadium ID must be greater than 0")
    private Long stadiumId;

    @NotNull(message = "Coach ID is mandatory")
    @Min(value = 1, message = "Coach ID must be greater than 0")
    private Long coachId;

    private List<@NotNull(message = "Player ID cannot be null")
    @Min(value = 1, message = "Player ID must be greater than 0") Long> playerIds;
}
