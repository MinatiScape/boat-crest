package com.google.mlkit.vision.barcode.internal;

import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_barcode.zzcs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfs;
import com.google.android.gms.internal.mlkit_vision_barcode.zzft;
import com.google.android.gms.internal.mlkit_vision_barcode.zzfv;
import com.google.android.gms.internal.mlkit_vision_barcode.zzol;
import com.google.android.gms.internal.mlkit_vision_barcode.zzop;
import com.google.android.gms.internal.mlkit_vision_barcode.zzoq;
import com.google.android.gms.internal.mlkit_vision_barcode.zzow;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpi;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpj;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpk;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpl;
import com.google.android.gms.internal.mlkit_vision_barcode.zzpx;
import com.google.android.gms.internal.mlkit_vision_barcode.zztm;
import com.google.android.gms.internal.mlkit_vision_barcode.zztw;
import com.google.android.gms.internal.mlkit_vision_barcode.zztx;
import com.google.android.gms.internal.mlkit_vision_barcode.zztz;
import com.google.android.gms.internal.mlkit_vision_barcode.zzua;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.vision.barcode.BarcodeScannerOptions;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.BitmapInStreamingChecker;
import com.google.mlkit.vision.common.internal.ImageUtils;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzk extends MLTask {
    public static final ImageUtils i = ImageUtils.getInstance();
    @VisibleForTesting
    public static boolean j = true;
    public final BarcodeScannerOptions c;
    public final a d;
    public final zztx e;
    public final zztz f;
    public final BitmapInStreamingChecker g = new BitmapInStreamingChecker();
    public boolean h;

    public zzk(MlKitContext mlKitContext, BarcodeScannerOptions barcodeScannerOptions, a aVar, zztx zztxVar) {
        Preconditions.checkNotNull(mlKitContext, "MlKitContext can not be null");
        Preconditions.checkNotNull(barcodeScannerOptions, "BarcodeScannerOptions can not be null");
        this.c = barcodeScannerOptions;
        this.d = aVar;
        this.e = zztxVar;
        this.f = zztz.zza(mlKitContext.getApplicationContext());
    }

    public final /* synthetic */ zztm c(long j2, zzpj zzpjVar, zzcs zzcsVar, zzcs zzcsVar2, InputImage inputImage) {
        zzoq zzoqVar;
        zzpx zzpxVar = new zzpx();
        zzow zzowVar = new zzow();
        zzowVar.zzc(Long.valueOf(j2));
        zzowVar.zzd(zzpjVar);
        zzowVar.zze(Boolean.valueOf(j));
        Boolean bool = Boolean.TRUE;
        zzowVar.zza(bool);
        zzowVar.zzb(bool);
        zzpxVar.zzh(zzowVar.zzf());
        zzpxVar.zzi(zzb.zzc(this.c));
        zzpxVar.zze(zzcsVar.zzf());
        zzpxVar.zzf(zzcsVar2.zzf());
        int format = inputImage.getFormat();
        int mobileVisionImageSize = i.getMobileVisionImageSize(inputImage);
        zzop zzopVar = new zzop();
        if (format == -1) {
            zzoqVar = zzoq.BITMAP;
        } else if (format == 35) {
            zzoqVar = zzoq.YUV_420_888;
        } else if (format == 842094169) {
            zzoqVar = zzoq.YV12;
        } else if (format == 16) {
            zzoqVar = zzoq.NV16;
        } else if (format != 17) {
            zzoqVar = zzoq.UNKNOWN_FORMAT;
        } else {
            zzoqVar = zzoq.NV21;
        }
        zzopVar.zza(zzoqVar);
        zzopVar.zzb(Integer.valueOf(mobileVisionImageSize));
        zzpxVar.zzg(zzopVar.zzd());
        zzpl zzplVar = new zzpl();
        zzplVar.zze(this.h ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzplVar.zzg(zzpxVar.zzj());
        return zzua.zzf(zzplVar);
    }

    public final /* synthetic */ zztm d(zzfv zzfvVar, int i2, zzol zzolVar) {
        zzpl zzplVar = new zzpl();
        zzplVar.zze(this.h ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzfs zzfsVar = new zzfs();
        zzfsVar.zza(Integer.valueOf(i2));
        zzfsVar.zzc(zzfvVar);
        zzfsVar.zzb(zzolVar);
        zzplVar.zzd(zzfsVar.zze());
        return zzua.zzf(zzplVar);
    }

    @WorkerThread
    public final void e(final zzpj zzpjVar, long j2, @NonNull final InputImage inputImage, @Nullable List list) {
        final zzcs zzcsVar = new zzcs();
        final zzcs zzcsVar2 = new zzcs();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Barcode barcode = (Barcode) it.next();
                zzcsVar.zzd(zzb.zza(barcode.getFormat()));
                zzcsVar2.zzd(zzb.zzb(barcode.getValueType()));
            }
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j2;
        this.e.zzf(new zztw() { // from class: com.google.mlkit.vision.barcode.internal.zzi
            @Override // com.google.android.gms.internal.mlkit_vision_barcode.zztw
            public final zztm zza() {
                return zzk.this.c(elapsedRealtime, zzpjVar, zzcsVar, zzcsVar2, inputImage);
            }
        }, zzpk.ON_DEVICE_BARCODE_DETECT);
        zzft zzftVar = new zzft();
        zzftVar.zze(zzpjVar);
        zzftVar.zzf(Boolean.valueOf(j));
        zzftVar.zzg(zzb.zzc(this.c));
        zzftVar.zzc(zzcsVar.zzf());
        zzftVar.zzd(zzcsVar2.zzf());
        final zzfv zzh = zzftVar.zzh();
        final zzj zzjVar = new zzj(this);
        final zztx zztxVar = this.e;
        final zzpk zzpkVar = zzpk.AGGREGATED_ON_DEVICE_BARCODE_DETECTION;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zztu
            @Override // java.lang.Runnable
            public final void run() {
                zztx.this.e(zzpkVar, zzh, elapsedRealtime, zzjVar);
            }
        });
        long currentTimeMillis = System.currentTimeMillis();
        this.f.zzc(true != this.h ? 24301 : 24302, zzpjVar.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    @WorkerThread
    public final synchronized void load() throws MlKitException {
        this.h = this.d.zzc();
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    @WorkerThread
    public final synchronized void release() {
        this.d.zzb();
        j = true;
        zztx zztxVar = this.e;
        zzpl zzplVar = new zzpl();
        zzplVar.zze(this.h ? zzpi.TYPE_THICK : zzpi.TYPE_THIN);
        zzpx zzpxVar = new zzpx();
        zzpxVar.zzi(zzb.zzc(this.c));
        zzplVar.zzg(zzpxVar.zzj());
        zztxVar.zzd(zzua.zzf(zzplVar), zzpk.ON_DEVICE_BARCODE_CLOSE);
    }

    @Override // com.google.mlkit.common.sdkinternal.MLTask
    @WorkerThread
    /* renamed from: zze */
    public final synchronized List run(@NonNull InputImage inputImage) throws MlKitException {
        zzpj zzpjVar;
        List a2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.g.check(inputImage);
        try {
            a2 = this.d.a(inputImage);
            e(zzpj.NO_ERROR, elapsedRealtime, inputImage, a2);
            j = false;
        } catch (MlKitException e) {
            if (e.getErrorCode() == 14) {
                zzpjVar = zzpj.MODEL_NOT_DOWNLOADED;
            } else {
                zzpjVar = zzpj.UNKNOWN_ERROR;
            }
            e(zzpjVar, elapsedRealtime, inputImage, null);
            throw e;
        }
        return a2;
    }
}
