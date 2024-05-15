package gr.aueb.cf.premierAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerDTO {

        private String firstname;
        private String lastname;
        private int age;
        private String position;
}
