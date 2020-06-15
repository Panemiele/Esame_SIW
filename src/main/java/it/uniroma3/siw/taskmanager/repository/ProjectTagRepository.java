package it.uniroma3.siw.taskmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.taskmanager.model.ProjectTag;

public interface ProjectTagRepository  extends CrudRepository<ProjectTag, Long>{
	
	
    public List<ProjectTag> findByName(String name);


}
