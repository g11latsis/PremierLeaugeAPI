package gr.aueb.cf.premierAPI.convert;

import gr.aueb.cf.premierAPI.dto.*;
import gr.aueb.cf.premierAPI.model.Player;
import gr.aueb.cf.premierAPI.model.Team;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerDTOConverter playerDTOConverter;

    public Team convertInsertDtoToTeam(TeamInsertDTO dto) {
        return modelMapper.map(dto, Team.class);
    }

    public Team convertUpdateDtoToTeam(TeamUpdateDTO dto) {
        return modelMapper.map(dto, Team.class);
    }

//    public TeamResponseDTO convertTeamToResponseDto(Team team) {
//        TeamResponseDTO responseDTO = modelMapper.map(team, TeamResponseDTO.class);
//        responseDTO.setCoachFirstName(team.getCoach().getFirstname());
//        responseDTO.setCoachLastName(team.getCoach().getLastname());
//        responseDTO.setStadiumName(team.getStadium().getName());
//        return responseDTO;
//    }

    public TeamDetailsDTO convertTeamToDetailsDto(Team team) {
        TeamDetailsDTO dto = new TeamDetailsDTO();
        dto.setId(team.getId());
        dto.setTeamName(team.getName());
        dto.setCoachFirstName(team.getCoach().getFirstname());
        dto.setCoachLastName(team.getCoach().getLastname());
        dto.setStadiumName(team.getStadium().getName());
        dto.setStadiumCapacity(team.getStadium().getCapacity());

        //Mapping the players
        List<PlayerDTO> playerDTOs = team.getPlayers()
                .stream()
                .map(playerDTOConverter::convertPlayerToDTO)
                .collect(Collectors.toList());
        dto.setPlayers(playerDTOs);

        return dto;
    }


}

