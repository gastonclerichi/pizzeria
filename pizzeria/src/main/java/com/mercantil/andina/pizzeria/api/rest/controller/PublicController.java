package com.mercantil.andina.pizzeria.api.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pizzeria/api/public")
public class PublicController 
{
	@GetMapping("/welcome")
	public String welcome()
	{
		return "Mercantil Andina - Api Rest - Pizzería!";
	}
}
