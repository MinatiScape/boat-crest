package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.util.Properties;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class WebSocketNetworkModule extends TCPNetworkModule {
    public static final String o = "org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketNetworkModule";
    public Logger g;
    public String h;
    public String i;
    public int j;
    public Properties k;
    public PipedInputStream l;
    public WebSocketReceiver m;
    public ByteArrayOutputStream n;

    public WebSocketNetworkModule(SocketFactory socketFactory, String str, String str2, int i, String str3, Properties properties) {
        super(socketFactory, str2, i, str3);
        this.g = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, o);
        this.n = new a(this);
        this.h = str;
        this.i = str2;
        this.j = i;
        this.k = properties;
        this.l = new PipedInputStream();
        this.g.setResourceName(str3);
    }

    public InputStream a() throws IOException {
        return super.getInputStream();
    }

    public OutputStream b() throws IOException {
        return super.getOutputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        return this.l;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        return this.n;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "ws://" + this.i + ":" + this.j;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        super.start();
        new WebSocketHandshake(a(), b(), this.h, this.i, this.j, this.k).execute();
        WebSocketReceiver webSocketReceiver = new WebSocketReceiver(a(), this.l);
        this.m = webSocketReceiver;
        webSocketReceiver.start("webSocketReceiver");
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        b().write(new WebSocketFrame((byte) 8, true, "1000".getBytes()).encodeFrame());
        b().flush();
        WebSocketReceiver webSocketReceiver = this.m;
        if (webSocketReceiver != null) {
            webSocketReceiver.stop();
        }
        super.stop();
    }
}
