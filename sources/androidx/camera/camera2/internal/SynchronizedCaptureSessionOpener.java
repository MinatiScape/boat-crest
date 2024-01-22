package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraDevice;
import android.os.Build;
import android.os.Handler;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.camera.camera2.internal.SynchronizedCaptureSession;
import androidx.camera.camera2.internal.compat.params.OutputConfigurationCompat;
import androidx.camera.camera2.internal.compat.params.SessionConfigurationCompat;
import androidx.camera.core.impl.DeferrableSurface;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
/* loaded from: classes.dex */
public final class SynchronizedCaptureSessionOpener {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final b f508a;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    /* loaded from: classes.dex */
    public @interface SynchronizedSessionFeature {
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public final Executor f509a;
        public final ScheduledExecutorService b;
        public final Handler c;
        public final x0 d;
        public final int e;
        public final Set<String> f;

        public a(@NonNull Executor executor, @NonNull ScheduledExecutorService scheduledExecutorService, @NonNull Handler handler, @NonNull x0 x0Var, int i) {
            HashSet hashSet = new HashSet();
            this.f = hashSet;
            this.f509a = executor;
            this.b = scheduledExecutorService;
            this.c = handler;
            this.d = x0Var;
            this.e = i;
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 23) {
                hashSet.add("force_close");
            }
            if (i == 2 || i2 <= 23) {
                hashSet.add("deferrableSurface_close");
            }
            if (i == 2) {
                hashSet.add("wait_for_request");
            }
        }

        @NonNull
        public SynchronizedCaptureSessionOpener a() {
            if (this.f.isEmpty()) {
                return new SynchronizedCaptureSessionOpener(new v1(this.d, this.f509a, this.b, this.c));
            }
            return new SynchronizedCaptureSessionOpener(new z1(this.f, this.d, this.f509a, this.b, this.c));
        }
    }

    /* loaded from: classes.dex */
    public interface b {
        @NonNull
        ListenableFuture<Void> a(@NonNull CameraDevice cameraDevice, @NonNull SessionConfigurationCompat sessionConfigurationCompat, @NonNull List<DeferrableSurface> list);

        @NonNull
        SessionConfigurationCompat g(int i, @NonNull List<OutputConfigurationCompat> list, @NonNull SynchronizedCaptureSession.StateCallback stateCallback);

        @NonNull
        Executor getExecutor();

        @NonNull
        ListenableFuture<List<Surface>> h(@NonNull List<DeferrableSurface> list, long j);

        boolean stop();
    }

    public SynchronizedCaptureSessionOpener(@NonNull b bVar) {
        this.f508a = bVar;
    }

    @NonNull
    public SessionConfigurationCompat a(int i, @NonNull List<OutputConfigurationCompat> list, @NonNull SynchronizedCaptureSession.StateCallback stateCallback) {
        return this.f508a.g(i, list, stateCallback);
    }

    @NonNull
    public Executor b() {
        return this.f508a.getExecutor();
    }

    @NonNull
    public ListenableFuture<Void> c(@NonNull CameraDevice cameraDevice, @NonNull SessionConfigurationCompat sessionConfigurationCompat, @NonNull List<DeferrableSurface> list) {
        return this.f508a.a(cameraDevice, sessionConfigurationCompat, list);
    }

    @NonNull
    public ListenableFuture<List<Surface>> d(@NonNull List<DeferrableSurface> list, long j) {
        return this.f508a.h(list, j);
    }

    public boolean e() {
        return this.f508a.stop();
    }
}
