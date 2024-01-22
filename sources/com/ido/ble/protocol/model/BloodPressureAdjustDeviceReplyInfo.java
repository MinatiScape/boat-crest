package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class BloodPressureAdjustDeviceReplyInfo {
    private static final int FLAG_BUSY = 3;
    private static final int FLAG_FAIL = 6;
    private static final int FLAG_ING = 1;
    private static final int FLAG_INVALID = 4;
    private static final int FLAG_SPORT_MODE = 2;
    private static final int FLAG_SUCCESS = 0;
    public int ret_code;

    /* loaded from: classes11.dex */
    public enum BloodAdjustResult {
        SUCCESS,
        ADJUST_ING,
        DEVICE_IN_SPORT_MODE,
        DEVICE_BUSY,
        STATUS_INVALID,
        FAILED
    }

    public BloodAdjustResult toResult() {
        int i = this.ret_code;
        if (i == 0) {
            return BloodAdjustResult.SUCCESS;
        }
        if (i == 1) {
            return BloodAdjustResult.ADJUST_ING;
        }
        if (i == 2) {
            return BloodAdjustResult.DEVICE_IN_SPORT_MODE;
        }
        if (i == 3) {
            return BloodAdjustResult.DEVICE_BUSY;
        }
        if (i != 4 && i == 6) {
            return BloodAdjustResult.FAILED;
        }
        return BloodAdjustResult.STATUS_INVALID;
    }
}
