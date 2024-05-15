package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.dto.TeamInsertDTO;
import gr.aueb.cf.premierAPI.dto.TeamUpdateDTO;
import gr.aueb.cf.premierAPI.model.Team;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityAlreadyExistsException;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import gr.aueb.cf.premierAPI.service.ITeamService;
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

    @Autowired
    public TeamRestController(ITeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping
    public ResponseEntity<TeamInsertDTO> insert(@RequestBody TeamInsertDTO dto) {
        try {
            Team team = teamService.insert(dto);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(team.getId())
                    .toUri();

            return ResponseEntity.created(location).body(dto);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TeamUpdateDTO> update(@PathVariable Long id, @RequestBody TeamUpdateDTO dto) {

        try {
            Team team = teamService.getById(id);
            Team updatedTeam = teamService.update(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Team> delete(@PathVariable Long id) {
        try {
            Team team = teamService.getById(id);
            teamService.delete(id);
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("")
    public ResponseEntity<Team> getTeamByName(@RequestParam String name) {
        Team team;
        try {
            team = teamService.getTeamByName(name);
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable Long id) {
        Team team;
        try {
            team = teamService.getById(id);
            return new ResponseEntity<>(team, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}