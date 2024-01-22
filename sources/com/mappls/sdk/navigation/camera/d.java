package com.mappls.sdk.navigation.camera;

import com.mappls.sdk.maps.location.OnLocationCameraTransitionListener;
/* loaded from: classes11.dex */
public class d implements OnLocationCameraTransitionListener {

    /* renamed from: a  reason: collision with root package name */
    public final NavigationCamera f12890a;

    public d(NavigationCamera navigationCamera) {
        this.f12890a = navigationCamera;
    }

    @Override // com.mappls.sdk.maps.location.OnLocationCameraTransitionListener
    public void onLocationCameraTransitionCanceled(int i) {
        this.f12890a.updateTransitionListenersCancelled(i);
        this.f12890a.updateIsResetting(false);
    }

    @Override // com.mappls.sdk.maps.location.OnLocationCameraTransitionListener
    public void onLocationCameraTransitionFinished(int i) {
        this.f12890a.updateTransitionListenersFinished(i);
    }
}
