package com.coveiot.android.dashboard2.model;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class FitnessVitalsDataModel {
    private int downValue;
    @Nullable
    private FitnessVitalData fitnessData;
    @NotNull
    private String fitnessVitalType;
    @Nullable
    private String infoText;
    private boolean isDataAvailable;
    private boolean isSelected;
    @Nullable
    private String title;
    private int upValue;

    public FitnessVitalsDataModel(@NotNull String fitnessVitalType, boolean z, boolean z2, @Nullable FitnessVitalData fitnessVitalData, @Nullable String str, int i, int i2, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(fitnessVitalType, "fitnessVitalType");
        this.fitnessVitalType = fitnessVitalType;
        this.isSelected = z;
        this.isDataAvailable = z2;
        this.fitnessData = fitnessVitalData;
        this.title = str;
        this.upValue = i;
        this.downValue = i2;
        this.infoText = str2;
    }

    @NotNull
    public final String component1() {
        return this.fitnessVitalType;
    }

    public final boolean component2() {
        return this.isSelected;
    }

    public final boolean component3() {
        return this.isDataAvailable;
    }

    @Nullable
    public final FitnessVitalData component4() {
        return this.fitnessData;
    }

    @Nullable
    public final String component5() {
        return this.title;
    }

    public final int component6() {
        return this.upValue;
    }

    public final int component7() {
        return this.downValue;
    }

    @Nullable
    public final String component8() {
        return this.infoText;
    }

    @NotNull
    public final FitnessVitalsDataModel copy(@NotNull String fitnessVitalType, boolean z, boolean z2, @Nullable FitnessVitalData fitnessVitalData, @Nullable String str, int i, int i2, @Nullable String str2) {
        Intrinsics.checkNotNullParameter(fitnessVitalType, "fitnessVitalType");
        return new FitnessVitalsDataModel(fitnessVitalType, z, z2, fitnessVitalData, str, i, i2, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FitnessVitalsDataModel) {
            FitnessVitalsDataModel fitnessVitalsDataModel = (FitnessVitalsDataModel) obj;
            return Intrinsics.areEqual(this.fitnessVitalType, fitnessVitalsDataModel.fitnessVitalType) && this.isSelected == fitnessVitalsDataModel.isSelected && this.isDataAvailable == fitnessVitalsDataModel.isDataAvailable && Intrinsics.areEqual(this.fitnessData, fitnessVitalsDataModel.fitnessData) && Intrinsics.areEqual(this.title, fitnessVitalsDataModel.title) && this.upValue == fitnessVitalsDataModel.upValue && this.downValue == fitnessVitalsDataModel.downValue && Intrinsics.areEqual(this.infoText, fitnessVitalsDataModel.infoText);
        }
        return false;
    }

    public final int getDownValue() {
        return this.downValue;
    }

    @Nullable
    public final FitnessVitalData getFitnessData() {
        return this.fitnessData;
    }

    @NotNull
    public final String getFitnessVitalType() {
        return this.fitnessVitalType;
    }

    @Nullable
    public final String getInfoText() {
        return this.infoText;
    }

    @Nullable
    public final String getTitle() {
        return this.title;
    }

    public final int getUpValue() {
        return this.upValue;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int hashCode = this.fitnessVitalType.hashCode() * 31;
        boolean z = this.isSelected;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode + i) * 31;
        boolean z2 = this.isDataAvailable;
        int i3 = (i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31;
        FitnessVitalData fitnessVitalData = this.fitnessData;
        int hashCode2 = (i3 + (fitnessVitalData == null ? 0 : fitnessVitalData.hashCode())) * 31;
        String str = this.title;
        int hashCode3 = (((((hashCode2 + (str == null ? 0 : str.hashCode())) * 31) + Integer.hashCode(this.upValue)) * 31) + Integer.hashCode(this.downValue)) * 31;
        String str2 = this.infoText;
        return hashCode3 + (str2 != null ? str2.hashCode() : 0);
    }

    public final boolean isDataAvailable() {
        return this.isDataAvailable;
    }

    public final boolean isSelected() {
        return this.isSelected;
    }

    public final void setDataAvailable(boolean z) {
        this.isDataAvailable = z;
    }

    public final void setDownValue(int i) {
        this.downValue = i;
    }

    public final void setFitnessData(@Nullable FitnessVitalData fitnessVitalData) {
        this.fitnessData = fitnessVitalData;
    }

    public final void setFitnessVitalType(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.fitnessVitalType = str;
    }

    public final void setInfoText(@Nullable String str) {
        this.infoText = str;
    }

    public final void setSelected(boolean z) {
        this.isSelected = z;
    }

    public final void setTitle(@Nullable String str) {
        this.title = str;
    }

    public final void setUpValue(int i) {
        this.upValue = i;
    }

    @NotNull
    public String toString() {
        return "FitnessVitalsDataModel(fitnessVitalType=" + this.fitnessVitalType + ", isSelected=" + this.isSelected + ", isDataAvailable=" + this.isDataAvailable + ", fitnessData=" + this.fitnessData + ", title=" + this.title + ", upValue=" + this.upValue + ", downValue=" + this.downValue + ", infoText=" + this.infoText + HexStringBuilder.COMMENT_END_CHAR;
    }

    public /* synthetic */ FitnessVitalsDataModel(String str, boolean z, boolean z2, FitnessVitalData fitnessVitalData, String str2, int i, int i2, String str3, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? false : z, (i3 & 4) != 0 ? false : z2, (i3 & 8) != 0 ? null : fitnessVitalData, (i3 & 16) != 0 ? null : str2, (i3 & 32) != 0 ? 0 : i, (i3 & 64) == 0 ? i2 : 0, (i3 & 128) == 0 ? str3 : null);
    }
}
