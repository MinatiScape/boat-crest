package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public enum ReminderFrequency {
    DAILY("DAILY"),
    WEEKLY("WEEKLY"),
    MONTHLY("MONTHLY"),
    QUARTERLY("QUARTERLY"),
    HALF_YEARLY("HALF_YEARLY"),
    YEARLY("YEARLY");
    
    private String reminderFrequency;

    ReminderFrequency(String str) {
        this.reminderFrequency = str;
    }

    public String getReminderFrequency() {
        return this.reminderFrequency;
    }
}
