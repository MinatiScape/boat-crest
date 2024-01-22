package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleGpsData implements Comparable<EABleGpsData> {
    public double latitude;
    public double longitude;
    public long time_stamp;

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public long getTime_stamp() {
        return this.time_stamp;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public void setTime_stamp(long j) {
        this.time_stamp = j;
    }

    public String toString() {
        return "EABleGpsData{time_stamp=" + this.time_stamp + ", latitude=" + this.latitude + ", longitude=" + this.longitude + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(EABleGpsData eABleGpsData) {
        if (eABleGpsData != null) {
            if (eABleGpsData.getTime_stamp() > getTime_stamp()) {
                return -1;
            }
            return eABleGpsData.getTime_stamp() == getTime_stamp() ? 0 : 1;
        }
        return 0;
    }
}
