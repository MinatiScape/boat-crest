package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleWeightFormat {
    public WeightUnit e_format;

    /* loaded from: classes.dex */
    public enum WeightUnit {
        kilogram(0),
        pound(1);
        
        public int value;

        WeightUnit(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public WeightUnit getE_format() {
        return this.e_format;
    }

    public void setE_format(WeightUnit weightUnit) {
        this.e_format = weightUnit;
    }
}
