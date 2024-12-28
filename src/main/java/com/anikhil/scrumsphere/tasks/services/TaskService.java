package com.anikhil.scrumsphere.tasks.services;

import com.anikhil.scrumsphere.shared.BaseService;
import com.anikhil.scrumsphere.shared.counter.CounterService;
import com.anikhil.scrumsphere.shared.counter.CounterType;
import com.anikhil.scrumsphere.tasks.dao.TaskDao;
import com.anikhil.scrumsphere.tasks.models.Task;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@Service
public class TaskService implements BaseService<Task> {

	private final TaskDao taskDao;
	private final CounterService counterService;

	@Autowired
	public TaskService(TaskDao taskDao, CounterService counterService) {
		this.taskDao = taskDao;
		this.counterService = counterService;
	}

	@Override
	public Task create(Task task) {

		// Generate a new ID for the task
		Long counter = this.counterService.getCounterForType(CounterType.TASKS);
		String id = String.format("TASK-%d", counter);
		LOGGER.info("Creating task with ID: {}", id);
		task.setTaskId(id);

		// Add the created date
		task.setCreatedDate(LocalDate.now());

		// Post update rituals
		this.addUpdateDate(task);

		return this.taskDao.save(task);
	}

	@Override
	public Task saveUpdate(Task task) {

		// Set the updated date
		this.addUpdateDate(task);
		return this.taskDao.save(task);
	}

	private void addUpdateDate(Task task) {
		task.setUpdatedDate(LocalDate.now());
	}

	public List<Task> getAllTasks() {
		return this.taskDao.getAllTasks();
	}

	public Task getTaskById(String id) {
		return this.taskDao.getTaskById(id);
	}
}
