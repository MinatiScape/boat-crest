package com.coveiot.covedb.ecg;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import com.coveiot.android.activitymodes.utils.WorkoutConstants;
@Entity(primaryKeys = {WorkoutConstants.SESSION_ID}, tableName = "ecg_summary_table")
/* loaded from: classes8.dex */
public class EntityECGSummaryData {
    public String endDateTime;
    public int heart_rate;
    public int high_bp;
    public int hrv_value;
    public boolean is_sync_server;
    public String json_data_URL;
    public int low_bp;
    public String mediaID;
    public String mediaURL;
    public int rr_interval;
    public String serial_no;
    @NonNull
    public String session_id;
    public String startDateTime;
    public int stress;

    public String getEndDateTime() {
        return this.endDateTime;
    }

    public int getHeart_rate() {
        return this.heart_rate;
    }

    public int getHigh_bp() {
        return this.high_bp;
    }

    public int getHrv_value() {
        return this.hrv_value;
    }

    public String getJson_data_URL() {
        return this.json_data_URL;
    }

    public int getLow_bp() {
        return this.low_bp;
    }

    public String getMediaID() {
        return this.mediaID;
    }

    public String getMediaURL() {
        return this.mediaURL;
    }

    public int getRr_interval() {
        return this.rr_interval;
    }

    @NonNull
    public String getSerial_no() {
        return this.serial_no;
    }

    public String getSessionId() {
        return this.session_id;
    }

    @NonNull
    public String getSession_id() {
        return this.session_id;
    }

    public String getStartDateTime() {
        return this.startDateTime;
    }

    public int getStress() {
        return this.stress;
    }

    public boolean isIs_sync_server() {
        return this.is_sync_server;
    }

    public void setEndDateTime(String str) {
        this.endDateTime = str;
    }

    public void setHeart_rate(int i) {
        this.heart_rate = i;
    }

    public void setHigh_bp(int i) {
        this.high_bp = i;
    }

    public void setHrv_value(int i) {
        this.hrv_value = i;
    }

    public void setIs_sync_server(boolean z) {
        this.is_sync_server = z;
    }

    public void setJson_data_URL(String str) {
        this.json_data_URL = str;
    }

    public void setLow_bp(int i) {
        this.low_bp = i;
    }

    public void setMediaID(String str) {
        this.mediaID = str;
    }

    public void setMediaURL(String str) {
        this.mediaURL = str;
    }

    public void setRr_interval(int i) {
        this.rr_interval = i;
    }

    public void setSerial_no(@NonNull String str) {
        this.serial_no = str;
    }

    public void setSessionId(String str) {
        this.session_id = str;
    }

    public void setSession_id(@NonNull String str) {
        this.session_id = str;
    }

    public void setStartDateTime(String str) {
        this.startDateTime = str;
    }

    public void setStress(int i) {
        this.stress = i;
    }
}
