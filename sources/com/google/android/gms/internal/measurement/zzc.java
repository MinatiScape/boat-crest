package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class zzc {

    /* renamed from: a  reason: collision with root package name */
    public final zzf f8948a;
    public zzg b;
    public final zzab c;
    public final zzz d;

    public zzc() {
        zzf zzfVar = new zzf();
        this.f8948a = zzfVar;
        this.b = zzfVar.b.zza();
        this.c = new zzab();
        this.d = new zzz();
        zzfVar.d.zza("internal.registerCallback", new Callable() { // from class: com.google.android.gms.internal.measurement.zza
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzc.this.zzb();
            }
        });
        zzfVar.d.zza("internal.eventLogger", new Callable() { // from class: com.google.android.gms.internal.measurement.zzb
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return new zzk(zzc.this.c);
            }
        });
    }

    public final zzab zza() {
        return this.c;
    }

    public final /* synthetic */ zzai zzb() throws Exception {
        return new zzv(this.d);
    }

    public final void zzc(zzgo zzgoVar) throws zzd {
        zzai zzaiVar;
        try {
            this.b = this.f8948a.b.zza();
            if (!(this.f8948a.zza(this.b, (zzgt[]) zzgoVar.zzc().toArray(new zzgt[0])) instanceof zzag)) {
                for (zzgm zzgmVar : zzgoVar.zza().zzd()) {
                    List<zzgt> zzc = zzgmVar.zzc();
                    String zzb = zzgmVar.zzb();
                    Iterator<zzgt> it = zzc.iterator();
                    while (it.hasNext()) {
                        zzap zza = this.f8948a.zza(this.b, it.next());
                        if (zza instanceof zzam) {
                            zzg zzgVar = this.b;
                            if (zzgVar.zzh(zzb)) {
                                zzap zzd = zzgVar.zzd(zzb);
                                if (!(zzd instanceof zzai)) {
                                    String valueOf = String.valueOf(zzb);
                                    throw new IllegalStateException(valueOf.length() != 0 ? "Invalid function name: ".concat(valueOf) : new String("Invalid function name: "));
                                }
                                zzaiVar = (zzai) zzd;
                            } else {
                                zzaiVar = null;
                            }
                            if (zzaiVar == null) {
                                String valueOf2 = String.valueOf(zzb);
                                throw new IllegalStateException(valueOf2.length() != 0 ? "Rule function is undefined: ".concat(valueOf2) : new String("Rule function is undefined: "));
                            }
                            zzaiVar.zza(this.b, Collections.singletonList(zza));
                        } else {
                            throw new IllegalArgumentException("Invalid rule definition");
                        }
                    }
                }
                return;
            }
            throw new IllegalStateException("Program loading failed");
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final void zzd(String str, Callable<? extends zzai> callable) {
        this.f8948a.d.zza(str, callable);
    }

    public final boolean zze(zzaa zzaaVar) throws zzd {
        try {
            this.c.zzd(zzaaVar);
            this.f8948a.c.zzg("runtime.counter", new zzah(Double.valueOf(0.0d)));
            this.d.zzb(this.b.zza(), this.c);
            if (zzg()) {
                return true;
            }
            return zzf();
        } catch (Throwable th) {
            throw new zzd(th);
        }
    }

    public final boolean zzf() {
        return !this.c.zzc().isEmpty();
    }

    public final boolean zzg() {
        return !this.c.zzb().equals(this.c.zza());
    }
}
