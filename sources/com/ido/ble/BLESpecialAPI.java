package com.ido.ble;

import com.ido.ble.bluetooth.device.BLEDevice;
import com.ido.ble.bluetooth.setting.BluetoothGattSettingListener;
import com.ido.ble.callback.DeviceGattCallBack;
import com.ido.ble.g.a.c.e;
import com.ido.ble.protocol.handler.u;
import com.ido.ble.protocol.model.SupportFunctionInfo;
/* loaded from: classes11.dex */
public class BLESpecialAPI {
    public static void forceSetBindStatus(boolean z) {
        if (isAllowed()) {
            if (z) {
                com.ido.ble.bluetooth.b.a();
            } else {
                com.ido.ble.bluetooth.b.c();
            }
        }
    }

    public static void getMTU() {
        if (isAllowed()) {
            u.d(317);
        }
    }

    private static boolean isAllowed() {
        return true;
    }

    public static void registerDeviceGattCallBack(DeviceGattCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().a(iCallBack);
    }

    public static void saveLastConnectedDeviceInfo(BLEDevice bLEDevice) {
        if (isAllowed()) {
            com.ido.ble.f.a.f.b.e().a(bLEDevice);
        }
    }

    public static void saveSupportFunctionInfo(SupportFunctionInfo supportFunctionInfo) {
        if (isAllowed()) {
            com.ido.ble.f.a.f.a.g0().a(supportFunctionInfo);
        }
    }

    public static void setBind(boolean z) {
        if (isAllowed()) {
            com.ido.ble.bluetooth.a.b(z);
        }
    }

    public static void setBindMacAddress(String str) {
        if (isAllowed()) {
            com.ido.ble.bluetooth.a.h(str);
        }
    }

    public static void setBluetoothGattSettingListener(BluetoothGattSettingListener.IListener iListener) {
        if (isAllowed()) {
            BluetoothGattSettingListener.setBluetoothGattSettingListener(iListener);
        }
    }

    public static void statScanStatus(boolean z) {
        e.b().a(z);
    }

    public static void unregisterDeviceGattCallBack(DeviceGattCallBack.ICallBack iCallBack) {
        com.ido.ble.callback.b.N().b(iCallBack);
    }
}
