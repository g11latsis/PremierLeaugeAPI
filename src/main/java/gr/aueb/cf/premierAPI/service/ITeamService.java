package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.TeamDetailsDTO;
import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ITeamService {
    Team insert(TeamInsertDTO dto) throws Exception;
    Team update(TeamUpdateDTO dto) throws EntityNotFoundException;
    Team delete(Long id) throws EntityNotFoundException;
    Team getTeamByName(String name) throws EntityNotFoundException;
    Team getById(Long id) throws EntityNotFoundException;
    List<TeamDetailsDTO> getAllTeams();
}
