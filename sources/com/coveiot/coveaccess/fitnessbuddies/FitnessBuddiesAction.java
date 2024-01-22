package com.coveiot.coveaccess.fitnessbuddies;
/* loaded from: classes8.dex */
public enum FitnessBuddiesAction {
    ACCEPT("accept"),
    REJECT("reject"),
    REINVITE("reinvite");
    
    private String action;

    FitnessBuddiesAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }
}
