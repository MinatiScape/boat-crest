package com.ido.ble.callback;

import com.ido.ble.bluetooth.device.BLEDevice;
/* loaded from: classes11.dex */
public class ScanCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onFindDevice(BLEDevice bLEDevice);

        void onScanFinished();

        void onStart();
    }

    public static final void a() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ScanCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().A()) {
                    if (iCallBack != null) {
                        iCallBack.onScanFinished();
                    }
                }
            }
        });
    }

    public static final void a(final BLEDevice bLEDevice) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ScanCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().A()) {
                    if (iCallBack != null) {
                        iCallBack.onFindDevice(BLEDevice.this);
                    }
                }
            }
        });
    }

    public static final void b() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.ScanCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().A()) {
                    if (iCallBack != null) {
                        iCallBack.onStart();
                    }
                }
            }
        });
    }
}
