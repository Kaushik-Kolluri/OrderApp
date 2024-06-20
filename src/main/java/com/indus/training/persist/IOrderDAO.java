package com.indus.training.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.indus.training.domain.Orders;

@Repository
public interface IOrderDAO extends JpaRepository<Orders, Integer> {
	

}
