package gr.aueb.cf.premierAPI.rest;

import gr.aueb.cf.premierAPI.convert.StadiumDtoConverter;
import gr.aueb.cf.premierAPI.dto.StadiumDTO;
import gr.aueb.cf.premierAPI.dto.StadiumInsertDTO;
import gr.aueb.cf.premierAPI.dto.StadiumUpdateDTO;
import gr.aueb.cf.premierAPI.model.Stadium;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import gr.aueb.cf.premierAPI.service.IStadiumService;
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

    @GetMapping("/all")
    public List<StadiumDTO> getAllStadiums() {
        return stadiumService.getAllStadiums();
    }

}
