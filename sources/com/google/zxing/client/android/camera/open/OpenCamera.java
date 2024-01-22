package com.google.zxing.client.android.camera.open;

import android.hardware.Camera;
/* loaded from: classes11.dex */
public final class OpenCamera {

    /* renamed from: a  reason: collision with root package name */
    public final int f11782a;
    public final Camera b;
    public final CameraFacing c;
    public final int d;

    public OpenCamera(int i, Camera camera, CameraFacing cameraFacing, int i2) {
        this.f11782a = i;
        this.b = camera;
        this.c = cameraFacing;
        this.d = i2;
    }

    public Camera getCamera() {
        return this.b;
    }

    public CameraFacing getFacing() {
        return this.c;
    }

    public int getOrientation() {
        return this.d;
    }

    public String toString() {
        return "Camera #" + this.f11782a + " : " + this.c + ',' + this.d;
    }
}
