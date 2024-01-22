package com.google.android.gms.internal.auth;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class z implements x {
    @GuardedBy("GservicesLoader.class")
    public static z c;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Context f8548a;
    @Nullable
    public final ContentObserver b;

    public z() {
        this.f8548a = null;
        this.b = null;
    }

    public z(Context context) {
        this.f8548a = context;
        y yVar = new y(this, null);
        this.b = yVar;
        context.getContentResolver().registerContentObserver(zzcb.zza, true, yVar);
    }

    public static z a(Context context) {
        z zVar;
        z zVar2;
        synchronized (z.class) {
            if (c == null) {
                if (PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                    zVar2 = new z(context);
                } else {
                    zVar2 = new z();
                }
                c = zVar2;
            }
            zVar = c;
        }
        return zVar;
    }

    public static synchronized void d() {
        Context context;
        synchronized (z.class) {
            z zVar = c;
            if (zVar != null && (context = zVar.f8548a) != null && zVar.b != null) {
                context.getContentResolver().unregisterContentObserver(c.b);
            }
            c = null;
        }
    }

    @Override // com.google.android.gms.internal.auth.x
    @Nullable
    /* renamed from: b */
    public final String zzb(final String str) {
        Context context = this.f8548a;
        if (context != null && !zzcc.zza(context)) {
            try {
                return (String) zzcj.zza(new zzck() { // from class: com.google.android.gms.internal.auth.zzcm
                    @Override // com.google.android.gms.internal.auth.zzck
                    public final Object zza() {
                        return z.this.c(str);
                    }
                });
            } catch (IllegalStateException | NullPointerException | SecurityException e) {
                Log.e("GservicesLoader", "Unable to read GServices for: ".concat(String.valueOf(str)), e);
            }
        }
        return null;
    }

    public final /* synthetic */ String c(String str) {
        return zzcb.zza(this.f8548a.getContentResolver(), str, null);
    }
}
