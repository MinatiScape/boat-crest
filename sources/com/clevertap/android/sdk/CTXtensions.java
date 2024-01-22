package com.clevertap.android.sdk;

import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.clevertap.android.sdk.events.EventGroup;
import com.clevertap.android.sdk.task.CTExecutorFactory;
import java.util.concurrent.Callable;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@JvmName(name = "CTXtensions")
/* loaded from: classes2.dex */
public final class CTXtensions {
    public static final boolean areAppNotificationsEnabled(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            return NotificationManagerCompat.from(context).areNotificationsEnabled();
        } catch (Exception e) {
            Logger.d("Unable to query notifications enabled flag, returning true!");
            e.printStackTrace();
            return true;
        }
    }

    public static final Void b(CleverTapAPI this_flushPushImpressionsOnPostAsyncSafely, Context context, String caller, String logTag) {
        Intrinsics.checkNotNullParameter(this_flushPushImpressionsOnPostAsyncSafely, "$this_flushPushImpressionsOnPostAsyncSafely");
        Intrinsics.checkNotNullParameter(context, "$context");
        Intrinsics.checkNotNullParameter(caller, "$caller");
        Intrinsics.checkNotNullParameter(logTag, "$logTag");
        try {
            this_flushPushImpressionsOnPostAsyncSafely.getCoreState().getBaseEventQueueManager().flushQueueSync(context, EventGroup.PUSH_NOTIFICATION_VIEWED, caller);
            return null;
        } catch (Exception unused) {
            Logger.d(logTag, "failed to flush push impressions on ct instance = " + this_flushPushImpressionsOnPostAsyncSafely.getCoreState().getConfig().getAccountId());
            return null;
        }
    }

    public static final void flushPushImpressionsOnPostAsyncSafely(@NotNull final CleverTapAPI cleverTapAPI, @NotNull final String logTag, @NotNull final String caller, @NotNull final Context context) {
        Intrinsics.checkNotNullParameter(cleverTapAPI, "<this>");
        Intrinsics.checkNotNullParameter(logTag, "logTag");
        Intrinsics.checkNotNullParameter(caller, "caller");
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            CTExecutorFactory.executors(cleverTapAPI.getCoreState().getConfig()).postAsyncSafelyTask().submit(logTag, new Callable() { // from class: com.clevertap.android.sdk.h
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    Void b;
                    b = CTXtensions.b(CleverTapAPI.this, context, caller, logTag);
                    return b;
                }
            }).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003c A[Catch: Exception -> 0x0019, TryCatch #0 {Exception -> 0x0019, blocks: (B:4:0x0010, B:12:0x001f, B:15:0x0026, B:17:0x0030, B:23:0x003c, B:27:0x0045, B:32:0x0050, B:34:0x005b, B:39:0x006a, B:33:0x0056), top: B:43:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0045 A[Catch: Exception -> 0x0019, TRY_LEAVE, TryCatch #0 {Exception -> 0x0019, blocks: (B:4:0x0010, B:12:0x001f, B:15:0x0026, B:17:0x0030, B:23:0x003c, B:27:0x0045, B:32:0x0050, B:34:0x005b, B:39:0x006a, B:33:0x0056), top: B:43:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0050 A[Catch: Exception -> 0x0019, TRY_ENTER, TryCatch #0 {Exception -> 0x0019, blocks: (B:4:0x0010, B:12:0x001f, B:15:0x0026, B:17:0x0030, B:23:0x003c, B:27:0x0045, B:32:0x0050, B:34:0x005b, B:39:0x006a, B:33:0x0056), top: B:43:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0056 A[Catch: Exception -> 0x0019, TryCatch #0 {Exception -> 0x0019, blocks: (B:4:0x0010, B:12:0x001f, B:15:0x0026, B:17:0x0030, B:23:0x003c, B:27:0x0045, B:32:0x0050, B:34:0x005b, B:39:0x006a, B:33:0x0056), top: B:43:0x0010 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.annotation.RequiresApi(26)
    @androidx.annotation.WorkerThread
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String getOrCreateChannel(@org.jetbrains.annotations.NotNull android.app.NotificationManager r4, @org.jetbrains.annotations.Nullable java.lang.String r5, @org.jetbrains.annotations.NotNull android.content.Context r6) {
        /*
            java.lang.String r0 = "fcm_fallback_notification_channel"
            java.lang.String r1 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            r1 = 0
            r2 = 1
            if (r5 == 0) goto L1c
            int r3 = r5.length()     // Catch: java.lang.Exception -> L19
            if (r3 != 0) goto L17
            goto L1c
        L17:
            r3 = r1
            goto L1d
        L19:
            r4 = move-exception
            goto L8d
        L1c:
            r3 = r2
        L1d:
            if (r3 != 0) goto L26
            android.app.NotificationChannel r3 = r4.getNotificationChannel(r5)     // Catch: java.lang.Exception -> L19
            if (r3 == 0) goto L26
            return r5
        L26:
            com.clevertap.android.sdk.ManifestInfo r5 = com.clevertap.android.sdk.ManifestInfo.getInstance(r6)     // Catch: java.lang.Exception -> L19
            java.lang.String r5 = r5.getDevDefaultPushChannelId()     // Catch: java.lang.Exception -> L19
            if (r5 == 0) goto L39
            int r3 = r5.length()     // Catch: java.lang.Exception -> L19
            if (r3 != 0) goto L37
            goto L39
        L37:
            r3 = r1
            goto L3a
        L39:
            r3 = r2
        L3a:
            if (r3 != 0) goto L43
            android.app.NotificationChannel r3 = r4.getNotificationChannel(r5)     // Catch: java.lang.Exception -> L19
            if (r3 == 0) goto L43
            return r5
        L43:
            if (r5 == 0) goto L4b
            int r5 = r5.length()     // Catch: java.lang.Exception -> L19
            if (r5 != 0) goto L4c
        L4b:
            r1 = r2
        L4c:
            java.lang.String r5 = "CleverTap"
            if (r1 == 0) goto L56
            java.lang.String r1 = "Missing Default CleverTap Notification Channel metadata in AndroidManifest."
            com.clevertap.android.sdk.Logger.d(r5, r1)     // Catch: java.lang.Exception -> L19
            goto L5b
        L56:
            java.lang.String r1 = "Notification Channel set in AndroidManifest.xml has not been created by the app."
            com.clevertap.android.sdk.Logger.d(r5, r1)     // Catch: java.lang.Exception -> L19
        L5b:
            android.app.NotificationChannel r1 = r4.getNotificationChannel(r0)     // Catch: java.lang.Exception -> L19
            if (r1 != 0) goto L8c
            int r1 = com.clevertap.android.sdk.R.string.fcm_fallback_notification_channel_label     // Catch: java.lang.Exception -> L68
            java.lang.String r6 = r6.getString(r1)     // Catch: java.lang.Exception -> L68
            goto L6a
        L68:
            java.lang.String r6 = "Misc"
        L6a:
            java.lang.String r1 = "try {\n                  …HANNEL_NAME\n            }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r1)     // Catch: java.lang.Exception -> L19
            android.app.NotificationChannel r1 = new android.app.NotificationChannel     // Catch: java.lang.Exception -> L19
            r2 = 3
            r1.<init>(r0, r6, r2)     // Catch: java.lang.Exception -> L19
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L19
            r6.<init>()     // Catch: java.lang.Exception -> L19
            java.lang.String r2 = "created default channel: "
            r6.append(r2)     // Catch: java.lang.Exception -> L19
            r6.append(r1)     // Catch: java.lang.Exception -> L19
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> L19
            com.clevertap.android.sdk.Logger.d(r5, r6)     // Catch: java.lang.Exception -> L19
            r4.createNotificationChannel(r1)     // Catch: java.lang.Exception -> L19
        L8c:
            return r0
        L8d:
            r4.printStackTrace()
            r4 = 0
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CTXtensions.getOrCreateChannel(android.app.NotificationManager, java.lang.String, android.content.Context):java.lang.String");
    }

    public static final int getTargetSdkVersion(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getApplicationContext().getApplicationInfo().targetSdkVersion;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean isNotificationChannelEnabled(@org.jetbrains.annotations.NotNull android.content.Context r4, @org.jetbrains.annotations.NotNull java.lang.String r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "channelId"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 1
            r2 = 0
            r3 = 26
            if (r0 < r3) goto L4b
            boolean r0 = areAppNotificationsEnabled(r4)
            if (r0 == 0) goto L49
            java.lang.String r0 = "notification"
            java.lang.Object r4 = r4.getSystemService(r0)     // Catch: java.lang.Exception -> L31
            java.lang.String r0 = "null cannot be cast to non-null type android.app.NotificationManager"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r0)     // Catch: java.lang.Exception -> L31
            android.app.NotificationManager r4 = (android.app.NotificationManager) r4     // Catch: java.lang.Exception -> L31
            android.app.NotificationChannel r4 = r4.getNotificationChannel(r5)     // Catch: java.lang.Exception -> L31
            int r4 = r4.getImportance()     // Catch: java.lang.Exception -> L31
            if (r4 == 0) goto L45
            r4 = r1
            goto L46
        L31:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r0 = "Unable to find notification channel with id = "
            r4.append(r0)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.clevertap.android.sdk.Logger.d(r4)
        L45:
            r4 = r2
        L46:
            if (r4 == 0) goto L49
            goto L4f
        L49:
            r1 = r2
            goto L4f
        L4b:
            boolean r1 = areAppNotificationsEnabled(r4)
        L4f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clevertap.android.sdk.CTXtensions.isNotificationChannelEnabled(android.content.Context, java.lang.String):boolean");
    }

    public static final boolean isPackageAndOsTargetsAbove(@NotNull Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return Build.VERSION.SDK_INT > i && getTargetSdkVersion(context) > i;
    }
}
