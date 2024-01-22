package com.coveiot.sdk.ble.model;
/* loaded from: classes9.dex */
public class BleActivityDataType {
    public boolean ALTITUDE;
    public boolean CALORIE;
    public boolean DISTANCE;
    public boolean GPS;
    public boolean HRS;
    public boolean PACE;
    public boolean SPEED;
    public boolean STEP;
    public boolean STEP_FREQ;
    public boolean STEP_STRIDE;

    public BleActivityDataType(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10) {
        this.HRS = false;
        this.STEP = false;
        this.STEP_STRIDE = false;
        this.STEP_FREQ = false;
        this.SPEED = false;
        this.PACE = false;
        this.ALTITUDE = false;
        this.GPS = false;
        this.CALORIE = false;
        this.DISTANCE = false;
        this.HRS = z;
        this.STEP = z2;
        this.STEP_STRIDE = z3;
        this.STEP_FREQ = z4;
        this.SPEED = z5;
        this.PACE = z6;
        this.ALTITUDE = z7;
        this.GPS = z8;
        this.CALORIE = z9;
        this.DISTANCE = z10;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v8, types: [int] */
    public int getDataTypeValue() {
        boolean z = this.HRS;
        boolean z2 = z;
        if (this.STEP) {
            z2 = (z ? 1 : 0) | true;
        }
        boolean z3 = z2;
        if (this.STEP_STRIDE) {
            z3 = (z2 ? 1 : 0) | true;
        }
        boolean z4 = z3;
        if (this.STEP_FREQ) {
            z4 = (z3 ? 1 : 0) | true;
        }
        boolean z5 = z4;
        if (this.SPEED) {
            z5 = (z4 ? 1 : 0) | true;
        }
        boolean z6 = z5;
        if (this.PACE) {
            z6 = (z5 ? 1 : 0) | true;
        }
        boolean z7 = z6;
        if (this.ALTITUDE) {
            z7 = (z6 ? 1 : 0) | true;
        }
        boolean z8 = z7;
        if (this.GPS) {
            z8 = (z7 ? 1 : 0) | true;
        }
        ?? r0 = z8;
        if (this.CALORIE) {
            r0 = (z8 ? 1 : 0) | true;
        }
        return this.DISTANCE ? r0 | 512 : r0;
    }
}
