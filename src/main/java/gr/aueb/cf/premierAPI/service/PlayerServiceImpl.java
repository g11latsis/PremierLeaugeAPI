package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.convert.PlayerDtoConverter;
import gr.aueb.cf.premierAPI.dto.PlayerDTO;
import gr.aueb.cf.premierAPI.dto.PlayerInsertDTO;
import gr.aueb.cf.premierAPI.dto.PlayerUpdateDTO;
import gr.aueb.cf.premierAPI.model.Player;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.repository.PlayerRepository;
import gr.aueb.cf.premierAPI.repository.TeamRepository;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PlayerServiceImpl implements IPlayerService{

    private final PlayerRepository playerRepository;
    private final PlayerDtoConverter playerDtoConverter;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerDtoConverter playerDtoConverter, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.playerDtoConverter = playerDtoConverter;
        this.teamRepository = teamRepository;
    }

    @Transactional
    @Override
    public Player insert(PlayerInsertDTO dto) throws Exception {
        try {
            Team team = teamRepository.findById((long) dto.getTeam()).orElseThrow(() -> new EntityNotFoundException(Team.class, (long) dto.getTeam()));
            Player player = playerRepository.save(playerDtoConverter.convertPlayerInsertDtoToPlayer(dto, team));
            if (player.getId() == null) {
                throw new Exception("Error inserting player");
            }
            return player;
        } catch (Exception e) {
            log.error("Error inserting player: " + e.getMessage());
            throw e;
        }
    }

    @Transactional
    @Override
    public Player update(PlayerUpdateDTO dto) throws EntityNotFoundException {
        Player player = null;
        Player updatedPlayer = null;
        try {
            player = playerRepository.findPlayerById(dto.getId());
            if (player == null) {
                throw new EntityNotFoundException(Player.class, dto.getId());
            }
            Team team = teamRepository.findById((long) dto.getTeam())
                    .orElseThrow(() -> new EntityNotFoundException(Team.class, (long) dto.getTeam()));
            updatedPlayer = playerRepository.save(playerDtoConverter.convertPlayerUpdateDtoToPlayer(dto, team));
            log.info("Player updated: " + updatedPlayer);
        } catch (Exception e) {
            log.error("Error updating player: " + e.getMessage());
            throw e;
        }
        return updatedPlayer;
    }

    @Transactional
    @Override
    public Player delete(Long id) throws EntityNotFoundException {
        Player player = null;
        try {
            player = playerRepository.findPlayerById(id);
            if (player == null) {
                throw new EntityNotFoundException(Player.class, id);
            }
            playerRepository.delete(player);
            log.info("Player deleted: " + player);
        } catch (Exception e) {
            log.error("Error deleting player: " + e.getMessage());
            throw e;
        }
        return player;
    }

    @Transactional
    @Override
    public Player getPlayerByLastname(String lastname) throws EntityNotFoundException {
        Player player = null;
        try {
            player = playerRepository.findByLastname(lastname);
            if (player == null) {
                throw new EntityNotFoundException(Player.class, 0L);
            }
        } catch (Exception e) {
            log.error("Error getting player by lastname: " + e.getMessage());
            throw e;
        }
        return player;
    }

    @Transactional
    @Override
    public Player getById(Long id) throws EntityNotFoundException {
        Player player = null;
        try {
            player = playerRepository.findPlayerById(id);
            if (player == null) {
                throw new EntityNotFoundException(Player.class, id);
            }
        } catch (Exception e) {
            log.error("Error getting player by id: " + e.getMessage());
            throw e;
        }
        return player;
    }

    @Transactional
    @Override
    public List<PlayerDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(playerDtoConverter::convertPlayerToDTO)
                .toList();
    }

}
