package org.greenrobot.greendao.async;

import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.query.Query;
/* loaded from: classes13.dex */
public class AsyncSession {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractDaoSession f15471a;
    public final a b = new a();
    public int c;

    public AsyncSession(AbstractDaoSession abstractDaoSession) {
        this.f15471a = abstractDaoSession;
    }

    public final <E> AsyncOperation a(AsyncOperation.OperationType operationType, Class<E> cls, Object obj, int i) {
        AsyncOperation asyncOperation = new AsyncOperation(operationType, this.f15471a.getDao(cls), null, obj, i | this.c);
        this.b.a(asyncOperation);
        return asyncOperation;
    }

    public final AsyncOperation b(AsyncOperation.OperationType operationType, Object obj, int i) {
        AsyncOperation asyncOperation = new AsyncOperation(operationType, null, this.f15471a.getDatabase(), obj, i | this.c);
        this.b.a(asyncOperation);
        return asyncOperation;
    }

    public final AsyncOperation c(AsyncOperation.OperationType operationType, Object obj, int i) {
        return a(operationType, obj.getClass(), obj, i);
    }

    public AsyncOperation callInTx(Callable<?> callable) {
        return callInTx(callable, 0);
    }

    public AsyncOperation count(Class<?> cls) {
        return count(cls, 0);
    }

    public AsyncOperation delete(Object obj) {
        return delete(obj, 0);
    }

    public <E> AsyncOperation deleteAll(Class<E> cls) {
        return deleteAll(cls, 0);
    }

    public AsyncOperation deleteByKey(Object obj) {
        return deleteByKey(obj, 0);
    }

    public <E> AsyncOperation deleteInTx(Class<E> cls, E... eArr) {
        return deleteInTx(cls, 0, eArr);
    }

    public AsyncOperationListener getListener() {
        return this.b.f();
    }

    public AsyncOperationListener getListenerMainThread() {
        return this.b.g();
    }

    public int getMaxOperationCountToMerge() {
        return this.b.h();
    }

    public int getSessionFlags() {
        return this.c;
    }

    public int getWaitForMergeMillis() {
        return this.b.i();
    }

    public AsyncOperation insert(Object obj) {
        return insert(obj, 0);
    }

    public <E> AsyncOperation insertInTx(Class<E> cls, E... eArr) {
        return insertInTx(cls, 0, eArr);
    }

    public AsyncOperation insertOrReplace(Object obj) {
        return insertOrReplace(obj, 0);
    }

    public <E> AsyncOperation insertOrReplaceInTx(Class<E> cls, E... eArr) {
        return insertOrReplaceInTx(cls, 0, eArr);
    }

    public boolean isCompleted() {
        return this.b.k();
    }

    public AsyncOperation load(Class<?> cls, Object obj) {
        return load(cls, obj, 0);
    }

    public AsyncOperation loadAll(Class<?> cls) {
        return loadAll(cls, 0);
    }

    public AsyncOperation queryList(Query<?> query) {
        return queryList(query, 0);
    }

    public AsyncOperation queryUnique(Query<?> query) {
        return queryUnique(query, 0);
    }

    public AsyncOperation refresh(Object obj) {
        return refresh(obj, 0);
    }

    public AsyncOperation runInTx(Runnable runnable) {
        return runInTx(runnable, 0);
    }

    public void setListener(AsyncOperationListener asyncOperationListener) {
        this.b.m(asyncOperationListener);
    }

    public void setListenerMainThread(AsyncOperationListener asyncOperationListener) {
        this.b.n(asyncOperationListener);
    }

    public void setMaxOperationCountToMerge(int i) {
        this.b.o(i);
    }

    public void setSessionFlags(int i) {
        this.c = i;
    }

    public void setWaitForMergeMillis(int i) {
        this.b.p(i);
    }

    public AsyncOperation update(Object obj) {
        return update(obj, 0);
    }

    public <E> AsyncOperation updateInTx(Class<E> cls, E... eArr) {
        return updateInTx(cls, 0, eArr);
    }

    public void waitForCompletion() {
        this.b.q();
    }

    public AsyncOperation callInTx(Callable<?> callable, int i) {
        return b(AsyncOperation.OperationType.TransactionCallable, callable, i);
    }

    public AsyncOperation count(Class<?> cls, int i) {
        return a(AsyncOperation.OperationType.Count, cls, null, i);
    }

    public AsyncOperation delete(Object obj, int i) {
        return c(AsyncOperation.OperationType.Delete, obj, i);
    }

    public <E> AsyncOperation deleteAll(Class<E> cls, int i) {
        return a(AsyncOperation.OperationType.DeleteAll, cls, null, i);
    }

    public AsyncOperation deleteByKey(Object obj, int i) {
        return c(AsyncOperation.OperationType.DeleteByKey, obj, i);
    }

    public <E> AsyncOperation deleteInTx(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.DeleteInTxArray, cls, eArr, i);
    }

    public AsyncOperation insert(Object obj, int i) {
        return c(AsyncOperation.OperationType.Insert, obj, i);
    }

    public <E> AsyncOperation insertInTx(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.InsertInTxArray, cls, eArr, i);
    }

    public AsyncOperation insertOrReplace(Object obj, int i) {
        return c(AsyncOperation.OperationType.InsertOrReplace, obj, i);
    }

    public <E> AsyncOperation insertOrReplaceInTx(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.InsertOrReplaceInTxArray, cls, eArr, i);
    }

    public AsyncOperation load(Class<?> cls, Object obj, int i) {
        return a(AsyncOperation.OperationType.Load, cls, obj, i);
    }

    public AsyncOperation loadAll(Class<?> cls, int i) {
        return a(AsyncOperation.OperationType.LoadAll, cls, null, i);
    }

    public AsyncOperation queryList(Query<?> query, int i) {
        return b(AsyncOperation.OperationType.QueryList, query, i);
    }

    public AsyncOperation queryUnique(Query<?> query, int i) {
        return b(AsyncOperation.OperationType.QueryUnique, query, i);
    }

    public AsyncOperation refresh(Object obj, int i) {
        return c(AsyncOperation.OperationType.Refresh, obj, i);
    }

    public AsyncOperation runInTx(Runnable runnable, int i) {
        return b(AsyncOperation.OperationType.TransactionRunnable, runnable, i);
    }

    public AsyncOperation update(Object obj, int i) {
        return c(AsyncOperation.OperationType.Update, obj, i);
    }

    public <E> AsyncOperation updateInTx(Class<E> cls, int i, E... eArr) {
        return a(AsyncOperation.OperationType.UpdateInTxArray, cls, eArr, i);
    }

    public boolean waitForCompletion(int i) {
        return this.b.r(i);
    }

    public <E> AsyncOperation deleteInTx(Class<E> cls, Iterable<E> iterable) {
        return deleteInTx(cls, iterable, 0);
    }

    public <E> AsyncOperation insertInTx(Class<E> cls, Iterable<E> iterable) {
        return insertInTx(cls, iterable, 0);
    }

    public <E> AsyncOperation insertOrReplaceInTx(Class<E> cls, Iterable<E> iterable) {
        return insertOrReplaceInTx(cls, iterable, 0);
    }

    public <E> AsyncOperation updateInTx(Class<E> cls, Iterable<E> iterable) {
        return updateInTx(cls, iterable, 0);
    }

    public <E> AsyncOperation deleteInTx(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.DeleteInTxIterable, cls, iterable, i);
    }

    public <E> AsyncOperation insertInTx(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.InsertInTxIterable, cls, iterable, i);
    }

    public <E> AsyncOperation insertOrReplaceInTx(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.InsertOrReplaceInTxIterable, cls, iterable, i);
    }

    public <E> AsyncOperation updateInTx(Class<E> cls, Iterable<E> iterable, int i) {
        return a(AsyncOperation.OperationType.UpdateInTxIterable, cls, iterable, i);
    }
}
