package com.proa.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proa.app.entities.Client;
import com.proa.app.exceptions.ClientNotFoundException;
import com.proa.app.services.IService;


@RestController
@RequestMapping("/client") //http://ip:port/client
public class MicroserviceController  {
	private static final Logger LOGGER=LoggerFactory.getLogger(MicroserviceController.class);
	@Autowired 
	private IService service;
	
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Client c){
		try {
			if(service.insert(c))
				return new ResponseEntity<>("insertado", HttpStatus.CREATED);
			
		}catch(Exception ex) {
			
			LOGGER.error("ERROR INSERT {}",ex.getMessage());
		}
		
		return new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping
	public ResponseEntity<List<Client>> selectAll(){
		return new ResponseEntity<>(service.selectAll(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<String> update(@RequestBody Client c){
		try {
			
			if(service.update(c))
				return new ResponseEntity<>("actualizado", HttpStatus.OK);
			
		}catch(ClientNotFoundException ex) {
			LOGGER.info(ex.getMessage());
			return new ResponseEntity<>("cliente no existe", HttpStatus.NOT_FOUND);
			
		}catch(Exception ex) {
			LOGGER.error("ERROR UPDATE {}",ex.getMessage());
		}
		
		return new ResponseEntity<>("internal error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/id") //http://ip:port/client/id?id=5
	public ResponseEntity<Client> selectById(@RequestParam long id){
		try {
			
			return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
			
		}catch(ClientNotFoundException ex) {
			LOGGER.info(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}catch(Exception ex) {
			LOGGER.error("ERROR SELECT BY ID {}",ex.getMessage());
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestParam long id){
		
		try {
			if(service.delete(id))
				return new ResponseEntity<>("eliminado", HttpStatus.OK);
			
		}catch(ClientNotFoundException ex) {
			LOGGER.info(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}catch(Exception ex) {
			LOGGER.error("ERROR DELETE {}",ex.getMessage());
		}
		
		return new ResponseEntity<>("internal error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	


}
