package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class SosSwitch {
    public static final int MODE_OFF = 85;
    public static final int MODE_ON = 170;
    public static final int TYPE_ANDROID = 2;
    public static final int TYPE_DORO = 1;
    public int on_off = 85;
    public int phone_type = 2;

    public String toString() {
        return "SosSwitch{on_off=" + this.on_off + ", phone_type=" + this.phone_type + '}';
    }
}
