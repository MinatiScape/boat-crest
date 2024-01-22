package com.coveiot.android.khmatrixdb.workout;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.TypeConverters;
import com.coveiot.android.khmatrixdb.converter.SportItemConverter;
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
public final class KhMatrixSportData {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public String f4634a;
    @NotNull
    public String b;
    public long c;
    public int d;
    public int e;
    public int f;
    public float g;
    public float h;
    @Nullable
    public String i;
    @TypeConverters({SportItemConverter.class})
    @ColumnInfo(name = "sportItemDetails")
    @Nullable
    public List<KhMatrixSportItemData> j;
    @ColumnInfo(name = "isProcessed")
    public boolean k;

    public KhMatrixSportData(@NotNull String sportId, @NotNull String mMacAddress, long j, int i, int i2, int i3, float f, float f2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(sportId, "sportId");
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f4634a = sportId;
        this.b = mMacAddress;
        this.c = j;
        this.d = i;
        this.e = i2;
        this.f = i3;
        this.g = f;
        this.h = f2;
        this.i = str;
        this.i = TimeConverter.fromDate(new Date(this.c));
    }

    @NotNull
    public final String component1() {
        return this.f4634a;
    }

    @NotNull
    public final String component2() {
        return this.b;
    }

    public final long component3() {
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

    public final float component7() {
        return this.g;
    }

    public final float component8() {
        return this.h;
    }

    @Nullable
    public final String component9() {
        return this.i;
    }

    @NotNull
    public final KhMatrixSportData copy(@NotNull String sportId, @NotNull String mMacAddress, long j, int i, int i2, int i3, float f, float f2, @Nullable String str) {
        Intrinsics.checkNotNullParameter(sportId, "sportId");
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhMatrixSportData(sportId, mMacAddress, j, i, i2, i3, f, f2, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhMatrixSportData) {
            KhMatrixSportData khMatrixSportData = (KhMatrixSportData) obj;
            return Intrinsics.areEqual(this.f4634a, khMatrixSportData.f4634a) && Intrinsics.areEqual(this.b, khMatrixSportData.b) && this.c == khMatrixSportData.c && this.d == khMatrixSportData.d && this.e == khMatrixSportData.e && this.f == khMatrixSportData.f && Float.compare(this.g, khMatrixSportData.g) == 0 && Float.compare(this.h, khMatrixSportData.h) == 0 && Intrinsics.areEqual(this.i, khMatrixSportData.i);
        }
        return false;
    }

    public final float getCalories() {
        return this.h;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.i;
    }

    public final float getDistance() {
        return this.g;
    }

    public final int getDuration() {
        return this.f;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.b;
    }

    @Nullable
    public final List<KhMatrixSportItemData> getMSportItemDetails() {
        return this.j;
    }

    public final long getMTime() {
        return this.c;
    }

    @NotNull
    public final String getSportId() {
        return this.f4634a;
    }

    public final int getSportType() {
        return this.d;
    }

    public final int getStep() {
        return this.e;
    }

    public int hashCode() {
        int hashCode = ((((((((((((((this.f4634a.hashCode() * 31) + this.b.hashCode()) * 31) + Long.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Integer.hashCode(this.e)) * 31) + Integer.hashCode(this.f)) * 31) + Float.hashCode(this.g)) * 31) + Float.hashCode(this.h)) * 31;
        String str = this.i;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isProcessed() {
        return this.k;
    }

    public final void setCalories(float f) {
        this.h = f;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.i = str;
    }

    public final void setDistance(float f) {
        this.g = f;
    }

    public final void setDuration(int i) {
        this.f = i;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.b = str;
    }

    public final void setMSportItemDetails(@Nullable List<KhMatrixSportItemData> list) {
        this.j = list;
    }

    public final void setMTime(long j) {
        this.c = j;
    }

    public final void setProcessed(boolean z) {
        this.k = z;
    }

    public final void setSportId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.f4634a = str;
    }

    public final void setSportType(int i) {
        this.d = i;
    }

    public final void setStep(int i) {
        this.e = i;
    }

    @NotNull
    public String toString() {
        return "KhMatrixSportData(sportId=" + this.f4634a + ", mMacAddress=" + this.b + ", mTime=" + this.c + ", sportType=" + this.d + ", step=" + this.e + ", duration=" + this.f + ", distance=" + this.g + ", calories=" + this.h + ", convertedDate=" + this.i + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ KhMatrixSportData(String str, String str2, long j, int i, int i2, int i3, float f, float f2, String str3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i4 & 4) != 0 ? 0L : j, (i4 & 8) != 0 ? 0 : i, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? 0 : i3, (i4 & 64) != 0 ? 0.0f : f, (i4 & 128) != 0 ? 0.0f : f2, (i4 & 256) != 0 ? null : str3);
    }
}
