package com.ido.ble.bluetooth.connect.r;

import com.clevertap.android.sdk.Constants;
import com.ido.ble.common.n;
import com.ido.ble.event.stat.one.d;
/* loaded from: classes11.dex */
public class a implements b {

    /* renamed from: a  reason: collision with root package name */
    private long f12096a = 0;
    private long b = 0;
    private com.ido.ble.event.stat.one.b c = new com.ido.ble.event.stat.one.b();
    private int d = -1;

    /* renamed from: com.ido.ble.bluetooth.connect.r.a$a  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public class C0576a implements n.b {
        public C0576a() {
        }

        @Override // com.ido.ble.common.n.b
        public void a() {
            com.ido.ble.event.stat.one.c.a(false);
            a.this.d = -1;
        }
    }

    private void a(String str) {
        this.c.a(str);
    }

    private String h() {
        com.ido.ble.event.stat.one.b bVar = this.c;
        return bVar != null ? bVar.b() : "null";
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void a() {
        if (n.a(this.d)) {
            com.ido.ble.event.stat.one.c.a(true);
            this.d = -1;
        }
        com.ido.ble.event.stat.one.c.a((System.currentTimeMillis() - this.b) / 1000, h(), d.R, "" + System.currentTimeMillis() + "--" + this.b);
        this.f12096a = System.currentTimeMillis();
        com.ido.ble.g.a.c.d.a();
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void a(int i, int i2) {
        String str = "(" + i + Constants.SEPARATOR_COMMA + i2 + ")";
        com.ido.ble.event.stat.one.c.d(d.W + str);
        a(d.W + str);
        com.ido.ble.g.a.c.d.a(d.W + str);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void b() {
        this.b = System.currentTimeMillis();
        this.c.a();
        this.d = n.a(new C0576a(), Constants.ONE_MIN_IN_MILLIS);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void b(int i, int i2) {
        com.ido.ble.event.stat.one.c.b("status=" + i + ",newState=" + i2);
        com.ido.ble.event.stat.one.c.a(((System.currentTimeMillis() - this.f12096a) / 1000) / 60);
        if (System.currentTimeMillis() - this.f12096a < 5000) {
            com.ido.ble.g.a.c.d.b("status=" + i + ",newState=" + i2);
        }
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void c() {
        com.ido.ble.g.a.c.d.b();
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void d() {
        com.ido.ble.event.stat.one.c.d(d.T);
        a(d.T);
        com.ido.ble.g.a.c.d.a(d.T);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void e() {
        com.ido.ble.event.stat.one.c.d(d.V);
        a(d.V);
        com.ido.ble.g.a.c.d.a(d.V);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void f() {
        com.ido.ble.event.stat.one.c.d(d.U);
        a(d.U);
        com.ido.ble.g.a.c.d.a(d.U);
    }

    @Override // com.ido.ble.bluetooth.connect.r.b
    public void g() {
        com.ido.ble.event.stat.one.c.d(d.Y);
        a(d.Y);
    }
}
