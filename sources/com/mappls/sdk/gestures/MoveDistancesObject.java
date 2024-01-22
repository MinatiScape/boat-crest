package com.mappls.sdk.gestures;

import androidx.annotation.Keep;
@Keep
/* loaded from: classes11.dex */
public final class MoveDistancesObject {
    private float currX;
    private float currY;
    private float distanceXSinceLast;
    private float distanceXSinceStart;
    private float distanceYSinceLast;
    private float distanceYSinceStart;
    private final float initialX;
    private final float initialY;
    private float prevX;
    private float prevY;

    public MoveDistancesObject(float f, float f2) {
        this.initialX = f;
        this.initialY = f2;
    }

    public void addNewPosition(float f, float f2) {
        float f3 = this.currX;
        this.prevX = f3;
        float f4 = this.currY;
        this.prevY = f4;
        this.currX = f;
        this.currY = f2;
        this.distanceXSinceLast = f3 - f;
        this.distanceYSinceLast = f4 - f2;
        this.distanceXSinceStart = this.initialX - f;
        this.distanceYSinceStart = this.initialY - f2;
    }

    public float getCurrentX() {
        return this.currX;
    }

    public float getCurrentY() {
        return this.currY;
    }

    public float getDistanceXSinceLast() {
        return this.distanceXSinceLast;
    }

    public float getDistanceXSinceStart() {
        return this.distanceXSinceStart;
    }

    public float getDistanceYSinceLast() {
        return this.distanceYSinceLast;
    }

    public float getDistanceYSinceStart() {
        return this.distanceYSinceStart;
    }

    public float getInitialX() {
        return this.initialX;
    }

    public float getInitialY() {
        return this.initialY;
    }

    public float getPreviousX() {
        return this.prevX;
    }

    public float getPreviousY() {
        return this.prevY;
    }
}
