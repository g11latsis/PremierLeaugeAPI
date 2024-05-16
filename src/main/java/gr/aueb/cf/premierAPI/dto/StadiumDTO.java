package gr.aueb.cf.premierAPI.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StadiumDTO extends BaseDTO {

        private String name;
        private int capacity;
}
