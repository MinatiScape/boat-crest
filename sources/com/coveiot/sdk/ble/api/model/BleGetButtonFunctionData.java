package com.coveiot.sdk.ble.api.model;

import androidx.annotation.NonNull;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class BleGetButtonFunctionData implements Serializable {
    public int longPress;
    public int position;
    public int shortPress;
    public int versionNumber;

    public BleGetButtonFunctionData() {
    }

    public int getLongPress() {
        return this.longPress;
    }

    public int getPosition() {
        return this.position;
    }

    public int getShortPress() {
        return this.shortPress;
    }

    public int getVersionNumber() {
        return this.versionNumber;
    }

    public void setLongPress(int i) {
        this.longPress = i;
    }

    public void setPosition(int i) {
        this.position = i;
    }

    public void setShortPress(int i) {
        this.shortPress = i;
    }

    public void setVersionNumber(int i) {
        this.versionNumber = i;
    }

    @NonNull
    public String toString() {
        return "BleGetButtonFunctionData{versionNumber=" + this.versionNumber + ", position=" + this.position + ", shortPress=" + this.shortPress + ", LongPress=" + this.longPress + '}';
    }

    public BleGetButtonFunctionData(int i, int i2, int i3, int i4) {
        this.versionNumber = i;
        this.position = i2;
        this.shortPress = i3;
        this.longPress = i4;
    }
}
