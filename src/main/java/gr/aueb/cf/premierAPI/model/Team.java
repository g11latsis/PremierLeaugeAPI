package gr.aueb.cf.premierAPI.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "teams")
public class Team extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "stadium_id", referencedColumnName = "id")
    private Stadium stadium;

    @OneToOne(mappedBy = "team")
    private Coach coach; // Assuming one coach per team

    @OneToMany(mappedBy = "team")
    private List<Player> players;
}
