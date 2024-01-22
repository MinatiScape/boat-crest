package com.mappls.sdk.navigation.camera;

import com.mappls.sdk.maps.MapplsMap;
import com.mappls.sdk.maps.camera.CameraUpdate;
/* loaded from: classes11.dex */
public class b implements MapplsMap.CancelableCallback {
    public CameraUpdate h;
    public MapplsMap i;

    public b(CameraUpdate cameraUpdate, MapplsMap mapplsMap) {
        this.h = cameraUpdate;
        this.i = mapplsMap;
    }

    @Override // com.mappls.sdk.maps.MapplsMap.CancelableCallback
    public void onCancel() {
    }

    @Override // com.mappls.sdk.maps.MapplsMap.CancelableCallback
    public void onFinish() {
        this.i.animateCamera(this.h, 750);
    }
}
