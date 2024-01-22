package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.GuardedBy;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes10.dex */
public final class zzij extends x {
    public volatile zzic b;
    public volatile zzic c;
    public final Map<Activity, zzic> d;
    @GuardedBy("activityLock")
    public Activity e;
    @GuardedBy("activityLock")
    public volatile boolean f;
    public volatile zzic g;
    public zzic h;
    @GuardedBy("activityLock")
    public boolean i;
    public final Object j;
    @GuardedBy("this")
    public String k;
    @VisibleForTesting
    public zzic zza;

    public zzij(zzfs zzfsVar) {
        super(zzfsVar);
        this.j = new Object();
        this.d = new ConcurrentHashMap();
    }

    public static /* bridge */ /* synthetic */ void i(zzij zzijVar, Bundle bundle, zzic zzicVar, zzic zzicVar2, long j) {
        bundle.remove(FirebaseAnalytics.Param.SCREEN_NAME);
        bundle.remove(FirebaseAnalytics.Param.SCREEN_CLASS);
        zzijVar.c(zzicVar, zzicVar2, j, true, zzijVar.zzs.zzv().R(null, FirebaseAnalytics.Event.SCREEN_VIEW, bundle, null, false));
    }

    @MainThread
    public final void b(Activity activity, zzic zzicVar, boolean z) {
        zzic zzicVar2;
        zzic zzicVar3 = this.b == null ? this.c : this.b;
        if (zzicVar.zzb == null) {
            zzicVar2 = new zzic(zzicVar.zza, activity != null ? f(activity.getClass(), "Activity") : null, zzicVar.zzc, zzicVar.zze, zzicVar.zzf);
        } else {
            zzicVar2 = zzicVar;
        }
        this.c = this.b;
        this.b = zzicVar2;
        this.zzs.zzaz().zzp(new b2(this, zzicVar2, zzicVar3, this.zzs.zzav().elapsedRealtime(), z));
    }

    @WorkerThread
    public final void c(zzic zzicVar, zzic zzicVar2, long j, boolean z, Bundle bundle) {
        Bundle bundle2;
        long j2;
        long j3;
        zzg();
        boolean z2 = false;
        boolean z3 = (zzicVar2 != null && zzicVar2.zzc == zzicVar.zzc && zzku.B(zzicVar2.zzb, zzicVar.zzb) && zzku.B(zzicVar2.zza, zzicVar.zza)) ? false : true;
        if (z && this.zza != null) {
            z2 = true;
        }
        if (z3) {
            if (bundle != null) {
                bundle2 = new Bundle(bundle);
            } else {
                bundle2 = new Bundle();
            }
            Bundle bundle3 = bundle2;
            zzku.zzJ(zzicVar, bundle3, true);
            if (zzicVar2 != null) {
                String str = zzicVar2.zza;
                if (str != null) {
                    bundle3.putString("_pn", str);
                }
                String str2 = zzicVar2.zzb;
                if (str2 != null) {
                    bundle3.putString("_pc", str2);
                }
                bundle3.putLong("_pi", zzicVar2.zzc);
            }
            if (z2) {
                l3 l3Var = this.zzs.zzu().zzb;
                long j4 = j - l3Var.b;
                l3Var.b = j;
                if (j4 > 0) {
                    this.zzs.zzv().f(bundle3, j4);
                }
            }
            if (!this.zzs.zzf().zzu()) {
                bundle3.putLong("_mst", 1L);
            }
            String str3 = true != zzicVar.zze ? "auto" : "app";
            long currentTimeMillis = this.zzs.zzav().currentTimeMillis();
            if (zzicVar.zze) {
                j2 = currentTimeMillis;
                long j5 = zzicVar.zzf;
                if (j5 != 0) {
                    j3 = j5;
                    this.zzs.zzq().c(str3, "_vs", j3, bundle3);
                }
            } else {
                j2 = currentTimeMillis;
            }
            j3 = j2;
            this.zzs.zzq().c(str3, "_vs", j3, bundle3);
        }
        if (z2) {
            d(this.zza, true, j);
        }
        this.zza = zzicVar;
        if (zzicVar.zze) {
            this.h = zzicVar;
        }
        this.zzs.zzt().zzG(zzicVar);
    }

    @WorkerThread
    public final void d(zzic zzicVar, boolean z, long j) {
        this.zzs.zzd().zzf(this.zzs.zzav().elapsedRealtime());
        if (!this.zzs.zzu().zzb.d(zzicVar != null && zzicVar.f10157a, z, j) || zzicVar == null) {
            return;
        }
        zzicVar.f10157a = false;
    }

    @VisibleForTesting
    public final String f(Class<?> cls, String str) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            return "Activity";
        }
        String[] split = canonicalName.split("\\.");
        int length = split.length;
        String str2 = length > 0 ? split[length - 1] : "";
        int length2 = str2.length();
        this.zzs.zzf();
        if (length2 > 100) {
            this.zzs.zzf();
            return str2.substring(0, 100);
        }
        return str2;
    }

    @MainThread
    public final zzic k(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);
        zzic zzicVar = this.d.get(activity);
        if (zzicVar == null) {
            zzic zzicVar2 = new zzic(null, f(activity.getClass(), "Activity"), this.zzs.zzv().zzq());
            this.d.put(activity, zzicVar2);
            zzicVar = zzicVar2;
        }
        return this.g != null ? this.g : zzicVar;
    }

    @Override // com.google.android.gms.measurement.internal.x
    public final boolean zzf() {
        return false;
    }

    public final zzic zzi() {
        return this.b;
    }

    @WorkerThread
    public final zzic zzj(boolean z) {
        zza();
        zzg();
        if (z) {
            zzic zzicVar = this.zza;
            return zzicVar != null ? zzicVar : this.h;
        }
        return this.zza;
    }

    @MainThread
    public final void zzr(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (!this.zzs.zzf().zzu() || bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.d.put(activity, new zzic(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle2.getString("referrer_name"), bundle2.getLong("id")));
    }

    @MainThread
    public final void zzs(Activity activity) {
        synchronized (this.j) {
            if (activity == this.e) {
                this.e = null;
            }
        }
        if (this.zzs.zzf().zzu()) {
            this.d.remove(activity);
        }
    }

    @MainThread
    public final void zzt(Activity activity) {
        synchronized (this.j) {
            this.i = false;
            this.f = true;
        }
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
        if (!this.zzs.zzf().zzu()) {
            this.b = null;
            this.zzs.zzaz().zzp(new d2(this, elapsedRealtime));
            return;
        }
        zzic k = k(activity);
        this.c = this.b;
        this.b = null;
        this.zzs.zzaz().zzp(new e2(this, k, elapsedRealtime));
    }

    @MainThread
    public final void zzu(Activity activity) {
        synchronized (this.j) {
            this.i = true;
            if (activity != this.e) {
                synchronized (this.j) {
                    this.e = activity;
                    this.f = false;
                }
                if (this.zzs.zzf().zzu()) {
                    this.g = null;
                    this.zzs.zzaz().zzp(new f2(this));
                }
            }
        }
        if (!this.zzs.zzf().zzu()) {
            this.b = this.g;
            this.zzs.zzaz().zzp(new c2(this));
            return;
        }
        b(activity, k(activity), false);
        zzd zzd = this.zzs.zzd();
        zzd.zzs.zzaz().zzp(new j(zzd, zzd.zzs.zzav().elapsedRealtime()));
    }

    @MainThread
    public final void zzv(Activity activity, Bundle bundle) {
        zzic zzicVar;
        if (!this.zzs.zzf().zzu() || bundle == null || (zzicVar = this.d.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", zzicVar.zzc);
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzicVar.zza);
        bundle2.putString("referrer_name", zzicVar.zzb);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
        if (r1 <= 100) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b4, code lost:
        if (r1 <= 100) goto L36;
     */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzw(@androidx.annotation.NonNull android.app.Activity r4, @androidx.annotation.Size(max = 36, min = 1) java.lang.String r5, @androidx.annotation.Size(max = 36, min = 1) java.lang.String r6) {
        /*
            r3 = this;
            com.google.android.gms.measurement.internal.zzfs r0 = r3.zzs
            com.google.android.gms.measurement.internal.zzaf r0 = r0.zzf()
            boolean r0 = r0.zzu()
            if (r0 != 0) goto L1c
            com.google.android.gms.measurement.internal.zzfs r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeg r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen cannot be called while screen reporting is disabled."
            r4.zza(r5)
            return
        L1c:
            com.google.android.gms.measurement.internal.zzic r0 = r3.b
            if (r0 != 0) goto L30
            com.google.android.gms.measurement.internal.zzfs r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeg r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen cannot be called while no activity active"
            r4.zza(r5)
            return
        L30:
            java.util.Map<android.app.Activity, com.google.android.gms.measurement.internal.zzic> r1 = r3.d
            java.lang.Object r1 = r1.get(r4)
            if (r1 != 0) goto L48
            com.google.android.gms.measurement.internal.zzfs r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeg r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen must be called with an activity in the activity lifecycle"
            r4.zza(r5)
            return
        L48:
            if (r6 != 0) goto L54
            java.lang.Class r6 = r4.getClass()
            java.lang.String r1 = "Activity"
            java.lang.String r6 = r3.f(r6, r1)
        L54:
            java.lang.String r1 = r0.zzb
            boolean r1 = com.google.android.gms.measurement.internal.zzku.B(r1, r6)
            java.lang.String r0 = r0.zza
            boolean r0 = com.google.android.gms.measurement.internal.zzku.B(r0, r5)
            if (r1 == 0) goto L75
            if (r0 != 0) goto L65
            goto L75
        L65:
            com.google.android.gms.measurement.internal.zzfs r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeg r4 = r4.zzl()
            java.lang.String r5 = "setCurrentScreen cannot be called with the same class and name"
            r4.zza(r5)
            return
        L75:
            r0 = 100
            if (r5 == 0) goto La3
            int r1 = r5.length()
            if (r1 <= 0) goto L8b
            int r1 = r5.length()
            com.google.android.gms.measurement.internal.zzfs r2 = r3.zzs
            r2.zzf()
            if (r1 > r0) goto L8b
            goto La3
        L8b:
            com.google.android.gms.measurement.internal.zzfs r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeg r4 = r4.zzl()
            int r5 = r5.length()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r6 = "Invalid screen name length in setCurrentScreen. Length"
            r4.zzb(r6, r5)
            return
        La3:
            if (r6 == 0) goto Lcf
            int r1 = r6.length()
            if (r1 <= 0) goto Lb7
            int r1 = r6.length()
            com.google.android.gms.measurement.internal.zzfs r2 = r3.zzs
            r2.zzf()
            if (r1 > r0) goto Lb7
            goto Lcf
        Lb7:
            com.google.android.gms.measurement.internal.zzfs r4 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r4 = r4.zzay()
            com.google.android.gms.measurement.internal.zzeg r4 = r4.zzl()
            int r5 = r6.length()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r6 = "Invalid class name length in setCurrentScreen. Length"
            r4.zzb(r6, r5)
            return
        Lcf:
            com.google.android.gms.measurement.internal.zzfs r0 = r3.zzs
            com.google.android.gms.measurement.internal.zzei r0 = r0.zzay()
            com.google.android.gms.measurement.internal.zzeg r0 = r0.zzj()
            if (r5 != 0) goto Lde
            java.lang.String r1 = "null"
            goto Ldf
        Lde:
            r1 = r5
        Ldf:
            java.lang.String r2 = "Setting current screen to name, class"
            r0.zzc(r2, r1, r6)
            com.google.android.gms.measurement.internal.zzic r0 = new com.google.android.gms.measurement.internal.zzic
            com.google.android.gms.measurement.internal.zzfs r1 = r3.zzs
            com.google.android.gms.measurement.internal.zzku r1 = r1.zzv()
            long r1 = r1.zzq()
            r0.<init>(r5, r6, r1)
            java.util.Map<android.app.Activity, com.google.android.gms.measurement.internal.zzic> r5 = r3.d
            r5.put(r4, r0)
            r5 = 1
            r3.b(r4, r0, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzij.zzw(android.app.Activity, java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0031, code lost:
        if (r2 > 100) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0063, code lost:
        if (r4 > 100) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzx(android.os.Bundle r13, long r14) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzij.zzx(android.os.Bundle, long):void");
    }

    @WorkerThread
    public final void zzy(String str, zzic zzicVar) {
        zzg();
        synchronized (this) {
            String str2 = this.k;
            if (str2 == null || str2.equals(str) || zzicVar != null) {
                this.k = str;
            }
        }
    }
}
