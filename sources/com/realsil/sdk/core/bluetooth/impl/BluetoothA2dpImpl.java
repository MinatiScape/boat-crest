package com.realsil.sdk.core.bluetooth.impl;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothA2dp;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes12.dex */
public class BluetoothA2dpImpl {
    @TargetApi(19)
    public static void setAvrcpAbsoluteVolume(BluetoothA2dp bluetoothA2dp, int i) {
        if (bluetoothA2dp == null) {
            return;
        }
        try {
            bluetoothA2dp.getClass().getMethod("setAvrcpAbsoluteVolume", Integer.TYPE).invoke(bluetoothA2dp, Integer.valueOf(i));
        } catch (IllegalAccessException e) {
            e = e;
            ZLogger.w("Could not execute method 'connect' in profile " + bluetoothA2dp.getClass().getName() + ", ignoring request." + e.toString());
        } catch (NoSuchMethodException unused) {
            ZLogger.w("No connect method in the " + bluetoothA2dp.getClass().getName() + " class, ignoring request.");
        } catch (InvocationTargetException e2) {
            e = e2;
            ZLogger.w("Could not execute method 'connect' in profile " + bluetoothA2dp.getClass().getName() + ", ignoring request." + e.toString());
        }
    }
}
