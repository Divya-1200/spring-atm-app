package com.demo.ExpenseTracker.Expdao;


import java.sql.ResultSet;    
import java.sql.SQLException;    
import java.util.List;    
import org.springframework.jdbc.core.BeanPropertyRowMapper;    
import org.springframework.jdbc.core.JdbcTemplate;    
import org.springframework.jdbc.core.RowMapper; 

import com.demo.ExpenseTracker.model.Expense;

public class ExpDao {
	JdbcTemplate template;
	
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		
	}
	
	public int save(Expense exp) {
		String sql="insert into atm(name,password,amount) values('"+exp.getName()+"',"+exp.getPassword()+",'0')";   
		return template.update(sql);
	}
	
}
