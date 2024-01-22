package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class TemperatureTimeIntervalRequest extends BleBaseRequest {
    public int f;
    public int h;
    public int i;
    public int j;
    public int k;
    public int g = 1;
    public boolean l = true;
    public boolean m = true;
    public boolean n = true;
    public boolean o = true;
    public boolean p = true;
    public boolean q = true;
    public boolean r = true;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3572a;
        public int c;
        public int d;
        public int b = 2;
        public int e = 23;
        public int f = 59;
        public boolean g = true;
        public boolean h = true;
        public boolean i = true;
        public boolean j = true;
        public boolean k = true;
        public boolean l = true;
        public boolean m = true;

        public Builder(int i) {
            this.f3572a = 30;
            this.f3572a = i;
        }

        @NotNull
        public final TemperatureTimeIntervalRequest build() {
            TemperatureTimeIntervalRequest temperatureTimeIntervalRequest = new TemperatureTimeIntervalRequest();
            temperatureTimeIntervalRequest.setTimeInterval(this.f3572a);
            temperatureTimeIntervalRequest.setOpen(this.b);
            temperatureTimeIntervalRequest.setStartHour(this.c);
            temperatureTimeIntervalRequest.setStartMinute(this.d);
            temperatureTimeIntervalRequest.setEndHour(this.e);
            temperatureTimeIntervalRequest.setEndMinute(this.f);
            temperatureTimeIntervalRequest.setSundayEnabled(this.g);
            temperatureTimeIntervalRequest.setMondayEnabled(this.h);
            temperatureTimeIntervalRequest.setTuesdayEnabled(this.i);
            temperatureTimeIntervalRequest.setWednesdayEnabled(this.j);
            temperatureTimeIntervalRequest.setThursdayEnabled(this.k);
            temperatureTimeIntervalRequest.setFridayEnabled(this.l);
            temperatureTimeIntervalRequest.setSaturdayEnabled(this.m);
            return temperatureTimeIntervalRequest;
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
            return this.f3572a;
        }

        public final boolean isFridayEnabled() {
            return this.l;
        }

        public final boolean isMondayEnabled() {
            return this.h;
        }

        public final boolean isSaturdayEnabled() {
            return this.m;
        }

        public final boolean isSundayEnabled() {
            return this.g;
        }

        public final boolean isThursdayEnabled() {
            return this.k;
        }

        public final boolean isTuesdayEnabled() {
            return this.i;
        }

        public final boolean isWednesdayEnabled() {
            return this.j;
        }

        /* renamed from: setEndHour  reason: collision with other method in class */
        public final void m90setEndHour(int i) {
            this.e = i;
        }

        /* renamed from: setEndMin  reason: collision with other method in class */
        public final void m91setEndMin(int i) {
            this.f = i;
        }

        /* renamed from: setFridayEnabled  reason: collision with other method in class */
        public final void m92setFridayEnabled(boolean z) {
            this.l = z;
        }

        /* renamed from: setMondayEnabled  reason: collision with other method in class */
        public final void m93setMondayEnabled(boolean z) {
            this.h = z;
        }

        /* renamed from: setOpen  reason: collision with other method in class */
        public final void m94setOpen(int i) {
            this.b = i;
        }

        /* renamed from: setSaturdayEnabled  reason: collision with other method in class */
        public final void m95setSaturdayEnabled(boolean z) {
            this.m = z;
        }

        /* renamed from: setStartHour  reason: collision with other method in class */
        public final void m96setStartHour(int i) {
            this.c = i;
        }

        /* renamed from: setStartMin  reason: collision with other method in class */
        public final void m97setStartMin(int i) {
            this.d = i;
        }

        /* renamed from: setSundayEnabled  reason: collision with other method in class */
        public final void m98setSundayEnabled(boolean z) {
            this.g = z;
        }

        /* renamed from: setThursdayEnabled  reason: collision with other method in class */
        public final void m99setThursdayEnabled(boolean z) {
            this.k = z;
        }

        public final void setTimeInterval(int i) {
            this.f3572a = i;
        }

        /* renamed from: setTuesdayEnabled  reason: collision with other method in class */
        public final void m100setTuesdayEnabled(boolean z) {
            this.i = z;
        }

        /* renamed from: setWednesdayEnabled  reason: collision with other method in class */
        public final void m101setWednesdayEnabled(boolean z) {
            this.j = z;
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
        public final Builder setFridayEnabled(boolean z) {
            this.l = z;
            return this;
        }

        @NotNull
        public final Builder setMondayEnabled(boolean z) {
            this.h = z;
            return this;
        }

        @NotNull
        public final Builder setOpen(int i) {
            this.b = i;
            return this;
        }

        @NotNull
        public final Builder setSaturdayEnabled(boolean z) {
            this.m = z;
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

        @NotNull
        public final Builder setSundayEnabled(boolean z) {
            this.g = z;
            return this;
        }

        @NotNull
        public final Builder setThursdayEnabled(boolean z) {
            this.k = z;
            return this;
        }

        @NotNull
        public final Builder setTuesdayEnabled(boolean z) {
            this.i = z;
            return this;
        }

        @NotNull
        public final Builder setWednesdayEnabled(boolean z) {
            this.j = z;
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

    public final boolean isFridayEnabled() {
        return this.q;
    }

    public final boolean isMondayEnabled() {
        return this.m;
    }

    public final boolean isSaturdayEnabled() {
        return this.r;
    }

    public final boolean isSundayEnabled() {
        return this.l;
    }

    public final boolean isThursdayEnabled() {
        return this.p;
    }

    public final boolean isTuesdayEnabled() {
        return this.n;
    }

    public final boolean isWednesdayEnabled() {
        return this.o;
    }

    public final void setEndHour(int i) {
        this.j = i;
    }

    public final void setEndMinute(int i) {
        this.k = i;
    }

    public final void setFridayEnabled(boolean z) {
        this.q = z;
    }

    public final void setMondayEnabled(boolean z) {
        this.m = z;
    }

    public final void setOpen(int i) {
        this.g = i;
    }

    public final void setSaturdayEnabled(boolean z) {
        this.r = z;
    }

    public final void setStartHour(int i) {
        this.h = i;
    }

    public final void setStartMinute(int i) {
        this.i = i;
    }

    public final void setSundayEnabled(boolean z) {
        this.l = z;
    }

    public final void setThursdayEnabled(boolean z) {
        this.p = z;
    }

    public final void setTimeInterval(int i) {
        this.f = i;
    }

    public final void setTuesdayEnabled(boolean z) {
        this.n = z;
    }

    public final void setWednesdayEnabled(boolean z) {
        this.o = z;
    }
}
