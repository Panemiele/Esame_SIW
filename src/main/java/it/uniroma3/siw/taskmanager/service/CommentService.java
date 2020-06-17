package it.uniroma3.siw.taskmanager.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.taskmanager.model.Comment;
import it.uniroma3.siw.taskmanager.model.User;
import it.uniroma3.siw.taskmanager.repository.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	protected CommentRepository commentRepository;
	
	@Transactional
	public Comment getComment(Long id) {
		
		Optional<Comment> c =  commentRepository.findById(id);
		return c.orElse(null);		
	}
	
	@Transactional
	public Comment saveComment(Comment c, User u) {
		c.setOwner(u);
		return commentRepository.save(c);
	}
	
	@Transactional
	public void deleteComment(Comment c) {
		commentRepository.delete(c);
	}

}
