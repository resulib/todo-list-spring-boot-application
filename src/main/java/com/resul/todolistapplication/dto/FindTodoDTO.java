package com.resul.todolistapplication.dto;

import com.resul.todolistapplication.shared.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindTodoDTO extends PageRequest {
    private Long userId;
    private String titlePhrase;
    private String contentPhrase;
}
