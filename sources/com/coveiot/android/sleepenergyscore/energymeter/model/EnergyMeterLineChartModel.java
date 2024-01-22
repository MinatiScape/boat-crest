package com.coveiot.android.sleepenergyscore.energymeter.model;

import com.github.mikephil.charting.data.Entry;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyMeterLineChartModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<Entry> f5722a;
    @Nullable
    public ArrayList<String> b;
    public int c;
    public int d;

    public EnergyMeterLineChartModel() {
        this(null, null, 0, 0, 15, null);
    }

    public EnergyMeterLineChartModel(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2, int i, int i2) {
        this.f5722a = arrayList;
        this.b = arrayList2;
        this.c = i;
        this.d = i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EnergyMeterLineChartModel copy$default(EnergyMeterLineChartModel energyMeterLineChartModel, ArrayList arrayList, ArrayList arrayList2, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            arrayList = energyMeterLineChartModel.f5722a;
        }
        if ((i3 & 2) != 0) {
            arrayList2 = energyMeterLineChartModel.b;
        }
        if ((i3 & 4) != 0) {
            i = energyMeterLineChartModel.c;
        }
        if ((i3 & 8) != 0) {
            i2 = energyMeterLineChartModel.d;
        }
        return energyMeterLineChartModel.copy(arrayList, arrayList2, i, i2);
    }

    @Nullable
    public final ArrayList<Entry> component1() {
        return this.f5722a;
    }

    @Nullable
    public final ArrayList<String> component2() {
        return this.b;
    }

    public final int component3() {
        return this.c;
    }

    public final int component4() {
        return this.d;
    }

    @NotNull
    public final EnergyMeterLineChartModel copy(@Nullable ArrayList<Entry> arrayList, @Nullable ArrayList<String> arrayList2, int i, int i2) {
        return new EnergyMeterLineChartModel(arrayList, arrayList2, i, i2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EnergyMeterLineChartModel) {
            EnergyMeterLineChartModel energyMeterLineChartModel = (EnergyMeterLineChartModel) obj;
            return Intrinsics.areEqual(this.f5722a, energyMeterLineChartModel.f5722a) && Intrinsics.areEqual(this.b, energyMeterLineChartModel.b) && this.c == energyMeterLineChartModel.c && this.d == energyMeterLineChartModel.d;
        }
        return false;
    }

    @Nullable
    public final ArrayList<Entry> getBarEntries() {
        return this.f5722a;
    }

    public final int getCurrentEnergyLevel() {
        return this.d;
    }

    @Nullable
    public final ArrayList<String> getLables() {
        return this.b;
    }

    public final int getStartEnergyLevel() {
        return this.c;
    }

    public int hashCode() {
        ArrayList<Entry> arrayList = this.f5722a;
        int hashCode = (arrayList == null ? 0 : arrayList.hashCode()) * 31;
        ArrayList<String> arrayList2 = this.b;
        return ((((hashCode + (arrayList2 != null ? arrayList2.hashCode() : 0)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d);
    }

    public final void setBarEntries(@Nullable ArrayList<Entry> arrayList) {
        this.f5722a = arrayList;
    }

    public final void setCurrentEnergyLevel(int i) {
        this.d = i;
    }

    public final void setLables(@Nullable ArrayList<String> arrayList) {
        this.b = arrayList;
    }

    public final void setStartEnergyLevel(int i) {
        this.c = i;
    }

    @NotNull
    public String toString() {
        return "EnergyMeterLineChartModel(barEntries=" + this.f5722a + ", lables=" + this.b + ", startEnergyLevel=" + this.c + ", currentEnergyLevel=" + this.d + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EnergyMeterLineChartModel(ArrayList arrayList, ArrayList arrayList2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : arrayList, (i3 & 2) != 0 ? null : arrayList2, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }
}
