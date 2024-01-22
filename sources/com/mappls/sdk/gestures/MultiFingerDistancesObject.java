package com.mappls.sdk.gestures;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public class MultiFingerDistancesObject {
    private final float currFingersDiffX;
    private final float currFingersDiffXY;
    private final float currFingersDiffY;
    private final float prevFingersDiffX;
    private final float prevFingersDiffXY;
    private final float prevFingersDiffY;

    public MultiFingerDistancesObject(float f, float f2, float f3, float f4) {
        this.prevFingersDiffX = f;
        this.prevFingersDiffY = f2;
        this.currFingersDiffX = f3;
        this.currFingersDiffY = f4;
        this.prevFingersDiffXY = (float) Math.sqrt((f * f) + (f2 * f2));
        this.currFingersDiffXY = (float) Math.sqrt((f3 * f3) + (f4 * f4));
    }

    public float getCurrFingersDiffX() {
        return this.currFingersDiffX;
    }

    public float getCurrFingersDiffXY() {
        return this.currFingersDiffXY;
    }

    public float getCurrFingersDiffY() {
        return this.currFingersDiffY;
    }

    public float getPrevFingersDiffX() {
        return this.prevFingersDiffX;
    }

    public float getPrevFingersDiffXY() {
        return this.prevFingersDiffXY;
    }

    public float getPrevFingersDiffY() {
        return this.prevFingersDiffY;
    }
}
