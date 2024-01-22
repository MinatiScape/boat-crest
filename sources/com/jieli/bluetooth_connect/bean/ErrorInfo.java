package com.jieli.bluetooth_connect.bean;

import android.bluetooth.BluetoothDevice;
/* loaded from: classes11.dex */
public class ErrorInfo {
    private int code;
    private BluetoothDevice device;
    private String message;

    public ErrorInfo() {
    }

    public int getCode() {
        return this.code;
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.device = bluetoothDevice;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "ErrorInfo{code=" + this.code + ", message='" + this.message + "', device=" + this.device + '}';
    }

    public ErrorInfo(int i, String str) {
        setCode(i);
        setMessage(str);
    }
}
