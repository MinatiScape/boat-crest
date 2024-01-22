package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABlePressureData implements Comparable<EABlePressureData> {
    public PressureLevel e_type;
    public int stess_value;
    public long time_stamp;

    /* loaded from: classes.dex */
    public enum PressureLevel {
        pressure_unkown(0),
        pressure_relax(1),
        pressure_normal(2),
        pressure_middle(3),
        pressure_high(4);
        
        public int value;

        PressureLevel(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public PressureLevel getE_type() {
        return this.e_type;
    }

    public int getStess_value() {
        return this.stess_value;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setE_type(PressureLevel pressureLevel) {
        this.e_type = pressureLevel;
    }

    public void setStess_value(int i) {
        this.stess_value = i;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EABlePressureData{time_stamp=");
        sb.append(this.time_stamp);
        sb.append(", stess_value=");
        sb.append(this.stess_value);
        sb.append(", e_type=");
        PressureLevel pressureLevel = this.e_type;
        sb.append(pressureLevel != null ? pressureLevel.getValue() : 0);
        sb.append('}');
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(EABlePressureData eABlePressureData) {
        if (eABlePressureData != null) {
            if (eABlePressureData.getTime_stamp() < getTime_stamp()) {
                return 1;
            }
            return eABlePressureData.getTime_stamp() == getTime_stamp() ? 0 : -1;
        }
        return 0;
    }
}
