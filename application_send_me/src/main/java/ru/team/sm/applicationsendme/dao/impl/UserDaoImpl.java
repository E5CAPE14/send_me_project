package ru.team.sm.applicationsendme.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import ru.team.sm.applicationsendme.dao.abstracts.UserDao;
import ru.team.sm.applicationsendme.dao.impl.util.SingleResultUtil;
import ru.team.sm.applicationsendme.model.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl extends ReadWriteDaoImpl<User,Integer> implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Cacheable(value = "users", key = "#email")
    public Optional<User> getByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery(
                        "SELECT u " +
                                "FROM User u " +
                                "INNER JOIN FETCH u.role " +
                                "WHERE u.email = :email " +
                                "AND u.isEnable = true",
                        User.class)
                .setParameter("email", email);
        return SingleResultUtil.getSingleResultOrNull(query);
    }

    @Override
    @Cacheable(value = "users", key = "#email")
    public Boolean isPresentByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT COUNT(u)>0 " +
                                "FROM User u " +
                                "WHERE u.email = :email " +
                                "AND u.isEnable = true",
                        Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public List<User> getAll() {
        return entityManager.createQuery(
                        "SELECT u " +
                                "FROM User u " +
                                "INNER JOIN FETCH u.role " +
                                "WHERE u.isEnable = true",
                        User.class)
                .getResultList();
    }

    @Override
    @CacheEvict(value = "users", key = "#email")
    public void deleteByEmail(String email) {
        entityManager.createQuery(
                        "UPDATE User " +
                                "SET isEnable = false " +
                                "WHERE email = :email")
                .setParameter("email", email)
                .executeUpdate();
    }

    @Override
    @CacheEvict(value = "users", key = "#email")
    public void updatePasswordByEmail (String email, String password) {
        entityManager.createQuery(
                        "UPDATE User u " +
                                "SET u.password = :password " +
                                "WHERE u.email = :email")
                .setParameter("password", password)
                .setParameter("email", email).executeUpdate();
    }

}
