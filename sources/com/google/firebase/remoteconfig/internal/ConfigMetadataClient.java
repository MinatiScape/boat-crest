package com.google.firebase.remoteconfig.internal;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.Date;
/* loaded from: classes10.dex */
public class ConfigMetadataClient {
    @VisibleForTesting
    public static final long LAST_FETCH_TIME_IN_MILLIS_NO_FETCH_YET = -1;
    public static final Date d = new Date(-1);
    @VisibleForTesting
    public static final Date e = new Date(-1);

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11497a;
    public final Object b = new Object();
    public final Object c = new Object();

    /* loaded from: classes10.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f11498a;
        public Date b;

        public a(int i, Date date) {
            this.f11498a = i;
            this.b = date;
        }

        public Date a() {
            return this.b;
        }

        public int b() {
            return this.f11498a;
        }
    }

    public ConfigMetadataClient(SharedPreferences sharedPreferences) {
        this.f11497a = sharedPreferences;
    }

    public a a() {
        a aVar;
        synchronized (this.c) {
            aVar = new a(this.f11497a.getInt("num_failed_fetches", 0), new Date(this.f11497a.getLong("backoff_end_time_in_millis", -1L)));
        }
        return aVar;
    }

    @Nullable
    public String b() {
        return this.f11497a.getString("last_fetch_etag", null);
    }

    public Date c() {
        return new Date(this.f11497a.getLong("last_fetch_time_in_millis", -1L));
    }

    @WorkerThread
    public void clear() {
        synchronized (this.b) {
            this.f11497a.edit().clear().commit();
        }
    }

    public void d() {
        e(0, e);
    }

    public void e(int i, Date date) {
        synchronized (this.c) {
            this.f11497a.edit().putInt("num_failed_fetches", i).putLong("backoff_end_time_in_millis", date.getTime()).apply();
        }
    }

    public void f(String str) {
        synchronized (this.b) {
            this.f11497a.edit().putString("last_fetch_etag", str).apply();
        }
    }

    public void g() {
        synchronized (this.b) {
            this.f11497a.edit().putInt("last_fetch_status", 1).apply();
        }
    }

    public long getFetchTimeoutInSeconds() {
        return this.f11497a.getLong("fetch_timeout_in_seconds", 60L);
    }

    public FirebaseRemoteConfigInfo getInfo() {
        FirebaseRemoteConfigInfoImpl build;
        synchronized (this.b) {
            long j = this.f11497a.getLong("last_fetch_time_in_millis", -1L);
            int i = this.f11497a.getInt("last_fetch_status", 0);
            build = FirebaseRemoteConfigInfoImpl.a().b(i).withLastSuccessfulFetchTimeInMillis(j).a(new FirebaseRemoteConfigSettings.Builder().setFetchTimeoutInSeconds(this.f11497a.getLong("fetch_timeout_in_seconds", 60L)).setMinimumFetchIntervalInSeconds(this.f11497a.getLong("minimum_fetch_interval_in_seconds", ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS)).build()).build();
        }
        return build;
    }

    public long getMinimumFetchIntervalInSeconds() {
        return this.f11497a.getLong("minimum_fetch_interval_in_seconds", ConfigFetchHandler.DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS);
    }

    public void h(Date date) {
        synchronized (this.b) {
            this.f11497a.edit().putInt("last_fetch_status", -1).putLong("last_fetch_time_in_millis", date.getTime()).apply();
        }
    }

    public void i() {
        synchronized (this.b) {
            this.f11497a.edit().putInt("last_fetch_status", 2).apply();
        }
    }

    @WorkerThread
    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.b) {
            this.f11497a.edit().putLong("fetch_timeout_in_seconds", firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong("minimum_fetch_interval_in_seconds", firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).commit();
        }
    }

    public void setConfigSettingsWithoutWaitingOnDiskWrite(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.b) {
            this.f11497a.edit().putLong("fetch_timeout_in_seconds", firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong("minimum_fetch_interval_in_seconds", firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).apply();
        }
    }
}
