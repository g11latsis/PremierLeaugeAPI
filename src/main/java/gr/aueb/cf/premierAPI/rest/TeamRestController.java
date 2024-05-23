package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.convert.TeamDtoConverter;
import gr.aueb.cf.premierAPI.dto.TeamDTO;
import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import gr.aueb.cf.premierAPI.service.ITeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {

    private final ITeamService teamService;
    private final TeamDtoConverter teamDTOConverter;

    @Autowired
    public TeamRestController(ITeamService teamService, TeamDtoConverter teamDTOConverter) {
        this.teamService = teamService;
        this.teamDTOConverter = teamDTOConverter;
    }


    @PostMapping
    public ResponseEntity<TeamDTO> insert(@Valid @RequestBody TeamInsertDTO dto) {
        try {
            TeamDTO teamDTO = teamService.insert(dto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(teamDTO.getId())
                    .toUri();
            return new ResponseEntity<>(teamDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id,@Valid @RequestBody TeamUpdateDTO dto) {

        try {
            Team team = teamService.getById(id);
            if (team == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Team updatedTeam = teamService.update(dto);
            TeamDTO teamDTO = teamDTOConverter.convertTeamToDTO(updatedTeam);
            return new ResponseEntity<>(teamDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeamDTO> delete(@PathVariable Long id) {
        try {
            Team team = teamService.getById(id);
            TeamDTO dto = teamDTOConverter.convertTeamToDTO(team);
            teamService.delete(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<TeamDTO> getTeamByName(@RequestParam String name) {
        Team team;
        try {
            team = teamService.getTeamByName(name);
            TeamDTO dto = teamDTOConverter.convertTeamToDTO(team);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getById(@PathVariable Long id) {
        Team team;
        try {
            team = teamService.getById(id);
            TeamDTO dto = teamDTOConverter.convertTeamToDTO(team);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }
}