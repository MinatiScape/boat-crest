package org.eclipse.paho.client.mqttv3.internal.websocket;

import com.coveiot.android.leonardo.utils.MusicConstants;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.SocketTimeoutException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class WebSocketReceiver implements Runnable {
    public static final String p = WebSocketReceiver.class.getName();
    public InputStream l;
    public volatile boolean n;
    public PipedOutputStream o;
    public Logger h = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, p);
    public boolean i = false;
    public boolean j = false;
    public final Object k = new Object();
    public Thread m = null;

    public WebSocketReceiver(InputStream inputStream, PipedInputStream pipedInputStream) throws IOException {
        this.l = inputStream;
        PipedOutputStream pipedOutputStream = new PipedOutputStream();
        this.o = pipedOutputStream;
        pipedInputStream.connect(pipedOutputStream);
    }

    public final void a() {
        try {
            this.o.close();
        } catch (IOException unused) {
        }
    }

    public boolean isReceiving() {
        return this.n;
    }

    public boolean isRunning() {
        return this.i;
    }

    @Override // java.lang.Runnable
    public void run() {
        while (this.i && this.l != null) {
            try {
                this.h.fine(p, "run", "852");
                this.n = this.l.available() > 0;
                WebSocketFrame webSocketFrame = new WebSocketFrame(this.l);
                if (!webSocketFrame.isCloseFlag()) {
                    for (int i = 0; i < webSocketFrame.getPayload().length; i++) {
                        this.o.write(webSocketFrame.getPayload()[i]);
                    }
                    this.o.flush();
                } else if (!this.j) {
                    throw new IOException("Server sent a WebSocket Frame with the Stop OpCode");
                    break;
                }
                this.n = false;
            } catch (SocketTimeoutException unused) {
            } catch (IOException unused2) {
                stop();
            }
        }
    }

    public void start(String str) {
        this.h.fine(p, "start", "855");
        synchronized (this.k) {
            if (!this.i) {
                this.i = true;
                Thread thread = new Thread(this, str);
                this.m = thread;
                thread.start();
            }
        }
    }

    public void stop() {
        Thread thread;
        boolean z = true;
        this.j = true;
        synchronized (this.k) {
            this.h.fine(p, MusicConstants.CMDSTOP, "850");
            if (this.i) {
                this.i = false;
                this.n = false;
                a();
            } else {
                z = false;
            }
        }
        if (z && !Thread.currentThread().equals(this.m) && (thread = this.m) != null) {
            try {
                thread.join();
            } catch (InterruptedException unused) {
            }
        }
        this.m = null;
        this.h.fine(p, MusicConstants.CMDSTOP, "851");
    }
}
