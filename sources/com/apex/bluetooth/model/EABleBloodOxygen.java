package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleBloodOxygen implements Comparable<EABleBloodOxygen> {
    public int blood_oxygen_value;
    public long time_stamp;

    public int getBlood_oxygen_value() {
        return this.blood_oxygen_value;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setBlood_oxygen_value(int i) {
        this.blood_oxygen_value = i;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        return "EABleBloodOxygen{time_stamp=" + this.time_stamp + ", blood_oxygen_value=" + this.blood_oxygen_value + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleBloodOxygen eABleBloodOxygen) {
        if (eABleBloodOxygen != null) {
            if (eABleBloodOxygen.getTime_stamp() > getTime_stamp()) {
                return 1;
            }
            return eABleBloodOxygen.getTime_stamp() == getTime_stamp() ? 0 : -1;
        }
        return 0;
    }
}
