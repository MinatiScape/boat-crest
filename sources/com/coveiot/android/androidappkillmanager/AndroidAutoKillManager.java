package com.coveiot.android.androidappkillmanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import com.coveiot.android.androidappkillmanager.model.AutoKillManagerIntentModel;
import com.coveiot.android.androidappkillmanager.model.AutostartIntent;
import com.coveiot.android.androidappkillmanager.model.BatteryManagerIntent;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public class AndroidAutoKillManager {
    private static final String LOG_TAG = "AndroidAutoKillManager";
    private static final String TAG = "AppPowerManagement";
    private static AndroidAutoKillManager ourInstance;
    private Gson gson = new Gson();
    private Context mContext;
    private ArrayList<AutoKillManagerIntentModel> mPowerSavingConfigIntentModels;

    /* loaded from: classes2.dex */
    public class a extends TypeToken<List<AutoKillManagerIntentModel>> {
        public a(AndroidAutoKillManager androidAutoKillManager) {
        }
    }

    /* loaded from: classes2.dex */
    public class b extends TypeToken<List<AutoKillManagerIntentModel>> {
        public b(AndroidAutoKillManager androidAutoKillManager) {
        }
    }

    private AndroidAutoKillManager(Context context) {
        this.mContext = context.getApplicationContext();
        com.coveiot.android.androidappkillmanager.a.e(context);
    }

    private String getAppLabel(Context context) {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(context.getApplicationInfo().packageName, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "Unknown");
    }

    public static AndroidAutoKillManager getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AndroidAutoKillManager(context);
        }
        return ourInstance;
    }

    private boolean isIntentServicable(Intent intent) {
        return this.mContext.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private void prepareData(Context context) {
        if (this.mPowerSavingConfigIntentModels == null) {
            try {
                this.mPowerSavingConfigIntentModels = (ArrayList) this.gson.fromJson(com.coveiot.android.androidappkillmanager.a.b(context), new a(this).getType());
            } catch (Exception unused) {
                this.mPowerSavingConfigIntentModels = null;
                Log.d(LOG_TAG, "Preparing data from Assets, issue with JSON from server");
                prepareDataFromAssets(context);
            }
        }
    }

    private void prepareDataFromAssets(Context context) {
        try {
            this.mPowerSavingConfigIntentModels = (ArrayList) this.gson.fromJson(com.coveiot.android.androidappkillmanager.a.c(context), new b(this).getType());
        } catch (Exception e) {
            Log.d(LOG_TAG, e.toString());
            this.mPowerSavingConfigIntentModels = null;
        }
    }

    public Intent getAndroidBatteryOptimizationIntent() {
        prepareData(this.mContext);
        ArrayList<AutoKillManagerIntentModel> arrayList = this.mPowerSavingConfigIntentModels;
        Intent intent = null;
        if (arrayList != null && arrayList.size() > 0 && Build.VERSION.SDK_INT >= 23) {
            try {
                String packageName = this.mContext.getPackageName();
                Intent intent2 = new Intent();
                if (!((PowerManager) this.mContext.getSystemService("power")).isIgnoringBatteryOptimizations(packageName)) {
                    intent2.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                    intent2.setFlags(268435456);
                    intent2.setData(Uri.parse("package:" + packageName));
                    intent = intent2;
                } else {
                    String str = LOG_TAG;
                    Log.d(str, "Battery optimization already disabled " + intent2.getAction());
                }
            } catch (Exception unused) {
            }
        }
        return intent;
    }

    public Intent getAutoStartIntent() {
        Intent intent = new Intent();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        prepareData(this.mContext);
        ArrayList<AutoKillManagerIntentModel> arrayList = this.mPowerSavingConfigIntentModels;
        if (arrayList == null || arrayList.size() <= 0) {
            return intent;
        }
        Iterator<AutoKillManagerIntentModel> it = this.mPowerSavingConfigIntentModels.iterator();
        boolean z = false;
        while (it.hasNext()) {
            AutoKillManagerIntentModel next = it.next();
            if (next.getManufacturer().trim().toLowerCase().equals(str.trim().toLowerCase())) {
                Iterator<AutostartIntent> it2 = next.getAutostartIntents().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        AutostartIntent next2 = it2.next();
                        if (next2.getComponent() != null && !next2.getComponent().trim().equals("")) {
                            intent.setComponent(new ComponentName(next2.getComponent(), next2.getAction()));
                        } else {
                            intent.setAction(next2.getAction());
                        }
                        if (isIntentServicable(intent)) {
                            z = true;
                            if (next2.getExtras() != null) {
                                Iterator<String> it3 = next2.getExtras().iterator();
                                while (it3.hasNext()) {
                                    String trim = it3.next().trim();
                                    if (!trim.equals("package_name") && !trim.equals("packageName")) {
                                        if (trim.equals("package_label")) {
                                            intent.putExtra(trim, getAppLabel(this.mContext));
                                        }
                                    } else {
                                        intent.putExtra(trim, this.mContext.getPackageName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (z) {
            return intent;
        }
        return null;
    }

    public Intent getManufacturerBatteryOptimizationIntent() {
        Intent intent = new Intent();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        prepareData(this.mContext);
        ArrayList<AutoKillManagerIntentModel> arrayList = this.mPowerSavingConfigIntentModels;
        if (arrayList == null || arrayList.size() <= 0) {
            return intent;
        }
        Iterator<AutoKillManagerIntentModel> it = this.mPowerSavingConfigIntentModels.iterator();
        boolean z = false;
        while (it.hasNext()) {
            AutoKillManagerIntentModel next = it.next();
            if (next.getManufacturer().trim().toLowerCase().equals(str.trim().toLowerCase())) {
                Iterator<BatteryManagerIntent> it2 = next.getBatteryManagerIntents().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        BatteryManagerIntent next2 = it2.next();
                        if (next2.getComponent() != null && !next2.getComponent().trim().equals("")) {
                            intent.setComponent(new ComponentName(next2.getComponent(), next2.getAction()));
                        } else {
                            intent.setAction(next2.getAction());
                        }
                        if (isIntentServicable(intent)) {
                            z = true;
                            if (next2.getExtras() != null) {
                                Iterator<String> it3 = next2.getExtras().iterator();
                                while (it3.hasNext()) {
                                    String trim = it3.next().trim();
                                    if (!trim.equals("package_name") && !trim.equals("packageName")) {
                                        if (trim.equals("package_label")) {
                                            intent.putExtra(trim, getAppLabel(this.mContext));
                                        }
                                    } else {
                                        intent.putExtra(trim, this.mContext.getPackageName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (z) {
            return intent;
        }
        return null;
    }

    public void init() {
    }
}
