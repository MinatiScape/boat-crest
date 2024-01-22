package com.coveiot.android.bleabstract.request;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class SetWomenWellnessSettingsRequest extends BleBaseRequest {
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

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f3556a;
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

        @NotNull
        public final SetWomenWellnessSettingsRequest build() {
            SetWomenWellnessSettingsRequest setWomenWellnessSettingsRequest = new SetWomenWellnessSettingsRequest();
            setWomenWellnessSettingsRequest.setMEnabled(this.f3556a);
            setWomenWellnessSettingsRequest.setMReminderHour(this.b);
            setWomenWellnessSettingsRequest.setMReminderMinute(this.c);
            setWomenWellnessSettingsRequest.setMPeriodReminderAdvance(this.d);
            setWomenWellnessSettingsRequest.setMOvulationReminderAdvance(this.e);
            setWomenWellnessSettingsRequest.setMLastPeriodYear(this.f);
            setWomenWellnessSettingsRequest.setMLastPeriodMonth(this.g);
            setWomenWellnessSettingsRequest.setMLastPeriodDay(this.h);
            setWomenWellnessSettingsRequest.setMMenstruationPeriodLength(this.i);
            setWomenWellnessSettingsRequest.setMMenstruationCycleLength(this.j);
            setWomenWellnessSettingsRequest.setMMenstrualReminder(this.k);
            setWomenWellnessSettingsRequest.setMOvulationDayReminder(this.l);
            setWomenWellnessSettingsRequest.setMOvulationReminder(this.m);
            setWomenWellnessSettingsRequest.setMOvulationEndReminder(this.n);
            return setWomenWellnessSettingsRequest;
        }

        public final boolean getMEnabled() {
            return this.f3556a;
        }

        public final int getMLastPeriodDay() {
            return this.h;
        }

        public final int getMLastPeriodMonth() {
            return this.g;
        }

        public final int getMLastPeriodYear() {
            return this.f;
        }

        public final boolean getMMenstrualReminder() {
            return this.k;
        }

        public final int getMMenstruationCycleLength() {
            return this.j;
        }

        public final int getMMenstruationPeriodLength() {
            return this.i;
        }

        public final boolean getMOvulationDayReminder() {
            return this.l;
        }

        public final boolean getMOvulationEndReminder() {
            return this.n;
        }

        public final boolean getMOvulationReminder() {
            return this.m;
        }

        public final int getMOvulationReminderAdvance() {
            return this.e;
        }

        public final int getMPeriodReminderAdvance() {
            return this.d;
        }

        public final int getMReminderHour() {
            return this.b;
        }

        public final int getMReminderMinute() {
            return this.c;
        }

        @NotNull
        public final Builder setEnabled(boolean z) {
            this.f3556a = z;
            return this;
        }

        @NotNull
        public final Builder setLastPeriodDay(int i) {
            this.h = i;
            return this;
        }

        @NotNull
        public final Builder setLastPeriodMonth(int i) {
            this.g = i;
            return this;
        }

        @NotNull
        public final Builder setLastPeriodYear(int i) {
            this.f = i;
            return this;
        }

        public final void setMEnabled(boolean z) {
            this.f3556a = z;
        }

        public final void setMLastPeriodDay(int i) {
            this.h = i;
        }

        public final void setMLastPeriodMonth(int i) {
            this.g = i;
        }

        public final void setMLastPeriodYear(int i) {
            this.f = i;
        }

        public final void setMMenstrualReminder(boolean z) {
            this.k = z;
        }

        public final void setMMenstruationCycleLength(int i) {
            this.j = i;
        }

        public final void setMMenstruationPeriodLength(int i) {
            this.i = i;
        }

        public final void setMOvulationDayReminder(boolean z) {
            this.l = z;
        }

        public final void setMOvulationEndReminder(boolean z) {
            this.n = z;
        }

        public final void setMOvulationReminder(boolean z) {
            this.m = z;
        }

        public final void setMOvulationReminderAdvance(int i) {
            this.e = i;
        }

        public final void setMPeriodReminderAdvance(int i) {
            this.d = i;
        }

        public final void setMReminderHour(int i) {
            this.b = i;
        }

        public final void setMReminderMinute(int i) {
            this.c = i;
        }

        @NotNull
        public final Builder setMenstrualReminder(boolean z) {
            this.k = z;
            return this;
        }

        @NotNull
        public final Builder setMenstruationCycleLength(int i) {
            this.j = i;
            return this;
        }

        @NotNull
        public final Builder setMenstruationPeriodLength(int i) {
            this.i = i;
            return this;
        }

        @NotNull
        public final Builder setOvulationDayReminder(boolean z) {
            this.l = z;
            return this;
        }

        @NotNull
        public final Builder setOvulationEndReminder(boolean z) {
            this.n = z;
            return this;
        }

        @NotNull
        public final Builder setOvulationReminder(boolean z) {
            this.m = z;
            return this;
        }

        @NotNull
        public final Builder setOvulationReminderAdvance(int i) {
            this.e = i;
            return this;
        }

        @NotNull
        public final Builder setPeriodReminderAdvance(int i) {
            this.d = i;
            return this;
        }

        @NotNull
        public final Builder setReminderHour(int i) {
            this.b = i;
            return this;
        }

        @NotNull
        public final Builder setReminderMinute(int i) {
            this.c = i;
            return this;
        }
    }

    public final boolean getMEnabled() {
        return this.f;
    }

    public final int getMLastPeriodDay() {
        return this.m;
    }

    public final int getMLastPeriodMonth() {
        return this.l;
    }

    public final int getMLastPeriodYear() {
        return this.k;
    }

    public final boolean getMMenstrualReminder() {
        return this.p;
    }

    public final int getMMenstruationCycleLength() {
        return this.o;
    }

    public final int getMMenstruationPeriodLength() {
        return this.n;
    }

    public final boolean getMOvulationDayReminder() {
        return this.q;
    }

    public final boolean getMOvulationEndReminder() {
        return this.s;
    }

    public final boolean getMOvulationReminder() {
        return this.r;
    }

    public final int getMOvulationReminderAdvance() {
        return this.j;
    }

    public final int getMPeriodReminderAdvance() {
        return this.i;
    }

    public final int getMReminderHour() {
        return this.g;
    }

    public final int getMReminderMinute() {
        return this.h;
    }

    public final void setMEnabled(boolean z) {
        this.f = z;
    }

    public final void setMLastPeriodDay(int i) {
        this.m = i;
    }

    public final void setMLastPeriodMonth(int i) {
        this.l = i;
    }

    public final void setMLastPeriodYear(int i) {
        this.k = i;
    }

    public final void setMMenstrualReminder(boolean z) {
        this.p = z;
    }

    public final void setMMenstruationCycleLength(int i) {
        this.o = i;
    }

    public final void setMMenstruationPeriodLength(int i) {
        this.n = i;
    }

    public final void setMOvulationDayReminder(boolean z) {
        this.q = z;
    }

    public final void setMOvulationEndReminder(boolean z) {
        this.s = z;
    }

    public final void setMOvulationReminder(boolean z) {
        this.r = z;
    }

    public final void setMOvulationReminderAdvance(int i) {
        this.j = i;
    }

    public final void setMPeriodReminderAdvance(int i) {
        this.i = i;
    }

    public final void setMReminderHour(int i) {
        this.g = i;
    }

    public final void setMReminderMinute(int i) {
        this.h = i;
    }
}
