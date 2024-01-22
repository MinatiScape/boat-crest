package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class BloodPressureData extends AbstractData {
    public int b;
    public int c;

    public int getDbp() {
        return this.c;
    }

    public int getSbp() {
        return this.b;
    }

    public void setDbp(int i) {
        this.c = i;
    }

    public void setSbp(int i) {
        this.b = i;
    }
}
