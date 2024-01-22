package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class LiveData implements Serializable {
    private static final long serialVersionUID = 1;
    public int dbp;
    public int heartRate;
    public int sbp;
    public int totalActiveTime;
    public int totalCalories;
    public int totalDistances;
    public int totalStep;

    public String toString() {
        return "LiveData{totalStep=" + this.totalStep + ", totalCalories=" + this.totalCalories + ", totalDistances=" + this.totalDistances + ", totalActiveTime=" + this.totalActiveTime + ", heartRate=" + this.heartRate + ", sbp=" + this.sbp + ", dbp=" + this.dbp + '}';
    }
}
