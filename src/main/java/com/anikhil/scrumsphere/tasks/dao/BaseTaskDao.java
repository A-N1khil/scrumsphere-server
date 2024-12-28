package com.anikhil.scrumsphere.tasks.dao;

import com.anikhil.scrumsphere.tasks.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseTaskDao extends MongoRepository<Task, String> {

	@Query(value = "{ 'taskId' : ?0 }")
	Task findTaskByTaskId(String taskId);
}
