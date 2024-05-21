package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.dto.CoachDTO;
import gr.aueb.cf.premierAPI.dto.CoachInsertDTO;
import gr.aueb.cf.premierAPI.model.Coach;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;

import java.util.List;

public interface ICoachService {
    Coach insert(CoachInsertDTO dto) throws Exception;
    Coach update(CoachDTO dto) throws EntityNotFoundException;
    Coach delete(Long id) throws EntityNotFoundException;
    Coach getCoachByLastname(String lastname) throws EntityNotFoundException;
    Coach getById(Long id) throws EntityNotFoundException;
    List<CoachDTO> getAllCoaches();

}
