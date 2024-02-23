package com.resul.todolistapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class TodoDTO {
    private Long id;
    private String title;
    private String content;
    private Instant createdAt;
}
