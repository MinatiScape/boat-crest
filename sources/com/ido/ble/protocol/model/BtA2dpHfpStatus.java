package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class BtA2dpHfpStatus implements Serializable {
    private static final int STATUS_INVALID = 0;
    private static final int STATUS_OFF = 170;
    private static final int STATUS_ON = 85;
    private static final long serialVersionUID = 1;
    public int a2dp_connect_states;
    public int bt_connect_states;
    public int bt_pair_states;
    public int hfp_connect_states;

    public String toString() {
        return "BtA2dpHfpStatus{bt_connect_states=" + this.bt_connect_states + ", bt_pair_states=" + this.bt_pair_states + ", a2dp_connect_states=" + this.a2dp_connect_states + ", hfp_connect_states=" + this.hfp_connect_states + '}';
    }
}
