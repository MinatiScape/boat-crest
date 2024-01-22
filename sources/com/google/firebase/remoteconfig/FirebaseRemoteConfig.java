package com.google.firebase.remoteconfig;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.XmlRes;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.AbtException;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.DefaultsXmlParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final String TAG = "FirebaseRemoteConfig";
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;

    /* renamed from: a  reason: collision with root package name */
    public final Context f11478a;
    @Nullable
    public final FirebaseABTesting b;
    public final Executor c;
    public final ConfigCacheClient d;
    public final ConfigCacheClient e;
    public final ConfigCacheClient f;
    public final ConfigFetchHandler g;
    public final ConfigGetParameterHandler h;
    public final ConfigMetadataClient i;
    public final FirebaseInstallationsApi j;

    public FirebaseRemoteConfig(Context context, FirebaseApp firebaseApp, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable FirebaseABTesting firebaseABTesting, Executor executor, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient) {
        this.f11478a = context;
        this.j = firebaseInstallationsApi;
        this.b = firebaseABTesting;
        this.c = executor;
        this.d = configCacheClient;
        this.e = configCacheClient2;
        this.f = configCacheClient3;
        this.g = configFetchHandler;
        this.h = configGetParameterHandler;
        this.i = configMetadataClient;
    }

    @NonNull
    public static FirebaseRemoteConfig getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static boolean j(ConfigContainer configContainer, @Nullable ConfigContainer configContainer2) {
        return configContainer2 == null || !configContainer.getFetchTime().equals(configContainer2.getFetchTime());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task k(Task task, Task task2, Task task3) throws Exception {
        if (task.isSuccessful() && task.getResult() != null) {
            ConfigContainer configContainer = (ConfigContainer) task.getResult();
            if (task2.isSuccessful() && !j(configContainer, (ConfigContainer) task2.getResult())) {
                return Tasks.forResult(Boolean.FALSE);
            }
            return this.e.put(configContainer).continueWith(this.c, new Continuation() { // from class: com.google.firebase.remoteconfig.b
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task4) {
                    boolean s;
                    s = FirebaseRemoteConfig.this.s(task4);
                    return Boolean.valueOf(s);
                }
            });
        }
        return Tasks.forResult(Boolean.FALSE);
    }

    public static /* synthetic */ FirebaseRemoteConfigInfo l(Task task, Task task2) throws Exception {
        return (FirebaseRemoteConfigInfo) task.getResult();
    }

    public static /* synthetic */ Task m(ConfigFetchHandler.FetchResponse fetchResponse) throws Exception {
        return Tasks.forResult(null);
    }

    public static /* synthetic */ Task n(ConfigFetchHandler.FetchResponse fetchResponse) throws Exception {
        return Tasks.forResult(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Task o(Void r1) throws Exception {
        return activate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void p() throws Exception {
        this.e.clear();
        this.d.clear();
        this.f.clear();
        this.i.clear();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Void q(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) throws Exception {
        this.i.setConfigSettings(firebaseRemoteConfigSettings);
        return null;
    }

    public static /* synthetic */ Task r(ConfigContainer configContainer) throws Exception {
        return Tasks.forResult(null);
    }

    @VisibleForTesting
    public static List<Map<String, String>> v(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            HashMap hashMap = new HashMap();
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, jSONObject.getString(next));
            }
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    @NonNull
    public Task<Boolean> activate() {
        final Task<ConfigContainer> task = this.d.get();
        final Task<ConfigContainer> task2 = this.e.get();
        return Tasks.whenAllComplete(task, task2).continueWithTask(this.c, new Continuation() { // from class: com.google.firebase.remoteconfig.c
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task3) {
                Task k;
                k = FirebaseRemoteConfig.this.k(task, task2, task3);
                return k;
            }
        });
    }

    @NonNull
    public Task<FirebaseRemoteConfigInfo> ensureInitialized() {
        Task<ConfigContainer> task = this.e.get();
        Task<ConfigContainer> task2 = this.f.get();
        Task<ConfigContainer> task3 = this.d.get();
        final Task call = Tasks.call(this.c, new Callable() { // from class: com.google.firebase.remoteconfig.h
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FirebaseRemoteConfig.this.getInfo();
            }
        });
        return Tasks.whenAllComplete(task, task2, task3, call, this.j.getId(), this.j.getToken(false)).continueWith(this.c, new Continuation() { // from class: com.google.firebase.remoteconfig.a
            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task4) {
                FirebaseRemoteConfigInfo l;
                l = FirebaseRemoteConfig.l(Task.this, task4);
                return l;
            }
        });
    }

    @NonNull
    public Task<Void> fetch() {
        return this.g.fetch().onSuccessTask(new SuccessContinuation() { // from class: com.google.firebase.remoteconfig.g
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                Task m;
                m = FirebaseRemoteConfig.m((ConfigFetchHandler.FetchResponse) obj);
                return m;
            }
        });
    }

    @NonNull
    public Task<Boolean> fetchAndActivate() {
        return fetch().onSuccessTask(this.c, new SuccessContinuation() { // from class: com.google.firebase.remoteconfig.d
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                Task o;
                o = FirebaseRemoteConfig.this.o((Void) obj);
                return o;
            }
        });
    }

    @NonNull
    public Map<String, FirebaseRemoteConfigValue> getAll() {
        return this.h.getAll();
    }

    public boolean getBoolean(@NonNull String str) {
        return this.h.getBoolean(str);
    }

    public double getDouble(@NonNull String str) {
        return this.h.getDouble(str);
    }

    @NonNull
    public FirebaseRemoteConfigInfo getInfo() {
        return this.i.getInfo();
    }

    @NonNull
    public Set<String> getKeysByPrefix(@NonNull String str) {
        return this.h.getKeysByPrefix(str);
    }

    public long getLong(@NonNull String str) {
        return this.h.getLong(str);
    }

    @NonNull
    public String getString(@NonNull String str) {
        return this.h.getString(str);
    }

    @NonNull
    public FirebaseRemoteConfigValue getValue(@NonNull String str) {
        return this.h.getValue(str);
    }

    @NonNull
    public Task<Void> reset() {
        return Tasks.call(this.c, new Callable() { // from class: com.google.firebase.remoteconfig.i
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void p;
                p = FirebaseRemoteConfig.this.p();
                return p;
            }
        });
    }

    public final boolean s(Task<ConfigContainer> task) {
        if (task.isSuccessful()) {
            this.d.clear();
            if (task.getResult() != null) {
                w(task.getResult().getAbtExperiments());
                return true;
            }
            Log.e(TAG, "Activated configs written to disk are null.");
            return true;
        }
        return false;
    }

    @NonNull
    public Task<Void> setConfigSettingsAsync(@NonNull final FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        return Tasks.call(this.c, new Callable() { // from class: com.google.firebase.remoteconfig.j
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Void q;
                q = FirebaseRemoteConfig.this.q(firebaseRemoteConfigSettings);
                return q;
            }
        });
    }

    @NonNull
    public Task<Void> setDefaultsAsync(@NonNull Map<String, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof byte[]) {
                hashMap.put(entry.getKey(), new String((byte[]) value));
            } else {
                hashMap.put(entry.getKey(), value.toString());
            }
        }
        return t(hashMap);
    }

    public final Task<Void> t(Map<String, String> map) {
        try {
            return this.f.put(ConfigContainer.newBuilder().replaceConfigsWith(map).build()).onSuccessTask(new SuccessContinuation() { // from class: com.google.firebase.remoteconfig.e
                @Override // com.google.android.gms.tasks.SuccessContinuation
                public final Task then(Object obj) {
                    Task r;
                    r = FirebaseRemoteConfig.r((ConfigContainer) obj);
                    return r;
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "The provided defaults map could not be processed.", e);
            return Tasks.forResult(null);
        }
    }

    public void u() {
        this.e.get();
        this.f.get();
        this.d.get();
    }

    @VisibleForTesting
    public void w(@NonNull JSONArray jSONArray) {
        if (this.b == null) {
            return;
        }
        try {
            this.b.replaceAllExperiments(v(jSONArray));
        } catch (AbtException e) {
            Log.w(TAG, "Could not update ABT experiments.", e);
        } catch (JSONException e2) {
            Log.e(TAG, "Could not parse ABT experiments from the JSON response.", e2);
        }
    }

    @NonNull
    public static FirebaseRemoteConfig getInstance(@NonNull FirebaseApp firebaseApp) {
        return ((RemoteConfigComponent) firebaseApp.get(RemoteConfigComponent.class)).d();
    }

    @NonNull
    public Task<Void> fetch(long j) {
        return this.g.fetch(j).onSuccessTask(new SuccessContinuation() { // from class: com.google.firebase.remoteconfig.f
            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                Task n;
                n = FirebaseRemoteConfig.n((ConfigFetchHandler.FetchResponse) obj);
                return n;
            }
        });
    }

    @NonNull
    public Task<Void> setDefaultsAsync(@XmlRes int i) {
        return t(DefaultsXmlParser.getDefaultsFromXml(this.f11478a, i));
    }
}
