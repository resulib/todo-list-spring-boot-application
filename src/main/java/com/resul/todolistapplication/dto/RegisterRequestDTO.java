package com.resul.todolistapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {
    private String name;
    private String username;
    private String email;
    private String password;
}
