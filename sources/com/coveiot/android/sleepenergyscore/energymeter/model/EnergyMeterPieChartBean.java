package com.coveiot.android.sleepenergyscore.energymeter.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyMeterPieChartBean {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public EnergyMeterFactorType f5723a;
    @Nullable
    public String b;
    public int c;
    @Nullable
    public String d;

    public EnergyMeterPieChartBean() {
        this(null, null, 0, null, 15, null);
    }

    public EnergyMeterPieChartBean(@Nullable EnergyMeterFactorType energyMeterFactorType, @Nullable String str, int i, @Nullable String str2) {
        this.f5723a = energyMeterFactorType;
        this.b = str;
        this.c = i;
        this.d = str2;
    }

    public static /* synthetic */ EnergyMeterPieChartBean copy$default(EnergyMeterPieChartBean energyMeterPieChartBean, EnergyMeterFactorType energyMeterFactorType, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            energyMeterFactorType = energyMeterPieChartBean.f5723a;
        }
        if ((i2 & 2) != 0) {
            str = energyMeterPieChartBean.b;
        }
        if ((i2 & 4) != 0) {
            i = energyMeterPieChartBean.c;
        }
        if ((i2 & 8) != 0) {
            str2 = energyMeterPieChartBean.d;
        }
        return energyMeterPieChartBean.copy(energyMeterFactorType, str, i, str2);
    }

    @Nullable
    public final EnergyMeterFactorType component1() {
        return this.f5723a;
    }

    @Nullable
    public final String component2() {
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
    public final EnergyMeterPieChartBean copy(@Nullable EnergyMeterFactorType energyMeterFactorType, @Nullable String str, int i, @Nullable String str2) {
        return new EnergyMeterPieChartBean(energyMeterFactorType, str, i, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyMeterPieChartBean) {
            EnergyMeterPieChartBean energyMeterPieChartBean = (EnergyMeterPieChartBean) obj;
            return this.f5723a == energyMeterPieChartBean.f5723a && Intrinsics.areEqual(this.b, energyMeterPieChartBean.b) && this.c == energyMeterPieChartBean.c && Intrinsics.areEqual(this.d, energyMeterPieChartBean.d);
        }
        return false;
    }

    public final int getContribution() {
        return this.c;
    }

    @Nullable
    public final String getName() {
        return this.b;
    }

    @Nullable
    public final EnergyMeterFactorType getType() {
        return this.f5723a;
    }

    @Nullable
    public final String getValue() {
        return this.d;
    }

    public int hashCode() {
        EnergyMeterFactorType energyMeterFactorType = this.f5723a;
        int hashCode = (energyMeterFactorType == null ? 0 : energyMeterFactorType.hashCode()) * 31;
        String str = this.b;
        int hashCode2 = (((hashCode + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.c)) * 31;
        String str2 = this.d;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public final void setContribution(int i) {
        this.c = i;
    }

    public final void setName(@Nullable String str) {
        this.b = str;
    }

    public final void setType(@Nullable EnergyMeterFactorType energyMeterFactorType) {
        this.f5723a = energyMeterFactorType;
    }

    public final void setValue(@Nullable String str) {
        this.d = str;
    }

    @NotNull
    public String toString() {
        return "EnergyMeterPieChartBean(type=" + this.f5723a + ", name=" + this.b + ", contribution=" + this.c + ", value=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EnergyMeterPieChartBean(EnergyMeterFactorType energyMeterFactorType, String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : energyMeterFactorType, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? null : str2);
    }
}
