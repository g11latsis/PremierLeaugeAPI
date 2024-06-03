package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.convert.PlayerDtoConverter;
import gr.aueb.cf.premierAPI.dto.PlayerDTO;
import gr.aueb.cf.premierAPI.dto.PlayerInsertDTO;
import gr.aueb.cf.premierAPI.dto.PlayerUpdateDTO;
import gr.aueb.cf.premierAPI.model.Player;
import gr.aueb.cf.premierAPI.service.IPlayerService;
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
@RequestMapping("/api/players")
public class PlayerRestController {

    private final IPlayerService playerService;
    private final PlayerDtoConverter playerDtoConverter;

    @Autowired
    public PlayerRestController(IPlayerService playerService, PlayerDtoConverter playerDtoConverter) {
        this.playerService = playerService;
        this.playerDtoConverter = playerDtoConverter;
    }

    @Operation(summary = "Insert a new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Player created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<PlayerDTO> insert(@Valid @RequestBody PlayerInsertDTO dto) {
        try {
            Player player = playerService.insert(dto);
            PlayerDTO playerDTO = playerDtoConverter.convertPlayerToDTO(player);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(playerDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(playerDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update an existing player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player updated"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @Valid @RequestBody PlayerUpdateDTO dto) {
        try {

            Player player = playerService.getById(id);
            if (player == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Player updatedPlayer = playerService.update(dto);
            PlayerDTO playerDTO = playerDtoConverter.convertPlayerToDTO(updatedPlayer);
            return ResponseEntity.ok(playerDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete an existing player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<PlayerDTO> delete(@PathVariable Long id) {
        try {
            Player player = playerService.getById(id);
            playerService.delete(id);
            return ResponseEntity.ok(playerDtoConverter.convertPlayerToDTO(player));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get a player by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player found"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable Long id) {
        try {
            Player player = playerService.getById(id);
            return ResponseEntity.ok(playerDtoConverter.convertPlayerToDTO(player));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get a player by lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player found"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    @GetMapping("")
    public ResponseEntity<PlayerDTO> getPlayerByLastname(@RequestParam String lastname) {
        try {
            Player player = playerService.getPlayerByLastname(lastname);
            return ResponseEntity.ok(playerDtoConverter.convertPlayerToDTO(player));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get all players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Players found"),
            @ApiResponse(responseCode = "404", description = "Players not found")
    })
    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}
