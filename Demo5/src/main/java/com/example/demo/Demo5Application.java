package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.pojo.Department;
import com.example.demo.service.DepartmentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication
public class Demo5Application {

	@Autowired
	public static DepartmentRepository departmentRepository;
	
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(Demo5Application.class, args);
		final List<Department> all = departmentRepository.findAll();

        System.out.println(all.get(0).getName());

        final Department one = departmentRepository.findOne(1);
        System.out.println(one.getName());
		
	}
	@Test
	public  void find(EntityManager entityManager){
		    
		 	final List<Department> all = departmentRepository.findAll();

	        System.out.println(all.get(0).getName());

	        final Department one = departmentRepository.findOne(1);
	        System.out.println(one.getName());
	}
	
	
}
