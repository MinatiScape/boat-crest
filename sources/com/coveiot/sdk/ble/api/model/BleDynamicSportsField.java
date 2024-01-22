package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
/* loaded from: classes9.dex */
public abstract class BleDynamicSportsField {
    public int color_rgb_565;
    public DevicePlatformEnum devicePlatformEnum = DevicePlatformEnum.Nordic;
    public int length;
    public int width;
    public int xPosition;
    public int yPosition;

    public BleDynamicSportsField(int i, int i2, int i3, int i4, int i5) {
        this.color_rgb_565 = i;
        this.xPosition = i2;
        this.yPosition = i3;
        this.length = i4;
        this.width = i5;
    }

    public abstract byte[] getDataBytes();

    public DevicePlatformEnum getDevicePlatformEnum() {
        return this.devicePlatformEnum;
    }

    public void setDevicePlatformEnum(DevicePlatformEnum devicePlatformEnum) {
        this.devicePlatformEnum = devicePlatformEnum;
    }
}
