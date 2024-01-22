package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.firebase.FirebaseApp;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
@KeepForSdk
/* loaded from: classes10.dex */
public class Metadata {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11285a;
    @GuardedBy("this")
    public String b;
    @GuardedBy("this")
    public String c;
    @GuardedBy("this")
    public int d;
    @GuardedBy("this")
    public int e = 0;

    public Metadata(Context context) {
        this.f11285a = context;
    }

    public static String getDefaultSenderId(FirebaseApp firebaseApp) {
        String gcmSenderId = firebaseApp.getOptions().getGcmSenderId();
        if (gcmSenderId != null) {
            return gcmSenderId;
        }
        String applicationId = firebaseApp.getOptions().getApplicationId();
        if (applicationId.startsWith("1:")) {
            String[] split = applicationId.split(":");
            if (split.length < 2) {
                return null;
            }
            String str = split[1];
            if (str.isEmpty()) {
                return null;
            }
            return str;
        }
        return applicationId;
    }

    public final PackageInfo a(String str) {
        try {
            return this.f11285a.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(valueOf.length() + 23);
            sb.append("Failed to find package ");
            sb.append(valueOf);
            Log.w("FirebaseInstanceId", sb.toString());
            return null;
        }
    }

    public final synchronized void b() {
        PackageInfo a2 = a(this.f11285a.getPackageName());
        if (a2 != null) {
            this.b = Integer.toString(a2.versionCode);
            this.c = a2.versionName;
        }
    }

    public synchronized String getAppVersionCode() {
        if (this.b == null) {
            b();
        }
        return this.b;
    }

    public synchronized String getAppVersionName() {
        if (this.c == null) {
            b();
        }
        return this.c;
    }

    public synchronized int getGmsVersionCode() {
        PackageInfo a2;
        if (this.d == 0 && (a2 = a("com.google.android.gms")) != null) {
            this.d = a2.versionCode;
        }
        return this.d;
    }

    public synchronized int getIidImplementation() {
        int i = this.e;
        if (i != 0) {
            return i;
        }
        PackageManager packageManager = this.f11285a.getPackageManager();
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
            Log.e("FirebaseInstanceId", "Google Play services missing or without correct permission.");
            return 0;
        }
        int i2 = 1;
        if (!PlatformVersion.isAtLeastO()) {
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                this.e = 1;
                return 1;
            }
        }
        Intent intent2 = new Intent("com.google.iid.TOKEN_REQUEST");
        intent2.setPackage("com.google.android.gms");
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
        if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
            this.e = 2;
            return 2;
        }
        Log.w("FirebaseInstanceId", "Failed to resolve IID implementation package, falling back");
        if (PlatformVersion.isAtLeastO()) {
            this.e = 2;
            i2 = 2;
        } else {
            this.e = 1;
        }
        return i2;
    }

    @KeepForSdk
    public boolean isGmscorePresent() {
        return getIidImplementation() != 0;
    }
}
