package com.coveiot.android.bleabstract.request;

import java.io.File;
/* loaded from: classes2.dex */
public class SendImageRequest extends BleBaseRequest {
    public int f;
    public File g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;

    public SendImageRequest(int i, File file, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f = i;
        this.g = file;
        this.h = i2;
        this.i = i3;
        this.j = i4;
        this.k = i5;
        this.l = i6;
        this.m = i7;
    }

    public int getCompression() {
        return this.h;
    }

    public int getHeight() {
        return this.l;
    }

    public File getImageFile() {
        return this.g;
    }

    public int getImageId() {
        return this.f;
    }

    public int getTransparentChannel() {
        return this.i;
    }

    public int getWidth() {
        return this.m;
    }

    public int getxCoordinate() {
        return this.j;
    }

    public int getyCoordinate() {
        return this.k;
    }
}
