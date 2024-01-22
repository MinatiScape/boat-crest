package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class Alarm implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3396a;
    public int b;
    public int c;
    public int d;
    public int e;
    @Nullable
    public String f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public int o;
    public int p;

    public Alarm() {
        this(false, 0, 0, 0, 0, null, false, false, false, false, false, false, false, false, 16383, null);
    }

    public Alarm(boolean z, int i, int i2, int i3, int i4, @Nullable String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
        this.f3396a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = str;
        this.g = z2;
        this.h = z3;
        this.i = z4;
        this.j = z5;
        this.k = z6;
        this.l = z7;
        this.m = z8;
        this.n = z9;
    }

    public final boolean component1() {
        return this.f3396a;
    }

    public final boolean component10() {
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

    @Nullable
    public final String component6() {
        return this.f;
    }

    public final boolean component7() {
        return this.g;
    }

    public final boolean component8() {
        return this.h;
    }

    public final boolean component9() {
        return this.i;
    }

    @NotNull
    public final Alarm copy(boolean z, int i, int i2, int i3, int i4, @Nullable String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9) {
        return new Alarm(z, i, i2, i3, i4, str, z2, z3, z4, z5, z6, z7, z8, z9);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Alarm) {
            Alarm alarm = (Alarm) obj;
            return this.f3396a == alarm.f3396a && this.b == alarm.b && this.c == alarm.c && this.d == alarm.d && this.e == alarm.e && Intrinsics.areEqual(this.f, alarm.f) && this.g == alarm.g && this.h == alarm.h && this.i == alarm.i && this.j == alarm.j && this.k == alarm.k && this.l == alarm.l && this.m == alarm.m && this.n == alarm.n;
        }
        return false;
    }

    public final int getAlarmId() {
        return this.b;
    }

    public final int getAlarmType() {
        return this.e;
    }

    @Nullable
    public final String getEventName() {
        return this.f;
    }

    public final int getHour() {
        return this.c;
    }

    public final int getMinute() {
        return this.d;
    }

    public final int getSnoozeDuration() {
        return this.o;
    }

    public final int getSnoozeRepeatTimes() {
        return this.p;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r2v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v13, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v17, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v21, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v23, types: [boolean] */
    public int hashCode() {
        boolean z = this.f3396a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int hashCode = ((((((((r0 * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31;
        String str = this.f;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        ?? r2 = this.g;
        int i = r2;
        if (r2 != 0) {
            i = 1;
        }
        int i2 = (hashCode2 + i) * 31;
        ?? r22 = this.h;
        int i3 = r22;
        if (r22 != 0) {
            i3 = 1;
        }
        int i4 = (i2 + i3) * 31;
        ?? r23 = this.i;
        int i5 = r23;
        if (r23 != 0) {
            i5 = 1;
        }
        int i6 = (i4 + i5) * 31;
        ?? r24 = this.j;
        int i7 = r24;
        if (r24 != 0) {
            i7 = 1;
        }
        int i8 = (i6 + i7) * 31;
        ?? r25 = this.k;
        int i9 = r25;
        if (r25 != 0) {
            i9 = 1;
        }
        int i10 = (i8 + i9) * 31;
        ?? r26 = this.l;
        int i11 = r26;
        if (r26 != 0) {
            i11 = 1;
        }
        int i12 = (i10 + i11) * 31;
        ?? r27 = this.m;
        int i13 = r27;
        if (r27 != 0) {
            i13 = 1;
        }
        int i14 = (i12 + i13) * 31;
        boolean z2 = this.n;
        return i14 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public final boolean isEnabled() {
        return this.f3396a;
    }

    public final boolean isFridayEnabled() {
        return this.m;
    }

    public final boolean isMondayEnabled() {
        return this.i;
    }

    public final boolean isRepeatEnabled() {
        return this.g;
    }

    public final boolean isSaturdayEnabled() {
        return this.n;
    }

    public final boolean isSundayEnabled() {
        return this.h;
    }

    public final boolean isThursdayEnabled() {
        return this.l;
    }

    public final boolean isTuesdayEnabled() {
        return this.j;
    }

    public final boolean isWednesdayEnabled() {
        return this.k;
    }

    public final void setAlarmId(int i) {
        this.b = i;
    }

    public final void setAlarmType(int i) {
        this.e = i;
    }

    public final void setEnabled(boolean z) {
        this.f3396a = z;
    }

    public final void setEventName(@Nullable String str) {
        this.f = str;
    }

    public final void setFridayEnabled(boolean z) {
        this.m = z;
    }

    public final void setHour(int i) {
        this.c = i;
    }

    public final void setMinute(int i) {
        this.d = i;
    }

    public final void setMondayEnabled(boolean z) {
        this.i = z;
    }

    public final void setRepeatEnabled(boolean z) {
        this.g = z;
    }

    public final void setSaturdayEnabled(boolean z) {
        this.n = z;
    }

    public final void setSnoozeDuration(int i) {
        this.o = i;
    }

    public final void setSnoozeRepeatTimes(int i) {
        this.p = i;
    }

    public final void setSundayEnabled(boolean z) {
        this.h = z;
    }

    public final void setThursdayEnabled(boolean z) {
        this.l = z;
    }

    public final void setTuesdayEnabled(boolean z) {
        this.j = z;
    }

    public final void setWednesdayEnabled(boolean z) {
        this.k = z;
    }

    @NotNull
    public String toString() {
        return "Alarm(isEnabled=" + this.f3396a + ", alarmId=" + this.b + ", hour=" + this.c + ", minute=" + this.d + ", alarmType=" + this.e + ", eventName=" + this.f + ", isRepeatEnabled=" + this.g + ", isSundayEnabled=" + this.h + ", isMondayEnabled=" + this.i + ", isTuesdayEnabled=" + this.j + ", isWednesdayEnabled=" + this.k + ", isThursdayEnabled=" + this.l + ", isFridayEnabled=" + this.m + ", isSaturdayEnabled=" + this.n + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ Alarm(boolean z, int i, int i2, int i3, int i4, String str, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? false : z, (i5 & 2) != 0 ? 0 : i, (i5 & 4) != 0 ? 0 : i2, (i5 & 8) != 0 ? 0 : i3, (i5 & 16) != 0 ? 1 : i4, (i5 & 32) != 0 ? null : str, (i5 & 64) != 0 ? false : z2, (i5 & 128) != 0 ? false : z3, (i5 & 256) != 0 ? false : z4, (i5 & 512) != 0 ? false : z5, (i5 & 1024) != 0 ? false : z6, (i5 & 2048) != 0 ? false : z7, (i5 & 4096) != 0 ? false : z8, (i5 & 8192) == 0 ? z9 : false);
    }
}
