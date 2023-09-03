package com.todolist.todo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.todo.dtos.TodoDTO;
import com.todolist.todo.model.Todo;
import com.todolist.todo.service.TodoService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("todo")
public class TodoController {

  @Autowired
  TodoService service; 
  
  @GetMapping
  public List<Todo> findAll () {
    return this.service.findAll();
  }

  @PostMapping
  public ResponseEntity<Todo> newTodo(@RequestBody TodoDTO data) {
    if(data == null) return ResponseEntity.badRequest().build();

    Todo newTodo = new Todo(data.description());

    this.service.saveTodo(newTodo);

    return ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Void> completeTodo(@PathVariable Long id) {
    Todo todo = this.service.findById(id).get();

    if(todo != null){
      todo.setStatus(true);
      this.service.saveTodo(todo);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Todo> editTodo(@PathVariable Long id, @RequestBody TodoDTO data) {
      if(data == null) return ResponseEntity.badRequest().build();

      Optional<Todo> findTodo = this.service.findById(id);

      if(findTodo.get() == null) return ResponseEntity.badRequest().build();

      Todo newTodo = findTodo.get();

      newTodo.setDescription(data.description());
      this.service.saveTodo(newTodo);

      return ResponseEntity.ok(newTodo);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id){
    Todo todo = this.service.findById(id).get();

    if(todo != null){
      this.service.deleteTodo(todo);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
