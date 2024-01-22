package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class ShortCut implements Serializable {
    public static final int MODE_CAMERA = 1;
    public static final int MODE_NOT_DISTURB = 3;
    public static final int MODE_SPORT = 2;
    private static final long serialVersionUID = 1;
    public int func1 = 1;

    public String toString() {
        return "ShortCut{func1=" + this.func1 + '}';
    }
}
