package it.uniroma3.siw.progetto.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.progetto.model.Project;
import it.uniroma3.siw.progetto.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	
	public Optional<User> findByUsername(String username);
	
	public List<User> findByVisibleProjects(Project project);
}
