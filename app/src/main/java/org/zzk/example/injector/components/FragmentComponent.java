package org.zzk.example.injector.components;

import android.app.Activity;

import org.zzk.example.injector.PerFragment;
import org.zzk.example.injector.modules.FragmentModule;
import org.zzk.example.ui.gank.GankFragment;
import org.zzk.example.ui.meitu.MeituListFragment;

import dagger.Component;

/**
 * Created by zwl on 16/9/5.
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(GankFragment gankFragment);

    void inject(MeituListFragment meituListFragment);
}
