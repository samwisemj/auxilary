package com.example.demo.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Customer;

@Repository
//public interface CustomerRepo extends JpaRepository<Customer, Integer> {
public interface CustomerRepo extends ElasticsearchRepository<Customer, Integer> {
}
