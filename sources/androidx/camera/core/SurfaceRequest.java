package androidx.camera.core;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.util.Size;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.camera.core.SurfaceRequest;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureCallback;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Consumer;
import androidx.core.util.Preconditions;
import com.google.auto.value.AutoValue;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes.dex */
public final class SurfaceRequest {

    /* renamed from: a  reason: collision with root package name */
    public final Size f652a;
    public final boolean b;
    public final CameraInternal c;
    public final ListenableFuture<Surface> d;
    public final CallbackToFutureAdapter.Completer<Surface> e;
    public final ListenableFuture<Void> f;
    public final CallbackToFutureAdapter.Completer<Void> g;
    public final DeferrableSurface h;
    @Nullable
    public TransformationInfo i;
    @Nullable
    public TransformationInfoListener j;
    @Nullable
    public Executor k;

    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class Result {
        public static final int RESULT_INVALID_SURFACE = 2;
        public static final int RESULT_REQUEST_CANCELLED = 1;
        public static final int RESULT_SURFACE_ALREADY_PROVIDED = 3;
        public static final int RESULT_SURFACE_USED_SUCCESSFULLY = 0;
        public static final int RESULT_WILL_NOT_PROVIDE_SURFACE = 4;

        @Retention(RetentionPolicy.SOURCE)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        /* loaded from: classes.dex */
        public @interface ResultCode {
        }

        @NonNull
        public static Result a(int i, @NonNull Surface surface) {
            return new f(i, surface);
        }

        public abstract int getResultCode();

        @NonNull
        public abstract Surface getSurface();
    }

    @AutoValue
    @ExperimentalUseCaseGroup
    /* loaded from: classes.dex */
    public static abstract class TransformationInfo {
        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static TransformationInfo of(@NonNull Rect rect, int i, int i2) {
            return new g(rect, i, i2);
        }

        @NonNull
        public abstract Rect getCropRect();

        public abstract int getRotationDegrees();

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public abstract int getTargetRotation();
    }

    @ExperimentalUseCaseGroup
    /* loaded from: classes.dex */
    public interface TransformationInfoListener {
        void onTransformationInfoUpdate(@NonNull TransformationInfo transformationInfo);
    }

    /* loaded from: classes.dex */
    public class a implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CallbackToFutureAdapter.Completer f653a;
        public final /* synthetic */ ListenableFuture b;

        public a(SurfaceRequest surfaceRequest, CallbackToFutureAdapter.Completer completer, ListenableFuture listenableFuture) {
            this.f653a = completer;
            this.b = listenableFuture;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r2) {
            Preconditions.checkState(this.f653a.set(null));
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            if (th instanceof e) {
                Preconditions.checkState(this.b.cancel(false));
            } else {
                Preconditions.checkState(this.f653a.set(null));
            }
        }
    }

    /* loaded from: classes.dex */
    public class b extends DeferrableSurface {
        public b() {
        }

        @Override // androidx.camera.core.impl.DeferrableSurface
        @NonNull
        public ListenableFuture<Surface> provideSurface() {
            return SurfaceRequest.this.d;
        }
    }

    /* loaded from: classes.dex */
    public class c implements FutureCallback<Surface> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ListenableFuture f654a;
        public final /* synthetic */ CallbackToFutureAdapter.Completer b;
        public final /* synthetic */ String c;

        public c(SurfaceRequest surfaceRequest, ListenableFuture listenableFuture, CallbackToFutureAdapter.Completer completer, String str) {
            this.f654a = listenableFuture;
            this.b = completer;
            this.c = str;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Surface surface) {
            Futures.propagate(this.f654a, this.b);
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            if (th instanceof CancellationException) {
                CallbackToFutureAdapter.Completer completer = this.b;
                Preconditions.checkState(completer.setException(new e(this.c + " cancelled.", th)));
                return;
            }
            this.b.set(null);
        }
    }

    /* loaded from: classes.dex */
    public class d implements FutureCallback<Void> {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Consumer f655a;
        public final /* synthetic */ Surface b;

        public d(SurfaceRequest surfaceRequest, Consumer consumer, Surface surface) {
            this.f655a = consumer;
            this.b = surface;
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        /* renamed from: a */
        public void onSuccess(@Nullable Void r3) {
            this.f655a.accept(Result.a(0, this.b));
        }

        @Override // androidx.camera.core.impl.utils.futures.FutureCallback
        public void onFailure(Throwable th) {
            Preconditions.checkState(th instanceof e, "Camera surface session should only fail with request cancellation. Instead failed due to:\n" + th);
            this.f655a.accept(Result.a(1, this.b));
        }
    }

    /* loaded from: classes.dex */
    public static final class e extends RuntimeException {
        public e(@NonNull String str, @NonNull Throwable th) {
            super(str, th);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public SurfaceRequest(@NonNull Size size, @NonNull CameraInternal cameraInternal, boolean z) {
        this.f652a = size;
        this.c = cameraInternal;
        this.b = z;
        final String str = "SurfaceRequest[size: " + size + ", id: " + hashCode() + "]";
        final AtomicReference atomicReference = new AtomicReference(null);
        ListenableFuture future = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.e2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                Object i;
                i = SurfaceRequest.i(atomicReference, str, completer);
                return i;
            }
        });
        CallbackToFutureAdapter.Completer<Void> completer = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference.get());
        this.g = completer;
        final AtomicReference atomicReference2 = new AtomicReference(null);
        ListenableFuture<Void> future2 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.f2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer2) {
                Object j;
                j = SurfaceRequest.j(atomicReference2, str, completer2);
                return j;
            }
        });
        this.f = future2;
        Futures.addCallback(future2, new a(this, completer, future), CameraXExecutors.directExecutor());
        final AtomicReference atomicReference3 = new AtomicReference(null);
        ListenableFuture<Surface> future3 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.d2
            @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
            public final Object attachCompleter(CallbackToFutureAdapter.Completer completer2) {
                Object k;
                k = SurfaceRequest.k(atomicReference3, str, completer2);
                return k;
            }
        });
        this.d = future3;
        this.e = (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference3.get());
        b bVar = new b();
        this.h = bVar;
        ListenableFuture<Void> terminationFuture = bVar.getTerminationFuture();
        Futures.addCallback(future3, new c(this, terminationFuture, (CallbackToFutureAdapter.Completer) Preconditions.checkNotNull((CallbackToFutureAdapter.Completer) atomicReference2.get()), str), CameraXExecutors.directExecutor());
        terminationFuture.addListener(new Runnable() { // from class: androidx.camera.core.i2
            @Override // java.lang.Runnable
            public final void run() {
                SurfaceRequest.this.l();
            }
        }, CameraXExecutors.directExecutor());
    }

    public static /* synthetic */ Object i(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-cancellation";
    }

    public static /* synthetic */ Object j(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-status";
    }

    public static /* synthetic */ Object k(AtomicReference atomicReference, String str, CallbackToFutureAdapter.Completer completer) throws Exception {
        atomicReference.set(completer);
        return str + "-Surface";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void l() {
        this.d.cancel(true);
    }

    public static /* synthetic */ void m(Consumer consumer, Surface surface) {
        consumer.accept(Result.a(3, surface));
    }

    public static /* synthetic */ void n(Consumer consumer, Surface surface) {
        consumer.accept(Result.a(4, surface));
    }

    @SuppressLint({"PairedRegistration"})
    public void addRequestCancellationListener(@NonNull Executor executor, @NonNull Runnable runnable) {
        this.g.addCancellationListener(runnable, executor);
    }

    @ExperimentalUseCaseGroup
    public void clearTransformationInfoListener() {
        this.j = null;
        this.k = null;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraInternal getCamera() {
        return this.c;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public DeferrableSurface getDeferrableSurface() {
        return this.h;
    }

    @NonNull
    public Size getResolution() {
        return this.f652a;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isRGBA8888Required() {
        return this.b;
    }

    public void provideSurface(@NonNull final Surface surface, @NonNull Executor executor, @NonNull final Consumer<Result> consumer) {
        if (!this.e.set(surface) && !this.d.isCancelled()) {
            Preconditions.checkState(this.d.isDone());
            try {
                this.d.get();
                executor.execute(new Runnable() { // from class: androidx.camera.core.j2
                    @Override // java.lang.Runnable
                    public final void run() {
                        SurfaceRequest.m(Consumer.this, surface);
                    }
                });
                return;
            } catch (InterruptedException | ExecutionException unused) {
                executor.execute(new Runnable() { // from class: androidx.camera.core.k2
                    @Override // java.lang.Runnable
                    public final void run() {
                        SurfaceRequest.n(Consumer.this, surface);
                    }
                });
                return;
            }
        }
        Futures.addCallback(this.f, new d(this, consumer, surface), executor);
    }

    @ExperimentalUseCaseGroup
    public void setTransformationInfoListener(@NonNull Executor executor, @NonNull final TransformationInfoListener transformationInfoListener) {
        this.j = transformationInfoListener;
        this.k = executor;
        final TransformationInfo transformationInfo = this.i;
        if (transformationInfo != null) {
            executor.execute(new Runnable() { // from class: androidx.camera.core.g2
                @Override // java.lang.Runnable
                public final void run() {
                    SurfaceRequest.TransformationInfoListener.this.onTransformationInfoUpdate(transformationInfo);
                }
            });
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @ExperimentalUseCaseGroup
    public void updateTransformationInfo(@NonNull final TransformationInfo transformationInfo) {
        this.i = transformationInfo;
        final TransformationInfoListener transformationInfoListener = this.j;
        if (transformationInfoListener != null) {
            this.k.execute(new Runnable() { // from class: androidx.camera.core.h2
                @Override // java.lang.Runnable
                public final void run() {
                    SurfaceRequest.TransformationInfoListener.this.onTransformationInfoUpdate(transformationInfo);
                }
            });
        }
    }

    public boolean willNotProvideSurface() {
        return this.e.setException(new DeferrableSurface.SurfaceUnavailableException("Surface request will not complete."));
    }
}
