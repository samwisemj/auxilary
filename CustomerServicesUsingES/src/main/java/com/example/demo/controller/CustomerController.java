package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Customer;
import com.example.demo.service.CustomerServiceLayer;

@RestController
@RequestMapping(value = "/customers", produces = MediaType.ALL_VALUE)
public class CustomerController {

	@Autowired
	private CustomerServiceLayer service;

	public CustomerController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestBody Customer customer) {
		// Customer customer=new Customer(id, name, address);
		service.add(customer);
		return "Done";
	}

	@RequestMapping(value = "/viewAll", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = {
			MediaType.ALL_VALUE })
	public ArrayList<Customer> viewAll() {

		return service.viewAll();
	}

	@RequestMapping(value = "/viewById/{id}", method = RequestMethod.GET)
	public Customer viewById(@PathVariable(value = "id") int id) throws Exception {
		if (service.findById(id).isPresent())
			return service.findById(id).get();
		else
			throw new Exception("Id " + id + " is not found");
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public String update(@PathVariable(value = "id") int id,
			@RequestParam(value = "name", required = false, defaultValue = "") String name,
			@RequestParam(value = "address") String address) throws Exception {
		service.update(id, name, address);
		return "done";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable(value = "id", required = true) int id) {
		service.delete(id);
		return "Deleted record with id " + id + ".";
	}
}
