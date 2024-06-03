package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.convert.TeamDtoConverter;
import gr.aueb.cf.premierAPI.dto.TeamDTO;
import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import gr.aueb.cf.premierAPI.service.ITeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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


    @Operation(summary = "Insert a new team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Team created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
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

    @Operation(summary = "Update an existing team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team updated"),
            @ApiResponse(responseCode = "503", description = "Service unavailable")
    })
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

    @Operation(summary = "Delete an existing team")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team deleted"),
            @ApiResponse(responseCode = "503", description = "Service unavailable")
    })
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

    @Operation(summary = "Get a team by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team found"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
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

    @Operation(summary = "Get a team by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Team found"),
            @ApiResponse(responseCode = "404", description = "Team not found")
    })
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

    @Operation(summary = "Get all teams")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Teams found"),
            @ApiResponse(responseCode = "503", description = "Service unavailable")
    })
    @GetMapping("/all")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }
}