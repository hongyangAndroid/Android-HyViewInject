// Generated code from HyViewInjector. Do not modify!
package com.zhy.sample.viewinjector;

import android.view.View;
import com.zhy.util.ioc.Finder;
import com.zhy.util.ioc.processor.AbstractInjector;

public class BeanAdaper$ViewHolder$$PROXY<T extends BeanAdaper.ViewHolder> implements AbstractInjector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findViewById(source , 2131230723 );
    target.mTitle = finder.castView( view , 2131230723 , "mTitle " );    view = finder.findViewById(source , 2131230724 );
    target.mDetail = finder.castView( view , 2131230724 , "mDetail " );  }

}
