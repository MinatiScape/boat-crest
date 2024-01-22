package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public abstract class CustomReminder {
    public String description;
    public boolean friday;
    public int imageId;
    public boolean isReminderOn;
    public boolean monday;
    public boolean saturday;
    public boolean sunday;
    public boolean thursday;
    public boolean tuesday;
    public int vibrationSeqID;
    public boolean wednesday;

    public CustomReminder(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i2, String str, boolean z8) {
        this.imageId = i;
        this.sunday = z;
        this.monday = z2;
        this.tuesday = z3;
        this.wednesday = z4;
        this.thursday = z5;
        this.friday = z6;
        this.saturday = z7;
        this.vibrationSeqID = i2;
        this.description = str;
        this.isReminderOn = z8;
    }

    public abstract byte[] getDataBytes();
}
