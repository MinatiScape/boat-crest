package com.crrepa.d;

import com.crrepa.ble.conn.callback.CRPDeviceFirmwareVersionCallback;
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public CRPDeviceFirmwareVersionCallback f7698a;
    public boolean b;

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final c f7699a = new c();
    }

    public c() {
        this.b = false;
    }

    public static c a() {
        return b.f7699a;
    }

    public void a(CRPDeviceFirmwareVersionCallback cRPDeviceFirmwareVersionCallback) {
        this.f7698a = cRPDeviceFirmwareVersionCallback;
        this.b = false;
    }

    public void a(String str) {
        if (this.f7698a == null || this.b) {
            return;
        }
        this.b = true;
        com.crrepa.i0.c.a("onDeviceFirmwareVersion: " + str);
        this.f7698a.onDeviceFirmwareVersion(str);
    }
}
