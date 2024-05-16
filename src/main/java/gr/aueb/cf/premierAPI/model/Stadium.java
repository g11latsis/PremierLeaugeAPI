package gr.aueb.cf.premierAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "stadiums")
public class Stadium extends AbstractEntity{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private int capacity;


}
