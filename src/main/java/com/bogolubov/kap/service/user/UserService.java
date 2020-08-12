package com.bogolubov.kap.service.user;

import com.bogolubov.kap.controllers.user.util.UserFilterContainer;
import com.bogolubov.kap.dao.user.dto.UserDto;
import com.bogolubov.kap.dao.user.dto.UserDtoForPageable;
import com.bogolubov.kap.dao.user.entity.User;
import com.bogolubov.kap.repository.user.UserRepository;
import com.bogolubov.kap.service.user.wrapper.UserWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final UserRepository userRepository;
    private final UserWrapper wrapper;
    private PagedResourcesAssembler<UserDtoForPageable> assemblerUser = new PagedResourcesAssembler<>(null, null);

    public UserDto getUser(Long id) {
        return wrapper.entityToDto(userRepository.findOne(id));
    }

    public List<UserDto> findAll() {
        return wrapper.getUsersDto(userRepository.findAll());
    }

    public PagedResources<Resource<UserDtoForPageable>> getPageable(UserFilterContainer container) {
        return assemblerUser.toResource(userRepository.getPageable(container));
    }

    public UserDto delete(Long id) {
        User user = userRepository.findOne(id);
        user.setEnabled(false);
        return save(user);
    }

    public UserDto saveOrUpdate(UserDto dto) {
        User save = userRepository.save(wrapper.dtoToEntity(dto));
        return wrapper.entityToDto(save);
    }

    private UserDto save(User user) {
        return wrapper.entityToDto(userRepository.save(user));
    }
}
