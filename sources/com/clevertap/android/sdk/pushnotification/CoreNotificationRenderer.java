package com.clevertap.android.sdk.pushnotification;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.CleverTapInstanceConfig;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.interfaces.AudibleNotification;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import com.clevertap.android.sdk.network.DownloadedBitmapFactory;
import org.json.JSONArray;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public class CoreNotificationRenderer implements INotificationRenderer, AudibleNotification {

    /* renamed from: a  reason: collision with root package name */
    public String f2659a;
    public String b;
    public int c;

    @Override // com.clevertap.android.sdk.pushnotification.INotificationRenderer
    public String getActionButtonIconKey() {
        return Constants.NOTIF_ICON;
    }

    @Override // com.clevertap.android.sdk.pushnotification.INotificationRenderer
    @Nullable
    public Object getCollapseKey(Bundle bundle) {
        return bundle.get(Constants.WZRK_COLLAPSE);
    }

    @Override // com.clevertap.android.sdk.pushnotification.INotificationRenderer
    public String getMessage(Bundle bundle) {
        String string = bundle.getString(Constants.NOTIF_MSG);
        this.f2659a = string;
        return string;
    }

    @Override // com.clevertap.android.sdk.pushnotification.INotificationRenderer
    public String getTitle(Bundle bundle, Context context) {
        String string = bundle.getString(Constants.NOTIF_TITLE, "");
        if (string.isEmpty()) {
            string = context.getApplicationInfo().name;
        }
        this.b = string;
        return string;
    }

    @Override // com.clevertap.android.sdk.pushnotification.INotificationRenderer
    @SuppressLint({"NotificationTrampoline"})
    public NotificationCompat.Builder renderNotification(Bundle bundle, Context context, NotificationCompat.Builder builder, CleverTapInstanceConfig cleverTapInstanceConfig, int i) {
        NotificationCompat.Style bigText;
        JSONArray jSONArray;
        String string = bundle.getString(Constants.NOTIF_ICON);
        String string2 = bundle.getString(Constants.WZRK_BIG_PICTURE);
        if (string2 != null && string2.startsWith("http")) {
            DownloadedBitmap nullBitmapWithStatus = DownloadedBitmapFactory.INSTANCE.nullBitmapWithStatus(DownloadedBitmap.Status.INIT_ERROR);
            try {
                DownloadedBitmap notificationBitmapWithTimeout = Utils.getNotificationBitmapWithTimeout(string2, false, context, cleverTapInstanceConfig, 5000L);
                Bitmap bitmap = notificationBitmapWithTimeout.getBitmap();
                if (bitmap != null) {
                    long downloadTime = notificationBitmapWithTimeout.getDownloadTime();
                    cleverTapInstanceConfig.getLogger().verbose("Fetched big picture in " + downloadTime + " millis");
                    bundle.putString(Constants.WZRK_BPDS, notificationBitmapWithTimeout.getStatus().getStatusValue());
                    if (bundle.containsKey(Constants.WZRK_MSG_SUMMARY)) {
                        bigText = new NotificationCompat.BigPictureStyle().setSummaryText(bundle.getString(Constants.WZRK_MSG_SUMMARY)).bigPicture(bitmap);
                    } else {
                        bigText = new NotificationCompat.BigPictureStyle().setSummaryText(this.f2659a).bigPicture(bitmap);
                    }
                } else {
                    throw new Exception("Failed to fetch big picture!");
                }
            } catch (Throwable th) {
                NotificationCompat.BigTextStyle bigText2 = new NotificationCompat.BigTextStyle().bigText(this.f2659a);
                bundle.putString(Constants.WZRK_BPDS, nullBitmapWithStatus.getStatus().getStatusValue());
                cleverTapInstanceConfig.getLogger().verbose(cleverTapInstanceConfig.getAccountId(), "Falling back to big text notification, couldn't fetch big picture", th);
                bigText = bigText2;
            }
        } else {
            bigText = new NotificationCompat.BigTextStyle().bigText(this.f2659a);
            bundle.putString(Constants.WZRK_BPDS, DownloadedBitmap.Status.NO_IMAGE.getStatusValue());
        }
        if ((Build.VERSION.SDK_INT >= 26) && bundle.containsKey(Constants.WZRK_SUBTITLE)) {
            builder.setSubText(bundle.getString(Constants.WZRK_SUBTITLE));
        }
        if (bundle.containsKey(Constants.WZRK_COLOR)) {
            builder.setColor(Color.parseColor(bundle.getString(Constants.WZRK_COLOR)));
            builder.setColorized(true);
        }
        builder.setContentTitle(this.b).setContentText(this.f2659a).setContentIntent(LaunchPendingIntentFactory.getLaunchPendingIntent(bundle, context)).setAutoCancel(true).setStyle(bigText).setSmallIcon(this.c);
        builder.setLargeIcon(Utils.getNotificationBitmapWithTimeout(string, true, context, cleverTapInstanceConfig, Constants.PN_LARGE_ICON_DOWNLOAD_TIMEOUT_IN_MILLIS).getBitmap());
        String string3 = bundle.getString(Constants.WZRK_ACTIONS);
        if (string3 != null) {
            try {
                jSONArray = new JSONArray(string3);
            } catch (Throwable th2) {
                cleverTapInstanceConfig.getLogger().debug(cleverTapInstanceConfig.getAccountId(), "error parsing notification actions: " + th2.getLocalizedMessage());
            }
            setActionButtons(context, bundle, i, builder, jSONArray);
            return builder;
        }
        jSONArray = null;
        setActionButtons(context, bundle, i, builder, jSONArray);
        return builder;
    }

    @Override // com.clevertap.android.sdk.pushnotification.INotificationRenderer
    public void setSmallIcon(int i, Context context) {
        this.c = i;
    }

    @Override // com.clevertap.android.sdk.interfaces.AudibleNotification
    public NotificationCompat.Builder setSound(Context context, Bundle bundle, NotificationCompat.Builder builder, CleverTapInstanceConfig cleverTapInstanceConfig) {
        try {
            if (bundle.containsKey(Constants.WZRK_SOUND)) {
                Uri uri = null;
                Object obj = bundle.get(Constants.WZRK_SOUND);
                if ((obj instanceof Boolean) && ((Boolean) obj).booleanValue()) {
                    uri = RingtoneManager.getDefaultUri(2);
                } else if (obj instanceof String) {
                    String str = (String) obj;
                    if (str.equals("true")) {
                        uri = RingtoneManager.getDefaultUri(2);
                    } else if (!str.isEmpty()) {
                        if (str.contains(".mp3") || str.contains(".ogg") || str.contains(".wav")) {
                            str = str.substring(0, str.length() - 4);
                        }
                        uri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + str);
                    }
                }
                if (uri != null) {
                    builder.setSound(uri);
                }
            }
        } catch (Throwable th) {
            cleverTapInstanceConfig.getLogger().debug(cleverTapInstanceConfig.getAccountId(), "Could not process sound parameter", th);
        }
        return builder;
    }
}
