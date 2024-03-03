package com.resul.todolistapplication.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindTodoVO {
    private Long userId;
    private String titlePhrase;
    private String contentPhrase;
}
