package com.code.repository.util;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

/**
 * bean copy
 * 
 * @author zhaoyuanli
 *
 */
public class BeanUtil {

	/**
	 * cglib's beanCopier 
	 * 
	 * must and only has set get method
	 *   
	 */
	public static void cglibBeanCopier(Object source,Object target){
		
		final BeanCopier copy = BeanCopier.create(source.getClass(), target.getClass(), false);
		
		copy.copy(source,target,null);
	}
	
	/**
	 * spring's BeanUtils
	 * 
	 * @param source
	 * @param target
	 */
	public static void springBeanUtils(Object source,Object target){
		BeanUtils.copyProperties(source, target);
	}
	


}

