package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
/* loaded from: classes8.dex */
public final class zzee {
    public static volatile zzee h;

    /* renamed from: a  reason: collision with root package name */
    public final String f8949a;
    public final AppMeasurementSdk b;
    @GuardedBy("listenerList")
    public final List<Pair<com.google.android.gms.measurement.internal.zzgt, t0>> c;
    public int d;
    public boolean e;
    public final String f;
    public volatile zzcc g;
    public final Clock zza;
    public final ExecutorService zzb;

    public zzee(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str != null && f(str2, str3)) {
            this.f8949a = str;
        } else {
            this.f8949a = "FA";
        }
        this.zza = DefaultClock.getInstance();
        this.zzb = zzbx.zza().zzb(new g0(this), 1);
        this.b = new AppMeasurementSdk(this);
        this.c = new ArrayList();
        try {
            if (com.google.android.gms.measurement.internal.zzib.zzc(context, "google_app_id", com.google.android.gms.measurement.internal.zzfk.zza(context)) != null && !zzR()) {
                this.f = null;
                this.e = true;
                Log.w(this.f8949a, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
                return;
            }
        } catch (IllegalStateException unused) {
        }
        if (f(str2, str3)) {
            this.f = str2;
        } else {
            this.f = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 == null)) {
                    Log.w(this.f8949a, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.f8949a, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
            }
        }
        e(new v(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.f8949a, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new b1(this));
        }
    }

    public static final boolean f(String str, String str2) {
        return (str2 == null || str == null || zzR()) ? false : true;
    }

    public static final boolean zzR() {
        return true;
    }

    public static zzee zzg(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (h == null) {
            synchronized (zzee.class) {
                if (h == null) {
                    h = new zzee(context, str, str2, str3, bundle);
                }
            }
        }
        return h;
    }

    public final void c(Exception exc, boolean z, boolean z2) {
        this.e |= z;
        if (z) {
            Log.w(this.f8949a, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zzA(5, "Error with data collection. Data lost.", exc, null, null);
        }
        Log.w(this.f8949a, "Error with data collection. Data lost.", exc);
    }

    public final void d(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        e(new p0(this, l, str, str2, bundle, z, z2));
    }

    public final void e(r0 r0Var) {
        this.zzb.execute(r0Var);
    }

    public final void zzA(int i, String str, Object obj, Object obj2, Object obj3) {
        e(new e0(this, false, 5, str, obj, null, null));
    }

    public final void zzB(com.google.android.gms.measurement.internal.zzgt zzgtVar) {
        Preconditions.checkNotNull(zzgtVar);
        synchronized (this.c) {
            for (int i = 0; i < this.c.size(); i++) {
                if (zzgtVar.equals(this.c.get(i).first)) {
                    Log.w(this.f8949a, "OnEventListener already registered.");
                    return;
                }
            }
            t0 t0Var = new t0(zzgtVar);
            this.c.add(new Pair<>(zzgtVar, t0Var));
            if (this.g != null) {
                try {
                    this.g.registerOnMeasurementEventListener(t0Var);
                    return;
                } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                    Log.w(this.f8949a, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
                }
            }
            e(new n0(this, t0Var));
        }
    }

    public final void zzC() {
        e(new t(this));
    }

    public final void zzD(Bundle bundle) {
        e(new l(this, bundle));
    }

    public final void zzE(Bundle bundle) {
        e(new r(this, bundle));
    }

    public final void zzF(Bundle bundle) {
        e(new s(this, bundle));
    }

    public final void zzG(Activity activity, String str, String str2) {
        e(new p(this, activity, str, str2));
    }

    public final void zzH(boolean z) {
        e(new k0(this, z));
    }

    public final void zzI(Bundle bundle) {
        e(new l0(this, bundle));
    }

    public final void zzJ(com.google.android.gms.measurement.internal.zzgs zzgsVar) {
        s0 s0Var = new s0(zzgsVar);
        if (this.g != null) {
            try {
                this.g.setEventInterceptor(s0Var);
                return;
            } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                Log.w(this.f8949a, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        e(new m0(this, s0Var));
    }

    public final void zzK(Boolean bool) {
        e(new q(this, bool));
    }

    public final void zzL(long j) {
        e(new u(this, j));
    }

    public final void zzM(String str) {
        e(new o(this, str));
    }

    public final void zzN(String str, String str2, Object obj, boolean z) {
        e(new q0(this, str, str2, obj, z));
    }

    public final void zzO(com.google.android.gms.measurement.internal.zzgt zzgtVar) {
        Pair<com.google.android.gms.measurement.internal.zzgt, t0> pair;
        Preconditions.checkNotNull(zzgtVar);
        synchronized (this.c) {
            int i = 0;
            while (true) {
                if (i >= this.c.size()) {
                    pair = null;
                    break;
                } else if (zzgtVar.equals(this.c.get(i).first)) {
                    pair = this.c.get(i);
                    break;
                } else {
                    i++;
                }
            }
            if (pair == null) {
                Log.w(this.f8949a, "OnEventListener had not been registered.");
                return;
            }
            this.c.remove(pair);
            t0 t0Var = (t0) pair.second;
            if (this.g != null) {
                try {
                    this.g.unregisterOnMeasurementEventListener(t0Var);
                    return;
                } catch (BadParcelableException | NetworkOnMainThreadException | RemoteException | IllegalArgumentException | IllegalStateException | NullPointerException | SecurityException | UnsupportedOperationException unused) {
                    Log.w(this.f8949a, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
                }
            }
            e(new o0(this, t0Var));
        }
    }

    public final int zza(String str) {
        zzbz zzbzVar = new zzbz();
        e(new h0(this, str, zzbzVar));
        Integer num = (Integer) zzbz.zze(zzbzVar.zzb(10000L), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final long zzb() {
        zzbz zzbzVar = new zzbz();
        e(new a0(this, zzbzVar));
        Long l = (Long) zzbz.zze(zzbzVar.zzb(500L), Long.class);
        if (l == null) {
            long nextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
            int i = this.d + 1;
            this.d = i;
            return nextLong + i;
        }
        return l.longValue();
    }

    public final Bundle zzc(Bundle bundle, boolean z) {
        zzbz zzbzVar = new zzbz();
        e(new f0(this, bundle, zzbzVar));
        if (z) {
            return zzbzVar.zzb(5000L);
        }
        return null;
    }

    public final AppMeasurementSdk zzd() {
        return this.b;
    }

    public final zzcc zzf(Context context, boolean z) {
        try {
            return zzcb.asInterface(DynamiteModule.load(context, DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e) {
            c(e, true, false);
            return null;
        }
    }

    public final Object zzh(int i) {
        zzbz zzbzVar = new zzbz();
        e(new j0(this, zzbzVar, i));
        return zzbz.zze(zzbzVar.zzb(15000L), Object.class);
    }

    public final String zzj() {
        return this.f;
    }

    @WorkerThread
    public final String zzk() {
        zzbz zzbzVar = new zzbz();
        e(new i0(this, zzbzVar));
        return zzbzVar.zzc(120000L);
    }

    public final String zzl() {
        zzbz zzbzVar = new zzbz();
        e(new z(this, zzbzVar));
        return zzbzVar.zzc(50L);
    }

    public final String zzm() {
        zzbz zzbzVar = new zzbz();
        e(new c0(this, zzbzVar));
        return zzbzVar.zzc(500L);
    }

    public final String zzn() {
        zzbz zzbzVar = new zzbz();
        e(new b0(this, zzbzVar));
        return zzbzVar.zzc(500L);
    }

    public final String zzo() {
        zzbz zzbzVar = new zzbz();
        e(new y(this, zzbzVar));
        return zzbzVar.zzc(500L);
    }

    public final List<Bundle> zzp(String str, String str2) {
        zzbz zzbzVar = new zzbz();
        e(new n(this, str, str2, zzbzVar));
        List<Bundle> list = (List) zzbz.zze(zzbzVar.zzb(5000L), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final Map<String, Object> zzq(String str, String str2, boolean z) {
        zzbz zzbzVar = new zzbz();
        e(new d0(this, str, str2, z, zzbzVar));
        Bundle zzb = zzbzVar.zzb(5000L);
        if (zzb != null && zzb.size() != 0) {
            HashMap hashMap = new HashMap(zzb.size());
            for (String str3 : zzb.keySet()) {
                Object obj = zzb.get(str3);
                if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                    hashMap.put(str3, obj);
                }
            }
            return hashMap;
        }
        return Collections.emptyMap();
    }

    public final void zzu(String str) {
        e(new w(this, str));
    }

    public final void zzv(String str, String str2, Bundle bundle) {
        e(new m(this, str, str2, bundle));
    }

    public final void zzw(String str) {
        e(new x(this, str));
    }

    public final void zzx(@NonNull String str, Bundle bundle) {
        d(null, str, bundle, false, true, null);
    }

    public final void zzy(String str, String str2, Bundle bundle) {
        d(str, str2, bundle, true, true, null);
    }

    public final void zzz(String str, String str2, Bundle bundle, long j) {
        d(str, str2, bundle, true, false, Long.valueOf(j));
    }
}
