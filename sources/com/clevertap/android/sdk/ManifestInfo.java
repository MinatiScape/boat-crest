package com.clevertap.android.sdk;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes2.dex */
public class ManifestInfo {
    public static String c;
    public static String d;
    public static String e;
    public static boolean f;
    public static boolean g;
    public static String h;
    public static ManifestInfo i;
    public static String j;
    public static boolean k;
    public static boolean l;
    public static boolean m;
    public static String n;
    public static String o;
    public static boolean p;
    public static String q;
    public static String r;
    public static String s;
    public static int t;

    /* renamed from: a  reason: collision with root package name */
    public final String f2583a;
    public final String[] b;

    public ManifestInfo(Context context) {
        Bundle bundle;
        try {
            bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (Throwable unused) {
            bundle = null;
        }
        bundle = bundle == null ? new Bundle() : bundle;
        if (c == null) {
            c = a(bundle, Constants.LABEL_ACCOUNT_ID);
        }
        if (d == null) {
            d = a(bundle, Constants.LABEL_TOKEN);
        }
        if (e == null) {
            e = a(bundle, Constants.LABEL_REGION);
        }
        h = a(bundle, Constants.LABEL_NOTIFICATION_ICON);
        f = "1".equals(a(bundle, Constants.LABEL_USE_GOOGLE_AD_ID));
        g = "1".equals(a(bundle, Constants.LABEL_DISABLE_APP_LAUNCH));
        j = a(bundle, Constants.LABEL_INAPP_EXCLUDE);
        k = "1".equals(a(bundle, Constants.LABEL_SSL_PINNING));
        l = "1".equals(a(bundle, Constants.LABEL_BACKGROUND_SYNC));
        m = "1".equals(a(bundle, Constants.LABEL_CUSTOM_ID));
        n = a(bundle, Constants.LABEL_FCM_SENDER_ID);
        try {
            int parseInt = Integer.parseInt(a(bundle, Constants.LABEL_ENCRYPTION_LEVEL));
            if (parseInt >= 0 && parseInt <= 1) {
                t = parseInt;
            } else {
                t = 0;
                Logger.v("Supported encryption levels are only 0 and 1. Setting it to 0 by default");
            }
        } catch (Throwable th) {
            t = 0;
            Logger.v("Unable to parse encryption level from the Manifest, Setting it to 0 by default", th.getCause());
        }
        String str = n;
        if (str != null) {
            n = str.replace("id:", "");
        }
        o = a(bundle, Constants.LABEL_PACKAGE_NAME);
        p = "1".equals(a(bundle, Constants.LABEL_BETA));
        if (q == null) {
            q = a(bundle, Constants.LABEL_INTENT_SERVICE);
        }
        if (r == null) {
            r = a(bundle, Constants.LABEL_XIAOMI_APP_KEY);
        }
        if (s == null) {
            s = a(bundle, Constants.LABEL_XIAOMI_APP_ID);
        }
        this.f2583a = a(bundle, Constants.LABEL_DEFAULT_CHANNEL_ID);
        this.b = i(bundle);
    }

    public static String a(Bundle bundle, String str) {
        try {
            Object obj = bundle.get(str);
            if (obj != null) {
                return obj.toString();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void b(String str, String str2, String str3) {
        c = str;
        d = str2;
        e = str3;
    }

    public static void c(String str, String str2) {
        if (s == null && r == null) {
            s = str;
            r = str2;
            return;
        }
        Logger.i("Xiaomi SDK already initialized with AppID:" + s + " and AppKey:" + r + ". Cannot change credentials to " + str + " and " + str2);
    }

    public static synchronized ManifestInfo getInstance(Context context) {
        ManifestInfo manifestInfo;
        synchronized (ManifestInfo.class) {
            if (i == null) {
                i = new ManifestInfo(context);
            }
            manifestInfo = i;
        }
        return manifestInfo;
    }

    public boolean d() {
        return p;
    }

    public String e() {
        return d;
    }

    public String f() {
        return o;
    }

    public boolean g() {
        return g;
    }

    public String getAccountId() {
        return c;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public String getAccountRegion() {
        Logger.v("ManifestInfo: getAccountRegion called, returning region:" + e);
        return e;
    }

    public String getDevDefaultPushChannelId() {
        return this.f2583a;
    }

    public int getEncryptionLevel() {
        return t;
    }

    public String getExcludedActivities() {
        return j;
    }

    public String getFCMSenderId() {
        return n;
    }

    public String getIntentServiceName() {
        return q;
    }

    public String getNotificationIcon() {
        return h;
    }

    public String[] getProfileKeys() {
        return this.b;
    }

    public String getXiaomiAppID() {
        return s;
    }

    public String getXiaomiAppKey() {
        return r;
    }

    public boolean h() {
        return l;
    }

    public final String[] i(Bundle bundle) {
        String a2 = a(bundle, Constants.CLEVERTAP_IDENTIFIER);
        return !TextUtils.isEmpty(a2) ? a2.split(Constants.SEPARATOR_COMMA) : Constants.NULL_STRING_ARRAY;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean isSSLPinningEnabled() {
        return k;
    }

    public boolean j() {
        return m;
    }

    public boolean k() {
        return f;
    }
}
