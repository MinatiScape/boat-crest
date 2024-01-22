package com.coveiot.android.fitnessbuddies.constants;
/* loaded from: classes4.dex */
public enum FitnessBuddiesLabels {
    NUDGE("Nudge"),
    CHEER("Cheer"),
    APPLAUD("Applaud");
    
    private String labelType;

    FitnessBuddiesLabels(String str) {
        this.labelType = str;
    }

    public String getLabelType() {
        return this.labelType;
    }
}
