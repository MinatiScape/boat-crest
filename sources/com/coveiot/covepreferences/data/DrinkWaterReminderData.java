package com.coveiot.covepreferences.data;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes8.dex */
public final class DrinkWaterReminderData {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable
    public static DrinkWaterReminderData g;

    /* renamed from: a  reason: collision with root package name */
    public int f7022a;
    public int b;
    public int c;
    public int d;
    public int e;
    public boolean f;

    /* loaded from: classes8.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final DrinkWaterReminderData getInstance() {
            if (DrinkWaterReminderData.g == null) {
                DrinkWaterReminderData.g = new DrinkWaterReminderData(null);
            }
            return DrinkWaterReminderData.g;
        }
    }

    public DrinkWaterReminderData() {
    }

    public /* synthetic */ DrinkWaterReminderData(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final int getEndHour() {
        return this.d;
    }

    public final int getEndMinute() {
        return this.e;
    }

    public final int getRemindInterval() {
        return this.f7022a;
    }

    public final int getStartHour() {
        return this.b;
    }

    public final int getStartMinute() {
        return this.c;
    }

    public final boolean isReminderEnable() {
        return this.f;
    }

    public final void setEndHour(int i) {
        this.d = i;
    }

    public final void setEndMinute(int i) {
        this.e = i;
    }

    public final void setRemindInterval(int i) {
        this.f7022a = i;
    }

    public final void setReminderEnable(boolean z) {
        this.f = z;
    }

    public final void setStartHour(int i) {
        this.b = i;
    }

    public final void setStartMinute(int i) {
        this.c = i;
    }
}
