package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.convert.StadiumDtoConverter;
import gr.aueb.cf.premierAPI.dto.StadiumDTO;
import gr.aueb.cf.premierAPI.dto.StadiumInsertDTO;
import gr.aueb.cf.premierAPI.dto.StadiumUpdateDTO;
import gr.aueb.cf.premierAPI.model.Stadium;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import gr.aueb.cf.premierAPI.service.IStadiumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stadiums")
public class StadiumRestController {

    private final IStadiumService stadiumService;
    private final StadiumDtoConverter stadiumDtoConverter;

    @Autowired
    public StadiumRestController(IStadiumService stadiumService, StadiumDtoConverter stadiumDtoConverter) {
        this.stadiumService = stadiumService;
        this.stadiumDtoConverter = stadiumDtoConverter;
    }

    @Operation(summary = "Insert a new stadium")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Stadium created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<StadiumDTO> insert(@Valid @RequestBody StadiumInsertDTO dto) {
        try {
            Stadium stadium = stadiumService.insert(dto);
            StadiumDTO stadiumDTO = stadiumDtoConverter.convertStadiumToStadiumDto(stadium);
            return new ResponseEntity<>(stadiumDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Update an existing stadium")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stadium updated"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/{id}")
    public ResponseEntity<StadiumDTO> update(@PathVariable Long id, @Valid @RequestBody StadiumUpdateDTO dto) {
        try {
           Stadium stadium =  stadiumService.update(dto);
           StadiumDTO stadiumDTO = stadiumDtoConverter.convertStadiumToStadiumDto(stadium);
            return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Delete an existing stadium")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stadium deleted"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<StadiumDTO> delete(@PathVariable Long id) {
        try {
            Stadium stadium = stadiumService.getById(id);
            stadiumService.delete(id);
            StadiumDTO stadiumDTO = stadiumDtoConverter.convertStadiumToStadiumDto(stadium);
            return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Get a stadium by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stadium found"),
            @ApiResponse(responseCode = "404", description = "Stadium not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<StadiumDTO> getById(@PathVariable Long id) {
        Stadium stadium;
        try {
            stadium = stadiumService.getById(id);
            StadiumDTO stadiumDTO = stadiumDtoConverter.convertStadiumToStadiumDto(stadium);
            return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get a stadium by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stadium found"),
            @ApiResponse(responseCode = "404", description = "Stadium not found")
    })
    @GetMapping("")
    public ResponseEntity<StadiumDTO> getStadiumByName(@RequestParam String name) {
        Stadium stadium;
        try {
            stadium = stadiumService.getStadiumByName(name);
            StadiumDTO stadiumDTO = stadiumDtoConverter.convertStadiumToStadiumDto(stadium);
            return new ResponseEntity<>(stadiumDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get all stadiums")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stadiums found"),
            @ApiResponse(responseCode = "404", description = "Stadiums not found")
    })
    @GetMapping("/all")
    public List<StadiumDTO> getAllStadiums() {
        return stadiumService.getAllStadiums();
    }

}
