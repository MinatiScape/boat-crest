package com.abupdate.iot_libs.engine;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import com.abupdate.trace.Trace;
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f1891a = {"android.permission.INTERNET", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.WAKE_LOCK", "android.permission.ACCESS_NETWORK_STATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.RECEIVE_BOOT_COMPLETED", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.ACCESS_COARSE_LOCATION"};
    public static final String[] b = {"com.abupdate.iot_libs.receiver.UpgradeReceiver"};
    public static final String[] c = {"com.abupdate.iot_libs.service.OtaService", "com.abupdate.mqtt_libs.mqtt_service.MqttService"};
    public static final String[] d = {"com.abupdate.iot_libs.service.OtaService", "com.abupdate.mqtt_libs.mqtt_service.MqttService", "com.abupdate.iot_libs.service.JobSchedulerService"};

    public static boolean a(Context context) {
        boolean c2 = c(context);
        boolean e = e(context);
        if (c2) {
            return e;
        }
        return false;
    }

    public static boolean b(PackageManager packageManager, Context context) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        try {
            ActivityInfo[] activityInfoArr = packageManager.getPackageInfo(context.getPackageName(), 2).receivers;
            int i = 0;
            while (true) {
                String[] strArr = b;
                if (i >= strArr.length) {
                    break;
                }
                String str = strArr[i];
                if (activityInfoArr != null) {
                    z = false;
                    for (ActivityInfo activityInfo : activityInfoArr) {
                        if (activityInfo.name.equals(str)) {
                            z = true;
                        }
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    if (sb.length() == 0) {
                        sb.append("please add Receiver in AndroidManifest:\n");
                    }
                    sb.append(str);
                    sb.append("\n");
                }
                i++;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (sb.length() == 0) {
            return true;
        }
        Trace.e("FotaVerifyManager", sb.toString());
        return false;
    }

    public static boolean c(Context context) {
        boolean z;
        PackageManager packageManager = context.getPackageManager();
        StringBuilder sb = new StringBuilder();
        try {
            String[] strArr = packageManager.getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            int i = 0;
            while (true) {
                String[] strArr2 = f1891a;
                if (i >= strArr2.length) {
                    break;
                }
                String str = strArr2[i];
                if (strArr != null) {
                    z = false;
                    for (String str2 : strArr) {
                        if (str2.equals(str)) {
                            z = true;
                        }
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    if (sb.length() <= 0) {
                        sb.append("please add permissions in AndroidManifest:\n");
                    }
                    sb.append(str);
                    sb.append("\n");
                }
                i++;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (sb.length() == 0) {
            return true;
        }
        Trace.e("FotaVerifyManager", sb.toString());
        return false;
    }

    public static boolean d(PackageManager packageManager, Context context) {
        String[] strArr;
        boolean z;
        StringBuilder sb = new StringBuilder();
        try {
            ServiceInfo[] serviceInfoArr = packageManager.getPackageInfo(context.getPackageName(), 4).services;
            if (Build.VERSION.SDK_INT >= 21) {
                strArr = d;
            } else {
                strArr = c;
            }
            for (String str : strArr) {
                if (serviceInfoArr != null) {
                    z = false;
                    for (ServiceInfo serviceInfo : serviceInfoArr) {
                        if (serviceInfo.name.equals(str)) {
                            z = true;
                        }
                    }
                } else {
                    z = false;
                }
                if (!z) {
                    if (sb.length() == 0) {
                        sb.append("please add Service in AndroidManifest:\n");
                    }
                    sb.append(str);
                    sb.append("\n");
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (sb.length() == 0) {
            return true;
        }
        Trace.e("FotaVerifyManager", sb.toString());
        return false;
    }

    public static boolean e(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return d(packageManager, context) && b(packageManager, context);
    }
}
