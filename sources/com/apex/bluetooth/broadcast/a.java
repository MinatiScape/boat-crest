package com.apex.bluetooth.broadcast;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes.dex */
public class a extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final String f2163a = a.class.getSimpleName();
    public String b;
    public BluetoothHeadset c;
    public BluetoothA2dp d;
    public Thread e;

    /* renamed from: com.apex.bluetooth.broadcast.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0202a extends Thread {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Context f2164a;
        public final /* synthetic */ BluetoothDevice b;

        public C0202a(Context context, BluetoothDevice bluetoothDevice) {
            this.f2164a = context;
            this.b = bluetoothDevice;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            a.a(a.this, this.f2164a.getApplicationContext(), this.b);
            a.b(a.this, this.f2164a.getApplicationContext(), this.b);
            a.this.e = null;
        }
    }

    public static void a(a aVar, Context context, BluetoothDevice bluetoothDevice) {
        Objects.requireNonNull(aVar);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter.isEnabled()) {
            defaultAdapter.getProfileProxy(context.getApplicationContext(), new b(aVar, bluetoothDevice, context), 1);
        }
    }

    public static void b(a aVar, Context context, BluetoothDevice bluetoothDevice) {
        Objects.requireNonNull(aVar);
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter.isEnabled()) {
            defaultAdapter.getProfileProxy(context.getApplicationContext(), new c(aVar, bluetoothDevice, context), 2);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        BluetoothDevice bluetoothDevice;
        BluetoothDevice bluetoothDevice2;
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            return;
        }
        if (action.equalsIgnoreCase("android.bluetooth.device.action.BOND_STATE_CHANGED")) {
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", -1);
            BluetoothDevice bluetoothDevice3 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice3 != null && bluetoothDevice3.getAddress().equalsIgnoreCase(this.b)) {
                String str = this.f2163a;
                Log.e(str, "蓝牙地址:" + bluetoothDevice3.getAddress());
            }
            switch (intExtra) {
                case 10:
                    if (bluetoothDevice3 == null || !bluetoothDevice3.getAddress().equalsIgnoreCase(this.b)) {
                        return;
                    }
                    Log.e(this.f2163a, "BOND_NONE 删除配对");
                    return;
                case 11:
                    if (bluetoothDevice3 == null || !bluetoothDevice3.getAddress().equalsIgnoreCase(this.b)) {
                        return;
                    }
                    Log.e(this.f2163a, "BOND_BONDING 正在配对");
                    return;
                case 12:
                    if (bluetoothDevice3 == null || !bluetoothDevice3.getAddress().equalsIgnoreCase(this.b)) {
                        return;
                    }
                    String str2 = Build.PRODUCT;
                    if (TextUtils.isEmpty(str2) || !(str2.equalsIgnoreCase("sagit") || str2.equalsIgnoreCase("walleye") || str2.equalsIgnoreCase("starqltezc"))) {
                        Thread thread = this.e;
                        if (thread != null) {
                            thread.interrupt();
                            this.e = null;
                        }
                        C0202a c0202a = new C0202a(context, bluetoothDevice3);
                        this.e = c0202a;
                        c0202a.start();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (action.equalsIgnoreCase("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED")) {
            int intExtra2 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
            String str3 = this.f2163a;
            Log.e(str3, "当前A2DP状态:" + intExtra2);
            if (intExtra2 == 2 && BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1) == 2 && a()) {
                Log.e(this.f2163a, "蓝牙已连接");
            }
        } else if (action.equalsIgnoreCase("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED")) {
            int intExtra3 = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
            String str4 = this.f2163a;
            Log.e(str4, "当前HEADSET状态:" + intExtra3);
            if (intExtra3 == 2 && BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(2) == 2 && a()) {
                Log.e(this.f2163a, "蓝牙已连接");
            }
        } else if (action.equalsIgnoreCase("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
            if (intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", 0) == 0 && (bluetoothDevice2 = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE")) != null && bluetoothDevice2.getAddress().equalsIgnoreCase(this.b)) {
                Log.e(this.f2163a, "设备断开连接");
            }
        } else if (action.equalsIgnoreCase("east.apex.bluetooth.a2dp.proxy")) {
            BluetoothDevice bluetoothDevice4 = (BluetoothDevice) intent.getParcelableExtra("device");
            if (bluetoothDevice4 == null || !bluetoothDevice4.getAddress().equalsIgnoreCase(this.b) || BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(2) != 0 || this.d == null) {
                return;
            }
            try {
                Log.e(this.f2163a, "开始连接A2DP");
                BluetoothA2dp.class.getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class).invoke(this.d, bluetoothDevice4);
            } catch (Exception e) {
                String str5 = this.f2163a;
                Log.e(str5, "A2DP:" + e.getMessage());
            }
        } else if (action.equalsIgnoreCase("east.apex.bluetooth.headset.proxy") && (bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("device")) != null && bluetoothDevice.getAddress().equalsIgnoreCase(this.b) && BluetoothAdapter.getDefaultAdapter().getProfileConnectionState(1) == 0 && this.c != null) {
            try {
                Log.e(this.f2163a, "开始连接HeadSet");
                BluetoothHeadset.class.getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class).invoke(this.c, bluetoothDevice);
            } catch (Exception e2) {
                String str6 = this.f2163a;
                Log.e(str6, "headSet:" + e2.getMessage());
            }
        }
    }

    public final boolean a() {
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            Method declaredMethod = defaultAdapter.getClass().getDeclaredMethod("getConnectionState", null);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(defaultAdapter, null)).intValue() == 2;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return false;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return false;
        }
    }
}
