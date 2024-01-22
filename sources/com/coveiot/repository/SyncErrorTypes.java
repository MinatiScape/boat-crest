package com.coveiot.repository;
/* loaded from: classes9.dex */
public enum SyncErrorTypes {
    ERR_CMD_FAILED(301),
    ERR_TIMEOUT(302),
    ERR_SERVICE_BUSY(303),
    ERR_API_FAILURE(400);
    
    public int value;

    SyncErrorTypes(int i) {
        this.value = i;
    }
}
