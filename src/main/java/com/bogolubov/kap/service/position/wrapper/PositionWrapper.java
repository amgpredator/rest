package com.bogolubov.kap.service.position.wrapper;

import com.bogolubov.kap.dao.position.dto.PositionDto;
import com.bogolubov.kap.dao.position.entity.Position;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionWrapper {

    public PositionDto entityToDto(Position entity) {
        PositionDto dto = new PositionDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Position dtoToEntity(PositionDto dto) {
        Position entity = new Position();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public List<PositionDto> getListDto(List<Position> listEntity) {
        List<PositionDto> listDto = new ArrayList<>();
        for (Position entity : listEntity) {
            listDto.add(entityToDto(entity));
        }
        return listDto;
    }
}
