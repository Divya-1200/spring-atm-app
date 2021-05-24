package com.demo.expense.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.expensetracker.model.Expense;


@Mapper
public interface CityMapper {

//	@Select("select * from city where state = #{state} limit 1")
//	City findByState(@Param("state") String state);
//	
//	@Select("select * from city where state = #{state}")
//	List<City> findByStates(@Param("state") String state);
	
	@Insert("insert into atm (username, password, amount) values (#{name}, #{password}, #{amount})") 
	void insertUser(@Param("name")String name , @Param("password") String password, @Param("amount") int amount);
	
	@Update("update city set state = #{state} where name = #{city}")
	void updateCity(@Param("state") String state, @Param("city") String city);
	
	@Select("select * from atm where username = #{name} and password = #{password}")
	Expense findByUser(@Param("name") String name, @Param("password") String password);
}
