package com.ido.ble.callback;

import com.ido.ble.protocol.model.V3AppExchangeDataDeviceReplayEndData;
import com.ido.ble.protocol.model.V3AppExchangeDataHeartRate;
import com.ido.ble.protocol.model.V3AppExchangeDataIngDeviceReplyData;
/* loaded from: classes11.dex */
public class V3AppExchangeDataCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onReplyExchangeDataEndData(V3AppExchangeDataDeviceReplayEndData v3AppExchangeDataDeviceReplayEndData);

        void onReplyExchangeDateIng(V3AppExchangeDataIngDeviceReplyData v3AppExchangeDataIngDeviceReplyData);

        void onReplyExchangeHeartRateData(V3AppExchangeDataHeartRate v3AppExchangeDataHeartRate);
    }

    public static void a(final V3AppExchangeDataDeviceReplayEndData v3AppExchangeDataDeviceReplayEndData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.V3AppExchangeDataCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().L()) {
                    iCallBack.onReplyExchangeDataEndData(V3AppExchangeDataDeviceReplayEndData.this);
                }
            }
        });
    }

    public static void a(final V3AppExchangeDataHeartRate v3AppExchangeDataHeartRate) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.V3AppExchangeDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().L()) {
                    iCallBack.onReplyExchangeHeartRateData(V3AppExchangeDataHeartRate.this);
                }
            }
        });
    }

    public static void a(final V3AppExchangeDataIngDeviceReplyData v3AppExchangeDataIngDeviceReplyData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.V3AppExchangeDataCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().L()) {
                    iCallBack.onReplyExchangeDateIng(V3AppExchangeDataIngDeviceReplyData.this);
                }
            }
        });
    }
}
