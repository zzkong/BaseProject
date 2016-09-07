package org.zzk.example.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;

import org.zzk.example.R;


/**
 * Created by zzk on 16/7/30.
 */
public class ResourceUtil {

    public static int getThemeColor(@NonNull Context context){
        return getThemeAttrColor(context, R.attr.colorPrimary);
    }

    public static int getThemeAttrColor(@NonNull Context context, @AttrRes int attr){
        TypedArray a = context.obtainStyledAttributes(null, new int[]{ attr });
        try {
            return a.getColor(0, 0);
        }finally {
            a.recycle();
        }
    }

    public static int getStatusBarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(resourceId > 0){
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
