package com.mappls.sdk.navigation.camera;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.camera.CameraUpdate;
/* loaded from: classes11.dex */
public class NavigationCameraUpdate {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final CameraUpdate f12887a;
    public CameraUpdateMode b = CameraUpdateMode.DEFAULT;

    public NavigationCameraUpdate(@NonNull CameraUpdate cameraUpdate) {
        this.f12887a = cameraUpdate;
    }

    @NonNull
    public CameraUpdate a() {
        return this.f12887a;
    }

    @NonNull
    public CameraUpdateMode b() {
        return this.b;
    }

    public void setMode(CameraUpdateMode cameraUpdateMode) {
        this.b = cameraUpdateMode;
    }
}
