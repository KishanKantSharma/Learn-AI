package com.ai.SpringAiProject.dto;

public class WeatherDTO {

    public record Request(String city){}
    
    public record Response(Location location, Current current){}
    
    public record Location(String name, String country){}
    
    public record Current(String temp_c){}

}