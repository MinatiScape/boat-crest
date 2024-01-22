package com.coveiot.sdk.ble.api.model;

import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleGetSOSConfig implements Serializable {
    public String contactName;
    public int contactNameLength;
    public String contactNumber;
    public int contactNumberLength;
    public boolean isSOSEnabled;
    public int timer;

    public BleGetSOSConfig() {
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

    public int getTimer() {
        return this.timer;
    }

    public boolean isSOSEnabled() {
        return this.isSOSEnabled;
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

    public void setSOSEnabled(boolean z) {
        this.isSOSEnabled = z;
    }

    public void setTimer(int i) {
        this.timer = i;
    }

    public String toString() {
        return "BleGetSOSConfig{isSOSEnabled=" + this.isSOSEnabled + ", timer=" + this.timer + ", contactNameLength=" + this.contactNameLength + ", contactName='" + this.contactName + "', contactNumberLength=" + this.contactNumberLength + ", contactNumber='" + this.contactNumber + "'}";
    }

    public BleGetSOSConfig(boolean z, int i, int i2, String str, int i3, String str2) {
        this.isSOSEnabled = z;
        this.timer = i;
        this.contactNameLength = i2;
        this.contactName = str;
        this.contactNumberLength = i3;
        this.contactNumber = str2;
    }
}
