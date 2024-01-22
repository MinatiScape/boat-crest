package com.abupdate.mqtt_libs.mqttv3.a.c;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
/* loaded from: classes.dex */
public class f extends InputStream {
    public com.abupdate.mqtt_libs.mqttv3.a.b h;
    public DataInputStream i;
    public ByteArrayOutputStream j = new ByteArrayOutputStream();
    public long k = -1;
    public long l;
    public byte[] m;

    public f(com.abupdate.mqtt_libs.mqttv3.a.b bVar, InputStream inputStream) {
        this.h = null;
        this.h = bVar;
        this.i = new DataInputStream(inputStream);
    }

    public u a() throws IOException, MqttException {
        try {
            if (this.k < 0) {
                this.j.reset();
                byte readByte = this.i.readByte();
                this.h.c(1);
                byte b = (byte) ((readByte >>> 4) & 15);
                if (b >= 1 && b <= 14) {
                    this.k = u.a(this.i).a();
                    this.j.write(readByte);
                    this.j.write(u.a(this.k));
                    this.m = new byte[(int) (this.j.size() + this.k)];
                    this.l = 0L;
                } else {
                    throw com.abupdate.mqtt_libs.mqttv3.a.i.a(32108);
                }
            }
            if (this.k >= 0) {
                b();
                this.k = -1L;
                byte[] byteArray = this.j.toByteArray();
                System.arraycopy(byteArray, 0, this.m, 0, byteArray.length);
                return u.a(this.m);
            }
            return null;
        } catch (SocketTimeoutException unused) {
            return null;
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.i.available();
    }

    public final void b() throws IOException {
        int size = this.j.size();
        long j = this.l;
        int i = size + ((int) j);
        int i2 = (int) (this.k - j);
        if (i2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i3 = 0;
        while (i3 < i2) {
            try {
                int read = this.i.read(this.m, i + i3, i2 - i3);
                this.h.c(read);
                if (read < 0) {
                    throw new EOFException();
                }
                i3 += read;
            } catch (SocketTimeoutException e) {
                this.l += i3;
                throw e;
            }
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.i.close();
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        return this.i.read();
    }
}
