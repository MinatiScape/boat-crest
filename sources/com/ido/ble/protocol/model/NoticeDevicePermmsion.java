package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class NoticeDevicePermmsion implements Serializable {
    public static final int CAMERA_PERMMSION = 0;
    public static final int STATUS_OFF = 85;
    public static final int STATUS_ON = 170;
    public int enable;
    public int type;

    public String toString() {
        return "CameraPermmsion{type=" + this.type + ", enable=" + this.enable + '}';
    }
}
