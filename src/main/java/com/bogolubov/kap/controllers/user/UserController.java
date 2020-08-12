package com.bogolubov.kap.controllers.user;

import com.bogolubov.kap.controllers.user.util.UserFilterContainer;
import com.bogolubov.kap.dao.department.dto.DepartmentDto;
import com.bogolubov.kap.dao.position.dto.PositionDto;
import com.bogolubov.kap.dao.user.dto.UserDto;
import com.bogolubov.kap.dao.user.dto.UserDtoForPageable;
import com.bogolubov.kap.service.department.wrapper.DepartmentWrapper;
import com.bogolubov.kap.service.position.wrapper.PositionWrapper;
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
    private final DepartmentWrapper departmentWrapper;
    private final PositionWrapper positionWrapper;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDto getUser(@PathVariable(value = "id") Long id) {
        return userService.getUser(id);
    }

    @DeleteMapping(value = "/{id}")
    public UserDto deleteUser(@PathVariable(value = "id") Long id) {
        return userService.delete(id);
    }

    @GetMapping(value = "/pageable")
    public PagedResources<Resource<UserDtoForPageable>> getPageable(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "fullName", required = false) String fullName,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            @RequestParam(value = "positionId", required = false) PositionDto position,
            @RequestParam(value = "departmentId", required = false) DepartmentDto department,
            @PageableDefault Pageable pageable
    ) {
        return userService.getPageable(new UserFilterContainer(
                login,
                email,
                fullName,
                enabled,
                positionWrapper.dtoToEntity(position),
                departmentWrapper.dtoToEntity(department),
                pageable));
    }

    @PostMapping
    public UserDto saveOrUpdate(UserDto dto) {
        return userService.saveOrUpdate(dto);
    }

}
