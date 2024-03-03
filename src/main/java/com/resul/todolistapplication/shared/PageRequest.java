package com.resul.todolistapplication.shared;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest{
    private int page = 0;
    private int size = 50;

}
