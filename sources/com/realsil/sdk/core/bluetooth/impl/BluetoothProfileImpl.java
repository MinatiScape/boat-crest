package com.realsil.sdk.core.bluetooth.impl;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import androidx.annotation.RequiresPermission;
import com.realsil.sdk.core.bluetooth.utils.BluetoothHelper;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes12.dex */
public class BluetoothProfileImpl {
    public static final int A2DP_SINK = 11;
    public static final int AVRCP = 13;
    public static final int AVRCP_CONTROLLER = 12;
    public static final int HEADSET_CLIENT = 16;
    public static final int HID_HOST = 4;
    public static final int MAP = 9;
    public static final int MAP_CLIENT = 18;
    public static final int PAN = 5;
    public static final int PBAP = 6;
    public static final int PBAP_CLIENT = 17;

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                ZLogger.v("connectProfile :" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
                Method method = bluetoothProfile.getClass().getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (IllegalAccessException e) {
                ZLogger.w("Could not execute method 'connect' in profile " + bluetoothProfile.getClass().getName() + ", ignoring request." + e.toString());
                return false;
            } catch (NoSuchMethodException unused) {
                ZLogger.w("No connect method in the " + bluetoothProfile.getClass().getName() + " class, ignoring request.");
            } catch (InvocationTargetException e2) {
                ZLogger.w("Could not execute method 'connect' in profile " + bluetoothProfile.getClass().getName() + ", ignoring request." + e2.toString());
                return false;
            }
        }
        return false;
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                ZLogger.v(String.format("disconnect : %s : %s", bluetoothProfile.getClass().getName(), BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true)));
                Method method = bluetoothProfile.getClass().getMethod(MqttServiceConstants.DISCONNECT_ACTION, BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (IllegalAccessException e) {
                e = e;
                ZLogger.e("Could not execute method 'disconnect' in profile , ignoring request." + e.toString());
                return false;
            } catch (NoSuchMethodException unused) {
                ZLogger.e("No disconnect method in the  class, ignoring request.");
            } catch (InvocationTargetException e2) {
                e = e2;
                ZLogger.e("Could not execute method 'disconnect' in profile , ignoring request." + e.toString());
                return false;
            }
        }
        return false;
    }

    public static List<BluetoothDevice> getConnectedDevices(BluetoothProfile bluetoothProfile, String str) {
        if (bluetoothProfile == null) {
            return null;
        }
        try {
            Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("getConnectedDevices", null);
            method.setAccessible(true);
            return (List) method.invoke(bluetoothProfile, null);
        } catch (ClassNotFoundException e) {
            ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
            return null;
        } catch (IllegalAccessException e2) {
            e = e2;
            ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
            return null;
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the CLASS_NAME class, ignoring request.");
            return null;
        } catch (InvocationTargetException e3) {
            e = e3;
            ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
            return null;
        }
    }

    @RequiresPermission("android.permission.BLUETOOTH")
    @TargetApi(19)
    public static int getConnectionState(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod("getConnectionState", BluetoothDevice.class);
                method.setAccessible(true);
                return ((Integer) method.invoke(bluetoothProfile, bluetoothDevice)).intValue();
            } catch (ClassNotFoundException e) {
                ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
                return 0;
            } catch (IllegalAccessException e2) {
                e = e2;
                ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
                return 0;
            } catch (NoSuchMethodException unused) {
                ZLogger.w("No connect method in the CLASS_NAME class, ignoring request.");
            } catch (InvocationTargetException e3) {
                e = e3;
                ZLogger.w("Could not execute method 'connect' in profile CLASS_NAME, ignoring request." + e.toString());
                return 0;
            }
        }
        return 0;
    }

    public static void setPriority(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothProfile == null) {
            return;
        }
        try {
            bluetoothProfile.getClass().getMethod("setPriority", BluetoothDevice.class, Integer.TYPE).invoke(bluetoothProfile, bluetoothDevice, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean disconnect(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                ZLogger.v(String.format("disconnect : %s : %s", str, BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true)));
                Method method = bluetoothProfile.getClass().asSubclass(Class.forName(str)).getMethod(MqttServiceConstants.DISCONNECT_ACTION, BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (ClassNotFoundException e) {
                ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
                return false;
            } catch (IllegalAccessException e2) {
                ZLogger.e("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e2.toString());
                return false;
            } catch (NoSuchMethodException unused) {
                ZLogger.e("No disconnect method in the " + str + " class, ignoring request.");
            } catch (InvocationTargetException e3) {
                ZLogger.e("Could not execute method 'disconnect' in profile " + str + ", ignoring request." + e3.toString());
                return false;
            }
        }
        return false;
    }

    public static boolean connectProfile(BluetoothProfile bluetoothProfile, String str, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile != null && bluetoothDevice != null) {
            try {
                ZLogger.v("connectProfile :" + BluetoothHelper.formatAddress(bluetoothDevice.getAddress(), true));
                Class<? extends U> asSubclass = bluetoothProfile.getClass().asSubclass(Class.forName(str));
                if (asSubclass == 0) {
                    ZLogger.w("no class found: " + str);
                    return false;
                }
                Method method = asSubclass.getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class);
                method.setAccessible(true);
                return ((Boolean) method.invoke(bluetoothProfile, bluetoothDevice)).booleanValue();
            } catch (ClassNotFoundException e) {
                ZLogger.w("Could not find clas: " + str + ", ignoring request." + e.toString());
                return false;
            } catch (IllegalAccessException e2) {
                ZLogger.w("Could not execute method 'connect' in profile " + str + ", ignoring request." + e2.toString());
                return false;
            } catch (NoSuchMethodException unused) {
                ZLogger.w("No connect method in the " + str + " class, ignoring request.");
            } catch (InvocationTargetException e3) {
                ZLogger.w("Could not execute method 'connect' in profile " + str + ", ignoring request." + e3.toString());
                return false;
            }
        }
        return false;
    }
}
