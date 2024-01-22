package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class BTCallControlData {
    private String macAddress;
    private String name;

    public BTCallControlData() {
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getName() {
        return this.name;
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public BTCallControlData(String str, String str2) {
        this.macAddress = str;
        this.name = str2;
    }
}
