package com.google.firebase.crashlytics.internal.common;

import android.app.ApplicationExitInfo;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.send.DataTransportCrashlyticsReportSender;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public class SessionReportingCoordinator {

    /* renamed from: a  reason: collision with root package name */
    public final CrashlyticsReportDataCapture f11139a;
    public final CrashlyticsReportPersistence b;
    public final DataTransportCrashlyticsReportSender c;
    public final LogFileManager d;
    public final UserMetadata e;

    public SessionReportingCoordinator(CrashlyticsReportDataCapture crashlyticsReportDataCapture, CrashlyticsReportPersistence crashlyticsReportPersistence, DataTransportCrashlyticsReportSender dataTransportCrashlyticsReportSender, LogFileManager logFileManager, UserMetadata userMetadata) {
        this.f11139a = crashlyticsReportDataCapture;
        this.b = crashlyticsReportPersistence;
        this.c = dataTransportCrashlyticsReportSender;
        this.d = logFileManager;
        this.e = userMetadata;
    }

    @RequiresApi(api = 19)
    @VisibleForTesting
    public static String convertInputStreamToString(@Nullable InputStream inputStream) throws IOException, NullPointerException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
        while (true) {
            try {
                int read = bufferedReader.read();
                if (read != -1) {
                    sb.append((char) read);
                } else {
                    String sb2 = sb.toString();
                    bufferedReader.close();
                    return sb2;
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static SessionReportingCoordinator create(Context context, IdManager idManager, FileStore fileStore, AppData appData, LogFileManager logFileManager, UserMetadata userMetadata, StackTraceTrimmingStrategy stackTraceTrimmingStrategy, SettingsDataProvider settingsDataProvider) {
        return new SessionReportingCoordinator(new CrashlyticsReportDataCapture(context, idManager, appData, stackTraceTrimmingStrategy), new CrashlyticsReportPersistence(new File(fileStore.getFilesDirPath()), settingsDataProvider), DataTransportCrashlyticsReportSender.create(context), logFileManager, userMetadata);
    }

    @RequiresApi(api = 30)
    public static CrashlyticsReport.ApplicationExitInfo e(ApplicationExitInfo applicationExitInfo) {
        String str;
        try {
            str = convertInputStreamToString(applicationExitInfo.getTraceInputStream());
        } catch (IOException | NullPointerException e) {
            Logger logger = Logger.getLogger();
            logger.w("Could not get input trace in application exit info: " + applicationExitInfo.toString() + " Error: " + e);
            str = null;
        }
        return CrashlyticsReport.ApplicationExitInfo.builder().setImportance(applicationExitInfo.getImportance()).setProcessName(applicationExitInfo.getProcessName()).setReasonCode(applicationExitInfo.getReason()).setTimestamp(applicationExitInfo.getTimestamp()).setPid(applicationExitInfo.getPid()).setPss(applicationExitInfo.getPss()).setRss(applicationExitInfo.getRss()).setTraceFile(str).build();
    }

    @NonNull
    public static List<CrashlyticsReport.CustomAttribute> f(@NonNull Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            arrayList.add(CrashlyticsReport.CustomAttribute.builder().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        Collections.sort(arrayList, new Comparator() { // from class: com.google.firebase.crashlytics.internal.common.q
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int g;
                g = SessionReportingCoordinator.g((CrashlyticsReport.CustomAttribute) obj, (CrashlyticsReport.CustomAttribute) obj2);
                return g;
            }
        });
        return arrayList;
    }

    public static /* synthetic */ int g(CrashlyticsReport.CustomAttribute customAttribute, CrashlyticsReport.CustomAttribute customAttribute2) {
        return customAttribute.getKey().compareTo(customAttribute2.getKey());
    }

    public final CrashlyticsReport.Session.Event c(CrashlyticsReport.Session.Event event) {
        return d(event, this.d, this.e);
    }

    public final CrashlyticsReport.Session.Event d(CrashlyticsReport.Session.Event event, LogFileManager logFileManager, UserMetadata userMetadata) {
        CrashlyticsReport.Session.Event.Builder builder = event.toBuilder();
        String logString = logFileManager.getLogString();
        if (logString != null) {
            builder.setLog(CrashlyticsReport.Session.Event.Log.builder().setContent(logString).build());
        } else {
            Logger.getLogger().v("No log data to include with this event.");
        }
        List<CrashlyticsReport.CustomAttribute> f = f(userMetadata.getCustomKeys());
        List<CrashlyticsReport.CustomAttribute> f2 = f(userMetadata.getInternalKeys());
        if (!f.isEmpty()) {
            builder.setApp(event.getApp().toBuilder().setCustomAttributes(ImmutableList.from(f)).setInternalKeys(ImmutableList.from(f2)).build());
        }
        return builder.build();
    }

    public void finalizeSessionWithNativeEvent(@NonNull String str, @NonNull List<n> list) {
        ArrayList arrayList = new ArrayList();
        for (n nVar : list) {
            CrashlyticsReport.FilesPayload.File a2 = nVar.a();
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        this.b.finalizeSessionWithNativeEvent(str, CrashlyticsReport.FilesPayload.builder().setFiles(ImmutableList.from(arrayList)).build());
    }

    public void finalizeSessions(long j, @Nullable String str) {
        this.b.finalizeReports(str, j);
    }

    public final boolean h(@NonNull Task<CrashlyticsReportWithSessionId> task) {
        if (task.isSuccessful()) {
            CrashlyticsReportWithSessionId result = task.getResult();
            Logger logger = Logger.getLogger();
            logger.d("Crashlytics report successfully enqueued to DataTransport: " + result.getSessionId());
            this.b.deleteFinalizedReport(result.getSessionId());
            return true;
        }
        Logger.getLogger().w("Crashlytics report could not be enqueued to DataTransport", task.getException());
        return false;
    }

    public boolean hasReportsToSend() {
        return this.b.hasFinalizedReports();
    }

    public final void i(@NonNull Throwable th, @NonNull Thread thread, @NonNull String str, @NonNull String str2, long j, boolean z) {
        this.b.persistEvent(c(this.f11139a.captureEventData(th, thread, str2, j, 4, 8, z)), str, str2.equals(AppMeasurement.CRASH_ORIGIN));
    }

    @NonNull
    public List<String> listSortedOpenSessionIds() {
        return this.b.listSortedOpenSessionIds();
    }

    public void onBeginSession(@NonNull String str, long j) {
        this.b.persistReport(this.f11139a.captureReportData(str, j));
    }

    public void onCustomKey(String str, String str2) {
        this.e.setCustomKey(str, str2);
    }

    public void onLog(long j, String str) {
        this.d.writeToLog(j, str);
    }

    public void onUserId(String str) {
        this.e.setUserId(str);
    }

    @RequiresApi(api = 30)
    public void persistAppExitInfoEvent(String str, ApplicationExitInfo applicationExitInfo, LogFileManager logFileManager, UserMetadata userMetadata) {
        if (applicationExitInfo.getTimestamp() >= this.b.getStartTimestampMillis(str) && applicationExitInfo.getReason() == 6) {
            CrashlyticsReport.Session.Event captureAnrEventData = this.f11139a.captureAnrEventData(e(applicationExitInfo));
            Logger logger = Logger.getLogger();
            logger.d("Persisting anr for session " + str);
            this.b.persistEvent(d(captureAnrEventData, logFileManager, userMetadata), str, true);
        }
    }

    public void persistFatalEvent(@NonNull Throwable th, @NonNull Thread thread, @NonNull String str, long j) {
        Logger logger = Logger.getLogger();
        logger.v("Persisting fatal event for session " + str);
        i(th, thread, str, AppMeasurement.CRASH_ORIGIN, j, true);
    }

    public void persistNonFatalEvent(@NonNull Throwable th, @NonNull Thread thread, @NonNull String str, long j) {
        Logger logger = Logger.getLogger();
        logger.v("Persisting non-fatal event for session " + str);
        i(th, thread, str, "error", j, false);
    }

    public void persistUserId(@NonNull String str) {
        String userId = this.e.getUserId();
        if (userId == null) {
            Logger.getLogger().v("Could not persist user ID; no user ID available");
        } else {
            this.b.persistUserIdForSession(userId, str);
        }
    }

    public void removeAllReports() {
        this.b.deleteAllReports();
    }

    public Task<Void> sendReports(@NonNull Executor executor) {
        List<CrashlyticsReportWithSessionId> loadFinalizedReports = this.b.loadFinalizedReports();
        ArrayList arrayList = new ArrayList();
        for (CrashlyticsReportWithSessionId crashlyticsReportWithSessionId : loadFinalizedReports) {
            arrayList.add(this.c.sendReport(crashlyticsReportWithSessionId).continueWith(executor, new Continuation() { // from class: com.google.firebase.crashlytics.internal.common.p
                @Override // com.google.android.gms.tasks.Continuation
                public final Object then(Task task) {
                    boolean h;
                    h = SessionReportingCoordinator.this.h(task);
                    return Boolean.valueOf(h);
                }
            }));
        }
        return Tasks.whenAll(arrayList);
    }
}
