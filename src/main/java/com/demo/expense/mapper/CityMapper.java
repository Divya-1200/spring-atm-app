package com.demo.expense.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.demo.expensetracker.model.City;


@Mapper
public interface CityMapper {

	@Select("select * from city where state = #{state} limit 1")
	City findByState(@Param("state") String state);
	
	@Select("select * from city where state = #{state}")
	List<City> findByStates(@Param("state") String state);
	
	@Insert("insert into city (name, state, country) values (#{name}, #{state}, #{country})") 
	void insertCity(@Param("name")String name , @Param("state") String state, @Param("country") String country);
	
	@Update("update city set state = #{state} where name = #{city}")
	void updateCity(@Param("state") String state, @Param("city") String city);
	
}
