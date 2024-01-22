package androidx.loader.content;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.os.OperationCanceledException;
import androidx.core.util.TimeUtils;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class AsyncTaskLoader<D> extends Loader<D> {
    public final Executor j;
    public volatile AsyncTaskLoader<D>.a k;
    public volatile AsyncTaskLoader<D>.a l;
    public long m;
    public long n;
    public Handler o;

    /* loaded from: classes.dex */
    public final class a extends ModernAsyncTask<Void, Void, D> implements Runnable {
        public final CountDownLatch q = new CountDownLatch(1);
        public boolean r;

        public a() {
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public void h(D d) {
            try {
                AsyncTaskLoader.this.a(this, d);
            } finally {
                this.q.countDown();
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        public void i(D d) {
            try {
                AsyncTaskLoader.this.b(this, d);
            } finally {
                this.q.countDown();
            }
        }

        @Override // androidx.loader.content.ModernAsyncTask
        /* renamed from: n */
        public D b(Void... voidArr) {
            try {
                return (D) AsyncTaskLoader.this.onLoadInBackground();
            } catch (OperationCanceledException e) {
                if (f()) {
                    return null;
                }
                throw e;
            }
        }

        public void o() {
            try {
                this.q.await();
            } catch (InterruptedException unused) {
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.r = false;
            AsyncTaskLoader.this.c();
        }
    }

    public AsyncTaskLoader(@NonNull Context context) {
        this(context, ModernAsyncTask.o);
    }

    public void a(AsyncTaskLoader<D>.a aVar, D d) {
        onCanceled(d);
        if (this.l == aVar) {
            rollbackContentChanged();
            this.n = SystemClock.uptimeMillis();
            this.l = null;
            deliverCancellation();
            c();
        }
    }

    public void b(AsyncTaskLoader<D>.a aVar, D d) {
        if (this.k != aVar) {
            a(aVar, d);
        } else if (isAbandoned()) {
            onCanceled(d);
        } else {
            commitContentChanged();
            this.n = SystemClock.uptimeMillis();
            this.k = null;
            deliverResult(d);
        }
    }

    public void c() {
        if (this.l != null || this.k == null) {
            return;
        }
        if (this.k.r) {
            this.k.r = false;
            this.o.removeCallbacks(this.k);
        }
        if (this.m > 0 && SystemClock.uptimeMillis() < this.n + this.m) {
            this.k.r = true;
            this.o.postAtTime(this.k, this.n + this.m);
            return;
        }
        this.k.c(this.j, null);
    }

    public void cancelLoadInBackground() {
    }

    @Override // androidx.loader.content.Loader
    @Deprecated
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        if (this.k != null) {
            printWriter.print(str);
            printWriter.print("mTask=");
            printWriter.print(this.k);
            printWriter.print(" waiting=");
            printWriter.println(this.k.r);
        }
        if (this.l != null) {
            printWriter.print(str);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.l);
            printWriter.print(" waiting=");
            printWriter.println(this.l.r);
        }
        if (this.m != 0) {
            printWriter.print(str);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.m, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.n, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }

    public boolean isLoadInBackgroundCanceled() {
        return this.l != null;
    }

    @Nullable
    public abstract D loadInBackground();

    @Override // androidx.loader.content.Loader
    public boolean onCancelLoad() {
        if (this.k != null) {
            if (!this.e) {
                this.h = true;
            }
            if (this.l != null) {
                if (this.k.r) {
                    this.k.r = false;
                    this.o.removeCallbacks(this.k);
                }
                this.k = null;
                return false;
            } else if (this.k.r) {
                this.k.r = false;
                this.o.removeCallbacks(this.k);
                this.k = null;
                return false;
            } else {
                boolean a2 = this.k.a(false);
                if (a2) {
                    this.l = this.k;
                    cancelLoadInBackground();
                }
                this.k = null;
                return a2;
            }
        }
        return false;
    }

    public void onCanceled(@Nullable D d) {
    }

    @Override // androidx.loader.content.Loader
    public void onForceLoad() {
        super.onForceLoad();
        cancelLoad();
        this.k = new a();
        c();
    }

    @Nullable
    public D onLoadInBackground() {
        return loadInBackground();
    }

    public void setUpdateThrottle(long j) {
        this.m = j;
        if (j != 0) {
            this.o = new Handler();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void waitForLoader() {
        AsyncTaskLoader<D>.a aVar = this.k;
        if (aVar != null) {
            aVar.o();
        }
    }

    public AsyncTaskLoader(@NonNull Context context, @NonNull Executor executor) {
        super(context);
        this.n = -10000L;
        this.j = executor;
    }
}
