package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleCheckSwitch {
    public CheckType checkType;
    public int sw;

    /* loaded from: classes.dex */
    public enum CheckType {
        default_type(0),
        hr(1),
        stress(2),
        blood_oxygen(3),
        breathe(4);
        
        public int value;

        CheckType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public CheckType getCheckType() {
        return this.checkType;
    }

    public int getSw() {
        return this.sw;
    }

    public void setCheckType(CheckType checkType) {
        this.checkType = checkType;
    }

    public void setSw(int i) {
        this.sw = i;
    }
}
