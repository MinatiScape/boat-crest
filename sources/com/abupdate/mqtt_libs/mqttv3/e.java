package com.abupdate.mqtt_libs.mqttv3;

import com.abupdate.iot_libs.constant.SDKConfig;
import com.abupdate.mqtt_libs.mqttv3.a.c.r;
import com.abupdate.mqtt_libs.mqttv3.a.c.t;
import com.abupdate.mqtt_libs.mqttv3.a.p;
import com.abupdate.mqtt_libs.mqttv3.a.q;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public class e implements com.abupdate.mqtt_libs.mqttv3.c {
    public static int q = 1000;
    public static Object r = new Object();

    /* renamed from: a  reason: collision with root package name */
    public com.abupdate.mqtt_libs.mqttv3.a.a f1966a;
    public String h;
    public String i;
    public g j;
    public MqttCallback k;
    public h l;
    public Object m;
    public Timer n;
    public boolean o;
    public ScheduledExecutorService p;

    /* loaded from: classes.dex */
    public class b implements IMqttActionListener {

        /* renamed from: a  reason: collision with root package name */
        public final String f1967a;

        public b(String str) {
            this.f1967a = str;
        }

        public final void a(int i) {
            synchronized (e.r) {
                if (e.this.l.n()) {
                    if (e.this.n != null) {
                        e.this.n.schedule(new d(), i);
                    } else {
                        int unused = e.q = i;
                        e.this.n();
                    }
                }
            }
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onFailure(IMqttToken iMqttToken, Throwable th) {
            if (e.q < 128000) {
                e.q *= 2;
            }
            a(e.q);
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.IMqttActionListener
        public void onSuccess(IMqttToken iMqttToken) {
            e.this.f1966a.b(false);
            e.this.o();
        }
    }

    /* loaded from: classes.dex */
    public class c implements f {

        /* renamed from: a  reason: collision with root package name */
        public final boolean f1968a;

        public c(boolean z) {
            this.f1968a = z;
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.f
        public void a(boolean z, String str) {
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
        public void connectionLost(Throwable th) {
            if (this.f1968a) {
                e.this.f1966a.b(true);
                e.this.o = true;
                e.this.n();
            }
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
        public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        }

        @Override // com.abupdate.mqtt_libs.mqttv3.MqttCallback
        public void messageArrived(String str, MqttMessage mqttMessage) throws Exception {
        }
    }

    /* loaded from: classes.dex */
    public class d extends TimerTask {
        public d() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            e.this.m();
        }
    }

    public e(String str, String str2, g gVar, l lVar) throws MqttException {
        this(str, str2, gVar, lVar, null);
    }

    public static boolean a(char c2) {
        return c2 >= 55296 && c2 <= 56319;
    }

    public com.abupdate.mqtt_libs.mqttv3.a.n[] a(String str, h hVar) throws MqttException, m {
        String[] m = hVar.m();
        if (m == null) {
            m = new String[]{str};
        } else if (m.length == 0) {
            m = new String[]{str};
        }
        com.abupdate.mqtt_libs.mqttv3.a.n[] nVarArr = new com.abupdate.mqtt_libs.mqttv3.a.n[m.length];
        for (int i = 0; i < m.length; i++) {
            nVarArr[i] = e(m[i], hVar);
        }
        return nVarArr;
    }

    public final String b(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            indexOf = str.indexOf(47);
        }
        if (indexOf == -1) {
            indexOf = str.length();
        }
        return str.substring(0, indexOf);
    }

    public final com.abupdate.mqtt_libs.mqttv3.a.n e(String str, h hVar) throws MqttException, m {
        com.abupdate.mqtt_libs.mqttv3.a.a.a aVar;
        String[] n;
        com.abupdate.mqtt_libs.mqttv3.a.a.a aVar2;
        String[] n2;
        SocketFactory g = hVar.g();
        int b2 = h.b(str);
        try {
            URI uri = new URI(str);
            if (uri.getHost() == null && str.contains("_")) {
                try {
                    Field declaredField = URI.class.getDeclaredField("host");
                    declaredField.setAccessible(true);
                    declaredField.set(uri, b(str.substring(uri.getScheme().length() + 3)));
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
                    throw com.abupdate.mqtt_libs.mqttv3.a.i.a(e.getCause());
                }
            }
            String host = uri.getHost();
            int port = uri.getPort();
            if (b2 == 0) {
                if (port == -1) {
                    port = SDKConfig.MQTT_TCP_PORT;
                }
                if (g == null) {
                    g = SocketFactory.getDefault();
                } else if (g instanceof SSLSocketFactory) {
                    throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32105);
                }
                q qVar = new q(g, host, port, this.h);
                qVar.b(hVar.f());
                return qVar;
            } else if (b2 == 1) {
                if (port == -1) {
                    port = 8883;
                }
                if (g == null) {
                    aVar = new com.abupdate.mqtt_libs.mqttv3.a.a.a();
                    Properties j = hVar.j();
                    if (j != null) {
                        aVar.a(j, (String) null);
                    }
                    g = aVar.o(null);
                } else if (!(g instanceof SSLSocketFactory)) {
                    throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32105);
                } else {
                    aVar = null;
                }
                p pVar = new p((SSLSocketFactory) g, host, port, this.h);
                pVar.a(hVar.f());
                pVar.a(hVar.k());
                if (aVar != null && (n = aVar.n(null)) != null) {
                    pVar.a(n);
                }
                return pVar;
            } else if (b2 == 3) {
                int i = port == -1 ? 80 : port;
                if (g == null) {
                    g = SocketFactory.getDefault();
                } else if (g instanceof SSLSocketFactory) {
                    throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32105);
                }
                com.abupdate.mqtt_libs.mqttv3.a.b.f fVar = new com.abupdate.mqtt_libs.mqttv3.a.b.f(g, str, host, i, this.h);
                fVar.b(hVar.f());
                return fVar;
            } else if (b2 != 4) {
                return null;
            } else {
                int i2 = port == -1 ? 443 : port;
                if (g == null) {
                    com.abupdate.mqtt_libs.mqttv3.a.a.a aVar3 = new com.abupdate.mqtt_libs.mqttv3.a.a.a();
                    Properties j2 = hVar.j();
                    if (j2 != null) {
                        aVar3.a(j2, (String) null);
                    }
                    aVar2 = aVar3;
                    g = aVar3.o(null);
                } else if (!(g instanceof SSLSocketFactory)) {
                    throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32105);
                } else {
                    aVar2 = null;
                }
                com.abupdate.mqtt_libs.mqttv3.a.b.h hVar2 = new com.abupdate.mqtt_libs.mqttv3.a.b.h((SSLSocketFactory) g, str, host, i2, this.h);
                hVar2.a(hVar.f());
                if (aVar2 != null && (n2 = aVar2.n(null)) != null) {
                    hVar2.a(n2);
                }
                return hVar2;
            }
        } catch (URISyntaxException e2) {
            throw new IllegalArgumentException("Malformed URI: " + str + ", " + e2.getMessage());
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.c
    public String getClientId() {
        return this.h;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.c
    public String getServerURI() {
        return this.i;
    }

    public final void m() {
        try {
            a(this.l, this.m, new b("attemptReconnect"));
        } catch (m | MqttException unused) {
        }
    }

    public final void n() {
        Timer timer = new Timer("MQTT Reconnect: " + this.h);
        this.n = timer;
        timer.schedule(new d(), (long) q);
    }

    public final void o() {
        synchronized (r) {
            if (this.l.n()) {
                Timer timer = this.n;
                if (timer != null) {
                    timer.cancel();
                    this.n = null;
                }
                q = 1000;
            }
        }
    }

    public e(String str, String str2, g gVar, l lVar, ScheduledExecutorService scheduledExecutorService) throws MqttException {
        int i = 0;
        this.o = false;
        if (str2 != null) {
            int i2 = 0;
            while (i < str2.length() - 1) {
                if (a(str2.charAt(i))) {
                    i++;
                }
                i2++;
                i++;
            }
            if (i2 <= 65535) {
                h.b(str);
                this.i = str;
                this.h = str2;
                this.j = gVar;
                if (gVar == null) {
                    this.j = new com.abupdate.mqtt_libs.mqttv3.b.a();
                }
                this.p = scheduledExecutorService;
                if (scheduledExecutorService == null) {
                    this.p = Executors.newScheduledThreadPool(10);
                }
                this.j.a(str2, str);
                this.f1966a = new com.abupdate.mqtt_libs.mqttv3.a.a(this, this.j, lVar, this.p);
                this.j.a();
                new Hashtable();
                return;
            }
            throw new IllegalArgumentException("ClientId longer than 65535 characters");
        }
        throw new IllegalArgumentException("Null clientId");
    }

    public void c() throws MqttException {
        if (!this.f1966a.a()) {
            if (!this.f1966a.b()) {
                if (!this.f1966a.d()) {
                    if (!this.f1966a.e()) {
                        o();
                        m();
                        return;
                    }
                    throw new MqttException(32111);
                }
                throw new MqttException(32102);
            }
            throw new MqttException(32110);
        }
        throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32100);
    }

    public int d() {
        return this.f1966a.k();
    }

    public IMqttDeliveryToken[] b() {
        return this.f1966a.h();
    }

    public IMqttToken a(h hVar, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, m {
        if (!this.f1966a.a()) {
            if (!this.f1966a.b()) {
                if (!this.f1966a.d()) {
                    if (!this.f1966a.e()) {
                        if (hVar == null) {
                            hVar = new h();
                        }
                        h hVar2 = hVar;
                        this.l = hVar2;
                        this.m = obj;
                        boolean n = hVar2.n();
                        this.f1966a.a(a(this.i, hVar2));
                        this.f1966a.a((f) new c(n));
                        n nVar = new n(getClientId());
                        com.abupdate.mqtt_libs.mqttv3.a.g gVar = new com.abupdate.mqtt_libs.mqttv3.a.g(this, this.j, this.f1966a, hVar2, nVar, obj, iMqttActionListener, this.o);
                        nVar.setActionCallback(gVar);
                        nVar.setUserContext(this);
                        MqttCallback mqttCallback = this.k;
                        if (mqttCallback instanceof f) {
                            gVar.a((f) mqttCallback);
                        }
                        this.f1966a.a(0);
                        gVar.a();
                        return nVar;
                    }
                    throw new MqttException(32111);
                }
                throw new MqttException(32102);
            }
            throw new MqttException(32110);
        }
        throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32100);
    }

    public void b(int i) {
        this.f1966a.c(i);
    }

    public IMqttToken a(Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return a(30000L, obj, iMqttActionListener);
    }

    public IMqttToken a(long j, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        n nVar = new n(getClientId());
        nVar.setActionCallback(iMqttActionListener);
        nVar.setUserContext(obj);
        try {
            this.f1966a.a(new com.abupdate.mqtt_libs.mqttv3.a.c.e(), j, nVar);
            return nVar;
        } catch (MqttException e) {
            throw e;
        }
    }

    public boolean a() {
        return this.f1966a.a();
    }

    public IMqttToken a(String str, int i, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return a(new String[]{str}, new int[]{i}, obj, iMqttActionListener);
    }

    public IMqttToken a(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        if (strArr.length == iArr.length) {
            for (String str : strArr) {
                this.f1966a.a(str);
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArr.length; i++) {
                if (i > 0) {
                    stringBuffer.append(", ");
                }
                stringBuffer.append("topic=");
                stringBuffer.append(strArr[i]);
                stringBuffer.append(" qos=");
                stringBuffer.append(iArr[i]);
                o.a(strArr[i], true);
            }
            n nVar = new n(getClientId());
            nVar.setActionCallback(iMqttActionListener);
            nVar.setUserContext(obj);
            nVar.f1970a.a(strArr);
            this.f1966a.b(new r(strArr, iArr), nVar);
            return nVar;
        }
        throw new IllegalArgumentException();
    }

    public void e() throws MqttException {
        a(false);
    }

    public IMqttToken a(String[] strArr, int[] iArr, com.abupdate.mqtt_libs.mqttv3.d[] dVarArr) throws MqttException {
        return a(strArr, iArr, null, null, dVarArr);
    }

    public IMqttToken a(String[] strArr, int[] iArr, Object obj, IMqttActionListener iMqttActionListener, com.abupdate.mqtt_libs.mqttv3.d[] dVarArr) throws MqttException {
        if (dVarArr.length == iArr.length && iArr.length == strArr.length) {
            IMqttToken a2 = a(strArr, iArr, obj, iMqttActionListener);
            for (int i = 0; i < strArr.length; i++) {
                this.f1966a.a(strArr[i], dVarArr[i]);
            }
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public IMqttToken a(String str, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        return a(new String[]{str}, obj, iMqttActionListener);
    }

    public IMqttToken a(String[] strArr, Object obj, IMqttActionListener iMqttActionListener) throws MqttException {
        for (String str : strArr) {
            o.a(str, true);
        }
        for (String str2 : strArr) {
            this.f1966a.a(str2);
        }
        n nVar = new n(getClientId());
        nVar.setActionCallback(iMqttActionListener);
        nVar.setUserContext(obj);
        nVar.f1970a.a(strArr);
        this.f1966a.b(new t(strArr), nVar);
        return nVar;
    }

    public void a(MqttCallback mqttCallback) {
        this.k = mqttCallback;
        this.f1966a.a(mqttCallback);
    }

    public IMqttDeliveryToken a(String str, byte[] bArr, int i, boolean z, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, k {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return a(str, mqttMessage, obj, iMqttActionListener);
    }

    public IMqttDeliveryToken a(String str, MqttMessage mqttMessage, Object obj, IMqttActionListener iMqttActionListener) throws MqttException, k {
        o.a(str, false);
        i iVar = new i(getClientId());
        iVar.setActionCallback(iMqttActionListener);
        iVar.setUserContext(obj);
        iVar.a(mqttMessage);
        iVar.f1970a.a(new String[]{str});
        this.f1966a.b(new com.abupdate.mqtt_libs.mqttv3.a.c.o(str, mqttMessage), iVar);
        return iVar;
    }

    public void a(com.abupdate.mqtt_libs.mqttv3.b bVar) {
        this.f1966a.a(new com.abupdate.mqtt_libs.mqttv3.a.h(bVar));
    }

    public MqttMessage a(int i) {
        return this.f1966a.b(i);
    }

    public void a(boolean z) throws MqttException {
        this.f1966a.a(z);
    }
}
