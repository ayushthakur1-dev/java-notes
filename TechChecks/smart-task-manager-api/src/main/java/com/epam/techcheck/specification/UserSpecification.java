package com.epam.techcheck.specification;

import com.epam.techcheck.entity.User;
import com.epam.techcheck.enumeration.UserRole;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasRole(UserRole role) {
        return (root, query, criteriaBuilder) ->
                role == null ? null : criteriaBuilder.equal(root.get("role"), role);
    }

    public static Specification<User> emailEquals(String email) {
        return (root, query, criteriaBuilder) ->
                email == null ? null : criteriaBuilder.equal(root.get("email"), email);
    }

    public static Specification<User> nameContainsIgnoreCase(String name) {
        return (root, query, criteriaBuilder) ->
                (name == null || name.isBlank()) ? null : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")) ,
                        "%" + name.trim().toLowerCase() + "%"
                );
    }

    public static Specification<User> hasDeleted(Boolean deleted) {
        return (root, query, criteriaBuilder) ->
                (deleted == null) ? null : criteriaBuilder.equal(root.get("deleted"), deleted);
    }
}
