package com.resul.todolistapplication.repository;

import com.resul.todolistapplication.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findAllByIsDeleted(boolean isDeleted);

    @Query("SELECT t FROM TodoEntity t WHERE t.userEntity.id = :userId and t.isDeleted = :isDeleted")
    List<TodoEntity> findAllByIdAndIsDeleted(Long userId, boolean isDeleted);

    Optional<TodoEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
}
