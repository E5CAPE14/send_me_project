package ru.team.sm.applicationsendme.service.impl.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.team.sm.applicationsendme.dao.abstracts.dto.UserDtoDao;
import ru.team.sm.applicationsendme.model.user.dto.UserDto;
import ru.team.sm.applicationsendme.service.abstracts.dto.UserDtoService;

import java.util.Optional;
@Service
public class UserDtoServiceImpl extends PaginationServiceDtoImpl<UserDto> implements UserDtoService {

    private final UserDtoDao userDtoDao;

    @Autowired
    public UserDtoServiceImpl(UserDtoDao userDtoDao) {
        this.userDtoDao = userDtoDao;
    }


    @Override
    public Optional<UserDto> getUserById(Integer id) {
        return userDtoDao.getUserById(id);
    }
}
