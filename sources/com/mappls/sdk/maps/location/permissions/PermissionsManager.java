package com.mappls.sdk.maps.location.permissions;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes11.dex */
public class PermissionsManager {

    /* renamed from: a  reason: collision with root package name */
    public PermissionsListener f12781a;

    public PermissionsManager(PermissionsListener permissionsListener) {
        this.f12781a = permissionsListener;
    }

    public static boolean a(Context context) {
        return c(context, "android.permission.ACCESS_COARSE_LOCATION");
    }

    public static boolean areLocationPermissionsGranted(Context context) {
        return a(context) || b(context);
    }

    public static boolean areRuntimePermissionsRequired() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean b(Context context) {
        return c(context, "android.permission.ACCESS_FINE_LOCATION");
    }

    public static boolean c(Context context, String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean isBackgroundLocationPermissionGranted(Context context) {
        if (Build.VERSION.SDK_INT >= 29) {
            return c(context, "android.permission.ACCESS_BACKGROUND_LOCATION");
        }
        return areLocationPermissionsGranted(context);
    }

    public final void d(Activity activity, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        if (z) {
            arrayList.add("android.permission.ACCESS_FINE_LOCATION");
        } else {
            arrayList.add("android.permission.ACCESS_COARSE_LOCATION");
        }
        if (Build.VERSION.SDK_INT >= 29 && z2) {
            arrayList.add("android.permission.ACCESS_BACKGROUND_LOCATION");
        }
        e(activity, (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    public final void e(Activity activity, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                arrayList.add(str);
            }
        }
        if (this.f12781a != null && arrayList.size() > 0) {
            this.f12781a.onExplanationNeeded(arrayList);
        }
        ActivityCompat.requestPermissions(activity, strArr, 0);
    }

    public PermissionsListener getListener() {
        return this.f12781a;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionsListener permissionsListener;
        if (i == 0 && (permissionsListener = this.f12781a) != null) {
            boolean z = false;
            if (iArr.length > 0 && iArr[0] == 0) {
                z = true;
            }
            permissionsListener.onPermissionResult(z);
        }
    }

    public void requestLocationPermissions(Activity activity) {
        try {
            String[] strArr = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096).requestedPermissions;
            if (strArr != null) {
                List asList = Arrays.asList(strArr);
                boolean contains = asList.contains("android.permission.ACCESS_FINE_LOCATION");
                boolean contains2 = asList.contains("android.permission.ACCESS_COARSE_LOCATION");
                boolean contains3 = asList.contains("android.permission.ACCESS_BACKGROUND_LOCATION");
                if (contains) {
                    d(activity, true, contains3);
                } else if (contains2) {
                    d(activity, false, contains3);
                } else {
                    Log.w("PermissionsManager", "Location permissions are missing");
                }
            }
        } catch (Exception e) {
            Log.w("PermissionsManager", e.getMessage());
        }
    }

    public void setListener(PermissionsListener permissionsListener) {
        this.f12781a = permissionsListener;
    }
}
