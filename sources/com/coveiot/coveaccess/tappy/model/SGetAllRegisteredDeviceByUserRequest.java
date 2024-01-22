package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetAllRegisteredDeviceByUserRequest implements Serializable {
    private long endUserId;

    public long getEndUserId() {
        return this.endUserId;
    }

    public void setEndUserId(long j) {
        this.endUserId = j;
    }
}
