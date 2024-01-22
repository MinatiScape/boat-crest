package com.coveiot.utils.permissions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class PermissionUtils {

    /* loaded from: classes9.dex */
    public interface PermissionAskListener {
        void onPermissionAsk();

        void onPermissionDisabled();

        void onPermissionGranted();

        void onPermissionPreviouslyDenied();
    }

    public static boolean a(Context context, String str) {
        return shouldAskPermission() && ContextCompat.checkSelfPermission(context, str) != 0;
    }

    @SuppressLint({"NewApi"})
    public static void checkPermission(Context context, String str, PermissionAskListener permissionAskListener) {
        if (a(context, str)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, str)) {
                permissionAskListener.onPermissionPreviouslyDenied();
                return;
            } else {
                permissionAskListener.onPermissionAsk();
                return;
            }
        }
        permissionAskListener.onPermissionGranted();
    }

    public static String[] checkPermissionsHasGranted(Context context, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            if (a(context, strArr[i])) {
                arrayList.add(strArr[i]);
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static boolean shouldAskPermission() {
        return Build.VERSION.SDK_INT >= 23;
    }
}
