package com.coveiot.khsmadb.location;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", DeviceKey.MacAddress})
/* loaded from: classes8.dex */
public final class KhLocation {

    /* renamed from: a  reason: collision with root package name */
    public int f7148a;
    public int b;
    public int c;
    public float d;
    public float e;
    @NotNull
    public String f;
    @ColumnInfo(name = "isProcessed")
    public boolean g;

    public KhLocation(int i, int i2, int i3, float f, float f2, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        this.f7148a = i;
        this.b = i2;
        this.c = i3;
        this.d = f;
        this.e = f2;
        this.f = macAddress;
    }

    public static /* synthetic */ KhLocation copy$default(KhLocation khLocation, int i, int i2, int i3, float f, float f2, String str, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = khLocation.f7148a;
        }
        if ((i4 & 2) != 0) {
            i2 = khLocation.b;
        }
        int i5 = i2;
        if ((i4 & 4) != 0) {
            i3 = khLocation.c;
        }
        int i6 = i3;
        if ((i4 & 8) != 0) {
            f = khLocation.d;
        }
        float f3 = f;
        if ((i4 & 16) != 0) {
            f2 = khLocation.e;
        }
        float f4 = f2;
        if ((i4 & 32) != 0) {
            str = khLocation.f;
        }
        return khLocation.copy(i, i5, i6, f3, f4, str);
    }

    public final int component1() {
        return this.f7148a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final float component4() {
        return this.d;
    }

    public final float component5() {
        return this.e;
    }

    @NotNull
    public final String component6() {
        return this.f;
    }

    @NotNull
    public final KhLocation copy(int i, int i2, int i3, float f, float f2, @NotNull String macAddress) {
        Intrinsics.checkNotNullParameter(macAddress, "macAddress");
        return new KhLocation(i, i2, i3, f, f2, macAddress);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhLocation) {
            KhLocation khLocation = (KhLocation) obj;
            return this.f7148a == khLocation.f7148a && this.b == khLocation.b && this.c == khLocation.c && Float.compare(this.d, khLocation.d) == 0 && Float.compare(this.e, khLocation.e) == 0 && Intrinsics.areEqual(this.f, khLocation.f);
        }
        return false;
    }

    @NotNull
    public final String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, this.f7148a);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    @NotNull
    public final String getDatetime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, this.f7148a);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    public final int getMActivityMode() {
        return this.b;
    }

    public final int getMAltitude() {
        return this.c;
    }

    public final float getMLatitude() {
        return this.e;
    }

    public final float getMLongitude() {
        return this.d;
    }

    public final int getMTime() {
        return this.f7148a;
    }

    @NotNull
    public final String getMacAddress() {
        return this.f;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.f7148a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Float.hashCode(this.d)) * 31) + Float.hashCode(this.e)) * 31) + this.f.hashCode();
    }

    public final boolean isProcessed() {
        return this.g;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
    }

    public final void setDatetime(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
    }

    public final void setMActivityMode(int i) {
        this.b = i;
    }

    public final void setMAltitude(int i) {
        this.c = i;
    }

    public final void setMLatitude(float f) {
        this.e = f;
    }

    public final void setMLongitude(float f) {
        this.d = f;
    }

    public final void setMTime(int i) {
        this.f7148a = i;
    }

    public final void setMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f = str;
    }

    public final void setProcessed(boolean z) {
        this.g = z;
    }

    @NotNull
    public String toString() {
        return "KhLocation(mTime=" + this.f7148a + ", mActivityMode=" + this.b + ", mAltitude=" + this.c + ", mLongitude=" + this.d + ", mLatitude=" + this.e + ", macAddress=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhLocation(int i, int i2, int i3, float f, float f2, String str, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3, (i4 & 8) != 0 ? 0.0f : f, (i4 & 16) != 0 ? 0.0f : f2, str);
    }
}
