package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes10.dex */
public final class v extends x0 {
    @VisibleForTesting
    public static final Pair<String, Long> w = new Pair<>("", 0L);
    public SharedPreferences b;
    public zzev c;
    public final zzet d;
    public final zzet e;
    public final zzew f;
    public String g;
    public boolean h;
    public long i;
    public final zzet j;
    public final zzer k;
    public final zzew l;
    public final zzer m;
    public final zzet n;
    public boolean o;
    public final zzer p;
    public final zzer q;
    public final zzet r;
    public final zzew s;
    public final zzew t;
    public final zzet u;
    public final zzes v;

    public v(zzfs zzfsVar) {
        super(zzfsVar);
        this.j = new zzet(this, "session_timeout", 1800000L);
        this.k = new zzer(this, "start_new_session", true);
        this.n = new zzet(this, "last_pause_time", 0L);
        this.l = new zzew(this, "non_personalized_ads", null);
        this.m = new zzer(this, "allow_remote_dynamite", false);
        this.d = new zzet(this, "first_open_time", 0L);
        this.e = new zzet(this, "app_install_time", 0L);
        this.f = new zzew(this, "app_instance_id", null);
        this.p = new zzer(this, "app_backgrounded", false);
        this.q = new zzer(this, "deep_link_retrieval_complete", false);
        this.r = new zzet(this, "deep_link_retrieval_attempts", 0L);
        this.s = new zzew(this, "firebase_feature_rollouts", null);
        this.t = new zzew(this, "deferred_attribution_cache", null);
        this.u = new zzet(this, "deferred_attribution_cache_timestamp", 0L);
        this.v = new zzes(this, "default_event_parameters", null);
    }

    @VisibleForTesting
    @WorkerThread
    public final SharedPreferences b() {
        zzg();
        zzu();
        Preconditions.checkNotNull(this.b);
        return this.b;
    }

    @WorkerThread
    public final Pair<String, Boolean> c(String str) {
        zzg();
        long elapsedRealtime = this.zzs.zzav().elapsedRealtime();
        String str2 = this.g;
        if (str2 != null && elapsedRealtime < this.i) {
            return new Pair<>(str2, Boolean.valueOf(this.h));
        }
        this.i = elapsedRealtime + this.zzs.zzf().zzi(str, zzdw.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.zzs.zzau());
            this.g = "";
            String id = advertisingIdInfo.getId();
            if (id != null) {
                this.g = id;
            }
            this.h = advertisingIdInfo.isLimitAdTrackingEnabled();
        } catch (Exception e) {
            this.zzs.zzay().zzc().zzb("Unable to get advertising id", e);
            this.g = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.g, Boolean.valueOf(this.h));
    }

    @WorkerThread
    public final zzag d() {
        zzg();
        return zzag.zzb(b().getString("consent_settings", "G1"));
    }

    @WorkerThread
    public final Boolean e() {
        zzg();
        if (b().contains("measurement_enabled")) {
            return Boolean.valueOf(b().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    @WorkerThread
    public final void f(Boolean bool) {
        zzg();
        SharedPreferences.Editor edit = b().edit();
        if (bool != null) {
            edit.putBoolean("measurement_enabled", bool.booleanValue());
        } else {
            edit.remove("measurement_enabled");
        }
        edit.apply();
    }

    @WorkerThread
    public final void g(boolean z) {
        zzg();
        this.zzs.zzay().zzj().zzb("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor edit = b().edit();
        edit.putBoolean("deferred_analytics_collection", z);
        edit.apply();
    }

    @WorkerThread
    public final boolean h() {
        SharedPreferences sharedPreferences = this.b;
        if (sharedPreferences == null) {
            return false;
        }
        return sharedPreferences.contains("deferred_analytics_collection");
    }

    public final boolean i(long j) {
        return j - this.j.zza() > this.n.zza();
    }

    @WorkerThread
    public final boolean j(int i) {
        return zzag.zzl(i, b().getInt("consent_source", 100));
    }

    @Override // com.google.android.gms.measurement.internal.x0
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    @WorkerThread
    public final void zzaA() {
        SharedPreferences sharedPreferences = this.zzs.zzau().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.b = sharedPreferences;
        boolean z = sharedPreferences.getBoolean("has_been_opened", false);
        this.o = z;
        if (!z) {
            SharedPreferences.Editor edit = this.b.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
        this.zzs.zzf();
        this.c = new zzev(this, "health_monitor", Math.max(0L, zzdw.zzb.zza(null).longValue()), null);
    }

    @Override // com.google.android.gms.measurement.internal.x0
    public final boolean zzf() {
        return true;
    }
}
