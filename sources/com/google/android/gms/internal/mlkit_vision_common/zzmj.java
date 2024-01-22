package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes8.dex */
public final class zzmj {
    @Nullable
    public static zzp j;
    public static final zzr k = zzr.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);

    /* renamed from: a  reason: collision with root package name */
    public final String f9792a;
    public final String b;
    public final zzmc c;
    public final SharedPrefManager d;
    public final Task e;
    public final Task f;
    public final String g;
    public final int h;
    public final Map i = new HashMap();

    public zzmj(Context context, final SharedPrefManager sharedPrefManager, zzmc zzmcVar, String str) {
        new HashMap();
        this.f9792a = context.getPackageName();
        this.b = CommonUtils.getAppVersion(context);
        this.d = sharedPrefManager;
        this.c = zzmcVar;
        zzmw.zza();
        this.g = str;
        this.e = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzmj.this.a();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.f = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmh
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzr zzrVar = k;
        this.h = zzrVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzrVar.get(str)) : -1;
    }

    @NonNull
    public static synchronized zzp c() {
        synchronized (zzmj.class) {
            zzp zzpVar = j;
            if (zzpVar != null) {
                return zzpVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzm zzmVar = new zzm();
            for (int i = 0; i < locales.size(); i++) {
                zzmVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzp zzc = zzmVar.zzc();
            j = zzc;
            return zzc;
        }
    }

    public final /* synthetic */ String a() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.g);
    }

    public final /* synthetic */ void b(zzmb zzmbVar, zziv zzivVar, String str) {
        String mlSdkInstanceId;
        zzmbVar.zza(zzivVar);
        String zzc = zzmbVar.zzc();
        zzky zzkyVar = new zzky();
        zzkyVar.zzb(this.f9792a);
        zzkyVar.zzc(this.b);
        zzkyVar.zzh(c());
        zzkyVar.zzg(Boolean.TRUE);
        zzkyVar.zzl(zzc);
        zzkyVar.zzj(str);
        if (this.f.isSuccessful()) {
            mlSdkInstanceId = (String) this.f.getResult();
        } else {
            mlSdkInstanceId = this.d.getMlSdkInstanceId();
        }
        zzkyVar.zzi(mlSdkInstanceId);
        zzkyVar.zzd(10);
        zzkyVar.zzk(Integer.valueOf(this.h));
        zzmbVar.zzb(zzkyVar);
        this.c.zza(zzmbVar);
    }

    @WorkerThread
    public final void zzc(zzmt zzmtVar, final zziv zzivVar) {
        zzii zziiVar;
        zzio zzioVar;
        final String version;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.i.get(zzivVar) != null && elapsedRealtime - ((Long) this.i.get(zzivVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.i.put(zzivVar, Long.valueOf(elapsedRealtime));
        int i = zzmtVar.zza;
        int i2 = zzmtVar.zzb;
        int i3 = zzmtVar.zzc;
        int i4 = zzmtVar.zzd;
        int i5 = zzmtVar.zze;
        long j2 = zzmtVar.zzf;
        int i6 = zzmtVar.zzg;
        zzin zzinVar = new zzin();
        if (i == -1) {
            zziiVar = zzii.BITMAP;
        } else if (i == 35) {
            zziiVar = zzii.YUV_420_888;
        } else if (i == 842094169) {
            zziiVar = zzii.YV12;
        } else if (i == 16) {
            zziiVar = zzii.NV16;
        } else if (i != 17) {
            zziiVar = zzii.UNKNOWN_FORMAT;
        } else {
            zziiVar = zzii.NV21;
        }
        zzinVar.zzd(zziiVar);
        if (i2 == 1) {
            zzioVar = zzio.BITMAP;
        } else if (i2 == 2) {
            zzioVar = zzio.BYTEARRAY;
        } else if (i2 == 3) {
            zzioVar = zzio.BYTEBUFFER;
        } else if (i2 != 4) {
            zzioVar = zzio.ANDROID_MEDIA_IMAGE;
        } else {
            zzioVar = zzio.FILEPATH;
        }
        zzinVar.zzf(zzioVar);
        zzinVar.zzc(Integer.valueOf(i3));
        zzinVar.zze(Integer.valueOf(i4));
        zzinVar.zzg(Integer.valueOf(i5));
        zzinVar.zzb(Long.valueOf(j2));
        zzinVar.zzh(Integer.valueOf(i6));
        zziq zzj = zzinVar.zzj();
        zziw zziwVar = new zziw();
        zziwVar.zzd(zzj);
        final zzmb zze = zzmk.zze(zziwVar);
        if (this.e.isSuccessful()) {
            version = (String) this.e.getResult();
        } else {
            version = LibraryVersion.getInstance().getVersion(this.g);
        }
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmi
            @Override // java.lang.Runnable
            public final void run() {
                zzmj.this.b(zze, zzivVar, version);
            }
        });
    }
}
