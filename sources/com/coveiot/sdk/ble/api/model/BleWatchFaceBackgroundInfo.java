package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BleWatchFaceBackgroundInfo {
    private int imageId;
    private int watchFaceId;

    public BleWatchFaceBackgroundInfo(int i, int i2) {
        this.watchFaceId = i;
        this.imageId = i2;
    }

    public int getImageId() {
        return this.imageId;
    }

    public int getWatchFaceId() {
        return this.watchFaceId;
    }
}
