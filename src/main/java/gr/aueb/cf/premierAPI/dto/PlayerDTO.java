package gr.aueb.cf.premierAPI.dto;

import lombok.*;

@Data
public class PlayerDTO extends BaseDTO{

        private String firstname;
        private String lastname;
        private int age;
        private String position;
}
