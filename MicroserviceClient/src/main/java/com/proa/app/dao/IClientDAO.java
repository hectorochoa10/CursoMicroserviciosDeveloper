package com.proa.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.proa.app.entities.Client;

public interface IClientDAO extends CrudRepository<Client, Long>{

	@Query("SELECT u FROM Client u WHERE u.email=:email")
	Optional<Client> selectByEmail(@Param("email") String email);
	
	
}
