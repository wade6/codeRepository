package com.code.repository.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //  combines @Controller and @ResponseBody
public class HelloController {

	@RequestMapping("/")
	public String index() {
		return "Hello world!";
	}
}
