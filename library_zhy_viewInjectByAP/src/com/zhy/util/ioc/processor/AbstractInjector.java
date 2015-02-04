package com.zhy.util.ioc.processor;

import com.zhy.util.ioc.Finder;

public interface AbstractInjector<T>
{
	
	void inject(Finder finder, T target, Object source);
	
}
