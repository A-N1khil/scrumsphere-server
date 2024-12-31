package com.anikhil.scrumsphere.projects.services;

import com.anikhil.scrumsphere.projects.dao.ProjectDao;
import com.anikhil.scrumsphere.projects.models.Project;
import com.anikhil.scrumsphere.shared.BaseService;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class ProjectService implements BaseService<Project> {

	private final ProjectDao projectDao;

	public List<Map<String, String>> getAllProjectNamesAndIds() {

		List<Map<String, String>> projectNamesAndIds = Lists.newLinkedList();
		this.projectDao.getAllProjects().forEach(project -> projectNamesAndIds.add(Map.of("projectId", project.getProjectId(), "projectName", project.getProjectName())));
		return projectNamesAndIds;
	}

	@Override
	public Project create(Project project) {
		return null;
	}

	@Override
	public Project saveUpdate(Project project) {
		return null;
	}

	public List<Project> getAllProjects() {
		return this.projectDao.getAllProjects();
	}
}
