package com.google.android.gms.internal.vision;

import android.content.Context;
import android.database.ContentObserver;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.core.content.PermissionChecker;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public final class e implements d {
    @GuardedBy("GservicesLoader.class")
    public static e c;
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Context f9967a;
    @Nullable
    public final ContentObserver b;

    public e(Context context) {
        this.f9967a = context;
        f fVar = new f(this, null);
        this.b = fVar;
        context.getContentResolver().registerContentObserver(zzal.CONTENT_URI, true, fVar);
    }

    public static synchronized void a() {
        Context context;
        synchronized (e.class) {
            e eVar = c;
            if (eVar != null && (context = eVar.f9967a) != null && eVar.b != null) {
                context.getContentResolver().unregisterContentObserver(c.b);
            }
            c = null;
        }
    }

    public static e d(Context context) {
        e eVar;
        synchronized (e.class) {
            if (c == null) {
                c = PermissionChecker.checkSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0 ? new e(context) : new e();
            }
            eVar = c;
        }
        return eVar;
    }

    @Override // com.google.android.gms.internal.vision.d
    /* renamed from: b */
    public final String zzb(final String str) {
        if (this.f9967a == null) {
            return null;
        }
        try {
            return (String) zzat.zza(new zzaw(this, str) { // from class: com.google.android.gms.internal.vision.g

                /* renamed from: a  reason: collision with root package name */
                public final e f9975a;
                public final String b;

                {
                    this.f9975a = this;
                    this.b = str;
                }

                @Override // com.google.android.gms.internal.vision.zzaw
                public final Object zzt() {
                    return this.f9975a.c(this.b);
                }
            });
        } catch (IllegalStateException | SecurityException e) {
            String valueOf = String.valueOf(str);
            Log.e("GservicesLoader", valueOf.length() != 0 ? "Unable to read GServices for: ".concat(valueOf) : new String("Unable to read GServices for: "), e);
            return null;
        }
    }

    public final /* synthetic */ String c(String str) {
        return zzal.zza(this.f9967a.getContentResolver(), str, null);
    }

    public e() {
        this.f9967a = null;
        this.b = null;
    }
}
