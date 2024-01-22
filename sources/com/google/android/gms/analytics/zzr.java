package com.google.android.gms.analytics;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzav;
import com.google.android.gms.internal.gtm.zzba;
import com.google.android.gms.internal.gtm.zzfs;
import java.lang.Thread;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
@VisibleForTesting
@SuppressLint({"StaticFieldLeak"})
/* loaded from: classes6.dex */
public final class zzr {
    public static volatile zzr f;

    /* renamed from: a  reason: collision with root package name */
    public final Context f8186a;
    public final List<zzs> b;
    public final f c;
    public volatile zzav d;
    public Thread.UncaughtExceptionHandler e;

    @VisibleForTesting
    public zzr(Context context) {
        Context applicationContext = context.getApplicationContext();
        Preconditions.checkNotNull(applicationContext);
        this.f8186a = applicationContext;
        this.c = new f(this);
        this.b = new CopyOnWriteArrayList();
        new zzg();
    }

    public static zzr zzb(Context context) {
        Preconditions.checkNotNull(context);
        if (f == null) {
            synchronized (zzr.class) {
                if (f == null) {
                    f = new zzr(context);
                }
            }
        }
        return f;
    }

    public static void zzh() {
        if (!(Thread.currentThread() instanceof h)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final void c(zzh zzhVar) {
        if (!zzhVar.d()) {
            if (!zzhVar.zzm()) {
                zzh zzhVar2 = new zzh(zzhVar);
                zzhVar2.c();
                this.c.execute(new d(this, zzhVar2));
                return;
            }
            throw new IllegalStateException("Measurement can only be submitted once");
        }
        throw new IllegalStateException("Measurement prototype can't be submitted");
    }

    public final Context zza() {
        return this.f8186a;
    }

    public final zzav zzc() {
        if (this.d == null) {
            synchronized (this) {
                if (this.d == null) {
                    zzav zzavVar = new zzav();
                    PackageManager packageManager = this.f8186a.getPackageManager();
                    String packageName = this.f8186a.getPackageName();
                    zzavVar.zzi(packageName);
                    zzavVar.zzj(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.f8186a.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (PackageManager.NameNotFoundException unused) {
                        String valueOf = String.valueOf(packageName);
                        Log.e("GAv4", valueOf.length() != 0 ? "Error retrieving package info: appName set to ".concat(valueOf) : new String("Error retrieving package info: appName set to "));
                    }
                    zzavVar.zzk(packageName);
                    zzavVar.zzl(str);
                    this.d = zzavVar;
                }
            }
        }
        return this.d;
    }

    public final zzba zzd() {
        DisplayMetrics displayMetrics = this.f8186a.getResources().getDisplayMetrics();
        zzba zzbaVar = new zzba();
        zzbaVar.zze(zzfs.zzd(Locale.getDefault()));
        zzbaVar.zza = displayMetrics.widthPixels;
        zzbaVar.zzb = displayMetrics.heightPixels;
        return zzbaVar;
    }

    public final <V> Future<V> zzg(Callable<V> callable) {
        Preconditions.checkNotNull(callable);
        if (Thread.currentThread() instanceof h) {
            FutureTask futureTask = new FutureTask(callable);
            futureTask.run();
            return futureTask;
        }
        return this.c.submit(callable);
    }

    public final void zzi(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        this.c.submit(runnable);
    }

    public final void zzj(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.e = uncaughtExceptionHandler;
    }
}
