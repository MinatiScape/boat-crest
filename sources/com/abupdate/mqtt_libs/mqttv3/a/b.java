package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.c.s;
import com.abupdate.mqtt_libs.mqttv3.a.c.t;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import java.io.EOFException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
/* loaded from: classes.dex */
public class b {
    public com.abupdate.mqtt_libs.mqttv3.l A;
    public volatile Vector c;
    public f e;
    public a f;
    public c g;
    public long h;
    public boolean i;
    public com.abupdate.mqtt_libs.mqttv3.g j;
    public int l;
    public int m;
    public Hashtable w;
    public Hashtable x;
    public Hashtable y;
    public Hashtable z;

    /* renamed from: a  reason: collision with root package name */
    public int f1945a = 0;
    public int k = 0;
    public Object n = new Object();
    public Object o = new Object();
    public boolean p = false;
    public long q = 0;
    public long r = 0;
    public Object t = new Object();
    public int u = 0;
    public boolean v = false;
    public Hashtable b = new Hashtable();
    public volatile Vector d = new Vector();
    public u s = new com.abupdate.mqtt_libs.mqttv3.a.c.i();

    public b(com.abupdate.mqtt_libs.mqttv3.g gVar, f fVar, c cVar, a aVar, com.abupdate.mqtt_libs.mqttv3.l lVar) throws MqttException {
        this.f = null;
        this.g = null;
        this.l = 0;
        this.m = 0;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.w = new Hashtable();
        this.x = new Hashtable();
        this.y = new Hashtable();
        this.z = new Hashtable();
        this.m = 0;
        this.l = 0;
        this.j = gVar;
        this.g = cVar;
        this.e = fVar;
        this.f = aVar;
        this.A = lVar;
        d();
    }

    public void a(int i) {
        this.k = i;
        this.c = new Vector(this.k);
    }

    public boolean b() {
        return this.i;
    }

    public void c() throws MqttException {
        this.j.c();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        this.w.clear();
        this.x.clear();
        this.y.clear();
        this.z.clear();
        this.e.d();
    }

    public final void d(Vector vector, u uVar) {
        int j = uVar.j();
        for (int i = 0; i < vector.size(); i++) {
            if (((u) vector.elementAt(i)).j() > j) {
                vector.insertElementAt(uVar, i);
                return;
            }
        }
        vector.addElement(uVar);
    }

    public u e() throws MqttException {
        synchronized (this.n) {
            u uVar = null;
            while (uVar == null) {
                if ((this.c.isEmpty() && this.d.isEmpty()) || (this.d.isEmpty() && this.l >= this.k)) {
                    try {
                        this.n.wait();
                    } catch (InterruptedException unused) {
                    }
                }
                if (!this.v && (this.d.isEmpty() || !(((u) this.d.elementAt(0)) instanceof com.abupdate.mqtt_libs.mqttv3.a.c.d))) {
                    return null;
                }
                if (!this.d.isEmpty()) {
                    uVar = (u) this.d.remove(0);
                    if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.n) {
                        this.m++;
                    }
                    f();
                } else if (!this.c.isEmpty() && this.l < this.k) {
                    uVar = (u) this.c.elementAt(0);
                    this.c.removeElementAt(0);
                    this.l++;
                }
            }
            return uVar;
        }
    }

    public final String f(u uVar) {
        return "s-" + uVar.j();
    }

    public final String g(u uVar) {
        return "sc-" + uVar.j();
    }

    public final String h(u uVar) {
        return "r-" + uVar.j();
    }

    public final String i(u uVar) {
        return "sb-" + uVar.j();
    }

    public int j() {
        return this.k;
    }

    public void k() {
        this.b.clear();
        if (this.c != null) {
            this.c.clear();
        }
        this.d.clear();
        this.w.clear();
        this.x.clear();
        this.y.clear();
        this.z.clear();
        this.e.d();
        this.b = null;
        this.c = null;
        this.d = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.e = null;
        this.g = null;
        this.f = null;
        this.j = null;
        this.s = null;
    }

    public final void l() {
        this.c = new Vector(this.k);
        this.d = new Vector();
        Enumeration keys = this.w.keys();
        while (keys.hasMoreElements()) {
            u uVar = (u) this.w.get(keys.nextElement());
            if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.o) {
                uVar.a(true);
                d(this.c, (com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar);
            } else if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.n) {
                d(this.d, (com.abupdate.mqtt_libs.mqttv3.a.c.n) uVar);
            }
        }
        Enumeration keys2 = this.x.keys();
        while (keys2.hasMoreElements()) {
            com.abupdate.mqtt_libs.mqttv3.a.c.o oVar = (com.abupdate.mqtt_libs.mqttv3.a.c.o) this.x.get(keys2.nextElement());
            oVar.a(true);
            d(this.c, oVar);
        }
        Enumeration keys3 = this.y.keys();
        while (keys3.hasMoreElements()) {
            Object nextElement = keys3.nextElement();
            d(this.c, (com.abupdate.mqtt_libs.mqttv3.a.c.o) this.y.get(nextElement));
        }
        this.d = c(this.d);
        this.c = c(this.c);
    }

    public final void m() {
        synchronized (this.n) {
            this.l--;
            if (!f()) {
                this.n.notifyAll();
            }
        }
    }

    public final synchronized int n() throws MqttException {
        int i;
        int i2 = this.f1945a;
        int i3 = 0;
        do {
            int i4 = this.f1945a + 1;
            this.f1945a = i4;
            if (i4 > 65535) {
                this.f1945a = 1;
            }
            i = this.f1945a;
            if (i == i2 && (i3 = i3 + 1) == 2) {
                throw i.a(32001);
            }
        } while (this.b.containsKey(new Integer(i)));
        Integer num = new Integer(this.f1945a);
        this.b.put(num, num);
        return this.f1945a;
    }

    public final u b(String str, com.abupdate.mqtt_libs.mqttv3.j jVar) throws MqttException {
        try {
            return u.a(jVar);
        } catch (MqttException e) {
            if (e.getCause() instanceof EOFException) {
                if (str != null) {
                    this.j.b(str);
                }
                return null;
            }
            throw e;
        }
    }

    public boolean f() {
        int e = this.e.e();
        if (this.p && e == 0 && this.d.size() == 0 && this.g.c()) {
            synchronized (this.o) {
                this.o.notifyAll();
            }
            return true;
        }
        return false;
    }

    public void g() {
        this.v = true;
        this.A.a();
    }

    public void h() {
        synchronized (this.n) {
            this.n.notifyAll();
        }
    }

    public int i() {
        return this.l;
    }

    public void a(long j) {
        this.h = j * 1000;
    }

    public long a() {
        return this.h;
    }

    public void a(boolean z) {
        this.i = z;
    }

    public void a(u uVar, com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        if (uVar.f_() && uVar.j() == 0) {
            if ((uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.o) && ((com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar).h().getQos() != 0) {
                uVar.a(n());
            } else if ((uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.k) || (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.m) || (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.n) || (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.l) || (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.r) || (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.q) || (uVar instanceof t) || (uVar instanceof s)) {
                uVar.a(n());
            }
        }
        if (nVar != null) {
            try {
                nVar.f1970a.a(uVar.j());
            } catch (Exception unused) {
            }
        }
        if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.o) {
            synchronized (this.n) {
                if (this.l < this.k) {
                    int qos = ((com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar).h().getQos();
                    if (qos == 1) {
                        this.x.put(new Integer(uVar.j()), uVar);
                        this.j.a(f(uVar), (com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar);
                    } else if (qos == 2) {
                        this.w.put(new Integer(uVar.j()), uVar);
                        this.j.a(f(uVar), (com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar);
                    }
                    this.e.a(nVar, uVar);
                    this.c.addElement(uVar);
                    this.n.notifyAll();
                } else {
                    throw new MqttException(32202);
                }
            }
        } else if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.d) {
            synchronized (this.n) {
                this.e.a(nVar, uVar);
                this.d.insertElementAt(uVar, 0);
                this.n.notifyAll();
            }
        } else {
            if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.i) {
                this.s = uVar;
            } else if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.n) {
                this.w.put(new Integer(uVar.j()), uVar);
                this.j.a(g(uVar), (com.abupdate.mqtt_libs.mqttv3.a.c.n) uVar);
            } else if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.l) {
                this.j.b(h(uVar));
            }
            synchronized (this.n) {
                if (!(uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.b)) {
                    this.e.a(nVar, uVar);
                }
                this.d.addElement(uVar);
                this.n.notifyAll();
            }
        }
    }

    public void b(u uVar) {
        try {
            this.j.b(i(uVar));
        } catch (com.abupdate.mqtt_libs.mqttv3.k unused) {
        }
    }

    public void b(int i) {
        if (i > 0) {
            this.q = System.currentTimeMillis();
        }
    }

    public void d() throws MqttException {
        Enumeration b = this.j.b();
        int i = this.f1945a;
        Vector vector = new Vector();
        while (b.hasMoreElements()) {
            String str = (String) b.nextElement();
            u b2 = b(str, this.j.a(str));
            if (b2 != null) {
                if (str.startsWith("r-")) {
                    this.z.put(new Integer(b2.j()), b2);
                } else if (str.startsWith("s-")) {
                    com.abupdate.mqtt_libs.mqttv3.a.c.o oVar = (com.abupdate.mqtt_libs.mqttv3.a.c.o) b2;
                    i = Math.max(oVar.j(), i);
                    if (this.j.c(g(oVar))) {
                        com.abupdate.mqtt_libs.mqttv3.a.c.n nVar = (com.abupdate.mqtt_libs.mqttv3.a.c.n) b(str, this.j.a(g(oVar)));
                        if (nVar != null) {
                            this.w.put(new Integer(nVar.j()), nVar);
                        }
                    } else {
                        oVar.a(true);
                        if (oVar.h().getQos() == 2) {
                            this.w.put(new Integer(oVar.j()), oVar);
                        } else {
                            this.x.put(new Integer(oVar.j()), oVar);
                        }
                    }
                    this.e.a(oVar).f1970a.a(this.f.i());
                    this.b.put(new Integer(oVar.j()), new Integer(oVar.j()));
                } else if (str.startsWith("sb-")) {
                    com.abupdate.mqtt_libs.mqttv3.a.c.o oVar2 = (com.abupdate.mqtt_libs.mqttv3.a.c.o) b2;
                    i = Math.max(oVar2.j(), i);
                    if (oVar2.h().getQos() == 2) {
                        this.w.put(new Integer(oVar2.j()), oVar2);
                    } else if (oVar2.h().getQos() == 1) {
                        this.x.put(new Integer(oVar2.j()), oVar2);
                    } else {
                        this.y.put(new Integer(oVar2.j()), oVar2);
                        this.j.b(str);
                    }
                    this.e.a(oVar2).f1970a.a(this.f.i());
                    this.b.put(new Integer(oVar2.j()), new Integer(oVar2.j()));
                } else if (str.startsWith("sc-") && !this.j.c(f((com.abupdate.mqtt_libs.mqttv3.a.c.n) b2))) {
                    vector.addElement(str);
                }
            }
        }
        Enumeration elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.j.b((String) elements.nextElement());
        }
        this.f1945a = i;
    }

    public void b(MqttException mqttException) {
        this.v = false;
        try {
            if (this.i) {
                c();
            }
            this.c.clear();
            this.d.clear();
            synchronized (this.t) {
                this.u = 0;
            }
        } catch (MqttException unused) {
        }
    }

    public final Vector c(Vector vector) {
        Vector vector2 = new Vector();
        if (vector.size() == 0) {
            return vector2;
        }
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < vector.size()) {
            int j = ((u) vector.elementAt(i)).j();
            int i5 = j - i2;
            if (i5 > i3) {
                i4 = i;
                i3 = i5;
            }
            i++;
            i2 = j;
        }
        if ((65535 - i2) + ((u) vector.elementAt(0)).j() > i3) {
            i4 = 0;
        }
        for (int i6 = i4; i6 < vector.size(); i6++) {
            vector2.addElement(vector.elementAt(i6));
        }
        for (int i7 = 0; i7 < i4; i7++) {
            vector2.addElement(vector.elementAt(i7));
        }
        return vector2;
    }

    public void b(long j) {
        if (j > 0) {
            synchronized (this.n) {
                this.p = true;
            }
            this.g.b();
            h();
            synchronized (this.o) {
                try {
                    if (this.e.e() > 0 || this.d.size() > 0 || !this.g.c()) {
                        this.o.wait(j);
                    }
                } catch (InterruptedException unused) {
                }
            }
            synchronized (this.n) {
                this.c.clear();
                this.d.clear();
                this.p = false;
                this.l = 0;
            }
        }
    }

    public void c(u uVar) {
        this.q = System.currentTimeMillis();
        com.abupdate.mqtt_libs.mqttv3.n a2 = this.e.a(uVar);
        a2.f1970a.i();
        if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.i) {
            synchronized (this.t) {
                System.currentTimeMillis();
                synchronized (this.t) {
                    this.u++;
                }
            }
        } else if ((uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.o) && ((com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar).h().getQos() == 0) {
            a2.f1970a.a(null, null);
            this.g.b(a2);
            m();
            e(uVar.j());
            this.e.b(uVar);
            f();
        }
    }

    public final synchronized void e(int i) {
        this.b.remove(new Integer(i));
    }

    public void b(com.abupdate.mqtt_libs.mqttv3.a.c.o oVar) throws com.abupdate.mqtt_libs.mqttv3.k {
        this.j.b(h(oVar));
        this.z.remove(new Integer(oVar.j()));
    }

    public void c(int i) {
        if (i > 0) {
            this.r = System.currentTimeMillis();
        }
    }

    public void a(u uVar) {
        i(uVar);
        try {
            uVar.a(n());
            String i = i(uVar);
            try {
                this.j.a(i, (com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar);
            } catch (com.abupdate.mqtt_libs.mqttv3.k unused) {
                this.j.a(this.f.i().getClientId(), this.f.i().getServerURI());
                this.j.a(i, (com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar);
            }
        } catch (MqttException unused2) {
        }
    }

    public void d(u uVar) throws MqttException {
        this.r = System.currentTimeMillis();
        if (this.p) {
            return;
        }
        if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.o) {
            com.abupdate.mqtt_libs.mqttv3.a.c.o oVar = (com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar;
            int qos = oVar.h().getQos();
            if (qos == 0 || qos == 1) {
                c cVar = this.g;
                if (cVar != null) {
                    cVar.a(oVar);
                }
            } else if (qos != 2) {
            } else {
                this.j.a(h(uVar), oVar);
                this.z.put(new Integer(oVar.j()), oVar);
                a(new com.abupdate.mqtt_libs.mqttv3.a.c.m(oVar), null);
            }
        } else if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.n) {
            com.abupdate.mqtt_libs.mqttv3.a.c.o oVar2 = (com.abupdate.mqtt_libs.mqttv3.a.c.o) this.z.get(new Integer(uVar.j()));
            if (oVar2 != null) {
                c cVar2 = this.g;
                if (cVar2 != null) {
                    cVar2.a(oVar2);
                    return;
                }
                return;
            }
            a(new com.abupdate.mqtt_libs.mqttv3.a.c.l(uVar.j()), null);
        }
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.a.c.o oVar) throws com.abupdate.mqtt_libs.mqttv3.k {
        synchronized (this.n) {
            if (oVar.h().getQos() == 1) {
                this.x.remove(new Integer(oVar.j()));
            } else {
                this.w.remove(new Integer(oVar.j()));
            }
            this.c.removeElement(oVar);
            this.j.b(f(oVar));
            this.e.b(oVar);
            if (oVar.h().getQos() > 0) {
                e(oVar.j());
                oVar.a(0);
            }
            f();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0048, code lost:
        if ((r0 - r14.q) >= (r14.h * 2)) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0051, code lost:
        throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32002);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.abupdate.mqtt_libs.mqttv3.n a(com.abupdate.mqtt_libs.mqttv3.IMqttActionListener r15) throws com.abupdate.mqtt_libs.mqttv3.MqttException {
        /*
            r14 = this;
            java.lang.Object r0 = r14.o
            monitor-enter(r0)
            boolean r1 = r14.p     // Catch: java.lang.Throwable -> Lb4
            r2 = 0
            if (r1 == 0) goto La
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb4
            return r2
        La:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb4
            r14.a()
            boolean r0 = r14.v
            if (r0 == 0) goto Lb3
            long r0 = r14.h
            r3 = 0
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto Lb3
            long r0 = java.lang.System.currentTimeMillis()
            r3 = 100
            java.lang.Object r4 = r14.t
            monitor-enter(r4)
            int r5 = r14.u     // Catch: java.lang.Throwable -> Lb0
            if (r5 <= 0) goto L3b
            long r6 = r14.r     // Catch: java.lang.Throwable -> Lb0
            long r6 = r0 - r6
            long r8 = r14.h     // Catch: java.lang.Throwable -> Lb0
            long r10 = (long) r3     // Catch: java.lang.Throwable -> Lb0
            long r8 = r8 + r10
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 >= 0) goto L34
            goto L3b
        L34:
            r15 = 32000(0x7d00, float:4.4842E-41)
            com.abupdate.mqtt_libs.mqttv3.MqttException r15 = com.abupdate.mqtt_libs.mqttv3.a.i.a(r15)     // Catch: java.lang.Throwable -> Lb0
            throw r15     // Catch: java.lang.Throwable -> Lb0
        L3b:
            if (r5 != 0) goto L52
            long r6 = r14.q     // Catch: java.lang.Throwable -> Lb0
            long r6 = r0 - r6
            r8 = 2
            long r10 = r14.h     // Catch: java.lang.Throwable -> Lb0
            long r10 = r10 * r8
            int r6 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r6 >= 0) goto L4b
            goto L52
        L4b:
            r15 = 32002(0x7d02, float:4.4844E-41)
            com.abupdate.mqtt_libs.mqttv3.MqttException r15 = com.abupdate.mqtt_libs.mqttv3.a.i.a(r15)     // Catch: java.lang.Throwable -> Lb0
            throw r15     // Catch: java.lang.Throwable -> Lb0
        L52:
            if (r5 != 0) goto L60
            long r5 = r14.r     // Catch: java.lang.Throwable -> Lb0
            long r5 = r0 - r5
            long r7 = r14.h     // Catch: java.lang.Throwable -> Lb0
            long r9 = (long) r3     // Catch: java.lang.Throwable -> Lb0
            long r7 = r7 - r9
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L6c
        L60:
            long r5 = r14.q     // Catch: java.lang.Throwable -> Lb0
            long r5 = r0 - r5
            long r7 = r14.h     // Catch: java.lang.Throwable -> Lb0
            long r9 = (long) r3     // Catch: java.lang.Throwable -> Lb0
            long r7 = r7 - r9
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 < 0) goto L97
        L6c:
            com.abupdate.mqtt_libs.mqttv3.n r0 = new com.abupdate.mqtt_libs.mqttv3.n     // Catch: java.lang.Throwable -> Lb0
            com.abupdate.mqtt_libs.mqttv3.a.a r1 = r14.f     // Catch: java.lang.Throwable -> Lb0
            com.abupdate.mqtt_libs.mqttv3.c r1 = r1.i()     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r1 = r1.getClientId()     // Catch: java.lang.Throwable -> Lb0
            r0.<init>(r1)     // Catch: java.lang.Throwable -> Lb0
            if (r15 == 0) goto L80
            r0.setActionCallback(r15)     // Catch: java.lang.Throwable -> Lb0
        L80:
            com.abupdate.mqtt_libs.mqttv3.a.f r15 = r14.e     // Catch: java.lang.Throwable -> Lb0
            com.abupdate.mqtt_libs.mqttv3.a.c.u r1 = r14.s     // Catch: java.lang.Throwable -> Lb0
            r15.a(r0, r1)     // Catch: java.lang.Throwable -> Lb0
            java.util.Vector r15 = r14.d     // Catch: java.lang.Throwable -> Lb0
            com.abupdate.mqtt_libs.mqttv3.a.c.u r1 = r14.s     // Catch: java.lang.Throwable -> Lb0
            r2 = 0
            r15.insertElementAt(r1, r2)     // Catch: java.lang.Throwable -> Lb0
            long r1 = r14.a()     // Catch: java.lang.Throwable -> Lb0
            r14.h()     // Catch: java.lang.Throwable -> Lb0
            goto La8
        L97:
            r5 = 1
            long r7 = r14.a()     // Catch: java.lang.Throwable -> Lb0
            long r9 = r14.q     // Catch: java.lang.Throwable -> Lb0
            long r0 = r0 - r9
            long r7 = r7 - r0
            long r0 = java.lang.Math.max(r5, r7)     // Catch: java.lang.Throwable -> Lb0
            r12 = r0
            r0 = r2
            r1 = r12
        La8:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> Lb0
            com.abupdate.mqtt_libs.mqttv3.l r15 = r14.A
            r15.a(r1)
            r2 = r0
            goto Lb3
        Lb0:
            r15 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> Lb0
            throw r15
        Lb3:
            return r2
        Lb4:
            r15 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lb4
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.mqtt_libs.mqttv3.a.b.a(com.abupdate.mqtt_libs.mqttv3.IMqttActionListener):com.abupdate.mqtt_libs.mqttv3.n");
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.a.c.b bVar) throws MqttException {
        this.r = System.currentTimeMillis();
        com.abupdate.mqtt_libs.mqttv3.n a2 = this.e.a(bVar);
        if (a2 != null) {
            if (bVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.m) {
                a(new com.abupdate.mqtt_libs.mqttv3.a.c.n((com.abupdate.mqtt_libs.mqttv3.a.c.m) bVar), a2);
            } else if (!(bVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.k) && !(bVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.l)) {
                if (bVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.j) {
                    synchronized (this.t) {
                        this.u = Math.max(0, this.u - 1);
                        a(bVar, a2, null);
                        if (this.u == 0) {
                            this.e.b(bVar);
                        }
                    }
                } else if (bVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.c) {
                    com.abupdate.mqtt_libs.mqttv3.a.c.c cVar = (com.abupdate.mqtt_libs.mqttv3.a.c.c) bVar;
                    int a_ = cVar.a_();
                    if (a_ == 0) {
                        synchronized (this.n) {
                            if (this.i) {
                                c();
                                this.e.a(a2, bVar);
                            }
                            this.m = 0;
                            this.l = 0;
                            l();
                            g();
                        }
                        this.f.a(cVar, (MqttException) null);
                        a(bVar, a2, null);
                        this.e.b(bVar);
                        synchronized (this.n) {
                            this.n.notifyAll();
                        }
                    } else {
                        throw i.a(a_);
                    }
                } else {
                    a(bVar, a2, null);
                    e(bVar.j());
                    this.e.b(bVar);
                }
            } else {
                a(bVar, a2, null);
            }
        }
        f();
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        u l = nVar.f1970a.l();
        if (l == null || !(l instanceof com.abupdate.mqtt_libs.mqttv3.a.c.b)) {
            return;
        }
        com.abupdate.mqtt_libs.mqttv3.a.c.b bVar = (com.abupdate.mqtt_libs.mqttv3.a.c.b) l;
        if (bVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.k) {
            this.j.b(f(l));
            this.j.b(i(l));
            this.x.remove(new Integer(bVar.j()));
            m();
            e(l.j());
            this.e.b(l);
        } else if (bVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.l) {
            this.j.b(f(l));
            this.j.b(g(l));
            this.j.b(i(l));
            this.w.remove(new Integer(bVar.j()));
            this.m--;
            m();
            e(l.j());
            this.e.b(l);
        }
        f();
    }

    public void a(u uVar, com.abupdate.mqtt_libs.mqttv3.n nVar, MqttException mqttException) {
        nVar.f1970a.a(uVar, mqttException);
        nVar.f1970a.g();
        if (uVar != null && (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.b) && !(uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.m)) {
            this.g.b(nVar);
        }
        if (uVar == null) {
            this.g.b(nVar);
        }
    }

    public Vector a(MqttException mqttException) {
        if (mqttException == null) {
            mqttException = new MqttException(32102);
        }
        Vector c = this.e.c();
        Enumeration elements = c.elements();
        while (elements.hasMoreElements()) {
            com.abupdate.mqtt_libs.mqttv3.n nVar = (com.abupdate.mqtt_libs.mqttv3.n) elements.nextElement();
            synchronized (nVar) {
                if (!nVar.isComplete() && !nVar.f1970a.e() && nVar.getException() == null) {
                    nVar.f1970a.a(mqttException);
                }
            }
            if (!(nVar instanceof com.abupdate.mqtt_libs.mqttv3.i)) {
                this.e.b(nVar.f1970a.o());
            }
        }
        return c;
    }
}
