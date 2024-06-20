package com.indus.training.service;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.indus.training.domain.Orders;

import com.indus.training.persist.IOrderDAO;

@Service
public class OrderService {

	@Autowired
	private IOrderDAO orderDAO;

	public IOrderDAO getOrderDAO() {
		return orderDAO;
	}

	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	
	public Orders saveOrder(Orders oIn) {

		Orders save = null;

		save = orderDAO.save(oIn);

		return save;
	}


	public Orders retrieveById(Integer id) {
		
		Orders retrieve = null;
		
		retrieve = orderDAO.findById(id).get();
		
		return retrieve;
		
	}
	
	public List<Orders> retrieveAll(){
		
		List<Orders> getAll = orderDAO.findAll();
		
		return getAll;
		
	}
	
	public void deleteById(Integer id) {
		
		orderDAO.deleteById(id);
		
	}
}
