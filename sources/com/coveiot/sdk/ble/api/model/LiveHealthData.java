package com.coveiot.sdk.ble.api.model;
/* loaded from: classes9.dex */
public class LiveHealthData {
    public int dbpValue;
    public int hrValue;
    public int rrValue;
    public int sbpValue;
    public int stressValue;

    public int getDbpValue() {
        return this.dbpValue;
    }

    public int getHrValue() {
        return this.hrValue;
    }

    public int getRrValue() {
        return this.rrValue;
    }

    public int getSbpValue() {
        return this.sbpValue;
    }

    public int getStressValue() {
        return this.stressValue;
    }

    public void setDbpValue(int i) {
        this.dbpValue = i;
    }

    public void setHrValue(int i) {
        this.hrValue = i;
    }

    public void setRrValue(int i) {
        this.rrValue = i;
    }

    public void setSbpValue(int i) {
        this.sbpValue = i;
    }

    public void setStressValue(int i) {
        this.stressValue = i;
    }
}
