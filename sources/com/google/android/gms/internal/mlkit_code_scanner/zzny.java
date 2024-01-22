package com.google.android.gms.internal.mlkit_code_scanner;

import android.content.Context;
import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.concurrent.Callable;
/* loaded from: classes8.dex */
public final class zzny {
    @Nullable
    public static zzp i;
    public static final zzr j = zzr.zzc("optional-module-barcode", OptionalModuleUtils.BARCODE_MODULE_ID);

    /* renamed from: a  reason: collision with root package name */
    public final String f9148a;
    public final String b;
    public final zznr c;
    public final SharedPrefManager d;
    public final Task e;
    public final Task f;
    public final String g;
    public final int h;

    public zzny(Context context, final SharedPrefManager sharedPrefManager, zznr zznrVar, String str) {
        new HashMap();
        new HashMap();
        this.f9148a = context.getPackageName();
        this.b = CommonUtils.getAppVersion(context);
        this.d = sharedPrefManager;
        this.c = zznrVar;
        zzok.zza();
        this.g = str;
        this.e = MLTaskExecutor.getInstance().scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zznv
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzny.this.a();
            }
        });
        MLTaskExecutor mLTaskExecutor = MLTaskExecutor.getInstance();
        sharedPrefManager.getClass();
        this.f = mLTaskExecutor.scheduleCallable(new Callable() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zznw
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.getMlSdkInstanceId();
            }
        });
        zzr zzrVar = j;
        this.h = zzrVar.containsKey(str) ? DynamiteModule.getRemoteVersion(context, (String) zzrVar.get(str)) : -1;
    }

    @NonNull
    public static synchronized zzp c() {
        synchronized (zzny.class) {
            zzp zzpVar = i;
            if (zzpVar != null) {
                return zzpVar;
            }
            LocaleListCompat locales = ConfigurationCompat.getLocales(Resources.getSystem().getConfiguration());
            zzm zzmVar = new zzm();
            for (int i2 = 0; i2 < locales.size(); i2++) {
                zzmVar.zza(CommonUtils.languageTagFromLocale(locales.get(i2)));
            }
            zzp zzb = zzmVar.zzb();
            i = zzb;
            return zzb;
        }
    }

    public final /* synthetic */ String a() throws Exception {
        return LibraryVersion.getInstance().getVersion(this.g);
    }

    public final /* synthetic */ void b(zznq zznqVar, zzkb zzkbVar, String str) {
        String mlSdkInstanceId;
        zznqVar.zza(zzkbVar);
        String zzc = zznqVar.zzc();
        zzmq zzmqVar = new zzmq();
        zzmqVar.zzb(this.f9148a);
        zzmqVar.zzc(this.b);
        zzmqVar.zzh(c());
        zzmqVar.zzg(Boolean.TRUE);
        zzmqVar.zzl(zzc);
        zzmqVar.zzj(str);
        if (this.f.isSuccessful()) {
            mlSdkInstanceId = (String) this.f.getResult();
        } else {
            mlSdkInstanceId = this.d.getMlSdkInstanceId();
        }
        zzmqVar.zzi(mlSdkInstanceId);
        zzmqVar.zzd(10);
        zzmqVar.zzk(Integer.valueOf(this.h));
        zznqVar.zzb(zzmqVar);
        this.c.zza(zznqVar);
    }

    public final void zzc(final zznq zznqVar, final zzkb zzkbVar) {
        final String version;
        if (this.e.isSuccessful()) {
            version = (String) this.e.getResult();
        } else {
            version = LibraryVersion.getInstance().getVersion(this.g);
        }
        MLTaskExecutor.workerThreadExecutor().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_code_scanner.zznx
            @Override // java.lang.Runnable
            public final void run() {
                zzny.this.b(zznqVar, zzkbVar, version);
            }
        });
    }
}
