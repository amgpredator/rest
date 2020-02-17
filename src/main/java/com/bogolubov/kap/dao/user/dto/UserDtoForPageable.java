package com.bogolubov.kap.dao.user.dto;

import com.bogolubov.kap.dao.department.entity.Department;
import com.bogolubov.kap.dao.position.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoForPageable {

    private Long id;
    private String login;
    private String email;
    private String password;
    private String fullName;
    private boolean enabled;
    private Position position;
    private Department department;

}
