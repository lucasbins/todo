package com.todolist.todo.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime created;

  private boolean status;

  @PrePersist
  protected void onCreate() {
    ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
    created = LocalDateTime.now(zoneId);
  }

  public Todo(String description) {
    this.description = description;
  }
}
