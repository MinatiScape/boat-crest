package com.crrepa.n;

import com.crrepa.ble.conn.listener.CRPDeviceBatteryListener;
import com.crrepa.i0.c;
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public CRPDeviceBatteryListener f7770a;

    /* renamed from: com.crrepa.n.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0345a {

        /* renamed from: a  reason: collision with root package name */
        public static a f7771a = new a();
    }

    public static a a() {
        return C0345a.f7771a;
    }

    public void a(int i) {
        c.a("battery: " + i);
        CRPDeviceBatteryListener cRPDeviceBatteryListener = this.f7770a;
        if (cRPDeviceBatteryListener != null) {
            cRPDeviceBatteryListener.onDeviceBattery(i);
        }
    }

    public void a(CRPDeviceBatteryListener cRPDeviceBatteryListener) {
        this.f7770a = cRPDeviceBatteryListener;
    }

    public void a(boolean z) {
        CRPDeviceBatteryListener cRPDeviceBatteryListener = this.f7770a;
        if (cRPDeviceBatteryListener != null) {
            cRPDeviceBatteryListener.onSubscribe(z);
        }
    }
}
