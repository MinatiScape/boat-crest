package com.google.firebase.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
/* loaded from: classes10.dex */
public class DataCollectionConfigStorage {
    @VisibleForTesting
    public static final String DATA_COLLECTION_DEFAULT_ENABLED = "firebase_data_collection_default_enabled";

    /* renamed from: a  reason: collision with root package name */
    public final Context f11326a;
    public final SharedPreferences b;
    public final Publisher c;
    public boolean d;

    public DataCollectionConfigStorage(Context context, String str, Publisher publisher) {
        Context a2 = a(context);
        this.f11326a = a2;
        this.b = a2.getSharedPreferences("com.google.firebase.common.prefs:" + str, 0);
        this.c = publisher;
        this.d = b();
    }

    public static Context a(Context context) {
        return Build.VERSION.SDK_INT < 24 ? context : ContextCompat.createDeviceProtectedStorageContext(context);
    }

    public final boolean b() {
        if (this.b.contains(DATA_COLLECTION_DEFAULT_ENABLED)) {
            return this.b.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED, true);
        }
        return c();
    }

    public final boolean c() {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            PackageManager packageManager = this.f11326a.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(this.f11326a.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey(DATA_COLLECTION_DEFAULT_ENABLED)) {
                return true;
            }
            return applicationInfo.metaData.getBoolean(DATA_COLLECTION_DEFAULT_ENABLED);
        } catch (PackageManager.NameNotFoundException unused) {
            return true;
        }
    }

    public final synchronized void d(boolean z) {
        if (this.d != z) {
            this.d = z;
            this.c.publish(new Event<>(DataCollectionDefaultChange.class, new DataCollectionDefaultChange(z)));
        }
    }

    public synchronized boolean isEnabled() {
        return this.d;
    }

    public synchronized void setEnabled(Boolean bool) {
        if (bool == null) {
            this.b.edit().remove(DATA_COLLECTION_DEFAULT_ENABLED).apply();
            d(c());
        } else {
            boolean equals = Boolean.TRUE.equals(bool);
            this.b.edit().putBoolean(DATA_COLLECTION_DEFAULT_ENABLED, equals).apply();
            d(equals);
        }
    }
}
