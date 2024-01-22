package com.mappls.sdk.navigation.camera;

import com.mappls.sdk.maps.MapplsMap;
/* loaded from: classes11.dex */
public class e implements MapplsMap.CancelableCallback {
    public final NavigationCamera h;

    public e(NavigationCamera navigationCamera) {
        this.h = navigationCamera;
    }

    @Override // com.mappls.sdk.maps.MapplsMap.CancelableCallback
    public void onCancel() {
        this.h.updateIsResetting(false);
    }

    @Override // com.mappls.sdk.maps.MapplsMap.CancelableCallback
    public void onFinish() {
        this.h.updateIsResetting(false);
    }
}
