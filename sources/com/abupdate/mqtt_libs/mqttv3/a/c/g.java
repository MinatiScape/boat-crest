package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes.dex */
public class g extends OutputStream {
    public com.abupdate.mqtt_libs.mqttv3.a.b h;
    public BufferedOutputStream i;

    public g(com.abupdate.mqtt_libs.mqttv3.a.b bVar, OutputStream outputStream) {
        this.h = null;
        this.h = bVar;
        this.i = new BufferedOutputStream(outputStream);
    }

    public void a(u uVar) throws IOException, MqttException {
        byte[] k = uVar.k();
        byte[] d_ = uVar.d_();
        int i = 0;
        this.i.write(k, 0, k.length);
        this.h.b(k.length);
        while (i < d_.length) {
            int min = Math.min(1024, d_.length - i);
            this.i.write(d_, i, min);
            i += 1024;
            this.h.b(min);
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.i.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.i.write(bArr);
        this.h.b(bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.i.write(bArr, i, i2);
        this.h.b(i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        this.i.write(i);
    }
}
