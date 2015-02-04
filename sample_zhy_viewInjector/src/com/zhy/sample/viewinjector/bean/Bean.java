package com.zhy.sample.viewinjector.bean;

import java.util.ArrayList;
import java.util.List;

public class Bean
{
	private String title;
	private String detail;

	public Bean(String title, String detail)
	{
		super();
		this.title = title;
		this.detail = detail;
	}

	public static List<Bean> generateDatas()
	{
		List<Bean> beans = new ArrayList<Bean>();

		beans.add(new Bean("hello", "helloworld"));
		beans.add(new Bean("world", "welcome"));

		return beans;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getDetail()
	{
		return detail;
	}

	public void setDetail(String detail)
	{
		this.detail = detail;
	}

	
}
