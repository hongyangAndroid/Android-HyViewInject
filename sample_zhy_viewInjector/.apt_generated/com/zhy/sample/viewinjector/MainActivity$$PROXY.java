// Generated code from HyViewInjector. Do not modify!
package com.zhy.sample.viewinjector;

import android.view.View;
import com.zhy.util.ioc.Finder;
import com.zhy.util.ioc.processor.AbstractInjector;

public class MainActivity$$PROXY<T extends MainActivity> implements AbstractInjector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
finder.setContentView( source , 2130903040 );
    View view;
    view = finder.findViewById(source , 2131230721 );
    target.mLvDetails = finder.castView( view , 2131230721 , "mLvDetails " );    view = finder.findViewById(source , 2131230720 );
    target.mTvName = finder.castView( view , 2131230720 , "mTvName " );  }

}
