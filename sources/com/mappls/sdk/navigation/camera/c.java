package com.mappls.sdk.navigation.camera;

import com.mappls.sdk.maps.location.OnCameraTrackingChangedListener;
/* loaded from: classes11.dex */
public class c implements OnCameraTrackingChangedListener {

    /* renamed from: a  reason: collision with root package name */
    public final NavigationCamera f12889a;

    public c(NavigationCamera navigationCamera) {
        this.f12889a = navigationCamera;
    }

    @Override // com.mappls.sdk.maps.location.OnCameraTrackingChangedListener
    public void onCameraTrackingChanged(int i) {
        Integer findTrackingModeFor = this.f12889a.findTrackingModeFor(i);
        if (findTrackingModeFor != null) {
            this.f12889a.updateCameraTrackingMode(findTrackingModeFor.intValue());
        }
    }

    @Override // com.mappls.sdk.maps.location.OnCameraTrackingChangedListener
    public void onCameraTrackingDismissed() {
        this.f12889a.updateCameraTrackingMode(2);
    }
}
