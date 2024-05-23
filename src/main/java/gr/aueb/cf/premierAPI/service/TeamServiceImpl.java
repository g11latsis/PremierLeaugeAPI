package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.TeamDTO;
import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.convert.TeamDtoConverter;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.repository.TeamRepository;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeamServiceImpl implements ITeamService {

    private final TeamRepository teamRepository;
    private final TeamDtoConverter teamDTOConverter;


    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TeamDtoConverter teamDTOConverter) {
        this.teamRepository = teamRepository;
        this.teamDTOConverter = teamDTOConverter;
    }

    @Override
    @Transactional
    public TeamDTO insert(TeamInsertDTO dto) throws Exception {
        try {
            Team team = teamDTOConverter.ConvertToEntity(dto);
            team = teamRepository.save(team);
            log.info("Team inserted: " + team);
            return teamDTOConverter.convertTeamToDTO(team);
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
            updatedTeam = teamRepository.save(teamDTOConverter.convertUpdateDtoToTeam(dto));
            log.info("Team updated: " + updatedTeam);
        } catch (EntityNotFoundException e) {
            log.error("Error updating team: " + e.getMessage());
            throw e;
        }
        return updatedTeam;
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
        } catch (EntityNotFoundException e) {
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

    @Override
    @Transactional
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamDTOConverter::convertTeamToDTO)
                .collect(Collectors.toList());
    }

}
