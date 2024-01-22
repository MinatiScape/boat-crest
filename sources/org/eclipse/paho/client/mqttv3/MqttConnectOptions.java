package org.eclipse.paho.client.mqttv3;

import java.util.Properties;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import org.eclipse.paho.client.mqttv3.internal.NetworkModuleService;
import org.eclipse.paho.client.mqttv3.util.Debug;
/* loaded from: classes13.dex */
public class MqttConnectOptions {
    public static final boolean CLEAN_SESSION_DEFAULT = true;
    public static final int CONNECTION_TIMEOUT_DEFAULT = 30;
    public static final int KEEP_ALIVE_INTERVAL_DEFAULT = 60;
    public static final int MAX_INFLIGHT_DEFAULT = 10;
    public static final int MQTT_VERSION_3_1 = 3;
    public static final int MQTT_VERSION_3_1_1 = 4;
    public static final int MQTT_VERSION_DEFAULT = 0;
    public String e;
    public char[] f;
    public SocketFactory g;

    /* renamed from: a  reason: collision with root package name */
    public int f15438a = 60;
    public int b = 10;
    public String c = null;
    public MqttMessage d = null;
    public Properties h = null;
    public boolean i = true;
    public HostnameVerifier j = null;
    public boolean k = true;
    public int l = 30;
    public String[] m = null;
    public int n = 0;
    public boolean o = false;
    public int p = 128000;
    public Properties q = null;
    public int r = 1;

    public final void a(String str, Object obj) {
        if (str != null && obj != null) {
            MqttTopic.validate(str, false);
            return;
        }
        throw new IllegalArgumentException();
    }

    public int getConnectionTimeout() {
        return this.l;
    }

    public Properties getCustomWebSocketHeaders() {
        return this.q;
    }

    public Properties getDebug() {
        Properties properties = new Properties();
        properties.put("MqttVersion", Integer.valueOf(getMqttVersion()));
        properties.put("CleanSession", Boolean.valueOf(isCleanSession()));
        properties.put("ConTimeout", Integer.valueOf(getConnectionTimeout()));
        properties.put("KeepAliveInterval", Integer.valueOf(getKeepAliveInterval()));
        properties.put("UserName", getUserName() == null ? "null" : getUserName());
        properties.put("WillDestination", getWillDestination() == null ? "null" : getWillDestination());
        if (getSocketFactory() == null) {
            properties.put("SocketFactory", "null");
        } else {
            properties.put("SocketFactory", getSocketFactory());
        }
        if (getSSLProperties() == null) {
            properties.put("SSLProperties", "null");
        } else {
            properties.put("SSLProperties", getSSLProperties());
        }
        return properties;
    }

    public int getExecutorServiceTimeout() {
        return this.r;
    }

    public int getKeepAliveInterval() {
        return this.f15438a;
    }

    public int getMaxInflight() {
        return this.b;
    }

    public int getMaxReconnectDelay() {
        return this.p;
    }

    public int getMqttVersion() {
        return this.n;
    }

    public char[] getPassword() {
        return this.f;
    }

    public HostnameVerifier getSSLHostnameVerifier() {
        return this.j;
    }

    public Properties getSSLProperties() {
        return this.h;
    }

    public String[] getServerURIs() {
        return this.m;
    }

    public SocketFactory getSocketFactory() {
        return this.g;
    }

    public String getUserName() {
        return this.e;
    }

    public String getWillDestination() {
        return this.c;
    }

    public MqttMessage getWillMessage() {
        return this.d;
    }

    public boolean isAutomaticReconnect() {
        return this.o;
    }

    public boolean isCleanSession() {
        return this.k;
    }

    public boolean isHttpsHostnameVerificationEnabled() {
        return this.i;
    }

    public void setAutomaticReconnect(boolean z) {
        this.o = z;
    }

    public void setCleanSession(boolean z) {
        this.k = z;
    }

    public void setConnectionTimeout(int i) {
        if (i >= 0) {
            this.l = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setCustomWebSocketHeaders(Properties properties) {
        this.q = properties;
    }

    public void setExecutorServiceTimeout(int i) {
        this.r = i;
    }

    public void setHttpsHostnameVerificationEnabled(boolean z) {
        this.i = z;
    }

    public void setKeepAliveInterval(int i) throws IllegalArgumentException {
        if (i >= 0) {
            this.f15438a = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setMaxInflight(int i) {
        if (i >= 0) {
            this.b = i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setMaxReconnectDelay(int i) {
        this.p = i;
    }

    public void setMqttVersion(int i) throws IllegalArgumentException {
        if (i != 0 && i != 3 && i != 4) {
            throw new IllegalArgumentException("An incorrect version was used \"" + i + "\". Acceptable version options are 0, 3 and 4.");
        }
        this.n = i;
    }

    public void setPassword(char[] cArr) {
        this.f = (char[]) cArr.clone();
    }

    public void setSSLHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.j = hostnameVerifier;
    }

    public void setSSLProperties(Properties properties) {
        this.h = properties;
    }

    public void setServerURIs(String[] strArr) {
        for (String str : strArr) {
            NetworkModuleService.validateURI(str);
        }
        this.m = (String[]) strArr.clone();
    }

    public void setSocketFactory(SocketFactory socketFactory) {
        this.g = socketFactory;
    }

    public void setUserName(String str) {
        this.e = str;
    }

    public void setWill(MqttTopic mqttTopic, byte[] bArr, int i, boolean z) {
        String name = mqttTopic.getName();
        a(name, bArr);
        setWill(name, new MqttMessage(bArr), i, z);
    }

    public String toString() {
        return Debug.dumpProperties(getDebug(), "Connection options");
    }

    public void setWill(String str, byte[] bArr, int i, boolean z) {
        a(str, bArr);
        setWill(str, new MqttMessage(bArr), i, z);
    }

    public void setWill(String str, MqttMessage mqttMessage, int i, boolean z) {
        this.c = str;
        this.d = mqttMessage;
        mqttMessage.setQos(i);
        this.d.setRetained(z);
        this.d.setMutable(false);
    }
}
