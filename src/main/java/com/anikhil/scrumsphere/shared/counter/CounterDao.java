package com.anikhil.scrumsphere.shared.counter;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterDao extends MongoRepository<Counter, String> {
	@Query(value = "{ 'name' : ?0 }")
	Counter getCurrentCounter(String name);
}