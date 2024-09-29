package com.ai.SpringAiProject.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.ai.SpringAiProject.dto.WeatherDTO;

@Component
public class WeatherServiceBuilder {

    @Value("${spring.weather.api.base.uri}")
    private String weatherBaseURI ;

    @Value("${spring.weather.api.key}")
    private String weatherAPIKey ;


    private RestClient restClient = RestClient.create();

    public WeatherDTO.Response getWeather(String city){
        return restClient.get()
                .uri(UriComponentsBuilder.fromUriString(weatherBaseURI)
                        .path("/current.json")
                        .queryParam("key", weatherAPIKey)
                        .queryParam("q", city) 
                        .toUriString())
                .retrieve()
                .body(WeatherDTO.Response.class);
    }

}