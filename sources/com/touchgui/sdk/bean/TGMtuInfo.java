package com.touchgui.sdk.bean;
/* loaded from: classes12.dex */
public class TGMtuInfo {
    private int dleLength;
    private int phySpeed;
    private int rxMtu;
    private int txMtu;

    public int getDleLength() {
        return this.dleLength;
    }

    public int getPhySpeed() {
        return this.phySpeed;
    }

    public int getRxMtu() {
        return this.rxMtu;
    }

    public int getTxMtu() {
        return this.txMtu;
    }

    public void setDleLength(int i) {
        this.dleLength = i;
    }

    public void setPhySpeed(int i) {
        this.phySpeed = i;
    }

    public void setRxMtu(int i) {
        this.rxMtu = i;
    }

    public void setTxMtu(int i) {
        this.txMtu = i;
    }

    public String toString() {
        return "MtuInfo{txMtu=" + this.txMtu + ", rxMtu=" + this.rxMtu + ", phySpeed=" + this.phySpeed + ", dleLength=" + this.dleLength + '}';
    }
}
