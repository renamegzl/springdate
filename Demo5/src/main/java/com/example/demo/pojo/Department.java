package com.example.demo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="Department")
@Entity
public class Department {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Integer id;
	private String name;
	@Column(name="age",length=5,nullable=false)
	private String age;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	
	
}
