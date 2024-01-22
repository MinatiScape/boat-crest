package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.util.Properties;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class WebSocketSecureNetworkModule extends SSLNetworkModule {
    public static final String w = "org.eclipse.paho.client.mqttv3.internal.websocket.WebSocketSecureNetworkModule";
    public Logger o;
    public PipedInputStream p;
    public WebSocketReceiver q;
    public String r;
    public String s;
    public int t;
    public Properties u;
    public ByteArrayOutputStream v;

    public WebSocketSecureNetworkModule(SSLSocketFactory sSLSocketFactory, String str, String str2, int i, String str3, Properties properties) {
        super(sSLSocketFactory, str2, i, str3);
        this.o = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, w);
        this.v = new a(this);
        this.r = str;
        this.s = str2;
        this.t = i;
        this.u = properties;
        this.p = new PipedInputStream();
        this.o.setResourceName(str3);
    }

    public InputStream a() throws IOException {
        return super.getInputStream();
    }

    public OutputStream b() throws IOException {
        return super.getOutputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        return this.p;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        return this.v;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule, org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "wss://" + this.s + ":" + this.t;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule, org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        super.start();
        new WebSocketHandshake(super.getInputStream(), super.getOutputStream(), this.r, this.s, this.t, this.u).execute();
        WebSocketReceiver webSocketReceiver = new WebSocketReceiver(a(), this.p);
        this.q = webSocketReceiver;
        webSocketReceiver.start("WssSocketReceiver");
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        b().write(new WebSocketFrame((byte) 8, true, "1000".getBytes()).encodeFrame());
        b().flush();
        WebSocketReceiver webSocketReceiver = this.q;
        if (webSocketReceiver != null) {
            webSocketReceiver.stop();
        }
        super.stop();
    }
}
