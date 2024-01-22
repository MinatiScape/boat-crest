package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public enum EABleDataType {
    default_data(0),
    steps_data(1),
    sleep_data(2),
    hr_data(3),
    gps_data(4),
    multi_sports_data(5),
    blood_oxygen_data_data(6),
    stress_data(7),
    step_freq_data(8),
    step_pace_data(9),
    resting_hr_data(10),
    habit_tracker_data(11);
    
    public int value;

    EABleDataType(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
