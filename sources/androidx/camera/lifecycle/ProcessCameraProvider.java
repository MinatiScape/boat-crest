package androidx.camera.lifecycle;

import android.content.Context;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.experimental.UseExperimental;
import androidx.arch.core.util.Function;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfoUnavailableException;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.CameraX;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.ExperimentalCameraFilter;
import androidx.camera.core.ExperimentalUseCaseGroup;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LifecycleOwner;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
/* loaded from: classes.dex */
public final class ProcessCameraProvider {
    public static final ProcessCameraProvider c = new ProcessCameraProvider();

    /* renamed from: a  reason: collision with root package name */
    public final LifecycleCameraRepository f795a = new LifecycleCameraRepository();
    public CameraX b;

    public static /* synthetic */ ProcessCameraProvider b(CameraX cameraX) {
        ProcessCameraProvider processCameraProvider = c;
        processCameraProvider.c(cameraX);
        return processCameraProvider;
    }

    @ExperimentalCameraProviderConfiguration
    public static void configureInstance(@NonNull CameraXConfig cameraXConfig) {
        CameraX.configureInstance(cameraXConfig);
    }

    @NonNull
    public static ListenableFuture<ProcessCameraProvider> getInstance(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        return Futures.transform(CameraX.getOrCreateInstance(context), new Function() { // from class: androidx.camera.lifecycle.b
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                ProcessCameraProvider b;
                b = ProcessCameraProvider.b((CameraX) obj);
                return b;
            }
        }, CameraXExecutors.directExecutor());
    }

    @NonNull
    @UseExperimental(markerClass = ExperimentalUseCaseGroup.class)
    @MainThread
    public Camera bindToLifecycle(@NonNull LifecycleOwner lifecycleOwner, @NonNull CameraSelector cameraSelector, @NonNull UseCase... useCaseArr) {
        return bindToLifecycle(lifecycleOwner, cameraSelector, null, useCaseArr);
    }

    public final void c(CameraX cameraX) {
        this.b = cameraX;
    }

    public boolean hasCamera(@NonNull CameraSelector cameraSelector) throws CameraInfoUnavailableException {
        try {
            cameraSelector.select(this.b.getCameraRepository().getCameras());
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public boolean isBound(@NonNull UseCase useCase) {
        for (LifecycleCamera lifecycleCamera : this.f795a.f()) {
            if (lifecycleCamera.e(useCase)) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.TESTS})
    public ListenableFuture<Void> shutdown() {
        this.f795a.b();
        return CameraX.shutdown();
    }

    @MainThread
    public void unbind(@NonNull UseCase... useCaseArr) {
        Threads.checkMainThread();
        this.f795a.l(Arrays.asList(useCaseArr));
    }

    @MainThread
    public void unbindAll() {
        Threads.checkMainThread();
        this.f795a.m();
    }

    @NonNull
    @ExperimentalUseCaseGroupLifecycle
    @UseExperimental(markerClass = ExperimentalUseCaseGroup.class)
    @MainThread
    public Camera bindToLifecycle(@NonNull LifecycleOwner lifecycleOwner, @NonNull CameraSelector cameraSelector, @NonNull UseCaseGroup useCaseGroup) {
        return bindToLifecycle(lifecycleOwner, cameraSelector, useCaseGroup.getViewPort(), (UseCase[]) useCaseGroup.getUseCases().toArray(new UseCase[0]));
    }

    @NonNull
    @ExperimentalUseCaseGroup
    @UseExperimental(markerClass = ExperimentalCameraFilter.class)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Camera bindToLifecycle(@NonNull LifecycleOwner lifecycleOwner, @NonNull CameraSelector cameraSelector, @Nullable ViewPort viewPort, @NonNull UseCase... useCaseArr) {
        Threads.checkMainThread();
        CameraSelector.Builder fromSelector = CameraSelector.Builder.fromSelector(cameraSelector);
        for (UseCase useCase : useCaseArr) {
            CameraSelector cameraSelector2 = useCase.getCurrentConfig().getCameraSelector(null);
            if (cameraSelector2 != null) {
                Iterator<CameraFilter> it = cameraSelector2.getCameraFilterSet().iterator();
                while (it.hasNext()) {
                    fromSelector.addCameraFilter(it.next());
                }
            }
        }
        LinkedHashSet<CameraInternal> filter = fromSelector.build().filter(this.b.getCameraRepository().getCameras());
        LifecycleCamera d = this.f795a.d(lifecycleOwner, CameraUseCaseAdapter.generateCameraId(filter));
        Collection<LifecycleCamera> f = this.f795a.f();
        for (UseCase useCase2 : useCaseArr) {
            for (LifecycleCamera lifecycleCamera : f) {
                if (lifecycleCamera.e(useCase2) && lifecycleCamera != d) {
                    throw new IllegalStateException(String.format("Use case %s already bound to a different lifecycle.", useCase2));
                }
            }
        }
        if (d == null) {
            d = this.f795a.c(lifecycleOwner, new CameraUseCaseAdapter(filter, this.b.getCameraDeviceSurfaceManager(), this.b.getDefaultConfigFactory()));
        }
        if (useCaseArr.length == 0) {
            return d;
        }
        this.f795a.a(d, viewPort, Arrays.asList(useCaseArr));
        return d;
    }
}
