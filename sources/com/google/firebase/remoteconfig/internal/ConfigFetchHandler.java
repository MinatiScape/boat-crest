package com.google.firebase.remoteconfig.internal;

import android.text.format.DateUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public class ConfigFetchHandler {
    public static final long DEFAULT_MINIMUM_FETCH_INTERVAL_IN_SECONDS = TimeUnit.HOURS.toSeconds(12);
    @VisibleForTesting
    public static final int[] j = {2, 4, 8, 16, 32, 64, 128, 256};

    /* renamed from: a  reason: collision with root package name */
    public final FirebaseInstallationsApi f11493a;
    public final Provider<AnalyticsConnector> b;
    public final Executor c;
    public final Clock d;
    public final Random e;
    public final ConfigCacheClient f;
    public final ConfigFetchHttpClient g;
    public final ConfigMetadataClient h;
    public final Map<String, String> i;

    /* loaded from: classes10.dex */
    public static class FetchResponse {

        /* renamed from: a  reason: collision with root package name */
        public final int f11494a;
        public final ConfigContainer b;
        @Nullable
        public final String c;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes10.dex */
        public @interface Status {
            public static final int BACKEND_HAS_NO_UPDATES = 1;
            public static final int BACKEND_UPDATES_FETCHED = 0;
            public static final int LOCAL_STORAGE_USED = 2;
        }

        public FetchResponse(Date date, int i, ConfigContainer configContainer, @Nullable String str) {
            this.f11494a = i;
            this.b = configContainer;
            this.c = str;
        }

        public static FetchResponse forBackendHasNoUpdates(Date date) {
            return new FetchResponse(date, 1, null, null);
        }

        public static FetchResponse forBackendUpdatesFetched(ConfigContainer configContainer, String str) {
            return new FetchResponse(configContainer.getFetchTime(), 0, configContainer, str);
        }

        public static FetchResponse forLocalStorageUsed(Date date) {
            return new FetchResponse(date, 2, null, null);
        }

        @Nullable
        public String a() {
            return this.c;
        }

        public int b() {
            return this.f11494a;
        }

        public ConfigContainer getFetchedConfigs() {
            return this.b;
        }
    }

    public ConfigFetchHandler(FirebaseInstallationsApi firebaseInstallationsApi, Provider<AnalyticsConnector> provider, Executor executor, Clock clock, Random random, ConfigCacheClient configCacheClient, ConfigFetchHttpClient configFetchHttpClient, ConfigMetadataClient configMetadataClient, Map<String, String> map) {
        this.f11493a = firebaseInstallationsApi;
        this.b = provider;
        this.c = executor;
        this.d = clock;
        this.e = random;
        this.f = configCacheClient;
        this.g = configFetchHttpClient;
        this.h = configMetadataClient;
        this.i = map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task q(Task task, Task task2, Date date, Task task3) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation ID for fetch.", task.getException()));
        }
        if (!task2.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation auth token for fetch.", task2.getException()));
        }
        return i((String) task.getResult(), ((InstallationTokenResult) task2.getResult()).getToken(), date);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task r(Date date, Task task) throws Exception {
        v(task, date);
        return task;
    }

    public final boolean e(long j2, Date date) {
        Date c = this.h.c();
        if (c.equals(ConfigMetadataClient.d)) {
            return false;
        }
        return date.before(new Date(c.getTime() + TimeUnit.SECONDS.toMillis(j2)));
    }

    public final FirebaseRemoteConfigServerException f(FirebaseRemoteConfigServerException firebaseRemoteConfigServerException) throws FirebaseRemoteConfigClientException {
        String str;
        int httpStatusCode = firebaseRemoteConfigServerException.getHttpStatusCode();
        if (httpStatusCode == 401) {
            str = "The request did not have the required credentials. Please make sure your google-services.json is valid.";
        } else if (httpStatusCode == 403) {
            str = "The user is not authorized to access the project. Please make sure you are using the API key that corresponds to your Firebase project.";
        } else if (httpStatusCode == 429) {
            throw new FirebaseRemoteConfigClientException("The throttled response from the server was not handled correctly by the FRC SDK.");
        } else {
            if (httpStatusCode != 500) {
                switch (httpStatusCode) {
                    case 502:
                    case 503:
                    case 504:
                        str = "The server is unavailable. Please try again later.";
                        break;
                    default:
                        str = "The server returned an unexpected error.";
                        break;
                }
            } else {
                str = "There was an internal server error.";
            }
        }
        int httpStatusCode2 = firebaseRemoteConfigServerException.getHttpStatusCode();
        return new FirebaseRemoteConfigServerException(httpStatusCode2, "Fetch failed: " + str, firebaseRemoteConfigServerException);
    }

    public Task<FetchResponse> fetch() {
        return fetch(this.h.getMinimumFetchIntervalInSeconds());
    }

    public final String g(long j2) {
        return String.format("Fetch is throttled. Please wait before calling fetch again: %s", DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(j2)));
    }

    @VisibleForTesting
    public Provider<AnalyticsConnector> getAnalyticsConnector() {
        return this.b;
    }

    @WorkerThread
    public final FetchResponse h(String str, String str2, Date date) throws FirebaseRemoteConfigException {
        try {
            FetchResponse fetch = this.g.fetch(this.g.c(), str, str2, m(), this.h.b(), this.i, date);
            if (fetch.a() != null) {
                this.h.f(fetch.a());
            }
            this.h.d();
            return fetch;
        } catch (FirebaseRemoteConfigServerException e) {
            ConfigMetadataClient.a t = t(e.getHttpStatusCode(), date);
            if (s(t, e.getHttpStatusCode())) {
                throw new FirebaseRemoteConfigFetchThrottledException(t.a().getTime());
            }
            throw f(e);
        }
    }

    public final Task<FetchResponse> i(String str, String str2, Date date) {
        try {
            final FetchResponse h = h(str, str2, date);
            if (h.b() != 0) {
                return Tasks.forResult(h);
            }
            return this.f.put(h.getFetchedConfigs()).onSuccessTask(this.c, new SuccessContinuation() { // from class: com.google.firebase.remoteconfig.internal.g
                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    Task forResult;
                    ConfigContainer configContainer = (ConfigContainer) obj;
                    forResult = Tasks.forResult(ConfigFetchHandler.FetchResponse.this);
                    return forResult;
                }
            });
        } catch (FirebaseRemoteConfigException e) {
            return Tasks.forException(e);
        }
    }

    /* renamed from: j */
    public final Task<FetchResponse> o(Task<ConfigContainer> task, long j2) {
        Task continueWithTask;
        final Date date = new Date(this.d.currentTimeMillis());
        if (task.isSuccessful() && e(j2, date)) {
            return Tasks.forResult(FetchResponse.forLocalStorageUsed(date));
        }
        Date k = k(date);
        if (k != null) {
            continueWithTask = Tasks.forException(new FirebaseRemoteConfigFetchThrottledException(g(k.getTime() - date.getTime()), k.getTime()));
        } else {
            final Task<String> id = this.f11493a.getId();
            final Task<InstallationTokenResult> token = this.f11493a.getToken(false);
            continueWithTask = Tasks.whenAllComplete(id, token).continueWithTask(this.c, new Continuation() { // from class: com.google.firebase.remoteconfig.internal.e
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task2) {
                    Task q;
                    q = ConfigFetchHandler.this.q(id, token, date, task2);
                    return q;
                }
            });
        }
        return continueWithTask.continueWithTask(this.c, new Continuation() { // from class: com.google.firebase.remoteconfig.internal.f
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task2) {
                Task r;
                r = ConfigFetchHandler.this.r(date, task2);
                return r;
            }
        });
    }

    @Nullable
    public final Date k(Date date) {
        Date a2 = this.h.a().a();
        if (date.before(a2)) {
            return a2;
        }
        return null;
    }

    public final long l(int i) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        int[] iArr = j;
        long millis = timeUnit.toMillis(iArr[Math.min(i, iArr.length) - 1]);
        return (millis / 2) + this.e.nextInt((int) millis);
    }

    @WorkerThread
    public final Map<String, String> m() {
        HashMap hashMap = new HashMap();
        AnalyticsConnector analyticsConnector = this.b.get();
        if (analyticsConnector == null) {
            return hashMap;
        }
        for (Map.Entry<String, Object> entry : analyticsConnector.getUserProperties(false).entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        return hashMap;
    }

    public final boolean n(int i) {
        return i == 429 || i == 502 || i == 503 || i == 504;
    }

    public final boolean s(ConfigMetadataClient.a aVar, int i) {
        return aVar.b() > 1 || i == 429;
    }

    public final ConfigMetadataClient.a t(int i, Date date) {
        if (n(i)) {
            u(date);
        }
        return this.h.a();
    }

    public final void u(Date date) {
        int b = this.h.a().b() + 1;
        this.h.e(b, new Date(date.getTime() + l(b)));
    }

    public final void v(Task<FetchResponse> task, Date date) {
        if (task.isSuccessful()) {
            this.h.h(date);
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            return;
        }
        if (exception instanceof FirebaseRemoteConfigFetchThrottledException) {
            this.h.i();
        } else {
            this.h.g();
        }
    }

    public Task<FetchResponse> fetch(final long j2) {
        return this.f.get().continueWithTask(this.c, new Continuation() { // from class: com.google.firebase.remoteconfig.internal.d
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                Task o;
                o = ConfigFetchHandler.this.o(j2, task);
                return o;
            }
        });
    }
}
