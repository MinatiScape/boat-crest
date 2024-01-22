package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes13.dex */
public class a extends ByteArrayOutputStream {
    public final WebSocketNetworkModule h;
    public final WebSocketSecureNetworkModule i;

    public a(WebSocketNetworkModule webSocketNetworkModule) {
        this.h = webSocketNetworkModule;
        this.i = null;
    }

    public OutputStream a() throws IOException {
        WebSocketNetworkModule webSocketNetworkModule = this.h;
        if (webSocketNetworkModule != null) {
            return webSocketNetworkModule.b();
        }
        WebSocketSecureNetworkModule webSocketSecureNetworkModule = this.i;
        if (webSocketSecureNetworkModule != null) {
            return webSocketSecureNetworkModule.b();
        }
        return null;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        ByteBuffer wrap;
        synchronized (this) {
            wrap = ByteBuffer.wrap(toByteArray());
            reset();
        }
        a().write(new WebSocketFrame((byte) 2, true, wrap.array()).encodeFrame());
        a().flush();
    }

    public a(WebSocketSecureNetworkModule webSocketSecureNetworkModule) {
        this.h = null;
        this.i = webSocketSecureNetworkModule;
    }
}
