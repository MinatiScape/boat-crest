package com.ido.ble.firmware.log;
/* loaded from: classes11.dex */
public interface ICollectDeviceRebootLogListener {
    void onFailed();

    void onStart();

    void onSuccess(String str);
}
