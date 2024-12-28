package com.anikhil.scrumsphere.tasks.dao;

import com.anikhil.scrumsphere.tasks.models.Task;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TaskDao {

	private final BaseTaskDao baseTaskDao;

	public Task save(Task task) {
		return this.baseTaskDao.save(task);
	}

	public List<Task> getAllTasks() {
		return this.baseTaskDao.findAll();
	}

	public Page<Task> getAllTasks(int page, int size) {
		return this.baseTaskDao.findAll(PageRequest.of(page, size));
	}


	public Task getTaskById(String id) {
		return this.baseTaskDao.findTaskByTaskId(id);
	}
}
