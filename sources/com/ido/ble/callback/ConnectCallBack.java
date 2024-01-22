package com.ido.ble.callback;

import com.ido.ble.bluetooth.connect.ConnectFailedReason;
import com.ido.ble.bluetooth.device.BLEDevice;
/* loaded from: classes11.dex */
public class ConnectCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onConnectBreak(String str);

        void onConnectFailed(ConnectFailedReason connectFailedReason, String str);

        void onConnectStart(String str);

        void onConnectSuccess(String str);

        void onConnecting(String str);

        void onDeviceInNotBindStatus(String str);

        void onInDfuMode(BLEDevice bLEDevice);

        void onInitCompleted(String str);

        void onRetry(int i, String str);
    }

    public static void a(final int i, final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onRetry(i, str);
                    }
                }
            }
        });
    }

    public static final void a(final ConnectFailedReason connectFailedReason, final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onConnectFailed(ConnectFailedReason.this, str);
                    }
                }
            }
        });
    }

    public static final void a(final BLEDevice bLEDevice) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onInDfuMode(BLEDevice.this);
                    }
                }
            }
        });
    }

    public static final void a(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onConnectBreak(str);
                    }
                }
            }
        });
    }

    public static final void b(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onConnectStart(str);
                    }
                }
            }
        });
    }

    public static final void c(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onConnectSuccess(str);
                    }
                }
            }
        });
    }

    public static final void d(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onConnecting(str);
                    }
                }
            }
        });
    }

    public static final void e(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onDeviceInNotBindStatus(str);
                    }
                }
            }
        });
    }

    public static final void f(final String str) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ConnectCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().h()) {
                    if (iCallBack != null) {
                        iCallBack.onInitCompleted(str);
                    }
                }
            }
        });
    }
}
