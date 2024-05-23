package gr.aueb.cf.premierAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeamDTO extends BaseDTO{
    private String name;
    private CoachDTO coach;
    private StadiumDTO stadium;
    private List<PlayerDTO> players;
}
