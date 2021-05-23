package com.demo.ExpenseMapper;

import java.util.List;
import com.demo.ExpenseTracker.model.*;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface CityMapper {

	@Select("select * from city where state = #{state} limit 1")
	City findByState(@Param("state") String state);
	
	@Select("select * from city where state = #{state}")
	List<City> findByStates(@Param("state") String state);
}
