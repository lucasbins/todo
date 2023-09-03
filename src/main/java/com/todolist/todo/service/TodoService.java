package com.todolist.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.todo.model.Todo;
import com.todolist.todo.repository.TodoRepository;

@Service
public class TodoService {

  @Autowired
  TodoRepository repository;

  public void saveTodo(Todo todo){
    this.repository.save(todo);
  }

  public Optional<Todo> findById (Long id) {
    return this.repository.findById(id);
  }

  public List<Todo> findAll(){
    return this.repository.findAll();
  }

  public void deleteTodo(Todo todo){
    this.repository.delete(todo);
  }
}
