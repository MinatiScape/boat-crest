package com.coveiot.sdk.ble.api.response;

import com.coveiot.sdk.ble.api.model.CameraState;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class CameraEventRes implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public CameraState f7553a;

    public CameraEventRes(CameraState cameraState) {
        this.f7553a = cameraState;
    }

    public CameraState getState() {
        return this.f7553a;
    }

    public String toString() {
        return "CameraEventRes{state=" + this.f7553a.name() + '}';
    }
}
