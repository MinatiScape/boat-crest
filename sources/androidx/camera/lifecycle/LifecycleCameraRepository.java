package androidx.camera.lifecycle;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.UseCase;
import androidx.camera.core.ViewPort;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.auto.value.AutoValue;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class LifecycleCameraRepository {

    /* renamed from: a  reason: collision with root package name */
    public final Object f794a = new Object();
    @GuardedBy("mLock")
    public final Map<a, LifecycleCamera> b = new HashMap();
    @GuardedBy("mLock")
    public final Map<LifecycleCameraRepositoryObserver, Set<a>> c = new HashMap();
    @GuardedBy("mLock")
    public final ArrayDeque<LifecycleOwner> d = new ArrayDeque<>();

    /* loaded from: classes.dex */
    public static class LifecycleCameraRepositoryObserver implements LifecycleObserver {
        public final LifecycleCameraRepository h;
        public final LifecycleOwner i;

        public LifecycleCameraRepositoryObserver(LifecycleOwner lifecycleOwner, LifecycleCameraRepository lifecycleCameraRepository) {
            this.i = lifecycleOwner;
            this.h = lifecycleCameraRepository;
        }

        public LifecycleOwner a() {
            return this.i;
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy(LifecycleOwner lifecycleOwner) {
            this.h.n(lifecycleOwner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        public void onStart(LifecycleOwner lifecycleOwner) {
            this.h.i(lifecycleOwner);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop(LifecycleOwner lifecycleOwner) {
            this.h.j(lifecycleOwner);
        }
    }

    @AutoValue
    /* loaded from: classes.dex */
    public static abstract class a {
        public static a a(@NonNull LifecycleOwner lifecycleOwner, @NonNull CameraUseCaseAdapter.CameraId cameraId) {
            return new androidx.camera.lifecycle.a(lifecycleOwner, cameraId);
        }

        @NonNull
        public abstract CameraUseCaseAdapter.CameraId b();

        @NonNull
        public abstract LifecycleOwner c();
    }

    public void a(@NonNull LifecycleCamera lifecycleCamera, @Nullable ViewPort viewPort, @NonNull Collection<UseCase> collection) {
        synchronized (this.f794a) {
            Preconditions.checkArgument(!collection.isEmpty());
            LifecycleOwner c = lifecycleCamera.c();
            for (a aVar : this.c.get(e(c))) {
                LifecycleCamera lifecycleCamera2 = (LifecycleCamera) Preconditions.checkNotNull(this.b.get(aVar));
                if (!lifecycleCamera2.equals(lifecycleCamera) && !lifecycleCamera2.d().isEmpty()) {
                    throw new IllegalArgumentException("Multiple LifecycleCameras with use cases are registered to the same LifecycleOwner.");
                }
            }
            try {
                lifecycleCamera.b().setViewPort(viewPort);
                lifecycleCamera.a(collection);
                if (c.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                    i(c);
                }
            } catch (CameraUseCaseAdapter.CameraException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    public void b() {
        synchronized (this.f794a) {
            for (LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver : new HashSet(this.c.keySet())) {
                n(lifecycleCameraRepositoryObserver.a());
            }
        }
    }

    public LifecycleCamera c(@NonNull LifecycleOwner lifecycleOwner, @NonNull CameraUseCaseAdapter cameraUseCaseAdapter) {
        LifecycleCamera lifecycleCamera;
        synchronized (this.f794a) {
            Preconditions.checkArgument(this.b.get(a.a(lifecycleOwner, cameraUseCaseAdapter.getCameraId())) == null, "LifecycleCamera already exists for the given LifecycleOwner and set of cameras");
            if (lifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) {
                lifecycleCamera = new LifecycleCamera(lifecycleOwner, cameraUseCaseAdapter);
                if (cameraUseCaseAdapter.getUseCases().isEmpty()) {
                    lifecycleCamera.f();
                }
                h(lifecycleCamera);
            } else {
                throw new IllegalArgumentException("Trying to create LifecycleCamera with destroyed lifecycle.");
            }
        }
        return lifecycleCamera;
    }

    @Nullable
    public LifecycleCamera d(LifecycleOwner lifecycleOwner, CameraUseCaseAdapter.CameraId cameraId) {
        LifecycleCamera lifecycleCamera;
        synchronized (this.f794a) {
            lifecycleCamera = this.b.get(a.a(lifecycleOwner, cameraId));
        }
        return lifecycleCamera;
    }

    public final LifecycleCameraRepositoryObserver e(LifecycleOwner lifecycleOwner) {
        synchronized (this.f794a) {
            for (LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver : this.c.keySet()) {
                if (lifecycleOwner.equals(lifecycleCameraRepositoryObserver.a())) {
                    return lifecycleCameraRepositoryObserver;
                }
            }
            return null;
        }
    }

    public Collection<LifecycleCamera> f() {
        Collection<LifecycleCamera> unmodifiableCollection;
        synchronized (this.f794a) {
            unmodifiableCollection = Collections.unmodifiableCollection(this.b.values());
        }
        return unmodifiableCollection;
    }

    public final boolean g(LifecycleOwner lifecycleOwner) {
        synchronized (this.f794a) {
            LifecycleCameraRepositoryObserver e = e(lifecycleOwner);
            if (e == null) {
                return false;
            }
            for (a aVar : this.c.get(e)) {
                if (!((LifecycleCamera) Preconditions.checkNotNull(this.b.get(aVar))).d().isEmpty()) {
                    return true;
                }
            }
            return false;
        }
    }

    public final void h(LifecycleCamera lifecycleCamera) {
        Set<a> hashSet;
        synchronized (this.f794a) {
            LifecycleOwner c = lifecycleCamera.c();
            a a2 = a.a(c, lifecycleCamera.b().getCameraId());
            LifecycleCameraRepositoryObserver e = e(c);
            if (e != null) {
                hashSet = this.c.get(e);
            } else {
                hashSet = new HashSet<>();
            }
            hashSet.add(a2);
            this.b.put(a2, lifecycleCamera);
            if (e == null) {
                LifecycleCameraRepositoryObserver lifecycleCameraRepositoryObserver = new LifecycleCameraRepositoryObserver(c, this);
                this.c.put(lifecycleCameraRepositoryObserver, hashSet);
                c.getLifecycle().addObserver(lifecycleCameraRepositoryObserver);
            }
        }
    }

    public void i(LifecycleOwner lifecycleOwner) {
        synchronized (this.f794a) {
            if (g(lifecycleOwner)) {
                if (this.d.isEmpty()) {
                    this.d.push(lifecycleOwner);
                } else {
                    LifecycleOwner peek = this.d.peek();
                    if (!lifecycleOwner.equals(peek)) {
                        k(peek);
                        this.d.remove(lifecycleOwner);
                        this.d.push(lifecycleOwner);
                    }
                }
                o(lifecycleOwner);
            }
        }
    }

    public void j(LifecycleOwner lifecycleOwner) {
        synchronized (this.f794a) {
            this.d.remove(lifecycleOwner);
            k(lifecycleOwner);
            if (!this.d.isEmpty()) {
                o(this.d.peek());
            }
        }
    }

    public final void k(LifecycleOwner lifecycleOwner) {
        synchronized (this.f794a) {
            for (a aVar : this.c.get(e(lifecycleOwner))) {
                ((LifecycleCamera) Preconditions.checkNotNull(this.b.get(aVar))).f();
            }
        }
    }

    public void l(@NonNull Collection<UseCase> collection) {
        synchronized (this.f794a) {
            for (a aVar : this.b.keySet()) {
                LifecycleCamera lifecycleCamera = this.b.get(aVar);
                boolean z = !lifecycleCamera.d().isEmpty();
                lifecycleCamera.g(collection);
                if (z && lifecycleCamera.d().isEmpty()) {
                    j(lifecycleCamera.c());
                }
            }
        }
    }

    public void m() {
        synchronized (this.f794a) {
            for (a aVar : this.b.keySet()) {
                LifecycleCamera lifecycleCamera = this.b.get(aVar);
                lifecycleCamera.h();
                j(lifecycleCamera.c());
            }
        }
    }

    public void n(LifecycleOwner lifecycleOwner) {
        synchronized (this.f794a) {
            LifecycleCameraRepositoryObserver e = e(lifecycleOwner);
            if (e == null) {
                return;
            }
            j(lifecycleOwner);
            for (a aVar : this.c.get(e)) {
                this.b.remove(aVar);
            }
            this.c.remove(e);
            e.a().getLifecycle().removeObserver(e);
        }
    }

    public final void o(LifecycleOwner lifecycleOwner) {
        synchronized (this.f794a) {
            for (a aVar : this.c.get(e(lifecycleOwner))) {
                LifecycleCamera lifecycleCamera = this.b.get(aVar);
                if (!((LifecycleCamera) Preconditions.checkNotNull(lifecycleCamera)).d().isEmpty()) {
                    lifecycleCamera.i();
                }
            }
        }
    }
}
