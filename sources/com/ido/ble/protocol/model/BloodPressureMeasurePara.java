package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BloodPressureMeasurePara implements Serializable {
    public static final int FLAG_GET_DATA = 3;
    public static final int FLAG_MEASURE_START = 1;
    public static final int FLAG_MEASURE_STOP = 2;
    private static final long serialVersionUID = 1;
    public int flag;

    public String toString() {
        return "BloodPressureMeasurePara{flag=" + this.flag + '}';
    }
}
