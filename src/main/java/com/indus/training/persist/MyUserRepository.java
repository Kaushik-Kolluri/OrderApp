package com.indus.training.persist;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indus.training.domain.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
	
	Optional<MyUser> findByUsername(String username);
	

}
