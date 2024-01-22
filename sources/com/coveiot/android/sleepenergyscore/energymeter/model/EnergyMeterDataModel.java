package com.coveiot.android.sleepenergyscore.energymeter.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyMeterDataModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public String f5720a;
    public int b;
    public int c;
    @Nullable
    public String d;
    public int e;
    @Nullable
    public String f;

    public EnergyMeterDataModel() {
        this(null, 0, 0, null, 0, null, 63, null);
    }

    public EnergyMeterDataModel(@Nullable String str, int i, int i2, @Nullable String str2, int i3, @Nullable String str3) {
        this.f5720a = str;
        this.b = i;
        this.c = i2;
        this.d = str2;
        this.e = i3;
        this.f = str3;
    }

    public static /* synthetic */ EnergyMeterDataModel copy$default(EnergyMeterDataModel energyMeterDataModel, String str, int i, int i2, String str2, int i3, String str3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = energyMeterDataModel.f5720a;
        }
        if ((i4 & 2) != 0) {
            i = energyMeterDataModel.b;
        }
        int i5 = i;
        if ((i4 & 4) != 0) {
            i2 = energyMeterDataModel.c;
        }
        int i6 = i2;
        if ((i4 & 8) != 0) {
            str2 = energyMeterDataModel.d;
        }
        String str4 = str2;
        if ((i4 & 16) != 0) {
            i3 = energyMeterDataModel.e;
        }
        int i7 = i3;
        if ((i4 & 32) != 0) {
            str3 = energyMeterDataModel.f;
        }
        return energyMeterDataModel.copy(str, i5, i6, str4, i7, str3);
    }

    @Nullable
    public final String component1() {
        return this.f5720a;
    }

    public final int component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    @Nullable
    public final String component4() {
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
    public final EnergyMeterDataModel copy(@Nullable String str, int i, int i2, @Nullable String str2, int i3, @Nullable String str3) {
        return new EnergyMeterDataModel(str, i, i2, str2, i3, str3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyMeterDataModel) {
            EnergyMeterDataModel energyMeterDataModel = (EnergyMeterDataModel) obj;
            return Intrinsics.areEqual(this.f5720a, energyMeterDataModel.f5720a) && this.b == energyMeterDataModel.b && this.c == energyMeterDataModel.c && Intrinsics.areEqual(this.d, energyMeterDataModel.d) && this.e == energyMeterDataModel.e && Intrinsics.areEqual(this.f, energyMeterDataModel.f);
        }
        return false;
    }

    @Nullable
    public final String getDwmValue() {
        return this.f5720a;
    }

    public final int getEnergyScore() {
        return this.e;
    }

    public final int getEnergyScoreMax() {
        return this.c;
    }

    public final int getEnergyScoreMin() {
        return this.b;
    }

    @Nullable
    public final String getHours() {
        return this.f;
    }

    @Nullable
    public final String getType() {
        return this.d;
    }

    public int hashCode() {
        String str = this.f5720a;
        int hashCode = (((((str == null ? 0 : str.hashCode()) * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31;
        String str2 = this.d;
        int hashCode2 = (((hashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + Integer.hashCode(this.e)) * 31;
        String str3 = this.f;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public final void setDwmValue(@Nullable String str) {
        this.f5720a = str;
    }

    public final void setEnergyScore(int i) {
        this.e = i;
    }

    public final void setEnergyScoreMax(int i) {
        this.c = i;
    }

    public final void setEnergyScoreMin(int i) {
        this.b = i;
    }

    public final void setHours(@Nullable String str) {
        this.f = str;
    }

    public final void setType(@Nullable String str) {
        this.d = str;
    }

    @NotNull
    public String toString() {
        return "EnergyMeterDataModel(dwmValue=" + this.f5720a + ", energyScoreMin=" + this.b + ", energyScoreMax=" + this.c + ", type=" + this.d + ", energyScore=" + this.e + ", hours=" + this.f + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EnergyMeterDataModel(String str, int i, int i2, String str2, int i3, String str3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? null : str, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? null : str2, (i4 & 16) == 0 ? i3 : 0, (i4 & 32) != 0 ? null : str3);
    }
}
