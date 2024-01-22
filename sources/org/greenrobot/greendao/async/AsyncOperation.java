package org.greenrobot.greendao.async;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.database.Database;
/* loaded from: classes13.dex */
public class AsyncOperation {
    public static final int FLAG_MERGE_TX = 1;
    public static final int FLAG_STOP_QUEUE_ON_EXCEPTION = 2;
    public static final int FLAG_TRACK_CREATOR_STACKTRACE = 4;

    /* renamed from: a  reason: collision with root package name */
    public final OperationType f15470a;
    public final AbstractDao<Object, Object> b;
    public final Database c;
    public final Object d;
    public final int e;
    public volatile long f;
    public volatile long g;
    public volatile boolean h;
    public volatile Throwable i;
    public final Exception j;
    public volatile Object k;
    public volatile int l;
    public int m;

    /* loaded from: classes13.dex */
    public enum OperationType {
        Insert,
        InsertInTxIterable,
        InsertInTxArray,
        InsertOrReplace,
        InsertOrReplaceInTxIterable,
        InsertOrReplaceInTxArray,
        Update,
        UpdateInTxIterable,
        UpdateInTxArray,
        Delete,
        DeleteInTxIterable,
        DeleteInTxArray,
        DeleteByKey,
        DeleteAll,
        TransactionRunnable,
        TransactionCallable,
        QueryList,
        QueryUnique,
        Load,
        LoadAll,
        Count,
        Refresh
    }

    public AsyncOperation(OperationType operationType, AbstractDao<?, ?> abstractDao, Database database, Object obj, int i) {
        this.f15470a = operationType;
        this.e = i;
        this.b = abstractDao;
        this.c = database;
        this.d = obj;
        this.j = (i & 4) != 0 ? new Exception("AsyncOperation was created here") : null;
    }

    public Database a() {
        Database database = this.c;
        return database != null ? database : this.b.getDatabase();
    }

    public boolean b(AsyncOperation asyncOperation) {
        return asyncOperation != null && isMergeTx() && asyncOperation.isMergeTx() && a() == asyncOperation.a();
    }

    public void c() {
        this.f = 0L;
        this.g = 0L;
        this.h = false;
        this.i = null;
        this.k = null;
        this.l = 0;
    }

    public synchronized void d() {
        this.h = true;
        notifyAll();
    }

    public Exception getCreatorStacktrace() {
        return this.j;
    }

    public long getDuration() {
        if (this.g != 0) {
            return this.g - this.f;
        }
        throw new DaoException("This operation did not yet complete");
    }

    public int getMergedOperationsCount() {
        return this.l;
    }

    public Object getParameter() {
        return this.d;
    }

    public synchronized Object getResult() {
        if (!this.h) {
            waitForCompletion();
        }
        if (this.i == null) {
        } else {
            throw new AsyncDaoException(this, this.i);
        }
        return this.k;
    }

    public int getSequenceNumber() {
        return this.m;
    }

    public Throwable getThrowable() {
        return this.i;
    }

    public long getTimeCompleted() {
        return this.g;
    }

    public long getTimeStarted() {
        return this.f;
    }

    public OperationType getType() {
        return this.f15470a;
    }

    public boolean isCompleted() {
        return this.h;
    }

    public boolean isCompletedSucessfully() {
        return this.h && this.i == null;
    }

    public boolean isFailed() {
        return this.i != null;
    }

    public boolean isMergeTx() {
        return (this.e & 1) != 0;
    }

    public void setThrowable(Throwable th) {
        this.i = th;
    }

    public synchronized Object waitForCompletion() {
        while (!this.h) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new DaoException("Interrupted while waiting for operation to complete", e);
            }
        }
        return this.k;
    }

    public synchronized boolean waitForCompletion(int i) {
        if (!this.h) {
            try {
                wait(i);
            } catch (InterruptedException e) {
                throw new DaoException("Interrupted while waiting for operation to complete", e);
            }
        }
        return this.h;
    }
}
