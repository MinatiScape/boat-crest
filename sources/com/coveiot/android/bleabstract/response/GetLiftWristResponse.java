package com.coveiot.android.bleabstract.response;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class GetLiftWristResponse implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3613a;
    public int b;
    public int c;
    public int d;
    public int e;

    public final int getEndHour() {
        return this.d;
    }

    public final int getEndMinute() {
        return this.e;
    }

    public final int getStartHour() {
        return this.b;
    }

    public final int getStartMinute() {
        return this.c;
    }

    public final boolean isLiftWristEnabled() {
        return this.f3613a;
    }

    public final void setEndHour(int i) {
        this.d = i;
    }

    public final void setEndMinute(int i) {
        this.e = i;
    }

    public final void setLiftWristEnabled(boolean z) {
        this.f3613a = z;
    }

    public final void setStartHour(int i) {
        this.b = i;
    }

    public final void setStartMinute(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "GetLiftWristResponse(isLiftWristEnabled=" + this.f3613a + ", startHour=" + this.b + ", startMinute=" + this.c + ", endHour=" + this.d + ", endMinute=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }
}
