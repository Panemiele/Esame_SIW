package it.uniroma3.siw.progetto.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.progetto.model.Credentials;

public interface CredentialsRepository extends CrudRepository<Credentials, Long>{
	public Optional<Credentials> findByUserName(String username);
}
