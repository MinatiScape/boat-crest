package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
/* loaded from: classes9.dex */
public final class t1 implements Runnable {
    public final Future h;
    public final zzek i;

    public t1(Future future, zzek zzekVar) {
        this.h = future;
        this.i = zzekVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        Throwable zza = zzfa.zza((zzez) this.h);
        if (zza == null) {
            try {
                Future future = this.h;
                boolean z = false;
                if (future.isDone()) {
                    while (true) {
                        try {
                            obj = future.get();
                            break;
                        } catch (InterruptedException unused) {
                            z = true;
                        } catch (Throwable th) {
                            if (z) {
                                Thread.currentThread().interrupt();
                            }
                            throw th;
                        }
                    }
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    this.i.zzb(obj);
                    return;
                }
                throw new IllegalStateException(zzbd.zzb("Future was expected to be done: %s", future));
            } catch (Error e) {
                e = e;
                this.i.zza(e);
                return;
            } catch (RuntimeException e2) {
                e = e2;
                this.i.zza(e);
                return;
            } catch (ExecutionException e3) {
                this.i.zza(e3.getCause());
                return;
            }
        }
        this.i.zza(zza);
    }

    public final String toString() {
        zzaw zza = zzax.zza(this);
        zza.zza(this.i);
        return zza.toString();
    }
}
