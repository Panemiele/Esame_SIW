package it.uniroma3.siw.taskmanager.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.taskmanager.model.Comment;
import it.uniroma3.siw.taskmanager.model.User;

public interface CommentRepository extends CrudRepository<Comment,Long>{

	
	@Transactional
	public Comment findByOwner(User user);
}
