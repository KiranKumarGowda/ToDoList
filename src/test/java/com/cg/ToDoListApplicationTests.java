package com.cg;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.entity.Task;
import com.cg.repository.TaskRepository;
import com.cg.service.TaskService;

@SpringBootTest
public class ToDoListApplicationTests
{
	@Autowired
	TaskRepository taskrepo;
	
	@Test
	//@Order(1)
	public void testCreate ()
	{
		Task task=new Task();
		task.setId(452);
		task.setTask("gym");
		task.setCompleted(false);
		taskrepo.save(task);
		assertNotNull(taskrepo.findById(452).get());
		
		
		task.setId(502);
		task.setTask("Study");
		task.setCompleted(true);
		taskrepo.save(task);
		assertNotNull(taskrepo.findById(502).get());
	}
	
	@Test
	@Order(2)
	public void TaskReadAll () {
		List list = taskrepo.findAll();
		assertThat(list).size().isGreaterThan(0);
	
	
	}
	
    	@Test
    	public void TaskRead () {
  		Task task = taskrepo.findByTask("Study");
		assertEquals(502, task.getId());
      }
    	
    	@Test
    	public void task()
    	{
    		Task task=taskrepo.findById(502).get();
    		assertEquals("Study" , task.getTask());
    		
    	}
}

  	

