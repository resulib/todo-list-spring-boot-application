package com.resul.todolistapplication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTodoDTO {
    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotBlank(message = "Content cannot be blank.")
    private String content;
}
