package com.gestioncursos.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestioncursos.entity.Customer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository <Customer, Serializable> {

	public abstract Customer findById(int id);
	
}
