package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public enum RemindersType {
    DRINK("DRINK"),
    MEDICINE("MEDICINE"),
    MEETING("MEETING"),
    HAND_WASH("HAND_WASH"),
    SEDENTARY("SEDENTARY"),
    OTHERS("OTHERS");
    
    private String remindersType;

    RemindersType(String str) {
        this.remindersType = str;
    }

    public String getRemindersType() {
        return this.remindersType;
    }
}
