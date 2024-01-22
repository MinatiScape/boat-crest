package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SDeleteRegisteredProductRequest implements Serializable {
    private transient long productRegistrationId;
    private transient long userId;

    public long getProductRegistrationId() {
        return this.productRegistrationId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setProductRegistrationId(long j) {
        this.productRegistrationId = j;
    }

    public void setUserId(long j) {
        this.userId = j;
    }
}
