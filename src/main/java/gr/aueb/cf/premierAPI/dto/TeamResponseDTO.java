package gr.aueb.cf.premierAPI.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamResponseDTO {
    private String teamName;
    private String coachFirstName;
    private String coachLastName;
    private String stadiumName;
}
