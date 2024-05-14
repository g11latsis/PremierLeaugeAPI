package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.mapper.TeamMapper;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.repository.TeamRepository;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeamServiceImpl implements ITeamService{

    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    @Transactional
    public Team insert(TeamInsertDTO dto) throws EntityAlreadyExistsException {
        try {
            Team team = TeamMapper.convertToEntity(dto);

            if (teamRepository.findTeamByName(dto.getName()) != null) {
                throw new EntityAlreadyExistsException(Team.class,0L);
            }
            log.info("Team inserted: " + team);
            return teamRepository.save(team);

        }catch (Exception e) {
            log.error("Error inserting team: " + e.getMessage());
            throw e;
        }

    }

    @Override
    public Team update(TeamUpdateDTO dto) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Team delete(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Team> getTeamByName(String name) throws EntityNotFoundException {
        return null;
    }

    @Override
    public Team getById(Long id) throws EntityNotFoundException {
        Team team;
        try{
            team = teamRepository.findTeamById(id);
            if(team == null){
                throw new EntityNotFoundException(Team.class, id);
            }
            log.info("Team found: " + team);

        }catch (EntityNotFoundException e){
            log.error("Error getting team by id: " + e.getMessage());
            throw e;
        }
        return team;
    }
}
