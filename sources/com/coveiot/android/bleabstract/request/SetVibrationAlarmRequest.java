package com.coveiot.android.bleabstract.request;

import com.coveiot.android.bleabstract.models.BleCommand;
/* loaded from: classes2.dex */
public class SetVibrationAlarmRequest extends BleBaseRequest {
    public boolean f;
    public int g;
    public int h;
    public int i;
    public String j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public int t = 1;
    public int u = 0;
    public int v = 0;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f3553a;
        public boolean b;
        public int c;
        public int d;
        public String e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public boolean m;
        public int n;
        public int o = 1;
        public int p = 0;
        public int q = 0;
        public boolean r;

        public SetVibrationAlarmRequest build() {
            SetVibrationAlarmRequest setVibrationAlarmRequest = new SetVibrationAlarmRequest(this.n, this.c, this.d);
            setVibrationAlarmRequest.f = this.b;
            setVibrationAlarmRequest.i = this.d;
            setVibrationAlarmRequest.h = this.c;
            setVibrationAlarmRequest.j = this.e;
            setVibrationAlarmRequest.k = this.f;
            setVibrationAlarmRequest.l = this.g;
            setVibrationAlarmRequest.m = this.h;
            setVibrationAlarmRequest.n = this.i;
            setVibrationAlarmRequest.o = this.j;
            setVibrationAlarmRequest.p = this.k;
            setVibrationAlarmRequest.q = this.l;
            setVibrationAlarmRequest.r = this.m;
            setVibrationAlarmRequest.g = this.n;
            setVibrationAlarmRequest.t = this.o;
            setVibrationAlarmRequest.u = this.p;
            setVibrationAlarmRequest.v = this.q;
            setVibrationAlarmRequest.s = this.r;
            return setVibrationAlarmRequest;
        }

        public Builder setAlarmId(int i) {
            this.n = i;
            return this;
        }

        public Builder setAlarmType(int i) {
            this.o = i;
            return this;
        }

        public Builder setEditingAlarm(boolean z) {
            this.r = z;
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setEventName(String str) {
            this.e = str;
            return this;
        }

        public Builder setFridayEnabled(boolean z) {
            this.l = z;
            return this;
        }

        public Builder setHour(int i) {
            this.c = i;
            return this;
        }

        public Builder setId(Object obj) {
            this.f3553a = obj;
            return this;
        }

        public Builder setMinute(int i) {
            this.d = i;
            return this;
        }

        public Builder setMondayEnabled(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setRepeatEnabled(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setSaturdayEnabled(boolean z) {
            this.m = z;
            return this;
        }

        public Builder setSnoozeDuration(int i) {
            this.p = i;
            return this;
        }

        public Builder setSnoozeRepeatTimes(int i) {
            this.q = i;
            return this;
        }

        public Builder setSundayEnabled(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setThursdayEnabled(boolean z) {
            this.k = z;
            return this;
        }

        public Builder setTuesdayEnabled(boolean z) {
            this.i = z;
            return this;
        }

        public Builder setWednesdayEnabled(boolean z) {
            this.j = z;
            return this;
        }
    }

    public SetVibrationAlarmRequest(int i, int i2, int i3) {
        this.h = i2;
        this.i = i3;
        this.g = i;
    }

    public int getAlarmId() {
        return this.g;
    }

    public int getAlarmType() {
        return this.t;
    }

    @Override // com.coveiot.android.bleabstract.request.BleBaseRequest
    public BleCommand getBleCommand() {
        return BleCommand.SET_VIBRATION_ALARM;
    }

    public String getEventName() {
        return this.j;
    }

    public int getHour() {
        return this.h;
    }

    public int getMinute() {
        return this.i;
    }

    public int getSnooze_duration() {
        return this.u;
    }

    public int getSnooze_repeat_times() {
        return this.v;
    }

    public boolean isEditingAlarm() {
        return this.s;
    }

    public boolean isEnabled() {
        return this.f;
    }

    public boolean isFridayEnabled() {
        return this.q;
    }

    public boolean isMondayEnabled() {
        return this.m;
    }

    public boolean isRepeatEnabled() {
        return this.k;
    }

    public boolean isSaturdayEnabled() {
        return this.r;
    }

    public boolean isSundayEnabled() {
        return this.l;
    }

    public boolean isThursdayEnabled() {
        return this.p;
    }

    public boolean isTuesdayEnabled() {
        return this.n;
    }

    public boolean isWednesdayEnabled() {
        return this.o;
    }
}
