package com.todo.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private long id;
    private String task;
    private boolean completed;
}
