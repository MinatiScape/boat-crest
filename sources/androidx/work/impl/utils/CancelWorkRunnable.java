package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Operation;
import androidx.work.WorkInfo;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.Scheduler;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpecDao;
import java.util.LinkedList;
import java.util.UUID;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public abstract class CancelWorkRunnable implements Runnable {
    public final OperationImpl h = new OperationImpl();

    /* loaded from: classes.dex */
    public class a extends CancelWorkRunnable {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ UUID j;

        public a(WorkManagerImpl workManagerImpl, UUID uuid) {
            this.i = workManagerImpl;
            this.j = uuid;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void d() {
            WorkDatabase workDatabase = this.i.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                a(this.i, this.j.toString());
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                c(this.i);
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends CancelWorkRunnable {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ String j;

        public b(WorkManagerImpl workManagerImpl, String str) {
            this.i = workManagerImpl;
            this.j = str;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void d() {
            WorkDatabase workDatabase = this.i.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                for (String str : workDatabase.workSpecDao().getUnfinishedWorkWithTag(this.j)) {
                    a(this.i, str);
                }
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                c(this.i);
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    /* loaded from: classes.dex */
    public class c extends CancelWorkRunnable {
        public final /* synthetic */ WorkManagerImpl i;
        public final /* synthetic */ String j;
        public final /* synthetic */ boolean k;

        public c(WorkManagerImpl workManagerImpl, String str, boolean z) {
            this.i = workManagerImpl;
            this.j = str;
            this.k = z;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void d() {
            WorkDatabase workDatabase = this.i.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                for (String str : workDatabase.workSpecDao().getUnfinishedWorkWithName(this.j)) {
                    a(this.i, str);
                }
                workDatabase.setTransactionSuccessful();
                workDatabase.endTransaction();
                if (this.k) {
                    c(this.i);
                }
            } catch (Throwable th) {
                workDatabase.endTransaction();
                throw th;
            }
        }
    }

    /* loaded from: classes.dex */
    public class d extends CancelWorkRunnable {
        public final /* synthetic */ WorkManagerImpl i;

        public d(WorkManagerImpl workManagerImpl) {
            this.i = workManagerImpl;
        }

        @Override // androidx.work.impl.utils.CancelWorkRunnable
        @WorkerThread
        public void d() {
            WorkDatabase workDatabase = this.i.getWorkDatabase();
            workDatabase.beginTransaction();
            try {
                for (String str : workDatabase.workSpecDao().getAllUnfinishedWork()) {
                    a(this.i, str);
                }
                new PreferenceUtils(this.i.getWorkDatabase()).setLastCancelAllTimeMillis(System.currentTimeMillis());
                workDatabase.setTransactionSuccessful();
            } finally {
                workDatabase.endTransaction();
            }
        }
    }

    public static CancelWorkRunnable forAll(@NonNull WorkManagerImpl workManagerImpl) {
        return new d(workManagerImpl);
    }

    public static CancelWorkRunnable forId(@NonNull UUID uuid, @NonNull WorkManagerImpl workManagerImpl) {
        return new a(workManagerImpl, uuid);
    }

    public static CancelWorkRunnable forName(@NonNull String str, @NonNull WorkManagerImpl workManagerImpl, boolean z) {
        return new c(workManagerImpl, str, z);
    }

    public static CancelWorkRunnable forTag(@NonNull String str, @NonNull WorkManagerImpl workManagerImpl) {
        return new b(workManagerImpl, str);
    }

    public void a(WorkManagerImpl workManagerImpl, String str) {
        b(workManagerImpl.getWorkDatabase(), str);
        workManagerImpl.getProcessor().stopAndCancelWork(str);
        for (Scheduler scheduler : workManagerImpl.getSchedulers()) {
            scheduler.cancel(str);
        }
    }

    public final void b(WorkDatabase workDatabase, String str) {
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        DependencyDao dependencyDao = workDatabase.dependencyDao();
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            WorkInfo.State state = workSpecDao.getState(str2);
            if (state != WorkInfo.State.SUCCEEDED && state != WorkInfo.State.FAILED) {
                workSpecDao.setState(WorkInfo.State.CANCELLED, str2);
            }
            linkedList.addAll(dependencyDao.getDependentWorkIds(str2));
        }
    }

    public void c(WorkManagerImpl workManagerImpl) {
        Schedulers.schedule(workManagerImpl.getConfiguration(), workManagerImpl.getWorkDatabase(), workManagerImpl.getSchedulers());
    }

    public abstract void d();

    public Operation getOperation() {
        return this.h;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            d();
            this.h.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.h.setState(new Operation.State.FAILURE(th));
        }
    }
}
