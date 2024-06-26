package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.convert.CoachDtoConverter;
import gr.aueb.cf.premierAPI.dto.CoachDTO;
import gr.aueb.cf.premierAPI.dto.CoachInsertDTO;
import gr.aueb.cf.premierAPI.model.Coach;
import gr.aueb.cf.premierAPI.service.ICoachService;
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
@RequestMapping("/api/coaches")
public class CoachRestController {

    private final ICoachService coachService;
    private final CoachDtoConverter coachDtoConverter;

    @Autowired
    public CoachRestController(ICoachService coachService, CoachDtoConverter coachDtoConverter) {
        this.coachService = coachService;
        this.coachDtoConverter = coachDtoConverter;
    }

    @Operation(summary = "Insert a new coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Coach created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<CoachDTO> insert(@Valid @RequestBody CoachInsertDTO dto) {
        try {
            Coach coach = coachService.insert(dto);
            CoachDTO coachDTO = coachDtoConverter.convertCoachToCoachDTO(coach);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(coachDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).body(coachDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update an existing coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach updated"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CoachDTO> update(@PathVariable Long id, @Valid @RequestBody CoachDTO dto) {
        try {
            coachService.update(dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete an existing coach")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CoachDTO> delete(@PathVariable Long id) {
        try {
            Coach coach = coachService.getById(id);
            coachService.delete(id);
            CoachDTO coachDTO = coachDtoConverter.convertCoachToCoachDTO(coach);
            return new ResponseEntity<>(coachDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get a coach by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach found"),
            @ApiResponse(responseCode = "404", description = "Coach not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CoachDTO> getById(@PathVariable Long id) {
        try {
            Coach coach = coachService.getById(id);
            CoachDTO coachDTO = coachDtoConverter.convertCoachToCoachDTO(coach);
            return new ResponseEntity<>(coachDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get a coach by lastname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coach found"),
            @ApiResponse(responseCode = "404", description = "Coach not found")
    })
    @GetMapping("")
    public ResponseEntity<CoachDTO> getCoachByLastname(@RequestParam String lastname) {
        try {
            Coach coach = coachService.getCoachByLastname(lastname);
            CoachDTO coachDTO = coachDtoConverter.convertCoachToCoachDTO(coach);
            return new ResponseEntity<>(coachDTO, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get all coaches")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coaches found"),
            @ApiResponse(responseCode = "503", description = "Service unavailable")
    })
    @GetMapping("/all")
    public List<CoachDTO> getAllCoaches() {
        return coachService.getAllCoaches();
    }
}
