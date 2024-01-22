package com.coveiot.android.bleabstract.models;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class AutoActivityDetectionModel {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public byte[] f3399a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public boolean j;
    @Nullable
    public List<Period> k;

    /* loaded from: classes2.dex */
    public static final class Period {

        /* renamed from: a  reason: collision with root package name */
        public int f3400a;
        public int b;

        public Period(int i, int i2) {
            this.f3400a = i;
            this.b = i2;
        }

        public final int getEndTime() {
            return this.b;
        }

        public final int getStartTime() {
            return this.f3400a;
        }

        public final void setEndTime(int i) {
            this.b = i;
        }

        public final void setStartTime(int i) {
            this.f3400a = i;
        }
    }

    public AutoActivityDetectionModel(@NotNull byte[] activities, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, boolean z8, @Nullable List<Period> list) {
        Intrinsics.checkNotNullParameter(activities, "activities");
        this.b = true;
        this.c = true;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.f3399a = activities;
        this.b = z;
        this.c = z2;
        this.d = z3;
        this.e = z4;
        this.f = z5;
        this.g = z6;
        this.h = z7;
        this.i = i;
        this.j = z8;
        this.k = list;
    }

    @NotNull
    public final byte[] getActivities() {
        return this.f3399a;
    }

    public final int getCountDownValue() {
        return this.i;
    }

    @Nullable
    public final List<Period> getPeriods() {
        return this.k;
    }

    public final boolean isFridayEnabled() {
        return this.g;
    }

    public final boolean isMondayEnabled() {
        return this.c;
    }

    public final boolean isSaturdayEnabled() {
        return this.h;
    }

    public final boolean isSundayEnabled() {
        return this.b;
    }

    public final boolean isThursdayEnabled() {
        return this.f;
    }

    public final boolean isTuesdayEnabled() {
        return this.d;
    }

    public final boolean isVibrationEnabled() {
        return this.j;
    }

    public final boolean isWednesdayEnabled() {
        return this.e;
    }

    public final void setActivities(@NotNull byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "<set-?>");
        this.f3399a = bArr;
    }

    public final void setCountDownValue(int i) {
        this.i = i;
    }

    public final void setFridayEnabled(boolean z) {
        this.g = z;
    }

    public final void setMondayEnabled(boolean z) {
        this.c = z;
    }

    public final void setPeriods(@Nullable List<Period> list) {
        this.k = list;
    }

    public final void setSaturdayEnabled(boolean z) {
        this.h = z;
    }

    public final void setSundayEnabled(boolean z) {
        this.b = z;
    }

    public final void setThursdayEnabled(boolean z) {
        this.f = z;
    }

    public final void setTuesdayEnabled(boolean z) {
        this.d = z;
    }

    public final void setVibrationEnabled(boolean z) {
        this.j = z;
    }

    public final void setWednesdayEnabled(boolean z) {
        this.e = z;
    }
}
