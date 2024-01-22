package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class DisplayMode implements Serializable {
    public static final int MODE_DEFAULT = 0;
    public static final int MODE_HORIZONTAL = 1;
    public static final int MODE_OVER_180 = 3;
    public static final int MODE_VERTICAL = 2;
    private static final long serialVersionUID = 1;
    public int mode;

    public String toString() {
        return "DisplayMode{mode=" + this.mode + '}';
    }
}
