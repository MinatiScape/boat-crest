package com.coveiot.android.bleabstract.models;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class DNDData implements Serializable {

    /* renamed from: a  reason: collision with root package name */
    public boolean f3422a;
    public int b;
    public int c;
    public int d;
    public int e;

    public DNDData() {
        this(false, 0, 0, 0, 0, 31, null);
    }

    public DNDData(boolean z, int i, int i2, int i3, int i4) {
        this.f3422a = z;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public static /* synthetic */ DNDData copy$default(DNDData dNDData, boolean z, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            z = dNDData.f3422a;
        }
        if ((i5 & 2) != 0) {
            i = dNDData.b;
        }
        int i6 = i;
        if ((i5 & 4) != 0) {
            i2 = dNDData.c;
        }
        int i7 = i2;
        if ((i5 & 8) != 0) {
            i3 = dNDData.d;
        }
        int i8 = i3;
        if ((i5 & 16) != 0) {
            i4 = dNDData.e;
        }
        return dNDData.copy(z, i6, i7, i8, i4);
    }

    public final boolean component1() {
        return this.f3422a;
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

    @NotNull
    public final DNDData copy(boolean z, int i, int i2, int i3, int i4) {
        return new DNDData(z, i, i2, i3, i4);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DNDData) {
            DNDData dNDData = (DNDData) obj;
            return this.f3422a == dNDData.f3422a && this.b == dNDData.b && this.c == dNDData.c && this.d == dNDData.d && this.e == dNDData.e;
        }
        return false;
    }

    public final int getEndHour() {
        return this.d;
    }

    public final int getEndMin() {
        return this.e;
    }

    public final int getStartHour() {
        return this.b;
    }

    public final int getStartMin() {
        return this.c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    public int hashCode() {
        boolean z = this.f3422a;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        return (((((((r0 * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e);
    }

    public final boolean isDNDEnabled() {
        return this.f3422a;
    }

    public final void setDNDEnabled(boolean z) {
        this.f3422a = z;
    }

    public final void setEndHour(int i) {
        this.d = i;
    }

    public final void setEndMin(int i) {
        this.e = i;
    }

    public final void setStartHour(int i) {
        this.b = i;
    }

    public final void setStartMin(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "DNDData(isDNDEnabled=" + this.f3422a + ", startHour=" + this.b + ", startMin=" + this.c + ", endHour=" + this.d + ", endMin=" + this.e + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ DNDData(boolean z, int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? false : z, (i5 & 2) != 0 ? 0 : i, (i5 & 4) != 0 ? 0 : i2, (i5 & 8) != 0 ? 0 : i3, (i5 & 16) != 0 ? 0 : i4);
    }
}
