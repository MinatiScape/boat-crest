package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AntiLostMode implements Serializable {
    public static final int MODE_LONG_DISTANCE = 3;
    public static final int MODE_MID_DISTANCE = 2;
    public static final int MODE_NOT_ANTI_LOST = 0;
    public static final int MODE_SHORT_DISTANCE = 1;
    private static final long serialVersionUID = 1;
    public int mode;

    public String toString() {
        return "AntiLostMode{mode=" + this.mode + '}';
    }
}
