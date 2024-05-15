package gr.aueb.cf.premierAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeamResponseDTO {
    private String teamName;
    private String coachName;
    private String stadiumName;
    private List<String> playerNames;
}