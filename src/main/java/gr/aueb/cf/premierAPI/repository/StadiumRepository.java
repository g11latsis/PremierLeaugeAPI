package gr.aueb.cf.premierAPI.repository;

import gr.aueb.cf.premierAPI.model.Stadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Long> {
    Stadium findStadiumById(Long id);
    Stadium findStadiumByName(String name);
}
