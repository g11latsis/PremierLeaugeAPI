package gr.aueb.cf.premierAPI.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamDetailsDTO extends BaseDTO{
    private String teamName;
    private String coachFirstName;
    private String coachLastName;
    private String stadiumName;
    private int stadiumCapacity;
    private List<PlayerDTO> players;
}
