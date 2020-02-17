package com.bogolubov.kap.service.user;

import com.bogolubov.kap.controllers.user.util.UserFilterContainer;
import com.bogolubov.kap.dao.user.dto.UserDtoForPageable;
import com.bogolubov.kap.dao.user.entity.User;
import com.bogolubov.kap.repository.user.UserRepository;
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
    private PagedResourcesAssembler<UserDtoForPageable> assemblerUser = new PagedResourcesAssembler<>(null, null);

    public User getUser(Long id) {
        return userRepository.findOne(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public PagedResources<Resource<UserDtoForPageable>> getPageable(UserFilterContainer container) {
        return assemblerUser.toResource(userRepository.getPageable(container));
    }

    public User delete(Long id) {
        User user = userRepository.findOne(id);
        user.setEnabled(false);
        return save(user);
    }

    private User save(User user) {
        return userRepository.save(user);
    }

}
