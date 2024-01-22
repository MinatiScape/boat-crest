package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.os.Build;
import androidx.annotation.RequiresPermission;
import com.realsil.sdk.core.logger.ZLogger;
/* loaded from: classes12.dex */
public class BluetoothHeadsetImpl {
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV = "+IPHONEACCEV";
    public static final int VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV_BATTERY_LEVEL = 1;
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT = "+XEVENT";
    public static final String VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL = "BATTERY";

    public static int getBatteryLevel(String str, Object[] objArr) {
        str.hashCode();
        if (str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_IPHONEACCEV)) {
            return getBatteryLevelFromAppleBatteryVsc(objArr);
        }
        if (str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT)) {
            return getBatteryLevelFromXEventVsc(objArr);
        }
        return -1;
    }

    public static int getBatteryLevelFromAppleBatteryVsc(Object[] objArr) {
        int i;
        if (objArr.length == 0) {
            ZLogger.w("empty arguments");
            return -1;
        }
        int i2 = 0;
        if (objArr[0] instanceof Integer) {
            int intValue = ((Integer) objArr[0]).intValue();
            if (objArr.length != (intValue * 2) + 1) {
                ZLogger.w("number of arguments does not match");
                return -1;
            }
            while (true) {
                if (i2 >= intValue) {
                    i = -1;
                    break;
                }
                int i3 = i2 * 2;
                Object obj = objArr[i3 + 1];
                if (obj instanceof Integer) {
                    if (((Integer) obj).intValue() != 1) {
                        i2++;
                    } else {
                        Object obj2 = objArr[i3 + 2];
                        if (obj2 instanceof Integer) {
                            i = ((Integer) obj2).intValue();
                        } else {
                            ZLogger.w("error parsing indicator value");
                            return -1;
                        }
                    }
                } else {
                    ZLogger.w("error parsing indicator type");
                    return -1;
                }
            }
            if (i < 0 || i > 9) {
                return -1;
            }
            return (i + 1) * 10;
        }
        ZLogger.w("error parsing number of arguments");
        return -1;
    }

    public static int getBatteryLevelFromXEventVsc(Object[] objArr) {
        if (objArr.length == 0) {
            ZLogger.w("empty arguments");
            return -1;
        }
        Object obj = objArr[0];
        if (!(obj instanceof String)) {
            ZLogger.w("error parsing event name");
            return -1;
        }
        String str = (String) obj;
        if (!str.equals(VENDOR_SPECIFIC_HEADSET_EVENT_XEVENT_BATTERY_LEVEL)) {
            ZLogger.w("skip none BATTERY event: " + str);
            return -1;
        } else if (objArr.length != 5) {
            ZLogger.w("wrong battery level event length: " + String.valueOf(objArr.length));
            return -1;
        } else if ((objArr[1] instanceof Integer) && (objArr[2] instanceof Integer)) {
            int intValue = ((Integer) objArr[1]).intValue();
            int intValue2 = ((Integer) objArr[2]).intValue();
            if (intValue >= 0 && intValue2 > 1 && intValue <= intValue2) {
                return (intValue * 100) / (intValue2 - 1);
            }
            ZLogger.w("wrong event value, batteryLevel=" + String.valueOf(intValue) + ", numberOfLevels=" + String.valueOf(intValue2));
            return -1;
        } else {
            ZLogger.w("error parsing event values");
            return -1;
        }
    }

    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("startScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequiresPermission("android.permission.BLUETOOTH_ADMIN")
    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset) {
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("stopScoUsingVirtualVoiceCall", new Class[0]).invoke(bluetoothHeadset, new Object[0])).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean startScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return startScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("startScoUsingVirtualVoiceCall", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean stopScoUsingVirtualVoiceCall(BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (Build.VERSION.SDK_INT >= 28) {
            return stopScoUsingVirtualVoiceCall(bluetoothHeadset);
        }
        if (bluetoothHeadset == null) {
            return false;
        }
        try {
            return ((Boolean) bluetoothHeadset.getClass().getMethod("stopScoUsingVirtualVoiceCall", BluetoothDevice.class).invoke(bluetoothHeadset, bluetoothDevice)).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
