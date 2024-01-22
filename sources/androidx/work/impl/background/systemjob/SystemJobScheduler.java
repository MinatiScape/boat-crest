package androidx.work.impl.background.systemjob;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.OutOfQuotaPolicy;
import androidx.work.WorkInfo;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.IdGenerator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
@RequiresApi(23)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SystemJobScheduler implements Scheduler {
    public static final String l = Logger.tagWithPrefix("SystemJobScheduler");
    public final Context h;
    public final JobScheduler i;
    public final WorkManagerImpl j;
    public final a k;

    public SystemJobScheduler(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl) {
        this(context, workManagerImpl, (JobScheduler) context.getSystemService("jobscheduler"), new a(context));
    }

    public static void a(@NonNull JobScheduler jobScheduler, int i) {
        try {
            jobScheduler.cancel(i);
        } catch (Throwable th) {
            Logger.get().error(l, String.format(Locale.getDefault(), "Exception while trying to cancel job (%d)", Integer.valueOf(i)), th);
        }
    }

    @Nullable
    public static List<Integer> b(@NonNull Context context, @NonNull JobScheduler jobScheduler, @NonNull String str) {
        List<JobInfo> c = c(context, jobScheduler);
        if (c == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(2);
        for (JobInfo jobInfo : c) {
            if (str.equals(d(jobInfo))) {
                arrayList.add(Integer.valueOf(jobInfo.getId()));
            }
        }
        return arrayList;
    }

    @Nullable
    public static List<JobInfo> c(@NonNull Context context, @NonNull JobScheduler jobScheduler) {
        List<JobInfo> list;
        try {
            list = jobScheduler.getAllPendingJobs();
        } catch (Throwable th) {
            Logger.get().error(l, "getAllPendingJobs() is not reliable on this device.", th);
            list = null;
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        ComponentName componentName = new ComponentName(context, SystemJobService.class);
        for (JobInfo jobInfo : list) {
            if (componentName.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    public static void cancelAll(@NonNull Context context) {
        List<JobInfo> c;
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null || (c = c(context, jobScheduler)) == null || c.isEmpty()) {
            return;
        }
        for (JobInfo jobInfo : c) {
            a(jobScheduler, jobInfo.getId());
        }
    }

    @Nullable
    public static String d(@NonNull JobInfo jobInfo) {
        PersistableBundle extras = jobInfo.getExtras();
        if (extras != null) {
            try {
                if (extras.containsKey("EXTRA_WORK_SPEC_ID")) {
                    return extras.getString("EXTRA_WORK_SPEC_ID");
                }
                return null;
            } catch (NullPointerException unused) {
                return null;
            }
        }
        return null;
    }

    public static boolean reconcileJobs(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl) {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        List<JobInfo> c = c(context, jobScheduler);
        List<String> workSpecIds = workManagerImpl.getWorkDatabase().systemIdInfoDao().getWorkSpecIds();
        boolean z = false;
        HashSet hashSet = new HashSet(c != null ? c.size() : 0);
        if (c != null && !c.isEmpty()) {
            for (JobInfo jobInfo : c) {
                String d = d(jobInfo);
                if (!TextUtils.isEmpty(d)) {
                    hashSet.add(d);
                } else {
                    a(jobScheduler, jobInfo.getId());
                }
            }
        }
        Iterator<String> it = workSpecIds.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!hashSet.contains(it.next())) {
                    Logger.get().debug(l, "Reconciling jobs", new Throwable[0]);
                    z = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (z) {
            WorkDatabase workDatabase = workManagerImpl.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                WorkSpecDao workSpecDao = workDatabase.workSpecDao();
                for (String str : workSpecIds) {
                    workSpecDao.markWorkSpecScheduled(str, -1L);
                }
                workDatabase.setTransactionSuccessful();
            } finally {
                workDatabase.endTransaction();
            }
        }
        return z;
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String str) {
        List<Integer> b = b(this.h, this.i, str);
        if (b == null || b.isEmpty()) {
            return;
        }
        for (Integer num : b) {
            a(this.i, num.intValue());
        }
        this.j.getWorkDatabase().systemIdInfoDao().removeSystemIdInfo(str);
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return true;
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecArr) {
        int nextJobSchedulerIdWithRange;
        List<Integer> b;
        int nextJobSchedulerIdWithRange2;
        WorkDatabase workDatabase = this.j.getWorkDatabase();
        IdGenerator idGenerator = new IdGenerator(workDatabase);
        for (WorkSpec workSpec : workSpecArr) {
            workDatabase.beginTransaction();
            try {
                WorkSpec workSpec2 = workDatabase.workSpecDao().getWorkSpec(workSpec.id);
                if (workSpec2 == null) {
                    Logger.get().warning(l, "Skipping scheduling " + workSpec.id + " because it's no longer in the DB", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else if (workSpec2.state != WorkInfo.State.ENQUEUED) {
                    Logger.get().warning(l, "Skipping scheduling " + workSpec.id + " because it is no longer enqueued", new Throwable[0]);
                    workDatabase.setTransactionSuccessful();
                } else {
                    SystemIdInfo systemIdInfo = workDatabase.systemIdInfoDao().getSystemIdInfo(workSpec.id);
                    if (systemIdInfo != null) {
                        nextJobSchedulerIdWithRange = systemIdInfo.systemId;
                    } else {
                        nextJobSchedulerIdWithRange = idGenerator.nextJobSchedulerIdWithRange(this.j.getConfiguration().getMinJobSchedulerId(), this.j.getConfiguration().getMaxJobSchedulerId());
                    }
                    if (systemIdInfo == null) {
                        this.j.getWorkDatabase().systemIdInfoDao().insertSystemIdInfo(new SystemIdInfo(workSpec.id, nextJobSchedulerIdWithRange));
                    }
                    scheduleInternal(workSpec, nextJobSchedulerIdWithRange);
                    if (Build.VERSION.SDK_INT == 23 && (b = b(this.h, this.i, workSpec.id)) != null) {
                        int indexOf = b.indexOf(Integer.valueOf(nextJobSchedulerIdWithRange));
                        if (indexOf >= 0) {
                            b.remove(indexOf);
                        }
                        if (!b.isEmpty()) {
                            nextJobSchedulerIdWithRange2 = b.get(0).intValue();
                        } else {
                            nextJobSchedulerIdWithRange2 = idGenerator.nextJobSchedulerIdWithRange(this.j.getConfiguration().getMinJobSchedulerId(), this.j.getConfiguration().getMaxJobSchedulerId());
                        }
                        scheduleInternal(workSpec, nextJobSchedulerIdWithRange2);
                    }
                    workDatabase.setTransactionSuccessful();
                }
                workDatabase.endTransaction();
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    @VisibleForTesting
    public void scheduleInternal(WorkSpec workSpec, int i) {
        JobInfo a2 = this.k.a(workSpec, i);
        Logger logger = Logger.get();
        String str = l;
        logger.debug(str, String.format("Scheduling work ID %s Job ID %s", workSpec.id, Integer.valueOf(i)), new Throwable[0]);
        try {
            if (this.i.schedule(a2) == 0) {
                Logger.get().warning(str, String.format("Unable to schedule work ID %s", workSpec.id), new Throwable[0]);
                if (workSpec.expedited && workSpec.outOfQuotaPolicy == OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST) {
                    workSpec.expedited = false;
                    Logger.get().debug(str, String.format("Scheduling a non-expedited job (work ID %s)", workSpec.id), new Throwable[0]);
                    scheduleInternal(workSpec, i);
                }
            }
        } catch (IllegalStateException e) {
            List<JobInfo> c = c(this.h, this.i);
            String format = String.format(Locale.getDefault(), "JobScheduler 100 job limit exceeded.  We count %d WorkManager jobs in JobScheduler; we have %d tracked jobs in our DB; our Configuration limit is %d.", Integer.valueOf(c != null ? c.size() : 0), Integer.valueOf(this.j.getWorkDatabase().workSpecDao().getScheduledWork().size()), Integer.valueOf(this.j.getConfiguration().getMaxSchedulerLimit()));
            Logger.get().error(l, format, new Throwable[0]);
            throw new IllegalStateException(format, e);
        } catch (Throwable th) {
            Logger.get().error(l, String.format("Unable to schedule %s", workSpec), th);
        }
    }

    @VisibleForTesting
    public SystemJobScheduler(Context context, WorkManagerImpl workManagerImpl, JobScheduler jobScheduler, a aVar) {
        this.h = context;
        this.j = workManagerImpl;
        this.i = jobScheduler;
        this.k = aVar;
    }
}
