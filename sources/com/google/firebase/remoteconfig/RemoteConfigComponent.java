package com.google.firebase.remoteconfig;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.BiConsumer;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigFetchHttpClient;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.ConfigStorageClient;
import com.google.firebase.remoteconfig.internal.Personalization;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@KeepForSdk
/* loaded from: classes10.dex */
public class RemoteConfigComponent {
    public static final String ACTIVATE_FILE_NAME = "activate";
    public static final long CONNECTION_TIMEOUT_IN_SECONDS = 60;
    public static final String DEFAULTS_FILE_NAME = "defaults";
    @VisibleForTesting
    public static final String DEFAULT_NAMESPACE = "firebase";
    public static final String FETCH_FILE_NAME = "fetch";
    public static final Clock j = DefaultClock.getInstance();
    public static final Random k = new Random();
    @GuardedBy("this")

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, FirebaseRemoteConfig> f11481a;
    public final Context b;
    public final ExecutorService c;
    public final FirebaseApp d;
    public final FirebaseInstallationsApi e;
    public final FirebaseABTesting f;
    @Nullable
    public final Provider<AnalyticsConnector> g;
    public final String h;
    @GuardedBy("this")
    public Map<String, String> i;

    public RemoteConfigComponent(Context context, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Provider<AnalyticsConnector> provider) {
        this(context, Executors.newCachedThreadPool(), firebaseApp, firebaseInstallationsApi, firebaseABTesting, provider, true);
    }

    @VisibleForTesting
    public static ConfigMetadataClient h(Context context, String str, String str2) {
        return new ConfigMetadataClient(context.getSharedPreferences(String.format("%s_%s_%s_%s", FirebaseABTesting.OriginService.REMOTE_CONFIG, str, str2, "settings"), 0));
    }

    @Nullable
    public static Personalization i(FirebaseApp firebaseApp, String str, Provider<AnalyticsConnector> provider) {
        if (k(firebaseApp) && str.equals(DEFAULT_NAMESPACE)) {
            return new Personalization(provider);
        }
        return null;
    }

    public static boolean j(FirebaseApp firebaseApp, String str) {
        return str.equals(DEFAULT_NAMESPACE) && k(firebaseApp);
    }

    public static boolean k(FirebaseApp firebaseApp) {
        return firebaseApp.getName().equals(FirebaseApp.DEFAULT_APP_NAME);
    }

    public static /* synthetic */ AnalyticsConnector l() {
        return null;
    }

    @VisibleForTesting
    public synchronized FirebaseRemoteConfig b(FirebaseApp firebaseApp, String str, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient) {
        if (!this.f11481a.containsKey(str)) {
            FirebaseRemoteConfig firebaseRemoteConfig = new FirebaseRemoteConfig(this.b, firebaseApp, firebaseInstallationsApi, j(firebaseApp, str) ? firebaseABTesting : null, executor, configCacheClient, configCacheClient2, configCacheClient3, configFetchHandler, configGetParameterHandler, configMetadataClient);
            firebaseRemoteConfig.u();
            this.f11481a.put(str, firebaseRemoteConfig);
        }
        return this.f11481a.get(str);
    }

    public final ConfigCacheClient c(String str, String str2) {
        return ConfigCacheClient.getInstance(Executors.newCachedThreadPool(), ConfigStorageClient.getInstance(this.b, String.format("%s_%s_%s_%s.json", FirebaseABTesting.OriginService.REMOTE_CONFIG, this.h, str, str2)));
    }

    public FirebaseRemoteConfig d() {
        return get(DEFAULT_NAMESPACE);
    }

    @VisibleForTesting
    public synchronized ConfigFetchHandler e(String str, ConfigCacheClient configCacheClient, ConfigMetadataClient configMetadataClient) {
        return new ConfigFetchHandler(this.e, k(this.d) ? this.g : new Provider() { // from class: com.google.firebase.remoteconfig.l
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                AnalyticsConnector l;
                l = RemoteConfigComponent.l();
                return l;
            }
        }, this.c, j, k, configCacheClient, f(this.d.getOptions().getApiKey(), str, configMetadataClient), configMetadataClient, this.i);
    }

    @VisibleForTesting
    public ConfigFetchHttpClient f(String str, String str2, ConfigMetadataClient configMetadataClient) {
        return new ConfigFetchHttpClient(this.b, this.d.getOptions().getApplicationId(), str, str2, configMetadataClient.getFetchTimeoutInSeconds(), configMetadataClient.getFetchTimeoutInSeconds());
    }

    public final ConfigGetParameterHandler g(ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
        return new ConfigGetParameterHandler(this.c, configCacheClient, configCacheClient2);
    }

    @KeepForSdk
    @VisibleForTesting
    public synchronized FirebaseRemoteConfig get(String str) {
        ConfigCacheClient c;
        ConfigCacheClient c2;
        ConfigCacheClient c3;
        ConfigMetadataClient h;
        ConfigGetParameterHandler g;
        c = c(str, FETCH_FILE_NAME);
        c2 = c(str, ACTIVATE_FILE_NAME);
        c3 = c(str, DEFAULTS_FILE_NAME);
        h = h(this.b, this.h, str);
        g = g(c2, c3);
        final Personalization i = i(this.d, str, this.g);
        if (i != null) {
            g.addListener(new BiConsumer() { // from class: com.google.firebase.remoteconfig.k
                @Override // com.google.android.gms.common.util.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    Personalization.this.logArmActive((String) obj, (ConfigContainer) obj2);
                }
            });
        }
        return b(this.d, str, this.e, this.f, this.c, c, c2, c3, e(str, c, h), g, h);
    }

    @VisibleForTesting
    public synchronized void setCustomHeaders(Map<String, String> map) {
        this.i = map;
    }

    @VisibleForTesting
    public RemoteConfigComponent(Context context, ExecutorService executorService, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Provider<AnalyticsConnector> provider, boolean z) {
        this.f11481a = new HashMap();
        this.i = new HashMap();
        this.b = context;
        this.c = executorService;
        this.d = firebaseApp;
        this.e = firebaseInstallationsApi;
        this.f = firebaseABTesting;
        this.g = provider;
        this.h = firebaseApp.getOptions().getApplicationId();
        if (z) {
            Tasks.call(executorService, new Callable() { // from class: com.google.firebase.remoteconfig.m
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return RemoteConfigComponent.this.d();
                }
            });
        }
    }
}
