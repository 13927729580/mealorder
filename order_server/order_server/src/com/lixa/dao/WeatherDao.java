package com.lixa.dao;

import com.lixa.bean.City;
import com.lixa.bean.Weather;

public interface WeatherDao {

	
    /**
     * 
     * @param u
     * @return
     */
	int addWeather(Weather w);

	
}
