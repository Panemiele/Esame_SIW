package it.uniroma3.siw.taskmanager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.taskmanager.controller.session.SessionData;
import it.uniroma3.siw.taskmanager.controller.validation.ProjectValidator;
import it.uniroma3.siw.taskmanager.controller.validation.TaskValidator;
import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.Task;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.service.ProjectService;
import it.uniroma3.siw.taskmanager.service.TaskService;
import it.uniroma3.siw.taskmanager.service.UserService;

@Controller
public class TaskController {
	@Autowired
	ProjectService projectService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	TaskValidator taskValidator;
	
	@Autowired
	SessionData sessionData;
	
	
	 @RequestMapping(value = { "/projects/tasks/{taskId}" }, method = RequestMethod.GET)
	    public String project(Model model, @PathVariable Long taskId) {
	    	Task task = taskService.getTask(taskId);
	        User loggedUser = sessionData.getLoggedUser();
	    	if(task == null)
	    		return "redirect:/projects";
	    	model.addAttribute("task", task);
	    	return "task";
	    }
	 
	    @RequestMapping(value = {"/projects/{projectId}/tasks/add"}, method = RequestMethod.GET)
	    public String createTaskForm(Model model, @PathVariable Long projectId) {
	    	User loggedUser = this.sessionData.getLoggedUser();
	    	model.addAttribute("loggedUser", loggedUser);
	    	model.addAttribute("taskForm", new Task());
	    	model.addAttribute("projectId", projectId);
	    	return "addTask";
	    }
	    
	    
	    @RequestMapping(value = {"/projects/{projectId}/tasks/add"}, method = RequestMethod.POST)
	    public String createProject(@Valid @ModelAttribute("taskForm")
	    							Task task,
	    							@PathVariable Long projectId,
	     							BindingResult taskBindingResult,
	    							Model model) {
	    	User loggedUser = this.sessionData.getLoggedUser();
	    	Project currentProject = projectService.getProject(projectId);
	    	taskValidator.validate(task, taskBindingResult);
	    	if(!taskBindingResult.hasErrors()) {
	    		currentProject.addTask(task);
	    		this.taskService.saveTask(task);
	    		return "redirect:/projects/tasks/" + task.getId();
	    	}
	    	model.addAttribute("loggedUser", loggedUser);
	    	return "addTask";
	    }
}
