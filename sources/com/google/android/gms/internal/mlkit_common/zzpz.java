package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.OptionalModuleUtils;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class zzpz {
    @Nullable
    public static zzar i;
    public static final zzau j = zzau.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);

    /* renamed from: a  reason: collision with root package name */
    public final String f9356a;
    public final String b;
    public final zzpr c;
    public final SharedPrefManager d;
    public final Task e;
    public final Task f;
    public final String g;
    public final int h;

    public zzpz(Context context, final SharedPrefManager sharedPrefManager, zzpr zzprVar, String str) {
        new HashMap();
        new HashMap();
        this.f9356a = context.getPackageName();
        this.b = CommonUtils.getAppVersion(context);
        this.d = sharedPrefManager;
        this.c = zzprVar;
        zzqn.zza();
        this.g = str;
        this.e = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzpv
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzpz.this.a();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.f = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzpw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzau zzauVar = j;
        this.h = zzauVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzauVar.get(str)) : -1;
    }

    @NonNull
    public static synchronized zzar d() {
        synchronized (zzpz.class) {
            zzar zzarVar = i;
            if (zzarVar != null) {
                return zzarVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzao zzaoVar = new zzao();
            for (int i2 = 0; i2 < locales.size(); i2++) {
                zzaoVar.zzb(CommonUtils.languageTagFromLocale(locales.get(i2)));
            }
            zzar zzc = zzaoVar.zzc();
            i = zzc;
            return zzc;
        }
    }

    public final /* synthetic */ String a() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.g);
    }

    public final /* synthetic */ void b(zzpq zzpqVar, zzln zzlnVar, String str) {
        zzpqVar.zza(zzlnVar);
        zzpqVar.zzc(e(zzpqVar.zzd(), str));
        this.c.zza(zzpqVar);
    }

    public final /* synthetic */ void c(zzpq zzpqVar, zzqb zzqbVar, RemoteModel remoteModel) {
        zzpqVar.zza(zzln.MODEL_DOWNLOAD);
        zzpqVar.zzc(e(zzqbVar.zze(), f()));
        zzpqVar.zzb(zzql.zza(remoteModel, this.d, zzqbVar));
        this.c.zza(zzpqVar);
    }

    public final zzol e(String str, String str2) {
        String mlSdkInstanceId;
        zzol zzolVar = new zzol();
        zzolVar.zzb(this.f9356a);
        zzolVar.zzc(this.b);
        zzolVar.zzh(d());
        zzolVar.zzg(Boolean.TRUE);
        zzolVar.zzl(str);
        zzolVar.zzj(str2);
        if (this.f.isSuccessful()) {
            mlSdkInstanceId = (String) this.f.getResult();
        } else {
            mlSdkInstanceId = this.d.getMlSdkInstanceId();
        }
        zzolVar.zzi(mlSdkInstanceId);
        zzolVar.zzd(10);
        zzolVar.zzk(Integer.valueOf(this.h));
        return zzolVar;
    }

    @WorkerThread
    public final String f() {
        if (this.e.isSuccessful()) {
            return (String) this.e.getResult();
        }
        return LibraryVersion.getInstance().getVersion(this.g);
    }

    public final void zzd(final zzpq zzpqVar, final zzln zzlnVar) {
        final String f = f();
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzpx
            @Override // java.lang.Runnable
            public final void run() {
                zzpz.this.b(zzpqVar, zzlnVar, f);
            }
        });
    }

    public final void zze(zzpq zzpqVar, RemoteModel remoteModel, boolean z, int i2) {
        zzqa zzh = zzqb.zzh();
        zzh.zzf(false);
        zzh.zzd(remoteModel.getModelType());
        zzh.zza(zzls.FAILED);
        zzh.zzb(zzlm.DOWNLOAD_FAILED);
        zzh.zzc(i2);
        zzg(zzpqVar, remoteModel, zzh.zzh());
    }

    public final void zzf(zzpq zzpqVar, RemoteModel remoteModel, zzlm zzlmVar, boolean z, ModelType modelType, zzls zzlsVar) {
        zzqa zzh = zzqb.zzh();
        zzh.zzf(z);
        zzh.zzd(modelType);
        zzh.zzb(zzlmVar);
        zzh.zza(zzlsVar);
        zzg(zzpqVar, remoteModel, zzh.zzh());
    }

    public final void zzg(final zzpq zzpqVar, final RemoteModel remoteModel, final zzqb zzqbVar) {
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzpy
            @Override // java.lang.Runnable
            public final void run() {
                zzpz.this.c(zzpqVar, zzqbVar, remoteModel);
            }
        });
    }
}
