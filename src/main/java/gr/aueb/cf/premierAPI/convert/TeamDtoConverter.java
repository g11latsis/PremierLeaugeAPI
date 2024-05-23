package gr.aueb.cf.premierAPI.convert;

import gr.aueb.cf.premierAPI.dto.*;
import gr.aueb.cf.premierAPI.model.Coach;
import gr.aueb.cf.premierAPI.model.Player;
import gr.aueb.cf.premierAPI.model.Stadium;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.repository.CoachRepository;
import gr.aueb.cf.premierAPI.repository.PlayerRepository;
import gr.aueb.cf.premierAPI.repository.StadiumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamDtoConverter {


    private final ModelMapper modelMapper;
    private final CoachRepository coachRepository;
    private final StadiumRepository stadiumRepository;
    private final PlayerRepository playerRepository;
    private final PlayerDtoConverter playerDTOConverter;

    @Autowired
    public TeamDtoConverter(ModelMapper modelMapper, CoachRepository coachRepository,
                            StadiumRepository stadiumRepository, PlayerRepository playerRepository, PlayerDtoConverter playerDTOConverter) {
        this.modelMapper = modelMapper;
        this.coachRepository = coachRepository;
        this.stadiumRepository = stadiumRepository;
        this.playerRepository = playerRepository;
        this.playerDTOConverter = playerDTOConverter;
    }


    public Team convertUpdateDtoToTeam(TeamUpdateDTO dto){
        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        Coach coach = coachRepository.findById(dto.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));
        team.setCoach(coach);

        Stadium stadium = stadiumRepository.findById(dto.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        team.setStadium(stadium);

        List<Player> players = playerRepository.findAllById(dto.getPlayerIds());
        if (players.size() != dto.getPlayerIds().size()) {
            throw new RuntimeException("Some players not found");
        }
        for (Player player : players) {
            player.setTeam(team);
        }
        team.setPlayers(players);

        return team;
    }

    public TeamDTO convertTeamToDTO(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setCoach(modelMapper.map(team.getCoach(), CoachDTO.class));
        dto.setStadium(modelMapper.map(team.getStadium(), StadiumDTO.class));

        // Check if the players list is null before streaming
        if (team.getPlayers() != null) {
            dto.setPlayers(team.getPlayers()
                    .stream()
                    .map(playerDTOConverter::convertPlayerToDTO)
                    .collect(Collectors.toList()));
        } else {
            dto.setPlayers(new ArrayList<>()); // or set it to null or handle it as appropriate
        }
        return dto;
    }

    public Team ConvertToEntity(TeamInsertDTO teamInsertDTO) {
        Team team = new Team();
        team.setName(teamInsertDTO.getName());
        Coach coach = coachRepository.findById(teamInsertDTO.getCoachId())
                .orElseThrow(() -> new RuntimeException("Coach not found"));
        team.setCoach(coach);

        Stadium stadium = stadiumRepository.findById(teamInsertDTO.getStadiumId())
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        team.setStadium(stadium);

        List<Player> players = playerRepository.findAllById(teamInsertDTO.getPlayerIds());
        if (players.size() != teamInsertDTO.getPlayerIds().size()) {
            throw new RuntimeException("Some players not found");
        }
        for (Player player : players) {
            player.setTeam(team);
        }
        team.setPlayers(players);

        return team;
    }


}

