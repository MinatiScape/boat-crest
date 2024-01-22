package com.ido.ble.gps.model;
/* loaded from: classes11.dex */
public class ConnParam {
    public int connTimeout;
    public int maxInterval;
    public int minInterval;
    public int mode;
    public int modifyConnParam;
    public int slaveLatency;

    public String toString() {
        return "ConnParam{mode=" + this.mode + ", modifyConnParam=" + this.modifyConnParam + ", maxInterval=" + this.maxInterval + ", minInterval=" + this.minInterval + ", slaveLatency=" + this.slaveLatency + ", connTimeout=" + this.connTimeout + '}';
    }
}
