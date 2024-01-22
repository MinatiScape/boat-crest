package com.ido.ble.callback;

import com.ido.ble.protocol.model.BloodPressureAdjustDeviceReplyInfo;
/* loaded from: classes11.dex */
public class QueryStatusCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onQueryBloodAdjustResult(BloodPressureAdjustDeviceReplyInfo.BloodAdjustResult bloodAdjustResult);
    }

    public static final void onQueryBloodAdjustResult(final BloodPressureAdjustDeviceReplyInfo.BloodAdjustResult bloodAdjustResult) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.QueryStatusCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().y()) {
                    iCallBack.onQueryBloodAdjustResult(BloodPressureAdjustDeviceReplyInfo.BloodAdjustResult.this);
                }
            }
        });
    }
}
