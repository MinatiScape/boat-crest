package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class StressTimeIntervalRequest extends BleBaseRequest {
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public boolean s;
    public int u;
    public int v;
    public int w;
    public boolean f = true;
    public boolean l = true;
    public boolean m = true;
    public boolean n = true;
    public boolean o = true;
    public boolean p = true;
    public boolean q = true;
    public boolean r = true;
    public float t = 80.0f;

    /* loaded from: classes2.dex */
    public static final class Builder {
        public int b;
        public int c;
        public int f;
        public boolean n;
        public int p;
        public int q;
        public int r;

        /* renamed from: a  reason: collision with root package name */
        public boolean f3567a = true;
        public int d = 23;
        public int e = 59;
        public boolean g = true;
        public boolean h = true;
        public boolean i = true;
        public boolean j = true;
        public boolean k = true;
        public boolean l = true;
        public boolean m = true;
        public float o = 80.0f;

        public Builder(int i) {
            this.f = 30;
            this.f = i;
        }

        @NotNull
        public final StressTimeIntervalRequest build() {
            StressTimeIntervalRequest stressTimeIntervalRequest = new StressTimeIntervalRequest();
            stressTimeIntervalRequest.setEnabled(this.f3567a);
            stressTimeIntervalRequest.setStartHour(this.b);
            stressTimeIntervalRequest.setStartMinute(this.c);
            stressTimeIntervalRequest.setEndHour(this.d);
            stressTimeIntervalRequest.setEndMinute(this.e);
            stressTimeIntervalRequest.setTimeInterval(this.f);
            stressTimeIntervalRequest.setSundayEnabled(this.g);
            stressTimeIntervalRequest.setMondayEnabled(this.h);
            stressTimeIntervalRequest.setTuesdayEnabled(this.i);
            stressTimeIntervalRequest.setWednesdayEnabled(this.j);
            stressTimeIntervalRequest.setThursdayEnabled(this.k);
            stressTimeIntervalRequest.setFridayEnabled(this.l);
            stressTimeIntervalRequest.setSaturdayEnabled(this.m);
            stressTimeIntervalRequest.setHighStressRemindEnabled(this.n);
            stressTimeIntervalRequest.setHighStressThreshold(this.o);
            stressTimeIntervalRequest.setBaseLineTime(this.p);
            stressTimeIntervalRequest.setReadinessTime(this.q);
            stressTimeIntervalRequest.setLimit(this.r);
            return stressTimeIntervalRequest;
        }

        public final int getBaseLineTime() {
            return this.p;
        }

        public final int getEndHour() {
            return this.d;
        }

        public final int getEndMin() {
            return this.e;
        }

        public final float getHighStressThreshold() {
            return this.o;
        }

        public final int getLimit() {
            return this.r;
        }

        public final int getReadinessTime() {
            return this.q;
        }

        public final int getStartHour() {
            return this.b;
        }

        public final int getStartMin() {
            return this.c;
        }

        public final int getTimeInterval() {
            return this.f;
        }

        public final boolean isEnabled() {
            return this.f3567a;
        }

        public final boolean isFridayEnabled() {
            return this.l;
        }

        public final boolean isHighStressRemindEnabled() {
            return this.n;
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

        /* renamed from: setBaseLineTime  reason: collision with other method in class */
        public final void m74setBaseLineTime(int i) {
            this.p = i;
        }

        /* renamed from: setEnabled  reason: collision with other method in class */
        public final void m75setEnabled(boolean z) {
            this.f3567a = z;
        }

        /* renamed from: setEndHour  reason: collision with other method in class */
        public final void m76setEndHour(int i) {
            this.d = i;
        }

        /* renamed from: setEndMin  reason: collision with other method in class */
        public final void m77setEndMin(int i) {
            this.e = i;
        }

        /* renamed from: setFridayEnabled  reason: collision with other method in class */
        public final void m78setFridayEnabled(boolean z) {
            this.l = z;
        }

        public final void setHighStressRemindEnabled(boolean z) {
            this.n = z;
        }

        /* renamed from: setHighStressThreshold  reason: collision with other method in class */
        public final void m79setHighStressThreshold(float f) {
            this.o = f;
        }

        @NotNull
        public final Builder setHighThresholdRemindEnabled(boolean z) {
            this.n = z;
            return this;
        }

        /* renamed from: setLimit  reason: collision with other method in class */
        public final void m80setLimit(int i) {
            this.r = i;
        }

        /* renamed from: setMondayEnabled  reason: collision with other method in class */
        public final void m81setMondayEnabled(boolean z) {
            this.h = z;
        }

        /* renamed from: setReadinessTime  reason: collision with other method in class */
        public final void m82setReadinessTime(int i) {
            this.q = i;
        }

        /* renamed from: setSaturdayEnabled  reason: collision with other method in class */
        public final void m83setSaturdayEnabled(boolean z) {
            this.m = z;
        }

        /* renamed from: setStartHour  reason: collision with other method in class */
        public final void m84setStartHour(int i) {
            this.b = i;
        }

        /* renamed from: setStartMin  reason: collision with other method in class */
        public final void m85setStartMin(int i) {
            this.c = i;
        }

        /* renamed from: setSundayEnabled  reason: collision with other method in class */
        public final void m86setSundayEnabled(boolean z) {
            this.g = z;
        }

        /* renamed from: setThursdayEnabled  reason: collision with other method in class */
        public final void m87setThursdayEnabled(boolean z) {
            this.k = z;
        }

        public final void setTimeInterval(int i) {
            this.f = i;
        }

        /* renamed from: setTuesdayEnabled  reason: collision with other method in class */
        public final void m88setTuesdayEnabled(boolean z) {
            this.i = z;
        }

        /* renamed from: setWednesdayEnabled  reason: collision with other method in class */
        public final void m89setWednesdayEnabled(boolean z) {
            this.j = z;
        }

        @NotNull
        public final Builder setBaseLineTime(int i) {
            this.p = i;
            return this;
        }

        @NotNull
        public final Builder setEnabled(boolean z) {
            this.f3567a = z;
            return this;
        }

        @NotNull
        public final Builder setEndHour(int i) {
            this.d = i;
            return this;
        }

        @NotNull
        public final Builder setEndMin(int i) {
            this.e = i;
            return this;
        }

        @NotNull
        public final Builder setFridayEnabled(boolean z) {
            this.l = z;
            return this;
        }

        @NotNull
        public final Builder setHighStressThreshold(float f) {
            this.o = f;
            return this;
        }

        @NotNull
        public final Builder setLimit(int i) {
            this.r = i;
            return this;
        }

        @NotNull
        public final Builder setMondayEnabled(boolean z) {
            this.h = z;
            return this;
        }

        @NotNull
        public final Builder setReadinessTime(int i) {
            this.q = i;
            return this;
        }

        @NotNull
        public final Builder setSaturdayEnabled(boolean z) {
            this.m = z;
            return this;
        }

        @NotNull
        public final Builder setStartHour(int i) {
            this.b = i;
            return this;
        }

        @NotNull
        public final Builder setStartMin(int i) {
            this.c = i;
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

    public final int getBaseLineTime() {
        return this.u;
    }

    public final int getEndHour() {
        return this.i;
    }

    public final int getEndMinute() {
        return this.j;
    }

    public final float getHighStressThreshold() {
        return this.t;
    }

    public final int getLimit() {
        return this.w;
    }

    public final int getReadinessTime() {
        return this.v;
    }

    public final int getStartHour() {
        return this.g;
    }

    public final int getStartMinute() {
        return this.h;
    }

    public final int getTimeInterval() {
        return this.k;
    }

    public final boolean isEnabled() {
        return this.f;
    }

    public final boolean isFridayEnabled() {
        return this.q;
    }

    public final boolean isHighStressRemindEnabled() {
        return this.s;
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

    public final void setBaseLineTime(int i) {
        this.u = i;
    }

    public final void setEnabled(boolean z) {
        this.f = z;
    }

    public final void setEndHour(int i) {
        this.i = i;
    }

    public final void setEndMinute(int i) {
        this.j = i;
    }

    public final void setFridayEnabled(boolean z) {
        this.q = z;
    }

    public final void setHighStressRemindEnabled(boolean z) {
        this.s = z;
    }

    public final void setHighStressThreshold(float f) {
        this.t = f;
    }

    public final void setLimit(int i) {
        this.w = i;
    }

    public final void setMondayEnabled(boolean z) {
        this.m = z;
    }

    public final void setReadinessTime(int i) {
        this.v = i;
    }

    public final void setSaturdayEnabled(boolean z) {
        this.r = z;
    }

    public final void setStartHour(int i) {
        this.g = i;
    }

    public final void setStartMinute(int i) {
        this.h = i;
    }

    public final void setSundayEnabled(boolean z) {
        this.l = z;
    }

    public final void setThursdayEnabled(boolean z) {
        this.p = z;
    }

    public final void setTimeInterval(int i) {
        this.k = i;
    }

    public final void setTuesdayEnabled(boolean z) {
        this.n = z;
    }

    public final void setWednesdayEnabled(boolean z) {
        this.o = z;
    }
}
