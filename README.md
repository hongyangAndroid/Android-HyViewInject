# Android-HyViewInject
一个Android的ViewInject的注入库，基于编译时注解的例子。

仅仅是个例子，不要使用的到项目中，如果需要请使用butterknife.

# 效果图

![Sample Screenshots][1]

效果图，第一个Activity中布局文件为TextView和ListView，第二个Activity中为
Fragment，主要测试Activity、Fragment、Adapter中的注入。

# 用法

### In Activity

```java
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

```

### In Fragment

```java
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
```



### In Adapter

```java
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

```

# 启用编译时注解
![Sample Screenshots][2]
按照上图步骤，启动即可。

# 关于我

[我的博客地址][3]

[1]: https://github.com/hongyangAndroid/Android-HyViewInject/blob/master/sample_zhy_viewInjector/screen_shot.gif
[2]: https://github.com/hongyangAndroid/Android-HyViewInject/blob/master/sample_zhy_viewInjector/enbled.gif
[3]: http://blog.csdn.net/lmj623565791
