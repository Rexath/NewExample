package com.spring.login.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.login.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	
}
