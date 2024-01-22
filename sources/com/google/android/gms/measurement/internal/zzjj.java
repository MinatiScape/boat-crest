package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzjj extends x {
    public final zzji b;
    public zzdz c;
    public volatile Boolean d;
    public final f e;
    public final n3 f;
    public final List<Runnable> g;
    public final f h;

    public zzjj(zzfs zzfsVar) {
        super(zzfsVar);
        this.g = new ArrayList();
        this.f = new n3(zzfsVar.zzav());
        this.b = new zzji(this);
        this.e = new p2(this, zzfsVar);
        this.h = new r2(this, zzfsVar);
    }

    public static /* bridge */ /* synthetic */ void o(zzjj zzjjVar, ComponentName componentName) {
        zzjjVar.zzg();
        if (zzjjVar.c != null) {
            zzjjVar.c = null;
            zzjjVar.zzs.zzay().zzj().zzb("Disconnected from device MeasurementService", componentName);
            zzjjVar.zzg();
            zzjjVar.q();
        }
    }

    @VisibleForTesting
    @WorkerThread
    public final void b(zzdz zzdzVar, AbstractSafeParcelable abstractSafeParcelable, zzp zzpVar) {
        int i;
        zzg();
        zza();
        i();
        this.zzs.zzf();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> zzi = this.zzs.zzi().zzi(100);
            if (zzi != null) {
                arrayList.addAll(zzi);
                i = zzi.size();
            } else {
                i = 0;
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            int size = arrayList.size();
            for (int i4 = 0; i4 < size; i4++) {
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) arrayList.get(i4);
                if (abstractSafeParcelable2 instanceof zzat) {
                    try {
                        zzdzVar.zzk((zzat) abstractSafeParcelable2, zzpVar);
                    } catch (RemoteException e) {
                        this.zzs.zzay().zzd().zzb("Failed to send event to the service", e);
                    }
                } else if (abstractSafeParcelable2 instanceof zzkq) {
                    try {
                        zzdzVar.zzt((zzkq) abstractSafeParcelable2, zzpVar);
                    } catch (RemoteException e2) {
                        this.zzs.zzay().zzd().zzb("Failed to send user property to the service", e2);
                    }
                } else if (abstractSafeParcelable2 instanceof zzab) {
                    try {
                        zzdzVar.zzn((zzab) abstractSafeParcelable2, zzpVar);
                    } catch (RemoteException e3) {
                        this.zzs.zzay().zzd().zzb("Failed to send conditional user property to the service", e3);
                    }
                } else {
                    this.zzs.zzay().zzd().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
    }

    @WorkerThread
    public final boolean c() {
        zzg();
        zza();
        return !d() || this.zzs.zzv().zzm() >= zzdw.zzao.zza(null).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x012d  */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean d() {
        /*
            Method dump skipped, instructions count: 338
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzjj.d():boolean");
    }

    @WorkerThread
    public final zzp e(boolean z) {
        Pair<String, Long> zza;
        this.zzs.zzaw();
        zzea zzh = this.zzs.zzh();
        String str = null;
        if (z) {
            zzei zzay = this.zzs.zzay();
            if (zzay.zzs.zzm().c != null && (zza = zzay.zzs.zzm().c.zza()) != null && zza != v.w) {
                String valueOf = String.valueOf(zza.second);
                String str2 = (String) zza.first;
                StringBuilder sb = new StringBuilder(valueOf.length() + 1 + String.valueOf(str2).length());
                sb.append(valueOf);
                sb.append(":");
                sb.append(str2);
                str = sb.toString();
            }
        }
        return zzh.d(str);
    }

    @WorkerThread
    public final void f() {
        zzg();
        this.zzs.zzay().zzj().zzb("Processing queued up service tasks", Integer.valueOf(this.g.size()));
        for (Runnable runnable : this.g) {
            try {
                runnable.run();
            } catch (RuntimeException e) {
                this.zzs.zzay().zzd().zzb("Task exception while flushing queue", e);
            }
        }
        this.g.clear();
        this.h.b();
    }

    @WorkerThread
    public final void g() {
        zzg();
        this.f.b();
        f fVar = this.e;
        this.zzs.zzf();
        fVar.d(zzdw.zzI.zza(null).longValue());
    }

    @WorkerThread
    public final void h(Runnable runnable) throws IllegalStateException {
        zzg();
        if (zzL()) {
            runnable.run();
            return;
        }
        int size = this.g.size();
        this.zzs.zzf();
        if (size >= 1000) {
            this.zzs.zzay().zzd().zza("Discarding data. Max runnable queue size reached");
            return;
        }
        this.g.add(runnable);
        this.h.d(Constants.ONE_MIN_IN_MILLIS);
        q();
    }

    public final boolean i() {
        this.zzs.zzaw();
        return true;
    }

    public final Boolean l() {
        return this.d;
    }

    @WorkerThread
    public final void q() {
        zzg();
        zza();
        if (zzL()) {
            return;
        }
        if (!d()) {
            if (this.zzs.zzf().h()) {
                return;
            }
            this.zzs.zzaw();
            List<ResolveInfo> queryIntentServices = this.zzs.zzau().getPackageManager().queryIntentServices(new Intent().setClassName(this.zzs.zzau(), "com.google.android.gms.measurement.AppMeasurementService"), 65536);
            if (queryIntentServices != null && queryIntentServices.size() > 0) {
                Intent intent = new Intent("com.google.android.gms.measurement.START");
                Context zzau = this.zzs.zzau();
                this.zzs.zzaw();
                intent.setComponent(new ComponentName(zzau, "com.google.android.gms.measurement.AppMeasurementService"));
                this.b.zzb(intent);
                return;
            }
            this.zzs.zzay().zzd().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            return;
        }
        this.b.zzc();
    }

    @WorkerThread
    public final void zzA(zzat zzatVar, String str) {
        Preconditions.checkNotNull(zzatVar);
        zzg();
        zza();
        i();
        h(new u2(this, true, e(true), this.zzs.zzi().zzo(zzatVar), zzatVar, str));
    }

    @WorkerThread
    public final void zzB(com.google.android.gms.internal.measurement.zzcf zzcfVar, zzat zzatVar, String str) {
        zzg();
        zza();
        if (this.zzs.zzv().zzo(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) != 0) {
            this.zzs.zzay().zzk().zza("Not bundling data. Service unavailable or out of date");
            this.zzs.zzv().zzR(zzcfVar, new byte[0]);
            return;
        }
        h(new q2(this, zzatVar, str, zzcfVar));
    }

    @WorkerThread
    public final void zzC() {
        zzg();
        zza();
        zzp e = e(false);
        i();
        this.zzs.zzi().zzj();
        h(new j2(this, e));
    }

    @WorkerThread
    public final void zzE(zzab zzabVar) {
        Preconditions.checkNotNull(zzabVar);
        zzg();
        zza();
        this.zzs.zzaw();
        h(new v2(this, true, e(true), this.zzs.zzi().zzn(zzabVar), new zzab(zzabVar), zzabVar));
    }

    @WorkerThread
    public final void zzF(boolean z) {
        zzg();
        zza();
        if (z) {
            i();
            this.zzs.zzi().zzj();
        }
        if (c()) {
            h(new t2(this, e(false)));
        }
    }

    @WorkerThread
    public final void zzG(zzic zzicVar) {
        zzg();
        zza();
        h(new n2(this, zzicVar));
    }

    @WorkerThread
    public final void zzH(Bundle bundle) {
        zzg();
        zza();
        h(new o2(this, e(false), bundle));
    }

    @WorkerThread
    public final void zzI() {
        zzg();
        zza();
        h(new s2(this, e(true)));
    }

    @VisibleForTesting
    @WorkerThread
    public final void zzJ(zzdz zzdzVar) {
        zzg();
        Preconditions.checkNotNull(zzdzVar);
        this.c = zzdzVar;
        g();
        f();
    }

    @WorkerThread
    public final void zzK(zzkq zzkqVar) {
        zzg();
        zza();
        i();
        h(new h2(this, e(true), this.zzs.zzi().zzp(zzkqVar), zzkqVar));
    }

    @WorkerThread
    public final boolean zzL() {
        zzg();
        zza();
        return this.c != null;
    }

    @Override // com.google.android.gms.measurement.internal.x
    public final boolean zzf() {
        return false;
    }

    @WorkerThread
    public final void zzq() {
        zzg();
        zza();
        zzp e = e(true);
        this.zzs.zzi().zzk();
        h(new m2(this, e));
    }

    @WorkerThread
    public final void zzs() {
        zzg();
        zza();
        this.b.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzs.zzau(), this.b);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.c = null;
    }

    @WorkerThread
    public final void zzt(com.google.android.gms.internal.measurement.zzcf zzcfVar) {
        zzg();
        zza();
        h(new l2(this, e(false), zzcfVar));
    }

    @WorkerThread
    public final void zzu(AtomicReference<String> atomicReference) {
        zzg();
        zza();
        h(new k2(this, atomicReference, e(false)));
    }

    @WorkerThread
    public final void zzv(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2) {
        zzg();
        zza();
        h(new y2(this, str, str2, e(false), zzcfVar));
    }

    @WorkerThread
    public final void zzw(AtomicReference<List<zzab>> atomicReference, String str, String str2, String str3) {
        zzg();
        zza();
        h(new x2(this, atomicReference, null, str2, str3, e(false)));
    }

    @WorkerThread
    public final void zzx(AtomicReference<List<zzkq>> atomicReference, boolean z) {
        zzg();
        zza();
        h(new i2(this, atomicReference, e(false), z));
    }

    @WorkerThread
    public final void zzy(com.google.android.gms.internal.measurement.zzcf zzcfVar, String str, String str2, boolean z) {
        zzg();
        zza();
        h(new g2(this, str, str2, e(false), z, zzcfVar));
    }

    @WorkerThread
    public final void zzz(AtomicReference<List<zzkq>> atomicReference, String str, String str2, String str3, boolean z) {
        zzg();
        zza();
        h(new z2(this, atomicReference, null, str2, str3, e(false), z));
    }
}
