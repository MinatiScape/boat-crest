package com.clevertap.android.sdk.validation;

import android.app.Application;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.text.TextUtils;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.DeviceInfo;
import com.clevertap.android.sdk.InAppNotificationActivity;
import com.clevertap.android.sdk.Logger;
import com.clevertap.android.sdk.ManifestInfo;
import com.clevertap.android.sdk.Utils;
import com.clevertap.android.sdk.inbox.CTInboxActivity;
import com.clevertap.android.sdk.pushnotification.CTNotificationIntentService;
import com.clevertap.android.sdk.pushnotification.CTPushNotificationReceiver;
import com.clevertap.android.sdk.pushnotification.PushConstants;
import com.clevertap.android.sdk.pushnotification.PushProviders;
import com.clevertap.android.sdk.pushnotification.amp.CTBackgroundIntentService;
import com.clevertap.android.sdk.pushnotification.amp.CTBackgroundJobService;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes2.dex */
public final class ManifestValidator {
    public static void a(Context context) {
        String str = context.getApplicationInfo().className;
        if (str != null && !str.isEmpty()) {
            if (str.equals("com.clevertap.android.sdk.Application")) {
                Logger.i("AndroidManifest.xml uses the CleverTap Application class, be sure you have properly added the CleverTap Account ID and Token to your AndroidManifest.xml, \nor set them programmatically in the onCreate method of your custom application class prior to calling super.onCreate()");
                return;
            }
            Logger.i("Application Class is " + str);
            return;
        }
        Logger.i("Unable to determine Application Class");
    }

    public static void b(Context context, PushProviders pushProviders) {
        try {
            e((Application) context.getApplicationContext(), CTPushNotificationReceiver.class.getName());
            f((Application) context.getApplicationContext(), CTNotificationIntentService.class.getName());
            d((Application) context.getApplicationContext(), InAppNotificationActivity.class);
            d((Application) context.getApplicationContext(), CTInboxActivity.class);
            e((Application) context.getApplicationContext(), "com.clevertap.android.geofence.CTGeofenceReceiver");
            e((Application) context.getApplicationContext(), "com.clevertap.android.geofence.CTLocationUpdateReceiver");
            e((Application) context.getApplicationContext(), "com.clevertap.android.geofence.CTGeofenceBootReceiver");
            if (Build.VERSION.SDK_INT >= 21) {
                f((Application) context.getApplicationContext(), CTBackgroundJobService.class.getName());
            }
            f((Application) context.getApplicationContext(), CTBackgroundIntentService.class.getName());
        } catch (Exception e) {
            Logger.v("Receiver/Service issue : " + e.toString());
        }
        ArrayList<PushConstants.PushType> availablePushTypes = pushProviders.getAvailablePushTypes();
        if (availablePushTypes == null) {
            return;
        }
        Iterator<PushConstants.PushType> it = availablePushTypes.iterator();
        while (it.hasNext()) {
            PushConstants.PushType next = it.next();
            if (next == PushConstants.PushType.FCM) {
                try {
                    f((Application) context.getApplicationContext(), "com.clevertap.android.sdk.pushnotification.fcm.FcmMessageListenerService");
                } catch (Error e2) {
                    Logger.v("FATAL : " + e2.getMessage());
                } catch (Exception e3) {
                    Logger.v("Receiver/Service issue : " + e3.toString());
                }
            } else if (next == PushConstants.PushType.HPS) {
                try {
                    f((Application) context.getApplicationContext(), "com.clevertap.android.hms.CTHmsMessageService");
                } catch (Error e4) {
                    Logger.v("FATAL : " + e4.getMessage());
                } catch (Exception e5) {
                    Logger.v("Receiver/Service issue : " + e5.toString());
                }
            } else if (next == PushConstants.PushType.XPS) {
                try {
                    e((Application) context.getApplicationContext(), "com.clevertap.android.xps.XiaomiMessageReceiver");
                } catch (Error e6) {
                    Logger.v("FATAL : " + e6.getMessage());
                } catch (Exception e7) {
                    Logger.v("Receiver/Service issue : " + e7.toString());
                }
            }
        }
    }

    public static void c(DeviceInfo deviceInfo) {
        Logger.i("SDK Version Code is " + deviceInfo.getSdkVersion());
    }

    public static void d(Application application, Class cls) throws PackageManager.NameNotFoundException {
        ActivityInfo[] activityInfoArr = application.getPackageManager().getPackageInfo(application.getPackageName(), 1).activities;
        String name = cls.getName();
        for (ActivityInfo activityInfo : activityInfoArr) {
            if (activityInfo.name.equals(name)) {
                Logger.i(name.replaceFirst("com.clevertap.android.sdk.", "") + " is present");
                return;
            }
        }
        Logger.i(name.replaceFirst("com.clevertap.android.sdk.", "") + " not present");
    }

    public static void e(Application application, String str) throws PackageManager.NameNotFoundException {
        for (ActivityInfo activityInfo : application.getPackageManager().getPackageInfo(application.getPackageName(), 2).receivers) {
            if (activityInfo.name.equals(str)) {
                Logger.i(str.replaceFirst("com.clevertap.android.", "") + " is present");
                return;
            }
        }
        Logger.i(str.replaceFirst("com.clevertap.android.", "") + " not present");
    }

    public static void f(Application application, String str) throws PackageManager.NameNotFoundException {
        for (ServiceInfo serviceInfo : application.getPackageManager().getPackageInfo(application.getPackageName(), 4).services) {
            if (serviceInfo.name.equals(str)) {
                Logger.i(str.replaceFirst("com.clevertap.android.sdk.", "") + " is present");
                return;
            }
        }
        Logger.i(str.replaceFirst("com.clevertap.android.sdk.", "") + " not present");
    }

    public static void g(Context context) {
        if (ActivityLifecycleCallback.registered || CleverTapAPI.isAppForeground()) {
            return;
        }
        Logger.i("Activity Lifecycle Callback not registered. Either set the android:name in your AndroidManifest.xml application tag to com.clevertap.android.sdk.Application, \n or, if you have a custom Application class, call ActivityLifecycleCallback.register(this); before super.onCreate() in your class");
        a(context);
    }

    public static void validate(Context context, DeviceInfo deviceInfo, PushProviders pushProviders) {
        if (!Utils.hasPermission(context, "android.permission.INTERNET")) {
            Logger.d("Missing Permission: android.permission.INTERNET");
        }
        c(deviceInfo);
        g(context);
        b(context, pushProviders);
        if (TextUtils.isEmpty(ManifestInfo.getInstance(context).getFCMSenderId())) {
            return;
        }
        Logger.i("We have noticed that your app is using a custom FCM Sender ID, this feature will be DISCONTINUED from the next version of the CleverTap Android SDK. With the next release, CleverTap Android SDK will only fetch the token using the google-services.json. Please reach out to CleverTap Support for any questions.");
    }
}
