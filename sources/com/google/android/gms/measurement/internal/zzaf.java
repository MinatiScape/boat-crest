package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes10.dex */
public final class zzaf extends w0 {

    /* renamed from: a  reason: collision with root package name */
    public Boolean f10139a;
    public b b;
    public Boolean c;

    public zzaf(zzfs zzfsVar) {
        super(zzfsVar);
        this.b = new b() { // from class: com.google.android.gms.measurement.internal.zzad
            @Override // com.google.android.gms.measurement.internal.b
            public final String zza(String str, String str2) {
                return null;
            }
        };
    }

    public static final long zzA() {
        return zzdw.zzC.zza(null).longValue();
    }

    public static final long zzz() {
        return zzdw.zzc.zza(null).longValue();
    }

    public final String a(String str, String str2) {
        try {
            String str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, "");
            Preconditions.checkNotNull(str3);
            return str3;
        } catch (ClassNotFoundException e) {
            this.zzs.zzay().zzd().zzb("Could not find SystemProperties class", e);
            return "";
        } catch (IllegalAccessException e2) {
            this.zzs.zzay().zzd().zzb("Could not access SystemProperties.get()", e2);
            return "";
        } catch (NoSuchMethodException e3) {
            this.zzs.zzay().zzd().zzb("Could not find SystemProperties.get() method", e3);
            return "";
        } catch (InvocationTargetException e4) {
            this.zzs.zzay().zzd().zzb("SystemProperties.get() threw an exception", e4);
            return "";
        }
    }

    public final int b(@Size(min = 1) String str) {
        return zzf(str, zzdw.zzG, 500, 2000);
    }

    @VisibleForTesting
    public final Bundle c() {
        try {
            if (this.zzs.zzau().getPackageManager() == null) {
                this.zzs.zzay().zzd().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo = Wrappers.packageManager(this.zzs.zzau()).getApplicationInfo(this.zzs.zzau().getPackageName(), 128);
            if (applicationInfo == null) {
                this.zzs.zzay().zzd().zza("Failed to load metadata: ApplicationInfo is null");
                return null;
            }
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            this.zzs.zzay().zzd().zzb("Failed to load metadata: Package name not found", e);
            return null;
        }
    }

    @VisibleForTesting
    public final Boolean d(@Size(min = 1) String str) {
        Preconditions.checkNotEmpty(str);
        Bundle c = c();
        if (c == null) {
            this.zzs.zzay().zzd().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        } else if (c.containsKey(str)) {
            return Boolean.valueOf(c.getBoolean(str));
        } else {
            return null;
        }
    }

    public final String e() {
        this.zzs.zzaw();
        return "FA";
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x002e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @com.google.android.gms.common.util.VisibleForTesting
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<java.lang.String> f(@androidx.annotation.Size(min = 1) java.lang.String r4) {
        /*
            r3 = this;
            java.lang.String r4 = "analytics.safelisted_events"
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r4)
            android.os.Bundle r0 = r3.c()
            r1 = 0
            if (r0 != 0) goto L1d
            com.google.android.gms.measurement.internal.zzfs r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeg r4 = r4.zzd()
            java.lang.String r0 = "Failed to load metadata: Metadata bundle is null"
            r4.zza(r0)
        L1b:
            r4 = r1
            goto L2c
        L1d:
            boolean r2 = r0.containsKey(r4)
            if (r2 != 0) goto L24
            goto L1b
        L24:
            int r4 = r0.getInt(r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
        L2c:
            if (r4 == 0) goto L58
            com.google.android.gms.measurement.internal.zzfs r0 = r3.zzs     // Catch: android.content.res.Resources.NotFoundException -> L48
            android.content.Context r0 = r0.zzau()     // Catch: android.content.res.Resources.NotFoundException -> L48
            android.content.res.Resources r0 = r0.getResources()     // Catch: android.content.res.Resources.NotFoundException -> L48
            int r4 = r4.intValue()     // Catch: android.content.res.Resources.NotFoundException -> L48
            java.lang.String[] r4 = r0.getStringArray(r4)     // Catch: android.content.res.Resources.NotFoundException -> L48
            if (r4 != 0) goto L43
            return r1
        L43:
            java.util.List r4 = java.util.Arrays.asList(r4)     // Catch: android.content.res.Resources.NotFoundException -> L48
            return r4
        L48:
            r4 = move-exception
            com.google.android.gms.measurement.internal.zzfs r0 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeg r0 = r0.zzd()
            java.lang.String r2 = "Failed to load string array from metadata: resource not found"
            r0.zzb(r2, r4)
        L58:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzaf.f(java.lang.String):java.util.List");
    }

    public final void g(b bVar) {
        this.b = bVar;
    }

    @WorkerThread
    public final boolean h() {
        if (this.f10139a == null) {
            Boolean d = d("app_measurement_lite");
            this.f10139a = d;
            if (d == null) {
                this.f10139a = Boolean.FALSE;
            }
        }
        return this.f10139a.booleanValue() || !this.zzs.zzN();
    }

    @WorkerThread
    public final double zza(String str, zzdv<Double> zzdvVar) {
        if (str == null) {
            return zzdvVar.zza(null).doubleValue();
        }
        String zza = this.b.zza(str, zzdvVar.zzb());
        if (TextUtils.isEmpty(zza)) {
            return zzdvVar.zza(null).doubleValue();
        }
        try {
            return zzdvVar.zza(Double.valueOf(Double.parseDouble(zza))).doubleValue();
        } catch (NumberFormatException unused) {
            return zzdvVar.zza(null).doubleValue();
        }
    }

    public final int zzc() {
        zzku zzv = this.zzs.zzv();
        Boolean l = zzv.zzs.zzt().l();
        if (zzv.zzm() < 201500) {
            return (l == null || l.booleanValue()) ? 25 : 100;
        }
        return 100;
    }

    public final int zzd(@Size(min = 1) String str) {
        return zzf(str, zzdw.zzH, 25, 100);
    }

    @WorkerThread
    public final int zze(String str, zzdv<Integer> zzdvVar) {
        if (str == null) {
            return zzdvVar.zza(null).intValue();
        }
        String zza = this.b.zza(str, zzdvVar.zzb());
        if (TextUtils.isEmpty(zza)) {
            return zzdvVar.zza(null).intValue();
        }
        try {
            return zzdvVar.zza(Integer.valueOf(Integer.parseInt(zza))).intValue();
        } catch (NumberFormatException unused) {
            return zzdvVar.zza(null).intValue();
        }
    }

    @WorkerThread
    public final int zzf(String str, zzdv<Integer> zzdvVar, int i, int i2) {
        return Math.max(Math.min(zze(str, zzdvVar), i2), i);
    }

    public final long zzh() {
        this.zzs.zzaw();
        return 42097L;
    }

    @WorkerThread
    public final long zzi(String str, zzdv<Long> zzdvVar) {
        if (str == null) {
            return zzdvVar.zza(null).longValue();
        }
        String zza = this.b.zza(str, zzdvVar.zzb());
        if (TextUtils.isEmpty(zza)) {
            return zzdvVar.zza(null).longValue();
        }
        try {
            return zzdvVar.zza(Long.valueOf(Long.parseLong(zza))).longValue();
        } catch (NumberFormatException unused) {
            return zzdvVar.zza(null).longValue();
        }
    }

    public final String zzl() {
        return a("debug.firebase.analytics.app", "");
    }

    public final String zzm() {
        return a("debug.deferred.deeplink", "");
    }

    @WorkerThread
    public final String zzo(String str, zzdv<String> zzdvVar) {
        if (str == null) {
            return zzdvVar.zza(null);
        }
        return zzdvVar.zza(this.b.zza(str, zzdvVar.zzb()));
    }

    public final boolean zzr() {
        Boolean d = d("google_analytics_adid_collection_enabled");
        return d == null || d.booleanValue();
    }

    @WorkerThread
    public final boolean zzs(String str, zzdv<Boolean> zzdvVar) {
        if (str == null) {
            return zzdvVar.zza(null).booleanValue();
        }
        String zza = this.b.zza(str, zzdvVar.zzb());
        if (TextUtils.isEmpty(zza)) {
            return zzdvVar.zza(null).booleanValue();
        }
        return zzdvVar.zza(Boolean.valueOf(Boolean.parseBoolean(zza))).booleanValue();
    }

    public final boolean zzt(String str) {
        return "1".equals(this.b.zza(str, "gaia_collection_enabled"));
    }

    public final boolean zzu() {
        Boolean d = d("google_analytics_automatic_screen_reporting_enabled");
        return d == null || d.booleanValue();
    }

    public final boolean zzv() {
        this.zzs.zzaw();
        Boolean d = d("firebase_analytics_collection_deactivated");
        return d != null && d.booleanValue();
    }

    public final boolean zzw(String str) {
        return "1".equals(this.b.zza(str, "measurement.event_sampling_enabled"));
    }

    @EnsuresNonNull({"this.isMainProcess"})
    public final boolean zzy() {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    ApplicationInfo applicationInfo = this.zzs.zzau().getApplicationInfo();
                    String myProcessName = ProcessUtils.getMyProcessName();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = false;
                        if (str != null && str.equals(myProcessName)) {
                            z = true;
                        }
                        this.c = Boolean.valueOf(z);
                    }
                    if (this.c == null) {
                        this.c = Boolean.TRUE;
                        this.zzs.zzay().zzd().zza("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.c.booleanValue();
    }
}
