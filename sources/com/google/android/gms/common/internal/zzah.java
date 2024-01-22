package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes6.dex */
public final class zzah {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f8343a = new Object();
    @GuardedBy("lock")
    public static boolean b;
    @Nullable
    public static String c;
    public static int d;

    public static void a(Context context) {
        Bundle bundle;
        synchronized (f8343a) {
            if (b) {
                return;
            }
            b = true;
            try {
                bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException e) {
                Log.wtf("MetadataValueReader", "This should never happen.", e);
            }
            if (bundle == null) {
                return;
            }
            c = bundle.getString("com.google.app.id");
            d = bundle.getInt("com.google.android.gms.version");
        }
    }

    public static int zza(Context context) {
        a(context);
        return d;
    }

    @Nullable
    public static String zzb(Context context) {
        a(context);
        return c;
    }
}
