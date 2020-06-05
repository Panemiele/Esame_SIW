package it.uniroma3.siw.progetto.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.progetto.model.Credentials;
import it.uniroma3.siw.progetto.repository.CredentialsRepository;

@Service
public class CredentialsService {
	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	@Transactional
	public Credentials getCredentials(long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);		//Se non trovo le credenziali, restituisco null
	}
	
	@Transactional
	public Credentials getCredentials(String userName) {
		Optional<Credentials> result = this.credentialsRepository.findByUserName(userName);
		return result.orElse(null);		//Se non trovo le credenziali, restituisco null
	}
	
	@Transactional
	public Credentials saveCredentials(Credentials credentials) {
		credentials.setRole(Credentials.DEFAULT_ROLE);
		return this.credentialsRepository.save(credentials);
	}
}
