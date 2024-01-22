package com.ido.ble.callback;

import com.ido.ble.protocol.model.DeviceExchangeDataIngPara;
import com.ido.ble.protocol.model.DeviceExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceExchangeDataStartPara;
import com.ido.ble.protocol.model.DeviceExchangeDataStopPara;
/* loaded from: classes11.dex */
public class DeviceExchangeDataCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onExchangeDataStart(DeviceExchangeDataStartPara deviceExchangeDataStartPara);

        void onExchangeDateIng(DeviceExchangeDataIngPara deviceExchangeDataIngPara);

        void onExchangeDatePause(DeviceExchangeDataPausePara deviceExchangeDataPausePara);

        void onExchangeDateResume(DeviceExchangeDataResumePara deviceExchangeDataResumePara);

        void onExchangeDateStop(DeviceExchangeDataStopPara deviceExchangeDataStopPara);
    }

    public static void a(final DeviceExchangeDataIngPara deviceExchangeDataIngPara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().j()) {
                    iCallBack.onExchangeDateIng(DeviceExchangeDataIngPara.this);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataPausePara deviceExchangeDataPausePara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().j()) {
                    iCallBack.onExchangeDatePause(DeviceExchangeDataPausePara.this);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataResumePara deviceExchangeDataResumePara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().j()) {
                    iCallBack.onExchangeDateResume(DeviceExchangeDataResumePara.this);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataStartPara deviceExchangeDataStartPara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().j()) {
                    iCallBack.onExchangeDataStart(DeviceExchangeDataStartPara.this);
                }
            }
        });
    }

    public static void a(final DeviceExchangeDataStopPara deviceExchangeDataStopPara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.DeviceExchangeDataCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().j()) {
                    iCallBack.onExchangeDateStop(DeviceExchangeDataStopPara.this);
                }
            }
        });
    }
}
