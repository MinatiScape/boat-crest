package org.eclipse.paho.client.mqttv3.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class TCPNetworkModule implements NetworkModule {
    public static final String f = "org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule";

    /* renamed from: a  reason: collision with root package name */
    public Logger f15452a;
    public SocketFactory b;
    public String c;
    public int d;
    public int e;
    public Socket socket;

    public TCPNetworkModule(SocketFactory socketFactory, String str, int i, String str2) {
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, f);
        this.f15452a = logger;
        logger.setResourceName(str2);
        this.b = socketFactory;
        this.c = str;
        this.d = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "tcp://" + this.c + ":" + this.d;
    }

    public void setConnectTimeout(int i) {
        this.e = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        try {
            this.f15452a.fine(f, "start", "252", new Object[]{this.c, Integer.valueOf(this.d), Long.valueOf(this.e * 1000)});
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.c, this.d);
            Socket createSocket = this.b.createSocket();
            this.socket = createSocket;
            createSocket.connect(inetSocketAddress, this.e * 1000);
            this.socket.setSoTimeout(1000);
        } catch (ConnectException e) {
            this.f15452a.fine(f, "start", "250", null, e);
            throw new MqttException(32103, e);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void stop() throws IOException {
        Socket socket = this.socket;
        if (socket != null) {
            socket.close();
        }
    }
}
