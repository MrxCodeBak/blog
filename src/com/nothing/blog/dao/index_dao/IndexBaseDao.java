package com.nothing.blog.dao.index_dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IndexBaseDao<T>
{
	T get(Class<T> clazz , Serializable id);
	
	void save(T so);
	
	void update(T so) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
	
	void delete(T so);
	
	void delete(Class<T> clazz , Serializable id);
	
	List<T> find(String queryStr);
	
	// field用于指定对哪个字段进行高亮
	List<T> highLightFind(String queryStr , Object[] params)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;	
	
}
