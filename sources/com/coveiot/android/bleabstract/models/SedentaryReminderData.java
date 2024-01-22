package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class SedentaryReminderData {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3447a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public int s;

    public SedentaryReminderData() {
        this(false, 0, 0, 0, 0, 0, 0, 0, 0, 0, false, false, false, false, false, false, false, false, 0, 524287, null);
    }

    public SedentaryReminderData(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i10) {
        this.f3447a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = i6;
        this.h = i7;
        this.i = i8;
        this.j = i9;
        this.k = z2;
        this.l = z3;
        this.m = z4;
        this.n = z5;
        this.o = z6;
        this.p = z7;
        this.q = z8;
        this.r = z9;
        this.s = i10;
    }

    public final boolean component1() {
        return this.f3447a;
    }

    public final int component10() {
        return this.j;
    }

    public final boolean component11() {
        return this.k;
    }

    public final boolean component12() {
        return this.l;
    }

    public final boolean component13() {
        return this.m;
    }

    public final boolean component14() {
        return this.n;
    }

    public final boolean component15() {
        return this.o;
    }

    public final boolean component16() {
        return this.p;
    }

    public final boolean component17() {
        return this.q;
    }

    public final boolean component18() {
        return this.r;
    }

    public final int component19() {
        return this.s;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    public final int component5() {
        return this.e;
    }

    public final int component6() {
        return this.f;
    }

    public final int component7() {
        return this.g;
    }

    public final int component8() {
        return this.h;
    }

    public final int component9() {
        return this.i;
    }

    @NotNull
    public final SedentaryReminderData copy(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i10) {
        return new SedentaryReminderData(z, i, i2, i3, i4, i5, i6, i7, i8, i9, z2, z3, z4, z5, z6, z7, z8, z9, i10);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SedentaryReminderData) {
            SedentaryReminderData sedentaryReminderData = (SedentaryReminderData) obj;
            return this.f3447a == sedentaryReminderData.f3447a && this.b == sedentaryReminderData.b && this.c == sedentaryReminderData.c && this.d == sedentaryReminderData.d && this.e == sedentaryReminderData.e && this.f == sedentaryReminderData.f && this.g == sedentaryReminderData.g && this.h == sedentaryReminderData.h && this.i == sedentaryReminderData.i && this.j == sedentaryReminderData.j && this.k == sedentaryReminderData.k && this.l == sedentaryReminderData.l && this.m == sedentaryReminderData.m && this.n == sedentaryReminderData.n && this.o == sedentaryReminderData.o && this.p == sedentaryReminderData.p && this.q == sedentaryReminderData.q && this.r == sedentaryReminderData.r && this.s == sedentaryReminderData.s;
        }
        return false;
    }

    public final int getEndHour1() {
        return this.f;
    }

    public final int getEndHour2() {
        return this.h;
    }

    public final int getEndMin1() {
        return this.g;
    }

    public final int getEndMin2() {
        return this.i;
    }

    public final int getLeastStep() {
        return this.s;
    }

    public final int getReminderInterval() {
        return this.j;
    }

    public final int getStartHour1() {
        return this.b;
    }

    public final int getStartHour2() {
        return this.d;
    }

    public final int getStartMin1() {
        return this.c;
    }

    public final int getStartMin2() {
        return this.e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r2v18, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v20, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v22, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v24, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v26, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v28, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v30, types: [boolean] */
    public int hashCode() {
        boolean z = this.f3447a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int hashCode = ((((((((((((((((((r0 * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g)) * 31) + Integer.hashCode(this.h)) * 31) + Integer.hashCode(this.i)) * 31) + Integer.hashCode(this.j)) * 31;
        ?? r2 = this.k;
        int i = r2;
        if (r2 != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        ?? r22 = this.l;
        int i3 = r22;
        if (r22 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        ?? r23 = this.m;
        int i5 = r23;
        if (r23 != 0) {
            i5 = 1;
        }
        int i6 = (i4 + i5) * 31;
        ?? r24 = this.n;
        int i7 = r24;
        if (r24 != 0) {
            i7 = 1;
        }
        int i8 = (i6 + i7) * 31;
        ?? r25 = this.o;
        int i9 = r25;
        if (r25 != 0) {
            i9 = 1;
        }
        int i10 = (i8 + i9) * 31;
        ?? r26 = this.p;
        int i11 = r26;
        if (r26 != 0) {
            i11 = 1;
        }
        int i12 = (i10 + i11) * 31;
        ?? r27 = this.q;
        int i13 = r27;
        if (r27 != 0) {
            i13 = 1;
        }
        int i14 = (i12 + i13) * 31;
        boolean z2 = this.r;
        return ((i14 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + Integer.hashCode(this.s);
    }

    public final boolean isEnabled() {
        return this.f3447a;
    }

    public final boolean isFridayEnabled() {
        return this.q;
    }

    public final boolean isMondayEnabled() {
        return this.m;
    }

    public final boolean isRepeatEnabled() {
        return this.k;
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

    public final void setEnabled(boolean z) {
        this.f3447a = z;
    }

    public final void setEndHour1(int i) {
        this.f = i;
    }

    public final void setEndHour2(int i) {
        this.h = i;
    }

    public final void setEndMin1(int i) {
        this.g = i;
    }

    public final void setEndMin2(int i) {
        this.i = i;
    }

    public final void setFridayEnabled(boolean z) {
        this.q = z;
    }

    public final void setLeastStep(int i) {
        this.s = i;
    }

    public final void setMondayEnabled(boolean z) {
        this.m = z;
    }

    public final void setReminderInterval(int i) {
        this.j = i;
    }

    public final void setRepeatEnabled(boolean z) {
        this.k = z;
    }

    public final void setSaturdayEnabled(boolean z) {
        this.r = z;
    }

    public final void setStartHour1(int i) {
        this.b = i;
    }

    public final void setStartHour2(int i) {
        this.d = i;
    }

    public final void setStartMin1(int i) {
        this.c = i;
    }

    public final void setStartMin2(int i) {
        this.e = i;
    }

    public final void setSundayEnabled(boolean z) {
        this.l = z;
    }

    public final void setThursdayEnabled(boolean z) {
        this.p = z;
    }

    public final void setTuesdayEnabled(boolean z) {
        this.n = z;
    }

    public final void setWednesdayEnabled(boolean z) {
        this.o = z;
    }

    @NotNull
    public String toString() {
        return "SedentaryReminderData(isEnabled=" + this.f3447a + ", startHour1=" + this.b + ", startMin1=" + this.c + ", startHour2=" + this.d + ", startMin2=" + this.e + ", endHour1=" + this.f + ", endMin1=" + this.g + ", endHour2=" + this.h + ", endMin2=" + this.i + ", reminderInterval=" + this.j + ", isRepeatEnabled=" + this.k + ", isSundayEnabled=" + this.l + ", isMondayEnabled=" + this.m + ", isTuesdayEnabled=" + this.n + ", isWednesdayEnabled=" + this.o + ", isThursdayEnabled=" + this.p + ", isFridayEnabled=" + this.q + ", isSaturdayEnabled=" + this.r + ", leastStep=" + this.s + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SedentaryReminderData(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i10, int i11, DefaultConstructorMarker defaultConstructorMarker) {
        this((i11 & 1) != 0 ? false : z, (i11 & 2) != 0 ? 0 : i, (i11 & 4) != 0 ? 0 : i2, (i11 & 8) != 0 ? 0 : i3, (i11 & 16) != 0 ? 0 : i4, (i11 & 32) != 0 ? 0 : i5, (i11 & 64) != 0 ? 0 : i6, (i11 & 128) != 0 ? 0 : i7, (i11 & 256) != 0 ? 0 : i8, (i11 & 512) == 0 ? i9 : 0, (i11 & 1024) != 0 ? true : z2, (i11 & 2048) != 0 ? true : z3, (i11 & 4096) != 0 ? true : z4, (i11 & 8192) != 0 ? true : z5, (i11 & 16384) != 0 ? true : z6, (i11 & 32768) != 0 ? true : z7, (i11 & 65536) != 0 ? true : z8, (i11 & 131072) != 0 ? true : z9, (i11 & 262144) != 0 ? 50 : i10);
    }
}
