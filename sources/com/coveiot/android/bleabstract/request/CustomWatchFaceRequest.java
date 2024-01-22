package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class CustomWatchFaceRequest extends BleBaseRequest {
    public int watchFacePosition = 1;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3483a;

        public CustomWatchFaceRequest build() {
            CustomWatchFaceRequest customWatchFaceRequest = new CustomWatchFaceRequest();
            customWatchFaceRequest.watchFacePosition = this.f3483a;
            return customWatchFaceRequest;
        }

        public Builder setWatchFacePosition(int i) {
            this.f3483a = i;
            return this;
        }
    }

    public int getWatchFacePosition() {
        return this.watchFacePosition;
    }
}
