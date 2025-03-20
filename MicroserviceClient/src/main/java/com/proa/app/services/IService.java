package com.proa.app.services;

import java.util.List;

import com.proa.app.entities.Client;
import com.proa.app.exceptions.ClientNotFoundException;

public interface IService {
	boolean insert(Client c);
	List<Client> selectAll();
	boolean update(Client c)throws ClientNotFoundException;
	Client findById(long id)throws ClientNotFoundException;
	boolean delete(long id)throws ClientNotFoundException;
}
