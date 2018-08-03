package com.lixa.dao;

import java.util.List;

import com.lixa.bean.City;

public interface CityDao {

	
    /**
     * 
     * @param u
     * @return
     */
	int addCity(City c);
	
	List<City> getAllCity();

	
}
