package com.abupdate.mqtt_libs.mqttv3.a.b;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import com.abupdate.mqtt_libs.mqttv3.a.q;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import javax.net.SocketFactory;
/* loaded from: classes.dex */
public class f extends q {
    public String f;
    public String g;
    public int h;
    public PipedInputStream i;
    public g j;
    public ByteArrayOutputStream k;

    public f(SocketFactory socketFactory, String str, String str2, int i, String str3) {
        super(socketFactory, str2, i, str3);
        this.k = new b(this);
        this.f = str;
        this.g = str2;
        this.h = i;
        this.i = new PipedInputStream();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public void a() throws IOException, MqttException {
        super.a();
        new e(g(), f(), this.f, this.g, this.h).a();
        g gVar = new g(g(), this.i);
        this.j = gVar;
        gVar.a("webSocketReceiver");
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public InputStream b() throws IOException {
        return this.i;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public OutputStream c() throws IOException {
        return this.k;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public void d() throws IOException {
        f().write(new d((byte) 8, true, "1000".getBytes()).c());
        f().flush();
        g gVar = this.j;
        if (gVar != null) {
            gVar.a();
        }
        super.d();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.q, com.abupdate.mqtt_libs.mqttv3.a.n
    public String e() {
        return "ws://" + this.g + ":" + this.h;
    }

    public OutputStream f() throws IOException {
        return super.c();
    }

    public InputStream g() throws IOException {
        return super.b();
    }
}
