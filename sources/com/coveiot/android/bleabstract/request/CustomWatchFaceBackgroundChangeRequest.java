package com.coveiot.android.bleabstract.request;

import android.graphics.Bitmap;
import com.szabh.smable3.watchface.Element;
import java.io.File;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public class CustomWatchFaceBackgroundChangeRequest extends BleBaseRequest {
    public Bitmap f;
    public File g;
    public int h;
    public int i;
    public int k;
    public String l;
    public Bitmap n;
    public int p;
    public int q;
    public Bitmap t;
    public Bitmap u;
    public Bitmap v;
    public Bitmap w;
    public int j = 1;
    public String m = null;
    public int o = 30;
    public CustomWatchFaceLayoutChangeRequest r = null;
    public ArrayList<Element> s = null;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f3480a;
        public int b;
        public int c;
        public String d;
        public Bitmap f;
        public File h;
        public int i;
        public int j;
        public int k;
        public int l;
        public Bitmap o;
        public Bitmap p;
        public Bitmap q;
        public Bitmap r;
        public String e = null;
        public int g = 30;
        public CustomWatchFaceLayoutChangeRequest m = null;
        public ArrayList<Element> n = null;

        public CustomWatchFaceBackgroundChangeRequest build() {
            CustomWatchFaceBackgroundChangeRequest customWatchFaceBackgroundChangeRequest = new CustomWatchFaceBackgroundChangeRequest();
            customWatchFaceBackgroundChangeRequest.f = this.f3480a;
            customWatchFaceBackgroundChangeRequest.j = this.b;
            customWatchFaceBackgroundChangeRequest.k = this.c;
            customWatchFaceBackgroundChangeRequest.l = this.d;
            customWatchFaceBackgroundChangeRequest.m = this.e;
            customWatchFaceBackgroundChangeRequest.n = this.f;
            customWatchFaceBackgroundChangeRequest.o = this.g;
            customWatchFaceBackgroundChangeRequest.r = this.m;
            customWatchFaceBackgroundChangeRequest.g = this.h;
            customWatchFaceBackgroundChangeRequest.h = this.i;
            customWatchFaceBackgroundChangeRequest.i = this.j;
            customWatchFaceBackgroundChangeRequest.p = this.k;
            customWatchFaceBackgroundChangeRequest.q = this.l;
            customWatchFaceBackgroundChangeRequest.s = this.n;
            customWatchFaceBackgroundChangeRequest.t = this.o;
            customWatchFaceBackgroundChangeRequest.u = this.p;
            customWatchFaceBackgroundChangeRequest.v = this.q;
            customWatchFaceBackgroundChangeRequest.w = this.r;
            return customWatchFaceBackgroundChangeRequest;
        }

        public Builder setBitmap(Bitmap bitmap) {
            this.f3480a = bitmap;
            return this;
        }

        public Builder setCompressionType(String str) {
            this.e = str;
            return this;
        }

        public Builder setCustomWatchFaceLayoutChangeRequest(CustomWatchFaceLayoutChangeRequest customWatchFaceLayoutChangeRequest) {
            this.m = customWatchFaceLayoutChangeRequest;
            return this;
        }

        public Builder setElementArray(ArrayList<Element> arrayList) {
            this.n = arrayList;
            return this;
        }

        public Builder setHeight(int i) {
            this.k = i;
            return this;
        }

        public Builder setHourBitmap(Bitmap bitmap) {
            this.p = bitmap;
            return this;
        }

        public Builder setImageFile(File file) {
            this.h = file;
            return this;
        }

        public Builder setImageId(int i) {
            this.i = i;
            return this;
        }

        public Builder setMinuteBitmap(Bitmap bitmap) {
            this.q = bitmap;
            return this;
        }

        public Builder setPreviewBitmap(Bitmap bitmap) {
            this.o = bitmap;
            return this;
        }

        public Builder setSecondBitmap(Bitmap bitmap) {
            this.r = bitmap;
            return this;
        }

        public Builder setThumbBitmap(Bitmap bitmap) {
            this.f = bitmap;
            return this;
        }

        public Builder setTimeout(int i) {
            this.g = i;
            return this;
        }

        public Builder setWatchFaceFilePath(String str) {
            this.d = str;
            return this;
        }

        public Builder setWatchFaceId(int i) {
            this.j = i;
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

        public Builder setWidth(int i) {
            this.l = i;
            return this;
        }
    }

    public Bitmap getBitmap() {
        return this.f;
    }

    public String getCompressionType() {
        return this.m;
    }

    public CustomWatchFaceLayoutChangeRequest getCustomWatchFaceLayoutChangeRequest() {
        return this.r;
    }

    public ArrayList<Element> getElementArrayList() {
        return this.s;
    }

    public int getHeight() {
        return this.p;
    }

    public Bitmap getHourBitmap() {
        return this.u;
    }

    public File getImageFile() {
        return this.g;
    }

    public int getImageId() {
        return this.h;
    }

    public Bitmap getMinuteBitmap() {
        return this.v;
    }

    public Bitmap getPreviewBitmap() {
        return this.t;
    }

    public Bitmap getSecondBitmap() {
        return this.w;
    }

    public Bitmap getThumbBitmap() {
        return this.n;
    }

    public int getTimeout() {
        return this.o;
    }

    public String getWatchFaceFilePath() {
        return this.l;
    }

    public int getWatchFaceId() {
        return this.i;
    }

    public int getWatchFacePosition() {
        return this.j;
    }

    public int getWatchFaceResourceID() {
        return this.k;
    }

    public int getWidth() {
        return this.q;
    }
}
