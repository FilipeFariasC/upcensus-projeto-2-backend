package br.edu.ifpb.upcensus.infrastructure.util;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class BeanUtils {
	private BeanUtils() {}
	public static String[] getNullPropertyNames (Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<>();
	    for(PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (isNullableOrEmpty(srcValue)) emptyNames.add(pd.getName());
	    }

	    String[] result = new String[emptyNames.size()];
	    
	    return emptyNames.toArray(result);
	}
	
	
	private static boolean isNullableOrEmpty(Object object) {
		return ObjectUtils.isNull(object) || 
			CollectionUtils.isEmpty(object) || 
			StringUtils.isEmpty(object.toString());
	}
	
    public static <T> T copyProperties(T src, T target) {
    	org.springframework.beans.BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    	return target;
    }
}
