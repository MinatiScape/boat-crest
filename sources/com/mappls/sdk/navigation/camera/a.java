package com.mappls.sdk.navigation.camera;

import androidx.annotation.NonNull;
import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.camera.CameraUpdate;
/* loaded from: classes11.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public final MapplsMap f12888a;

    public a(MapplsMap mapplsMap) {
        this.f12888a = mapplsMap;
    }

    public final boolean a() {
        return this.f12888a.getLocationComponent().getCameraMode() != 8;
    }

    public void b(@NonNull NavigationCameraUpdate navigationCameraUpdate, int i, MapplsMap.CancelableCallback cancelableCallback) {
        CameraUpdateMode b = navigationCameraUpdate.b();
        CameraUpdate a2 = navigationCameraUpdate.a();
        if (b == CameraUpdateMode.OVERRIDE) {
            this.f12888a.getLocationComponent().setCameraMode(8);
            this.f12888a.animateCamera(a2, i, cancelableCallback);
        } else if (a()) {
        } else {
            this.f12888a.animateCamera(a2, i, cancelableCallback);
        }
    }
}
