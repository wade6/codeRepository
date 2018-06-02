package com.code.repository.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //  combines @Controller and @ResponseBody
public class HelloController {
	
	private String name;

	@RequestMapping("/")
	public String index(String name) {
		return "Hello "+name;
	}
}
