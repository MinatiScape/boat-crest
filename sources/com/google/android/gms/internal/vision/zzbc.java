package com.google.android.gms.internal.vision;

import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.util.Log;
/* loaded from: classes10.dex */
public final class zzbc {

    /* renamed from: a  reason: collision with root package name */
    public static volatile zzcn<Boolean> f10013a = zzcn.zzbx();
    public static final Object b = new Object();

    public static boolean a(Context context) {
        return (context.getPackageManager().getApplicationInfo("com.google.android.gms", 0).flags & 129) != 0;
    }

    public static boolean zza(Context context, Uri uri) {
        ProviderInfo resolveContentProvider;
        boolean z;
        String authority = uri.getAuthority();
        boolean z2 = false;
        if (!"com.google.android.gms.phenotype".equals(authority)) {
            StringBuilder sb = new StringBuilder(String.valueOf(authority).length() + 91);
            sb.append(authority);
            sb.append(" is an unsupported authority. Only com.google.android.gms.phenotype authority is supported.");
            Log.e("PhenotypeClientHelper", sb.toString());
            return false;
        } else if (f10013a.isPresent()) {
            return f10013a.get().booleanValue();
        } else {
            synchronized (b) {
                if (f10013a.isPresent()) {
                    return f10013a.get().booleanValue();
                }
                if (!"com.google.android.gms".equals(context.getPackageName()) && ((resolveContentProvider = context.getPackageManager().resolveContentProvider("com.google.android.gms.phenotype", 0)) == null || !"com.google.android.gms".equals(resolveContentProvider.packageName))) {
                    z = false;
                    if (z && a(context)) {
                        z2 = true;
                    }
                    f10013a = zzcn.zzc(Boolean.valueOf(z2));
                    return f10013a.get().booleanValue();
                }
                z = true;
                if (z) {
                    z2 = true;
                }
                f10013a = zzcn.zzc(Boolean.valueOf(z2));
                return f10013a.get().booleanValue();
            }
        }
    }
}
