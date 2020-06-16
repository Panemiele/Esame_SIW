package it.uniroma3.siw.taskmanager.controller.validation;


import org.springframework.validation.Validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import it.uniroma3.siw.taskmanager.model.ProjectTag;

@Component
public class ProjectTagValidator  implements Validator{
	final Integer MAX_NAME_LENGTH = 100;
	final Integer MIN_NAME_LENGTH = 2;
	final Integer MAX_DESCRIPTION_LENGTH = 1000;

	
    @Override
    public void validate(Object o, Errors errors) {
    	ProjectTag projectTag = (ProjectTag) o;
    	String name = projectTag.getName().trim();
    	String description = projectTag.getDescription().trim();

        if (name.isBlank())
            errors.rejectValue("name", "required");
        else if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH)
            errors.rejectValue("name", "size");
        
        if (description.isBlank())
            errors.rejectValue("description", "required");
        else if (description.length() > MAX_DESCRIPTION_LENGTH)
            errors.rejectValue("description", "size");
    }

	@Override
	public boolean supports(Class<?> clazz) {
		return ProjectTag.class.equals(clazz);
	}
}
