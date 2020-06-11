package it.uniroma3.siw.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

import it.uniroma3.siw.taskmanager.controller.session.SessionData;
import it.uniroma3.siw.taskmanager.model.Credentials;
import it.uniroma3.siw.taskmanager.model.Project;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.service.ProjectService;
import it.uniroma3.siw.taskmanager.service.UserService;

@Controller
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	UserService userSerivice;
	
	@Autowired
	ProjectValidator projectValidator;
	
	@Autowired
	SessionData sessionData;
	
	
    @RequestMapping(value = { "/projects" }, method = RequestMethod.GET)
    public String myOwnedProjects(Model model) {
        User loggedUser = sessionData.getLoggedUser();
        List<Project> projectsList = projectService.retrieveProjectsOwnedBy(loggedUser);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("projectsList", projectsList);

        return "myOwnedProjects";
    }

}
