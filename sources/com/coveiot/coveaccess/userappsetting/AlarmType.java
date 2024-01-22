package com.coveiot.coveaccess.userappsetting;
/* loaded from: classes8.dex */
public enum AlarmType {
    MEDICINE("TAKE_MEDICINE"),
    DRINK("DRINK_WATER"),
    FOOD("HAVE_FOOD");
    
    private String alarmType;

    AlarmType(String str) {
        this.alarmType = str;
    }

    public String getAlarmType() {
        return this.alarmType;
    }
}
