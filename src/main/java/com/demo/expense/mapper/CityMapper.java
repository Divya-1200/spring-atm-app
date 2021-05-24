package com.demo.expense.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.demo.expensetracker.model.City;


@Mapper
public interface CityMapper {

	@Select("select * from city where state = #{state} limit 1")
	City findByState(@Param("state") String state);
	
	@Select("select * from city where state = #{state}")
	List<City> findByStates(@Param("state") String state);
}
