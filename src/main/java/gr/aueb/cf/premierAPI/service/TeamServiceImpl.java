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

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TeamServiceImpl implements ITeamService {

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
                throw new EntityAlreadyExistsException(Team.class, 0L);
            }
            log.info("Team inserted: " + team);
            return teamRepository.save(team);

        } catch (Exception e) {
            log.error("Error inserting team: " + e.getMessage());
            throw e;
        }

    }

    @Override
    @Transactional
    public Team update(TeamUpdateDTO dto) throws EntityNotFoundException {
        Team team = null;
        Team updatedTeam = null;
        try {
            team = teamRepository.findTeamById(dto.getId());
            if (team == null) {
                throw new EntityNotFoundException(Team.class, dto.getId());
            }
            updatedTeam = teamRepository.save(TeamMapper.convertToEntity(dto));
            log.info("Team updated: " + updatedTeam);
        } catch (EntityNotFoundException e) {
            log.error("Error updating team: " + e.getMessage());
            throw e;
        }
        return team;
    }

    @Override
    @Transactional
    public Team delete(Long id) throws EntityNotFoundException {
        Team team = null;

        try {
            team = teamRepository.findTeamById(id);
            if (team == null) {
                throw new EntityNotFoundException(Team.class, id);
            }
            teamRepository.delete(team);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting team: " + e.getMessage());
            throw e;
        }
        return team;
    }

    @Override
    @Transactional
    public Team getTeamByName(String name) throws EntityNotFoundException {
        Team team = null;

        try {
            team = teamRepository.findTeamByName(name);
           if (team == null) {
                    throw new EntityNotFoundException(Team.class, 0L);
                }
            log.info("Team found: " + team);
        }catch (EntityNotFoundException e) {
            log.error("Error getting team by name: " + e.getMessage());
            throw e;
        }
        return team;
    }

    @Override
    @Transactional
    public Team getById(Long id) throws EntityNotFoundException {
        Team team;
        try {
            team = teamRepository.findTeamById(id);
            if (team == null) {
                throw new EntityNotFoundException(Team.class, id);
            }
            log.info("Team found: " + team);

        } catch (EntityNotFoundException e) {
            log.error("Error getting team by id: " + e.getMessage());
            throw e;
        }
        return team;
    }
}
