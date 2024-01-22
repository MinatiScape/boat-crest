package com.coveiot.coveaccess.tappy.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public class SGetUserRegisteredProductByUserIdAndPRegId implements Serializable {
    private long productId;
    private long userId;

    public long getProductId() {
        return this.productId;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setProductId(long j) {
        this.productId = j;
    }

    public void setUserId(long j) {
        this.userId = j;
    }
}
