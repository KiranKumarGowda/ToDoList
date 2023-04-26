package com.cg.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer>
{
	 public Task findByTask(String task);

}
