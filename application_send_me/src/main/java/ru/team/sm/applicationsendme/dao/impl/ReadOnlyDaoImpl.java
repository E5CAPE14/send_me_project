package ru.team.sm.applicationsendme.dao.impl;

import ru.team.sm.applicationsendme.dao.abstracts.ReadOnlyDao;
import ru.team.sm.applicationsendme.dao.impl.util.SingleResultUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class ReadOnlyDaoImpl<E, K> implements ReadOnlyDao<E, K> {
    @PersistenceContext
    private EntityManager entityManager;

    private final Class<E> clazz = (Class<E>)((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];

    public List<E> getAll() {
        return entityManager.createQuery("FROM " + clazz.getName()).getResultList();
    }

    public boolean existsById(K id) {
        long count = (long) entityManager.createQuery("SELECT COUNT(e) FROM " + clazz.getName() + " e WHERE e.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    public Optional<E> getById(K id) {
        TypedQuery<E> query = (TypedQuery<E>) entityManager.createQuery("FROM " + clazz.getName() + " WHERE id = :id")
                .setParameter("id", id);
        return SingleResultUtil.getSingleResultOrNull(query);
    }

    public List<E> getAllByIds(Iterable<K> ids) {
        if (ids != null && ids.iterator().hasNext()) {
            return entityManager.createQuery("FROM " + clazz.getName() + " e WHERE e.id IN :ids")
                    .setParameter("ids", ids)
                    .getResultList();
        } else {
            return new ArrayList<>();
        }
    }

    public boolean existsByAllIds(Collection<K> ids) {
        if (ids != null && ids.size() > 0) {
            Long count = (Long) entityManager.createQuery("SELECT COUNT(*) FROM " + clazz.getName() + " e WHERE e.id IN :ids")
                    .setParameter("ids", ids)
                    .getSingleResult();
            return ids.size() == count;
        }
        return false;
    }
}