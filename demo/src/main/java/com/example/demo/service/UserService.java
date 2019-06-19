package com.example.demo.service;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
 

@Repository
public class UserService  {
	
	JdbcTemplate jdbcTemplate;

	public UserService() {
		jdbcTemplate = new JdbcTemplate(setDataSource());
	}

	private DataSource setDataSource() {
		return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver")
				.url("jdbc:mysql://localhost:3306/emp_db").username("root").password("ravi").build();
	}

	
	public int addEmployee(User user) {
		String sql = "insert into user(fname,lname,email,userName,password) values(?,?,?,?,?)";
		return jdbcTemplate.update(sql,user.getfName(),user.getlName(),user.getEmail(),user.getUserName(),user.getPassword());
	}

}
