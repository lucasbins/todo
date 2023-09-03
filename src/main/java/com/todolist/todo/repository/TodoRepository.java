package com.todolist.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{
  
}
