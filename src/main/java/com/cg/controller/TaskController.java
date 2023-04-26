package com.cg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Task;
import com.cg.service.TaskService;

@RestController
@RequestMapping
public class TaskController {
	@Autowired
	private TaskService taskservice;
	
	@PostMapping("/addtask")
	public Task createtask(@RequestBody Task task)
	{
		return taskservice.CreateTask(task);
		
	}
	
	@GetMapping("/getall")
	public List<Task> getAll()
	{
		return taskservice.GetAllTask();
	}
	
	@GetMapping("/getById/{id}")
	public Task getbyid(@PathVariable("id") int pid)
	{
		return taskservice.getbyid(pid);
		
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskservice.updateTask(task));
     }
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<Boolean> delete(@PathVariable("id") int pid , @RequestBody Task task)
	{
		
		 taskservice.Delete(pid);
		 return ResponseEntity.ok(true);
		 
	}
}
