package com.google.firebase.crashlytics.internal.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.common.SystemCurrentTimeProvider;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SettingsRequest;
import com.google.firebase.crashlytics.internal.settings.network.DefaultSettingsSpiCall;
import com.google.firebase.crashlytics.internal.settings.network.SettingsSpiCall;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class SettingsController implements SettingsDataProvider {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11256a;
    public final SettingsRequest b;
    public final SettingsJsonParser c;
    public final CurrentTimeProvider d;
    public final CachedSettingsIo e;
    public final SettingsSpiCall f;
    public final DataCollectionArbiter g;
    public final AtomicReference<Settings> h;
    public final AtomicReference<TaskCompletionSource<AppSettingsData>> i;

    /* loaded from: classes10.dex */
    public class a implements SuccessContinuation<Void, Void> {
        public a() {
        }

        @Override // com.google.android.gms.tasks.SuccessContinuation
        @NonNull
        /* renamed from: a */
        public Task<Void> then(@Nullable Void r5) throws Exception {
            JSONObject invoke = SettingsController.this.f.invoke(SettingsController.this.b, true);
            if (invoke != null) {
                SettingsData parseSettingsJson = SettingsController.this.c.parseSettingsJson(invoke);
                SettingsController.this.e.writeCachedSettings(parseSettingsJson.getExpiresAtMillis(), invoke);
                SettingsController.this.l(invoke, "Loaded settings: ");
                SettingsController settingsController = SettingsController.this;
                settingsController.m(settingsController.b.instanceId);
                SettingsController.this.h.set(parseSettingsJson);
                ((TaskCompletionSource) SettingsController.this.i.get()).trySetResult(parseSettingsJson.getAppSettingsData());
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                taskCompletionSource.trySetResult(parseSettingsJson.getAppSettingsData());
                SettingsController.this.i.set(taskCompletionSource);
            }
            return Tasks.forResult(null);
        }
    }

    public SettingsController(Context context, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonParser settingsJsonParser, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall, DataCollectionArbiter dataCollectionArbiter) {
        AtomicReference<Settings> atomicReference = new AtomicReference<>();
        this.h = atomicReference;
        this.i = new AtomicReference<>(new TaskCompletionSource());
        this.f11256a = context;
        this.b = settingsRequest;
        this.d = currentTimeProvider;
        this.c = settingsJsonParser;
        this.e = cachedSettingsIo;
        this.f = settingsSpiCall;
        this.g = dataCollectionArbiter;
        atomicReference.set(com.google.firebase.crashlytics.internal.settings.a.e(currentTimeProvider));
    }

    public static SettingsController create(Context context, String str, IdManager idManager, HttpRequestFactory httpRequestFactory, String str2, String str3, DataCollectionArbiter dataCollectionArbiter) {
        String installerPackageName = idManager.getInstallerPackageName();
        SystemCurrentTimeProvider systemCurrentTimeProvider = new SystemCurrentTimeProvider();
        return new SettingsController(context, new SettingsRequest(str, idManager.getModelName(), idManager.getOsBuildVersionString(), idManager.getOsDisplayVersionString(), idManager, CommonUtils.createInstanceIdFrom(CommonUtils.getMappingFileId(context), str, str3, str2), str3, str2, DeliveryMechanism.determineFrom(installerPackageName).getId()), systemCurrentTimeProvider, new SettingsJsonParser(systemCurrentTimeProvider), new CachedSettingsIo(context), new DefaultSettingsSpiCall(String.format(Locale.US, "https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/%s/settings", str), httpRequestFactory), dataCollectionArbiter);
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsDataProvider
    public Task<AppSettingsData> getAppSettings() {
        return this.i.get().getTask();
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsDataProvider
    public Settings getSettings() {
        return this.h.get();
    }

    public boolean i() {
        return !k().equals(this.b.instanceId);
    }

    public final SettingsData j(SettingsCacheBehavior settingsCacheBehavior) {
        SettingsData settingsData = null;
        try {
            if (!SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(settingsCacheBehavior)) {
                JSONObject readCachedSettings = this.e.readCachedSettings();
                if (readCachedSettings != null) {
                    SettingsData parseSettingsJson = this.c.parseSettingsJson(readCachedSettings);
                    if (parseSettingsJson != null) {
                        l(readCachedSettings, "Loaded cached settings: ");
                        long currentTimeMillis = this.d.getCurrentTimeMillis();
                        if (!SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(settingsCacheBehavior) && parseSettingsJson.isExpired(currentTimeMillis)) {
                            Logger.getLogger().v("Cached settings have expired.");
                        }
                        try {
                            Logger.getLogger().v("Returning cached settings.");
                            settingsData = parseSettingsJson;
                        } catch (Exception e) {
                            e = e;
                            settingsData = parseSettingsJson;
                            Logger.getLogger().e("Failed to get cached settings", e);
                            return settingsData;
                        }
                    } else {
                        Logger.getLogger().e("Failed to parse cached settings data.", null);
                    }
                } else {
                    Logger.getLogger().d("No cached settings data found.");
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        return settingsData;
    }

    public final String k() {
        return CommonUtils.getSharedPrefs(this.f11256a).getString("existing_instance_identifier", "");
    }

    public final void l(JSONObject jSONObject, String str) throws JSONException {
        Logger logger = Logger.getLogger();
        logger.d(str + jSONObject.toString());
    }

    public Task<Void> loadSettingsData(Executor executor) {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE, executor);
    }

    @SuppressLint({"CommitPrefEdits"})
    public final boolean m(String str) {
        SharedPreferences.Editor edit = CommonUtils.getSharedPrefs(this.f11256a).edit();
        edit.putString("existing_instance_identifier", str);
        edit.apply();
        return true;
    }

    public Task<Void> loadSettingsData(SettingsCacheBehavior settingsCacheBehavior, Executor executor) {
        SettingsData j;
        if (!i() && (j = j(settingsCacheBehavior)) != null) {
            this.h.set(j);
            this.i.get().trySetResult(j.getAppSettingsData());
            return Tasks.forResult(null);
        }
        SettingsData j2 = j(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
        if (j2 != null) {
            this.h.set(j2);
            this.i.get().trySetResult(j2.getAppSettingsData());
        }
        return this.g.waitForDataCollectionPermission().onSuccessTask(executor, new a());
    }
}
