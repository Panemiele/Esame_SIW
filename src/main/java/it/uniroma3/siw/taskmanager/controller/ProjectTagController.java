package it.uniroma3.siw.taskmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.taskmanager.controller.validation.ProjectTagValidator;
import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.ProjectTag;
import it.uniroma3.siw.taskmanager.model.Task;
import it.uniroma3.siw.taskmanager.service.ProjectService;
import it.uniroma3.siw.taskmanager.service.ProjectTagService;
import it.uniroma3.siw.taskmanager.service.TaskService;

@Controller
public class ProjectTagController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectTagService projectTagService;
	
	@Autowired
	ProjectTagValidator projectTagValidator;
	
	@Autowired
	TaskService taskService;
	
	
	@RequestMapping(value = "/projects/{projectId}/tag/{tagId}", method = RequestMethod.GET)
	public String tag(@PathVariable Long projectId, @PathVariable Long tagId, Model model) {
		ProjectTag tag = this.projectTagService.getProjectTag(tagId);
		model.addAttribute("tag", tag);
		model.addAttribute("projectId", projectId);
		return "tag";
	}
	
	
	@RequestMapping(value = "/project/{projectId}/addTag", method = RequestMethod.GET)
	public String addTagForm(@PathVariable Long projectId, Model model) {
		model.addAttribute("tagForm", new ProjectTag());
		return "addTag";
	}
	
	
	@RequestMapping(value = "/project/{projectId}/addTag", method = RequestMethod.POST)
	public String addTag(@PathVariable Long projectId,
						 @Valid @ModelAttribute ProjectTag projectTag,
						 BindingResult projectTagBindingResult, Model model) {
		Project project = this.projectService.getProject(projectId);
		this.projectTagValidator.validate(projectTag, projectTagBindingResult);
		if(!projectTagBindingResult.hasErrors()) {
			project.addTag(projectTag);
			this.projectTagService.saveProjectTag(projectTag);
			return "redirect:/projects/" + projectId;
		}
		return "addTag";
	}
	
	
	@RequestMapping(value = { "/projects/{projectId}/tasks/{taskId}/addTag" }, method = RequestMethod.GET)
	public String addTagToTaskForm(@PathVariable Long projectId,
								   @PathVariable Long taskId,
								   Model model) {
		model.addAttribute("tagForm", new ProjectTag());
		return "addTag";
	}
	
	
	@RequestMapping(value = { "/projects/{projectId}/tasks/{taskId}/addTag" }, method = RequestMethod.POST)
	public String addTagToTask(@PathVariable Long projectId,
							   @PathVariable Long taskId,
							   @Valid @ModelAttribute ProjectTag projectTag,
							   BindingResult projectTagBindingResult,
							   Model model) {
		Task task = this.taskService.getTask(taskId);
		this.projectTagValidator.validate(projectTag, projectTagBindingResult);
		if(!projectTagBindingResult.hasErrors()) {
			this.projectTagService.saveProjectTag(projectTag);
			this.taskService.addTagToTask(task, projectTag);
			return "redirect:/projects/" + projectId + "/tasks/" + taskId;
		}
		return "addTag";
	}
}
