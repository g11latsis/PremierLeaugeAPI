package gr.aueb.cf.premierAPI.convert;

import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamResponseDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.model.Team;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Team convertInsertDtoToTeam(TeamInsertDTO dto) {
        return modelMapper.map(dto, Team.class);
    }

    public Team convertUpdateDtoToTeam(TeamUpdateDTO dto) {
        return modelMapper.map(dto, Team.class);
    }

    public TeamResponseDTO convertTeamToResponseDto(Team team) {
        TeamResponseDTO responseDTO = modelMapper.map(team, TeamResponseDTO.class);
        responseDTO.setCoachFirstName(team.getCoach().getFirstname());
        responseDTO.setCoachLastName(team.getCoach().getLastname());
        responseDTO.setStadiumName(team.getStadium().getName());
        return responseDTO;
    }

}

