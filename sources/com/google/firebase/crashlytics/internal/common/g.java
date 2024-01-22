package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.NativeSessionFileProvider;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger;
import com.google.firebase.crashlytics.internal.common.j;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.model.StaticSessionData;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.jose4j.jwk.JsonWebKeySet;
/* loaded from: classes10.dex */
public class g {
    public static final FilenameFilter t = new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.common.f
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            boolean O;
            O = g.O(file, str);
            return O;
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public final Context f11151a;
    public final DataCollectionArbiter b;
    public final com.google.firebase.crashlytics.internal.common.i c;
    public final UserMetadata d;
    public final com.google.firebase.crashlytics.internal.common.e e;
    public final IdManager f;
    public final FileStore g;
    public final AppData h;
    public final LogFileManager.DirectoryProvider i;
    public final LogFileManager j;
    public final CrashlyticsNativeComponent k;
    public final String l;
    public final AnalyticsEventLogger m;
    public final SessionReportingCoordinator n;
    public com.google.firebase.crashlytics.internal.common.j o;
    public final TaskCompletionSource<Boolean> p = new TaskCompletionSource<>();
    public final TaskCompletionSource<Boolean> q = new TaskCompletionSource<>();
    public final TaskCompletionSource<Void> r = new TaskCompletionSource<>();
    public final AtomicBoolean s = new AtomicBoolean(false);

    /* loaded from: classes10.dex */
    public class a implements Callable<Void> {
        public final /* synthetic */ long h;

        public a(long j) {
            this.h = j;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            Bundle bundle = new Bundle();
            bundle.putInt("fatal", 1);
            bundle.putLong("timestamp", this.h);
            g.this.m.logEvent("_ae", bundle);
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public class b implements j.a {
        public b() {
        }

        @Override // com.google.firebase.crashlytics.internal.common.j.a
        public void a(@NonNull SettingsDataProvider settingsDataProvider, @NonNull Thread thread, @NonNull Throwable th) {
            g.this.M(settingsDataProvider, thread, th);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Callable<Task<Void>> {
        public final /* synthetic */ long h;
        public final /* synthetic */ Throwable i;
        public final /* synthetic */ Thread j;
        public final /* synthetic */ SettingsDataProvider k;

        /* loaded from: classes10.dex */
        public class a implements SuccessContinuation<AppSettingsData, Void> {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ Executor f11153a;

            public a(Executor executor) {
                this.f11153a = executor;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            @NonNull
            /* renamed from: a */
            public Task<Void> then(@Nullable AppSettingsData appSettingsData) throws Exception {
                if (appSettingsData == null) {
                    Logger.getLogger().w("Received null app settings, cannot send reports at crash time.");
                    return Tasks.forResult(null);
                }
                return Tasks.whenAll(g.this.T(), g.this.n.sendReports(this.f11153a));
            }
        }

        public c(long j, Throwable th, Thread thread, SettingsDataProvider settingsDataProvider) {
            this.h = j;
            this.i = th;
            this.j = thread;
            this.k = settingsDataProvider;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Task<Void> call() throws Exception {
            long L = g.L(this.h);
            String G = g.this.G();
            if (G != null) {
                g.this.c.a();
                g.this.n.persistFatalEvent(this.i, this.j, G, L);
                g.this.z(this.h);
                g.this.w(this.k);
                g.this.y();
                if (g.this.b.isAutomaticDataCollectionEnabled()) {
                    Executor c = g.this.e.c();
                    return this.k.getAppSettings().onSuccessTask(c, new a(c));
                }
                return Tasks.forResult(null);
            }
            Logger.getLogger().e("Tried to write a fatal exception while no session was open.");
            return Tasks.forResult(null);
        }
    }

    /* loaded from: classes10.dex */
    public class d implements SuccessContinuation<Void, Boolean> {
        public d(g gVar) {
        }

        @Override // com.google.android.gms.tasks.SuccessContinuation
        @NonNull
        /* renamed from: a */
        public Task<Boolean> then(@Nullable Void r1) throws Exception {
            return Tasks.forResult(Boolean.TRUE);
        }
    }

    /* loaded from: classes10.dex */
    public class e implements SuccessContinuation<Boolean, Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Task f11154a;

        /* loaded from: classes10.dex */
        public class a implements Callable<Task<Void>> {
            public final /* synthetic */ Boolean h;

            /* renamed from: com.google.firebase.crashlytics.internal.common.g$e$a$a  reason: collision with other inner class name */
            /* loaded from: classes10.dex */
            public class C0536a implements SuccessContinuation<AppSettingsData, Void> {

                /* renamed from: a  reason: collision with root package name */
                public final /* synthetic */ Executor f11155a;

                public C0536a(Executor executor) {
                    this.f11155a = executor;
                }

                @Override // com.google.android.gms.tasks.SuccessContinuation
                @NonNull
                /* renamed from: a */
                public Task<Void> then(@Nullable AppSettingsData appSettingsData) throws Exception {
                    if (appSettingsData != null) {
                        g.this.T();
                        g.this.n.sendReports(this.f11155a);
                        g.this.r.trySetResult(null);
                        return Tasks.forResult(null);
                    }
                    Logger.getLogger().w("Received null app settings at app startup. Cannot send cached reports");
                    return Tasks.forResult(null);
                }
            }

            public a(Boolean bool) {
                this.h = bool;
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Task<Void> call() throws Exception {
                if (!this.h.booleanValue()) {
                    Logger.getLogger().v("Deleting cached crash reports...");
                    g.t(g.this.P());
                    g.this.n.removeAllReports();
                    g.this.r.trySetResult(null);
                    return Tasks.forResult(null);
                }
                Logger.getLogger().d("Sending cached crash reports...");
                g.this.b.grantDataCollectionPermission(this.h.booleanValue());
                Executor c = g.this.e.c();
                return e.this.f11154a.onSuccessTask(c, new C0536a(c));
            }
        }

        public e(Task task) {
            this.f11154a = task;
        }

        @Override // com.google.android.gms.tasks.SuccessContinuation
        @NonNull
        /* renamed from: a */
        public Task<Void> then(@Nullable Boolean bool) throws Exception {
            return g.this.e.i(new a(bool));
        }
    }

    /* loaded from: classes10.dex */
    public class f implements Callable<Void> {
        public final /* synthetic */ long h;
        public final /* synthetic */ String i;

        public f(long j, String str) {
            this.h = j;
            this.i = str;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            if (g.this.N()) {
                return null;
            }
            g.this.j.writeToLog(this.h, this.i);
            return null;
        }
    }

    /* renamed from: com.google.firebase.crashlytics.internal.common.g$g  reason: collision with other inner class name */
    /* loaded from: classes10.dex */
    public class RunnableC0537g implements Runnable {
        public final /* synthetic */ long h;
        public final /* synthetic */ Throwable i;
        public final /* synthetic */ Thread j;

        public RunnableC0537g(long j, Throwable th, Thread thread) {
            this.h = j;
            this.i = th;
            this.j = thread;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (g.this.N()) {
                return;
            }
            long L = g.L(this.h);
            String G = g.this.G();
            if (G != null) {
                g.this.n.persistNonFatalEvent(this.i, this.j, G, L);
            } else {
                Logger.getLogger().w("Tried to write a non-fatal exception while no session was open.");
            }
        }
    }

    /* loaded from: classes10.dex */
    public class h implements Callable<Void> {
        public final /* synthetic */ UserMetadata h;

        public h(UserMetadata userMetadata) {
            this.h = userMetadata;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            String G = g.this.G();
            if (G != null) {
                g.this.n.persistUserId(G);
                new m(g.this.I()).k(G, this.h);
                return null;
            }
            Logger.getLogger().d("Tried to cache user data while no session was open.");
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public class i implements Callable<Void> {
        public final /* synthetic */ Map h;
        public final /* synthetic */ boolean i;

        public i(Map map, boolean z) {
            this.h = map;
            this.i = z;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            new m(g.this.I()).j(g.this.G(), this.h, this.i);
            return null;
        }
    }

    /* loaded from: classes10.dex */
    public class j implements Callable<Void> {
        public j() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Void call() throws Exception {
            g.this.y();
            return null;
        }
    }

    public g(Context context, com.google.firebase.crashlytics.internal.common.e eVar, IdManager idManager, DataCollectionArbiter dataCollectionArbiter, FileStore fileStore, com.google.firebase.crashlytics.internal.common.i iVar, AppData appData, UserMetadata userMetadata, LogFileManager logFileManager, LogFileManager.DirectoryProvider directoryProvider, SessionReportingCoordinator sessionReportingCoordinator, CrashlyticsNativeComponent crashlyticsNativeComponent, AnalyticsEventLogger analyticsEventLogger) {
        this.f11151a = context;
        this.e = eVar;
        this.f = idManager;
        this.b = dataCollectionArbiter;
        this.g = fileStore;
        this.c = iVar;
        this.h = appData;
        this.d = userMetadata;
        this.j = logFileManager;
        this.i = directoryProvider;
        this.k = crashlyticsNativeComponent;
        this.l = appData.unityVersionProvider.getUnityVersion();
        this.m = analyticsEventLogger;
        this.n = sessionReportingCoordinator;
    }

    public static File[] B(File[] fileArr) {
        return fileArr == null ? new File[0] : fileArr;
    }

    public static boolean E() {
        try {
            Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static long H() {
        return L(System.currentTimeMillis());
    }

    @NonNull
    public static List<n> J(NativeSessionFileProvider nativeSessionFileProvider, String str, File file, byte[] bArr) {
        m mVar = new m(file);
        File c2 = mVar.c(str);
        File b2 = mVar.b(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.google.firebase.crashlytics.internal.common.c("logs_file", "logs", bArr));
        arrayList.add(new k("crash_meta_file", "metadata", nativeSessionFileProvider.getMetadataFile()));
        arrayList.add(new k("session_meta_file", "session", nativeSessionFileProvider.getSessionFile()));
        arrayList.add(new k("app_meta_file", "app", nativeSessionFileProvider.getAppFile()));
        arrayList.add(new k("device_meta_file", "device", nativeSessionFileProvider.getDeviceFile()));
        arrayList.add(new k("os_meta_file", "os", nativeSessionFileProvider.getOsFile()));
        arrayList.add(new k("minidump_file", "minidump", nativeSessionFileProvider.getMinidumpFile()));
        arrayList.add(new k("user_meta_file", "user", c2));
        arrayList.add(new k("keys_file", JsonWebKeySet.JWK_SET_MEMBER_NAME, b2));
        return arrayList;
    }

    public static long L(long j2) {
        return j2 / 1000;
    }

    public static /* synthetic */ boolean O(File file, String str) {
        return str.startsWith(".ae");
    }

    public static File[] Q(File file, FilenameFilter filenameFilter) {
        return B(file.listFiles(filenameFilter));
    }

    public static StaticSessionData.AppData q(IdManager idManager, AppData appData, String str) {
        return StaticSessionData.AppData.create(idManager.getAppIdentifier(), appData.versionCode, appData.versionName, idManager.getCrashlyticsInstallId(), DeliveryMechanism.determineFrom(appData.installerPackageName).getId(), str);
    }

    public static StaticSessionData.DeviceData r(Context context) {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return StaticSessionData.DeviceData.create(CommonUtils.getCpuArchitectureInt(), Build.MODEL, Runtime.getRuntime().availableProcessors(), CommonUtils.getTotalRamInBytes(), statFs.getBlockCount() * statFs.getBlockSize(), CommonUtils.isEmulator(context), CommonUtils.getDeviceState(context), Build.MANUFACTURER, Build.PRODUCT);
    }

    public static StaticSessionData.OsData s(Context context) {
        return StaticSessionData.OsData.create(Build.VERSION.RELEASE, Build.VERSION.CODENAME, CommonUtils.isRooted(context));
    }

    public static void t(File[] fileArr) {
        if (fileArr == null) {
            return;
        }
        for (File file : fileArr) {
            file.delete();
        }
    }

    public void A(Thread.UncaughtExceptionHandler uncaughtExceptionHandler, SettingsDataProvider settingsDataProvider) {
        U();
        com.google.firebase.crashlytics.internal.common.j jVar = new com.google.firebase.crashlytics.internal.common.j(new b(), settingsDataProvider, uncaughtExceptionHandler);
        this.o = jVar;
        Thread.setDefaultUncaughtExceptionHandler(jVar);
    }

    public final void C(String str) {
        Logger logger = Logger.getLogger();
        logger.v("Finalizing native report for session " + str);
        NativeSessionFileProvider sessionFileProvider = this.k.getSessionFileProvider(str);
        File minidumpFile = sessionFileProvider.getMinidumpFile();
        if (minidumpFile != null && minidumpFile.exists()) {
            long lastModified = minidumpFile.lastModified();
            LogFileManager logFileManager = new LogFileManager(this.f11151a, this.i, str);
            File file = new File(K(), str);
            if (!file.mkdirs()) {
                Logger.getLogger().w("Couldn't create directory to store native session files, aborting.");
                return;
            }
            z(lastModified);
            List<n> J = J(sessionFileProvider, str, I(), logFileManager.getBytesForLog());
            o.b(file, J);
            this.n.finalizeSessionWithNativeEvent(str, J);
            logFileManager.clearLog();
            return;
        }
        Logger logger2 = Logger.getLogger();
        logger2.w("No minidump data found for session " + str);
    }

    public boolean D(SettingsDataProvider settingsDataProvider) {
        this.e.b();
        if (N()) {
            Logger.getLogger().w("Skipping session finalization because a crash has already occurred.");
            return false;
        }
        Logger.getLogger().v("Finalizing previously open sessions.");
        try {
            x(true, settingsDataProvider);
            Logger.getLogger().v("Closed all previously open sessions.");
            return true;
        } catch (Exception e2) {
            Logger.getLogger().e("Unable to finalize previously open sessions.", e2);
            return false;
        }
    }

    public final Context F() {
        return this.f11151a;
    }

    @Nullable
    public final String G() {
        List<String> listSortedOpenSessionIds = this.n.listSortedOpenSessionIds();
        if (listSortedOpenSessionIds.isEmpty()) {
            return null;
        }
        return listSortedOpenSessionIds.get(0);
    }

    public File I() {
        return this.g.getFilesDir();
    }

    public File K() {
        return new File(I(), "native-sessions");
    }

    public synchronized void M(@NonNull SettingsDataProvider settingsDataProvider, @NonNull Thread thread, @NonNull Throwable th) {
        Logger logger = Logger.getLogger();
        logger.d("Handling uncaught exception \"" + th + "\" from thread " + thread.getName());
        try {
            Utils.awaitEvenIfOnMainThread(this.e.i(new c(System.currentTimeMillis(), th, thread, settingsDataProvider)));
        } catch (Exception e2) {
            Logger.getLogger().e("Error handling uncaught exception", e2);
        }
    }

    public boolean N() {
        com.google.firebase.crashlytics.internal.common.j jVar = this.o;
        return jVar != null && jVar.a();
    }

    public File[] P() {
        return R(t);
    }

    public final File[] R(FilenameFilter filenameFilter) {
        return Q(I(), filenameFilter);
    }

    public final Task<Void> S(long j2) {
        if (E()) {
            Logger.getLogger().w("Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
            return Tasks.forResult(null);
        }
        Logger.getLogger().d("Logging app exception event to Firebase Analytics");
        return Tasks.call(new ScheduledThreadPoolExecutor(1), new a(j2));
    }

    public final Task<Void> T() {
        File[] P;
        ArrayList arrayList = new ArrayList();
        for (File file : P()) {
            try {
                arrayList.add(S(Long.parseLong(file.getName().substring(3))));
            } catch (NumberFormatException unused) {
                Logger.getLogger().w("Could not parse app exception timestamp from file " + file.getName());
            }
            file.delete();
        }
        return Tasks.whenAll(arrayList);
    }

    public void U() {
        this.e.h(new j());
    }

    public Task<Void> V() {
        this.q.trySetResult(Boolean.TRUE);
        return this.r.getTask();
    }

    public void W(String str, String str2) {
        try {
            this.d.setCustomKey(str, str2);
            n(this.d.getCustomKeys(), false);
        } catch (IllegalArgumentException e2) {
            Context context = this.f11151a;
            if (context != null && CommonUtils.isAppDebuggable(context)) {
                throw e2;
            }
            Logger.getLogger().e("Attempting to set custom attribute with null key, ignoring.");
        }
    }

    public void X(Map<String, String> map) {
        this.d.setCustomKeys(map);
        n(this.d.getCustomKeys(), false);
    }

    public void Y(String str, String str2) {
        try {
            this.d.setInternalKey(str, str2);
            n(this.d.getInternalKeys(), true);
        } catch (IllegalArgumentException e2) {
            Context context = this.f11151a;
            if (context != null && CommonUtils.isAppDebuggable(context)) {
                throw e2;
            }
            Logger.getLogger().e("Attempting to set custom attribute with null key, ignoring.");
        }
    }

    public void Z(String str) {
        this.d.setUserId(str);
        o(this.d);
    }

    public Task<Void> a0(Task<AppSettingsData> task) {
        if (!this.n.hasReportsToSend()) {
            Logger.getLogger().v("No crash reports are available to be sent.");
            this.p.trySetResult(Boolean.FALSE);
            return Tasks.forResult(null);
        }
        Logger.getLogger().v("Crash reports are available to be sent.");
        return b0().onSuccessTask(new e(task));
    }

    public final Task<Boolean> b0() {
        if (this.b.isAutomaticDataCollectionEnabled()) {
            Logger.getLogger().d("Automatic data collection is enabled. Allowing upload.");
            this.p.trySetResult(Boolean.FALSE);
            return Tasks.forResult(Boolean.TRUE);
        }
        Logger.getLogger().d("Automatic data collection is disabled.");
        Logger.getLogger().v("Notifying that unsent reports are available.");
        this.p.trySetResult(Boolean.TRUE);
        Task<TContinuationResult> onSuccessTask = this.b.waitForAutomaticDataCollectionEnabled().onSuccessTask(new d(this));
        Logger.getLogger().d("Waiting for send/deleteUnsentReports to be called.");
        return Utils.race(onSuccessTask, this.q.getTask());
    }

    public final void c0(String str) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 30) {
            List<ApplicationExitInfo> historicalProcessExitReasons = ((ActivityManager) this.f11151a.getSystemService("activity")).getHistoricalProcessExitReasons(null, 0, 1);
            if (historicalProcessExitReasons.size() != 0) {
                LogFileManager logFileManager = new LogFileManager(this.f11151a, this.i, str);
                UserMetadata userMetadata = new UserMetadata();
                userMetadata.setCustomKeys(new m(I()).f(str));
                this.n.persistAppExitInfoEvent(str, historicalProcessExitReasons.get(0), logFileManager, userMetadata);
                return;
            }
            return;
        }
        Logger logger = Logger.getLogger();
        logger.v("ANR feature enabled, but device is API " + i2);
    }

    public void d0(@NonNull Thread thread, @NonNull Throwable th) {
        this.e.g(new RunnableC0537g(System.currentTimeMillis(), th, thread));
    }

    public void e0(long j2, String str) {
        this.e.h(new f(j2, str));
    }

    public final void n(Map<String, String> map, boolean z) {
        this.e.h(new i(map, z));
    }

    public final void o(UserMetadata userMetadata) {
        this.e.h(new h(userMetadata));
    }

    @NonNull
    public Task<Boolean> p() {
        if (!this.s.compareAndSet(false, true)) {
            Logger.getLogger().w("checkForUnsentReports should only be called once per execution.");
            return Tasks.forResult(Boolean.FALSE);
        }
        return this.p.getTask();
    }

    public Task<Void> u() {
        this.q.trySetResult(Boolean.FALSE);
        return this.r.getTask();
    }

    public boolean v() {
        if (!this.c.c()) {
            String G = G();
            return G != null && this.k.hasCrashDataForSession(G);
        }
        Logger.getLogger().v("Found previous crash marker.");
        this.c.d();
        return true;
    }

    public void w(SettingsDataProvider settingsDataProvider) {
        x(false, settingsDataProvider);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void x(boolean z, SettingsDataProvider settingsDataProvider) {
        List<String> listSortedOpenSessionIds = this.n.listSortedOpenSessionIds();
        if (listSortedOpenSessionIds.size() <= z) {
            Logger.getLogger().v("No open sessions to be closed.");
            return;
        }
        String str = listSortedOpenSessionIds.get(z ? 1 : 0);
        if (settingsDataProvider.getSettings().getFeaturesData().collectAnrs) {
            c0(str);
        }
        if (this.k.hasCrashDataForSession(str)) {
            C(str);
            this.k.finalizeSession(str);
        }
        this.n.finalizeSessions(H(), z != 0 ? listSortedOpenSessionIds.get(0) : null);
    }

    public final void y() {
        long H = H();
        String dVar = new com.google.firebase.crashlytics.internal.common.d(this.f).toString();
        Logger logger = Logger.getLogger();
        logger.d("Opening a new session with ID " + dVar);
        this.k.openSession(dVar, String.format(Locale.US, "Crashlytics Android SDK/%s", CrashlyticsCore.getVersion()), H, StaticSessionData.create(q(this.f, this.h, this.l), s(F()), r(F())));
        this.j.setCurrentSession(dVar);
        this.n.onBeginSession(dVar, H);
    }

    public final void z(long j2) {
        try {
            File I = I();
            new File(I, ".ae" + j2).createNewFile();
        } catch (IOException e2) {
            Logger.getLogger().w("Could not create app exception marker file.", e2);
        }
    }
}
