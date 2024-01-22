package androidx.camera.view;

import android.content.Context;
import android.view.OrientationEventListener;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.view.RotationProvider;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public final class RotationProvider {
    @NonNull
    @GuardedBy("mLock")
    @VisibleForTesting
    public final OrientationEventListener b;
    @Nullable
    @GuardedBy("mLock")
    public Executor c;
    @Nullable
    @GuardedBy("mLock")
    public Listener d;

    /* renamed from: a  reason: collision with root package name */
    public final Object f805a = new Object();
    @VisibleForTesting
    public boolean e = false;

    /* loaded from: classes.dex */
    public interface Listener {
        void onRotationChanged(int i);
    }

    /* loaded from: classes.dex */
    public class a extends OrientationEventListener {

        /* renamed from: a  reason: collision with root package name */
        public int f806a;

        public a(Context context) {
            super(context);
            this.f806a = -1;
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i) {
            final int a2;
            Executor executor;
            final Listener listener;
            if (i == -1 || this.f806a == (a2 = RotationProvider.a(i))) {
                return;
            }
            this.f806a = a2;
            synchronized (RotationProvider.this.f805a) {
                RotationProvider rotationProvider = RotationProvider.this;
                executor = rotationProvider.c;
                listener = rotationProvider.d;
            }
            if (executor == null || listener == null) {
                return;
            }
            executor.execute(new Runnable() { // from class: androidx.camera.view.s
                @Override // java.lang.Runnable
                public final void run() {
                    RotationProvider.Listener.this.onRotationChanged(a2);
                }
            });
        }
    }

    public RotationProvider(@NonNull Context context) {
        this.b = new a(context);
    }

    @VisibleForTesting
    public static int a(int i) {
        if (i >= 315 || i < 45) {
            return 0;
        }
        if (i >= 225) {
            return 1;
        }
        return i >= 135 ? 2 : 3;
    }

    public void clearListener() {
        synchronized (this.f805a) {
            this.b.disable();
            this.c = null;
            this.d = null;
        }
    }

    public boolean setListener(@NonNull Listener listener) {
        return setListener(CameraXExecutors.mainThreadExecutor(), listener);
    }

    public boolean setListener(@NonNull Executor executor, @NonNull Listener listener) {
        synchronized (this.f805a) {
            if (this.b.canDetectOrientation() || this.e) {
                this.c = executor;
                this.d = listener;
                this.b.enable();
                return true;
            }
            return false;
        }
    }
}
