package com.coveiot.android.bleabstract.error;
/* loaded from: classes2.dex */
public enum CommandError {
    COMMAND_FAILED(301),
    COMMAND_ERROR_TIMEOUT(302),
    SERVICE_BUSY(303),
    WATCH_BUSY(304),
    COMMAND_WRITE_FAILED(305);
    
    public int value;

    CommandError(int i) {
        this.value = i;
    }
}
