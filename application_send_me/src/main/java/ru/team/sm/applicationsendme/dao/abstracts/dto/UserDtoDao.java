package ru.team.sm.applicationsendme.dao.abstracts.dto;

import ru.team.sm.applicationsendme.model.user.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserDtoDao {

    Optional<UserDto> getUserById(Integer id);
    List<UserDto> getAll();

}
