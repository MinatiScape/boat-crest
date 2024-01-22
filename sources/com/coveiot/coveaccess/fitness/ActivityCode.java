package com.coveiot.coveaccess.fitness;

import com.coveiot.coveaccess.constants.CoveApiConstants;
/* loaded from: classes8.dex */
public enum ActivityCode {
    WALK("WALK"),
    LIGHT_RUN("LIGHT_RUN"),
    RUN(CoveApiConstants.RUN);
    
    private String activityCode;

    ActivityCode(String str) {
        this.activityCode = str;
    }

    public String getActivityType() {
        return this.activityCode;
    }
}
