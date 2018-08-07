package com.black.board.common.di.component;;

import com.black.board.common.base.BaseDaggerApplication;
import com.black.board.common.di.module.AppModule;
import com.black.board.common.di.module.ClientModule;
import com.black.board.di.module.AllActivitysModule;
import com.black.board.di.module.AllFragmentModule;
import com.black.board.di.module.ApiServieModule;

import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;


@Singleton
@Component(modules = {AndroidInjectionModule.class
             , AndroidSupportInjectionModule.class
             , AllActivitysModule.class
             , AllFragmentModule.class
             , ClientModule.class
             , AppModule.class
             , ApiServieModule.class})
public interface AppComponent {

   void inject(BaseDaggerApplication application);
}
