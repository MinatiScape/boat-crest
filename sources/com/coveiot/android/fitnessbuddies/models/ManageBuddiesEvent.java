package com.coveiot.android.fitnessbuddies.models;
/* loaded from: classes4.dex */
public enum ManageBuddiesEvent {
    INVITE("invite"),
    RE_INVITE("reinvite"),
    DELETE("delete"),
    ACCEPT("accept"),
    REJECT("reject");
    
    private String manageBuddiesType;

    ManageBuddiesEvent(String str) {
        this.manageBuddiesType = str;
    }

    public String getManageBuddiesType() {
        return this.manageBuddiesType;
    }
}
