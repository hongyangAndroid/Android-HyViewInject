package com.zhy.sample.viewinjector;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhy.util.ioc.HyViewInjector;
import com.zhy.util.ioc.annotation.InjectView;

@InjectView(R.layout.activity_second)
public class SecondActivity extends FragmentActivity
{
	@InjectView(R.id.id_tv_name)
	public TextView mTvName;

	@InjectView(R.id.id_fra_container)
	public FrameLayout mFrameLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);
		HyViewInjector.inject(this);
		mTvName.setText("Hello HyInject From Second Act");

		FragmentManager fm = getSupportFragmentManager();
		SecondActFragment fragment = (SecondActFragment) fm
				.findFragmentById(R.id.id_fra_container);
		if (fragment == null)
		{
			fragment = new SecondActFragment();
			fm.beginTransaction().add(R.id.id_fra_container, fragment).commit();

		}

	}

}
