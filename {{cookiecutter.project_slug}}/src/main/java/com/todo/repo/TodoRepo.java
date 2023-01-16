package com.todo.repo;

import com.todo.entity.TodoEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TodoRepo extends PagingAndSortingRepository<TodoEntity, Long> {
    List<TodoEntity> findAll();

}
