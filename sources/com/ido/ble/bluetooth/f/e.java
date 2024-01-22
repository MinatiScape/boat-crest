package com.ido.ble.bluetooth.f;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import com.ido.ble.LocalDataManager;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import java.lang.reflect.Method;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes11.dex */
public class e {
    public static BluetoothDevice a(String str) {
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (str.equals(bluetoothDevice.getAddress())) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    public static String a() {
        String str = "phone has paired list:\n";
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (bluetoothDevice != null) {
                str = str + bluetoothDevice.getAddress() + MqttTopic.TOPIC_LEVEL_SEPARATOR + bluetoothDevice.getName() + "\n";
            }
        }
        return str;
    }

    public static boolean a(BluetoothDevice bluetoothDevice) {
        if (BluetoothAdapter.getDefaultAdapter() == null) {
            return false;
        }
        try {
            Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("isConnected", null);
            declaredMethod.setAccessible(true);
            return ((Boolean) declaredMethod.invoke(bluetoothDevice, null)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static BluetoothDevice b(String str) {
        for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
            if (bluetoothDevice != null && !TextUtils.isEmpty(bluetoothDevice.getAddress()) && bluetoothDevice.getAddress().endsWith(str)) {
                return bluetoothDevice;
            }
        }
        return null;
    }

    public static boolean b() {
        SupportFunctionInfo supportFunctionInfo = LocalDataManager.getSupportFunctionInfo();
        if (supportFunctionInfo != null) {
            return supportFunctionInfo.V3_dev_support_pair_each_connect;
        }
        return false;
    }

    public static boolean b(BluetoothDevice bluetoothDevice) {
        try {
            bluetoothDevice.getClass().getMethod("removeBond", null).invoke(bluetoothDevice, null);
            return c(bluetoothDevice.getAddress());
        } catch (Exception e) {
            LogTool.b("PairedDeviceUtils", e.getMessage());
            return false;
        }
    }

    public static boolean c(String str) {
        return a(str) != null;
    }

    public static boolean d(String str) {
        BluetoothDevice a2 = a(str);
        if (a2 != null) {
            return b(a2);
        }
        return true;
    }
}
