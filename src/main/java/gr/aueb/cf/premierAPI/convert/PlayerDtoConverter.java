package gr.aueb.cf.premierAPI.convert;

import gr.aueb.cf.premierAPI.dto.PlayerDTO;
import gr.aueb.cf.premierAPI.model.Player;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayerDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public  PlayerDTO convertPlayerToDTO(Player player) {
        return modelMapper.map(player, PlayerDTO.class);
    }
}
