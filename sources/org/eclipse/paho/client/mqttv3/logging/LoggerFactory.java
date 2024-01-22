package org.eclipse.paho.client.mqttv3.logging;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/* loaded from: classes13.dex */
public class LoggerFactory {
    public static final String MQTT_CLIENT_MSG_CAT = "org.eclipse.paho.client.mqttv3.internal.nls.logcat";

    /* renamed from: a  reason: collision with root package name */
    public static final String f15463a = "org.eclipse.paho.client.mqttv3.logging.LoggerFactory";
    public static String b;
    public static String c = JSR47Logger.class.getName();

    public static Logger a(String str, ResourceBundle resourceBundle, String str2, String str3) {
        try {
            Logger logger = (Logger) Class.forName(str).newInstance();
            logger.initialise(resourceBundle, str2, str3);
            return logger;
        } catch (ClassNotFoundException | ExceptionInInitializerError | IllegalAccessException | InstantiationException | NoClassDefFoundError | SecurityException unused) {
            return null;
        }
    }

    public static Logger getLogger(String str, String str2) {
        String str3 = b;
        if (str3 == null) {
            str3 = c;
        }
        Logger a2 = a(str3, ResourceBundle.getBundle(str), str2, null);
        if (a2 != null) {
            return a2;
        }
        throw new MissingResourceException("Error locating the logging class", f15463a, str2);
    }

    public static String getLoggingProperty(String str) {
        try {
            Class<?> cls = Class.forName("java.util.logging.LogManager");
            return (String) cls.getMethod("getProperty", String.class).invoke(cls.getMethod("getLogManager", new Class[0]).invoke(null, null), str);
        } catch (Exception unused) {
            return null;
        }
    }

    public static void setLogger(String str) {
        b = str;
    }
}
