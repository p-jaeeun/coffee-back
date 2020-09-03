package com.example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.example.beans.ParkingBean;

public interface ParkingMapper {
	@Select("select * from parking")
	public List<ParkingBean> selectAllParkings();

}

