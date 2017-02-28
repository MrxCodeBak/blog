package com.nothing.blog.dao.index_dao.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassHighlighter;
import org.compass.core.CompassHits;
import org.compass.core.CompassSession;
import org.compass.core.CompassTransaction;
import org.compass.spring.CompassDaoSupport;

import com.nothing.blog.dao.index_dao.IndexBaseDao;

public class IndexBaseDaoImpl<T> extends CompassDaoSupport implements IndexBaseDao<T>
{
	@Override
	public T get(Class<T> clazz, Serializable id)
	{
		return getCompassTemplate().get(clazz, id);
	}

	@Override
	public void save(T so)
	{
		getCompassTemplate().save(so);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void update(T so) throws NoSuchMethodException
	, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		// 下面3行代码就是通过反射获取so的getId()的返回值
		Class clazz = so.getClass();
		Method getId = clazz.getMethod("getId");
		Serializable idVal = (Serializable) getId.invoke(so);
		// 先删除该so的id值对应的记录。
		delete(clazz , idVal);
		// 保存新的so
		save(so);
	}

	@Override
	public void delete(T so)
	{
		getCompassTemplate().delete(so);
	}

	@Override
	public void delete(Class<T> clazz, Serializable id)
	{
		delete(get(clazz , id));
	}

	@Override
	public List<T> find(String queryStr)
	{
		CompassSession sess = getCompass().openSession();
		CompassTransaction tx = sess.beginTransaction();
		
		// 执行检索
		CompassHits hits = sess.find(queryStr);
		List<T> result = new ArrayList<>();
		// 遍历检索结果，并将检索结果添加到List集合中
		for(int i = 0 ; i < hits.length(); i ++)
		{
			result.add((T) hits.data(i));
		}
		tx.commit();
		sess.close();
		return result;
	}

	@Override
	public List<T> highLightFind(String queryStr, Object[] params)
			throws NoSuchMethodException
			, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		CompassSession sess = getCompass().openSession();
		CompassTransaction tx = sess.beginTransaction();		
		// 执行检索
		CompassHits hits = sess.find(queryStr);
		List<T> result = new ArrayList<>();
		// 遍历检索结果，并将检索结果添加到List集合中
		for(int i = 0 ; i < hits.length(); i ++)
		{
			T so = (T) hits.data(i);
			CompassHighlighter highlighter = hits.highlighter(i);
			for (int j = 0; j < params.length; j++) {
				// 得到field对应的getter方法
				Method getter = so.getClass().getMethod("get"
						+ params[j].toString().substring(0 , 1).toUpperCase() // 首字母大写
						+ params[j].toString().substring(1));
				String fieldVal = (String) getter.invoke(so , null);
				String highlighterVal = highlighter.fragment(params[j].toString(), fieldVal);
				// 如果高亮后的文本内容存在
				if(highlighterVal != null && highlighterVal.trim().length() > 0)
				{
					// 得到field对应的setter方法
					Method setter = so.getClass().getMethod("set"
							+ params[j].toString().substring(0 , 1).toUpperCase() // 首字母大写
							+ params[j].toString().substring(1) , String.class);
					setter.invoke(so , highlighterVal);// 相当于调用了setter方法
				}
			}
			result.add(so);
		}
		tx.commit();
		sess.close();	
		return result;
	}

}
