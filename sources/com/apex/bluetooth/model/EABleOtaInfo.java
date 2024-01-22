package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleOtaInfo {
    public OtaStatus otaStatus;
    public int receive_bytes;
    public int request_id;

    /* loaded from: classes.dex */
    public enum OtaStatus {
        accept(0),
        reject(1),
        reject_version_error(2),
        proceed(3),
        crc_error(4),
        complete(5);
        
        public int value;

        OtaStatus(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public OtaStatus getOtaStatus() {
        return this.otaStatus;
    }

    public int getReceive_bytes() {
        return this.receive_bytes;
    }

    public int getRequest_id() {
        return this.request_id;
    }

    public void setOtaStatus(OtaStatus otaStatus) {
        this.otaStatus = otaStatus;
    }

    public void setReceive_bytes(int i) {
        this.receive_bytes = i;
    }

    public void setRequest_id(int i) {
        this.request_id = i;
    }
}
