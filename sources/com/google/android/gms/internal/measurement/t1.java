package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public final class t1 implements r1 {
    @GuardedBy("GservicesLoader.class")
    public static t1 c;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Context f8930a;
    @Nullable
    public final ContentObserver b;

    public t1() {
        this.f8930a = null;
        this.b = null;
    }

    public t1(Context context) {
        this.f8930a = context;
        s1 s1Var = new s1(this, null);
        this.b = s1Var;
        context.getContentResolver().registerContentObserver(zzgv.zza, true, s1Var);
    }

    public static t1 a(Context context) {
        t1 t1Var;
        t1 t1Var2;
        synchronized (t1.class) {
            if (c == null) {
                if (PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
                    t1Var2 = new t1(context);
                } else {
                    t1Var2 = new t1();
                }
                c = t1Var2;
            }
            t1Var = c;
        }
        return t1Var;
    }

    public static synchronized void d() {
        Context context;
        synchronized (t1.class) {
            t1 t1Var = c;
            if (t1Var != null && (context = t1Var.f8930a) != null && t1Var.b != null) {
                context.getContentResolver().unregisterContentObserver(c.b);
            }
            c = null;
        }
    }

    @Override // com.google.android.gms.internal.measurement.r1
    /* renamed from: b */
    public final String zzb(final String str) {
        if (this.f8930a == null) {
            return null;
        }
        try {
            return (String) zzhc.zza(new zzhd() { // from class: com.google.android.gms.internal.measurement.zzhf
                @Override // com.google.android.gms.internal.measurement.zzhd
                public final Object zza() {
                    return t1.this.c(str);
                }
            });
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    public final /* synthetic */ String c(String str) {
        return zzgv.zza(this.f8930a.getContentResolver(), str, null);
    }
}
