package com.coveiot.android.bleabstract.request;

import android.graphics.Bitmap;
import java.io.File;
/* loaded from: classes2.dex */
public class CustomWatchFaceLayoutChangeRequest extends BleBaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public String j = null;
    public int k;
    public int l;
    public int m;
    public int n;
    public Bitmap o;
    public File p;
    public String q;
    public int r;
    public Boolean s;
    public int t;
    public Boolean u;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3482a;
        public int b;
        public int c;
        public int d;
        public String e = null;
        public int f;
        public int g;
        public int h;
        public int i;
        public Bitmap j;
        public File k;
        public String l;
        public int m;
        public Boolean n;
        public int o;
        public Boolean p;

        public CustomWatchFaceLayoutChangeRequest build() {
            CustomWatchFaceLayoutChangeRequest customWatchFaceLayoutChangeRequest = new CustomWatchFaceLayoutChangeRequest();
            customWatchFaceLayoutChangeRequest.f = this.f3482a;
            customWatchFaceLayoutChangeRequest.j = this.e;
            customWatchFaceLayoutChangeRequest.g = this.b;
            customWatchFaceLayoutChangeRequest.h = this.c;
            customWatchFaceLayoutChangeRequest.i = this.d;
            customWatchFaceLayoutChangeRequest.k = this.f;
            customWatchFaceLayoutChangeRequest.l = this.g;
            customWatchFaceLayoutChangeRequest.m = this.h;
            customWatchFaceLayoutChangeRequest.n = this.i;
            customWatchFaceLayoutChangeRequest.o = this.j;
            customWatchFaceLayoutChangeRequest.p = this.k;
            customWatchFaceLayoutChangeRequest.q = this.l;
            customWatchFaceLayoutChangeRequest.r = this.m;
            customWatchFaceLayoutChangeRequest.s = this.n;
            customWatchFaceLayoutChangeRequest.t = this.o;
            customWatchFaceLayoutChangeRequest.u = this.p;
            return customWatchFaceLayoutChangeRequest;
        }

        public Builder setBackgroundPictureMd5(String str) {
            this.e = str;
            return this;
        }

        public Builder setBasicBinFile(File file) {
            this.k = file;
            return this;
        }

        public Builder setBottomContent(int i) {
            this.c = i;
            return this;
        }

        public Builder setCircle(Boolean bool) {
            this.n = bool;
            return this;
        }

        public Builder setCornerRadius(int i) {
            this.o = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.f = i;
            return this;
        }

        public Builder setPosition(int i) {
            this.f3482a = i;
            return this;
        }

        public Builder setRGBAFormatWatchScreen(Boolean bool) {
            this.p = bool;
            return this;
        }

        public Builder setStyleBitMap(Bitmap bitmap) {
            this.j = bitmap;
            return this;
        }

        public Builder setStylePosition(int i) {
            this.m = i;
            return this;
        }

        public Builder setTextColor(int i) {
            this.d = i;
            return this;
        }

        public Builder setThumbHeight(int i) {
            this.h = i;
            return this;
        }

        public Builder setThumbWidth(int i) {
            this.i = i;
            return this;
        }

        public Builder setTimePosition(String str) {
            this.l = str;
            return this;
        }

        public Builder setTopContent(int i) {
            this.b = i;
            return this;
        }

        public Builder setWidth(int i) {
            this.g = i;
            return this;
        }
    }

    public String getBackgroundPictureMd5() {
        return this.j;
    }

    public File getBasicBinFile() {
        return this.p;
    }

    public int getBottomContent() {
        return this.h;
    }

    public Boolean getCircle() {
        return this.s;
    }

    public int getCornerRadius() {
        return this.t;
    }

    public int getHeight() {
        return this.k;
    }

    public int getPosition() {
        return this.f;
    }

    public Boolean getRGBAFormatWatchScreen() {
        return this.u;
    }

    public Bitmap getStyleBitMap() {
        return this.o;
    }

    public int getStylePosition() {
        return this.r;
    }

    public int getTextColor() {
        return this.i;
    }

    public int getThumbHeight() {
        return this.m;
    }

    public int getThumbWidth() {
        return this.n;
    }

    public String getTimePosition() {
        return this.q;
    }

    public int getTopContent() {
        return this.g;
    }

    public int getWidth() {
        return this.l;
    }
}
