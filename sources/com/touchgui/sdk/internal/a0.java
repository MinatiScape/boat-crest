package com.touchgui.sdk.internal;

import android.content.Context;
import com.touchgui.sdk.TGConnectionListener;
import com.touchgui.sdk.TGLogManager;
import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes12.dex */
public final class a0 implements e2 {
    public final int A;
    public final int C;
    public final t F;

    /* renamed from: a  reason: collision with root package name */
    public int f13739a;
    public int b;
    public final Context d;
    public final g e;
    public final i3 f;
    public ArrayList h;
    public final t9 i;
    public l9 k;
    public com.touchgui.sdk.l l;
    public final com.touchgui.sdk.o m;
    public final ma n;
    public final oa o;
    public e5 p;
    public com.touchgui.sdk.k q;
    public com.touchgui.sdk.j r;
    public com.touchgui.sdk.c s;
    public com.touchgui.sdk.h t;
    public com.touchgui.sdk.m u;
    public w3 v;
    public final boolean w;
    public final int y;
    public int c = 0;
    public final CopyOnWriteArrayList g = new CopyOnWriteArrayList();
    public final d2 j = new d2(this);
    public boolean x = false;
    public int z = 0;
    public int B = 0;
    public int D = 0;
    public String E = null;

    public a0(z zVar) {
        t tVar = new t(this);
        this.F = tVar;
        this.d = z.a(zVar);
        g gVar = new g(this);
        this.e = gVar;
        gVar.a(tVar);
        this.f = new i3(this);
        this.i = new t9(this);
        this.m = new com.touchgui.sdk.o(this);
        this.o = new oa(this);
        this.n = new ma(this);
        this.w = z.b(zVar);
        this.y = z.c(zVar);
        this.A = 3;
        this.C = 3;
    }

    public static void a(a0 a0Var, TGDevice tGDevice, boolean z) {
        a0Var.getClass();
        String address = tGDevice.getAddress();
        TGLogger.d(address, "ready:name=" + tGDevice.getName() + "init func=" + z);
        Iterator it = a0Var.g.iterator();
        while (it.hasNext()) {
            ((TGConnectionListener) it.next()).onReady(tGDevice);
        }
        if (z) {
            a0Var.B = 0;
            a0Var.z = 0;
        }
        if (r.f13821a.booleanValue() && a0Var.f13739a != 0) {
            int a2 = c.a(tGDevice.getAddress());
            if (a2 != 0) {
                a0Var.D = a2;
            } else {
                new v8(a0Var, new x7()).execute(new y(a0Var, tGDevice));
            }
        }
    }

    public final Context b() {
        return this.d;
    }

    public final String c() {
        return this.e.b.n;
    }

    public final int d() {
        if (this.v == null) {
            return -1;
        }
        return this.f13739a;
    }

    public final x3 e() {
        if (this.s == null) {
            this.s = new com.touchgui.sdk.c(this);
        }
        return this.s;
    }

    public final TGLogManager f() {
        if (this.u == null) {
            com.touchgui.sdk.m mVar = new com.touchgui.sdk.m(this);
            this.u = mVar;
            if (this.h == null) {
                this.h = new ArrayList();
            }
            if (!this.h.contains(mVar)) {
                this.h.add(mVar);
            }
        }
        return this.u;
    }

    public final int g() {
        int i = this.b;
        if (i == 6 || i == 50) {
            return this.y;
        }
        return 0;
    }

    public final boolean h() {
        return this.e.b.k == 2;
    }

    public final boolean i() {
        return ((this.c & 65280) >> 8) == 3 || this.f13739a == 506;
    }

    public final com.touchgui.sdk.k j() {
        return new com.touchgui.sdk.k(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(TGDevice tGDevice, String str) {
        String address = tGDevice.getAddress();
        int a2 = c.a(address, this.f13739a, str);
        c.f13749a.put(address, Integer.valueOf(a2));
        if (Objects.equals(this.e.b.n, tGDevice.getAddress())) {
            this.D = a2;
        }
    }

    public final void a(final TGDevice tGDevice, final String str) {
        new Thread(new Runnable() { // from class: com.touchgui.sdk.internal.ub
            @Override // java.lang.Runnable
            public final void run() {
                a0.this.b(tGDevice, str);
            }
        }).start();
    }

    public final boolean a(String str, boolean z) {
        boolean z2;
        TGLogger.d(str, MqttServiceConstants.CONNECT_ACTION);
        g gVar = this.e;
        if (gVar.b.e()) {
            q qVar = gVar.b;
            qVar.j = gVar;
            qVar.i = gVar;
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            String c = c();
            if (str != null && !str.equals(c)) {
                this.f13739a = 0;
                this.b = 0;
                this.v = null;
                this.c = 0;
            }
            g gVar2 = this.e;
            boolean z3 = this.w;
            q qVar2 = gVar2.b;
            qVar2.d = z3;
            gVar2.g = this.F;
            gVar2.h = this;
            return qVar2.a(str, z);
        }
        return false;
    }

    public final void a() {
        TGLogger.d(c(), MqttServiceConstants.DISCONNECT_ACTION);
        g gVar = this.e;
        q qVar = gVar.b;
        synchronized (qVar.l) {
            qVar.h();
        }
        gVar.b.a(true, false);
        this.v = null;
        this.D = 0;
    }

    public final boolean a(int i) {
        if (this.v == null) {
            TGLogger.w(c(), "Call after onReady");
        }
        if (i == 34017025 && i()) {
            return true;
        }
        if (i == 34017028 && i()) {
            return true;
        }
        w3 w3Var = this.v;
        return w3Var != null && w3Var.a(i);
    }
}
