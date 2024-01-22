package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class SetReminderRequest extends BleBaseRequest {
    public boolean f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public boolean p = true;
    public boolean q = true;
    public boolean r = true;
    public boolean s = true;
    public boolean t = true;
    public boolean u = true;
    public boolean v = true;
    public boolean w = true;
    public int x = 50;
    public ReminderType y = ReminderType.SEDENTARY_REMINDER;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3544a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public boolean k = true;
        public boolean l = true;
        public boolean m = true;
        public boolean n = true;
        public boolean o = true;
        public boolean p = true;
        public boolean q = true;
        public boolean r = true;
        public int s = 50;
        public ReminderType t = ReminderType.SEDENTARY_REMINDER;

        public SetReminderRequest build() {
            SetReminderRequest setReminderRequest = new SetReminderRequest();
            setReminderRequest.f = this.f3544a;
            setReminderRequest.g = this.b;
            setReminderRequest.i = this.d;
            setReminderRequest.k = this.f;
            setReminderRequest.m = this.h;
            setReminderRequest.l = this.g;
            setReminderRequest.n = this.i;
            setReminderRequest.h = this.c;
            setReminderRequest.j = this.e;
            setReminderRequest.o = this.j;
            setReminderRequest.p = this.k;
            setReminderRequest.q = this.l;
            setReminderRequest.r = this.m;
            setReminderRequest.s = this.n;
            setReminderRequest.t = this.o;
            setReminderRequest.u = this.p;
            setReminderRequest.v = this.q;
            setReminderRequest.w = this.r;
            setReminderRequest.x = this.s;
            setReminderRequest.y = this.t;
            return setReminderRequest;
        }

        public Builder setEnabled(boolean z) {
            this.f3544a = z;
            return this;
        }

        public Builder setEndHour1(int i) {
            this.f = i;
            return this;
        }

        public Builder setEndHour2(int i) {
            this.h = i;
            return this;
        }

        public Builder setEndMin1(int i) {
            this.g = i;
            return this;
        }

        public Builder setEndMin2(int i) {
            this.i = i;
            return this;
        }

        public Builder setFridayEnabled(boolean z) {
            this.q = z;
            return this;
        }

        public Builder setLeastStep(int i) {
            this.s = i;
            return this;
        }

        public Builder setMondayEnabled(boolean z) {
            this.m = z;
            return this;
        }

        public Builder setReminderInterval(int i) {
            this.j = i;
            return this;
        }

        public Builder setReminderType(ReminderType reminderType) {
            this.t = reminderType;
            return this;
        }

        public Builder setRepeatEnabled(boolean z) {
            this.k = z;
            return this;
        }

        public Builder setSaturdayEnabled(boolean z) {
            this.r = z;
            return this;
        }

        public Builder setStartHour1(int i) {
            this.b = i;
            return this;
        }

        public Builder setStartHour2(int i) {
            this.d = i;
            return this;
        }

        public Builder setStartMin1(int i) {
            this.c = i;
            return this;
        }

        public Builder setStartMin2(int i) {
            this.e = i;
            return this;
        }

        public Builder setSundayEnabled(boolean z) {
            this.l = z;
            return this;
        }

        public Builder setThursdayEnabled(boolean z) {
            this.p = z;
            return this;
        }

        public Builder setTuesdayEnabled(boolean z) {
            this.n = z;
            return this;
        }

        public Builder setWednesdayEnabled(boolean z) {
            this.o = z;
            return this;
        }
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.SET_SEDENTARY_REMINDER;
    }

    public int getEndHour1() {
        return this.k;
    }

    public int getEndHour2() {
        return this.m;
    }

    public int getEndMin1() {
        return this.l;
    }

    public int getEndMin2() {
        return this.n;
    }

    public int getLeastStep() {
        return this.x;
    }

    public int getReminderInterval() {
        return this.o;
    }

    public ReminderType getReminderType() {
        return this.y;
    }

    public int getStartHour1() {
        return this.g;
    }

    public int getStartHour2() {
        return this.i;
    }

    public int getStartMin1() {
        return this.h;
    }

    public int getStartMin2() {
        return this.j;
    }

    public boolean isEnabled() {
        return this.f;
    }

    public boolean isFridayEnabled() {
        return this.v;
    }

    public boolean isMondayEnabled() {
        return this.r;
    }

    public boolean isRepeatEnabled() {
        return this.p;
    }

    public boolean isSaturdayEnabled() {
        return this.w;
    }

    public boolean isSundayEnabled() {
        return this.q;
    }

    public boolean isThursdayEnabled() {
        return this.u;
    }

    public boolean isTuesdayEnabled() {
        return this.s;
    }

    public boolean isWednesdayEnabled() {
        return this.t;
    }
}
