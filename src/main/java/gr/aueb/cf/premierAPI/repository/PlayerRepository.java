package gr.aueb.cf.premierAPI.repository;

import gr.aueb.cf.premierAPI.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    List<Player> findByTeamId(Long teamId);
    List<Player> findByLastname(String lastname);
    Player findPlayerById(Long id);
}
