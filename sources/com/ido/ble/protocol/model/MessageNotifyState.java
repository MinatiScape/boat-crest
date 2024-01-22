package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class MessageNotifyState implements Serializable {
    private static final long serialVersionUID = 1;
    public int evt_type;
    public int notify_state;
    public int pic_flag;

    public String toString() {
        return "MessageNotifyState{evt_type=" + this.evt_type + ", notify_state=" + this.notify_state + ", pic_flag=" + this.pic_flag + '}';
    }
}
