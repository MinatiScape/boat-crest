package com.coveiot.android.permissionsandsettings;

import android.app.Application;
import com.coveiot.android.androidappkillmanager.AndroidAutoKillManager;
/* loaded from: classes5.dex */
public class AndroidAppKillManagerApplication extends Application {
    @Override // android.app.Application
    public void onCreate() {
        AndroidAutoKillManager.getInstance(getApplicationContext()).init();
        super.onCreate();
    }
}
