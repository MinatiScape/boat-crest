package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.Constants;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import java.util.concurrent.ExecutionException;
/* loaded from: classes10.dex */
public class MessagingAnalytics {
    public static boolean a() {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            FirebaseApp.getInstance();
            Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("export_to_big_query")) {
                return sharedPreferences.getBoolean("export_to_big_query", false);
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager != null && (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) != null && (bundle = applicationInfo.metaData) != null && bundle.containsKey("delivery_metrics_exported_to_big_query_enabled")) {
                    return applicationInfo.metaData.getBoolean("delivery_metrics_exported_to_big_query_enabled", false);
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            return false;
        } catch (IllegalStateException unused2) {
            Log.i(Constants.TAG, "FirebaseApp has not being initialized. Device might be in direct boot mode. Skip exporting delivery metrics to Big Query");
            return false;
        }
    }

    public static MessagingClientEvent b(MessagingClientEvent.Event event, Intent intent) {
        if (intent == null) {
            return null;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            extras = Bundle.EMPTY;
        }
        MessagingClientEvent.Builder newBuilder = MessagingClientEvent.newBuilder();
        newBuilder.setTtl(p(extras));
        newBuilder.setEvent(event);
        newBuilder.setInstanceId(f(extras));
        newBuilder.setPackageName(m());
        newBuilder.setSdkPlatform(MessagingClientEvent.SDKPlatform.ANDROID);
        newBuilder.setMessageType(k(extras));
        String h = h(extras);
        if (h != null) {
            newBuilder.setMessageId(h);
        }
        String o = o(extras);
        if (o != null) {
            newBuilder.setTopic(o);
        }
        String c = c(extras);
        if (c != null) {
            newBuilder.setCollapseKey(c);
        }
        String i = i(extras);
        if (i != null) {
            newBuilder.setAnalyticsLabel(i);
        }
        String e = e(extras);
        if (e != null) {
            newBuilder.setComposerLabel(e);
        }
        long n = n(extras);
        if (n > 0) {
            newBuilder.setProjectNumber(n);
        }
        return newBuilder.build();
    }

    @Nullable
    public static String c(Bundle bundle) {
        return bundle.getString(Constants.MessagePayloadKeys.COLLAPSE_KEY);
    }

    @Nullable
    public static String d(Bundle bundle) {
        return bundle.getString(Constants.AnalyticsKeys.COMPOSER_ID);
    }

    @Nullable
    public static String e(Bundle bundle) {
        return bundle.getString(Constants.AnalyticsKeys.COMPOSER_LABEL);
    }

    @NonNull
    public static String f(Bundle bundle) {
        String string = bundle.getString(Constants.MessagePayloadKeys.TO);
        if (TextUtils.isEmpty(string)) {
            try {
                return (String) Tasks.await(FirebaseInstallations.getInstance(FirebaseApp.getInstance()).getId());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return string;
    }

    @Nullable
    public static String g(Bundle bundle) {
        return bundle.getString(Constants.AnalyticsKeys.MESSAGE_CHANNEL);
    }

    @Nullable
    public static String h(Bundle bundle) {
        String string = bundle.getString(Constants.MessagePayloadKeys.MSGID);
        return string == null ? bundle.getString(Constants.MessagePayloadKeys.MSGID_SERVER) : string;
    }

    @Nullable
    public static String i(Bundle bundle) {
        return bundle.getString(Constants.AnalyticsKeys.MESSAGE_LABEL);
    }

    @Nullable
    public static String j(Bundle bundle) {
        return bundle.getString(Constants.AnalyticsKeys.MESSAGE_TIMESTAMP);
    }

    @NonNull
    public static MessagingClientEvent.MessageType k(Bundle bundle) {
        if (bundle != null && NotificationParams.isNotification(bundle)) {
            return MessagingClientEvent.MessageType.DISPLAY_NOTIFICATION;
        }
        return MessagingClientEvent.MessageType.DATA_MESSAGE;
    }

    @NonNull
    public static String l(Bundle bundle) {
        return true != NotificationParams.isNotification(bundle) ? "data" : Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION;
    }

    public static void logNotificationDismiss(@NonNull Intent intent) {
        t(Constants.ScionAnalytics.EVENT_NOTIFICATION_DISMISS, intent.getExtras());
    }

    public static void logNotificationForeground(@NonNull Intent intent) {
        t(Constants.ScionAnalytics.EVENT_NOTIFICATION_FOREGROUND, intent.getExtras());
    }

    public static void logNotificationOpen(@NonNull Bundle bundle) {
        v(bundle);
        t(Constants.ScionAnalytics.EVENT_NOTIFICATION_OPEN, bundle);
    }

    public static void logNotificationReceived(@NonNull Intent intent) {
        if (shouldUploadScionMetrics(intent)) {
            t(Constants.ScionAnalytics.EVENT_NOTIFICATION_RECEIVE, intent.getExtras());
        }
        if (shouldUploadFirelogAnalytics(intent)) {
            s(MessagingClientEvent.Event.MESSAGE_DELIVERED, intent, FirebaseMessaging.getTransportFactory());
        }
    }

    @NonNull
    public static String m() {
        return FirebaseApp.getInstance().getApplicationContext().getPackageName();
    }

    @Nullable
    public static long n(Bundle bundle) {
        if (bundle.containsKey(Constants.MessagePayloadKeys.SENDER_ID)) {
            try {
                return Long.parseLong(bundle.getString(Constants.MessagePayloadKeys.SENDER_ID));
            } catch (NumberFormatException e) {
                Log.w(Constants.TAG, "error parsing project number", e);
            }
        }
        FirebaseApp firebaseApp = FirebaseApp.getInstance();
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            try {
                return Long.parseLong(gcmSenderId);
            } catch (NumberFormatException e2) {
                Log.w(Constants.TAG, "error parsing sender ID", e2);
            }
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (!applicationId.startsWith("1:")) {
            try {
                return Long.parseLong(applicationId);
            } catch (NumberFormatException e3) {
                Log.w(Constants.TAG, "error parsing app ID", e3);
            }
        } else {
            String[] split = applicationId.split(":");
            if (split.length < 2) {
                return 0L;
            }
            String str = split[1];
            if (str.isEmpty()) {
                return 0L;
            }
            try {
                return Long.parseLong(str);
            } catch (NumberFormatException e4) {
                Log.w(Constants.TAG, "error parsing app ID", e4);
            }
        }
        return 0L;
    }

    @Nullable
    public static String o(Bundle bundle) {
        String string = bundle.getString("from");
        if (string == null || !string.startsWith("/topics/")) {
            return null;
        }
        return string;
    }

    @NonNull
    public static int p(Bundle bundle) {
        Object obj = bundle.get(Constants.MessagePayloadKeys.TTL);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException unused) {
                String valueOf = String.valueOf(obj);
                StringBuilder sb = new StringBuilder(valueOf.length() + 13);
                sb.append("Invalid TTL: ");
                sb.append(valueOf);
                Log.w(Constants.TAG, sb.toString());
                return 0;
            }
        }
        return 0;
    }

    @Nullable
    public static String q(Bundle bundle) {
        if (bundle.containsKey(Constants.AnalyticsKeys.MESSAGE_USE_DEVICE_TIME)) {
            return bundle.getString(Constants.AnalyticsKeys.MESSAGE_USE_DEVICE_TIME);
        }
        return null;
    }

    public static boolean r(Intent intent) {
        return FirebaseMessagingService.ACTION_DIRECT_BOOT_REMOTE_INTENT.equals(intent.getAction());
    }

    public static void s(MessagingClientEvent.Event event, Intent intent, @Nullable TransportFactory transportFactory) {
        if (transportFactory == null) {
            Log.e(Constants.TAG, "TransportFactory is null. Skip exporting message delivery metrics to Big Query");
            return;
        }
        MessagingClientEvent b = b(event, intent);
        if (b == null) {
            return;
        }
        try {
            Transport transport = transportFactory.getTransport(Constants.FirelogAnalytics.FCM_LOG_SOURCE, MessagingClientEventExtension.class, Encoding.of("proto"), g0.f11342a);
            MessagingClientEventExtension.Builder newBuilder = MessagingClientEventExtension.newBuilder();
            newBuilder.setMessagingClientEvent(b);
            transport.send(Event.ofTelemetry(newBuilder.build()));
        } catch (RuntimeException e) {
            Log.w(Constants.TAG, "Failed to send big query analytics payload.", e);
        }
    }

    public static boolean shouldUploadFirelogAnalytics(@NonNull Intent intent) {
        if (intent == null || r(intent)) {
            return false;
        }
        return a();
    }

    public static boolean shouldUploadScionMetrics(@NonNull Intent intent) {
        if (intent == null || r(intent)) {
            return false;
        }
        return shouldUploadScionMetrics(intent.getExtras());
    }

    @VisibleForTesting
    public static void t(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = new Bundle();
        String d = d(bundle);
        if (d != null) {
            bundle2.putString("_nmid", d);
        }
        String e = e(bundle);
        if (e != null) {
            bundle2.putString(Constants.ScionAnalytics.PARAM_MESSAGE_NAME, e);
        }
        String i = i(bundle);
        if (!TextUtils.isEmpty(i)) {
            bundle2.putString(Constants.ScionAnalytics.PARAM_LABEL, i);
        }
        String g = g(bundle);
        if (!TextUtils.isEmpty(g)) {
            bundle2.putString(Constants.ScionAnalytics.PARAM_MESSAGE_CHANNEL, g);
        }
        String o = o(bundle);
        if (o != null) {
            bundle2.putString(Constants.ScionAnalytics.PARAM_TOPIC, o);
        }
        String j = j(bundle);
        if (j != null) {
            try {
                bundle2.putInt(Constants.ScionAnalytics.PARAM_MESSAGE_TIME, Integer.parseInt(j));
            } catch (NumberFormatException e2) {
                Log.w(Constants.TAG, "Error while parsing timestamp in GCM event", e2);
            }
        }
        String q = q(bundle);
        if (q != null) {
            try {
                bundle2.putInt(Constants.ScionAnalytics.PARAM_MESSAGE_DEVICE_TIME, Integer.parseInt(q));
            } catch (NumberFormatException e3) {
                Log.w(Constants.TAG, "Error while parsing use_device_time in GCM event", e3);
            }
        }
        String l = l(bundle);
        if (Constants.ScionAnalytics.EVENT_NOTIFICATION_RECEIVE.equals(str) || Constants.ScionAnalytics.EVENT_NOTIFICATION_FOREGROUND.equals(str)) {
            bundle2.putString(Constants.ScionAnalytics.PARAM_MESSAGE_TYPE, l);
        }
        if (Log.isLoggable(Constants.TAG, 3)) {
            String valueOf = String.valueOf(bundle2);
            StringBuilder sb = new StringBuilder(str.length() + 37 + valueOf.length());
            sb.append("Logging to scion event=");
            sb.append(str);
            sb.append(" scionPayload=");
            sb.append(valueOf);
            Log.d(Constants.TAG, sb.toString());
        }
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if (analyticsConnector != null) {
            analyticsConnector.logEvent("fcm", str, bundle2);
        } else {
            Log.w(Constants.TAG, "Unable to log event: analytics library is missing");
        }
    }

    public static void u(boolean z) {
        FirebaseApp.getInstance().getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit().putBoolean("export_to_big_query", z).apply();
    }

    public static void v(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if ("1".equals(bundle.getString(Constants.AnalyticsKeys.TRACK_CONVERSIONS))) {
            AnalyticsConnector analyticsConnector = (AnalyticsConnector) FirebaseApp.getInstance().get(AnalyticsConnector.class);
            if (Log.isLoggable(Constants.TAG, 3)) {
                Log.d(Constants.TAG, "Received event with track-conversion=true. Setting user property and reengagement event");
            }
            if (analyticsConnector != null) {
                String string = bundle.getString(Constants.AnalyticsKeys.COMPOSER_ID);
                analyticsConnector.setUserProperty("fcm", Constants.ScionAnalytics.USER_PROPERTY_FIREBASE_LAST_NOTIFICATION, string);
                Bundle bundle2 = new Bundle();
                bundle2.putString("source", "Firebase");
                bundle2.putString("medium", "notification");
                bundle2.putString("campaign", string);
                analyticsConnector.logEvent("fcm", Constants.ScionAnalytics.EVENT_FIREBASE_CAMPAIGN, bundle2);
                return;
            }
            Log.w(Constants.TAG, "Unable to set user property for conversion tracking:  analytics library is missing");
        } else if (Log.isLoggable(Constants.TAG, 3)) {
            Log.d(Constants.TAG, "Received event with track-conversion=false. Do not set user property");
        }
    }

    public static boolean shouldUploadScionMetrics(@NonNull Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return "1".equals(bundle.getString(Constants.AnalyticsKeys.ENABLED));
    }
}
