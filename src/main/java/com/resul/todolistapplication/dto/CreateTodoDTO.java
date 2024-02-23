package com.resul.todolistapplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTodoDTO {
    @NotBlank(message = "Title cannot be blank.")
    private String title;

    @NotNull(message = "Content cannot be null.")
    private String content;
}
