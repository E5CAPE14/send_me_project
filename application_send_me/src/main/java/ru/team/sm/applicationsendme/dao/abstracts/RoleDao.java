package ru.team.sm.applicationsendme.dao.abstracts;

import ru.team.sm.applicationsendme.model.user.entity.Role;

import java.util.Optional;

public interface RoleDao extends ReadWriteDao<Role,Integer>{
    Optional<Role> getRoleByName(String name);
}
