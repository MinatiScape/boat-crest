package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleOtaRequest {
    public int crc;
    public int current_size;
    public OtaRequestType e_type;
    public int is_test_mode;
    public int pop_up_interface;
    public int res_addr;
    public int total_size;
    public String version;
    public int wait_bytes;

    /* loaded from: classes.dex */
    public enum OtaRequestType {
        apollo(0),
        stm32(1),
        res(2),
        tp(3),
        hr(4),
        gps(5),
        agps(6),
        user_wf(7);
        
        public int value;

        OtaRequestType(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public int getCrc() {
        return this.crc;
    }

    public int getCurrent_size() {
        return this.current_size;
    }

    public OtaRequestType getE_type() {
        return this.e_type;
    }

    public int getIs_test_mode() {
        return this.is_test_mode;
    }

    public int getPop_up_interface() {
        return this.pop_up_interface;
    }

    public int getRes_addr() {
        return this.res_addr;
    }

    public int getTotal_size() {
        return this.total_size;
    }

    public String getVersion() {
        return this.version;
    }

    public int getWait_bytes() {
        return this.wait_bytes;
    }

    public void setCrc(int i) {
        this.crc = i;
    }

    public void setCurrent_size(int i) {
        this.current_size = i;
    }

    public void setE_type(OtaRequestType otaRequestType) {
        this.e_type = otaRequestType;
    }

    public void setIs_test_mode(int i) {
        this.is_test_mode = i;
    }

    public void setPop_up_interface(int i) {
        this.pop_up_interface = i;
    }

    public void setRes_addr(int i) {
        this.res_addr = i;
    }

    public void setTotal_size(int i) {
        this.total_size = i;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setWait_bytes(int i) {
        this.wait_bytes = i;
    }
}
