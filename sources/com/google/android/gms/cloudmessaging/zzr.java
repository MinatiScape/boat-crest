package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zzr {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8233a;
    @GuardedBy("this")
    public int b;
    @GuardedBy("this")
    public int c = 0;

    public zzr(Context context) {
        this.f8233a = context;
    }

    @Nullable
    public final PackageInfo a(String str) {
        try {
            return Wrappers.packageManager(this.f8233a).getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            String valueOf = String.valueOf(e);
            StringBuilder sb = new StringBuilder(valueOf.length() + 23);
            sb.append("Failed to find package ");
            sb.append(valueOf);
            Log.w("Metadata", sb.toString());
            return null;
        }
    }

    public final synchronized int zza() {
        int i = this.c;
        if (i != 0) {
            return i;
        }
        PackageManager packageManager = this.f8233a.getPackageManager();
        if (Wrappers.packageManager(this.f8233a).checkPermission("com.google.android.c2dm.permission.SEND", "com.google.android.gms") == -1) {
            Log.e("Metadata", "Google Play services missing or without correct permission.");
            return 0;
        }
        if (!PlatformVersion.isAtLeastO()) {
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                this.c = 1;
                return 1;
            }
        }
        Intent intent2 = new Intent("com.google.iid.TOKEN_REQUEST");
        intent2.setPackage("com.google.android.gms");
        List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 0);
        if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
            this.c = 2;
            return 2;
        }
        Log.w("Metadata", "Failed to resolve IID implementation package, falling back");
        if (PlatformVersion.isAtLeastO()) {
            this.c = 2;
        } else {
            this.c = 1;
        }
        return this.c;
    }

    public final synchronized int zzb() {
        PackageInfo a2;
        if (this.b == 0 && (a2 = a("com.google.android.gms")) != null) {
            this.b = a2.versionCode;
        }
        return this.b;
    }
}
