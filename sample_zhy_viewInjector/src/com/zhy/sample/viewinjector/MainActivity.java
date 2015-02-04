package com.zhy.sample.viewinjector;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.sample.viewinjector.bean.Bean;
import com.zhy.util.ioc.HyViewInjector;
import com.zhy.util.ioc.annotation.InjectView;

@InjectView(R.layout.activity_main)
public class MainActivity extends Activity
{
	@InjectView(R.id.id_tv_name)
	public TextView mTvName;
	@InjectView(R.id.id_lv_detail)
	ListView mLvDetails;
	private BeanAdaper mAdapter;
	private List<Bean> mDatas = Bean.generateDatas();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		HyViewInjector.inject(this);
		mAdapter = new BeanAdaper(this, mDatas);
		mLvDetails.setAdapter(mAdapter);
		
		mTvName.setText("Hello HyInject");
		mTvName.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				startActivity(intent);
			}
		});
	}
	
	@Override
	protected void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

}
