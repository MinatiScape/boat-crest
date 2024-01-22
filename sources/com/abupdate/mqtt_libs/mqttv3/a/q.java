package com.abupdate.mqtt_libs.mqttv3.a;

import com.abupdate.mqtt_libs.mqttv3.MqttException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;
/* loaded from: classes.dex */
public class q implements n {

    /* renamed from: a  reason: collision with root package name */
    public Socket f1958a;
    public SocketFactory b;
    public String c;
    public int d;
    public int e;

    public q(SocketFactory socketFactory, String str, int i, String str2) {
        this.b = socketFactory;
        this.c = str;
        this.d = i;
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.n
    public void a() throws IOException, MqttException {
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(this.c, this.d);
            SocketFactory socketFactory = this.b;
            if (socketFactory instanceof SSLSocketFactory) {
                Socket socket = new Socket();
                socket.connect(inetSocketAddress, this.e * 1000);
                this.f1958a = ((SSLSocketFactory) this.b).createSocket(socket, this.c, this.d, true);
                return;
            }
            Socket createSocket = socketFactory.createSocket();
            this.f1958a = createSocket;
            createSocket.connect(inetSocketAddress, this.e * 1000);
        } catch (ConnectException e) {
            throw new MqttException(32103, e);
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.n
    public InputStream b() throws IOException {
        return this.f1958a.getInputStream();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.n
    public OutputStream c() throws IOException {
        return this.f1958a.getOutputStream();
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.n
    public void d() throws IOException {
        Socket socket = this.f1958a;
        if (socket != null) {
            socket.shutdownInput();
            this.f1958a.close();
        }
    }

    @Override // com.abupdate.mqtt_libs.mqttv3.a.n
    public String e() {
        return "tcp://" + this.c + ":" + this.d;
    }

    public void b(int i) {
        this.e = i;
    }
}
