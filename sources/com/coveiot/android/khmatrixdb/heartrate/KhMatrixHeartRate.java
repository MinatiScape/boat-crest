package com.coveiot.android.khmatrixdb.heartrate;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.android.khmatrixdb.converter.TimeConverter;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Date;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", "mMacAddress"})
/* loaded from: classes3.dex */
public final class KhMatrixHeartRate {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4620a;
    public long b;
    public int c;
    @Nullable
    public String d;
    @ColumnInfo(name = "isProcessed")
    public boolean e;

    public KhMatrixHeartRate(@NotNull String mMacAddress, long j, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f4620a = mMacAddress;
        this.b = j;
        this.c = i;
        this.d = str;
        this.d = TimeConverter.fromDate(new Date(this.b));
    }

    public static /* synthetic */ KhMatrixHeartRate copy$default(KhMatrixHeartRate khMatrixHeartRate, String str, long j, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = khMatrixHeartRate.f4620a;
        }
        if ((i2 & 2) != 0) {
            j = khMatrixHeartRate.b;
        }
        long j2 = j;
        if ((i2 & 4) != 0) {
            i = khMatrixHeartRate.c;
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str2 = khMatrixHeartRate.d;
        }
        return khMatrixHeartRate.copy(str, j2, i3, str2);
    }

    @NotNull
    public final String component1() {
        return this.f4620a;
    }

    public final long component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
        return this.d;
    }

    @NotNull
    public final KhMatrixHeartRate copy(@NotNull String mMacAddress, long j, int i, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhMatrixHeartRate(mMacAddress, j, i, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhMatrixHeartRate) {
            KhMatrixHeartRate khMatrixHeartRate = (KhMatrixHeartRate) obj;
            return Intrinsics.areEqual(this.f4620a, khMatrixHeartRate.f4620a) && this.b == khMatrixHeartRate.b && this.c == khMatrixHeartRate.c && Intrinsics.areEqual(this.d, khMatrixHeartRate.d);
        }
        return false;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.d;
    }

    public final int getMBpm() {
        return this.c;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.f4620a;
    }

    public final long getMTime() {
        return this.b;
    }

    public int hashCode() {
        int hashCode = ((((this.f4620a.hashCode() * 31) + Long.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31;
        String str = this.d;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isProcessed() {
        return this.e;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.d = str;
    }

    public final void setMBpm(int i) {
        this.c = i;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4620a = str;
    }

    public final void setMTime(long j) {
        this.b = j;
    }

    public final void setProcessed(boolean z) {
        this.e = z;
    }

    @NotNull
    public String toString() {
        return "KhMatrixHeartRate(mMacAddress=" + this.f4620a + ", mTime=" + this.b + ", mBpm=" + this.c + ", convertedDate=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhMatrixHeartRate(String str, long j, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i2 & 2) != 0 ? 0L : j, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str2);
    }
}
