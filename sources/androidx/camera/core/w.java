package androidx.camera.core;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.CaptureBundle;
import androidx.camera.core.impl.CaptureStage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public final class w {

    /* loaded from: classes.dex */
    public static final class a implements CaptureBundle {

        /* renamed from: a  reason: collision with root package name */
        public final List<CaptureStage> f783a;

        public a(List<CaptureStage> list) {
            if (list != null && !list.isEmpty()) {
                this.f783a = Collections.unmodifiableList(new ArrayList(list));
                return;
            }
            throw new IllegalArgumentException("Cannot set an empty CaptureStage list.");
        }

        @Override // androidx.camera.core.impl.CaptureBundle
        public List<CaptureStage> getCaptureStages() {
            return this.f783a;
        }
    }

    @NonNull
    public static CaptureBundle a(@NonNull List<CaptureStage> list) {
        return new a(list);
    }

    @NonNull
    public static CaptureBundle b(@NonNull CaptureStage... captureStageArr) {
        return new a(Arrays.asList(captureStageArr));
    }

    @NonNull
    public static CaptureBundle c() {
        return b(new CaptureStage.DefaultCaptureStage());
    }
}
