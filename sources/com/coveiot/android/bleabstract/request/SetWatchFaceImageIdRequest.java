package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetWatchFaceImageIdRequest extends BleBaseRequest {
    public int f;
    public int g;

    public SetWatchFaceImageIdRequest(int i, int i2) {
        this.f = i;
        this.g = i2;
    }

    public int getImageId() {
        return this.f;
    }

    public int getWatchFaceId() {
        return this.g;
    }
}
