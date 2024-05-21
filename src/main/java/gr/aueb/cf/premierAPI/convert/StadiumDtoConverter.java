package gr.aueb.cf.premierAPI.convert;

import gr.aueb.cf.premierAPI.dto.StadiumDTO;
import gr.aueb.cf.premierAPI.dto.StadiumInsertDTO;
import gr.aueb.cf.premierAPI.dto.StadiumUpdateDTO;
import gr.aueb.cf.premierAPI.model.Stadium;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StadiumDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public StadiumDTO convertStadiumToStadiumDto(Stadium stadium) {
        return modelMapper.map(stadium, StadiumDTO.class);
    }

    public Stadium convertStadiumDtoToStadium(StadiumUpdateDTO dto) {

        return modelMapper.map(dto, Stadium.class);
    }

    public Stadium convertStadiumToStadiumInsertDto(StadiumInsertDTO dto) {
        return modelMapper.map(dto, Stadium.class);
    }

}
