package gr.aueb.cf.premierAPI.repository;

import gr.aueb.cf.premierAPI.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long>{
    Coach findByLastname(String lastname);
    Coach findCoachById(Long id);
}
