package com.proa.app.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proa.app.dao.IOrderDAO;
import com.proa.app.entities.Order;
import com.proa.app.exceptions.ClientNotFoundException;
import com.proa.app.exceptions.OrderNotFoundException;
import com.proa.app.feign.IFeignClientM;

import feign.FeignException.NotFound;

@Service
public class ServiceImpl implements IService{
	@Autowired
	private IOrderDAO dao;
	
	@Autowired
	private IFeignClientM feign;
	

	@Override
	public boolean insert(long idClient, double total) throws ClientNotFoundException{
		try {
			var response= feign.selectById(idClient);
			var order=new Order();
			order.setNameClient(response.getName());
			order.setAddressClient(response.getAddress());
			order.setEmailClient(response.getEmail());
			order.setDate(LocalDate.now());
			order.setTotal(total);
			
			var result=dao.save(order);
			
			return result!=null;
			
		}catch(NotFound ex) {
			throw new ClientNotFoundException(ex.getMessage());
			
		}
		
	
	}

	@Override
	public List<Order> selectAll() {
		return (List<Order>)dao.findAll();
	}

	@Override
	public boolean delete(long id) throws OrderNotFoundException{
	     if(dao.existsById(id)) {
	    	 dao.deleteById(id);
	    	 return true;
	     }
		
		throw new OrderNotFoundException("Order "+id+" no existe!!");
	}

}
