package gr.aueb.cf.premierAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teams")
public class Team extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "stadium_id", referencedColumnName = "id")
    private Stadium stadium;

    @OneToOne
    @JoinColumn(name = "coach_id", referencedColumnName = "id")
    private Coach coach;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
//    @JsonBackReference
    @JsonManagedReference
    @JsonIgnoreProperties("team")
    private List<Player> players;

}
