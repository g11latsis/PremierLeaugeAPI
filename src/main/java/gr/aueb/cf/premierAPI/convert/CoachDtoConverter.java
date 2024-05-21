package gr.aueb.cf.premierAPI.convert;

import gr.aueb.cf.premierAPI.dto.CoachDTO;
import gr.aueb.cf.premierAPI.dto.CoachInsertDTO;
import gr.aueb.cf.premierAPI.model.Coach;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoachDtoConverter {

    private final ModelMapper modelMapper;

    @Autowired
    public CoachDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CoachDTO convertCoachToCoachDTO(Coach coach) {
        return modelMapper.map(coach, CoachDTO.class);
    }

    public Coach convertCoachDtoToCoach(CoachDTO dto) {
        return modelMapper.map(dto, Coach.class);
    }

    public Coach convertCoachInsertDtoToCoach(CoachInsertDTO dto) {
        return modelMapper.map(dto, Coach.class);
    }

}
