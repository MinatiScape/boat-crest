package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class Spo2TimeIntervalRequest extends BleBaseRequest {
    public int f;
    public int g = 1;
    public int h;
    public int i;
    public int j;
    public int k;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3561a;
        public int c;
        public int d;
        public int b = 1;
        public int e = 23;
        public int f = 59;

        public Builder(int i) {
            this.f3561a = 30;
            this.f3561a = i;
        }

        @NotNull
        public final Spo2TimeIntervalRequest build() {
            Spo2TimeIntervalRequest spo2TimeIntervalRequest = new Spo2TimeIntervalRequest();
            spo2TimeIntervalRequest.setTimeInterval(this.f3561a);
            spo2TimeIntervalRequest.setOpen(this.b);
            spo2TimeIntervalRequest.setStartHour(this.c);
            spo2TimeIntervalRequest.setStartMinute(this.d);
            spo2TimeIntervalRequest.setEndHour(this.e);
            spo2TimeIntervalRequest.setEndMinute(this.f);
            return spo2TimeIntervalRequest;
        }

        public final int getEndHour() {
            return this.e;
        }

        public final int getEndMin() {
            return this.f;
        }

        public final int getOpen() {
            return this.b;
        }

        public final int getStartHour() {
            return this.c;
        }

        public final int getStartMin() {
            return this.d;
        }

        public final int getTimeInterval() {
            return this.f3561a;
        }

        /* renamed from: setEndHour  reason: collision with other method in class */
        public final void m69setEndHour(int i) {
            this.e = i;
        }

        /* renamed from: setEndMin  reason: collision with other method in class */
        public final void m70setEndMin(int i) {
            this.f = i;
        }

        /* renamed from: setOpen  reason: collision with other method in class */
        public final void m71setOpen(int i) {
            this.b = i;
        }

        /* renamed from: setStartHour  reason: collision with other method in class */
        public final void m72setStartHour(int i) {
            this.c = i;
        }

        /* renamed from: setStartMin  reason: collision with other method in class */
        public final void m73setStartMin(int i) {
            this.d = i;
        }

        public final void setTimeInterval(int i) {
            this.f3561a = i;
        }

        @NotNull
        public final Builder setEndHour(int i) {
            this.e = i;
            return this;
        }

        @NotNull
        public final Builder setEndMin(int i) {
            this.f = i;
            return this;
        }

        @NotNull
        public final Builder setOpen(int i) {
            this.b = i;
            return this;
        }

        @NotNull
        public final Builder setStartHour(int i) {
            this.c = i;
            return this;
        }

        @NotNull
        public final Builder setStartMin(int i) {
            this.d = i;
            return this;
        }
    }

    public final int getEndHour() {
        return this.j;
    }

    public final int getEndMinute() {
        return this.k;
    }

    public final int getOpen() {
        return this.g;
    }

    public final int getStartHour() {
        return this.h;
    }

    public final int getStartMinute() {
        return this.i;
    }

    public final int getTimeInterval() {
        return this.f;
    }

    public final void setEndHour(int i) {
        this.j = i;
    }

    public final void setEndMinute(int i) {
        this.k = i;
    }

    public final void setOpen(int i) {
        this.g = i;
    }

    public final void setStartHour(int i) {
        this.h = i;
    }

    public final void setStartMinute(int i) {
        this.i = i;
    }

    public final void setTimeInterval(int i) {
        this.f = i;
    }
}
