package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public final class zzog {
    @Nullable
    public static zzbm k;
    public static final zzbo l = zzbo.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);

    /* renamed from: a  reason: collision with root package name */
    public final String f9951a;
    public final String b;
    public final zzof c;
    public final SharedPrefManager d;
    public final Task e;
    public final Task f;
    public final String g;
    public final int h;
    public final Map i = new HashMap();
    public final Map j = new HashMap();

    public zzog(Context context, final SharedPrefManager sharedPrefManager, zzof zzofVar, String str) {
        this.f9951a = context.getPackageName();
        this.b = CommonUtils.getAppVersion(context);
        this.d = sharedPrefManager;
        this.c = zzofVar;
        zzos.zza();
        this.g = str;
        this.e = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zznz
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzog.this.b();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.f = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzoa
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzbo zzboVar = l;
        this.h = zzboVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzboVar.get(str)) : -1;
    }

    @VisibleForTesting
    public static long a(List list, double d) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    @NonNull
    public static synchronized zzbm f() {
        synchronized (zzog.class) {
            zzbm zzbmVar = k;
            if (zzbmVar != null) {
                return zzbmVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzbj zzbjVar = new zzbj();
            for (int i = 0; i < locales.size(); i++) {
                zzbjVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzbm zzc = zzbjVar.zzc();
            k = zzc;
            return zzc;
        }
    }

    public final /* synthetic */ String b() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.g);
    }

    public final /* synthetic */ void c(zznv zznvVar, zzkt zzktVar, String str) {
        String mlSdkInstanceId;
        zznvVar.zzb(zzktVar);
        String zzd = zznvVar.zzd();
        zzmw zzmwVar = new zzmw();
        zzmwVar.zzb(this.f9951a);
        zzmwVar.zzc(this.b);
        zzmwVar.zzh(f());
        zzmwVar.zzg(Boolean.TRUE);
        zzmwVar.zzl(zzd);
        zzmwVar.zzj(str);
        if (this.f.isSuccessful()) {
            mlSdkInstanceId = (String) this.f.getResult();
        } else {
            mlSdkInstanceId = this.d.getMlSdkInstanceId();
        }
        zzmwVar.zzi(mlSdkInstanceId);
        zzmwVar.zzd(10);
        zzmwVar.zzk(Integer.valueOf(this.h));
        zznvVar.zzc(zzmwVar);
        this.c.zza(zznvVar);
    }

    public final /* synthetic */ void d(zzkt zzktVar, com.google.mlkit.vision.text.internal.zzo zzoVar) {
        zzbr zzbrVar = (zzbr) this.j.get(zzktVar);
        if (zzbrVar != null) {
            for (Object obj : zzbrVar.zzq()) {
                ArrayList<Long> arrayList = new ArrayList(zzbrVar.zzc(obj));
                Collections.sort(arrayList);
                zzjz zzjzVar = new zzjz();
                long j = 0;
                for (Long l2 : arrayList) {
                    j += l2.longValue();
                }
                zzjzVar.zza(Long.valueOf(j / arrayList.size()));
                zzjzVar.zzc(Long.valueOf(a(arrayList, 100.0d)));
                zzjzVar.zzf(Long.valueOf(a(arrayList, 75.0d)));
                zzjzVar.zzd(Long.valueOf(a(arrayList, 50.0d)));
                zzjzVar.zzb(Long.valueOf(a(arrayList, 25.0d)));
                zzjzVar.zze(Long.valueOf(a(arrayList, 0.0d)));
                zze(zzoVar.zza(obj, arrayList.size(), zzjzVar.zzg()), zzktVar, g());
            }
            this.j.remove(zzktVar);
        }
    }

    public final /* synthetic */ void e(final zzkt zzktVar, Object obj, long j, final com.google.mlkit.vision.text.internal.zzo zzoVar) {
        if (!this.j.containsKey(zzktVar)) {
            this.j.put(zzktVar, zzar.zzr());
        }
        ((zzbr) this.j.get(zzktVar)).zzo(obj, Long.valueOf(j));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (h(zzktVar, elapsedRealtime, 30L)) {
            this.i.put(zzktVar, Long.valueOf(elapsedRealtime));
            MLTaskExecutor.workerThreadExecutor().execute(new Runnable(zzktVar, zzoVar, null) { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzod
                public final /* synthetic */ zzkt zzb;
                public final /* synthetic */ com.google.mlkit.vision.text.internal.zzo zzc;

                @Override // java.lang.Runnable
                public final void run() {
                    zzog.this.d(this.zzb, this.zzc);
                }
            });
        }
    }

    @WorkerThread
    public final String g() {
        if (this.e.isSuccessful()) {
            return (String) this.e.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.g);
    }

    @WorkerThread
    public final boolean h(zzkt zzktVar, long j, long j2) {
        return this.i.get(zzktVar) == null || j - ((Long) this.i.get(zzktVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    public final void zzd(zznv zznvVar, zzkt zzktVar) {
        zze(zznvVar, zzktVar, g());
    }

    public final void zze(final zznv zznvVar, final zzkt zzktVar, final String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzob
            @Override // java.lang.Runnable
            public final void run() {
                zzog.this.c(zznvVar, zzktVar, str);
            }
        });
    }

    @WorkerThread
    public final void zzf(zzoe zzoeVar, zzkt zzktVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (h(zzktVar, elapsedRealtime, 30L)) {
            this.i.put(zzktVar, Long.valueOf(elapsedRealtime));
            zze(zzoeVar.zza(), zzktVar, g());
        }
    }
}
