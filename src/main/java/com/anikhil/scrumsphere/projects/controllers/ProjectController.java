package com.anikhil.scrumsphere.projects.controllers;

import com.anikhil.scrumsphere.http.BaseController;
import com.anikhil.scrumsphere.projects.services.ProjectService;
import com.anikhil.scrumsphere.utils.JsonUtils;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectController extends BaseController {

	private final ProjectService projectService;

	@Autowired
	public ProjectController(JsonUtils jsonUtils, ProjectService projectService) {
		super(jsonUtils);
		this.projectService = projectService;
	}

	@GetMapping("all")
	public JsonNode getAllProjects() {
		return sendSuccessResponse(projectService.getAllProjects());
	}

	@GetMapping("idsAndName")
	public JsonNode getAllProjectNamesAndIds() {
		return sendSuccessResponse(projectService.getAllProjectNamesAndIds());
	}

}
