package com.resul.todolistapplication.repository;

import com.resul.todolistapplication.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    @Query("SELECT t FROM TodoEntity t WHERE t.userEntity.id = :userId and t.isDeleted = :isDeleted")
    Page<TodoEntity> findAllByIdAndIsDeleted(Long userId, boolean isDeleted, Pageable pageable);

    Optional<TodoEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
}
