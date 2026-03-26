package com.epam.techcheck.specification;

import com.epam.techcheck.entity.Task;
import com.epam.techcheck.enumeration.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class TaskSpecification {

    public static Specification<Task> titleLike(String title) {
        return (root, query, criteriaBuilder) ->
                title == null ? null : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")),
                        "%" + title.toLowerCase().trim() + "%"
                );
    }

    public static Specification<Task> statusEquals(TaskStatus status) {
        return (root, query, criteriaBuilder) ->
                status == null ? null : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Task> assignedUserIdEquals(UUID userId) {
        return (root, query, criteriaBuilder) ->
                userId == null ? null : criteriaBuilder.equal(root.get("assignedTo").get("id"), userId);
    }

    public static Specification<Task> hasDeleted(Boolean deleted) {
        return (root, query, criteriaBuilder) ->
                deleted == null ? null : criteriaBuilder.equal(root.get("deleted"), deleted);
    }
}
