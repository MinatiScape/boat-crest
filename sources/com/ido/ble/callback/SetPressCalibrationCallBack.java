package com.ido.ble.callback;

import com.ido.ble.protocol.model.PressCalibrationReplyInfo;
/* loaded from: classes11.dex */
public class SetPressCalibrationCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onSetPressCalibrationResult(PressCalibrationReplyInfo.PressCalibrationResult pressCalibrationResult);
    }

    public static final void onSetPressCalibrationResult(final PressCalibrationReplyInfo.PressCalibrationResult pressCalibrationResult) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SetPressCalibrationCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().B()) {
                    iCallBack.onSetPressCalibrationResult(PressCalibrationReplyInfo.PressCalibrationResult.this);
                }
            }
        });
    }
}
