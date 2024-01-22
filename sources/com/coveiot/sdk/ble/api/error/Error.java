package com.coveiot.sdk.ble.api.error;
/* loaded from: classes9.dex */
public class Error {
    public String message;
    public String reqId;
    public boolean shouldClearCommandQueue = true;
    public Type type;

    public Error(Type type, String str) {
        this.type = type;
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    public String getReqId() {
        return this.reqId;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isShouldClearCommandQueue() {
        return this.shouldClearCommandQueue;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public void setShouldClearCommandQueue(boolean z) {
        this.shouldClearCommandQueue = z;
    }

    public String toString() {
        return "Error{type=" + this.type.toString() + ", message='" + this.message + "'}";
    }
}
