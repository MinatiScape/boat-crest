package com.ido.ble.gps.model;
/* loaded from: classes11.dex */
public class ControlGpsReply {
    public int errorCode;
    public int status;
    public int type;

    public String toString() {
        return "ControlGpsReply{type=" + this.type + ", status=" + this.status + ", errorCode=" + this.errorCode + '}';
    }
}
