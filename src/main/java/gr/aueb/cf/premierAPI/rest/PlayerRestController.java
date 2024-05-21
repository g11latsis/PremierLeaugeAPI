package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.convert.PlayerDtoConverter;
import gr.aueb.cf.premierAPI.dto.PlayerDTO;
import gr.aueb.cf.premierAPI.dto.PlayerInsertDTO;
import gr.aueb.cf.premierAPI.dto.PlayerUpdateDTO;
import gr.aueb.cf.premierAPI.model.Player;
import gr.aueb.cf.premierAPI.service.IPlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTO> update(@PathVariable Long id, @Valid @RequestBody PlayerUpdateDTO dto) {
        try {
            Player player = playerService.update(dto);
            PlayerDTO playerDTO = playerDtoConverter.convertPlayerToDTO(player);
            return ResponseEntity.ok(playerDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getById(@PathVariable Long id) {
        try {
            Player player = playerService.getById(id);
            return ResponseEntity.ok(playerDtoConverter.convertPlayerToDTO(player));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<PlayerDTO> getPlayerByLastname(@RequestParam String lastname) {
        try {
            Player player = playerService.getPlayerByLastname(lastname);
            return ResponseEntity.ok(playerDtoConverter.convertPlayerToDTO(player));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }
}
