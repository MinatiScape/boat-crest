package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class EventReminder implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public int f3428a;
    @Nullable
    public String b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;

    public EventReminder() {
        this(0, null, 0, 0, 0, 0, 0, 127, null);
    }

    public EventReminder(int i, @Nullable String str, int i2, int i3, int i4, int i5, int i6) {
        this.f3428a = i;
        this.b = str;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = i6;
    }

    public static /* synthetic */ EventReminder copy$default(EventReminder eventReminder, int i, String str, int i2, int i3, int i4, int i5, int i6, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i = eventReminder.f3428a;
        }
        if ((i7 & 2) != 0) {
            str = eventReminder.b;
        }
        String str2 = str;
        if ((i7 & 4) != 0) {
            i2 = eventReminder.c;
        }
        int i8 = i2;
        if ((i7 & 8) != 0) {
            i3 = eventReminder.d;
        }
        int i9 = i3;
        if ((i7 & 16) != 0) {
            i4 = eventReminder.e;
        }
        int i10 = i4;
        if ((i7 & 32) != 0) {
            i5 = eventReminder.f;
        }
        int i11 = i5;
        if ((i7 & 64) != 0) {
            i6 = eventReminder.g;
        }
        return eventReminder.copy(i, str2, i8, i9, i10, i11, i6);
    }

    public final int component1() {
        return this.f3428a;
    }

    @Nullable
    public final String component2() {
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

    @NotNull
    public final EventReminder copy(int i, @Nullable String str, int i2, int i3, int i4, int i5, int i6) {
        return new EventReminder(i, str, i2, i3, i4, i5, i6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EventReminder) {
            EventReminder eventReminder = (EventReminder) obj;
            return this.f3428a == eventReminder.f3428a && Intrinsics.areEqual(this.b, eventReminder.b) && this.c == eventReminder.c && this.d == eventReminder.d && this.e == eventReminder.e && this.f == eventReminder.f && this.g == eventReminder.g;
        }
        return false;
    }

    public final int getDay() {
        return this.e;
    }

    public final int getEventId() {
        return this.f3428a;
    }

    @Nullable
    public final String getEventName() {
        return this.b;
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
        return this.h;
    }

    public final int getType() {
        return this.i;
    }

    public final int getYear() {
        return this.c;
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.f3428a) * 31;
        String str = this.b;
        return ((((((((((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Integer.hashCode(this.g);
    }

    public final void setDay(int i) {
        this.e = i;
    }

    public final void setEventId(int i) {
        this.f3428a = i;
    }

    public final void setEventName(@Nullable String str) {
        this.b = str;
    }

    public final void setHour(int i) {
        this.f = i;
    }

    public final void setMinute(int i) {
        this.g = i;
    }

    public final void setMonth(int i) {
        this.d = i;
    }

    public final void setRepeatType(int i) {
        this.h = i;
    }

    public final void setType(int i) {
        this.i = i;
    }

    public final void setYear(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "EventReminder(eventId=" + this.f3428a + ", eventName=" + this.b + ", year=" + this.c + ", month=" + this.d + ", day=" + this.e + ", hour=" + this.f + ", minute=" + this.g + ", repeatType=" + this.h + ", type=" + this.i + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EventReminder(int i, String str, int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? null : str, (i7 & 4) != 0 ? 0 : i2, (i7 & 8) != 0 ? 0 : i3, (i7 & 16) != 0 ? 0 : i4, (i7 & 32) != 0 ? 0 : i5, (i7 & 64) != 0 ? 0 : i6);
    }
}
