package com.resul.todolistapplication.mapper;

import com.resul.todolistapplication.dto.CreateTodoDTO;
import com.resul.todolistapplication.dto.FindTodoDTO;
import com.resul.todolistapplication.dto.TodoDTO;
import com.resul.todolistapplication.dto.UpdateTodoDTO;
import com.resul.todolistapplication.entity.TodoEntity;
import com.resul.todolistapplication.vo.FindTodoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    List<TodoDTO> toTodoDTOList(List<TodoEntity> todoEntities);

    TodoEntity toTodoEntity(CreateTodoDTO createTodoDTO);

    TodoDTO toTodoDTO(TodoEntity todoEntity);

    TodoEntity toTodoEntity(UpdateTodoDTO updateTodoDTO, @MappingTarget TodoEntity todoEntity);

    @Mapping(target = "titlePhrase", expression = "java(trimToNull(findTodoDTO.getTitlePhrase()))")
    @Mapping(target = "contentPhrase", expression = "java(trimToNull(findTodoDTO.getContentPhrase()))")
    FindTodoVO toFindTodoVO(FindTodoDTO findTodoDTO);

    default String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
