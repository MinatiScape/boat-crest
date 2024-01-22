package androidx.camera.core.internal;

import android.graphics.Rect;
import android.util.Size;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ExperimentalCameraFilter;
import androidx.camera.core.ExperimentalUseCaseGroup;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.ViewPort;
import androidx.camera.core.impl.CameraConfig;
import androidx.camera.core.impl.CameraConfigs;
import androidx.camera.core.impl.CameraControlInternal;
import androidx.camera.core.impl.CameraDeviceSurfaceManager;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public final class CameraUseCaseAdapter implements Camera {
    @NonNull
    public CameraInternal h;
    public final LinkedHashSet<CameraInternal> i;
    public final CameraDeviceSurfaceManager j;
    public final UseCaseConfigFactory k;
    public final CameraId l;
    @Nullable
    @GuardedBy("mLock")
    public ViewPort n;
    @GuardedBy("mLock")
    public final List<UseCase> m = new ArrayList();
    @NonNull
    @GuardedBy("mLock")
    public CameraConfig o = CameraConfigs.emptyConfig();
    public final Object p = new Object();
    @GuardedBy("mLock")
    public boolean q = true;
    @GuardedBy("mLock")
    public Config r = null;

    /* loaded from: classes.dex */
    public static final class CameraException extends Exception {
        public CameraException() {
        }

        public CameraException(@NonNull String str) {
            super(str);
        }

        public CameraException(@NonNull Throwable th) {
            super(th);
        }
    }

    /* loaded from: classes.dex */
    public static final class CameraId {

        /* renamed from: a  reason: collision with root package name */
        public final List<String> f753a = new ArrayList();

        public CameraId(LinkedHashSet<CameraInternal> linkedHashSet) {
            Iterator<CameraInternal> it = linkedHashSet.iterator();
            while (it.hasNext()) {
                this.f753a.add(it.next().getCameraInfoInternal().getCameraId());
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof CameraId) {
                return this.f753a.equals(((CameraId) obj).f753a);
            }
            return false;
        }

        public int hashCode() {
            return this.f753a.hashCode() * 53;
        }
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public UseCaseConfig<?> f754a;
        public UseCaseConfig<?> b;

        public a(UseCaseConfig<?> useCaseConfig, UseCaseConfig<?> useCaseConfig2) {
            this.f754a = useCaseConfig;
            this.b = useCaseConfig2;
        }
    }

    public CameraUseCaseAdapter(@NonNull LinkedHashSet<CameraInternal> linkedHashSet, @NonNull CameraDeviceSurfaceManager cameraDeviceSurfaceManager, @NonNull UseCaseConfigFactory useCaseConfigFactory) {
        this.h = linkedHashSet.iterator().next();
        LinkedHashSet<CameraInternal> linkedHashSet2 = new LinkedHashSet<>(linkedHashSet);
        this.i = linkedHashSet2;
        this.l = new CameraId(linkedHashSet2);
        this.j = cameraDeviceSurfaceManager;
        this.k = useCaseConfigFactory;
    }

    @NonNull
    public static CameraId generateCameraId(@NonNull LinkedHashSet<CameraInternal> linkedHashSet) {
        return new CameraId(linkedHashSet);
    }

    public final void a() {
        synchronized (this.p) {
            CameraControlInternal cameraControlInternal = this.h.getCameraControlInternal();
            this.r = cameraControlInternal.getInteropConfig();
            cameraControlInternal.clearInteropConfig();
        }
    }

    @UseExperimental(markerClass = ExperimentalUseCaseGroup.class)
    public void addUseCases(@NonNull Collection<UseCase> collection) throws CameraException {
        synchronized (this.p) {
            ArrayList<UseCase> arrayList = new ArrayList();
            for (UseCase useCase : collection) {
                if (this.m.contains(useCase)) {
                    Logger.d("CameraUseCaseAdapter", "Attempting to attach already attached UseCase");
                } else {
                    arrayList.add(useCase);
                }
            }
            Map<UseCase, a> c = c(arrayList, this.o.getUseCaseConfigFactory(), this.k);
            try {
                Map<UseCase, Size> b = b(this.h.getCameraInfoInternal(), arrayList, this.m, c);
                e(b, collection);
                for (UseCase useCase2 : arrayList) {
                    a aVar = c.get(useCase2);
                    useCase2.onAttach(this.h, aVar.f754a, aVar.b);
                    useCase2.updateSuggestedResolution((Size) Preconditions.checkNotNull(b.get(useCase2)));
                }
                this.m.addAll(arrayList);
                if (this.q) {
                    this.h.attachUseCases(arrayList);
                }
                for (UseCase useCase3 : arrayList) {
                    useCase3.notifyState();
                }
            } catch (IllegalArgumentException e) {
                throw new CameraException(e.getMessage());
            }
        }
    }

    public void attachUseCases() {
        synchronized (this.p) {
            if (!this.q) {
                this.h.attachUseCases(this.m);
                d();
                for (UseCase useCase : this.m) {
                    useCase.notifyState();
                }
                this.q = true;
            }
        }
    }

    public final Map<UseCase, Size> b(@NonNull CameraInfoInternal cameraInfoInternal, @NonNull List<UseCase> list, @NonNull List<UseCase> list2, @NonNull Map<UseCase, a> map) {
        ArrayList arrayList = new ArrayList();
        String cameraId = cameraInfoInternal.getCameraId();
        HashMap hashMap = new HashMap();
        for (UseCase useCase : list2) {
            arrayList.add(this.j.transformSurfaceConfig(cameraId, useCase.getImageFormat(), useCase.getAttachedSurfaceResolution()));
            hashMap.put(useCase, useCase.getAttachedSurfaceResolution());
        }
        if (!list.isEmpty()) {
            HashMap hashMap2 = new HashMap();
            for (UseCase useCase2 : list) {
                a aVar = map.get(useCase2);
                hashMap2.put(useCase2.mergeConfigs(cameraInfoInternal, aVar.f754a, aVar.b), useCase2);
            }
            Map<UseCaseConfig<?>, Size> suggestedResolutions = this.j.getSuggestedResolutions(cameraId, arrayList, new ArrayList(hashMap2.keySet()));
            for (Map.Entry entry : hashMap2.entrySet()) {
                hashMap.put((UseCase) entry.getValue(), suggestedResolutions.get(entry.getKey()));
            }
        }
        return hashMap;
    }

    public final Map<UseCase, a> c(List<UseCase> list, UseCaseConfigFactory useCaseConfigFactory, UseCaseConfigFactory useCaseConfigFactory2) {
        HashMap hashMap = new HashMap();
        for (UseCase useCase : list) {
            hashMap.put(useCase, new a(useCase.getDefaultConfig(false, useCaseConfigFactory), useCase.getDefaultConfig(true, useCaseConfigFactory2)));
        }
        return hashMap;
    }

    public void checkAttachUseCases(@NonNull List<UseCase> list) throws CameraException {
        synchronized (this.p) {
            try {
                try {
                    b(this.h.getCameraInfoInternal(), list, Collections.emptyList(), c(list, this.o.getUseCaseConfigFactory(), this.k));
                } catch (IllegalArgumentException e) {
                    throw new CameraException(e.getMessage());
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void d() {
        synchronized (this.p) {
            if (this.r != null) {
                this.h.getCameraControlInternal().addInteropConfig(this.r);
            }
        }
    }

    public void detachUseCases() {
        synchronized (this.p) {
            if (this.q) {
                a();
                this.h.detachUseCases(new ArrayList(this.m));
                this.q = false;
            }
        }
    }

    @UseExperimental(markerClass = ExperimentalUseCaseGroup.class)
    public final void e(@NonNull Map<UseCase, Size> map, @NonNull Collection<UseCase> collection) {
        synchronized (this.p) {
            if (this.n != null) {
                Map<UseCase, Rect> calculateViewPortRects = ViewPorts.calculateViewPortRects(this.h.getCameraControlInternal().getSensorRect(), this.h.getCameraInfoInternal().getLensFacing().intValue() == 0, this.n.getAspectRatio(), this.h.getCameraInfoInternal().getSensorRotationDegrees(this.n.getRotation()), this.n.getScaleType(), this.n.getLayoutDirection(), map);
                for (UseCase useCase : collection) {
                    useCase.setViewPortCropRect((Rect) Preconditions.checkNotNull(calculateViewPortRects.get(useCase)));
                }
            }
        }
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public CameraControl getCameraControl() {
        return this.h.getCameraControlInternal();
    }

    @NonNull
    public CameraId getCameraId() {
        return this.l;
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public CameraInfo getCameraInfo() {
        return this.h.getCameraInfoInternal();
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public LinkedHashSet<CameraInternal> getCameraInternals() {
        return this.i;
    }

    @Override // androidx.camera.core.Camera
    @NonNull
    public CameraConfig getExtendedConfig() {
        CameraConfig cameraConfig;
        synchronized (this.p) {
            cameraConfig = this.o;
        }
        return cameraConfig;
    }

    @NonNull
    public List<UseCase> getUseCases() {
        ArrayList arrayList;
        synchronized (this.p) {
            arrayList = new ArrayList(this.m);
        }
        return arrayList;
    }

    public boolean isEquivalent(@NonNull CameraUseCaseAdapter cameraUseCaseAdapter) {
        return this.l.equals(cameraUseCaseAdapter.getCameraId());
    }

    public void removeUseCases(@NonNull Collection<UseCase> collection) {
        synchronized (this.p) {
            this.h.detachUseCases(collection);
            for (UseCase useCase : collection) {
                if (this.m.contains(useCase)) {
                    useCase.onDetach(this.h);
                } else {
                    Logger.e("CameraUseCaseAdapter", "Attempting to detach non-attached UseCase: " + useCase);
                }
            }
            this.m.removeAll(collection);
        }
    }

    @Override // androidx.camera.core.Camera
    @UseExperimental(markerClass = ExperimentalCameraFilter.class)
    public void setExtendedConfig(@Nullable CameraConfig cameraConfig) throws CameraException {
        synchronized (this.p) {
            if (cameraConfig == null) {
                try {
                    cameraConfig = CameraConfigs.emptyConfig();
                } catch (Throwable th) {
                    throw th;
                }
            }
            CameraInternal select = new CameraSelector.Builder().addCameraFilter(cameraConfig.getCameraFilter()).build().select(this.i);
            Map<UseCase, a> c = c(this.m, cameraConfig.getUseCaseConfigFactory(), this.k);
            try {
                Map<UseCase, Size> b = b(select.getCameraInfoInternal(), this.m, Collections.emptyList(), c);
                e(b, this.m);
                if (this.q) {
                    this.h.detachUseCases(this.m);
                }
                for (UseCase useCase : this.m) {
                    useCase.onDetach(this.h);
                }
                for (UseCase useCase2 : this.m) {
                    a aVar = c.get(useCase2);
                    useCase2.onAttach(select, aVar.f754a, aVar.b);
                    useCase2.updateSuggestedResolution((Size) Preconditions.checkNotNull(b.get(useCase2)));
                }
                if (this.q) {
                    select.attachUseCases(this.m);
                }
                for (UseCase useCase3 : this.m) {
                    useCase3.notifyState();
                }
                this.h = select;
                this.o = cameraConfig;
            } catch (IllegalArgumentException e) {
                throw new CameraException(e.getMessage());
            }
        }
    }

    public void setViewPort(@Nullable ViewPort viewPort) {
        synchronized (this.p) {
            this.n = viewPort;
        }
    }
}
