package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;
/* loaded from: classes10.dex */
public final class zzcc extends Thread {
    public static zzcc zza;
    public final LinkedBlockingQueue<Runnable> zzb;
    public volatile boolean zzc;
    public volatile boolean zzd;
    public volatile zzcd zze;
    public final Context zzf;

    public zzcc(Context context) {
        super("GAThread");
        this.zzb = new LinkedBlockingQueue<>();
        this.zzc = false;
        this.zzd = false;
        if (context != null) {
            this.zzf = context.getApplicationContext();
        } else {
            this.zzf = null;
        }
        start();
    }

    public static zzcc zzb(Context context) {
        if (zza == null) {
            zza = new zzcc(context);
        }
        return zza;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (true) {
            try {
                try {
                    Runnable take = this.zzb.take();
                    if (!this.zzc) {
                        take.run();
                    }
                } catch (InterruptedException e) {
                    zzdh.zzb.zzb(e.toString());
                }
            } catch (Exception e2) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(byteArrayOutputStream);
                e2.printStackTrace(printStream);
                printStream.flush();
                String str = new String(byteArrayOutputStream.toByteArray());
                zzdh.zza(str.length() != 0 ? "Error on Google TagManager Thread: ".concat(str) : new String("Error on Google TagManager Thread: "));
                zzdh.zza("Google TagManager is shutting down.");
                this.zzc = true;
            }
        }
    }

    public final void zze(Runnable runnable) {
        this.zzb.add(runnable);
    }

    @VisibleForTesting
    public final void zzf(String str, long j) {
        this.zzb.add(new zzcb(this, this, j, str, null));
    }
}
