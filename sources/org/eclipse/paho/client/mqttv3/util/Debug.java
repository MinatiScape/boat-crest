package org.eclipse.paho.client.mqttv3.util;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.Enumeration;
import java.util.Properties;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
/* loaded from: classes13.dex */
public class Debug {
    public static final String d = ClientComms.class.getName();
    public static final String e = System.getProperty("line.separator", "\n");

    /* renamed from: a  reason: collision with root package name */
    public Logger f15467a;
    public String b;
    public ClientComms c;

    public Debug(String str, ClientComms clientComms) {
        Logger logger = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, d);
        this.f15467a = logger;
        this.b = str;
        this.c = clientComms;
        logger.setResourceName(str);
    }

    public static String dumpProperties(Properties properties, String str) {
        StringBuffer stringBuffer = new StringBuffer();
        Enumeration<?> propertyNames = properties.propertyNames();
        String str2 = e;
        stringBuffer.append(String.valueOf(str2) + "==============" + HexStringBuilder.DEFAULT_SEPARATOR + str + HexStringBuilder.DEFAULT_SEPARATOR + "==============" + str2);
        while (propertyNames.hasMoreElements()) {
            String str3 = (String) propertyNames.nextElement();
            stringBuffer.append(String.valueOf(left(str3, 28, ' ')) + ":  " + properties.get(str3) + e);
        }
        stringBuffer.append("==========================================" + e);
        return stringBuffer.toString();
    }

    public static String left(String str, int i, char c) {
        if (str.length() >= i) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(i);
        stringBuffer.append(str);
        int length = i - str.length();
        while (true) {
            length--;
            if (length < 0) {
                return stringBuffer.toString();
            }
            stringBuffer.append(c);
        }
    }

    public void dumpBaseDebug() {
        dumpVersion();
        dumpSystemProperties();
        dumpMemoryTrace();
    }

    public void dumpClientComms() {
        ClientComms clientComms = this.c;
        if (clientComms != null) {
            Properties debug = clientComms.getDebug();
            Logger logger = this.f15467a;
            String str = d;
            logger.fine(str, "dumpClientComms", dumpProperties(debug, String.valueOf(this.b) + " : ClientComms").toString());
        }
    }

    public void dumpClientDebug() {
        dumpClientComms();
        dumpConOptions();
        dumpClientState();
        dumpBaseDebug();
    }

    public void dumpClientState() {
        ClientComms clientComms = this.c;
        if (clientComms == null || clientComms.getClientState() == null) {
            return;
        }
        Properties debug = this.c.getClientState().getDebug();
        Logger logger = this.f15467a;
        String str = d;
        logger.fine(str, "dumpClientState", dumpProperties(debug, String.valueOf(this.b) + " : ClientState").toString());
    }

    public void dumpConOptions() {
        ClientComms clientComms = this.c;
        if (clientComms != null) {
            Properties debug = clientComms.getConOptions().getDebug();
            Logger logger = this.f15467a;
            String str = d;
            logger.fine(str, "dumpConOptions", dumpProperties(debug, String.valueOf(this.b) + " : Connect Options").toString());
        }
    }

    public void dumpMemoryTrace() {
        this.f15467a.dumpTrace();
    }

    public void dumpSystemProperties() {
        this.f15467a.fine(d, "dumpSystemProperties", dumpProperties(System.getProperties(), "SystemProperties").toString());
    }

    public void dumpVersion() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = e;
        stringBuffer.append(String.valueOf(str) + "============== Version Info ==============" + str);
        StringBuilder sb = new StringBuilder(String.valueOf(left("Version", 20, ' ')));
        sb.append(":  ");
        sb.append(ClientComms.VERSION);
        sb.append(str);
        stringBuffer.append(sb.toString());
        stringBuffer.append(String.valueOf(left("Build Level", 20, ' ')) + ":  " + ClientComms.BUILD_LEVEL + str);
        StringBuilder sb2 = new StringBuilder("==========================================");
        sb2.append(str);
        stringBuffer.append(sb2.toString());
        this.f15467a.fine(d, "dumpVersion", stringBuffer.toString());
    }
}
