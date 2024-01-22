package com.apex.bluetooth.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class ClassicBluetoothUtils {
    public final String TAG = ClassicBluetoothUtils.class.getSimpleName();

    private boolean cancelBond(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            try {
                Method method = bluetoothDevice.getClass().getMethod("cancelBondProcess", new Class[0]);
                if (method != null) {
                    method.setAccessible(true);
                    return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    private boolean isConnect(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            try {
                Method method = bluetoothDevice.getClass().getMethod("isConnected", new Class[0]);
                if (method != null) {
                    method.setAccessible(true);
                    return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    private boolean reflectionBondBT(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            try {
                Method method = bluetoothDevice.getClass().getMethod("createBond", Integer.TYPE);
                if (method != null) {
                    method.setAccessible(true);
                    return ((Boolean) method.invoke(bluetoothDevice, 1)).booleanValue();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return false;
    }

    private void removeBond(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice != null) {
            try {
                ((Boolean) bluetoothDevice.getClass().getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0])).booleanValue();
                Log.e(this.TAG, "取消配对");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.TAG, "取消配对失败");
            }
        }
    }

    public void startBTConnect(String str) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled() && !TextUtils.isEmpty(str)) {
            try {
                BluetoothDevice remoteDevice = defaultAdapter.getRemoteDevice(str);
                if (remoteDevice != null) {
                    int bondState = remoteDevice.getBondState();
                    defaultAdapter.cancelDiscovery();
                    if (bondState == 10) {
                        if (reflectionBondBT(remoteDevice)) {
                            Log.e(this.TAG, "配对成功");
                            return;
                        } else {
                            Log.e(this.TAG, "配对失败");
                            return;
                        }
                    } else if (bondState == 12) {
                        Log.e(this.TAG, "已配对");
                        if (defaultAdapter.isEnabled()) {
                            defaultAdapter.getClass().getMethod("setActiveDevice", BluetoothDevice.class, Integer.TYPE).invoke(defaultAdapter, remoteDevice, 2);
                        }
                        if (isConnect(remoteDevice)) {
                            Log.e(this.TAG, "BT已连接");
                            return;
                        } else {
                            Log.e(this.TAG, "BT未连接");
                            return;
                        }
                    } else {
                        if (cancelBond(remoteDevice)) {
                            reflectionBondBT(remoteDevice);
                        }
                        Log.e(this.TAG, "正在配对中");
                        return;
                    }
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Log.e(this.TAG, "蓝牙不存在,或者蓝牙未打开,或者连接地址不存在");
    }
}
