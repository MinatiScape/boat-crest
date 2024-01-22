package androidx.camera.core.impl;

import android.util.Log;
import android.view.Surface;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class DeferrableSurface {
    public static final boolean f = Logger.isDebugEnabled("DeferrableSurface");
    public static final AtomicInteger g = new AtomicInteger(0);
    public static final AtomicInteger h = new AtomicInteger(0);

    /* renamed from: a  reason: collision with root package name */
    public final Object f700a = new Object();
    @GuardedBy("mLock")
    public int b = 0;
    @GuardedBy("mLock")
    public boolean c = false;
    @GuardedBy("mLock")
    public CallbackToFutureAdapter.Completer<Void> d;
    public final ListenableFuture<Void> e;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static final class SurfaceClosedException extends Exception {
        public DeferrableSurface mDeferrableSurface;

        public SurfaceClosedException(@NonNull String str, @NonNull DeferrableSurface deferrableSurface) {
            super(str);
            this.mDeferrableSurface = deferrableSurface;
        }

        @NonNull
        public DeferrableSurface getDeferrableSurface() {
            return this.mDeferrableSurface;
        }
    }

    /* loaded from: classes.dex */
    public static final class SurfaceUnavailableException extends Exception {
        public SurfaceUnavailableException(@NonNull String str) {
            super(str);
        }
    }

    public DeferrableSurface() {
        ListenableFuture<Void> future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.k
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object c;
                c = DeferrableSurface.this.c(completer);
                return c;
            }
        });
        this.e = future;
        if (Logger.isDebugEnabled("DeferrableSurface")) {
            e("Surface created", h.incrementAndGet(), g.get());
            final String stackTraceString = Log.getStackTraceString(new Exception());
            future.addListener(new Runnable() { // from class: androidx.camera.core.impl.l
                @Override // java.lang.Runnable
                public final void run() {
                    DeferrableSurface.this.d(stackTraceString);
                }
            }, CameraXExecutors.directExecutor());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object c(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.f700a) {
            this.d = completer;
        }
        return "DeferrableSurface-termination(" + this + ")";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(String str) {
        try {
            this.e.get();
            e("Surface terminated", h.decrementAndGet(), g.get());
        } catch (Exception e) {
            Logger.e("DeferrableSurface", "Unexpected surface termination for " + this + "\nStack Trace:\n" + str);
            synchronized (this.f700a) {
                throw new IllegalArgumentException(String.format("DeferrableSurface %s [closed: %b, use_count: %s] terminated with unexpected exception.", this, Boolean.valueOf(this.c), Integer.valueOf(this.b)), e);
            }
        }
    }

    public final void close() {
        CallbackToFutureAdapter.Completer<Void> completer;
        synchronized (this.f700a) {
            if (this.c) {
                completer = null;
            } else {
                this.c = true;
                if (this.b == 0) {
                    completer = this.d;
                    this.d = null;
                } else {
                    completer = null;
                }
                if (Logger.isDebugEnabled("DeferrableSurface")) {
                    Logger.d("DeferrableSurface", "surface closed,  useCount=" + this.b + " closed=true " + this);
                }
            }
        }
        if (completer != null) {
            completer.set(null);
        }
    }

    public void decrementUseCount() {
        CallbackToFutureAdapter.Completer<Void> completer;
        synchronized (this.f700a) {
            int i = this.b;
            if (i != 0) {
                int i2 = i - 1;
                this.b = i2;
                if (i2 == 0 && this.c) {
                    completer = this.d;
                    this.d = null;
                } else {
                    completer = null;
                }
                if (Logger.isDebugEnabled("DeferrableSurface")) {
                    Logger.d("DeferrableSurface", "use count-1,  useCount=" + this.b + " closed=" + this.c + HexStringBuilder.DEFAULT_SEPARATOR + this);
                    if (this.b == 0) {
                        e("Surface no longer in use", h.get(), g.decrementAndGet());
                    }
                }
            } else {
                throw new IllegalStateException("Decrementing use count occurs more times than incrementing");
            }
        }
        if (completer != null) {
            completer.set(null);
        }
    }

    public final void e(@NonNull String str, int i, int i2) {
        if (!f && Logger.isDebugEnabled("DeferrableSurface")) {
            Logger.d("DeferrableSurface", "DeferrableSurface usage statistics may be inaccurate since debug logging was not enabled at static initialization time. App restart may be required to enable accurate usage statistics.");
        }
        Logger.d("DeferrableSurface", str + "[total_surfaces=" + i + ", used_surfaces=" + i2 + "](" + this + "}");
    }

    @NonNull
    public final ListenableFuture<Surface> getSurface() {
        synchronized (this.f700a) {
            if (this.c) {
                return Futures.immediateFailedFuture(new SurfaceClosedException("DeferrableSurface already closed.", this));
            }
            return provideSurface();
        }
    }

    @NonNull
    public ListenableFuture<Void> getTerminationFuture() {
        return Futures.nonCancellationPropagating(this.e);
    }

    @RestrictTo({RestrictTo.Scope.TESTS})
    public int getUseCount() {
        int i;
        synchronized (this.f700a) {
            i = this.b;
        }
        return i;
    }

    public void incrementUseCount() throws SurfaceClosedException {
        synchronized (this.f700a) {
            int i = this.b;
            if (i == 0 && this.c) {
                throw new SurfaceClosedException("Cannot begin use on a closed surface.", this);
            }
            this.b = i + 1;
            if (Logger.isDebugEnabled("DeferrableSurface")) {
                if (this.b == 1) {
                    e("New surface in use", h.get(), g.incrementAndGet());
                }
                Logger.d("DeferrableSurface", "use count+1, useCount=" + this.b + HexStringBuilder.DEFAULT_SEPARATOR + this);
            }
        }
    }

    @NonNull
    public abstract ListenableFuture<Surface> provideSurface();
}
