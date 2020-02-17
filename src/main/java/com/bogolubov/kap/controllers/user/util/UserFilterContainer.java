package com.bogolubov.kap.controllers.user.util;

import com.bogolubov.kap.dao.department.entity.Department;
import com.bogolubov.kap.dao.position.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserFilterContainer {

    private String login;
    private String email;
    private String fullName;
    private Boolean enabled;
    private Position position;
    private Department department;
    private Pageable pageable;

}
