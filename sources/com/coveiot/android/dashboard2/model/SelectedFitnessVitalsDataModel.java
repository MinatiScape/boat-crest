package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class SelectedFitnessVitalsDataModel {
    @NotNull
    private List<FitnessVitalsDataModel> fitnessDataModels;

    public SelectedFitnessVitalsDataModel() {
        this(null, 1, null);
    }

    public SelectedFitnessVitalsDataModel(@NotNull List<FitnessVitalsDataModel> fitnessDataModels) {
        Intrinsics.checkNotNullParameter(fitnessDataModels, "fitnessDataModels");
        this.fitnessDataModels = fitnessDataModels;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SelectedFitnessVitalsDataModel copy$default(SelectedFitnessVitalsDataModel selectedFitnessVitalsDataModel, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = selectedFitnessVitalsDataModel.fitnessDataModels;
        }
        return selectedFitnessVitalsDataModel.copy(list);
    }

    @NotNull
    public final List<FitnessVitalsDataModel> component1() {
        return this.fitnessDataModels;
    }

    @NotNull
    public final SelectedFitnessVitalsDataModel copy(@NotNull List<FitnessVitalsDataModel> fitnessDataModels) {
        Intrinsics.checkNotNullParameter(fitnessDataModels, "fitnessDataModels");
        return new SelectedFitnessVitalsDataModel(fitnessDataModels);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SelectedFitnessVitalsDataModel) && Intrinsics.areEqual(this.fitnessDataModels, ((SelectedFitnessVitalsDataModel) obj).fitnessDataModels);
    }

    @NotNull
    public final List<FitnessVitalsDataModel> getFitnessDataModels() {
        return this.fitnessDataModels;
    }

    public int hashCode() {
        return this.fitnessDataModels.hashCode();
    }

    public final void setFitnessDataModels(@NotNull List<FitnessVitalsDataModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.fitnessDataModels = list;
    }

    @NotNull
    public String toString() {
        return "SelectedFitnessVitalsDataModel(fitnessDataModels=" + this.fitnessDataModels + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ SelectedFitnessVitalsDataModel(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list);
    }
}
