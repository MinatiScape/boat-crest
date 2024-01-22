package com.coveiot.android.sleepenergyscore.energymeter.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyMeterInsightModel {

    /* renamed from: a  reason: collision with root package name */
    public int f5721a;
    public boolean b;

    public EnergyMeterInsightModel(int i, boolean z) {
        this.f5721a = i;
        this.b = z;
    }

    public static /* synthetic */ EnergyMeterInsightModel copy$default(EnergyMeterInsightModel energyMeterInsightModel, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = energyMeterInsightModel.f5721a;
        }
        if ((i2 & 2) != 0) {
            z = energyMeterInsightModel.b;
        }
        return energyMeterInsightModel.copy(i, z);
    }

    public final int component1() {
        return this.f5721a;
    }

    public final boolean component2() {
        return this.b;
    }

    @NotNull
    public final EnergyMeterInsightModel copy(int i, boolean z) {
        return new EnergyMeterInsightModel(i, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyMeterInsightModel) {
            EnergyMeterInsightModel energyMeterInsightModel = (EnergyMeterInsightModel) obj;
            return this.f5721a == energyMeterInsightModel.f5721a && this.b == energyMeterInsightModel.b;
        }
        return false;
    }

    public final int getEnergyDifference() {
        return this.f5721a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = Integer.hashCode(this.f5721a) * 31;
        boolean z = this.b;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode + i;
    }

    public final boolean isEnergyIncreased() {
        return this.b;
    }

    public final void setEnergyDifference(int i) {
        this.f5721a = i;
    }

    public final void setEnergyIncreased(boolean z) {
        this.b = z;
    }

    @NotNull
    public String toString() {
        return "EnergyMeterInsightModel(energyDifference=" + this.f5721a + ", isEnergyIncreased=" + this.b + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EnergyMeterInsightModel(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? false : z);
    }
}
