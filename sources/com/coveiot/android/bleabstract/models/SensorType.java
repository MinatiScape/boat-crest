package com.coveiot.android.bleabstract.models;
/* loaded from: classes2.dex */
public enum SensorType {
    ALL(1),
    ACCELEROMETER(2),
    TEMPERATURE(3),
    PPG(4);
    

    /* renamed from: a  reason: collision with root package name */
    public int f3449a;

    SensorType(int i) {
        this.f3449a = i;
    }

    public static SensorType getByType(int i) {
        SensorType sensorType = ALL;
        if (i == sensorType.f3449a) {
            return sensorType;
        }
        SensorType sensorType2 = ACCELEROMETER;
        if (i != sensorType2.f3449a) {
            sensorType2 = TEMPERATURE;
            if (i != sensorType2.f3449a) {
                sensorType2 = PPG;
                if (i != sensorType2.f3449a) {
                    return sensorType;
                }
            }
        }
        return sensorType2;
    }

    public int getType() {
        return this.f3449a;
    }
}
