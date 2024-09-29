package com.ai.SpringAiProject.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.SpringAiProject.dto.WeatherDTO;
import com.ai.SpringAiProject.dto.WeatherDTO.Request;
import com.ai.SpringAiProject.dto.WeatherDTO.Response;

public class WeatherService implements Function<WeatherDTO.Request, WeatherDTO.Response>{

	@Autowired
	WeatherServiceBuilder weatherServiceBuilder;
	
	@Override
	public WeatherDTO.Response apply(WeatherDTO.Request request) {
		
		return weatherServiceBuilder.getWeather(request.city());
	}
}
