package com.bitee.springboottodoapplication.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.bitee.springboottodoapplication.models.TodoItem;

public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {
    
    
}
