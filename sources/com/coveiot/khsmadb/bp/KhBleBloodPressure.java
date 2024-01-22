package com.coveiot.khsmadb.bp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.coveiot.khsmadb.Utils;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Entity(primaryKeys = {"mTime", "mMacAddress"})
/* loaded from: classes8.dex */
public final class KhBleBloodPressure {

    /* renamed from: a  reason: collision with root package name */
    public int f7139a;
    public int b;
    public int c;
    @NotNull
    public String d;
    @Nullable
    public String e;
    @ColumnInfo(name = "isProcessed")
    public boolean f;
    @ColumnInfo(name = "Timestamp")
    public String timeStamp;

    public KhBleBloodPressure(int i, int i2, int i3, @NotNull String mMacAddress, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        this.f7139a = i;
        this.b = i2;
        this.c = i3;
        this.d = mMacAddress;
        this.e = str;
        Utils utils = Utils.INSTANCE;
        this.e = utils.convertSDKTimeToDate(i);
        String convertSDKTime = utils.convertSDKTime(this.f7139a);
        Intrinsics.checkNotNull(convertSDKTime);
        setTimeStamp(convertSDKTime);
    }

    public static /* synthetic */ KhBleBloodPressure copy$default(KhBleBloodPressure khBleBloodPressure, int i, int i2, int i3, String str, String str2, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = khBleBloodPressure.f7139a;
        }
        if ((i4 & 2) != 0) {
            i2 = khBleBloodPressure.b;
        }
        int i5 = i2;
        if ((i4 & 4) != 0) {
            i3 = khBleBloodPressure.c;
        }
        int i6 = i3;
        if ((i4 & 8) != 0) {
            str = khBleBloodPressure.d;
        }
        String str3 = str;
        if ((i4 & 16) != 0) {
            str2 = khBleBloodPressure.e;
        }
        return khBleBloodPressure.copy(i, i5, i6, str3, str2);
    }

    public final int component1() {
        return this.f7139a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    @NotNull
    public final String component4() {
        return this.d;
    }

    @Nullable
    public final String component5() {
        return this.e;
    }

    @NotNull
    public final KhBleBloodPressure copy(int i, int i2, int i3, @NotNull String mMacAddress, @Nullable String str) {
        Intrinsics.checkNotNullParameter(mMacAddress, "mMacAddress");
        return new KhBleBloodPressure(i, i2, i3, mMacAddress, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof KhBleBloodPressure) {
            KhBleBloodPressure khBleBloodPressure = (KhBleBloodPressure) obj;
            return this.f7139a == khBleBloodPressure.f7139a && this.b == khBleBloodPressure.b && this.c == khBleBloodPressure.c && Intrinsics.areEqual(this.d, khBleBloodPressure.d) && Intrinsics.areEqual(this.e, khBleBloodPressure.e);
        }
        return false;
    }

    @Nullable
    public final String getConvertedDate() {
        return this.e;
    }

    public final int getMDiastolic() {
        return this.c;
    }

    @NotNull
    public final String getMMacAddress() {
        return this.d;
    }

    public final int getMSystolic() {
        return this.b;
    }

    public final int getMTime() {
        return this.f7139a;
    }

    @NotNull
    public final String getTimeStamp() {
        String str = this.timeStamp;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("timeStamp");
        return null;
    }

    public int hashCode() {
        int hashCode = ((((((Integer.hashCode(this.f7139a) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + this.d.hashCode()) * 31;
        String str = this.e;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public final boolean isProcessed() {
        return this.f;
    }

    public final void setConvertedDate(@Nullable String str) {
        this.e = str;
    }

    public final void setMDiastolic(int i) {
        this.c = i;
    }

    public final void setMMacAddress(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.d = str;
    }

    public final void setMSystolic(int i) {
        this.b = i;
    }

    public final void setMTime(int i) {
        this.f7139a = i;
    }

    public final void setProcessed(boolean z) {
        this.f = z;
    }

    public final void setTimeStamp(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.timeStamp = str;
    }

    @NotNull
    public String toString() {
        return "KhBleBloodPressure(mTime=" + this.f7139a + ", mSystolic=" + this.b + ", mDiastolic=" + this.c + ", mMacAddress='" + this.d + "', convertedDate=" + this.e + ", isProcessed=" + this.f + ", timeStamp='" + getTimeStamp() + "')";
    }

    public /* synthetic */ KhBleBloodPressure(int i, int i2, int i3, String str, String str2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0 : i, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3, str, (i4 & 16) != 0 ? null : str2);
    }
}
