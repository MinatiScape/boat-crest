package com.google.firebase.crashlytics;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponentDeferredProxy;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.AppData;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.ExecutorUtils;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.settings.SettingsController;
import com.google.firebase.crashlytics.internal.unity.ResourceUnityVersionProvider;
import com.google.firebase.inject.Deferred;
import com.google.firebase.installations.FirebaseInstallationsApi;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
/* loaded from: classes10.dex */
public class FirebaseCrashlytics {
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final CrashlyticsCore f11118a;

    /* loaded from: classes10.dex */
    public class a implements Continuation<Void, Object> {
        @Override // com.google.android.gms.tasks.Continuation
        public Object then(@NonNull Task<Void> task) throws Exception {
            if (task.isSuccessful()) {
                return null;
            }
            Logger.getLogger().e("Error fetching settings.", task.getException());
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Callable<Void> {
        public final /* synthetic */ boolean h;
        public final /* synthetic */ CrashlyticsCore i;
        public final /* synthetic */ SettingsController j;

        public b(boolean z, CrashlyticsCore crashlyticsCore, SettingsController settingsController) {
            this.h = z;
            this.i = crashlyticsCore;
            this.j = settingsController;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            if (this.h) {
                this.i.doBackgroundInitializationAsync(this.j);
                return null;
            }
            return null;
        }
    }

    public FirebaseCrashlytics(@NonNull CrashlyticsCore crashlyticsCore) {
        this.f11118a = crashlyticsCore;
    }

    @Nullable
    public static FirebaseCrashlytics a(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseInstallationsApi firebaseInstallationsApi, @NonNull Deferred<CrashlyticsNativeComponent> deferred, @NonNull Deferred<AnalyticsConnector> deferred2) {
        Context applicationContext = firebaseApp.getApplicationContext();
        String packageName = applicationContext.getPackageName();
        Logger logger = Logger.getLogger();
        logger.i("Initializing Firebase Crashlytics " + CrashlyticsCore.getVersion() + " for " + packageName);
        DataCollectionArbiter dataCollectionArbiter = new DataCollectionArbiter(firebaseApp);
        IdManager idManager = new IdManager(applicationContext, packageName, firebaseInstallationsApi, dataCollectionArbiter);
        CrashlyticsNativeComponentDeferredProxy crashlyticsNativeComponentDeferredProxy = new CrashlyticsNativeComponentDeferredProxy(deferred);
        AnalyticsDeferredProxy analyticsDeferredProxy = new AnalyticsDeferredProxy(deferred2);
        CrashlyticsCore crashlyticsCore = new CrashlyticsCore(firebaseApp, idManager, crashlyticsNativeComponentDeferredProxy, dataCollectionArbiter, analyticsDeferredProxy.getDeferredBreadcrumbSource(), analyticsDeferredProxy.getAnalyticsEventLogger(), ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler"));
        String applicationId = firebaseApp.getOptions().getApplicationId();
        String mappingFileId = CommonUtils.getMappingFileId(applicationContext);
        Logger logger2 = Logger.getLogger();
        logger2.d("Mapping file ID is: " + mappingFileId);
        try {
            AppData create = AppData.create(applicationContext, idManager, applicationId, mappingFileId, new ResourceUnityVersionProvider(applicationContext));
            Logger logger3 = Logger.getLogger();
            logger3.v("Installer package name is: " + create.installerPackageName);
            ExecutorService buildSingleThreadExecutorService = ExecutorUtils.buildSingleThreadExecutorService("com.google.firebase.crashlytics.startup");
            SettingsController create2 = SettingsController.create(applicationContext, applicationId, idManager, new HttpRequestFactory(), create.versionCode, create.versionName, dataCollectionArbiter);
            create2.loadSettingsData(buildSingleThreadExecutorService).continueWith(buildSingleThreadExecutorService, new a());
            Tasks.call(buildSingleThreadExecutorService, new b(crashlyticsCore.onPreExecute(create, create2), crashlyticsCore, create2));
            return new FirebaseCrashlytics(crashlyticsCore);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.getLogger().e("Error retrieving app package info.", e);
            return null;
        }
    }

    @NonNull
    public static FirebaseCrashlytics getInstance() {
        FirebaseCrashlytics firebaseCrashlytics = (FirebaseCrashlytics) FirebaseApp.getInstance().get(FirebaseCrashlytics.class);
        Objects.requireNonNull(firebaseCrashlytics, "FirebaseCrashlytics component is not present.");
        return firebaseCrashlytics;
    }

    @NonNull
    public Task<Boolean> checkForUnsentReports() {
        return this.f11118a.checkForUnsentReports();
    }

    public void deleteUnsentReports() {
        this.f11118a.deleteUnsentReports();
    }

    public boolean didCrashOnPreviousExecution() {
        return this.f11118a.didCrashOnPreviousExecution();
    }

    public void log(@NonNull String str) {
        this.f11118a.log(str);
    }

    public void recordException(@NonNull Throwable th) {
        if (th == null) {
            Logger.getLogger().w("A null value was passed to recordException. Ignoring.");
        } else {
            this.f11118a.logException(th);
        }
    }

    public void sendUnsentReports() {
        this.f11118a.sendUnsentReports();
    }

    public void setCrashlyticsCollectionEnabled(boolean z) {
        this.f11118a.setCrashlyticsCollectionEnabled(Boolean.valueOf(z));
    }

    public void setCustomKey(@NonNull String str, boolean z) {
        this.f11118a.setCustomKey(str, Boolean.toString(z));
    }

    public void setCustomKeys(@NonNull CustomKeysAndValues customKeysAndValues) {
        this.f11118a.setCustomKeys(customKeysAndValues.f11116a);
    }

    public void setUserId(@NonNull String str) {
        this.f11118a.setUserId(str);
    }

    public void setCrashlyticsCollectionEnabled(@Nullable Boolean bool) {
        this.f11118a.setCrashlyticsCollectionEnabled(bool);
    }

    public void setCustomKey(@NonNull String str, double d) {
        this.f11118a.setCustomKey(str, Double.toString(d));
    }

    public void setCustomKey(@NonNull String str, float f) {
        this.f11118a.setCustomKey(str, Float.toString(f));
    }

    public void setCustomKey(@NonNull String str, int i) {
        this.f11118a.setCustomKey(str, Integer.toString(i));
    }

    public void setCustomKey(@NonNull String str, long j) {
        this.f11118a.setCustomKey(str, Long.toString(j));
    }

    public void setCustomKey(@NonNull String str, @NonNull String str2) {
        this.f11118a.setCustomKey(str, str2);
    }
}
