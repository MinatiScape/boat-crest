package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.IMqttActionListener;
import com.abupdate.mqtt_libs.mqttv3.MqttCallback;
import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.MqttMessage;
import com.abupdate.mqtt_libs.mqttv3.a.c.u;
import java.util.Enumeration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttDisconnect;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static String f1941a = "${project.version}";
    public static String b = "L${build.level}";
    public com.abupdate.mqtt_libs.mqttv3.c c;
    public int d;
    public n[] e;
    public d f;
    public e g;
    public com.abupdate.mqtt_libs.mqttv3.a.c h;
    public com.abupdate.mqtt_libs.mqttv3.a.b i;
    public com.abupdate.mqtt_libs.mqttv3.h j;
    public com.abupdate.mqtt_libs.mqttv3.g k;
    public com.abupdate.mqtt_libs.mqttv3.l l;
    public f m;
    public byte o;
    public h r;
    public ExecutorService s;
    public boolean n = false;
    public Object p = new Object();
    public boolean q = false;

    /* renamed from: com.abupdate.mqtt_libs.mqttv3.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0198a implements Runnable {
        public a h;
        public com.abupdate.mqtt_libs.mqttv3.n i;
        public com.abupdate.mqtt_libs.mqttv3.a.c.d j;
        public String k;

        public RunnableC0198a(a aVar, com.abupdate.mqtt_libs.mqttv3.n nVar, com.abupdate.mqtt_libs.mqttv3.a.c.d dVar, ExecutorService executorService) {
            this.h = null;
            this.h = aVar;
            this.i = nVar;
            this.j = dVar;
            this.k = "MQTT Con: " + a.this.i().getClientId();
        }

        public void a() {
            a.this.s.execute(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName(this.k);
            MqttException e = null;
            try {
                for (com.abupdate.mqtt_libs.mqttv3.i iVar : a.this.m.b()) {
                    iVar.f1970a.a((MqttException) null);
                }
                a.this.m.a(this.i, this.j);
                n nVar = a.this.e[a.this.d];
                nVar.a();
                a.this.f = new d(this.h, a.this.i, a.this.m, nVar.b());
                a.this.f.a("MQTT Rec: " + a.this.i().getClientId(), a.this.s);
                a.this.g = new e(this.h, a.this.i, a.this.m, nVar.c());
                a.this.g.a("MQTT Snd: " + a.this.i().getClientId(), a.this.s);
                a.this.h.a("MQTT Call: " + a.this.i().getClientId(), a.this.s);
                a.this.d(this.j, this.i);
            } catch (MqttException e2) {
                e = e2;
            } catch (Exception e3) {
                e = i.a(e3);
            }
            if (e != null) {
                a.this.a(this.i, e);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public com.abupdate.mqtt_libs.mqttv3.a.c.e h;
        public long i;
        public com.abupdate.mqtt_libs.mqttv3.n j;
        public String k;

        public b(com.abupdate.mqtt_libs.mqttv3.a.c.e eVar, long j, com.abupdate.mqtt_libs.mqttv3.n nVar, ExecutorService executorService) {
            this.h = eVar;
            this.i = j;
            this.j = nVar;
        }

        public void a() {
            this.k = "MQTT Disc: " + a.this.i().getClientId();
            a.this.s.execute(this);
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setName(this.k);
            a.this.i.b(this.i);
            try {
                a.this.d(this.h, this.j);
                this.j.f1970a.h();
            } catch (MqttException unused) {
            } catch (Throwable th) {
                this.j.f1970a.a(null, null);
                a.this.a(this.j, (MqttException) null);
                throw th;
            }
            this.j.f1970a.a(null, null);
            a.this.a(this.j, (MqttException) null);
        }
    }

    /* loaded from: classes.dex */
    public class c implements k {
        public c(String str) {
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.a.k
        public void a(com.abupdate.mqtt_libs.mqttv3.a aVar) throws MqttException {
            if (a.this.a()) {
                while (a.this.i.i() >= a.this.i.j() - 1) {
                    Thread.yield();
                }
                a.this.d(aVar.a(), aVar.b());
                a.this.i.b(aVar.a());
                return;
            }
            throw i.a(32104);
        }
    }

    public a(com.abupdate.mqtt_libs.mqttv3.c cVar, com.abupdate.mqtt_libs.mqttv3.g gVar, com.abupdate.mqtt_libs.mqttv3.l lVar, ExecutorService executorService) throws MqttException {
        this.o = (byte) 3;
        this.o = (byte) 3;
        this.c = cVar;
        this.k = gVar;
        this.l = lVar;
        lVar.a(this);
        this.s = executorService;
        this.m = new f(i().getClientId());
        this.h = new com.abupdate.mqtt_libs.mqttv3.a.c(this);
        com.abupdate.mqtt_libs.mqttv3.a.b bVar = new com.abupdate.mqtt_libs.mqttv3.a.b(gVar, this.m, this.h, this, lVar);
        this.i = bVar;
        this.h.a(bVar);
    }

    public void b(boolean z) {
    }

    public void d(u uVar, com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        if (nVar.getClient() == null) {
            nVar.f1970a.a(i());
            try {
                this.i.a(uVar, nVar);
                return;
            } catch (MqttException e) {
                if (uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.o) {
                    this.i.a((com.abupdate.mqtt_libs.mqttv3.a.c.o) uVar);
                }
                throw e;
            }
        }
        throw new MqttException(32201);
    }

    public boolean e() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 4;
        }
        return z;
    }

    public final com.abupdate.mqtt_libs.mqttv3.n g(com.abupdate.mqtt_libs.mqttv3.n nVar, MqttException mqttException) {
        com.abupdate.mqtt_libs.mqttv3.n nVar2 = null;
        if (nVar != null) {
            try {
                if (this.m.a(nVar.f1970a.o()) == null) {
                    this.m.a(nVar, nVar.f1970a.o());
                }
            } catch (Exception unused) {
            }
        }
        Enumeration elements = this.i.a(mqttException).elements();
        while (elements.hasMoreElements()) {
            com.abupdate.mqtt_libs.mqttv3.n nVar3 = (com.abupdate.mqtt_libs.mqttv3.n) elements.nextElement();
            if (!nVar3.f1970a.o().equals(MqttDisconnect.KEY) && !nVar3.f1970a.o().equals("Con")) {
                this.h.b(nVar3);
            }
            nVar2 = nVar3;
        }
        return nVar2;
    }

    public final void n() {
        this.s.shutdown();
        try {
            ExecutorService executorService = this.s;
            TimeUnit timeUnit = TimeUnit.SECONDS;
            if (executorService.awaitTermination(1L, timeUnit)) {
                return;
            }
            this.s.shutdownNow();
            this.s.awaitTermination(1L, timeUnit);
        } catch (InterruptedException unused) {
            this.s.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void a(boolean z) throws MqttException {
        synchronized (this.p) {
            if (!e()) {
                if (!c() || z) {
                    if (!b()) {
                        if (!a()) {
                            if (d()) {
                                this.q = true;
                                return;
                            }
                        } else {
                            throw i.a(32100);
                        }
                    } else {
                        throw new MqttException(32110);
                    }
                }
                this.o = (byte) 4;
                n();
                this.i.k();
                this.i = null;
                this.h = null;
                this.k = null;
                this.g = null;
                this.l = null;
                this.f = null;
                this.e = null;
                this.j = null;
                this.m = null;
            }
        }
    }

    public void b(u uVar, com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        if (!a() && ((a() || !(uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.d)) && (!d() || !(uVar instanceof com.abupdate.mqtt_libs.mqttv3.a.c.e)))) {
            h hVar = this.r;
            if (hVar != null) {
                if (hVar.b()) {
                    this.i.a(uVar);
                }
                this.r.a(uVar, nVar);
                return;
            }
            throw i.a(32104);
        }
        h hVar2 = this.r;
        if (hVar2 != null && hVar2.a() != 0) {
            if (this.r.b()) {
                this.i.a(uVar);
            }
            this.r.a(uVar, nVar);
            return;
        }
        d(uVar, nVar);
    }

    public boolean c() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 3;
        }
        return z;
    }

    public int f() {
        return this.d;
    }

    public com.abupdate.mqtt_libs.mqttv3.i[] h() {
        return this.m.b();
    }

    public com.abupdate.mqtt_libs.mqttv3.c i() {
        return this.c;
    }

    public long j() {
        return this.i.a();
    }

    public int k() {
        return this.r.a();
    }

    public void l() {
        h hVar = this.r;
        if (hVar != null) {
            hVar.a(new c("notifyConnect"));
            this.s.execute(this.r);
        }
    }

    public final void e(Exception exc) {
        MqttException mqttException;
        if (!(exc instanceof MqttException)) {
            mqttException = new MqttException(32109, exc);
        } else {
            mqttException = (MqttException) exc;
        }
        a((com.abupdate.mqtt_libs.mqttv3.n) null, mqttException);
    }

    public void c(int i) {
        this.r.b(i);
    }

    public boolean d() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 2;
        }
        return z;
    }

    public n[] g() {
        return this.e;
    }

    public boolean b() {
        boolean z;
        synchronized (this.p) {
            z = true;
            if (this.o != 1) {
                z = false;
            }
        }
        return z;
    }

    public MqttMessage b(int i) {
        return ((com.abupdate.mqtt_libs.mqttv3.a.c.o) this.r.a(i).a()).h();
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.h hVar, com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        synchronized (this.p) {
            if (c() && !this.q) {
                this.o = (byte) 1;
                this.j = hVar;
                com.abupdate.mqtt_libs.mqttv3.a.c.d dVar = new com.abupdate.mqtt_libs.mqttv3.a.c.d(this.c.getClientId(), this.j.d(), this.j.l(), this.j.c(), this.j.b(), this.j.a(), this.j.i(), this.j.h());
                this.i.a(this.j.c());
                this.i.a(this.j.l());
                this.i.a(this.j.e());
                this.m.a();
                new RunnableC0198a(this, nVar, dVar, this.s).a();
            } else if (!e() && !this.q) {
                if (!b()) {
                    if (d()) {
                        throw new MqttException(32102);
                    }
                    throw i.a(32100);
                }
                throw new MqttException(32110);
            } else {
                throw new MqttException(32111);
            }
        }
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.a.c.c cVar, MqttException mqttException) throws MqttException {
        int a_ = cVar.a_();
        synchronized (this.p) {
            if (a_ == 0) {
                this.o = (byte) 0;
                return;
            }
            throw mqttException;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(26:11|(33:16|17|18|(1:22)|23|(1:25)|26|(1:28)|29|30|(1:34)|36|37|38|(1:40)|42|(1:44)|45|(1:47)|48|49|(1:53)|55|90|(1:62)(1:89)|63|(1:65)|66|(1:68)|(1:72)|73|b2|79)|97|17|18|(2:20|22)|23|(0)|26|(0)|29|30|(2:32|34)|36|37|38|(0)|42|(0)|45|(0)|48|49|(2:51|53)|55|90) */
    /* JADX WARN: Removed duplicated region for block: B:26:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0070 A[Catch: Exception -> 0x0075, TRY_LEAVE, TryCatch #3 {Exception -> 0x0075, blocks: (B:36:0x0063, B:38:0x0070), top: B:95:0x0063 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.abupdate.mqtt_libs.mqttv3.n r7, com.abupdate.mqtt_libs.mqttv3.MqttException r8) {
        /*
            Method dump skipped, instructions count: 199
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.abupdate.mqtt_libs.mqttv3.a.a.a(com.abupdate.mqtt_libs.mqttv3.n, com.abupdate.mqtt_libs.mqttv3.MqttException):void");
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.a.c.e eVar, long j, com.abupdate.mqtt_libs.mqttv3.n nVar) throws MqttException {
        synchronized (this.p) {
            if (!e()) {
                if (!c()) {
                    if (!d()) {
                        if (Thread.currentThread() != this.h.d()) {
                            this.o = (byte) 2;
                            new b(eVar, j, nVar, this.s).a();
                        } else {
                            throw i.a(32107);
                        }
                    } else {
                        throw i.a(32102);
                    }
                } else {
                    throw i.a(32101);
                }
            } else {
                throw i.a(32111);
            }
        }
    }

    public boolean a() {
        boolean z;
        synchronized (this.p) {
            z = this.o == 0;
        }
        return z;
    }

    public void a(MqttCallback mqttCallback) {
        this.h.a(mqttCallback);
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.f fVar) {
        this.h.a(fVar);
    }

    public void a(String str, com.abupdate.mqtt_libs.mqttv3.d dVar) {
        this.h.a(str, dVar);
    }

    public void a(String str) {
        this.h.a(str);
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(n[] nVarArr) {
        this.e = nVarArr;
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.a.c.o oVar) throws com.abupdate.mqtt_libs.mqttv3.k {
        this.i.b(oVar);
    }

    public com.abupdate.mqtt_libs.mqttv3.n a(IMqttActionListener iMqttActionListener) {
        try {
            return this.i.a(iMqttActionListener);
        } catch (MqttException e) {
            e(e);
            return null;
        } catch (Exception e2) {
            e(e2);
            return null;
        }
    }

    public void a(h hVar) {
        this.r = hVar;
    }
}
