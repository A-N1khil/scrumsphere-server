package com.anikhil.scrumsphere.projects.dao;

import com.anikhil.scrumsphere.projects.models.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProjectDao {

	private final BaseProjectDao baseProjectDao;

	public List<Project> getAllProjects() {
		return baseProjectDao.findAll();
	}
}
