package com.abupdate.mqtt_libs.mqttv3.a.b;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.p;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public class h extends p {
    public PipedInputStream k;
    public g l;
    public String m;
    public String n;
    public int o;
    public ByteArrayOutputStream p;

    public h(SSLSocketFactory sSLSocketFactory, String str, String str2, int i, String str3) {
        super(sSLSocketFactory, str2, i, str3);
        this.p = new b(this);
        this.m = str;
        this.n = str2;
        this.o = i;
        this.k = new PipedInputStream();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.p, com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public void a() throws IOException, MqttException {
        super.a();
        new e(super.b(), super.c(), this.m, this.n, this.o).a();
        g gVar = new g(g(), this.k);
        this.l = gVar;
        gVar.a("WssSocketReceiver");
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public InputStream b() throws IOException {
        return this.k;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public OutputStream c() throws IOException {
        return this.p;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public void d() throws IOException {
        f().write(new d((byte) 8, true, "1000".getBytes()).c());
        f().flush();
        g gVar = this.l;
        if (gVar != null) {
            gVar.a();
        }
        super.d();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.p, com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public String e() {
        return "wss://" + this.n + ":" + this.o;
    }

    public OutputStream f() throws IOException {
        return super.c();
    }

    public InputStream g() throws IOException {
        return super.b();
    }
}
