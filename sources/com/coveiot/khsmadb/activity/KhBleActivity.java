package com.coveiot.khsmadb.activity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.khsmadb.Utils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", "mMacAddress"})
/* loaded from: classes8.dex */
public final class KhBleActivity {

    /* renamed from: a  reason: collision with root package name */
    public int f7138a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    @NotNull
    public String g;
    @ColumnInfo(name = "isReadyToProcess")
    public boolean h;
    @ColumnInfo(name = "isProcessed")
    public boolean i;
    @ColumnInfo(name = "isProcessedInWorkout")
    public boolean j;

    public KhBleActivity(int i, int i2, int i3, int i4, int i5, int i6, @NotNull String mMacAddress) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f7138a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = mMacAddress;
        Utils utils = Utils.INSTANCE;
        Intrinsics.checkNotNull(utils.convertSDKTime(i));
        Intrinsics.checkNotNull(utils.convertSDKTimeToDate(this.f7138a));
    }

    public static /* synthetic */ KhBleActivity copy$default(KhBleActivity khBleActivity, int i, int i2, int i3, int i4, int i5, int i6, String str, int i7, Object obj) {
        if ((i7 & 1) != 0) {
            i = khBleActivity.f7138a;
        }
        if ((i7 & 2) != 0) {
            i2 = khBleActivity.b;
        }
        int i8 = i2;
        if ((i7 & 4) != 0) {
            i3 = khBleActivity.c;
        }
        int i9 = i3;
        if ((i7 & 8) != 0) {
            i4 = khBleActivity.d;
        }
        int i10 = i4;
        if ((i7 & 16) != 0) {
            i5 = khBleActivity.e;
        }
        int i11 = i5;
        if ((i7 & 32) != 0) {
            i6 = khBleActivity.f;
        }
        int i12 = i6;
        if ((i7 & 64) != 0) {
            str = khBleActivity.g;
        }
        return khBleActivity.copy(i, i8, i9, i10, i11, i12, str);
    }

    public final int component1() {
        return this.f7138a;
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

    @NotNull
    public final String component7() {
        return this.g;
    }

    @NotNull
    public final KhBleActivity copy(int i, int i2, int i3, int i4, int i5, int i6, @NotNull String mMacAddress) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhBleActivity(i, i2, i3, i4, i5, i6, mMacAddress);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhBleActivity) {
            KhBleActivity khBleActivity = (KhBleActivity) obj;
            return this.f7138a == khBleActivity.f7138a && this.b == khBleActivity.b && this.c == khBleActivity.c && this.d == khBleActivity.d && this.e == khBleActivity.e && this.f == khBleActivity.f && Intrinsics.areEqual(this.g, khBleActivity.g);
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
        calendar.add(13, this.f7138a);
        String format = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    public final int getMCalorie() {
        return this.e;
    }

    public final int getMDistance() {
        return this.f;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.g;
    }

    public final int getMMode() {
        return this.b;
    }

    public final int getMState() {
        return this.c;
    }

    public final int getMStep() {
        return this.d;
    }

    public final int getMTime() {
        return this.f7138a;
    }

    @NotNull
    public final String getTimeStamp() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 2000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(13, this.f7138a);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        Intrinsics.checkNotNullExpressionValue(format, "df.format(calendar.time)");
        return format;
    }

    public int hashCode() {
        return (((((((((((Integer.hashCode(this.f7138a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + this.g.hashCode();
    }

    public final boolean isProcessed() {
        return this.i;
    }

    public final boolean isProcessedInWorkout() {
        return this.j;
    }

    public final boolean isReadyToProcess() {
        return this.h;
    }

    public final void setDate(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
    }

    public final void setMCalorie(int i) {
        this.e = i;
    }

    public final void setMDistance(int i) {
        this.f = i;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.g = str;
    }

    public final void setMMode(int i) {
        this.b = i;
    }

    public final void setMState(int i) {
        this.c = i;
    }

    public final void setMStep(int i) {
        this.d = i;
    }

    public final void setMTime(int i) {
        this.f7138a = i;
    }

    public final void setProcessed(boolean z) {
        this.i = z;
    }

    public final void setProcessedInWorkout(boolean z) {
        this.j = z;
    }

    public final void setReadyToProcess(boolean z) {
        this.h = z;
    }

    public final void setTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
    }

    @NotNull
    public String toString() {
        return "KhBleActivity(mTime=" + this.f7138a + ", mMode=" + this.b + ", mState=" + this.c + ", mStep=" + this.d + ", mCalorie=" + this.e + ", mDistance=" + this.f + ", mMacAddress='" + this.g + "', isProcessed=" + this.i + ", isProcessedInWorkout=" + this.j + ", isReadyToProcess=" + this.h + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhBleActivity(int i, int i2, int i3, int i4, int i5, int i6, String str, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? 0 : i2, (i7 & 4) != 0 ? 0 : i3, (i7 & 8) != 0 ? 0 : i4, (i7 & 16) != 0 ? 0 : i5, (i7 & 32) != 0 ? 0 : i6, str);
    }
}
