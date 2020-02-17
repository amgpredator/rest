package com.bogolubov.kap.repository.user;

import com.bogolubov.kap.controllers.user.util.UserFilterContainer;
import com.bogolubov.kap.dao.user.dto.UserDtoForPageable;
import org.springframework.data.domain.Page;

public interface UserRepositoryCustom {

    Page<UserDtoForPageable> getPageable(UserFilterContainer container);

}
