package com.abupdate.mqtt_libs.mqttv3.a.b;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
/* loaded from: classes.dex */
public class g implements Runnable {
    public InputStream k;
    public PipedOutputStream m;
    public boolean h = false;
    public boolean i = false;
    public Object j = new Object();
    public Thread l = null;

    public g(InputStream inputStream, PipedInputStream pipedInputStream) throws IOException {
        this.k = inputStream;
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        this.m = pipedOutputStream;
        pipedInputStream.connect(pipedOutputStream);
    }

    public void a(String str) {
        synchronized (this.j) {
            if (!this.h) {
                this.h = true;
                Thread thread = new Thread(this, str);
                this.l = thread;
                thread.start();
            }
        }
    }

    public final void b() {
        try {
            this.m.close();
        } catch (IOException unused) {
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        InputStream inputStream;
        while (this.h && (inputStream = this.k) != null) {
            try {
                inputStream.available();
                d dVar = new d(this.k);
                if (!dVar.b()) {
                    for (int i = 0; i < dVar.a().length; i++) {
                        this.m.write(dVar.a()[i]);
                    }
                    this.m.flush();
                } else if (!this.i) {
                    throw new IOException("Server sent a WebSocket Frame with the Stop OpCode");
                    break;
                }
            } catch (IOException unused) {
                a();
            }
        }
    }

    public void a() {
        boolean z = true;
        this.i = true;
        synchronized (this.j) {
            if (this.h) {
                this.h = false;
                b();
            } else {
                z = false;
            }
        }
        if (z && !Thread.currentThread().equals(this.l)) {
            try {
                this.l.join();
            } catch (InterruptedException unused) {
            }
        }
        this.l = null;
    }
}
