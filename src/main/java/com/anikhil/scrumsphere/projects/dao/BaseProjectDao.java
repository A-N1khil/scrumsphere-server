package com.anikhil.scrumsphere.projects.dao;

import com.anikhil.scrumsphere.projects.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseProjectDao extends MongoRepository<Project, String> {


}
