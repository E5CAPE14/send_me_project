package ru.team.sm.applicationsendme.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.RoleDao;
import ru.team.sm.applicationsendme.dao.impl.util.SingleResultUtil;
import ru.team.sm.applicationsendme.model.user.entity.Role;

import java.util.Optional;

@Repository
public class RoleDaoImpl extends ReadWriteDaoImpl<Role, Integer> implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Role> getRoleByName(String name) {
        TypedQuery<Role> typedQuery = entityManager.createQuery(
                        "SELECT r " +
                                "FROM Role r " +
                                "WHERE r.name = :name",
                        Role.class)
                .setParameter("name", name);
        return SingleResultUtil.getSingleResultOrNull(typedQuery);
    }
}
