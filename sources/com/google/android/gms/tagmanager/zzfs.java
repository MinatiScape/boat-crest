package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.internal.ShowFirstParty;
@ShowFirstParty
/* loaded from: classes10.dex */
public final class zzfs {
    public GoogleAnalytics zza;
    public final Context zzb;
    public Tracker zzc;

    public zzfs(Context context) {
        this.zzb = context;
    }

    public final Tracker zza(String str) {
        zzb("_GTM_DEFAULT_TRACKER_");
        return this.zzc;
    }

    public final synchronized void zzb(String str) {
        if (this.zza == null) {
            GoogleAnalytics googleAnalytics = GoogleAnalytics.getInstance(this.zzb);
            this.zza = googleAnalytics;
            googleAnalytics.setLogger(new zzfr());
            this.zzc = this.zza.newTracker("_GTM_DEFAULT_TRACKER_");
        }
    }
}
