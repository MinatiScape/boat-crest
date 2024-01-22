package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class TemperatureData extends AbstractData {
    public float b;
    public float c;

    public float getBody() {
        return this.b;
    }

    public float getWrist() {
        return this.c;
    }

    public void setBody(float f) {
        this.b = f;
    }

    public void setWrist(float f) {
        this.c = f;
    }
}
