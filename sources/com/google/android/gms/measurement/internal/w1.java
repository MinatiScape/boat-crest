package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.MainThread;
@TargetApi(14)
@MainThread
/* loaded from: classes10.dex */
public final class w1 implements Application.ActivityLifecycleCallbacks {
    public final /* synthetic */ zzhv h;

    public /* synthetic */ w1(zzhv zzhvVar, zzht zzhtVar) {
        this.h = zzhvVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        zzfs zzfsVar;
        try {
            try {
                this.h.zzs.zzay().zzj().zza("onActivityCreated");
                Intent intent = activity.getIntent();
                if (intent == null) {
                    zzfsVar = this.h.zzs;
                } else {
                    Uri data = intent.getData();
                    if (data != null && data.isHierarchical()) {
                        this.h.zzs.zzv();
                        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
                        boolean z = true;
                        String str = true != ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(stringExtra) || "https://www.google.com".equals(stringExtra) || "android-app://com.google.appcrawler".equals(stringExtra)) ? "auto" : "gs";
                        String queryParameter = data.getQueryParameter("referrer");
                        if (bundle != null) {
                            z = false;
                        }
                        this.h.zzs.zzaz().zzp(new v1(this, z, data, str, queryParameter));
                        zzfsVar = this.h.zzs;
                    }
                    zzfsVar = this.h.zzs;
                }
            } catch (RuntimeException e) {
                this.h.zzs.zzay().zzd().zzb("Throwable caught in onActivityCreated", e);
                zzfsVar = this.h.zzs;
            }
            zzfsVar.zzs().zzr(activity, bundle);
        } catch (Throwable th) {
            this.h.zzs.zzs().zzr(activity, bundle);
            throw th;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.h.zzs.zzs().zzs(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.h.zzs.zzs().zzt(activity);
        zzjy zzu = this.h.zzs.zzu();
        zzu.zzs.zzaz().zzp(new h3(zzu, zzu.zzs.zzav().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    @MainThread
    public final void onActivityResumed(Activity activity) {
        zzjy zzu = this.h.zzs.zzu();
        zzu.zzs.zzaz().zzp(new g3(zzu, zzu.zzs.zzav().elapsedRealtime()));
        this.h.zzs.zzs().zzu(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.h.zzs.zzs().zzv(activity, bundle);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }
}
