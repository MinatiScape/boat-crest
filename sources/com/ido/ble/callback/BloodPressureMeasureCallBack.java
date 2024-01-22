package com.ido.ble.callback;

import com.ido.ble.protocol.model.BloodPressureMeasureDeviceReplyData;
/* loaded from: classes11.dex */
public class BloodPressureMeasureCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onReply(BloodPressureMeasureDeviceReplyData bloodPressureMeasureDeviceReplyData);
    }

    public static void a(final BloodPressureMeasureDeviceReplyData bloodPressureMeasureDeviceReplyData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.BloodPressureMeasureCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().g()) {
                    iCallBack.onReply(BloodPressureMeasureDeviceReplyData.this);
                }
            }
        });
    }
}
