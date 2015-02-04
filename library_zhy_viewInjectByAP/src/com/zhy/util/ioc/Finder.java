package com.zhy.util.ioc;

import android.app.Activity;
import android.view.View;

public enum Finder
{
	VIEW
	{
		@Override
		public View findViewById(Object source, int id)
		{
			return ((View) source).findViewById(id);
		}

		public void setContentView(Object source, int layoutId)
		{
		}
	},
	ACTIVITY
	{
		@Override
		public View findViewById(Object source, int id)
		{
			return ((Activity) source).findViewById(id);
		}

		public void setContentView(Object source, int layoutId)
		{
			((Activity) source).setContentView(layoutId);
		}
	};

	public <T> T castView(View view, int id, String who)
	{
		return (T) view;
	}

	public abstract View findViewById(Object source, int id);

	public abstract void setContentView(Object source, int layoutId);
}
