package com.zhy.sample.viewinjector;

import com.zhy.util.ioc.HyViewInjector;
import com.zhy.util.ioc.annotation.InjectView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondActFragment extends Fragment
{
	@InjectView(R.id.id_tv_title)
	public TextView mTitle ; 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_second, container , false);
		HyViewInjector.inject(this, view);
		
		mTitle.setText("I am a title in fragment!");
		return view ; 
	}

}
