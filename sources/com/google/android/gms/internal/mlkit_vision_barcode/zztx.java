package com.google.android.gms.internal.mlkit_vision_barcode;

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
/* loaded from: classes9.dex */
public final class zztx {
    @Nullable
    public static zzcv k;
    public static final zzcx l = zzcx.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);

    /* renamed from: a  reason: collision with root package name */
    public final String f9576a;
    public final String b;
    public final zztn c;
    public final SharedPrefManager d;
    public final Task e;
    public final Task f;
    public final String g;
    public final int h;
    public final Map i = new HashMap();
    public final Map j = new HashMap();

    public zztx(Context context, final SharedPrefManager sharedPrefManager, zztn zztnVar, String str) {
        this.f9576a = context.getPackageName();
        this.b = CommonUtils.getAppVersion(context);
        this.d = sharedPrefManager;
        this.c = zztnVar;
        zzuj.zza();
        this.g = str;
        this.e = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zztr
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zztx.this.b();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.f = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zzts
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzcx zzcxVar = l;
        this.h = zzcxVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzcxVar.get(str)) : -1;
    }

    @VisibleForTesting
    public static long a(List list, double d) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    @NonNull
    public static synchronized zzcv f() {
        synchronized (zztx.class) {
            zzcv zzcvVar = k;
            if (zzcvVar != null) {
                return zzcvVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzcs zzcsVar = new zzcs();
            for (int i = 0; i < locales.size(); i++) {
                zzcsVar.zzd(CommonUtils.languageTagFromLocale(locales.get(i)));
            }
            zzcv zzf = zzcsVar.zzf();
            k = zzf;
            return zzf;
        }
    }

    public final /* synthetic */ String b() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.g);
    }

    public final /* synthetic */ void c(zztm zztmVar, zzpk zzpkVar, String str) {
        String mlSdkInstanceId;
        zztmVar.zzb(zzpkVar);
        String zzd = zztmVar.zzd();
        zzsj zzsjVar = new zzsj();
        zzsjVar.zzb(this.f9576a);
        zzsjVar.zzc(this.b);
        zzsjVar.zzh(f());
        zzsjVar.zzg(Boolean.TRUE);
        zzsjVar.zzl(zzd);
        zzsjVar.zzj(str);
        if (this.f.isSuccessful()) {
            mlSdkInstanceId = (String) this.f.getResult();
        } else {
            mlSdkInstanceId = this.d.getMlSdkInstanceId();
        }
        zzsjVar.zzi(mlSdkInstanceId);
        zzsjVar.zzd(10);
        zzsjVar.zzk(Integer.valueOf(this.h));
        zztmVar.zzc(zzsjVar);
        this.c.zza(zztmVar);
    }

    public final /* synthetic */ void d(zzpk zzpkVar, com.google.mlkit.vision.barcode.internal.zzj zzjVar) {
        zzdb zzdbVar = (zzdb) this.j.get(zzpkVar);
        if (zzdbVar != null) {
            for (Object obj : zzdbVar.zzw()) {
                ArrayList<Long> arrayList = new ArrayList(zzdbVar.zze(obj));
                Collections.sort(arrayList);
                zzoj zzojVar = new zzoj();
                long j = 0;
                for (Long l2 : arrayList) {
                    j += l2.longValue();
                }
                zzojVar.zza(Long.valueOf(j / arrayList.size()));
                zzojVar.zzc(Long.valueOf(a(arrayList, 100.0d)));
                zzojVar.zzf(Long.valueOf(a(arrayList, 75.0d)));
                zzojVar.zzd(Long.valueOf(a(arrayList, 50.0d)));
                zzojVar.zzb(Long.valueOf(a(arrayList, 25.0d)));
                zzojVar.zze(Long.valueOf(a(arrayList, 0.0d)));
                zze(zzjVar.zza(obj, arrayList.size(), zzojVar.zzg()), zzpkVar, g());
            }
            this.j.remove(zzpkVar);
        }
    }

    public final /* synthetic */ void e(final zzpk zzpkVar, Object obj, long j, final com.google.mlkit.vision.barcode.internal.zzj zzjVar) {
        if (!this.j.containsKey(zzpkVar)) {
            this.j.put(zzpkVar, zzbz.zzz());
        }
        ((zzdb) this.j.get(zzpkVar)).zzt(obj, Long.valueOf(j));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (h(zzpkVar, elapsedRealtime, 30L)) {
            this.i.put(zzpkVar, Long.valueOf(elapsedRealtime));
            MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zztv
                @Override // java.lang.Runnable
                public final void run() {
                    zztx.this.d(zzpkVar, zzjVar);
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
    public final boolean h(zzpk zzpkVar, long j, long j2) {
        return this.i.get(zzpkVar) == null || j - ((Long) this.i.get(zzpkVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    public final void zzd(zztm zztmVar, zzpk zzpkVar) {
        zze(zztmVar, zzpkVar, g());
    }

    public final void zze(final zztm zztmVar, final zzpk zzpkVar, final String str) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_barcode.zztt
            @Override // java.lang.Runnable
            public final void run() {
                zztx.this.c(zztmVar, zzpkVar, str);
            }
        });
    }

    @WorkerThread
    public final void zzf(zztw zztwVar, zzpk zzpkVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (h(zzpkVar, elapsedRealtime, 30L)) {
            this.i.put(zzpkVar, Long.valueOf(elapsedRealtime));
            zze(zztwVar.zza(), zzpkVar, g());
        }
    }
}
