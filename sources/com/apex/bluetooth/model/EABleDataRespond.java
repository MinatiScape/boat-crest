package com.apex.bluetooth.model;
/* loaded from: classes.dex */
public class EABleDataRespond {
    public ResultCode e_error_code;
    public int request_id;

    /* loaded from: classes.dex */
    public enum ResultCode {
        success(0),
        fail(1);
        
        public int value;

        ResultCode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    public ResultCode getE_error_code() {
        return this.e_error_code;
    }

    public int getRequest_id() {
        return this.request_id;
    }

    public void setE_error_code(ResultCode resultCode) {
        this.e_error_code = resultCode;
    }

    public void setRequest_id(int i) {
        this.request_id = i;
    }
}
