package com.resul.todolistapplication.specification;

import com.resul.todolistapplication.entity.TodoEntity;
import com.resul.todolistapplication.entity.TodoEntity_;
import com.resul.todolistapplication.entity.UserEntity_;
import com.resul.todolistapplication.vo.FindTodoVO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public final class TodoSpecification implements Specification<TodoEntity> {
    private final FindTodoVO findTodoVO;

    @Override
    public Predicate toPredicate(Root<TodoEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.where(
                userId())
                .and(titleLike())
                .and(contentLike())
                .and(isNotDeleted())
                .toPredicate(root, query, criteriaBuilder);
    }

    public Specification<TodoEntity> userId() {
        var userId = findTodoVO.getUserId();
        if (userId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.join(TodoEntity_.userEntity).get(UserEntity_.id), userId);
    }

    public Specification<TodoEntity> titleLike() {
        var titlePhrase = findTodoVO.getTitlePhrase();
        if (titlePhrase == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get(TodoEntity_.title), '%' + titlePhrase + '%');
    }

    public Specification<TodoEntity> contentLike() {
        var contentPhrase = findTodoVO.getContentPhrase();
        if (contentPhrase == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(root.get(TodoEntity_.content), '%' + contentPhrase + '%');
    }

    public Specification<TodoEntity> isNotDeleted() {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(TodoEntity_.isDeleted), false);
    }
}
