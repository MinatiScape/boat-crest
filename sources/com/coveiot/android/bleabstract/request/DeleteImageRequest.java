package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public final class DeleteImageRequest extends BleBaseRequest {
    public final int f;

    public DeleteImageRequest(int i) {
        this.f = i;
    }

    public final int getImageId() {
        return this.f;
    }
}
