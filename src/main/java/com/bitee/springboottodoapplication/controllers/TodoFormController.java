package com.bitee.springboottodoapplication.controllers;


import java.time.Instant;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitee.springboottodoapplication.Repositories.TodoItemRepository;
import com.bitee.springboottodoapplication.models.TodoItem;

@Controller 
public class TodoFormController {
    private final Logger logger = LoggerFactory.getLogger(TodoFormController.class);


    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem){
        return "add-todo-item";
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem,BindingResult result, Model model){
         if(result.hasErrors()){
            
            return "add-todo-item";
        }
        todoItem.setCreateDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        TodoItem todoItem = todoItemRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));
    
        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id,@Valid TodoItem todoItem,BindingResult result,Model model ){
        if(result.hasErrors()){
            todoItem.setId(id);
            return "update-todo-item";
        }
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model){
         TodoItem todoItem = todoItemRepository
        .findById(id)
        .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));
        
        todoItemRepository.delete(todoItem);
        return "redirect:/";
    }

}
