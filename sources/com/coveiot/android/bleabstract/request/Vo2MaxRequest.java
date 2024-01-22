package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class Vo2MaxRequest extends BleBaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3575a;
        public int b;
        public int c;
        public int d;

        public Builder(int i, int i2, int i3, int i4) {
            this.f3575a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public Vo2MaxRequest build() {
            Vo2MaxRequest vo2MaxRequest = new Vo2MaxRequest();
            vo2MaxRequest.f = this.f3575a;
            vo2MaxRequest.h = this.c;
            vo2MaxRequest.i = this.d;
            vo2MaxRequest.g = this.b;
            return vo2MaxRequest;
        }
    }

    public int getDay() {
        return this.i;
    }

    public int getMonth() {
        return this.h;
    }

    public int getVo2MaxValue() {
        return this.f;
    }

    public int getYear() {
        return this.g;
    }
}
