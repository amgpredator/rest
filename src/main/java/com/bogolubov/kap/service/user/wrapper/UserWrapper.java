package com.bogolubov.kap.service.user.wrapper;

import com.bogolubov.kap.dao.user.dto.UserDto;
import com.bogolubov.kap.dao.user.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserWrapper {

    public UserDto entityToDto(User entity) {
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    public User dtoToEntity(UserDto dto) {
        User entity = new User();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }

    public List<UserDto> getUsersDto(List<User> listEntity) {
        List<UserDto> listDto = new ArrayList<>();
        for (User entity : listEntity) {
            listDto.add(entityToDto(entity));
        }
        return listDto;
    }
}
