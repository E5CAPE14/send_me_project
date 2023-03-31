package ru.team.sm.applicationsendme.dao.impl.util;

import jakarta.persistence.TypedQuery;

import java.util.Optional;

public class SingleResultUtil {
    public static <T> Optional<T> getSingleResultOrNull(TypedQuery<T> var) {
        try {
            return Optional.of(var.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}