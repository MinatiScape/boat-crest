package androidx.camera.core.impl;

import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public final class DeferrableSurfaces {

    /* loaded from: classes.dex */
    public class a implements FutureCallback<List<Surface>> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ boolean f701a;
        public final /* synthetic */ CallbackToFutureAdapter.Completer b;
        public final /* synthetic */ ScheduledFuture c;

        public a(boolean z, CallbackToFutureAdapter.Completer completer, ScheduledFuture scheduledFuture) {
            this.f701a = z;
            this.b = completer;
            this.c = scheduledFuture;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable List<Surface> list) {
            ArrayList arrayList = new ArrayList(list);
            if (this.f701a) {
                arrayList.removeAll(Collections.singleton(null));
            }
            this.b.set(arrayList);
            this.c.cancel(true);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            this.b.set(Collections.unmodifiableList(Collections.emptyList()));
            this.c.cancel(true);
        }
    }

    public static void decrementAll(@NonNull List<DeferrableSurface> list) {
        for (DeferrableSurface deferrableSurface : list) {
            deferrableSurface.decrementUseCount();
        }
    }

    public static /* synthetic */ void e(ListenableFuture listenableFuture, CallbackToFutureAdapter.Completer completer, long j) {
        if (listenableFuture.isDone()) {
            return;
        }
        completer.setException(new TimeoutException("Cannot complete surfaceList within " + j));
        listenableFuture.cancel(true);
    }

    public static /* synthetic */ void f(Executor executor, final ListenableFuture listenableFuture, final CallbackToFutureAdapter.Completer completer, final long j) {
        executor.execute(new Runnable() { // from class: androidx.camera.core.impl.o
            @Override // java.lang.Runnable
            public final void run() {
                DeferrableSurfaces.e(ListenableFuture.this, completer, j);
            }
        });
    }

    public static /* synthetic */ Object h(List list, ScheduledExecutorService scheduledExecutorService, final Executor executor, final long j, boolean z, final CallbackToFutureAdapter.Completer completer) throws Exception {
        final ListenableFuture successfulAsList = Futures.successfulAsList(list);
        ScheduledFuture<?> schedule = scheduledExecutorService.schedule(new Runnable() { // from class: androidx.camera.core.impl.p
            @Override // java.lang.Runnable
            public final void run() {
                DeferrableSurfaces.f(executor, successfulAsList, completer, j);
            }
        }, j, TimeUnit.MILLISECONDS);
        completer.addCancellationListener(new Runnable() { // from class: androidx.camera.core.impl.n
            @Override // java.lang.Runnable
            public final void run() {
                ListenableFuture.this.cancel(true);
            }
        }, executor);
        Futures.addCallback(successfulAsList, new a(z, completer, schedule), executor);
        return "surfaceList";
    }

    public static void incrementAll(@NonNull List<DeferrableSurface> list) throws DeferrableSurface.SurfaceClosedException {
        if (list.isEmpty()) {
            return;
        }
        int i = 0;
        do {
            try {
                list.get(i).incrementUseCount();
                i++;
            } catch (DeferrableSurface.SurfaceClosedException e) {
                for (int i2 = i - 1; i2 >= 0; i2--) {
                    list.get(i2).decrementUseCount();
                }
                throw e;
            }
        } while (i < list.size());
    }

    @NonNull
    public static ListenableFuture<List<Surface>> surfaceListWithTimeout(@NonNull Collection<DeferrableSurface> collection, final boolean z, final long j, @NonNull final Executor executor, @NonNull final ScheduledExecutorService scheduledExecutorService) {
        final ArrayList arrayList = new ArrayList();
        for (DeferrableSurface deferrableSurface : collection) {
            arrayList.add(deferrableSurface.getSurface());
        }
        return CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.m
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object h;
                h = DeferrableSurfaces.h(arrayList, scheduledExecutorService, executor, j, z, completer);
                return h;
            }
        });
    }

    public static boolean tryIncrementAll(@NonNull List<DeferrableSurface> list) {
        try {
            incrementAll(list);
            return true;
        } catch (DeferrableSurface.SurfaceClosedException unused) {
            return false;
        }
    }
}
