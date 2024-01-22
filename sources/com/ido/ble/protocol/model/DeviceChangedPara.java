package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class DeviceChangedPara {
    public static final int CHANGED = 1;
    public static final int DATA_TYPE_DEVICE_IN_NOT_BIND_STATUS = 1;
    public static final int DATA_TYPE_HEART_MODE_PARA_CHANGED = 2;
    public static final int DATA_TYPE_INVALID = 0;
    public static final int NOT_CHANGE = 0;
    public int dataType;
    public int doNotDisturb;
    public int errorIndex;
    public int is_success;
    public int msg_ID;
    public int msg_notice;
    public int msg_type;
    public int notifyType;

    public String toString() {
        return "DeviceChangedPara{doNotDisturb=" + this.doNotDisturb + ", dataType=" + this.dataType + ", msg_type=" + this.msg_type + ", msg_ID=" + this.msg_ID + ", msg_notice=" + this.msg_notice + ", errorIndex=" + this.errorIndex + ", notifyType=" + this.notifyType + ", is_success=" + this.is_success + '}';
    }
}
