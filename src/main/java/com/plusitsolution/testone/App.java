package com.plusitsolution.testone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication (scanBasePackages = {"com.plusitsolution"})
public class App 
{
	public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
