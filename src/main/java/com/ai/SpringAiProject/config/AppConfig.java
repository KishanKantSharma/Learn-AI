package com.ai.SpringAiProject.config;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;

import com.ai.SpringAiProject.dto.WeatherDTO;
import com.ai.SpringAiProject.service.WeatherService;

@Configuration
public class AppConfig{
	
	
	@Bean
	@Description("Get current weather of a city")
	Function<WeatherDTO.Request, WeatherDTO.Response> currentWeather(){
		
		return new WeatherService(); 
		
	}
		
}

