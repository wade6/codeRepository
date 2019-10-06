package com.code.repository.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RestController //  combines @Controller and @ResponseBody
public class HelloController {


	private String name;

	@RequestMapping("/")
	public String index(String name) {

				return "Hello "+name;
	}

	public static void main(String[] args){

		System.out.println(TestEnum.valueOf("ALIEXPRESS").getId());


		Set<String> data = new HashSet<>();
		data.add("q");
		data.add("w");
		data.add("e");

		Iterator<String> it = data.iterator();
		while (it.hasNext()){
			String value = it.next();
			if(value.equals("w")){
				it.remove();
			}
		}

		System.out.println(data);

	}

}
