package com.resul.todolistapplication.dto;

import com.resul.todolistapplication.shared.PageRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserDTO extends PageRequest {
    private String namePhrase;
    private String emailPhrase;
}
