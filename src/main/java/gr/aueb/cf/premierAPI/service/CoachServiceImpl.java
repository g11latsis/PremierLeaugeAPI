package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.convert.CoachDtoConverter;
import gr.aueb.cf.premierAPI.dto.CoachDTO;
import gr.aueb.cf.premierAPI.dto.CoachInsertDTO;
import gr.aueb.cf.premierAPI.model.Coach;
import gr.aueb.cf.premierAPI.repository.CoachRepository;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CoachServiceImpl implements ICoachService {

    private final CoachRepository coachRepository;
    private final CoachDtoConverter coachDtoConverter;

    @Autowired
    public CoachServiceImpl(CoachRepository coachRepository, CoachDtoConverter coachDtoConverter) {
        this.coachRepository = coachRepository;
        this.coachDtoConverter = coachDtoConverter;
    }

    @Transactional
    @Override
    public Coach insert(CoachInsertDTO dto) throws Exception {
        try {
            Coach coach = coachRepository.save(coachDtoConverter.convertCoachInsertDtoToCoach(dto));
            if (coach.getId() == null) {
                throw new Exception("Error inserting coach");
            }
            return coach;
        } catch (Exception e) {
            log.error("Error inserting coach: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public Coach update(CoachDTO dto) throws EntityNotFoundException {
        Coach coach = null;
        Coach updatedCoach = null;
        try {
            coach = coachRepository.findCoachById(dto.getId());
            if (coach == null) {
                throw new EntityNotFoundException(Coach.class, dto.getId());
            }
            updatedCoach = coachRepository.save(coachDtoConverter.convertCoachDtoToCoach(dto));
            log.info("Coach updated: " + updatedCoach);
        } catch (Exception e) {
            log.error("Error updating coach: " + e.getMessage());
            throw e;
        }
        return updatedCoach;
    }

    @Transactional
    @Override
    public Coach delete(Long id) throws EntityNotFoundException {
        Coach coach = null;
        try {
            coach = coachRepository.findCoachById(id);
            if (coach == null) {
                throw new EntityNotFoundException(Coach.class, id);
            }
            coachRepository.delete(coach);
            log.info("Coach deleted: " + coach);
        } catch (Exception e) {
            log.error("Error deleting coach: " + e.getMessage());
            throw e;
        }
        return coach;
    }

    @Transactional
    @Override
    public Coach getCoachByLastname(String lastname) throws EntityNotFoundException {
       Coach coach = null;
        try {
            coach = coachRepository.findByLastname(lastname);
            if (coach == null) {
                throw new EntityNotFoundException(Coach.class, 0L);
            }
            log.info("Coach found: " + coach);
        } catch (Exception e) {
            log.error("Error getting coach by name: " + e.getMessage());
            throw e;
        }
        return coach;
    }

    @Transactional
    @Override
    public Coach getById(Long id) throws EntityNotFoundException {
        Coach coach = null;
        try {
            coach = coachRepository.findCoachById(id);
            if (coach == null) {
                throw new EntityNotFoundException(Coach.class, id);
            }
            log.info("Coach found: " + coach);
        } catch (Exception e) {
            log.error("Error getting coach by id: " + e.getMessage());
            throw e;
        }
        return coach;
    }

    @Transactional
    @Override
    public List<CoachDTO> getAllCoaches() {
       return coachRepository.findAll()
                .stream()
                .map(coachDtoConverter::convertCoachToCoachDTO)
                .toList();
    }
}
