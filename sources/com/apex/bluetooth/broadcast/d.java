package com.apex.bluetooth.broadcast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class d extends Thread {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Context f2167a;
    public final /* synthetic */ a b;

    public d(a aVar, Context context) {
        this.b = aVar;
        this.f2167a = context;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            BluetoothDevice remoteDevice = defaultAdapter.getRemoteDevice(this.b.b);
            if (remoteDevice == null) {
                return;
            }
            int bondState = remoteDevice.getBondState();
            String str = this.b.f2163a;
            Log.e(str, "系统版本:" + Build.VERSION.SDK_INT + ",蓝牙类型:" + remoteDevice.getType());
            if (bondState == 10) {
                Log.e(this.b.f2163a, "8.0走直连配对");
                if (!TextUtils.isEmpty(Build.BRAND)) {
                    String str2 = Build.PRODUCT;
                    if (str2.equalsIgnoreCase("sagit") || str2.equalsIgnoreCase("starqltezc") || str2.equalsIgnoreCase("MHA-AL00")) {
                        remoteDevice.createBond();
                        return;
                    }
                }
                try {
                    Method declaredMethod = BluetoothDevice.class.getDeclaredMethod("createBond", Integer.TYPE);
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(remoteDevice, 1);
                } catch (IllegalAccessException e) {
                    String str3 = this.b.f2163a;
                    Log.e(str3, "反射时非法错误:" + e.getMessage());
                } catch (NoSuchMethodException unused) {
                    Log.e(this.b.f2163a, "找不到反射的方法");
                } catch (InvocationTargetException e2) {
                    String str4 = this.b.f2163a;
                    Log.e(str4, "反射时的错误:" + e2.getMessage());
                }
            } else if (bondState == 12) {
                Log.e(this.b.f2163a, "已配对");
                a.a(this.b, this.f2167a.getApplicationContext(), remoteDevice);
                a.b(this.b, this.f2167a.getApplicationContext(), remoteDevice);
            } else {
                Log.e(this.b.f2163a, "正在配对中");
            }
        }
        this.b.e = null;
    }
}
