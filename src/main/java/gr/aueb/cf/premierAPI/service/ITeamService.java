package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.TeamDTO;
import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;

import java.util.List;

public interface ITeamService {
    TeamDTO insert(TeamInsertDTO dto) throws Exception;
    Team update(TeamUpdateDTO dto) throws EntityNotFoundException;
    Team delete(Long id) throws EntityNotFoundException;
    Team getTeamByName(String name) throws EntityNotFoundException;
    Team getById(Long id) throws EntityNotFoundException;
    List<TeamDTO> getAllTeams();
}
