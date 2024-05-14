package gr.aueb.cf.premierAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeamUpdateDTO extends BaseDTO{
    private String name;
    private String stadiumName;
    private int stadiumCapacity;
    private String coachName;
    private String coachSurname;
    private int coachAge;
}
