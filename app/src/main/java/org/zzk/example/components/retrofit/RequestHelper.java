package org.zzk.example.components.retrofit;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Created by gzsll on 2014/9/23 0023.
 */
public class RequestHelper {

  private Context mContext;

  public RequestHelper(Context mContext) {
    this.mContext = mContext;
  }


  public String getAndroidId() {
    return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
  }

  public String getDeviceId() {
    String deviceId;
    TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
    if (tm.getDeviceId() == null) {
      deviceId = getAndroidId();
    } else {
      deviceId = tm.getDeviceId();
    }
    return deviceId;
  }

}
