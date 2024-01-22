package org.eclipse.paho.client.mqttv3.internal;

import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;
import org.eclipse.paho.client.mqttv3.spi.NetworkModuleFactory;
/* loaded from: classes13.dex */
public class NetworkModuleService {

    /* renamed from: a  reason: collision with root package name */
    public static Logger f15451a = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, NetworkModuleService.class.getSimpleName());
    public static final ServiceLoader<NetworkModuleFactory> b = ServiceLoader.load(NetworkModuleFactory.class, NetworkModuleService.class.getClassLoader());
    public static final Pattern c = Pattern.compile("((.+)@)?([^:]*)(:(\\d+))?");

    public static void a(URI uri, String str, Object obj) {
        try {
            Field declaredField = URI.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(uri, obj);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e) {
            f15451a.warning(NetworkModuleService.class.getName(), "setURIField", "115", new Object[]{uri.toString()}, e);
        }
    }

    public static void applyRFC3986AuthorityPatch(URI uri) {
        if (uri == null || uri.getHost() != null || uri.getAuthority() == null || uri.getAuthority().isEmpty()) {
            return;
        }
        Matcher matcher = c.matcher(uri.getAuthority());
        if (matcher.find()) {
            a(uri, "userInfo", matcher.group(2));
            a(uri, "host", matcher.group(3));
            String group = matcher.group(5);
            a(uri, "port", Integer.valueOf(group != null ? Integer.parseInt(group) : -1));
        }
    }

    public static NetworkModule createInstance(String str, MqttConnectOptions mqttConnectOptions, String str2) throws MqttException, IllegalArgumentException {
        try {
            URI uri = new URI(str);
            applyRFC3986AuthorityPatch(uri);
            String lowerCase = uri.getScheme().toLowerCase();
            ServiceLoader<NetworkModuleFactory> serviceLoader = b;
            synchronized (serviceLoader) {
                Iterator<NetworkModuleFactory> it = serviceLoader.iterator();
                while (it.hasNext()) {
                    NetworkModuleFactory next = it.next();
                    if (next.getSupportedUriSchemes().contains(lowerCase)) {
                        return next.createNetworkModule(uri, mqttConnectOptions, str2);
                    }
                }
                throw new IllegalArgumentException(uri.toString());
            }
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(str, e);
        }
    }

    public static void validateURI(String str) throws IllegalArgumentException {
        try {
            URI uri = new URI(str);
            String scheme = uri.getScheme();
            if (scheme != null && !scheme.isEmpty()) {
                String lowerCase = scheme.toLowerCase();
                ServiceLoader<NetworkModuleFactory> serviceLoader = b;
                synchronized (serviceLoader) {
                    Iterator<NetworkModuleFactory> it = serviceLoader.iterator();
                    while (it.hasNext()) {
                        NetworkModuleFactory next = it.next();
                        if (next.getSupportedUriSchemes().contains(lowerCase)) {
                            next.validateURI(uri);
                            return;
                        }
                    }
                    throw new IllegalArgumentException("no NetworkModule installed for scheme \"" + lowerCase + "\" of URI \"" + str + "\"");
                }
            }
            throw new IllegalArgumentException("missing scheme in broker URI: " + str);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Can't parse string to URI \"" + str + "\"", e);
        }
    }
}
