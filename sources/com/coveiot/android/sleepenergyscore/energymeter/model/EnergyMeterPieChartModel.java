package com.coveiot.android.sleepenergyscore.energymeter.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class EnergyMeterPieChartModel {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<EnergyMeterPieChartBean> f5724a;

    public EnergyMeterPieChartModel() {
        this(null, 1, null);
    }

    public EnergyMeterPieChartModel(@Nullable List<EnergyMeterPieChartBean> list) {
        this.f5724a = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EnergyMeterPieChartModel copy$default(EnergyMeterPieChartModel energyMeterPieChartModel, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = energyMeterPieChartModel.f5724a;
        }
        return energyMeterPieChartModel.copy(list);
    }

    @Nullable
    public final List<EnergyMeterPieChartBean> component1() {
        return this.f5724a;
    }

    @NotNull
    public final EnergyMeterPieChartModel copy(@Nullable List<EnergyMeterPieChartBean> list) {
        return new EnergyMeterPieChartModel(list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof EnergyMeterPieChartModel) && Intrinsics.areEqual(this.f5724a, ((EnergyMeterPieChartModel) obj).f5724a);
    }

    @Nullable
    public final List<EnergyMeterPieChartBean> getPieChartModels() {
        return this.f5724a;
    }

    public int hashCode() {
        List<EnergyMeterPieChartBean> list = this.f5724a;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public final void setPieChartModels(@Nullable List<EnergyMeterPieChartBean> list) {
        this.f5724a = list;
    }

    @NotNull
    public String toString() {
        return "EnergyMeterPieChartModel(pieChartModels=" + this.f5724a + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ EnergyMeterPieChartModel(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list);
    }
}
