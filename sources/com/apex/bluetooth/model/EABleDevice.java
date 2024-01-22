package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleDevice {
    public String deviceAddress;
    public String deviceName;
    public String deviceSign;
    public boolean isBind;
    public int rssi;
    public int type;

    public String getDeviceAddress() {
        return this.deviceAddress;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceSign() {
        return this.deviceSign;
    }

    public int getRssi() {
        return this.rssi;
    }

    public int getType() {
        return this.type;
    }

    public boolean isBind() {
        return this.isBind;
    }

    public void setBind(boolean z) {
        this.isBind = z;
    }

    public void setDeviceAddress(String str) {
        this.deviceAddress = str;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setDeviceSign(String str) {
        this.deviceSign = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String toString() {
        return "EABleDevice{deviceName='" + this.deviceName + "', deviceAddress='" + this.deviceAddress + "', deviceSign='" + this.deviceSign + "', isBind=" + this.isBind + ", rssi=" + this.rssi + ", type=" + this.type + '}';
    }
}
