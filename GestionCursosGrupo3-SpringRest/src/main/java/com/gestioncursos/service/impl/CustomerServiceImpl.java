package com.gestioncursos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gestioncursos.entity.Customer;
import com.gestioncursos.model.CustomerDTO;
import com.gestioncursos.repository.CustomerRepository;
import com.gestioncursos.service.CustomerService;


@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	@Qualifier("customerRepository")
	private CustomerRepository customerRepository;
	
	@Override
	public CustomerDTO addCustomer(CustomerDTO customerDTO) {
		customerRepository.save(transform(customerDTO));
		return customerDTO;
	}

	@Override
	public List<CustomerDTO> listAllCustomers() {
		return customerRepository.findAll().stream().map(c->transform(c)).collect(Collectors.toList());
	}

	@Override
	public Customer findCustomerById(int id) {
		return customerRepository.findById(id);
	}

	@Override
	public CustomerDTO findByCustomerIdModel(int id) {
		return transform(customerRepository.findById(id));
	}

	@Override
	public void removeCustomer(int id) {
		customerRepository.deleteById(id);
	}

	@Override
	public Customer transform(CustomerDTO customerDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(customerDTO,Customer.class);
	}

	@Override
	public CustomerDTO transform(Customer customer) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(customer,CustomerDTO.class);
	}
	

	
}
