package com.crrepa.d;

import com.crrepa.ble.conn.callback.CRPDeviceDfuStatusCallback;
import com.crrepa.ble.conn.callback.CRPDeviceDfuTypeCallback;
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public CRPDeviceDfuStatusCallback f7696a;
    public CRPDeviceDfuTypeCallback b;
    public boolean c;
    public boolean d;

    /* renamed from: com.crrepa.d.b$b  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0337b {

        /* renamed from: a  reason: collision with root package name */
        public static final b f7697a = new b();
    }

    public b() {
        this.c = false;
        this.d = false;
    }

    public static b a() {
        return C0337b.f7697a;
    }

    public void a(int i) {
        if (this.f7696a == null || this.c) {
            return;
        }
        this.c = true;
        com.crrepa.i0.c.a("onDeviceDfuStatus state: " + i);
        this.f7696a.onDeviceDfuStatus(i);
    }

    public void a(CRPDeviceDfuStatusCallback cRPDeviceDfuStatusCallback) {
        this.f7696a = cRPDeviceDfuStatusCallback;
        this.c = false;
    }

    public void a(CRPDeviceDfuTypeCallback cRPDeviceDfuTypeCallback) {
        this.b = cRPDeviceDfuTypeCallback;
        this.d = false;
    }

    public void b(int i) {
        CRPDeviceDfuTypeCallback cRPDeviceDfuTypeCallback = this.b;
        if (cRPDeviceDfuTypeCallback == null || this.d) {
            return;
        }
        this.d = true;
        cRPDeviceDfuTypeCallback.onDfuType(i);
    }
}
