package gr.aueb.cf.premierAPI.mapper;

import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamResponseDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.model.Team;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class TeamMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper.addMappings(new PropertyMap<TeamInsertDTO, Team>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
    }

    public static Team convertToEntity(TeamInsertDTO dto) {
        return modelMapper.map(dto, Team.class);
    }

    public static TeamInsertDTO convertToDTO(Team entity) {
        return modelMapper.map(entity, TeamInsertDTO.class);
    }

    public static Team convertToEntity(TeamUpdateDTO dto) {
        return modelMapper.map(dto, Team.class);
    }

    public TeamResponseDTO mapToTeamResponseDTO(Team team) {
        return modelMapper.map(team, TeamResponseDTO.class);
    }
}
