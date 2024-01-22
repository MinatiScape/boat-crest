package org.greenrobot.greendao.async;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.DaoLog;
import org.greenrobot.greendao.async.AsyncOperation;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.Query;
/* loaded from: classes13.dex */
public class a implements Runnable, Handler.Callback {
    public static ExecutorService r = Executors.newCachedThreadPool();
    public volatile boolean i;
    public volatile AsyncOperationListener k;
    public volatile AsyncOperationListener l;
    public int n;
    public int o;
    public Handler p;
    public int q;
    public final BlockingQueue<AsyncOperation> h = new LinkedBlockingQueue();
    public volatile int j = 50;
    public volatile int m = 50;

    /* renamed from: org.greenrobot.greendao.async.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static /* synthetic */ class C0916a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f15472a;

        static {
            int[] iArr = new int[AsyncOperation.OperationType.values().length];
            f15472a = iArr;
            try {
                iArr[AsyncOperation.OperationType.Delete.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f15472a[AsyncOperation.OperationType.DeleteInTxIterable.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f15472a[AsyncOperation.OperationType.DeleteInTxArray.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f15472a[AsyncOperation.OperationType.Insert.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f15472a[AsyncOperation.OperationType.InsertInTxIterable.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f15472a[AsyncOperation.OperationType.InsertInTxArray.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f15472a[AsyncOperation.OperationType.InsertOrReplace.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f15472a[AsyncOperation.OperationType.InsertOrReplaceInTxIterable.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f15472a[AsyncOperation.OperationType.InsertOrReplaceInTxArray.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f15472a[AsyncOperation.OperationType.Update.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f15472a[AsyncOperation.OperationType.UpdateInTxIterable.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f15472a[AsyncOperation.OperationType.UpdateInTxArray.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f15472a[AsyncOperation.OperationType.TransactionRunnable.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f15472a[AsyncOperation.OperationType.TransactionCallable.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f15472a[AsyncOperation.OperationType.QueryList.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                f15472a[AsyncOperation.OperationType.QueryUnique.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                f15472a[AsyncOperation.OperationType.DeleteByKey.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                f15472a[AsyncOperation.OperationType.DeleteAll.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                f15472a[AsyncOperation.OperationType.Load.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                f15472a[AsyncOperation.OperationType.LoadAll.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                f15472a[AsyncOperation.OperationType.Count.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                f15472a[AsyncOperation.OperationType.Refresh.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
        }
    }

    public void a(AsyncOperation asyncOperation) {
        synchronized (this) {
            int i = this.q + 1;
            this.q = i;
            asyncOperation.m = i;
            this.h.add(asyncOperation);
            this.n++;
            if (!this.i) {
                this.i = true;
                r.execute(this);
            }
        }
    }

    public final void b(AsyncOperation asyncOperation) {
        asyncOperation.f = System.currentTimeMillis();
        try {
            switch (C0916a.f15472a[asyncOperation.f15470a.ordinal()]) {
                case 1:
                    asyncOperation.b.delete(asyncOperation.d);
                    break;
                case 2:
                    asyncOperation.b.deleteInTx((Iterable) asyncOperation.d);
                    break;
                case 3:
                    asyncOperation.b.deleteInTx((Object[]) asyncOperation.d);
                    break;
                case 4:
                    asyncOperation.b.insert(asyncOperation.d);
                    break;
                case 5:
                    asyncOperation.b.insertInTx((Iterable) asyncOperation.d);
                    break;
                case 6:
                    asyncOperation.b.insertInTx((Object[]) asyncOperation.d);
                    break;
                case 7:
                    asyncOperation.b.insertOrReplace(asyncOperation.d);
                    break;
                case 8:
                    asyncOperation.b.insertOrReplaceInTx((Iterable) asyncOperation.d);
                    break;
                case 9:
                    asyncOperation.b.insertOrReplaceInTx((Object[]) asyncOperation.d);
                    break;
                case 10:
                    asyncOperation.b.update(asyncOperation.d);
                    break;
                case 11:
                    asyncOperation.b.updateInTx((Iterable) asyncOperation.d);
                    break;
                case 12:
                    asyncOperation.b.updateInTx((Object[]) asyncOperation.d);
                    break;
                case 13:
                    e(asyncOperation);
                    break;
                case 14:
                    d(asyncOperation);
                    break;
                case 15:
                    asyncOperation.k = ((Query) asyncOperation.d).forCurrentThread().list();
                    break;
                case 16:
                    asyncOperation.k = ((Query) asyncOperation.d).forCurrentThread().unique();
                    break;
                case 17:
                    asyncOperation.b.deleteByKey(asyncOperation.d);
                    break;
                case 18:
                    asyncOperation.b.deleteAll();
                    break;
                case 19:
                    asyncOperation.k = asyncOperation.b.load(asyncOperation.d);
                    break;
                case 20:
                    asyncOperation.k = asyncOperation.b.loadAll();
                    break;
                case 21:
                    asyncOperation.k = Long.valueOf(asyncOperation.b.count());
                    break;
                case 22:
                    asyncOperation.b.refresh(asyncOperation.d);
                    break;
                default:
                    throw new DaoException("Unsupported operation: " + asyncOperation.f15470a);
            }
        } catch (Throwable th) {
            asyncOperation.i = th;
        }
        asyncOperation.g = System.currentTimeMillis();
    }

    public final void c(AsyncOperation asyncOperation) {
        b(asyncOperation);
        j(asyncOperation);
    }

    public final void d(AsyncOperation asyncOperation) throws Exception {
        Database a2 = asyncOperation.a();
        a2.beginTransaction();
        try {
            asyncOperation.k = ((Callable) asyncOperation.d).call();
            a2.setTransactionSuccessful();
        } finally {
            a2.endTransaction();
        }
    }

    public final void e(AsyncOperation asyncOperation) {
        Database a2 = asyncOperation.a();
        a2.beginTransaction();
        try {
            ((Runnable) asyncOperation.d).run();
            a2.setTransactionSuccessful();
        } finally {
            a2.endTransaction();
        }
    }

    public AsyncOperationListener f() {
        return this.k;
    }

    public AsyncOperationListener g() {
        return this.l;
    }

    public int h() {
        return this.j;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        AsyncOperationListener asyncOperationListener = this.l;
        if (asyncOperationListener != null) {
            asyncOperationListener.onAsyncOperationCompleted((AsyncOperation) message.obj);
            return false;
        }
        return false;
    }

    public int i() {
        return this.m;
    }

    public final void j(AsyncOperation asyncOperation) {
        asyncOperation.d();
        AsyncOperationListener asyncOperationListener = this.k;
        if (asyncOperationListener != null) {
            asyncOperationListener.onAsyncOperationCompleted(asyncOperation);
        }
        if (this.l != null) {
            if (this.p == null) {
                this.p = new Handler(Looper.getMainLooper(), this);
            }
            this.p.sendMessage(this.p.obtainMessage(1, asyncOperation));
        }
        synchronized (this) {
            int i = this.o + 1;
            this.o = i;
            if (i == this.n) {
                notifyAll();
            }
        }
    }

    public synchronized boolean k() {
        return this.n == this.o;
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0063, code lost:
        r4 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(org.greenrobot.greendao.async.AsyncOperation r8, org.greenrobot.greendao.async.AsyncOperation r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Async transaction could not be ended, success so far was: "
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.add(r8)
            r1.add(r9)
            org.greenrobot.greendao.database.Database r8 = r8.a()
            r8.beginTransaction()
            r9 = 0
            r2 = r9
        L16:
            int r3 = r1.size()     // Catch: java.lang.Throwable -> Lb5
            r4 = 1
            if (r2 >= r3) goto L63
            java.lang.Object r3 = r1.get(r2)     // Catch: java.lang.Throwable -> Lb5
            org.greenrobot.greendao.async.AsyncOperation r3 = (org.greenrobot.greendao.async.AsyncOperation) r3     // Catch: java.lang.Throwable -> Lb5
            r7.b(r3)     // Catch: java.lang.Throwable -> Lb5
            boolean r5 = r3.isFailed()     // Catch: java.lang.Throwable -> Lb5
            if (r5 == 0) goto L2d
            goto L63
        L2d:
            int r5 = r1.size()     // Catch: java.lang.Throwable -> Lb5
            int r5 = r5 - r4
            if (r2 != r5) goto L60
            java.util.concurrent.BlockingQueue<org.greenrobot.greendao.async.AsyncOperation> r5 = r7.h     // Catch: java.lang.Throwable -> Lb5
            java.lang.Object r5 = r5.peek()     // Catch: java.lang.Throwable -> Lb5
            org.greenrobot.greendao.async.AsyncOperation r5 = (org.greenrobot.greendao.async.AsyncOperation) r5     // Catch: java.lang.Throwable -> Lb5
            int r6 = r7.j     // Catch: java.lang.Throwable -> Lb5
            if (r2 >= r6) goto L5c
            boolean r3 = r3.b(r5)     // Catch: java.lang.Throwable -> Lb5
            if (r3 == 0) goto L5c
            java.util.concurrent.BlockingQueue<org.greenrobot.greendao.async.AsyncOperation> r3 = r7.h     // Catch: java.lang.Throwable -> Lb5
            java.lang.Object r3 = r3.remove()     // Catch: java.lang.Throwable -> Lb5
            org.greenrobot.greendao.async.AsyncOperation r3 = (org.greenrobot.greendao.async.AsyncOperation) r3     // Catch: java.lang.Throwable -> Lb5
            if (r3 != r5) goto L54
            r1.add(r3)     // Catch: java.lang.Throwable -> Lb5
            goto L60
        L54:
            org.greenrobot.greendao.DaoException r1 = new org.greenrobot.greendao.DaoException     // Catch: java.lang.Throwable -> Lb5
            java.lang.String r2 = "Internal error: peeked op did not match removed op"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> Lb5
            throw r1     // Catch: java.lang.Throwable -> Lb5
        L5c:
            r8.setTransactionSuccessful()     // Catch: java.lang.Throwable -> Lb5
            goto L64
        L60:
            int r2 = r2 + 1
            goto L16
        L63:
            r4 = r9
        L64:
            r8.endTransaction()     // Catch: java.lang.RuntimeException -> L69
            r9 = r4
            goto L7c
        L69:
            r8 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r4)
            java.lang.String r0 = r2.toString()
            org.greenrobot.greendao.DaoLog.i(r0, r8)
        L7c:
            if (r9 == 0) goto L98
            int r8 = r1.size()
            java.util.Iterator r9 = r1.iterator()
        L86:
            boolean r0 = r9.hasNext()
            if (r0 == 0) goto Lb4
            java.lang.Object r0 = r9.next()
            org.greenrobot.greendao.async.AsyncOperation r0 = (org.greenrobot.greendao.async.AsyncOperation) r0
            r0.l = r8
            r7.j(r0)
            goto L86
        L98:
            java.lang.String r8 = "Reverted merged transaction because one of the operations failed. Executing operations one by one instead..."
            org.greenrobot.greendao.DaoLog.i(r8)
            java.util.Iterator r8 = r1.iterator()
        La1:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto Lb4
            java.lang.Object r9 = r8.next()
            org.greenrobot.greendao.async.AsyncOperation r9 = (org.greenrobot.greendao.async.AsyncOperation) r9
            r9.c()
            r7.c(r9)
            goto La1
        Lb4:
            return
        Lb5:
            r1 = move-exception
            r8.endTransaction()     // Catch: java.lang.RuntimeException -> Lba
            goto Lcd
        Lba:
            r8 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r0)
            r2.append(r9)
            java.lang.String r9 = r2.toString()
            org.greenrobot.greendao.DaoLog.i(r9, r8)
        Lcd:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.greenrobot.greendao.async.a.l(org.greenrobot.greendao.async.AsyncOperation, org.greenrobot.greendao.async.AsyncOperation):void");
    }

    public void m(AsyncOperationListener asyncOperationListener) {
        this.k = asyncOperationListener;
    }

    public void n(AsyncOperationListener asyncOperationListener) {
        this.l = asyncOperationListener;
    }

    public void o(int i) {
        this.j = i;
    }

    public void p(int i) {
        this.m = i;
    }

    public synchronized void q() {
        while (!k()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e);
            }
        }
    }

    public synchronized boolean r(int i) {
        if (!k()) {
            try {
                wait(i);
            } catch (InterruptedException e) {
                throw new DaoException("Interrupted while waiting for all operations to complete", e);
            }
        }
        return k();
    }

    @Override // java.lang.Runnable
    public void run() {
        AsyncOperation poll;
        while (true) {
            try {
                AsyncOperation poll2 = this.h.poll(1L, TimeUnit.SECONDS);
                if (poll2 == null) {
                    synchronized (this) {
                        poll2 = this.h.poll();
                        if (poll2 == null) {
                            return;
                        }
                    }
                }
                if (poll2.isMergeTx() && (poll = this.h.poll(this.m, TimeUnit.MILLISECONDS)) != null) {
                    if (poll2.b(poll)) {
                        l(poll2, poll);
                    } else {
                        c(poll2);
                        c(poll);
                    }
                } else {
                    c(poll2);
                }
            } catch (InterruptedException e) {
                DaoLog.w(Thread.currentThread().getName() + " was interruppted", e);
                return;
            } finally {
                this.i = false;
            }
        }
    }
}
