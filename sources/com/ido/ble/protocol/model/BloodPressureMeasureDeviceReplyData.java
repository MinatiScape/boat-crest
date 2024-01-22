package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BloodPressureMeasureDeviceReplyData implements Serializable {
    public static final int STATE_FAILED = 3;
    public static final int STATE_IN_SPORTING_MODE = 4;
    public static final int STATE_MEASURING = 1;
    public static final int STATE_NO_SUPPORT = 0;
    public static final int STATE_SUCCESS = 2;
    private static final long serialVersionUID = 1;
    public int diastolic_bp;
    public int status;
    public int systolic_bp;

    public String toString() {
        return "BloodPressureMeasureDeviceReplyData{status=" + this.status + ", systolic_bp=" + this.systolic_bp + ", diastolic_bp=" + this.diastolic_bp + '}';
    }
}
