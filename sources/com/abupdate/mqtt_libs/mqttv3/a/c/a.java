package com.abupdate.mqtt_libs.mqttv3.a.c;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class a extends InputStream {
    public InputStream h;
    public int i = 0;

    public a(InputStream inputStream) {
        this.h = inputStream;
    }

    public int a() {
        return this.i;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int read = this.h.read();
        if (read != -1) {
            this.i++;
        }
        return read;
    }
}
