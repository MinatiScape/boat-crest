package com.google.mlkit.vision.text.internal;

import android.content.Context;
import android.os.SystemClock;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzeb;
import com.google.android.gms.internal.mlkit_vision_text_common.zzec;
import com.google.android.gms.internal.mlkit_vision_text_common.zzee;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkb;
import com.google.android.gms.internal.mlkit_vision_text_common.zzke;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkf;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkj;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkr;
import com.google.android.gms.internal.mlkit_vision_text_common.zzks;
import com.google.android.gms.internal.mlkit_vision_text_common.zzkt;
import com.google.android.gms.internal.mlkit_vision_text_common.zzku;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmk;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmm;
import com.google.android.gms.internal.mlkit_vision_text_common.zzmn;
import com.google.android.gms.internal.mlkit_vision_text_common.zznv;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoe;
import com.google.android.gms.internal.mlkit_vision_text_common.zzog;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoi;
import com.google.android.gms.internal.mlkit_vision_text_common.zzoj;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.common.sdkinternal.MLTask;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.MlKitContext;
import com.google.mlkit.common.sdkinternal.TaskQueue;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.common.internal.ImageUtils;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognizerOptionsInterface;
/* loaded from: classes10.dex */
public class TextRecognizerTaskWithResource extends MLTask<Text, InputImage> {
    @VisibleForTesting
    public static boolean h = true;
    @NonNull
    @GuardedBy("this")
    public final e c;
    public final zzog d;
    public final zzoi e;
    public final TextRecognizerOptionsInterface f;
    public static final ImageUtils i = ImageUtils.getInstance();
    @KeepForSdk
    public static final TaskQueue g = new TaskQueue();

    public TextRecognizerTaskWithResource(@NonNull zzog zzogVar, @NonNull e eVar, @NonNull TextRecognizerOptionsInterface textRecognizerOptionsInterface) {
        super(g);
        this.d = zzogVar;
        this.c = eVar;
        this.e = zzoi.zza(MlKitContext.getInstance().getApplicationContext());
        this.f = textRecognizerOptionsInterface;
    }

    public static e e(Context context, TextRecognizerOptionsInterface textRecognizerOptionsInterface, zzog zzogVar) {
        if (GoogleApiAvailabilityLight.getInstance().getApkVersion(context) < 204700000 && !textRecognizerOptionsInterface.getIsThickClient()) {
            return new c(context);
        }
        return new b(context, textRecognizerOptionsInterface, zzogVar);
    }

    public final /* synthetic */ zznv c(long j, zzks zzksVar, InputImage inputImage) {
        zzkf zzkfVar;
        zzkr zzkrVar;
        zzmk zzmkVar = new zzmk();
        zzkj zzkjVar = new zzkj();
        zzkjVar.zzc(Long.valueOf(j));
        zzkjVar.zzd(zzksVar);
        zzkjVar.zze(Boolean.valueOf(h));
        Boolean bool = Boolean.TRUE;
        zzkjVar.zza(bool);
        zzkjVar.zzb(bool);
        zzmkVar.zzd(zzkjVar.zzf());
        ImageUtils imageUtils = i;
        int mobileVisionImageFormat = imageUtils.getMobileVisionImageFormat(inputImage);
        int mobileVisionImageSize = imageUtils.getMobileVisionImageSize(inputImage);
        zzke zzkeVar = new zzke();
        if (mobileVisionImageFormat == -1) {
            zzkfVar = zzkf.BITMAP;
        } else if (mobileVisionImageFormat == 35) {
            zzkfVar = zzkf.YUV_420_888;
        } else if (mobileVisionImageFormat == 842094169) {
            zzkfVar = zzkf.YV12;
        } else if (mobileVisionImageFormat == 16) {
            zzkfVar = zzkf.NV16;
        } else if (mobileVisionImageFormat != 17) {
            zzkfVar = zzkf.UNKNOWN_FORMAT;
        } else {
            zzkfVar = zzkf.NV21;
        }
        zzkeVar.zza(zzkfVar);
        zzkeVar.zzb(Integer.valueOf(mobileVisionImageSize));
        zzmkVar.zzc(zzkeVar.zzd());
        zzmn zzmnVar = new zzmn();
        zzmnVar.zza(LoggingUtils.a(this.f.getLoggingLanguageOption()));
        zzmkVar.zze(zzmnVar.zzc());
        zzmm zzf = zzmkVar.zzf();
        zzku zzkuVar = new zzku();
        if (this.f.getIsThickClient()) {
            zzkrVar = zzkr.TYPE_THICK;
        } else {
            zzkrVar = zzkr.TYPE_THIN;
        }
        zzkuVar.zze(zzkrVar);
        zzkuVar.zzh(zzf);
        return zzoj.zzf(zzkuVar);
    }

    public final /* synthetic */ zznv d(zzee zzeeVar, int i2, zzkb zzkbVar) {
        zzkr zzkrVar;
        zzku zzkuVar = new zzku();
        if (this.f.getIsThickClient()) {
            zzkrVar = zzkr.TYPE_THICK;
        } else {
            zzkrVar = zzkr.TYPE_THIN;
        }
        zzkuVar.zze(zzkrVar);
        zzeb zzebVar = new zzeb();
        zzebVar.zza(Integer.valueOf(i2));
        zzebVar.zzc(zzeeVar);
        zzebVar.zzb(zzkbVar);
        zzkuVar.zzd(zzebVar.zze());
        return zzoj.zzf(zzkuVar);
    }

    @WorkerThread
    public final void f(final zzks zzksVar, long j, final InputImage inputImage) {
        final long elapsedRealtime = SystemClock.elapsedRealtime() - j;
        this.d.zzf(new zzoe() { // from class: com.google.mlkit.vision.text.internal.zzn
            @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzoe
            public final zznv zza() {
                return TextRecognizerTaskWithResource.this.c(elapsedRealtime, zzksVar, inputImage);
            }
        }, zzkt.ON_DEVICE_TEXT_DETECT);
        zzec zzecVar = new zzec();
        zzecVar.zza(zzksVar);
        zzecVar.zzb(Boolean.valueOf(h));
        zzmn zzmnVar = new zzmn();
        zzmnVar.zza(LoggingUtils.a(this.f.getLoggingLanguageOption()));
        zzecVar.zzc(zzmnVar.zzc());
        final zzee zzd = zzecVar.zzd();
        final zzo zzoVar = new zzo(this);
        final zzog zzogVar = this.d;
        final zzkt zzktVar = zzkt.AGGREGATED_ON_DEVICE_TEXT_DETECTION;
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzktVar, zzd, elapsedRealtime, zzoVar, null) { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzoc
            public final /* synthetic */ zzkt zzb;
            public final /* synthetic */ Object zzc;
            public final /* synthetic */ long zzd;
            public final /* synthetic */ com.google.mlkit.vision.text.internal.zzo zze;

            @Override // java.lang.Runnable
            public final void run() {
                zzog.this.e(this.zzb, this.zzc, this.zzd, this.zze);
            }
        });
        long currentTimeMillis = System.currentTimeMillis();
        this.e.zzc(this.f.getLoggingEventId(), zzksVar.zza(), currentTimeMillis - elapsedRealtime, currentTimeMillis);
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    @WorkerThread
    public final synchronized void load() throws MlKitException {
        this.c.zzb();
    }

    @Override // com.google.mlkit.common.sdkinternal.ModelResource
    @WorkerThread
    public final synchronized void release() {
        h = true;
        this.c.zzc();
    }

    @Override // com.google.mlkit.common.sdkinternal.MLTask
    @NonNull
    @WorkerThread
    /* renamed from: zze */
    public final synchronized Text run(@NonNull InputImage inputImage) throws MlKitException {
        zzks zzksVar;
        Text a2;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        try {
            a2 = this.c.a(inputImage);
            f(zzks.NO_ERROR, elapsedRealtime, inputImage);
            h = false;
        } catch (MlKitException e) {
            if (e.getErrorCode() == 14) {
                zzksVar = zzks.MODEL_NOT_DOWNLOADED;
            } else {
                zzksVar = zzks.UNKNOWN_ERROR;
            }
            f(zzksVar, elapsedRealtime, inputImage);
            throw e;
        }
        return a2;
    }
}
