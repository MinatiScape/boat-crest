package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class DialPlate implements Serializable {
    public static final int MODE_1 = 1;
    public static final int MODE_2 = 2;
    public static final int MODE_3 = 3;
    public static final int MODE_4 = 4;
    private static final int MODE_INVALID = 0;
    private static final long serialVersionUID = 1;
    public int dial_id = 0;

    public String toString() {
        return "DialPlate{dial_id=" + this.dial_id + '}';
    }
}
