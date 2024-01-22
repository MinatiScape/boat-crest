package com.coveiot.covepreferences.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class CameraSettingsData {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static CameraSettingsData b;

    /* renamed from: a  reason: collision with root package name */
    public boolean f7018a;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final CameraSettingsData getInstance() {
            if (CameraSettingsData.b == null) {
                CameraSettingsData.b = new CameraSettingsData(null);
            }
            return CameraSettingsData.b;
        }
    }

    public CameraSettingsData() {
    }

    public /* synthetic */ CameraSettingsData(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final boolean isCameraEnabled() {
        return this.f7018a;
    }

    @NotNull
    public final CameraSettingsData setCameraEnabled(boolean z) {
        this.f7018a = z;
        return this;
    }
}
