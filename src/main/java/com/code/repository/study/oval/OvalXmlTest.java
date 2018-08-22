package com.code.repository.study.oval;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.configuration.xml.XMLConfigurer;
import net.sf.oval.guard.Guard;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

public class OvalXmlTest {
	
	private Long number;
	
	private String name;
	
	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) throws IOException {
		
		OvalXmlTest test = new OvalXmlTest();
		test.setName("12");
		test.setNumber(12L);
		
		ClassPathResource re = new ClassPathResource("com/alibaba/webx/study/oval/ovaloval-config.xml");
		XMLConfigurer xmlConfigurer = new XMLConfigurer(re.getFile());
		Guard guard = new Guard(xmlConfigurer);
		
		List<ConstraintViolation> list = guard.validate(test);
		
		for(ConstraintViolation v:list){
			System.out.println(v.getMessage());
		}
	}

}
