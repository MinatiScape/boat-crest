package com.coveiot.covepreferences.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class EventReminderData {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static EventReminderData i;

    /* renamed from: a  reason: collision with root package name */
    public int f7023a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    @Nullable
    public String h;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final EventReminderData getINSTANCE() {
            return EventReminderData.i;
        }

        @Nullable
        public final EventReminderData getInstance() {
            setINSTANCE(new EventReminderData(null));
            return getINSTANCE();
        }

        public final void setINSTANCE(@Nullable EventReminderData eventReminderData) {
            EventReminderData.i = eventReminderData;
        }
    }

    public EventReminderData() {
    }

    public /* synthetic */ EventReminderData(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int getDay() {
        return this.d;
    }

    public final int getEventId() {
        return this.f7023a;
    }

    public final int getHour() {
        return this.e;
    }

    public final int getMinute() {
        return this.f;
    }

    public final int getMonth() {
        return this.c;
    }

    public final int getRepeatType() {
        return this.g;
    }

    @Nullable
    public final String getTitle() {
        return this.h;
    }

    public final int getYear() {
        return this.b;
    }

    public final void setDay(int i2) {
        this.d = i2;
    }

    public final void setEventId(int i2) {
        this.f7023a = i2;
    }

    public final void setHour(int i2) {
        this.e = i2;
    }

    public final void setMinute(int i2) {
        this.f = i2;
    }

    public final void setMonth(int i2) {
        this.c = i2;
    }

    public final void setRepeatType(int i2) {
        this.g = i2;
    }

    public final void setTitle(@Nullable String str) {
        this.h = str;
    }

    public final void setYear(int i2) {
        this.b = i2;
    }
}
