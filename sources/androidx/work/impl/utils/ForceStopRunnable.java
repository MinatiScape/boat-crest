package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.ApplicationExitInfo;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteAccessPermException;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteTableLockedException;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import androidx.core.os.BuildCompat;
import androidx.work.Configuration;
import androidx.work.InitializationExceptionHandler;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkDatabasePathHelper;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.model.WorkProgressDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ForceStopRunnable implements Runnable {
    public static final String k = Logger.tagWithPrefix("ForceStopRunnable");
    public static final long l = TimeUnit.DAYS.toMillis(3650);
    public final Context h;
    public final WorkManagerImpl i;
    public int j = 0;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static class BroadcastReceiver extends android.content.BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        public static final String f1830a = Logger.tagWithPrefix("ForceStopRunnable$Rcvr");

        @Override // android.content.BroadcastReceiver
        public void onReceive(@NonNull Context context, @Nullable Intent intent) {
            if (intent == null || !"ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                return;
            }
            Logger.get().verbose(f1830a, "Rescheduling alarm that keeps track of force-stops.", new Throwable[0]);
            ForceStopRunnable.c(context);
        }
    }

    public ForceStopRunnable(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl) {
        this.h = context.getApplicationContext();
        this.i = workManagerImpl;
    }

    @VisibleForTesting
    public static Intent a(Context context) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return intent;
    }

    public static PendingIntent b(Context context, int i) {
        return PendingIntent.getBroadcast(context, -1, a(context), i);
    }

    @SuppressLint({"ClassVerificationFailure"})
    public static void c(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        PendingIntent b = b(context, BuildCompat.isAtLeastS() ? 167772160 : 134217728);
        long currentTimeMillis = System.currentTimeMillis() + l;
        if (alarmManager != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(0, currentTimeMillis, b);
            } else {
                alarmManager.set(0, currentTimeMillis, b);
            }
        }
    }

    @VisibleForTesting
    public boolean cleanUp() {
        boolean reconcileJobs = Build.VERSION.SDK_INT >= 23 ? SystemJobScheduler.reconcileJobs(this.h, this.i) : false;
        WorkDatabase workDatabase = this.i.getWorkDatabase();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        WorkProgressDao workProgressDao = workDatabase.workProgressDao();
        workDatabase.beginTransaction();
        try {
            List<WorkSpec> runningWork = workSpecDao.getRunningWork();
            boolean z = (runningWork == null || runningWork.isEmpty()) ? false : true;
            if (z) {
                for (WorkSpec workSpec : runningWork) {
                    workSpecDao.setState(WorkInfo.State.ENQUEUED, workSpec.id);
                    workSpecDao.markWorkSpecScheduled(workSpec.id, -1L);
                }
            }
            workProgressDao.deleteAll();
            workDatabase.setTransactionSuccessful();
            return z || reconcileJobs;
        } finally {
            workDatabase.endTransaction();
        }
    }

    @VisibleForTesting
    public boolean d() {
        return this.i.getPreferenceUtils().getNeedsReschedule();
    }

    @VisibleForTesting
    public void forceStopRunnable() {
        boolean cleanUp = cleanUp();
        if (d()) {
            Logger.get().debug(k, "Rescheduling Workers.", new Throwable[0]);
            this.i.rescheduleEligibleWork();
            this.i.getPreferenceUtils().setNeedsReschedule(false);
        } else if (isForceStopped()) {
            Logger.get().debug(k, "Application was force-stopped, rescheduling.", new Throwable[0]);
            this.i.rescheduleEligibleWork();
        } else if (cleanUp) {
            Logger.get().debug(k, "Found unfinished work, scheduling it.", new Throwable[0]);
            Schedulers.schedule(this.i.getConfiguration(), this.i.getWorkDatabase(), this.i.getSchedulers());
        }
    }

    @SuppressLint({"ClassVerificationFailure"})
    @VisibleForTesting
    public boolean isForceStopped() {
        int i = PKIFailureInfo.duplicateCertReq;
        try {
            if (BuildCompat.isAtLeastS()) {
                i = 570425344;
            }
            PendingIntent b = b(this.h, i);
            if (Build.VERSION.SDK_INT >= 30) {
                if (b != null) {
                    b.cancel();
                }
                List<ApplicationExitInfo> historicalProcessExitReasons = ((ActivityManager) this.h.getSystemService("activity")).getHistoricalProcessExitReasons(null, 0, 0);
                if (historicalProcessExitReasons != null && !historicalProcessExitReasons.isEmpty()) {
                    for (int i2 = 0; i2 < historicalProcessExitReasons.size(); i2++) {
                        if (historicalProcessExitReasons.get(i2).getReason() == 10) {
                            return true;
                        }
                    }
                }
            } else if (b == null) {
                c(this.h);
                return true;
            }
            return false;
        } catch (IllegalArgumentException | SecurityException e) {
            Logger.get().warning(k, "Ignoring exception", e);
            return true;
        }
    }

    @VisibleForTesting
    public boolean multiProcessChecks() {
        Configuration configuration = this.i.getConfiguration();
        if (TextUtils.isEmpty(configuration.getDefaultProcessName())) {
            Logger.get().debug(k, "The default process name was not specified.", new Throwable[0]);
            return true;
        }
        boolean isDefaultProcess = ProcessUtils.isDefaultProcess(this.h, configuration);
        Logger.get().debug(k, String.format("Is default app process = %s", Boolean.valueOf(isDefaultProcess)), new Throwable[0]);
        return isDefaultProcess;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        try {
            if (multiProcessChecks()) {
                while (true) {
                    WorkDatabasePathHelper.migrateDatabase(this.h);
                    Logger.get().debug(k, "Performing cleanup operations.", new Throwable[0]);
                    try {
                        forceStopRunnable();
                        break;
                    } catch (SQLiteAccessPermException | SQLiteCantOpenDatabaseException | SQLiteConstraintException | SQLiteDatabaseCorruptException | SQLiteDatabaseLockedException | SQLiteTableLockedException e) {
                        i = this.j + 1;
                        this.j = i;
                        if (i >= 3) {
                            Logger logger = Logger.get();
                            String str = k;
                            logger.error(str, "The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e);
                            IllegalStateException illegalStateException = new IllegalStateException("The file system on the device is in a bad state. WorkManager cannot access the app's internal data store.", e);
                            InitializationExceptionHandler exceptionHandler = this.i.getConfiguration().getExceptionHandler();
                            if (exceptionHandler != null) {
                                Logger.get().debug(str, "Routing exception to the specified exception handler", illegalStateException);
                                exceptionHandler.handleException(illegalStateException);
                            } else {
                                throw illegalStateException;
                            }
                        } else {
                            Logger.get().debug(k, String.format("Retrying after %s", Long.valueOf(i * 300)), e);
                            sleep(this.j * 300);
                        }
                    }
                    Logger.get().debug(k, String.format("Retrying after %s", Long.valueOf(i * 300)), e);
                    sleep(this.j * 300);
                }
            }
        } finally {
            this.i.onForceStopRunnableCompleted();
        }
    }

    @VisibleForTesting
    public void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
        }
    }
}
