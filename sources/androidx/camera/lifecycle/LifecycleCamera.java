package androidx.camera.lifecycle;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
/* loaded from: classes.dex */
final class LifecycleCamera implements LifecycleObserver, Camera {
    @GuardedBy("mLock")
    public final LifecycleOwner i;
    public final CameraUseCaseAdapter j;
    public final Object h = new Object();
    @GuardedBy("mLock")
    public boolean k = false;
    @GuardedBy("mLock")
    public boolean l = false;

    public LifecycleCamera(LifecycleOwner lifecycleOwner, CameraUseCaseAdapter cameraUseCaseAdapter) {
        this.i = lifecycleOwner;
        this.j = cameraUseCaseAdapter;
        if (lifecycleOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            cameraUseCaseAdapter.attachUseCases();
        } else {
            cameraUseCaseAdapter.detachUseCases();
        }
        lifecycleOwner.getLifecycle().addObserver(this);
    }

    public void a(Collection<UseCase> collection) throws CameraUseCaseAdapter.CameraException {
        synchronized (this.h) {
            this.j.addUseCases(collection);
        }
    }

    public CameraUseCaseAdapter b() {
        return this.j;
    }

    public LifecycleOwner c() {
        LifecycleOwner lifecycleOwner;
        synchronized (this.h) {
            lifecycleOwner = this.i;
        }
        return lifecycleOwner;
    }

    @NonNull
    public List<UseCase> d() {
        List<UseCase> unmodifiableList;
        synchronized (this.h) {
            unmodifiableList = Collections.unmodifiableList(this.j.getUseCases());
        }
        return unmodifiableList;
    }

    public boolean e(@NonNull UseCase useCase) {
        boolean contains;
        synchronized (this.h) {
            contains = this.j.getUseCases().contains(useCase);
        }
        return contains;
    }

    public void f() {
        synchronized (this.h) {
            if (this.k) {
                return;
            }
            onStop(this.i);
            this.k = true;
        }
    }

    public void g(Collection<UseCase> collection) {
        synchronized (this.h) {
            ArrayList arrayList = new ArrayList(collection);
            arrayList.retainAll(this.j.getUseCases());
            this.j.removeUseCases(arrayList);
        }
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public CameraControl getCameraControl() {
        return this.j.getCameraControl();
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public CameraInfo getCameraInfo() {
        return this.j.getCameraInfo();
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public LinkedHashSet<CameraInternal> getCameraInternals() {
        return this.j.getCameraInternals();
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public CameraConfig getExtendedConfig() {
        return this.j.getExtendedConfig();
    }

    public void h() {
        synchronized (this.h) {
            CameraUseCaseAdapter cameraUseCaseAdapter = this.j;
            cameraUseCaseAdapter.removeUseCases(cameraUseCaseAdapter.getUseCases());
        }
    }

    public void i() {
        synchronized (this.h) {
            if (this.k) {
                this.k = false;
                if (this.i.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    onStart(this.i);
                }
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        synchronized (this.h) {
            CameraUseCaseAdapter cameraUseCaseAdapter = this.j;
            cameraUseCaseAdapter.removeUseCases(cameraUseCaseAdapter.getUseCases());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(LifecycleOwner lifecycleOwner) {
        synchronized (this.h) {
            if (!this.k && !this.l) {
                this.j.attachUseCases();
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(LifecycleOwner lifecycleOwner) {
        synchronized (this.h) {
            if (!this.k && !this.l) {
                this.j.detachUseCases();
            }
        }
    }

    @Override // androidx.camera.core.Camera
    public void setExtendedConfig(@Nullable CameraConfig cameraConfig) throws CameraUseCaseAdapter.CameraException {
        this.j.setExtendedConfig(cameraConfig);
    }
}
