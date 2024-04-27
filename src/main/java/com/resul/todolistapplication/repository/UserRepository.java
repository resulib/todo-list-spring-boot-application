package com.resul.todolistapplication.repository;

import com.resul.todolistapplication.entity.UserEntity;
import com.resul.todolistapplication.vo.FindUserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT user FROM UserEntity user " +
            " WHERE (:#{#findUserVO.namePhrase} IS NULL OR user.name LIKE %:#{#findUserVO.namePhrase}%)" +
            " AND (:#{#findUserVO.emailPhrase} IS NULL OR user.email LIKE %:#{#findUserVO.emailPhrase}%)" +
            " AND (user.isDeleted = FALSE) ORDER BY user.id")
    Page<UserEntity> findAll(FindUserVO findUserVO, Pageable pageable);

    Optional<UserEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findByUsernameAndIsDeleted(String username, boolean isDeleted);
}
