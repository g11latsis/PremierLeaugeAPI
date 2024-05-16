package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.StadiumDTO;
import gr.aueb.cf.premierAPI.model.Stadium;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;

import java.util.List;

public interface IStadiumService {
    Stadium insert(StadiumDTO dto) throws Exception;
    Stadium update(StadiumDTO dto) throws EntityNotFoundException;
    Stadium delete(Long id) throws EntityNotFoundException;
    Stadium getStadiumByName(String name) throws EntityNotFoundException;
    Stadium getById(Long id) throws EntityNotFoundException;
    List<StadiumDTO> getAllStadiums();
}
