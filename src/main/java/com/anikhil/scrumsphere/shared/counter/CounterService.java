package com.anikhil.scrumsphere.shared.counter;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CounterService {

	private final CounterDao counterDao;
	private final MongoTemplate mongoTemplate;

	public CounterService(CounterDao counterDao, MongoTemplate mongoTemplate) {
		this.counterDao = counterDao;
		this.mongoTemplate = mongoTemplate;
	}

	public Counter getAll() {
		Counter counter = counterDao.getCurrentCounter("counter");
		LOGGER.info("Current status: {}", counter);
		return counter;
	}

	public Long getCounterForType(CounterType counterType) {
		Counter counter = this.incrementAndGet(counterType);
		return switch (counterType) {
			case TASKS -> counter.getTasks();
			case EPICS -> counter.getEpics();
			case PROJECTS -> counter.getProjects();
		};
	}

	public Counter incrementAndGet(CounterType counterType) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is("counter"));
		Update update = new Update().inc(counterType.getType(), 1);
		FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);
		return this.mongoTemplate.findAndModify(query, update, options, Counter.class);
	}

	public Long getNextCounterForType(CounterType counterType) {
		Counter counter = this.getAll();
		return switch (counterType) {
			case TASKS -> counter.getTasks() + 1;
			case EPICS -> counter.getEpics() + 1;
			case PROJECTS -> counter.getProjects() + 1;
		};
	}
}