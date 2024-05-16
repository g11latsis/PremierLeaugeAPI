package gr.aueb.cf.premierAPI.service;

import gr.aueb.cf.premierAPI.convert.StadiumDtoConverter;
import gr.aueb.cf.premierAPI.dto.StadiumDTO;
import gr.aueb.cf.premierAPI.model.Stadium;
import gr.aueb.cf.premierAPI.repository.StadiumRepository;
import gr.aueb.cf.premierAPI.service.Exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StadiumServiceImpl implements IStadiumService {

    private final StadiumRepository stadiumRepository;
    private final StadiumDtoConverter stadiumDtoConverter;

    @Autowired
    public StadiumServiceImpl(StadiumRepository stadiumRepository, StadiumDtoConverter stadiumDtoConverter) {
        this.stadiumRepository = stadiumRepository;
        this.stadiumDtoConverter = stadiumDtoConverter;
    }

    @Transactional
    @Override
    public Stadium insert(StadiumDTO dto) throws Exception {
        try {
            Stadium stadium = stadiumRepository.save(stadiumDtoConverter.convertStadiumDtoToStadium(dto));
            if (stadium.getId() == null) {
                throw new Exception("Error inserting stadium");
            }
            return stadium;
        } catch (Exception e) {
            log.error("Error inserting stadium: " + e.getMessage());
            throw e;
        }

    }

    @Transactional
    @Override
    public Stadium update(StadiumDTO dto) throws EntityNotFoundException {
        Stadium stadium = null;
        Stadium updatedStadium = null;
        try {
            stadium = stadiumRepository.findStadiumById(dto.getId());
            if (stadium == null) {
                throw new EntityNotFoundException(Stadium.class, dto.getId());
            }
            updatedStadium = stadiumRepository.save(stadiumDtoConverter.convertStadiumDtoToStadium(dto));
            log.info("Stadium updated: " + updatedStadium);
        } catch (Exception e) {
            log.error("Error updating stadium: " + e.getMessage());
            throw e;
        }
        return updatedStadium;
    }

    @Transactional
    @Override
    public Stadium delete(Long id) throws EntityNotFoundException {
        Stadium stadium = null;
        try {
            stadium = stadiumRepository.findStadiumById(id);
            if (stadium == null) {
                throw new EntityNotFoundException(Stadium.class, id);
            }
            stadiumRepository.delete(stadium);
            log.info("Stadium deleted: " + stadium);
        } catch (EntityNotFoundException e) {
            log.error("Error deleting stadium: " + e.getMessage());
            throw e;
        }
        return stadium;
    }

    @Transactional
    @Override
    public Stadium getStadiumByName(String name) throws EntityNotFoundException {
        Stadium stadium = null;
        try {
            stadium = stadiumRepository.findStadiumByName(name);
            if (stadium == null) {
                throw new EntityNotFoundException(Stadium.class, 0L);
            }
            log.info("Stadium found: " + stadium);
        } catch (EntityNotFoundException e) {
            log.error("Error finding stadium: " + e.getMessage());
            throw e;
        }
        return stadium;
    }

    @Transactional
    @Override
    public Stadium getById(Long id) throws EntityNotFoundException {
        Stadium stadium;
        try {
            stadium = stadiumRepository.findStadiumById(id);
            if (stadium == null) {
                throw new EntityNotFoundException(Stadium.class, id);
            }
            log.info("Stadium found: " + stadium);
        } catch (EntityNotFoundException e) {
            log.error("Error finding stadium: " + e.getMessage());
            throw e;
        }
        return stadium;
    }

    @Transactional
    @Override
    public List<StadiumDTO> getAllStadiums() {
        return stadiumRepository.findAll()
                .stream()
                .map(stadiumDtoConverter::convertStadiumToStadiumDto)
                .collect(Collectors.toList());
    }

}

