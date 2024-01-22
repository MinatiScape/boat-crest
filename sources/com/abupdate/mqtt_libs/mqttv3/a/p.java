package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.IOException;
import java.net.Socket;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public class p extends q {
    public String[] f;
    public int g;
    public HostnameVerifier h;
    public String i;
    public int j;

    public p(SSLSocketFactory sSLSocketFactory, String str, int i, String str2) {
        super(sSLSocketFactory, str, i, str2);
        this.i = str;
        this.j = i;
    }

    public void a(String[] strArr) {
        this.f = strArr;
        Socket socket = this.f1958a;
        if (socket == null || strArr == null) {
            return;
        }
        ((SSLSocket) socket).setEnabledCipherSuites(strArr);
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public String e() {
        return "ssl://" + this.i + ":" + this.j;
    }

    public void a(int i) {
        super.b(i);
        this.g = i;
    }

    public void a(HostnameVerifier hostnameVerifier) {
        this.h = hostnameVerifier;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public void a() throws IOException, MqttException {
        super.a();
        a(this.f);
        int soTimeout = this.f1958a.getSoTimeout();
        this.f1958a.setSoTimeout(this.g * 1000);
        ((SSLSocket) this.f1958a).startHandshake();
        if (this.h != null) {
            this.h.verify(this.i, ((SSLSocket) this.f1958a).getSession());
        }
        this.f1958a.setSoTimeout(soTimeout);
    }
}
