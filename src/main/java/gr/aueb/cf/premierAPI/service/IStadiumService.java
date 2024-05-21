package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.StadiumDTO;
import gr.aueb.cf.premierAPI.dto.StadiumInsertDTO;
import gr.aueb.cf.premierAPI.dto.StadiumUpdateDTO;
import gr.aueb.cf.premierAPI.model.Stadium;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;

import java.util.List;

public interface IStadiumService {
    Stadium insert(StadiumInsertDTO dto) throws Exception;
    Stadium update(StadiumUpdateDTO dto) throws EntityNotFoundException;
    Stadium delete(Long id) throws EntityNotFoundException;
    Stadium getStadiumByName(String name) throws EntityNotFoundException;
    Stadium getById(Long id) throws EntityNotFoundException;
    List<StadiumDTO> getAllStadiums();
}
