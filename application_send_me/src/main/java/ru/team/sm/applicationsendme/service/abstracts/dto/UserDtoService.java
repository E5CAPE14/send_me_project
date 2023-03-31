package ru.team.sm.applicationsendme.service.abstracts.dto;

import ru.team.sm.applicationsendme.model.user.dto.UserDto;

import java.util.Optional;

public interface UserDtoService extends PaginationServiceDto<UserDto> {

    Optional<UserDto> getUserById(Integer id);

}
