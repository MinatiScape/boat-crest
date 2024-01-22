package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleDistanceFormat {
    public DistanceUnit e_format;

    /* loaded from: classes.dex */
    public enum DistanceUnit {
        kilometre(0),
        mile(1);
        
        public int value;

        DistanceUnit(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public DistanceUnit getE_format() {
        return this.e_format;
    }

    public void setE_format(DistanceUnit distanceUnit) {
        this.e_format = distanceUnit;
    }
}
