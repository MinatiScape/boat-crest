package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.BuildConfig;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler;
import com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbSource;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.persistence.FileStoreImpl;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.stacktrace.MiddleOutFallbackStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.RemoveRepeatsStrategy;
import java.io.File;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes10.dex */
public class CrashlyticsCore {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11133a;
    public final DataCollectionArbiter b;
    @VisibleForTesting
    public final BreadcrumbSource breadcrumbSource;
    public final long c = System.currentTimeMillis();
    public i d;
    public i e;
    public boolean f;
    public g g;
    public final IdManager h;
    public final AnalyticsEventLogger i;
    public final ExecutorService j;
    public final com.google.firebase.crashlytics.internal.common.e k;
    public final CrashlyticsNativeComponent l;

    /* loaded from: classes10.dex */
    public class a implements Callable<Task<Void>> {
        public final /* synthetic */ SettingsDataProvider h;

        public a(SettingsDataProvider settingsDataProvider) {
            this.h = settingsDataProvider;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Task<Void> call() throws Exception {
            return CrashlyticsCore.this.f(this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public final /* synthetic */ SettingsDataProvider h;

        public b(SettingsDataProvider settingsDataProvider) {
            this.h = settingsDataProvider;
        }

        @Override // java.lang.Runnable
        public void run() {
            CrashlyticsCore.this.f(this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Callable<Boolean> {
        public c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() throws Exception {
            try {
                boolean d = CrashlyticsCore.this.d.d();
                if (!d) {
                    Logger.getLogger().w("Initialization marker file was not properly removed.");
                }
                return Boolean.valueOf(d);
            } catch (Exception e) {
                Logger.getLogger().e("Problem encountered deleting Crashlytics initialization marker.", e);
                return Boolean.FALSE;
            }
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Callable<Boolean> {
        public d() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Boolean call() throws Exception {
            return Boolean.valueOf(CrashlyticsCore.this.g.v());
        }
    }

    /* loaded from: classes10.dex */
    public static final class e implements LogFileManager.DirectoryProvider {

        /* renamed from: a  reason: collision with root package name */
        public final FileStore f11134a;

        public e(FileStore fileStore) {
            this.f11134a = fileStore;
        }

        @Override // com.google.firebase.crashlytics.internal.log.LogFileManager.DirectoryProvider
        public File getLogFileDir() {
            File file = new File(this.f11134a.getFilesDir(), "log-files");
            if (!file.exists()) {
                file.mkdirs();
            }
            return file;
        }
    }

    public CrashlyticsCore(FirebaseApp firebaseApp, IdManager idManager, CrashlyticsNativeComponent crashlyticsNativeComponent, DataCollectionArbiter dataCollectionArbiter, BreadcrumbSource breadcrumbSource, AnalyticsEventLogger analyticsEventLogger, ExecutorService executorService) {
        this.b = dataCollectionArbiter;
        this.f11133a = firebaseApp.getApplicationContext();
        this.h = idManager;
        this.l = crashlyticsNativeComponent;
        this.breadcrumbSource = breadcrumbSource;
        this.i = analyticsEventLogger;
        this.j = executorService;
        this.k = new com.google.firebase.crashlytics.internal.common.e(executorService);
    }

    public static String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public static boolean h(String str, boolean z) {
        if (!z) {
            Logger.getLogger().v("Configured not to require a build ID.");
            return true;
        } else if (TextUtils.isEmpty(str)) {
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, ".     |  | ");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".   \\ |  | /");
            Log.e(Logger.TAG, ".    \\    /");
            Log.e(Logger.TAG, ".     \\  /");
            Log.e(Logger.TAG, ".      \\/");
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, "The Crashlytics build ID is missing. This occurs when Crashlytics tooling is absent from your app's build configuration. Please review Crashlytics onboarding instructions and ensure you have a valid Crashlytics account.");
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, ".      /\\");
            Log.e(Logger.TAG, ".     /  \\");
            Log.e(Logger.TAG, ".    /    \\");
            Log.e(Logger.TAG, ".   / |  | \\");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".");
            return false;
        } else {
            return true;
        }
    }

    @NonNull
    public Task<Boolean> checkForUnsentReports() {
        return this.g.p();
    }

    public final void d() {
        try {
            this.f = Boolean.TRUE.equals((Boolean) Utils.awaitEvenIfOnMainThread(this.k.h(new d())));
        } catch (Exception unused) {
            this.f = false;
        }
    }

    public Task<Void> deleteUnsentReports() {
        return this.g.u();
    }

    public boolean didCrashOnPreviousExecution() {
        return this.f;
    }

    public Task<Void> doBackgroundInitializationAsync(SettingsDataProvider settingsDataProvider) {
        return Utils.callTask(this.j, new a(settingsDataProvider));
    }

    public boolean e() {
        return this.d.c();
    }

    public final Task<Void> f(SettingsDataProvider settingsDataProvider) {
        j();
        try {
            this.breadcrumbSource.registerBreadcrumbHandler(new BreadcrumbHandler() { // from class: com.google.firebase.crashlytics.internal.common.h
                @Override // com.google.firebase.crashlytics.internal.breadcrumbs.BreadcrumbHandler
                public final void handleBreadcrumb(String str) {
                    CrashlyticsCore.this.log(str);
                }
            });
            if (!settingsDataProvider.getSettings().getFeaturesData().collectReports) {
                Logger.getLogger().d("Collection of crash reports disabled in Crashlytics settings.");
                return Tasks.forException(new RuntimeException("Collection of crash reports disabled in Crashlytics settings."));
            }
            if (!this.g.D(settingsDataProvider)) {
                Logger.getLogger().w("Previous sessions could not be finalized.");
            }
            return this.g.a0(settingsDataProvider.getAppSettings());
        } catch (Exception e2) {
            Logger.getLogger().e("Crashlytics encountered a problem during asynchronous initialization.", e2);
            return Tasks.forException(e2);
        } finally {
            i();
        }
    }

    public final void g(SettingsDataProvider settingsDataProvider) {
        Future<?> submit = this.j.submit(new b(settingsDataProvider));
        Logger.getLogger().d("Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            submit.get(4L, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            Logger.getLogger().e("Crashlytics was interrupted during initialization.", e2);
        } catch (ExecutionException e3) {
            Logger.getLogger().e("Crashlytics encountered a problem during initialization.", e3);
        } catch (TimeoutException e4) {
            Logger.getLogger().e("Crashlytics timed out during initialization.", e4);
        }
    }

    public void i() {
        this.k.h(new c());
    }

    public void j() {
        this.k.b();
        this.d.a();
        Logger.getLogger().v("Initialization marker file was created.");
    }

    public void log(String str) {
        this.g.e0(System.currentTimeMillis() - this.c, str);
    }

    public void logException(@NonNull Throwable th) {
        this.g.d0(Thread.currentThread(), th);
    }

    public boolean onPreExecute(AppData appData, SettingsDataProvider settingsDataProvider) {
        if (h(appData.buildId, CommonUtils.getBooleanResourceValue(this.f11133a, "com.crashlytics.RequireBuildId", true))) {
            try {
                FileStoreImpl fileStoreImpl = new FileStoreImpl(this.f11133a);
                this.e = new i("crash_marker", fileStoreImpl);
                this.d = new i("initialization_marker", fileStoreImpl);
                UserMetadata userMetadata = new UserMetadata();
                e eVar = new e(fileStoreImpl);
                LogFileManager logFileManager = new LogFileManager(this.f11133a, eVar);
                this.g = new g(this.f11133a, this.k, this.h, this.b, fileStoreImpl, this.e, appData, userMetadata, logFileManager, eVar, SessionReportingCoordinator.create(this.f11133a, this.h, fileStoreImpl, appData, logFileManager, userMetadata, new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10)), settingsDataProvider), this.l, this.i);
                boolean e2 = e();
                d();
                this.g.A(Thread.getDefaultUncaughtExceptionHandler(), settingsDataProvider);
                if (e2 && CommonUtils.canTryConnection(this.f11133a)) {
                    Logger.getLogger().d("Crashlytics did not finish previous background initialization. Initializing synchronously.");
                    g(settingsDataProvider);
                    return false;
                }
                Logger.getLogger().d("Successfully configured exception handler.");
                return true;
            } catch (Exception e3) {
                Logger.getLogger().e("Crashlytics was not started due to an exception during initialization", e3);
                this.g = null;
                return false;
            }
        }
        throw new IllegalStateException("The Crashlytics build ID is missing. This occurs when Crashlytics tooling is absent from your app's build configuration. Please review Crashlytics onboarding instructions and ensure you have a valid Crashlytics account.");
    }

    public Task<Void> sendUnsentReports() {
        return this.g.V();
    }

    public void setCrashlyticsCollectionEnabled(@Nullable Boolean bool) {
        this.b.setCrashlyticsDataCollectionEnabled(bool);
    }

    public void setCustomKey(String str, String str2) {
        this.g.W(str, str2);
    }

    public void setCustomKeys(Map<String, String> map) {
        this.g.X(map);
    }

    public void setInternalKey(String str, String str2) {
        this.g.Y(str, str2);
    }

    public void setUserId(String str) {
        this.g.Z(str);
    }
}
