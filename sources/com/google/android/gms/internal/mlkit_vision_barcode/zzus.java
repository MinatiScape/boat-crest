package com.google.android.gms.internal.mlkit_vision_barcode;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import com.google.mlkit.vision.barcode.ZoomSuggestionOptions;
import com.google.mlkit.vision.barcode.internal.BarcodeScannerImpl;
import com.google.mlkit.vision.barcode.internal.zzf;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
/* loaded from: classes9.dex */
public final class zzus {
    public static final GmsLogger s = new GmsLogger("AutoZoom");
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final zzuu f9582a;
    public final AtomicBoolean b;
    public final Object c;
    @VisibleForTesting
    public final zzbz d;
    public final ScheduledExecutorService e;
    public final zzbf f;
    public final zztx g;
    public final String h;
    public Executor i;
    public float j;
    public float k;
    public long l;
    public long m;
    @Nullable
    @VisibleForTesting
    public ScheduledFuture n;
    @Nullable
    @VisibleForTesting
    public String o;
    public boolean p;
    @VisibleForTesting
    public int q;
    public zzf r;

    public zzus(Context context, zzuu zzuuVar, String str) {
        zzg.zza();
        ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(2));
        zzbf zza = zzar.zza();
        zztx zztxVar = new zztx(context, new SharedPrefManager(context), new zztq(context, zztp.zzd("scanner-auto-zoom").zzd()), "scanner-auto-zoom");
        this.c = new Object();
        this.f9582a = zzuuVar;
        this.b = new AtomicBoolean(false);
        this.d = zzbz.zzz();
        this.e = unconfigurableScheduledExecutorService;
        this.f = zza;
        this.g = zztxVar;
        this.h = str;
        this.q = 1;
        this.j = 1.0f;
        this.k = -1.0f;
        this.l = zza.zza();
    }

    public static /* bridge */ /* synthetic */ void d(zzus zzusVar, float f) {
        synchronized (zzusVar.c) {
            zzusVar.j = f;
            zzusVar.i(false);
        }
    }

    public static zzus zzd(Context context, String str) {
        return new zzus(context, zzuu.zzb, str);
    }

    public static /* synthetic */ void zzf(zzus zzusVar) {
        ScheduledFuture scheduledFuture;
        synchronized (zzusVar.c) {
            if (zzusVar.q == 2 && !zzusVar.b.get() && (scheduledFuture = zzusVar.n) != null && !scheduledFuture.isCancelled()) {
                if (zzusVar.j > 1.0f && zzusVar.zza() >= zzusVar.f9582a.i()) {
                    s.i("AutoZoom", "Reset zoom = 1");
                    zzusVar.f(1.0f, zzpk.SCANNER_AUTO_ZOOM_AUTO_RESET, null);
                }
            }
        }
    }

    public final /* synthetic */ zzev b(float f) throws Exception {
        zzf zzfVar = this.r;
        float g = g(f);
        ZoomSuggestionOptions zoomSuggestionOptions = zzfVar.zza;
        int i = BarcodeScannerImpl.zzc;
        if (true != zoomSuggestionOptions.zzb().setZoom(g)) {
            g = 0.0f;
        }
        return zzem.zza(Float.valueOf(g));
    }

    @VisibleForTesting
    public final void f(float f, zzpk zzpkVar, @Nullable zzuv zzuvVar) {
        synchronized (this.c) {
            if (this.i != null && this.r != null && this.q == 2) {
                if (this.b.compareAndSet(false, true)) {
                    zzem.zzb(zzem.zzc(new zzup(this, f), this.i), new v8(this, zzpkVar, this.j, zzuvVar, f), zzew.zza());
                }
            }
        }
    }

    public final float g(float f) {
        int i = (f > 1.0f ? 1 : (f == 1.0f ? 0 : -1));
        float f2 = this.k;
        if (i < 0) {
            f = 1.0f;
        }
        return (f2 <= 0.0f || f <= f2) ? f : f2;
    }

    public final void h(zzpk zzpkVar, float f, float f2, @Nullable zzuv zzuvVar) {
        long convert;
        if (this.o != null) {
            zzsb zzsbVar = new zzsb();
            zzsbVar.zza(this.h);
            String str = this.o;
            Objects.requireNonNull(str);
            zzsbVar.zze(str);
            zzsbVar.zzf(Float.valueOf(f));
            zzsbVar.zzc(Float.valueOf(f2));
            synchronized (this.c) {
                convert = TimeUnit.MILLISECONDS.convert(this.f.zza() - this.m, TimeUnit.NANOSECONDS);
            }
            zzsbVar.zzb(Long.valueOf(convert));
            if (zzuvVar != null) {
                zzsc zzscVar = new zzsc();
                zzscVar.zzc(Float.valueOf(zzuvVar.c()));
                zzscVar.zze(Float.valueOf(zzuvVar.e()));
                zzscVar.zzb(Float.valueOf(zzuvVar.b()));
                zzscVar.zzd(Float.valueOf(zzuvVar.d()));
                zzscVar.zza(Float.valueOf(0.0f));
                zzsbVar.zzd(zzscVar.zzf());
            }
            zztx zztxVar = this.g;
            zzpl zzplVar = new zzpl();
            zzplVar.zzi(zzsbVar.zzh());
            zztxVar.zzd(zzua.zzf(zzplVar), zzpkVar);
        }
    }

    public final void i(boolean z) {
        ScheduledFuture scheduledFuture;
        synchronized (this.c) {
            this.d.zzs();
            this.l = this.f.zza();
            if (z && (scheduledFuture = this.n) != null) {
                scheduledFuture.cancel(false);
                this.n = null;
            }
        }
    }

    @VisibleForTesting
    public final long zza() {
        long convert;
        synchronized (this.c) {
            convert = TimeUnit.MILLISECONDS.convert(this.f.zza() - this.l, TimeUnit.NANOSECONDS);
        }
        return convert;
    }

    /* JADX WARN: Removed duplicated region for block: B:88:0x015d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00df A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void zzi(int r13, com.google.android.gms.internal.mlkit_vision_barcode.zzuv r14) {
        /*
            Method dump skipped, instructions count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.mlkit_vision_barcode.zzus.zzi(int, com.google.android.gms.internal.mlkit_vision_barcode.zzuv):void");
    }

    public final void zzj() {
        synchronized (this.c) {
            if (this.q == 4) {
                return;
            }
            zzn(false);
            this.e.shutdown();
            this.q = 4;
        }
    }

    public final void zzk(float f) {
        synchronized (this.c) {
            zzbc.zzc(f >= 1.0f);
            this.k = f;
        }
    }

    public final void zzm() {
        synchronized (this.c) {
            int i = this.q;
            if (i != 2 && i != 4) {
                i(true);
                this.n = this.e.scheduleWithFixedDelay(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzuq
                    @Override // java.lang.Runnable
                    public final void run() {
                        zzus.zzf(zzus.this);
                    }
                }, 500L, 500L, TimeUnit.MILLISECONDS);
                if (this.q == 1) {
                    this.o = UUID.randomUUID().toString();
                    this.m = this.f.zza();
                    this.p = false;
                    zzpk zzpkVar = zzpk.SCANNER_AUTO_ZOOM_START;
                    float f = this.j;
                    h(zzpkVar, f, f, null);
                } else {
                    zzpk zzpkVar2 = zzpk.SCANNER_AUTO_ZOOM_RESUME;
                    float f2 = this.j;
                    h(zzpkVar2, f2, f2, null);
                }
                this.q = 2;
            }
        }
    }

    public final void zzn(boolean z) {
        synchronized (this.c) {
            int i = this.q;
            if (i != 1 && i != 4) {
                i(true);
                if (z) {
                    if (!this.p) {
                        zzpk zzpkVar = zzpk.SCANNER_AUTO_ZOOM_FIRST_ATTEMPT;
                        float f = this.j;
                        h(zzpkVar, f, f, null);
                    }
                    zzpk zzpkVar2 = zzpk.SCANNER_AUTO_ZOOM_SCAN_SUCCESS;
                    float f2 = this.j;
                    h(zzpkVar2, f2, f2, null);
                } else {
                    zzpk zzpkVar3 = zzpk.SCANNER_AUTO_ZOOM_SCAN_FAILED;
                    float f3 = this.j;
                    h(zzpkVar3, f3, f3, null);
                }
                this.p = false;
                this.q = 1;
                this.o = null;
            }
        }
    }

    public final void zzo(zzf zzfVar, Executor executor) {
        this.r = zzfVar;
        this.i = executor;
    }
}
