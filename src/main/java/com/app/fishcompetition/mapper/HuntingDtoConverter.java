package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.HuntingDto;
import com.app.fishcompetition.model.entity.Hunting;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class HuntingDtoConverter {

      private final ModelMapper modelMapper;

      public Hunting convertToEntity(HuntingDto huntingDto) {
          return modelMapper.map(huntingDto, Hunting.class);
      }
      public HuntingDto convertToDto(Hunting hunting) {
          return modelMapper.map(hunting, HuntingDto.class);
      }
}
