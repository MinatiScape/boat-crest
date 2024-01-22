package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class StopFindPhone implements Serializable {
    public static final int STOP_FIND = 1;
    public int states = 1;

    public String toString() {
        return "StopFindPhone{states=" + this.states + '}';
    }
}
