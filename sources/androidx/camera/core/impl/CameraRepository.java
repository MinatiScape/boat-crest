package androidx.camera.core.impl;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.camera.core.CameraUnavailableException;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public final class CameraRepository {

    /* renamed from: a  reason: collision with root package name */
    public final Object f694a = new Object();
    @GuardedBy("mCamerasLock")
    public final Map<String, CameraInternal> b = new LinkedHashMap();
    @GuardedBy("mCamerasLock")
    public final Set<CameraInternal> c = new HashSet();
    @GuardedBy("mCamerasLock")
    public ListenableFuture<Void> d;
    @GuardedBy("mCamerasLock")
    public CallbackToFutureAdapter.Completer<Void> e;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Object c(CallbackToFutureAdapter.Completer completer) throws Exception {
        synchronized (this.f694a) {
            this.e = completer;
        }
        return "CameraRepository-deinit";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(CameraInternal cameraInternal) {
        synchronized (this.f694a) {
            this.c.remove(cameraInternal);
            if (this.c.isEmpty()) {
                Preconditions.checkNotNull(this.e);
                this.e.set(null);
                this.e = null;
                this.d = null;
            }
        }
    }

    @NonNull
    public ListenableFuture<Void> deinit() {
        synchronized (this.f694a) {
            if (this.b.isEmpty()) {
                ListenableFuture<Void> listenableFuture = this.d;
                if (listenableFuture == null) {
                    listenableFuture = Futures.immediateFuture(null);
                }
                return listenableFuture;
            }
            ListenableFuture<Void> listenableFuture2 = this.d;
            if (listenableFuture2 == null) {
                listenableFuture2 = CallbackToFutureAdapter.getFuture(new CallbackToFutureAdapter.Resolver() { // from class: androidx.camera.core.impl.h
                    @Override // androidx.concurrent.futures.CallbackToFutureAdapter.Resolver
                    public final Object attachCompleter(CallbackToFutureAdapter.Completer completer) {
                        Object c;
                        c = CameraRepository.this.c(completer);
                        return c;
                    }
                });
                this.d = listenableFuture2;
            }
            this.c.addAll(this.b.values());
            for (final CameraInternal cameraInternal : this.b.values()) {
                cameraInternal.release().addListener(new Runnable() { // from class: androidx.camera.core.impl.i
                    @Override // java.lang.Runnable
                    public final void run() {
                        CameraRepository.this.d(cameraInternal);
                    }
                }, CameraXExecutors.directExecutor());
            }
            this.b.clear();
            return listenableFuture2;
        }
    }

    @NonNull
    public CameraInternal getCamera(@NonNull String str) {
        CameraInternal cameraInternal;
        synchronized (this.f694a) {
            cameraInternal = this.b.get(str);
            if (cameraInternal == null) {
                throw new IllegalArgumentException("Invalid camera: " + str);
            }
        }
        return cameraInternal;
    }

    @NonNull
    public LinkedHashSet<CameraInternal> getCameras() {
        LinkedHashSet<CameraInternal> linkedHashSet;
        synchronized (this.f694a) {
            linkedHashSet = new LinkedHashSet<>(this.b.values());
        }
        return linkedHashSet;
    }

    public void init(@NonNull CameraFactory cameraFactory) throws InitializationException {
        synchronized (this.f694a) {
            try {
                try {
                    for (String str : cameraFactory.getAvailableCameraIds()) {
                        Logger.d("CameraRepository", "Added camera: " + str);
                        this.b.put(str, cameraFactory.getCamera(str));
                    }
                } catch (CameraUnavailableException e) {
                    throw new InitializationException(e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
