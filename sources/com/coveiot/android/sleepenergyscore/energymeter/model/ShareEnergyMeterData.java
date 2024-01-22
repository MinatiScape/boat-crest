package com.coveiot.android.sleepenergyscore.energymeter.model;

import java.io.Serializable;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes6.dex */
public final class ShareEnergyMeterData implements Serializable {
    @Nullable
    private String dwmValue;
    private int endEnergyScore;
    @Nullable
    private String graphType;
    private int startEnergyScore;

    @Nullable
    public final String getDwmValue() {
        return this.dwmValue;
    }

    public final int getEndEnergyScore() {
        return this.endEnergyScore;
    }

    @Nullable
    public final String getGraphType() {
        return this.graphType;
    }

    public final int getStartEnergyScore() {
        return this.startEnergyScore;
    }

    public final void setDwmValue(@Nullable String str) {
        this.dwmValue = str;
    }

    public final void setEndEnergyScore(int i) {
        this.endEnergyScore = i;
    }

    public final void setGraphType(@Nullable String str) {
        this.graphType = str;
    }

    public final void setStartEnergyScore(int i) {
        this.startEnergyScore = i;
    }
}
