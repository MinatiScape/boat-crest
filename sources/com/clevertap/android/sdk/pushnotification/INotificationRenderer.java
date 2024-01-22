package com.clevertap.android.sdk.pushnotification;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public interface INotificationRenderer {
    String getActionButtonIconKey();

    @Nullable
    Object getCollapseKey(Bundle bundle);

    @Nullable
    String getMessage(Bundle bundle);

    @Nullable
    String getTitle(Bundle bundle, Context context);

    @Nullable
    NotificationCompat.Builder renderNotification(Bundle bundle, Context context, NotificationCompat.Builder builder, CleverTapInstanceConfig cleverTapInstanceConfig, int i);

    default NotificationCompat.Builder setActionButtons(Context context, Bundle bundle, int i, NotificationCompat.Builder builder, JSONArray jSONArray) {
        String optString;
        String optString2;
        String optString3;
        String optString4;
        boolean optBoolean;
        Intent launchIntentForPackage;
        PendingIntent activity;
        JSONArray jSONArray2 = jSONArray;
        String intentServiceName = ManifestInfo.getInstance(context).getIntentServiceName();
        Class<?> cls = null;
        if (intentServiceName != null) {
            try {
                try {
                    cls = Class.forName(intentServiceName);
                } catch (ClassNotFoundException unused) {
                    Logger.d("No Intent Service found");
                }
            } catch (ClassNotFoundException unused2) {
                cls = Class.forName("com.clevertap.android.sdk.pushnotification.CTNotificationIntentService");
            }
        } else {
            try {
                cls = Class.forName("com.clevertap.android.sdk.pushnotification.CTNotificationIntentService");
            } catch (ClassNotFoundException unused3) {
                Logger.d("No Intent Service found");
            }
        }
        boolean isServiceAvailable = Utils.isServiceAvailable(context, cls);
        if (jSONArray2 != null && jSONArray.length() > 0) {
            int i2 = 0;
            while (i2 < jSONArray.length()) {
                try {
                    JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                    optString = jSONObject.optString("l");
                    optString2 = jSONObject.optString("dl");
                    optString3 = jSONObject.optString(getActionButtonIconKey());
                    optString4 = jSONObject.optString("id");
                    optBoolean = jSONObject.optBoolean("ac", true);
                } catch (Throwable th) {
                    th = th;
                }
                if (!optString.isEmpty() && !optString4.isEmpty()) {
                    int identifier = !optString3.isEmpty() ? context.getResources().getIdentifier(optString3, "drawable", context.getPackageName()) : 0;
                    int i3 = Build.VERSION.SDK_INT;
                    boolean z = i3 < 31 && optBoolean && isServiceAvailable;
                    String string = bundle.getString("pt_dismiss_on_click");
                    if (!z && PushNotificationHandler.isForPushTemplates(bundle) && optString4.contains("remind") && string != null && string.equalsIgnoreCase("true") && optBoolean && isServiceAvailable) {
                        z = true;
                    }
                    boolean z2 = (!z && PushNotificationHandler.isForPushTemplates(bundle) && string != null && string.equalsIgnoreCase("true") && optBoolean && isServiceAvailable) ? true : z;
                    if (z2) {
                        launchIntentForPackage = new Intent(CTNotificationIntentService.MAIN_ACTION);
                        launchIntentForPackage.setPackage(context.getPackageName());
                        launchIntentForPackage.putExtra(Constants.KEY_CT_TYPE, CTNotificationIntentService.TYPE_BUTTON_CLICK);
                        if (!optString2.isEmpty()) {
                            launchIntentForPackage.putExtra("dl", optString2);
                        }
                    } else if (!optString2.isEmpty()) {
                        launchIntentForPackage = new Intent("android.intent.action.VIEW", Uri.parse(optString2));
                        Utils.setPackageNameFromResolveInfoList(context, launchIntentForPackage);
                    } else {
                        launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                    }
                    if (launchIntentForPackage != null) {
                        launchIntentForPackage.putExtras(bundle);
                        launchIntentForPackage.removeExtra(Constants.WZRK_ACTIONS);
                        launchIntentForPackage.putExtra("actionId", optString4);
                        launchIntentForPackage.putExtra("autoCancel", optBoolean);
                        launchIntentForPackage.putExtra(Constants.KEY_C2A, optString4);
                        try {
                            launchIntentForPackage.putExtra(Constants.PT_NOTIF_ID, i);
                            launchIntentForPackage.setFlags(603979776);
                        } catch (Throwable th2) {
                            th = th2;
                            Logger.d("error adding notification action : " + th.getLocalizedMessage());
                            i2++;
                            jSONArray2 = jSONArray;
                        }
                    }
                    int currentTimeMillis = ((int) System.currentTimeMillis()) + i2;
                    int i4 = i3 >= 23 ? 201326592 : 134217728;
                    if (z2) {
                        activity = PendingIntent.getService(context, currentTimeMillis, launchIntentForPackage, i4);
                    } else {
                        activity = PendingIntent.getActivity(context, currentTimeMillis, launchIntentForPackage, i4);
                    }
                    builder.addAction(identifier, optString, activity);
                    i2++;
                    jSONArray2 = jSONArray;
                }
                Logger.d("not adding push notification action: action label or id missing");
                i2++;
                jSONArray2 = jSONArray;
            }
        }
        return builder;
    }

    void setSmallIcon(int i, Context context);
}
