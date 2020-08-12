package com.bogolubov.kap.service.department.wrapper;

import com.bogolubov.kap.dao.department.dto.DepartmentDto;
import com.bogolubov.kap.dao.department.entity.Department;
import com.bogolubov.kap.dao.user.dto.UserDto;
import com.bogolubov.kap.dao.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentWrapper {

    public DepartmentDto entityToDto(Department entity) {
        DepartmentDto dto = new DepartmentDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public Department dtoToEntity(DepartmentDto dto) {
        Department entity = new Department();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public List<DepartmentDto> getListDto(List<Department> listEntity) {
        List<DepartmentDto> listDto = new ArrayList<>();
        for (Department entity : listEntity) {
            listDto.add(entityToDto(entity));
        }
        return listDto;
    }
}
