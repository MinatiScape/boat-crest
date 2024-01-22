package com.ido.ble.callback;

import com.ido.ble.protocol.model.AppExchangeDataIngDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataPauseDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataResumeDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStartDeviceReplyData;
import com.ido.ble.protocol.model.AppExchangeDataStopDeviceReplyData;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataPausePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataResumePara;
import com.ido.ble.protocol.model.DeviceNoticeAppExchangeDataStopPara;
/* loaded from: classes11.dex */
public class AppExchangeDataCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onDeviceNoticeAppPause(DeviceNoticeAppExchangeDataPausePara deviceNoticeAppExchangeDataPausePara);

        void onDeviceNoticeAppResume(DeviceNoticeAppExchangeDataResumePara deviceNoticeAppExchangeDataResumePara);

        void onDeviceNoticeAppStop(DeviceNoticeAppExchangeDataStopPara deviceNoticeAppExchangeDataStopPara);

        void onReplyExchangeDataStart(AppExchangeDataStartDeviceReplyData appExchangeDataStartDeviceReplyData);

        void onReplyExchangeDateIng(AppExchangeDataIngDeviceReplyData appExchangeDataIngDeviceReplyData);

        void onReplyExchangeDatePause(AppExchangeDataPauseDeviceReplyData appExchangeDataPauseDeviceReplyData);

        void onReplyExchangeDateResume(AppExchangeDataResumeDeviceReplyData appExchangeDataResumeDeviceReplyData);

        void onReplyExchangeDateStop(AppExchangeDataStopDeviceReplyData appExchangeDataStopDeviceReplyData);
    }

    public static void a(final AppExchangeDataIngDeviceReplyData appExchangeDataIngDeviceReplyData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onReplyExchangeDateIng(AppExchangeDataIngDeviceReplyData.this);
                }
            }
        });
    }

    public static void a(final AppExchangeDataPauseDeviceReplyData appExchangeDataPauseDeviceReplyData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onReplyExchangeDatePause(AppExchangeDataPauseDeviceReplyData.this);
                }
            }
        });
    }

    public static void a(final AppExchangeDataResumeDeviceReplyData appExchangeDataResumeDeviceReplyData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onReplyExchangeDateResume(AppExchangeDataResumeDeviceReplyData.this);
                }
            }
        });
    }

    public static void a(final AppExchangeDataStartDeviceReplyData appExchangeDataStartDeviceReplyData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onReplyExchangeDataStart(AppExchangeDataStartDeviceReplyData.this);
                }
            }
        });
    }

    public static void a(final AppExchangeDataStopDeviceReplyData appExchangeDataStopDeviceReplyData) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onReplyExchangeDateStop(AppExchangeDataStopDeviceReplyData.this);
                }
            }
        });
    }

    public static void a(final DeviceNoticeAppExchangeDataPausePara deviceNoticeAppExchangeDataPausePara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onDeviceNoticeAppPause(DeviceNoticeAppExchangeDataPausePara.this);
                }
            }
        });
    }

    public static void a(final DeviceNoticeAppExchangeDataResumePara deviceNoticeAppExchangeDataResumePara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onDeviceNoticeAppResume(DeviceNoticeAppExchangeDataResumePara.this);
                }
            }
        });
    }

    public static void a(final DeviceNoticeAppExchangeDataStopPara deviceNoticeAppExchangeDataStopPara) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.AppExchangeDataCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().b()) {
                    iCallBack.onDeviceNoticeAppStop(DeviceNoticeAppExchangeDataStopPara.this);
                }
            }
        });
    }
}
