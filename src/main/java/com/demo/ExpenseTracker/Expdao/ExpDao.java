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
		String sql="insert into atm values(?,?,?)";   
		return template.update(sql, new Object[] {exp.getName(), exp.getPassword(), exp.getAmount()});
	}
	
}
