package com.htsmart.wristband2.bean.data;
/* loaded from: classes11.dex */
public class StepData extends AbstractData {
    public int b;
    public float c;
    public float d;

    public float getCalories() {
        return this.d;
    }

    public float getDistance() {
        return this.c;
    }

    public int getStep() {
        return this.b;
    }

    public void setCalories(float f) {
        this.d = f;
    }

    public void setDistance(float f) {
        this.c = f;
    }

    public void setStep(int i) {
        this.b = i;
    }
}
