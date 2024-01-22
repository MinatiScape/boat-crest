package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class SOSRecordItem implements Serializable {
    public String contactName;
    public int contactNameLength;
    public String contactNumber;
    public int contactNumberLength;
    public int status;
    public Long unixTimeStamp;

    public SOSRecordItem() {
    }

    public String getContactName() {
        return this.contactName;
    }

    public int getContactNameLength() {
        return this.contactNameLength;
    }

    public String getContactNumber() {
        return this.contactNumber;
    }

    public int getContactNumberLength() {
        return this.contactNumberLength;
    }

    public int getStatus() {
        return this.status;
    }

    public Long getUnixTimeStamp() {
        return this.unixTimeStamp;
    }

    public void setContactName(String str) {
        this.contactName = str;
    }

    public void setContactNameLength(int i) {
        this.contactNameLength = i;
    }

    public void setContactNumber(String str) {
        this.contactNumber = str;
    }

    public void setContactNumberLength(int i) {
        this.contactNumberLength = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setUnixTimeStamp(Long l) {
        this.unixTimeStamp = l;
    }

    public String toString() {
        return "SOSRecordItem{unixTimeStamp='" + this.unixTimeStamp + "', contactNameLength=" + this.contactNameLength + ", contactName='" + this.contactName + "', contactNumberLength=" + this.contactNumberLength + ", contactNumber='" + this.contactNumber + "'}";
    }

    public SOSRecordItem(int i, Long l, int i2, String str, int i3, String str2) {
        this.status = i;
        this.unixTimeStamp = l;
        this.contactNameLength = i2;
        this.contactName = str;
        this.contactNumberLength = i3;
        this.contactNumber = str2;
    }
}
