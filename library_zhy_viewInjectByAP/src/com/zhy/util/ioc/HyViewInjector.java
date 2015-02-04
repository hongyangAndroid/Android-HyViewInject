package com.zhy.util.ioc;

import java.net.IDN;
import java.util.LinkedHashMap;
import java.util.Map;

import com.zhy.util.ioc.processor.AbstractInjector;
import com.zhy.util.ioc.processor.ProxyInfo;

import android.app.Activity;
import android.view.View;

public class HyViewInjector
{
	static final Map<Class<?>, AbstractInjector<Object>> INJECTORS = new LinkedHashMap<Class<?>, AbstractInjector<Object>>();

	public static void inject(Activity activity)
	{
		AbstractInjector<Object> injector = findInjector(activity);
		injector.inject(Finder.ACTIVITY, activity, activity);
	}

	public static void inject(View view)
	{
	}

	public static void inject(Object target, View view)
	{
		AbstractInjector<Object> injector = findInjector(target);
		injector.inject(Finder.VIEW, target, view);
	}
	
	
	private static AbstractInjector<Object> findInjector(Object activity)
	{
		Class<?> clazz = activity.getClass();
		AbstractInjector<Object> injector = INJECTORS.get(clazz);
		if (injector == null)
		{
			try
			{
				Class injectorClazz = Class.forName(clazz.getName() + "$$"
						+ ProxyInfo.PROXY);
				injector = (AbstractInjector<Object>) injectorClazz
						.newInstance();
				INJECTORS.put(clazz, injector);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} catch (InstantiationException e)
			{
				e.printStackTrace();
			} catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		return injector;
	}
}
