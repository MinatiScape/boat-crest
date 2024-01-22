package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGBluetoothConnectionConfig {
    private int connTimeout;
    private int maxInterval;
    private int minInterval;
    private int mode;
    private int modifyConnParam;
    private int slaveLatency;

    public int getConnTimeout() {
        return this.connTimeout;
    }

    public int getMaxInterval() {
        return this.maxInterval;
    }

    public int getMinInterval() {
        return this.minInterval;
    }

    public int getMode() {
        return this.mode;
    }

    public int getModifyConnParam() {
        return this.modifyConnParam;
    }

    public int getSlaveLatency() {
        return this.slaveLatency;
    }

    public void setConnTimeout(int i) {
        this.connTimeout = i;
    }

    public void setMaxInterval(int i) {
        this.maxInterval = i;
    }

    public void setMinInterval(int i) {
        this.minInterval = i;
    }

    public void setMode(int i) {
        this.mode = i;
    }

    public void setModifyConnParam(int i) {
        this.modifyConnParam = i;
    }

    public void setSlaveLatency(int i) {
        this.slaveLatency = i;
    }
}
