package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.installations.FirebaseInstallationsApi;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes10.dex */
public class IdManager implements InstallIdProvider {
    public static final String DEFAULT_VERSION_NAME = "0.0";
    public static final Pattern g = Pattern.compile("[^\\p{Alnum}]");
    public static final String h = Pattern.quote(MqttTopic.TOPIC_LEVEL_SEPARATOR);

    /* renamed from: a  reason: collision with root package name */
    public final l f11137a;
    public final Context b;
    public final String c;
    public final FirebaseInstallationsApi d;
    public final DataCollectionArbiter e;
    public String f;

    public IdManager(Context context, String str, FirebaseInstallationsApi firebaseInstallationsApi, DataCollectionArbiter dataCollectionArbiter) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        }
        if (str != null) {
            this.b = context;
            this.c = str;
            this.d = firebaseInstallationsApi;
            this.e = dataCollectionArbiter;
            this.f11137a = new l();
            return;
        }
        throw new IllegalArgumentException("appIdentifier must not be null");
    }

    public static String b() {
        return "SYN_" + UUID.randomUUID().toString();
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        return g.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    public static boolean e(String str) {
        return str != null && str.startsWith("SYN_");
    }

    @NonNull
    public final synchronized String a(String str, SharedPreferences sharedPreferences) {
        String d;
        d = d(UUID.randomUUID().toString());
        Logger logger = Logger.getLogger();
        logger.v("Created new Crashlytics installation ID: " + d + " for FID: " + str);
        sharedPreferences.edit().putString("crashlytics.installation.id", d).putString("firebase.installation.id", str).apply();
        return d;
    }

    @Nullable
    public final String c() {
        try {
            return (String) Utils.awaitEvenIfOnMainThread(this.d.getId());
        } catch (Exception e) {
            Logger.getLogger().w("Failed to retrieve Firebase Installations ID.", e);
            return null;
        }
    }

    public final String f(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString("crashlytics.installation.id", null);
    }

    public final String g(String str) {
        return str.replaceAll(h, "");
    }

    public String getAppIdentifier() {
        return this.c;
    }

    @Override // com.google.firebase.crashlytics.internal.common.InstallIdProvider
    @NonNull
    public synchronized String getCrashlyticsInstallId() {
        String str = this.f;
        if (str != null) {
            return str;
        }
        Logger.getLogger().v("Determining Crashlytics installation ID...");
        SharedPreferences sharedPrefs = CommonUtils.getSharedPrefs(this.b);
        String string = sharedPrefs.getString("firebase.installation.id", null);
        Logger logger = Logger.getLogger();
        logger.v("Cached Firebase Installation ID: " + string);
        if (this.e.isAutomaticDataCollectionEnabled()) {
            String c = c();
            Logger logger2 = Logger.getLogger();
            logger2.v("Fetched Firebase Installation ID: " + c);
            if (c == null) {
                c = string == null ? b() : string;
            }
            if (c.equals(string)) {
                this.f = f(sharedPrefs);
            } else {
                this.f = a(c, sharedPrefs);
            }
        } else if (e(string)) {
            this.f = f(sharedPrefs);
        } else {
            this.f = a(b(), sharedPrefs);
        }
        if (this.f == null) {
            Logger.getLogger().w("Unable to determine Crashlytics Install Id, creating a new one.");
            this.f = a(b(), sharedPrefs);
        }
        Logger logger3 = Logger.getLogger();
        logger3.v("Crashlytics installation ID: " + this.f);
        return this.f;
    }

    public String getInstallerPackageName() {
        return this.f11137a.a(this.b);
    }

    public String getModelName() {
        return String.format(Locale.US, "%s/%s", g(Build.MANUFACTURER), g(Build.MODEL));
    }

    public String getOsBuildVersionString() {
        return g(Build.VERSION.INCREMENTAL);
    }

    public String getOsDisplayVersionString() {
        return g(Build.VERSION.RELEASE);
    }
}
