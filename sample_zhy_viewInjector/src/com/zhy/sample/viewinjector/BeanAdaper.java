package com.zhy.sample.viewinjector;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zhy.sample.viewinjector.bean.Bean;
import com.zhy.util.ioc.HyViewInjector;
import com.zhy.util.ioc.annotation.InjectView;

public class BeanAdaper extends ArrayAdapter<Bean>
{
	public BeanAdaper(Context context, List<Bean> objects)
	{
		super(context, 0, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = null;
		if (convertView == null)
		{
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.item_lv, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mTitle.setText(getItem(position).getTitle());
		holder.mDetail.setText(getItem(position).getDetail());
		return convertView;
	}

	static class ViewHolder
	{
		@InjectView(R.id.id_tv_title)
		TextView mTitle;
		@InjectView(R.id.id_tv_detail)
		TextView mDetail;
		
		public ViewHolder(View view)
		{
			HyViewInjector.inject(this, view);
		}
	}

}
