package org.eclipse.paho.client.mqttv3.internal;

import com.clevertap.android.sdk.Constants;
import java.io.IOException;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class SSLNetworkModule extends TCPNetworkModule {
    public static final String n = "org.eclipse.paho.client.mqttv3.internal.SSLNetworkModule";
    public Logger g;
    public String[] h;
    public int i;
    public HostnameVerifier j;
    public boolean k;
    public String l;
    public int m;

    public SSLNetworkModule(SSLSocketFactory sSLSocketFactory, String str, int i, String str2) {
        super(sSLSocketFactory, str, i, str2);
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, n);
        this.g = logger;
        this.k = false;
        this.l = str;
        this.m = i;
        logger.setResourceName(str2);
    }

    public String[] getEnabledCiphers() {
        return this.h;
    }

    public HostnameVerifier getSSLHostnameVerifier() {
        return this.j;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public String getServerURI() {
        return "ssl://" + this.l + ":" + this.m;
    }

    public boolean isHttpsHostnameVerificationEnabled() {
        return this.k;
    }

    public void setEnabledCiphers(String[] strArr) {
        if (strArr != null) {
            this.h = (String[]) strArr.clone();
        }
        if (this.socket == null || this.h == null) {
            return;
        }
        if (this.g.isLoggable(5)) {
            String str = "";
            for (int i = 0; i < this.h.length; i++) {
                if (i > 0) {
                    str = String.valueOf(str) + Constants.SEPARATOR_COMMA;
                }
                str = String.valueOf(str) + this.h[i];
            }
            this.g.fine(n, "setEnabledCiphers", "260", new Object[]{str});
        }
        ((SSLSocket) this.socket).setEnabledCipherSuites(this.h);
    }

    public void setHttpsHostnameVerificationEnabled(boolean z) {
        this.k = z;
    }

    public void setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.j = hostnameVerifier;
    }

    public void setSSLhandshakeTimeout(int i) {
        super.setConnectTimeout(i);
        this.i = i;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.TCPNetworkModule, org.eclipse.paho.client.mqttv3.internal.NetworkModule
    public void start() throws IOException, MqttException {
        super.start();
        setEnabledCiphers(this.h);
        int soTimeout = this.socket.getSoTimeout();
        this.socket.setSoTimeout(this.i * 1000);
        try {
            SSLParameters sSLParameters = new SSLParameters();
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(new SNIHostName(this.l));
            sSLParameters.setServerNames(arrayList);
            ((SSLSocket) this.socket).setSSLParameters(sSLParameters);
        } catch (NoClassDefFoundError unused) {
        }
        if (this.k) {
            try {
                SSLParameters sSLParameters2 = new SSLParameters();
                sSLParameters2.setEndpointIdentificationAlgorithm("HTTPS");
                ((SSLSocket) this.socket).setSSLParameters(sSLParameters2);
            } catch (NoSuchMethodError unused2) {
            }
        }
        ((SSLSocket) this.socket).startHandshake();
        if (this.j != null && !this.k) {
            SSLSession session = ((SSLSocket) this.socket).getSession();
            if (!this.j.verify(this.l, session)) {
                session.invalidate();
                this.socket.close();
                throw new SSLPeerUnverifiedException("Host: " + this.l + ", Peer Host: " + session.getPeerHost());
            }
        }
        this.socket.setSoTimeout(soTimeout);
    }
}
