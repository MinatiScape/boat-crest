package com.coveiot.android.bleabstract.request;

import android.graphics.Bitmap;
/* loaded from: classes2.dex */
public class CustomWatchFaceFileImageRequest extends BleBaseRequest {
    public Bitmap bitmap;
    public boolean f;
    public String watchFaceFilePath;
    public int watchFaceID;
    public int watchFacePosition = 1;
    public int watchFaceResourceID;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f3481a;
        public int b;
        public int c;
        public String d;
        public int e;
        public boolean f;

        public CustomWatchFaceFileImageRequest build() {
            CustomWatchFaceFileImageRequest customWatchFaceFileImageRequest = new CustomWatchFaceFileImageRequest();
            customWatchFaceFileImageRequest.bitmap = this.f3481a;
            customWatchFaceFileImageRequest.watchFacePosition = this.b;
            customWatchFaceFileImageRequest.watchFaceResourceID = this.c;
            customWatchFaceFileImageRequest.watchFaceFilePath = this.d;
            customWatchFaceFileImageRequest.watchFaceID = this.e;
            customWatchFaceFileImageRequest.f = this.f;
            return customWatchFaceFileImageRequest;
        }

        public Builder setBitmap(Bitmap bitmap) {
            this.f3481a = bitmap;
            return this;
        }

        public Builder setShouldUseSDK(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setWatchFaceFilePath(String str) {
            this.d = str;
            return this;
        }

        public Builder setWatchFaceID(int i) {
            this.e = i;
            return this;
        }

        public Builder setWatchFacePosition(int i) {
            this.b = i;
            return this;
        }

        public Builder setWatchFaceResourceID(int i) {
            this.c = i;
            return this;
        }
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getWatchFaceFilePath() {
        return this.watchFaceFilePath;
    }

    public int getWatchFacePosition() {
        return this.watchFacePosition;
    }

    public int getWatchFaceResourceID() {
        return this.watchFaceResourceID;
    }

    public boolean isShouldUseSdk() {
        return this.f;
    }
}
