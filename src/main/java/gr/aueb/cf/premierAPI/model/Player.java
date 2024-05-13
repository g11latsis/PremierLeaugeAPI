package gr.aueb.cf.premierAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private int age;
    private String position;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    public void setTeam(Team team) {
        this.team = team;
        team.getPlayers().add(this);
    }

    public void removeTeam() {
        if (team != null) {
            team.getPlayers().remove(this);
            team = null;
        }
    }

    public void addUser(User user) {
        this.user = user;
        user.setPlayer(this);
    }

    public void removeUser() {
        if (user != null) {
            user.setPlayer(null);
            user = null;
        }
    }
}
