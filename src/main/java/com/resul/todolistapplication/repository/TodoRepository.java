package com.resul.todolistapplication.repository;

import com.resul.todolistapplication.entity.TodoEntity;
import com.resul.todolistapplication.vo.FindTodoVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long>, JpaSpecificationExecutor<TodoEntity> {

//    @Query("SELECT t FROM TodoEntity t " +
//            " WHERE  (:#{#findTodoVO.userId} IS NULL OR t.userEntity.id = :#{#findTodoVO.userId})" +
//            " AND (:#{#findTodoVO.titlePhrase} IS NULL OR t.title LIKE %:#{#findTodoVO.titlePhrase}%)" +
//            " AND (:#{#findTodoVO.contentPhrase} IS NULL OR t.content LIKE %:#{#findTodoVO.contentPhrase}%)" +
//            " AND (t.isDeleted = FALSE) ORDER BY t.id")
//    Page<TodoEntity> findAll(FindTodoVO findTodoVO, Pageable pageable);

    Optional<TodoEntity> findByIdAndIsDeleted(Long id, boolean isDeleted);
}
