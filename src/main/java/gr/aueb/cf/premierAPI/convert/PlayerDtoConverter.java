package gr.aueb.cf.premierAPI.convert;

import gr.aueb.cf.premierAPI.dto.PlayerDTO;
import gr.aueb.cf.premierAPI.dto.PlayerInsertDTO;
import gr.aueb.cf.premierAPI.dto.PlayerUpdateDTO;
import gr.aueb.cf.premierAPI.model.Player;
import gr.aueb.cf.premierAPI.model.Team;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public PlayerDTO convertPlayerToDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setFirstname(player.getFirstname());
        dto.setLastname(player.getLastname());
        dto.setAge(player.getAge());
        dto.setPosition(player.getPosition());
        dto.setTeam(player.getTeam().getName());
        return dto;
    }

    public Player convertPlayerInsertDtoToPlayer(PlayerInsertDTO playerInsertDTO, Team team) {
        Player player = new Player();
        player.setFirstname(playerInsertDTO.getFirstname());
        player.setLastname(playerInsertDTO.getLastname());
        player.setAge(playerInsertDTO.getAge());
        player.setPosition(playerInsertDTO.getPosition());
        player.setTeam(team);
        return player;
    }

    public Player convertPlayerUpdateDtoToPlayer(PlayerUpdateDTO playerUpdateDTO, Team team) {
        Player player = new Player();
        player.setId(playerUpdateDTO.getId());
        player.setFirstname(playerUpdateDTO.getFirstname());
        player.setLastname(playerUpdateDTO.getLastname());
        player.setAge(playerUpdateDTO.getAge());
        player.setPosition(playerUpdateDTO.getPosition());
        player.setTeam(team);
        return player;
    }
}
