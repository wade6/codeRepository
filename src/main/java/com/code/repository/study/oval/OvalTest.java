package com.code.repository.study.oval;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.IsInvariant;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.util.List;

/**
 * 数据校验框架  http://oval.sourceforge.net/userguide.html
 * 
 * @author zhaoyuan.lizy
 *
 */
public class OvalTest {
	
	@Min(20)
    private int age;
	
	@NotNull
	@NotEmpty
    @Length(min = 6, max = 10)
    private String name;
    
    
    public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@IsInvariant
	@NotNull 
	@Length(max = 1)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		
        OvalTest ovalTest = new OvalTest();
        ovalTest.setAge(21);
        ovalTest.setName("hello");
        
        Validator validator = new Validator(); // 验证类
        List<ConstraintViolation> ret = validator.validate(ovalTest);// 验证对象
        
        for(ConstraintViolation cv : ret){
        	System.out.println(cv.getMessage());
        }
        
    }
	

}
