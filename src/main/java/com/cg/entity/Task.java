package com.cg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Task 
{
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int id;
    @NotBlank(message="Task is mandatory")
	private String task;
	@NotNull()
	private boolean completed;
	
	public Task(){
		super();
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", task=" + task + ", completed=" + completed + "]";
	}

	public Task(int id, String task, boolean completed) {
		super();
		this.id = id;
		this.task = task;
		this.completed = completed;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	
	
	
}
