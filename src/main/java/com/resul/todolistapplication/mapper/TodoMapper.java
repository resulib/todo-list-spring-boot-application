package com.resul.todolistapplication.mapper;

import com.resul.todolistapplication.dto.CreateTodoDTO;
import com.resul.todolistapplication.dto.TodoDTO;
import com.resul.todolistapplication.dto.UpdateTodoDTO;
import com.resul.todolistapplication.entity.TodoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    List<TodoDTO> toTodoDTOList(List<TodoEntity> todoEntities);

    TodoEntity toTodoEntity(CreateTodoDTO createTodoDTO);

    TodoDTO toTodoDTO(TodoEntity todoEntity);

    TodoEntity toTodoEntity(UpdateTodoDTO updateTodoDTO, @MappingTarget TodoEntity todoEntity);
}
