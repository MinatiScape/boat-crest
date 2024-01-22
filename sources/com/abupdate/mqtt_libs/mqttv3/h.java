package com.abupdate.mqtt_libs.mqttv3;

import com.google.android.gms.common.internal.ImagesContract;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
/* loaded from: classes.dex */
public class h {
    public String e;
    public char[] f;
    public SocketFactory g;

    /* renamed from: a  reason: collision with root package name */
    public int f1969a = 60;
    public int b = 10;
    public String c = null;
    public MqttMessage d = null;
    public Properties h = null;
    public HostnameVerifier i = null;
    public boolean j = true;
    public int k = 30;
    public String[] l = null;
    public int m = 0;
    public boolean n = false;

    public char[] a() {
        return this.f;
    }

    public String b() {
        return this.e;
    }

    public int c() {
        return this.f1969a;
    }

    public int d() {
        return this.m;
    }

    public int e() {
        return this.b;
    }

    public int f() {
        return this.k;
    }

    public SocketFactory g() {
        return this.g;
    }

    public String h() {
        return this.c;
    }

    public MqttMessage i() {
        return this.d;
    }

    public Properties j() {
        return this.h;
    }

    public HostnameVerifier k() {
        return this.i;
    }

    public boolean l() {
        return this.j;
    }

    public String[] m() {
        return this.l;
    }

    public boolean n() {
        return this.n;
    }

    public Properties o() {
        Properties properties = new Properties();
        properties.put("MqttVersion", new Integer(d()));
        properties.put("CleanSession", Boolean.valueOf(l()));
        properties.put("ConTimeout", new Integer(f()));
        properties.put("KeepAliveInterval", new Integer(c()));
        properties.put("UserName", b() == null ? "null" : b());
        properties.put("WillDestination", h() == null ? "null" : h());
        if (g() == null) {
            properties.put("SocketFactory", "null");
        } else {
            properties.put("SocketFactory", g());
        }
        if (j() == null) {
            properties.put("SSLProperties", "null");
        } else {
            properties.put("SSLProperties", j());
        }
        return properties;
    }

    public String toString() {
        return com.abupdate.mqtt_libs.mqttv3.c.a.a(o(), "Connection options");
    }

    public void a(char[] cArr) {
        this.f = cArr;
    }

    public final void b(String str, Object obj) {
        if (str != null && obj != null) {
            o.a(str, false);
            return;
        }
        throw new IllegalArgumentException();
    }

    public void c(int i) throws IllegalArgumentException {
        if (i != 0 && i != 3 && i != 4) {
            throw new IllegalArgumentException();
        }
        this.m = i;
    }

    public void a(String str) {
        if (str != null && "".equals(str.trim())) {
            throw new IllegalArgumentException();
        }
        this.e = str;
    }

    public void b(int i) {
        if (i >= 0) {
            this.k = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public static int b(String str) {
        try {
            URI uri = new URI(str);
            if ("ws".equals(uri.getScheme())) {
                return 3;
            }
            if ("wss".equals(uri.getScheme())) {
                return 4;
            }
            if (uri.getPath() != null && !uri.getPath().isEmpty()) {
                throw new IllegalArgumentException(str);
            }
            if ("tcp".equals(uri.getScheme())) {
                return 0;
            }
            if ("ssl".equals(uri.getScheme())) {
                return 1;
            }
            if (ImagesContract.LOCAL.equals(uri.getScheme())) {
                return 2;
            }
            throw new IllegalArgumentException(str);
        } catch (URISyntaxException unused) {
            throw new IllegalArgumentException(str);
        }
    }

    public void a(String str, byte[] bArr, int i, boolean z) {
        b(str, bArr);
        a(str, new MqttMessage(bArr), i, z);
    }

    public void a(String str, MqttMessage mqttMessage, int i, boolean z) {
        this.c = str;
        this.d = mqttMessage;
        mqttMessage.setQos(i);
        this.d.setRetained(z);
        this.d.setMutable(false);
    }

    public void a(int i) throws IllegalArgumentException {
        if (i >= 0) {
            this.f1969a = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void a(SocketFactory socketFactory) {
        this.g = socketFactory;
    }

    public void a(boolean z) {
        this.j = z;
    }
}
