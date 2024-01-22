package com.clevertap.android.sdk.pushnotification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Utils;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class LaunchPendingIntentFactory {
    public static PendingIntent getActivityIntent(@NonNull Bundle bundle, @NonNull Context context) {
        Intent launchIntentForPackage;
        if (bundle.containsKey(Constants.DEEP_LINK_KEY) && bundle.getString(Constants.DEEP_LINK_KEY) != null) {
            launchIntentForPackage = new Intent("android.intent.action.VIEW", Uri.parse(bundle.getString(Constants.DEEP_LINK_KEY)));
            Utils.setPackageNameFromResolveInfoList(context, launchIntentForPackage);
        } else {
            launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            if (launchIntentForPackage == null) {
                return null;
            }
        }
        launchIntentForPackage.setFlags(872415232);
        launchIntentForPackage.putExtras(bundle);
        launchIntentForPackage.removeExtra(Constants.WZRK_ACTIONS);
        return PendingIntent.getActivity(context, (int) System.currentTimeMillis(), launchIntentForPackage, Build.VERSION.SDK_INT >= 23 ? 201326592 : 134217728);
    }

    public static PendingIntent getLaunchPendingIntent(@NonNull Bundle bundle, @NonNull Context context) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 31) {
            return getActivityIntent(bundle, context);
        }
        Intent intent = new Intent(context, CTPushNotificationReceiver.class);
        intent.putExtras(bundle);
        intent.removeExtra(Constants.WZRK_ACTIONS);
        return PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, i >= 23 ? 201326592 : 134217728);
    }
}
