package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.errorprone.annotations.CheckReturnValue;
import java.security.MessageDigest;
import java.util.concurrent.Callable;
@CheckReturnValue
/* loaded from: classes6.dex */
public final class m {
    public static volatile zzag e;
    public static Context g;

    /* renamed from: a  reason: collision with root package name */
    public static final k f8351a = new e(i.c("0\u0082\u0005È0\u0082\u0003° \u0003\u0002\u0001\u0002\u0002\u0014\u0010\u008ae\bsù/\u008eQí"));
    public static final k b = new f(i.c("0\u0082\u0006\u00040\u0082\u0003ì \u0003\u0002\u0001\u0002\u0002\u0014\u0003£²\u00ad×árÊkì"));
    public static final k c = new g(i.c("0\u0082\u0004C0\u0082\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000Âà\u0087FdJ0\u008d0"));
    public static final k d = new h(i.c("0\u0082\u0004¨0\u0082\u0003\u0090 \u0003\u0002\u0001\u0002\u0002\t\u0000Õ\u0085¸l}ÓNõ0"));
    public static final Object f = new Object();

    public static o a(String str, i iVar, boolean z, boolean z2) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return h(str, iVar, z, z2);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public static o b(String str, boolean z, boolean z2, boolean z3) {
        return i(str, z, false, false, true);
    }

    public static o c(String str, boolean z, boolean z2, boolean z3) {
        return i(str, z, false, false, false);
    }

    public static /* synthetic */ String d(boolean z, String str, i iVar) throws Exception {
        String str2 = true != (!z && h(str, iVar, true, false).f8364a) ? "not allowed" : "debug cert rejected";
        MessageDigest zza = AndroidUtilsLight.zza("SHA-256");
        Preconditions.checkNotNull(zza);
        return String.format("%s: pkg=%s, sha256=%s, atk=%s, ver=%s", str2, str, Hex.bytesToStringLowercase(zza.digest(iVar.t())), Boolean.valueOf(z), "12451000.false");
    }

    public static synchronized void e(Context context) {
        synchronized (m.class) {
            if (g != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                g = context.getApplicationContext();
            }
        }
    }

    public static boolean f() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                j();
                return e.zzg();
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        } catch (RemoteException | DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return false;
        }
    }

    public static boolean g() {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            try {
                j();
                return e.zzi();
            } finally {
                StrictMode.setThreadPolicy(allowThreadDiskReads);
            }
        } catch (RemoteException | DynamiteModule.LoadingException e2) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            return false;
        }
    }

    public static o h(final String str, final i iVar, final boolean z, boolean z2) {
        try {
            j();
            Preconditions.checkNotNull(g);
            try {
                return e.zzh(new zzs(str, iVar, z, z2), ObjectWrapper.wrap(g.getPackageManager())) ? o.b() : new n(new Callable() { // from class: com.google.android.gms.common.zze
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return m.d(z, str, iVar);
                    }
                }, null);
            } catch (RemoteException e2) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
                return o.d("module call", e2);
            }
        } catch (DynamiteModule.LoadingException e3) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
            return o.d("module init: ".concat(String.valueOf(e3.getMessage())), e3);
        }
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.google.android.gms.dynamic.IObjectWrapper, android.os.IBinder] */
    public static o i(String str, boolean z, boolean z2, boolean z3, boolean z4) {
        o d2;
        zzq zzf;
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            Preconditions.checkNotNull(g);
            try {
                j();
                zzo zzoVar = new zzo(str, z, false, ObjectWrapper.wrap(g), false, true);
                try {
                    if (z4) {
                        zzf = e.zze(zzoVar);
                    } else {
                        zzf = e.zzf(zzoVar);
                    }
                    if (zzf.zzb()) {
                        d2 = o.f(zzf.zzc());
                    } else {
                        String zza = zzf.zza();
                        PackageManager.NameNotFoundException nameNotFoundException = zzf.zzd() == 4 ? new PackageManager.NameNotFoundException() : null;
                        if (zza == null) {
                            zza = "error checking package certificate";
                        }
                        d2 = o.g(zzf.zzc(), zzf.zzd(), zza, nameNotFoundException);
                    }
                } catch (RemoteException e2) {
                    Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
                    d2 = o.d("module call", e2);
                }
            } catch (DynamiteModule.LoadingException e3) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
                d2 = o.d("module init: ".concat(String.valueOf(e3.getMessage())), e3);
            }
            return d2;
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public static void j() throws DynamiteModule.LoadingException {
        if (e != null) {
            return;
        }
        Preconditions.checkNotNull(g);
        synchronized (f) {
            if (e == null) {
                e = zzaf.zzb(DynamiteModule.load(g, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
            }
        }
    }
}
