package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.PlayerDTO;
import gr.aueb.cf.premierAPI.dto.PlayerInsertDTO;
import gr.aueb.cf.premierAPI.dto.PlayerUpdateDTO;
import gr.aueb.cf.premierAPI.model.Player;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;

import java.util.List;

public interface IPlayerService {
    Player insert(PlayerInsertDTO dto) throws Exception;
    Player update(PlayerUpdateDTO dto) throws EntityNotFoundException;
    Player delete(Long id) throws EntityNotFoundException;
    Player getPlayerByLastname(String lastname) throws EntityNotFoundException;
    Player getById(Long id) throws EntityNotFoundException;
    List<PlayerDTO> getAllPlayers();
}
