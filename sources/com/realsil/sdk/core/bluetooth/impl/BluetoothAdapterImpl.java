package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothAdapter;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;
import java.util.Locale;
/* loaded from: classes12.dex */
public class BluetoothAdapterImpl {
    public static final String ACTION_BLE_ACL_CONNECTED = "android.bluetooth.adapter.action.BLE_ACL_CONNECTED";
    public static final String ACTION_BLE_ACL_DISCONNECTED = "android.bluetooth.adapter.action.BLE_ACL_DISCONNECTED";

    public static boolean setScanMode(BluetoothAdapter bluetoothAdapter, int i, int i2) {
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not enabled");
            return false;
        }
        ZLogger.v(String.format(Locale.US, "mode=%d, duration=%d", Integer.valueOf(i), Integer.valueOf(i2)));
        try {
            Class<?> cls = bluetoothAdapter.getClass();
            Class<?> cls2 = Integer.TYPE;
            Method method = cls.getMethod("setScanMode", cls2, cls2);
            method.setAccessible(true);
            return ((Boolean) method.invoke(bluetoothAdapter, Integer.valueOf(i), Integer.valueOf(i2))).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public int getConnectionState(BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter == null) {
            ZLogger.w("BT is not enabled");
            return 0;
        }
        try {
            Method method = bluetoothAdapter.getClass().getMethod("getConnectionState", null);
            method.setAccessible(true);
            return ((Integer) method.invoke(bluetoothAdapter, null)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
