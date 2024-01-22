package androidx.camera.core.impl;

import androidx.camera.core.impl.CaptureConfig;
/* loaded from: classes.dex */
public interface CaptureStage {

    /* loaded from: classes.dex */
    public static final class DefaultCaptureStage implements CaptureStage {

        /* renamed from: a  reason: collision with root package name */
        public final CaptureConfig f699a = new CaptureConfig.Builder().build();

        @Override // androidx.camera.core.impl.CaptureStage
        public CaptureConfig getCaptureConfig() {
            return this.f699a;
        }

        @Override // androidx.camera.core.impl.CaptureStage
        public int getId() {
            return 0;
        }
    }

    CaptureConfig getCaptureConfig();

    int getId();
}
