package com.google.android.gms.gcm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.messaging.Constants;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes6.dex */
public final class a {
    public static a d;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8480a;
    public String b;
    public final AtomicInteger c = new AtomicInteger((int) SystemClock.elapsedRealtime());

    public a(Context context) {
        this.f8480a = context.getApplicationContext();
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new a(context);
            }
            aVar = d;
        }
        return aVar;
    }

    public static String b(Bundle bundle, String str) {
        String string = bundle.getString(str);
        return string == null ? bundle.getString(str.replace(Constants.MessageNotificationKeys.NOTIFICATION_PREFIX, Constants.MessageNotificationKeys.NOTIFICATION_PREFIX_OLD)) : string;
    }

    public final String c(Bundle bundle, String str) {
        String b = b(bundle, str);
        if (TextUtils.isEmpty(b)) {
            String valueOf = String.valueOf(str);
            String b2 = b(bundle, Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX.length() != 0 ? valueOf.concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX) : new String(valueOf));
            if (TextUtils.isEmpty(b2)) {
                return null;
            }
            Resources resources = this.f8480a.getResources();
            int identifier = resources.getIdentifier(b2, "string", this.f8480a.getPackageName());
            if (identifier == 0) {
                String valueOf2 = String.valueOf(str);
                String substring = (Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX.length() != 0 ? valueOf2.concat(Constants.MessageNotificationKeys.TEXT_RESOURCE_SUFFIX) : new String(valueOf2)).substring(6);
                StringBuilder sb = new StringBuilder(String.valueOf(substring).length() + 49 + String.valueOf(b2).length());
                sb.append(substring);
                sb.append(" resource not found: ");
                sb.append(b2);
                sb.append(" Default value will be used.");
                Log.w("GcmNotification", sb.toString());
                return null;
            }
            String valueOf3 = String.valueOf(str);
            String b3 = b(bundle, Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX.length() != 0 ? valueOf3.concat(Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX) : new String(valueOf3));
            if (TextUtils.isEmpty(b3)) {
                return resources.getString(identifier);
            }
            try {
                JSONArray jSONArray = new JSONArray(b3);
                int length = jSONArray.length();
                Object[] objArr = new String[length];
                for (int i = 0; i < length; i++) {
                    objArr[i] = jSONArray.opt(i);
                }
                return resources.getString(identifier, objArr);
            } catch (MissingFormatArgumentException e) {
                StringBuilder sb2 = new StringBuilder(String.valueOf(b2).length() + 58 + String.valueOf(b3).length());
                sb2.append("Missing format argument for ");
                sb2.append(b2);
                sb2.append(": ");
                sb2.append(b3);
                sb2.append(" Default value will be used.");
                Log.w("GcmNotification", sb2.toString(), e);
                return null;
            } catch (JSONException unused) {
                String valueOf4 = String.valueOf(str);
                String substring2 = (Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX.length() != 0 ? valueOf4.concat(Constants.MessageNotificationKeys.TEXT_ARGS_SUFFIX) : new String(valueOf4)).substring(6);
                StringBuilder sb3 = new StringBuilder(String.valueOf(substring2).length() + 41 + String.valueOf(b3).length());
                sb3.append("Malformed ");
                sb3.append(substring2);
                sb3.append(": ");
                sb3.append(b3);
                sb3.append("  Default value will be used.");
                Log.w("GcmNotification", sb3.toString());
                return null;
            }
        }
        return b;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0264  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0279  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean d(android.os.Bundle r14) {
        /*
            Method dump skipped, instructions count: 661
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.gcm.a.d(android.os.Bundle):boolean");
    }

    public final Bundle e() {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            applicationInfo = this.f8480a.getPackageManager().getApplicationInfo(this.f8480a.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            applicationInfo = null;
        }
        return (applicationInfo == null || (bundle = applicationInfo.metaData) == null) ? Bundle.EMPTY : bundle;
    }
}
