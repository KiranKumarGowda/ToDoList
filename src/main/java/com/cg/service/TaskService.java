package com.cg.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.entity.Task;
import com.cg.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired

	TaskRepository taskrepo;

	public Task CreateTask(Task task) {
		return taskrepo.save(task);
	}

	public List<Task> GetAllTask() {
		return taskrepo.findAll();
	}

	public Task getbyid(int pid) {
		return taskrepo.findById(pid).get();
	}

	public Task updateTask(Task task) {
		return taskrepo.save(task);
	}
    
	public void  Delete(int pid)
	{
		 taskrepo.deleteById(pid);
		 
	}
}
