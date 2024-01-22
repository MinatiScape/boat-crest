package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetLiftWristRequest extends BleBaseRequest {
    public boolean f;
    public int g = 8;
    public int h = 0;
    public int i = 22;
    public int j = 0;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3534a;
        public int b = 8;
        public int c = 0;
        public int d = 22;
        public int e = 0;

        public Builder(boolean z) {
            this.f3534a = z;
        }

        public SetLiftWristRequest build() {
            SetLiftWristRequest setLiftWristRequest = new SetLiftWristRequest();
            setLiftWristRequest.f = this.f3534a;
            setLiftWristRequest.g = this.b;
            setLiftWristRequest.h = this.c;
            setLiftWristRequest.i = this.d;
            setLiftWristRequest.j = this.e;
            return setLiftWristRequest;
        }

        public void setEndHour(int i) {
            this.d = i;
        }

        public void setEndMinute(int i) {
            this.e = i;
        }

        public void setStartHour(int i) {
            this.b = i;
        }

        public void setStartMinute(int i) {
            this.c = i;
        }
    }

    public int getEndHour() {
        return this.i;
    }

    public int getEndMinute() {
        return this.j;
    }

    public int getStartHour() {
        return this.g;
    }

    public int getStartMinute() {
        return this.h;
    }

    public boolean isLiftWristEnabled() {
        return this.f;
    }
}
