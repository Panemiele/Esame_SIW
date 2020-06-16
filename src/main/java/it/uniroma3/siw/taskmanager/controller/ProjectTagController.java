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
import it.uniroma3.siw.taskmanager.service.ProjectService;
import it.uniroma3.siw.taskmanager.service.ProjectTagService;

@Controller
public class ProjectTagController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectTagService projectTagService;
	
	@Autowired
	ProjectTagValidator projectTagValidator;
	
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
}
