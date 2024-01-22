package com.coveiot.android.bleabstract.request;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SetEventReminderRequest extends BleBaseRequest {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int REPEAT_TYPE_DAILY = 1;
    public static final int REPEAT_TYPE_EVERY_WEEK = 2;
    public static final int REPEAT_TYPE_MONTHLY = 3;
    public static final int REPEAT_TYPE_NO_REPETITION = 0;
    public static final int REPEAT_TYPE_PER_YEAR = 4;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    @Nullable
    public String m;
    public int n;

    /* loaded from: classes2.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f3530a = 1;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        @Nullable
        public String h;
        public int i;

        @NotNull
        public final SetEventReminderRequest build() {
            SetEventReminderRequest setEventReminderRequest = new SetEventReminderRequest();
            setEventReminderRequest.setEventId(this.f3530a);
            setEventReminderRequest.setType(this.b);
            setEventReminderRequest.setDay(this.e);
            setEventReminderRequest.setMonth(this.d);
            setEventReminderRequest.setYear(this.c);
            setEventReminderRequest.setMinute(this.g);
            setEventReminderRequest.setHour(this.f);
            setEventReminderRequest.setEventName(this.h);
            setEventReminderRequest.setRepeatType(this.i);
            return setEventReminderRequest;
        }

        public final int getDay() {
            return this.e;
        }

        public final int getEventId() {
            return this.f3530a;
        }

        @Nullable
        public final String getEventName() {
            return this.h;
        }

        public final int getHour() {
            return this.f;
        }

        public final int getMinute() {
            return this.g;
        }

        public final int getMonth() {
            return this.d;
        }

        public final int getRepeatType() {
            return this.i;
        }

        public final int getType() {
            return this.b;
        }

        public final int getYear() {
            return this.c;
        }

        /* renamed from: setDay  reason: collision with other method in class */
        public final void m48setDay(int i) {
            this.e = i;
        }

        /* renamed from: setEventId  reason: collision with other method in class */
        public final void m49setEventId(int i) {
            this.f3530a = i;
        }

        /* renamed from: setEventName  reason: collision with other method in class */
        public final void m50setEventName(@Nullable String str) {
            this.h = str;
        }

        /* renamed from: setHour  reason: collision with other method in class */
        public final void m51setHour(int i) {
            this.f = i;
        }

        /* renamed from: setMinute  reason: collision with other method in class */
        public final void m52setMinute(int i) {
            this.g = i;
        }

        /* renamed from: setMonth  reason: collision with other method in class */
        public final void m53setMonth(int i) {
            this.d = i;
        }

        /* renamed from: setRepeatType  reason: collision with other method in class */
        public final void m54setRepeatType(int i) {
            this.i = i;
        }

        /* renamed from: setType  reason: collision with other method in class */
        public final void m55setType(int i) {
            this.b = i;
        }

        /* renamed from: setYear  reason: collision with other method in class */
        public final void m56setYear(int i) {
            this.c = i;
        }

        @NotNull
        public final Builder setDay(int i) {
            this.e = i;
            return this;
        }

        @NotNull
        public final Builder setEventId(int i) {
            this.f3530a = i;
            return this;
        }

        @NotNull
        public final Builder setEventName(@Nullable String str) {
            this.h = str;
            return this;
        }

        @NotNull
        public final Builder setHour(int i) {
            this.f = i;
            return this;
        }

        @NotNull
        public final Builder setMinute(int i) {
            this.g = i;
            return this;
        }

        @NotNull
        public final Builder setMonth(int i) {
            this.d = i;
            return this;
        }

        @NotNull
        public final Builder setRepeatType(int i) {
            this.i = i;
            return this;
        }

        @NotNull
        public final Builder setType(int i) {
            this.b = i;
            return this;
        }

        @NotNull
        public final Builder setYear(int i) {
            this.c = i;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final int getDay() {
        return this.j;
    }

    public final int getEventId() {
        return this.f;
    }

    @Nullable
    public final String getEventName() {
        return this.m;
    }

    public final int getHour() {
        return this.k;
    }

    public final int getMinute() {
        return this.l;
    }

    public final int getMonth() {
        return this.i;
    }

    public final int getRepeatType() {
        return this.n;
    }

    public final int getType() {
        return this.g;
    }

    public final int getYear() {
        return this.h;
    }

    public final void setDay(int i) {
        this.j = i;
    }

    public final void setEventId(int i) {
        this.f = i;
    }

    public final void setEventName(@Nullable String str) {
        this.m = str;
    }

    public final void setHour(int i) {
        this.k = i;
    }

    public final void setMinute(int i) {
        this.l = i;
    }

    public final void setMonth(int i) {
        this.i = i;
    }

    public final void setRepeatType(int i) {
        this.n = i;
    }

    public final void setType(int i) {
        this.g = i;
    }

    public final void setYear(int i) {
        this.h = i;
    }
}
