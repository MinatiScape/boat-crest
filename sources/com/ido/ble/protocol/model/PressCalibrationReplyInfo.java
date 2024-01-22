package com.ido.ble.protocol.model;
/* loaded from: classes11.dex */
public class PressCalibrationReplyInfo {
    private static final int FLAG_CHARGE_ING = 2;
    private static final int FLAG_ING = 1;
    private static final int FLAG_NO_WEAR = 3;
    private static final int FLAG_SPORT_ING = 4;
    private static final int FLAG_SUCCESS = 0;
    public int ret_code;

    /* loaded from: classes11.dex */
    public enum PressCalibrationResult {
        SUCCESS,
        FAILED_CALIBRATION_ING,
        FAILED_CHARGE_ING,
        FAILED_NO_WEAR,
        FAILED_SPORT_ING,
        STATUS_INVALID
    }

    public PressCalibrationResult toResult() {
        int i = this.ret_code;
        return i == 0 ? PressCalibrationResult.SUCCESS : i == 1 ? PressCalibrationResult.FAILED_CALIBRATION_ING : i == 2 ? PressCalibrationResult.FAILED_CHARGE_ING : i == 3 ? PressCalibrationResult.FAILED_NO_WEAR : i == 4 ? PressCalibrationResult.FAILED_SPORT_ING : PressCalibrationResult.STATUS_INVALID;
    }
}
