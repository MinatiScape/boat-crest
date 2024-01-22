package androidx.core.provider;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import androidx.annotation.GuardedBy;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.polidea.rxandroidble2.ClientComponent;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
@Deprecated
/* loaded from: classes.dex */
public class SelfDestructiveThread {
    @GuardedBy("mLock")
    public HandlerThread b;
    @GuardedBy("mLock")
    public Handler c;
    public final int f;
    public final int g;
    public final String h;

    /* renamed from: a  reason: collision with root package name */
    public final Object f1084a = new Object();
    public Handler.Callback e = new a();
    @GuardedBy("mLock")
    public int d = 0;

    /* loaded from: classes.dex */
    public interface ReplyCallback<T> {
        void onReply(T t);
    }

    /* loaded from: classes.dex */
    public class a implements Handler.Callback {
        public a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                SelfDestructiveThread.this.a();
                return true;
            } else if (i != 1) {
                return true;
            } else {
                SelfDestructiveThread.this.b((Runnable) message.obj);
                return true;
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ Callable h;
        public final /* synthetic */ Handler i;
        public final /* synthetic */ ReplyCallback j;

        /* loaded from: classes.dex */
        public class a implements Runnable {
            public final /* synthetic */ Object h;

            public a(Object obj) {
                this.h = obj;
            }

            @Override // java.lang.Runnable
            public void run() {
                b.this.j.onReply(this.h);
            }
        }

        public b(SelfDestructiveThread selfDestructiveThread, Callable callable, Handler handler, ReplyCallback replyCallback) {
            this.h = callable;
            this.i = handler;
            this.j = replyCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            try {
                obj = this.h.call();
            } catch (Exception unused) {
                obj = null;
            }
            this.i.post(new a(obj));
        }
    }

    /* loaded from: classes.dex */
    public class c implements Runnable {
        public final /* synthetic */ AtomicReference h;
        public final /* synthetic */ Callable i;
        public final /* synthetic */ ReentrantLock j;
        public final /* synthetic */ AtomicBoolean k;
        public final /* synthetic */ Condition l;

        public c(SelfDestructiveThread selfDestructiveThread, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
            this.h = atomicReference;
            this.i = callable;
            this.j = reentrantLock;
            this.k = atomicBoolean;
            this.l = condition;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.h.set(this.i.call());
            } catch (Exception unused) {
            }
            this.j.lock();
            try {
                this.k.set(false);
                this.l.signal();
            } finally {
                this.j.unlock();
            }
        }
    }

    public SelfDestructiveThread(String str, int i, int i2) {
        this.h = str;
        this.g = i;
        this.f = i2;
    }

    public void a() {
        synchronized (this.f1084a) {
            if (this.c.hasMessages(1)) {
                return;
            }
            this.b.quit();
            this.b = null;
            this.c = null;
        }
    }

    public void b(Runnable runnable) {
        runnable.run();
        synchronized (this.f1084a) {
            this.c.removeMessages(0);
            Handler handler = this.c;
            handler.sendMessageDelayed(handler.obtainMessage(0), this.f);
        }
    }

    public final void c(Runnable runnable) {
        synchronized (this.f1084a) {
            if (this.b == null) {
                HandlerThread handlerThread = new HandlerThread(this.h, this.g);
                this.b = handlerThread;
                handlerThread.start();
                this.c = new Handler(this.b.getLooper(), this.e);
                this.d++;
            }
            this.c.removeMessages(0);
            Handler handler = this.c;
            handler.sendMessage(handler.obtainMessage(1, runnable));
        }
    }

    @VisibleForTesting
    public int getGeneration() {
        int i;
        synchronized (this.f1084a) {
            i = this.d;
        }
        return i;
    }

    @VisibleForTesting
    public boolean isRunning() {
        boolean z;
        synchronized (this.f1084a) {
            z = this.b != null;
        }
        return z;
    }

    public <T> void postAndReply(Callable<T> callable, ReplyCallback<T> replyCallback) {
        c(new b(this, callable, androidx.core.provider.b.a(), replyCallback));
    }

    public <T> T postAndWait(Callable<T> callable, int i) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition newCondition = reentrantLock.newCondition();
        AtomicReference atomicReference = new AtomicReference();
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        c(new c(this, atomicReference, callable, reentrantLock, atomicBoolean, newCondition));
        reentrantLock.lock();
        try {
            if (!atomicBoolean.get()) {
                return (T) atomicReference.get();
            }
            long nanos = TimeUnit.MILLISECONDS.toNanos(i);
            do {
                try {
                    nanos = newCondition.awaitNanos(nanos);
                } catch (InterruptedException unused) {
                }
                if (!atomicBoolean.get()) {
                    return (T) atomicReference.get();
                }
            } while (nanos > 0);
            throw new InterruptedException(ClientComponent.NamedSchedulers.TIMEOUT);
        } finally {
            reentrantLock.unlock();
        }
    }
}
