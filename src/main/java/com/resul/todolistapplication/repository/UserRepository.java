package com.resul.todolistapplication.repository;

import com.resul.todolistapplication.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByIsDeleted(boolean isDeleted);

    Optional<UserEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
}