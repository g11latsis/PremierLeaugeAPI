package gr.aueb.cf.premierAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private int age;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team; // Change to OneToOne

    public void setTeam(Team team) {
        this.team = team;
        team.setCoach(this);
    }

    public void removeTeam() {
        if (team != null) {
            team.setCoach(null);
            team = null;
        }
    }

    public void addUser(User user) {
        this.user = user;
        user.setCoach(this);
    }

    public void removeUser() {
        if (user != null) {
            user.setCoach(null);
            user = null;
        }
    }
}
