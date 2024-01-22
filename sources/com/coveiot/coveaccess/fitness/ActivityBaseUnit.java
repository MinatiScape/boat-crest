package com.coveiot.coveaccess.fitness;

import com.coveiot.android.fitnesschallenges.utils.FitnessChallengeConstants;
import com.coveiot.android.watchfaceui.constants.WatchfaceConstants;
/* loaded from: classes8.dex */
public enum ActivityBaseUnit {
    STEPS("STEPS"),
    LAPS("LAPS"),
    METERS(FitnessChallengeConstants.METERS),
    MINUTES("MINUTES"),
    PERCENTAGE("PERCENTAGE"),
    CALORIES(FitnessChallengeConstants.CALORIES),
    STROKES("STROKES"),
    KILOMETERS(WatchfaceConstants.KILOMETERS);
    
    private String activityBaseUnit;

    ActivityBaseUnit(String str) {
        this.activityBaseUnit = str;
    }

    public String getActivityBaseUnit() {
        return this.activityBaseUnit;
    }
}
