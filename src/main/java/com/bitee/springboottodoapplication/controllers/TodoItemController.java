package com.bitee.springboottodoapplication.controllers;


import java.time.Instant;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.bitee.springboottodoapplication.Repositories.TodoItemRepository;

@RestController
public class TodoItemController{
    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);
   
    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("request to get Index");
        
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepository.findAll());
        modelAndView.addObject(("today"), Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }
}
