package com.polidea.rxandroidble2.internal.util;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.content.Context;
import android.os.Build;
import bleshadow.javax.inject.Inject;
import com.polidea.rxandroidble2.internal.RxBleLog;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes12.dex */
public class BleConnectionCompat {

    /* renamed from: a  reason: collision with root package name */
    public final Context f13498a;

    @Inject
    public BleConnectionCompat(Context context) {
        this.f13498a = context;
    }

    public static boolean b(BluetoothGatt bluetoothGatt, BluetoothGattCallback bluetoothGattCallback, boolean z) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        RxBleLog.v("Connecting using reflection", new Object[0]);
        g(bluetoothGatt, z);
        Method declaredMethod = bluetoothGatt.getClass().getDeclaredMethod(MqttServiceConstants.CONNECT_ACTION, Boolean.class, BluetoothGattCallback.class);
        declaredMethod.setAccessible(true);
        return ((Boolean) declaredMethod.invoke(bluetoothGatt, Boolean.TRUE, bluetoothGattCallback)).booleanValue();
    }

    public static Object d(Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (obj == null) {
            return null;
        }
        return f(obj.getClass(), "getBluetoothGatt").invoke(obj, new Object[0]);
    }

    public static Object e() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            return null;
        }
        return f(defaultAdapter.getClass(), "getBluetoothManager").invoke(defaultAdapter, new Object[0]);
    }

    public static Method f(Class<?> cls, String str) throws NoSuchMethodException {
        Method declaredMethod = cls.getDeclaredMethod(str, new Class[0]);
        declaredMethod.setAccessible(true);
        return declaredMethod;
    }

    public static void g(BluetoothGatt bluetoothGatt, boolean z) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = bluetoothGatt.getClass().getDeclaredField("mAutoConnect");
        declaredField.setAccessible(true);
        declaredField.setBoolean(bluetoothGatt, z);
    }

    public final BluetoothGatt a(BluetoothGattCallback bluetoothGattCallback, BluetoothDevice bluetoothDevice, boolean z) {
        RxBleLog.v("Connecting without reflection", new Object[0]);
        if (Build.VERSION.SDK_INT >= 23) {
            return bluetoothDevice.connectGatt(this.f13498a, z, bluetoothGattCallback, 2);
        }
        return bluetoothDevice.connectGatt(this.f13498a, z, bluetoothGattCallback);
    }

    @TargetApi(23)
    public final BluetoothGatt c(Object obj, BluetoothDevice bluetoothDevice) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<?> constructor = BluetoothGatt.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        RxBleLog.v("Found constructor with args count = " + constructor.getParameterTypes().length, new Object[0]);
        return constructor.getParameterTypes().length == 4 ? (BluetoothGatt) constructor.newInstance(this.f13498a, obj, bluetoothDevice, 2) : (BluetoothGatt) constructor.newInstance(this.f13498a, obj, bluetoothDevice);
    }

    public BluetoothGatt connectGatt(BluetoothDevice bluetoothDevice, boolean z, BluetoothGattCallback bluetoothGattCallback) {
        if (bluetoothDevice == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 24 && z) {
            try {
                RxBleLog.v("Trying to connectGatt using reflection.", new Object[0]);
                Object d = d(e());
                if (d == null) {
                    RxBleLog.w("Couldn't get iBluetoothGatt object", new Object[0]);
                    return a(bluetoothGattCallback, bluetoothDevice, true);
                }
                BluetoothGatt c = c(d, bluetoothDevice);
                if (c == null) {
                    RxBleLog.w("Couldn't create BluetoothGatt object", new Object[0]);
                    return a(bluetoothGattCallback, bluetoothDevice, true);
                }
                if (!b(c, bluetoothGattCallback, true)) {
                    RxBleLog.w("Connection using reflection failed, closing gatt", new Object[0]);
                    c.close();
                }
                return c;
            } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
                RxBleLog.w(e, "Error while trying to connect via reflection", new Object[0]);
                return a(bluetoothGattCallback, bluetoothDevice, true);
            }
        }
        return a(bluetoothGattCallback, bluetoothDevice, z);
    }
}
