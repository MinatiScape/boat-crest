package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.experimental.UseExperimental;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.LensFacingCameraFilter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
/* loaded from: classes.dex */
public final class CameraSelector {
    public static final int LENS_FACING_BACK = 1;
    public static final int LENS_FACING_FRONT = 0;

    /* renamed from: a  reason: collision with root package name */
    public LinkedHashSet<CameraFilter> f615a;
    @NonNull
    public static final CameraSelector DEFAULT_FRONT_CAMERA = new Builder().requireLensFacing(0).build();
    @NonNull
    public static final CameraSelector DEFAULT_BACK_CAMERA = new Builder().requireLensFacing(1).build();

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface LensFacing {
    }

    public CameraSelector(LinkedHashSet<CameraFilter> linkedHashSet) {
        this.f615a = linkedHashSet;
    }

    @NonNull
    @UseExperimental(markerClass = ExperimentalCameraFilter.class)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public List<CameraInfo> filter(@NonNull List<CameraInfo> list) {
        ArrayList arrayList = new ArrayList(list);
        List<CameraInfo> arrayList2 = new ArrayList<>(list);
        Iterator<CameraFilter> it = this.f615a.iterator();
        while (it.hasNext()) {
            arrayList2 = it.next().filter(Collections.unmodifiableList(arrayList2));
            if (!arrayList2.isEmpty()) {
                if (arrayList.containsAll(arrayList2)) {
                    arrayList.retainAll(arrayList2);
                } else {
                    throw new IllegalArgumentException("The output isn't contained in the input.");
                }
            } else {
                throw new IllegalArgumentException("No available camera can be found.");
            }
        }
        return arrayList2;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public LinkedHashSet<CameraFilter> getCameraFilterSet() {
        return this.f615a;
    }

    @Nullable
    @UseExperimental(markerClass = ExperimentalCameraFilter.class)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Integer getLensFacing() {
        Iterator<CameraFilter> it = this.f615a.iterator();
        Integer num = null;
        while (it.hasNext()) {
            CameraFilter next = it.next();
            if (next instanceof LensFacingCameraFilter) {
                Integer valueOf = Integer.valueOf(((LensFacingCameraFilter) next).getLensFacing());
                if (num == null) {
                    num = valueOf;
                } else if (!num.equals(valueOf)) {
                    throw new IllegalStateException("Multiple conflicting lens facing requirements exist.");
                }
            }
        }
        return num;
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public CameraInternal select(@NonNull LinkedHashSet<CameraInternal> linkedHashSet) {
        return filter(linkedHashSet).iterator().next();
    }

    /* loaded from: classes.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final LinkedHashSet<CameraFilter> f616a;

        public Builder() {
            this.f616a = new LinkedHashSet<>();
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
        public static Builder fromSelector(@NonNull CameraSelector cameraSelector) {
            return new Builder(cameraSelector.getCameraFilterSet());
        }

        @NonNull
        @ExperimentalCameraFilter
        public Builder addCameraFilter(@NonNull CameraFilter cameraFilter) {
            this.f616a.add(cameraFilter);
            return this;
        }

        @NonNull
        public CameraSelector build() {
            return new CameraSelector(this.f616a);
        }

        @NonNull
        @UseExperimental(markerClass = ExperimentalCameraFilter.class)
        public Builder requireLensFacing(int i) {
            this.f616a.add(new LensFacingCameraFilter(i));
            return this;
        }

        public Builder(@NonNull LinkedHashSet<CameraFilter> linkedHashSet) {
            this.f616a = new LinkedHashSet<>(linkedHashSet);
        }
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public LinkedHashSet<CameraInternal> filter(@NonNull LinkedHashSet<CameraInternal> linkedHashSet) {
        ArrayList arrayList = new ArrayList();
        Iterator<CameraInternal> it = linkedHashSet.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getCameraInfo());
        }
        List<CameraInfo> filter = filter(arrayList);
        LinkedHashSet<CameraInternal> linkedHashSet2 = new LinkedHashSet<>();
        Iterator<CameraInternal> it2 = linkedHashSet.iterator();
        while (it2.hasNext()) {
            CameraInternal next = it2.next();
            if (filter.contains(next.getCameraInfo())) {
                linkedHashSet2.add(next);
            }
        }
        return linkedHashSet2;
    }
}
