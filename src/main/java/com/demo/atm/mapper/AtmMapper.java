package com.demo.atm.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.atm.model.Expense;


@Mapper
public interface AtmMapper {

//	@Select("select * from city where state = #{state} limit 1")
//	City findByState(@Param("state") String state);
//	
//	@Select("select * from city where state = #{state}")
//	List<City> findByStates(@Param("state") String state);
	
	@Insert("insert into atm (username, password, amount) values (#{name}, #{password}, #{amount})") 
	void insertUser(@Param("name")String name , @Param("password") String password, @Param("amount") int amount);
	
	@Update("update atm set amount = amount + #{amount} where username = #{name}")
	void depositBalance(@Param("name") String name, @Param("amount") int amount);
	
	@Select("select * from atm where username = #{name} and password = #{password}")
	Expense findByUser(@Param("name") String name, @Param("password") String password);
	
	@Select("select amount from atm where username = #{name}")
	int findBalance(@Param("name") String name);
	
	@Update("update atm set amount = amount - #{amount} where username = #{name}")
	void withdrawBalance(@Param("name") String name, @Param("amount") int amount);
}
