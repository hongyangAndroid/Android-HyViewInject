// Generated code from HyViewInjector. Do not modify!
package com.zhy.sample.viewinjector;

import android.view.View;
import com.zhy.util.ioc.Finder;
import com.zhy.util.ioc.processor.AbstractInjector;

public class SecondActivity$$PROXY<T extends SecondActivity> implements AbstractInjector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
finder.setContentView( source , 2130903041 );
    View view;
    view = finder.findViewById(source , 2131230720 );
    target.mTvName = finder.castView( view , 2131230720 , "mTvName " );    view = finder.findViewById(source , 2131230722 );
    target.mFrameLayout = finder.castView( view , 2131230722 , "mFrameLayout " );  }

}
