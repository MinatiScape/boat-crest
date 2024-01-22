package com.ido.ble.gps.model;
/* loaded from: classes11.dex */
public class GpsStatus {
    public static final int STATUS_IDLE = 0;
    public static final int STATUS_RUNNING = 2;
    public static final int STATUS_SEARCHING = 1;
    public int gps_run_status = -1;
    public int agps_is_valid = -1;

    public String toString() {
        return "GpsStatus{gps_run_status=" + this.gps_run_status + ", agps_is_valid=" + this.agps_is_valid + '}';
    }
}
