package com.gestioncursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestioncursos.model.CustomerDTO;
import com.gestioncursos.service.CustomerService;

@RestController
@RequestMapping("/api")
public class RestCustomer {

	@Autowired
	@Qualifier("customerService")
	private CustomerService customerService;
	
//	@GetMapping("/customers")
//	public List<CustomerDTO> getCustomers() {
//		return customerService.listAllCustomers();
//	}
	
	@GetMapping("/listCustomers")
	public ResponseEntity<?> getListCustomers() {
		List<CustomerDTO> list = customerService.listAllCustomers();
		if(list.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(list);
		}
	}
	
//	@GetMapping("/customers/{customerId}")
//	public CustomerDTO getCustomer(@PathVariable int customerId) {
//		return customerService.findByCustomerIdModel(customerId);
//	}
	
	@GetMapping("/listCustomers/{customerId}")
	public ResponseEntity<?> getCustomerResp(@PathVariable int customerId) {
		CustomerDTO customer = customerService.findByCustomerIdModel(customerId);
		if(customer==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(customer);
		}
	}
	
//	@PostMapping("/customers")
//	public CustomerDTO updateCustomer(@RequestBody CustomerDTO customer) {
//		customerService.addCustomer(customer);
//		return customer;
//	}
	
//	@PostMapping("/customersNew")
//	public ResponseEntity<?> insertCustomerNew(@RequestBody CustomerDTO customer) {
//		customerService.addCustomer(customer);
//		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
//	}
	
	@PostMapping("/customersNew")
	public ResponseEntity<?> insertCustomerNew(@RequestBody CustomerDTO customer) {
		
		return ResponseEntity.ok(customerService.addCustomer(customer));
	}
	
	@DeleteMapping("/customers/{customerId}")
	public void deleteCustomer(@PathVariable int customerId) {
		customerService.removeCustomer(customerId);
	}
	
}
