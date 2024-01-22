package com.crrepa.m;

import android.bluetooth.BluetoothGatt;
import java.lang.reflect.Method;
/* loaded from: classes9.dex */
public class b {

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ BluetoothGatt h;

        public a(BluetoothGatt bluetoothGatt) {
            this.h = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothGatt bluetoothGatt = this.h;
            if (bluetoothGatt != null) {
                bluetoothGatt.disconnect();
            }
        }
    }

    /* renamed from: com.crrepa.m.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public class RunnableC0344b implements Runnable {
        public final /* synthetic */ BluetoothGatt h;

        public RunnableC0344b(BluetoothGatt bluetoothGatt) {
            this.h = bluetoothGatt;
        }

        @Override // java.lang.Runnable
        public void run() {
            BluetoothGatt bluetoothGatt = this.h;
            if (bluetoothGatt != null) {
                b.d(bluetoothGatt);
                this.h.close();
            }
        }
    }

    public static synchronized void b(BluetoothGatt bluetoothGatt) {
        synchronized (b.class) {
            com.crrepa.g.a.a(new RunnableC0344b(bluetoothGatt), 0L);
        }
    }

    public static synchronized void c(BluetoothGatt bluetoothGatt) {
        synchronized (b.class) {
            com.crrepa.g.a.a(new a(bluetoothGatt), 0L);
        }
    }

    public static void d(BluetoothGatt bluetoothGatt) {
        try {
            Method method = BluetoothGatt.class.getMethod("refresh", new Class[0]);
            if (method == null || bluetoothGatt == null) {
                return;
            }
            boolean booleanValue = ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
            com.crrepa.i0.c.c("refreshDeviceCache, is success:  " + booleanValue);
        } catch (Exception e) {
            com.crrepa.i0.c.c("exception occur while refreshing device: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
