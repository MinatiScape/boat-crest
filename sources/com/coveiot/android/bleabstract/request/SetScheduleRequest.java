package com.coveiot.android.bleabstract.request;
/* loaded from: classes2.dex */
public class SetScheduleRequest extends BleBaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public String m;
    public String n;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f3546a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public String i;
        public String j;

        public SetScheduleRequest build() {
            SetScheduleRequest setScheduleRequest = new SetScheduleRequest(this.b);
            setScheduleRequest.g = this.c;
            setScheduleRequest.h = this.d;
            setScheduleRequest.i = this.e;
            setScheduleRequest.j = this.f;
            setScheduleRequest.k = this.g;
            setScheduleRequest.l = this.h;
            setScheduleRequest.m = this.i;
            setScheduleRequest.n = this.j;
            return setScheduleRequest;
        }

        public Builder setAdvance(int i) {
            this.h = i;
            return this;
        }

        public Builder setContent(String str) {
            this.j = str;
            return this;
        }

        public Builder setDay(int i) {
            this.e = i;
            return this;
        }

        public Builder setHour(int i) {
            this.f = i;
            return this;
        }

        public Builder setId(Object obj) {
            this.f3546a = obj;
            return this;
        }

        public Builder setMinute(int i) {
            this.g = i;
            return this;
        }

        public Builder setMonth(int i) {
            this.d = i;
            return this;
        }

        public Builder setScheduleId(int i) {
            this.b = i;
            return this;
        }

        public Builder setTitle(String str) {
            this.i = str;
            return this;
        }

        public Builder setYear(int i) {
            this.c = i;
            return this;
        }
    }

    public SetScheduleRequest(int i) {
        this.f = i;
    }

    public int getAdvance() {
        return this.l;
    }

    public String getContent() {
        return this.n;
    }

    public int getDay() {
        return this.i;
    }

    public int getHour() {
        return this.j;
    }

    public int getMinute() {
        return this.k;
    }

    public int getMonth() {
        return this.h;
    }

    public int getScheduleId() {
        return this.f;
    }

    public String getTitle() {
        return this.m;
    }

    public int getYear() {
        return this.g;
    }
}
