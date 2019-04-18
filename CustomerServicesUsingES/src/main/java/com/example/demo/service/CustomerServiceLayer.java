package com.example.demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerRepo;
import com.example.demo.pojo.Customer;

@Service
public class CustomerServiceLayer {

	@Autowired
	private CustomerRepo dao;

	public CustomerServiceLayer() {
		super();
	}

	public void add(Customer customer) {
		dao.save(customer);
	}

	public ArrayList<Customer> viewAll() {
		Iterator<Customer> iterator = dao.findAll().iterator();
		ArrayList<Customer> customers = new ArrayList<>();
		while (iterator.hasNext())
			customers.add(iterator.next());
		return customers;
	}

	public Optional<Customer> findById(int id) {
		return dao.findById(id);
	}

	public void update(Customer customer) {
		dao.save(customer);
	}

	public void delete(int id) {
		dao.deleteById(id);
	}

	public void update(int id, String name, String address) throws Exception {
		if (address == null || address.equalsIgnoreCase(""))
			throw new Exception("Address is missing");
		if (findById(id).isPresent()) {
			if (name.equalsIgnoreCase(""))
				name = findById(id).get().getName();
		} else
			throw new Exception("Id " + id + " is not valid");
		dao.save(new Customer(id, name, address));

	}
}
