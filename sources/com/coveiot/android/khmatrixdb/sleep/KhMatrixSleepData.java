package com.coveiot.android.khmatrixdb.sleep;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;
import com.coveiot.android.khmatrixdb.converter.SleepItemConverter;
import com.coveiot.android.khmatrixdb.converter.TimeConverter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Date;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", "mMacAddress"})
/* loaded from: classes3.dex */
public final class KhMatrixSleepData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4622a;
    public long b;
    public int c;
    public int d;
    public int e;
    @Nullable
    public String f;
    @TypeConverters({SleepItemConverter.class})
    @ColumnInfo(name = "sleepDetail")
    @Nullable
    public List<KhMatrixSleepItem> g;
    @ColumnInfo(name = "isProcessed")
    public boolean h;

    public KhMatrixSleepData(@NotNull String mMacAddress, long j, int i, int i2, int i3, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f4622a = mMacAddress;
        this.b = j;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = str;
        this.f = TimeConverter.fromDate(new Date(this.b));
    }

    public static /* synthetic */ KhMatrixSleepData copy$default(KhMatrixSleepData khMatrixSleepData, String str, long j, int i, int i2, int i3, String str2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = khMatrixSleepData.f4622a;
        }
        if ((i4 & 2) != 0) {
            j = khMatrixSleepData.b;
        }
        long j2 = j;
        if ((i4 & 4) != 0) {
            i = khMatrixSleepData.c;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = khMatrixSleepData.d;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = khMatrixSleepData.e;
        }
        int i7 = i3;
        if ((i4 & 32) != 0) {
            str2 = khMatrixSleepData.f;
        }
        return khMatrixSleepData.copy(str, j2, i5, i6, i7, str2);
    }

    @NotNull
    public final String component1() {
        return this.f4622a;
    }

    public final long component2() {
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

    @NotNull
    public final KhMatrixSleepData copy(@NotNull String mMacAddress, long j, int i, int i2, int i3, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhMatrixSleepData(mMacAddress, j, i, i2, i3, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhMatrixSleepData) {
            KhMatrixSleepData khMatrixSleepData = (KhMatrixSleepData) obj;
            return Intrinsics.areEqual(this.f4622a, khMatrixSleepData.f4622a) && this.b == khMatrixSleepData.b && this.c == khMatrixSleepData.c && this.d == khMatrixSleepData.d && this.e == khMatrixSleepData.e && Intrinsics.areEqual(this.f, khMatrixSleepData.f);
        }
        return false;
    }

    public final int getAwake() {
        return this.e;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.f;
    }

    public final int getDeepSleep() {
        return this.c;
    }

    public final int getLightSleep() {
        return this.d;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.f4622a;
    }

    @Nullable
    public final List<KhMatrixSleepItem> getMSleepDetail() {
        return this.g;
    }

    public final long getMTime() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = ((((((((this.f4622a.hashCode() * 31) + Long.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31;
        String str = this.f;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isProcessed() {
        return this.h;
    }

    public final void setAwake(int i) {
        this.e = i;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.f = str;
    }

    public final void setDeepSleep(int i) {
        this.c = i;
    }

    public final void setLightSleep(int i) {
        this.d = i;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4622a = str;
    }

    public final void setMSleepDetail(@Nullable List<KhMatrixSleepItem> list) {
        this.g = list;
    }

    public final void setMTime(long j) {
        this.b = j;
    }

    public final void setProcessed(boolean z) {
        this.h = z;
    }

    @NotNull
    public String toString() {
        return "KhMatrixSleepData(mMacAddress=" + this.f4622a + ", mTime=" + this.b + ", deepSleep=" + this.c + ", lightSleep=" + this.d + ", awake=" + this.e + ", convertedDate=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhMatrixSleepData(String str, long j, int i, int i2, int i3, String str2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i4 & 2) != 0 ? 0L : j, (i4 & 4) != 0 ? 0 : i, (i4 & 8) != 0 ? 0 : i2, (i4 & 16) == 0 ? i3 : 0, (i4 & 32) != 0 ? null : str2);
    }
}
