package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.annotation.RequiresPermission;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzbv;
import com.google.android.gms.internal.gtm.zzet;
import com.google.android.gms.internal.gtm.zzeu;
import com.google.android.gms.internal.gtm.zzfa;
import com.google.android.gms.internal.gtm.zzfq;
import com.google.android.gms.internal.gtm.zzfr;
import com.google.android.gms.internal.gtm.zzft;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@VisibleForTesting
/* loaded from: classes6.dex */
public final class GoogleAnalytics extends zza {
    @Nullable
    public static List<Runnable> k = new ArrayList();
    public boolean e;
    public Set<j> f;
    public boolean g;
    public boolean h;
    public volatile boolean i;
    public boolean j;

    @VisibleForTesting
    public GoogleAnalytics(zzbv zzbvVar) {
        super(zzbvVar);
        this.f = new HashSet();
    }

    @RecentlyNonNull
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static GoogleAnalytics getInstance(@RecentlyNonNull Context context) {
        return zzbv.zzg(context).zzc();
    }

    public static void zzf() {
        synchronized (GoogleAnalytics.class) {
            List<Runnable> list = k;
            if (list != null) {
                for (Runnable runnable : list) {
                    runnable.run();
                }
                k = null;
            }
        }
    }

    @VisibleForTesting
    public final void b(Activity activity) {
        for (j jVar : this.f) {
            jVar.a(activity);
        }
    }

    @VisibleForTesting
    public final void c(Activity activity) {
        for (j jVar : this.f) {
            jVar.b(activity);
        }
    }

    public final void d(j jVar) {
        this.f.add(jVar);
        Context zza = a().zza();
        if (zza instanceof Application) {
            enableAutoActivityReports((Application) zza);
        }
    }

    public void dispatchLocalHits() {
        a().zzf().zzc();
    }

    public final void e(j jVar) {
        this.f.remove(jVar);
    }

    @TargetApi(14)
    public void enableAutoActivityReports(@RecentlyNonNull Application application) {
        if (this.g) {
            return;
        }
        application.registerActivityLifecycleCallbacks(new b(this));
        this.g = true;
    }

    public boolean getAppOptOut() {
        return this.i;
    }

    @RecentlyNonNull
    @Deprecated
    public Logger getLogger() {
        return zzfa.zza();
    }

    public boolean isDryRunEnabled() {
        return this.h;
    }

    @RecentlyNonNull
    public Tracker newTracker(int i) {
        Tracker tracker;
        zzfr zza;
        synchronized (this) {
            tracker = new Tracker(a(), null, null);
            if (i > 0 && (zza = new zzfq(a()).zza(i)) != null) {
                tracker.m(zza);
            }
            tracker.zzX();
        }
        return tracker;
    }

    public void reportActivityStart(@RecentlyNonNull Activity activity) {
        if (this.g) {
            return;
        }
        b(activity);
    }

    public void reportActivityStop(@RecentlyNonNull Activity activity) {
        if (this.g) {
            return;
        }
        c(activity);
    }

    public void setAppOptOut(boolean z) {
        this.i = z;
        if (this.i) {
            a().zzf().zzg();
        }
    }

    public void setDryRun(boolean z) {
        this.h = z;
    }

    public void setLocalDispatchPeriod(int i) {
        a().zzf().zzl(i);
    }

    @Deprecated
    public void setLogger(@RecentlyNonNull Logger logger) {
        zzfa.zzc(logger);
        if (this.j) {
            return;
        }
        zzet<String> zzetVar = zzeu.zzc;
        String zzb = zzetVar.zzb();
        StringBuilder sb = new StringBuilder(zzb.length() + 112);
        sb.append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.");
        sb.append(zzb);
        sb.append(" DEBUG");
        Log.i(zzetVar.zzb(), sb.toString());
        this.j = true;
    }

    public final void zzg() {
        zzft zzq = a().zzq();
        zzq.zzf();
        if (zzq.zze()) {
            setDryRun(zzq.zzc());
        }
        zzq.zzf();
        this.e = true;
    }

    public final boolean zzj() {
        return this.e;
    }

    @RecentlyNonNull
    public Tracker newTracker(@RecentlyNonNull String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(a(), str, null);
            tracker.zzX();
        }
        return tracker;
    }
}
