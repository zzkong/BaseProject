package org.zzk.example.utils;

import android.support.design.widget.NavigationView;
import android.view.View;

import org.zzk.example.ui.BaseActivity;

/**
 * Created by zzk on 15/11/27.
 */
public class EventHelper {

    public static void click(BaseActivity baseActivity, View ...views){
        if(!(baseActivity instanceof View.OnClickListener)) return;
        if(views == null || views.length == 0) return;
        for (View v : views) v.setOnClickListener((View.OnClickListener) baseActivity);
    }

    public static void setNavigationItemSelected(BaseActivity baseActivity, View ...views){
        if(!(baseActivity instanceof NavigationView.OnNavigationItemSelectedListener)) return;
        if(views == null  || views.length == 0) return;
        for (View v : views) ((NavigationView)v).setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) baseActivity);
    }
}
