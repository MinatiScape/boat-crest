package com.google.firebase.crashlytics.internal.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.Logger;
/* loaded from: classes10.dex */
public class DataCollectionArbiter {

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11136a;
    public final FirebaseApp b;
    public final Object c;
    public TaskCompletionSource<Void> d;
    public boolean e;
    public boolean f;
    @Nullable
    public Boolean g;
    public final TaskCompletionSource<Void> h;

    public DataCollectionArbiter(FirebaseApp firebaseApp) {
        Object obj = new Object();
        this.c = obj;
        this.d = new TaskCompletionSource<>();
        this.e = false;
        this.f = false;
        this.h = new TaskCompletionSource<>();
        Context applicationContext = firebaseApp.getApplicationContext();
        this.b = firebaseApp;
        this.f11136a = CommonUtils.getSharedPrefs(applicationContext);
        Boolean b = b();
        this.g = b == null ? a(applicationContext) : b;
        synchronized (obj) {
            if (isAutomaticDataCollectionEnabled()) {
                this.d.trySetResult(null);
                this.e = true;
            }
        }
    }

    @Nullable
    public static Boolean d(Context context) {
        ApplicationInfo applicationInfo;
        Bundle bundle;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey("firebase_crashlytics_collection_enabled")) {
                return null;
            }
            return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_crashlytics_collection_enabled"));
        } catch (PackageManager.NameNotFoundException e) {
            Logger.getLogger().e("Could not read data collection permission from manifest", e);
            return null;
        }
    }

    @SuppressLint({"ApplySharedPref"})
    public static void e(SharedPreferences sharedPreferences, Boolean bool) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (bool != null) {
            edit.putBoolean("firebase_crashlytics_collection_enabled", bool.booleanValue());
        } else {
            edit.remove("firebase_crashlytics_collection_enabled");
        }
        edit.commit();
    }

    @Nullable
    public final Boolean a(Context context) {
        Boolean d = d(context);
        if (d == null) {
            this.f = false;
            return null;
        }
        this.f = true;
        return Boolean.valueOf(Boolean.TRUE.equals(d));
    }

    @Nullable
    public final Boolean b() {
        if (this.f11136a.contains("firebase_crashlytics_collection_enabled")) {
            this.f = false;
            return Boolean.valueOf(this.f11136a.getBoolean("firebase_crashlytics_collection_enabled", true));
        }
        return null;
    }

    public final void c(boolean z) {
        String str;
        String str2 = z ? "ENABLED" : "DISABLED";
        if (this.g == null) {
            str = "global Firebase setting";
        } else {
            str = this.f ? "firebase_crashlytics_collection_enabled manifest flag" : "API";
        }
        Logger.getLogger().d(String.format("Crashlytics automatic data collection %s by %s.", str2, str));
    }

    public void grantDataCollectionPermission(boolean z) {
        if (z) {
            this.h.trySetResult(null);
            return;
        }
        throw new IllegalStateException("An invalid data collection token was used.");
    }

    public synchronized boolean isAutomaticDataCollectionEnabled() {
        boolean isDataCollectionDefaultEnabled;
        Boolean bool = this.g;
        if (bool != null) {
            isDataCollectionDefaultEnabled = bool.booleanValue();
        } else {
            isDataCollectionDefaultEnabled = this.b.isDataCollectionDefaultEnabled();
        }
        c(isDataCollectionDefaultEnabled);
        return isDataCollectionDefaultEnabled;
    }

    public synchronized void setCrashlyticsDataCollectionEnabled(@Nullable Boolean bool) {
        if (bool != null) {
            try {
                this.f = false;
            } catch (Throwable th) {
                throw th;
            }
        }
        this.g = bool != null ? bool : a(this.b.getApplicationContext());
        e(this.f11136a, bool);
        synchronized (this.c) {
            if (isAutomaticDataCollectionEnabled()) {
                if (!this.e) {
                    this.d.trySetResult(null);
                    this.e = true;
                }
            } else if (this.e) {
                this.d = new TaskCompletionSource<>();
                this.e = false;
            }
        }
    }

    public Task<Void> waitForAutomaticDataCollectionEnabled() {
        Task<Void> task;
        synchronized (this.c) {
            task = this.d.getTask();
        }
        return task;
    }

    public Task<Void> waitForDataCollectionPermission() {
        return Utils.race(this.h.getTask(), waitForAutomaticDataCollectionEnabled());
    }
}
