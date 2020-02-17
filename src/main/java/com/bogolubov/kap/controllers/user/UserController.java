package com.bogolubov.kap.controllers.user;

import com.bogolubov.kap.controllers.user.util.UserFilterContainer;
import com.bogolubov.kap.dao.department.entity.Department;
import com.bogolubov.kap.dao.position.entity.Position;
import com.bogolubov.kap.dao.user.dto.UserDtoForPageable;
import com.bogolubov.kap.dao.user.entity.User;
import com.bogolubov.kap.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RequestMapping(value = "/api/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping(value = "/{id}")
    public User deleteUser(@PathVariable(value = "id")Long id){
        return userService.delete(id);
    }

    @GetMapping(value = "/pageable")
    public PagedResources<Resource<UserDtoForPageable>> getPageable(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "positionId", required = false) Position position,
            @RequestParam(value = "departmentId", required = false) Department department,
            @PageableDefault Pageable pageable
    ) {
        return userService.getPageable(new UserFilterContainer(login,email,fullName,enabled,position,department,pageable));
    }

}
