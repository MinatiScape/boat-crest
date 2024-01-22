package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class ChangeWatchFaceRequest extends BleBaseRequest {
    public int watchFacePosition = 1;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3479a = 1;

        public ChangeWatchFaceRequest build() {
            ChangeWatchFaceRequest changeWatchFaceRequest = new ChangeWatchFaceRequest();
            changeWatchFaceRequest.watchFacePosition = this.f3479a;
            return changeWatchFaceRequest;
        }

        public Builder setWatchFacePosition(int i) {
            this.f3479a = i;
            return this;
        }
    }

    public int getWatchFacePosition() {
        return this.watchFacePosition;
    }
}
