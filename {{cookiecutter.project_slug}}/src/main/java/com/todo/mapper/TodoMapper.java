package com.todo.mapper;

import com.todo.dto.Todo;
import com.todo.entity.TodoEntity;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class TodoMapper {

    public abstract Todo generate(TodoEntity input);

    @InheritInverseConfiguration
    public abstract TodoEntity generate(Todo input);
}
