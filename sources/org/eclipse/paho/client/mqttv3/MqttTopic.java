package org.eclipse.paho.client.mqttv3;

import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.internal.ClientComms;
import org.eclipse.paho.client.mqttv3.internal.wire.MqttPublish;
import org.eclipse.paho.client.mqttv3.util.Strings;
/* loaded from: classes13.dex */
public class MqttTopic {
    public static final String MULTI_LEVEL_WILDCARD = "#";
    public static final String MULTI_LEVEL_WILDCARD_PATTERN = "/#";
    public static final String SINGLE_LEVEL_WILDCARD = "+";
    public static final String TOPIC_LEVEL_SEPARATOR = "/";
    public static final String TOPIC_WILDCARDS = "#+";

    /* renamed from: a  reason: collision with root package name */
    public ClientComms f15439a;
    public String b;

    public MqttTopic(String str, ClientComms clientComms) {
        this.f15439a = clientComms;
        this.b = str;
    }

    public static void b(String str) {
        char charAt = "+".charAt(0);
        char charAt2 = TOPIC_LEVEL_SEPARATOR.charAt(0);
        char[] charArray = str.toCharArray();
        int length = charArray.length;
        int i = 0;
        while (i < length) {
            int i2 = i - 1;
            char c = i2 >= 0 ? charArray[i2] : (char) 0;
            int i3 = i + 1;
            char c2 = i3 < length ? charArray[i3] : (char) 0;
            if (charArray[i] == charAt && ((c != charAt2 && c != 0) || (c2 != charAt2 && c2 != 0))) {
                throw new IllegalArgumentException(String.format("Invalid usage of single-level wildcard in topic string '%s'!", str));
            }
            i = i3;
        }
    }

    public static boolean isMatched(String str, String str2) throws IllegalArgumentException {
        int length = str2.length();
        int length2 = str.length();
        validate(str, true);
        validate(str2, false);
        if (str.equals(str2)) {
            return true;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= length2 || i2 >= length) {
                break;
            } else if (str.charAt(i) != '#') {
                if ((str2.charAt(i2) == '/' && str.charAt(i) != '/') || (str.charAt(i) != '+' && str.charAt(i) != '#' && str.charAt(i) != str2.charAt(i2))) {
                    break;
                }
                if (str.charAt(i) == '+') {
                    while (true) {
                        int i3 = i2 + 1;
                        if (i3 < length && str2.charAt(i3) != '/') {
                            i2++;
                        }
                    }
                }
                i++;
                i2++;
            } else {
                i2 = length;
                i = length2;
                break;
            }
        }
        if (i2 == length && i == length2) {
            return true;
        }
        if (str.length() - i > 0 && i2 == length) {
            if (str2.charAt(i2 - 1) == '/' && str.charAt(i) == '#') {
                return true;
            }
            if (str.length() - i > 1 && str.substring(i, i + 2).equals(MULTI_LEVEL_WILDCARD_PATTERN)) {
                return true;
            }
        }
        return false;
    }

    public static void validate(String str, boolean z) throws IllegalArgumentException {
        try {
            int length = str.getBytes("UTF-8").length;
            if (length < 1 || length > 65535) {
                throw new IllegalArgumentException(String.format("Invalid topic length, should be in range[%d, %d]!", 1, 65535));
            }
            if (z) {
                if (Strings.equalsAny(str, new String[]{MULTI_LEVEL_WILDCARD, "+"})) {
                    return;
                }
                if (Strings.countMatches(str, MULTI_LEVEL_WILDCARD) <= 1 && (!str.contains(MULTI_LEVEL_WILDCARD) || str.endsWith(MULTI_LEVEL_WILDCARD_PATTERN))) {
                    b(str);
                    return;
                }
                throw new IllegalArgumentException("Invalid usage of multi-level wildcard in topic string: " + str);
            } else if (Strings.containsAny(str, TOPIC_WILDCARDS)) {
                throw new IllegalArgumentException("The topic name MUST NOT contain any wildcard characters (#+)");
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public final MqttPublish a(MqttMessage mqttMessage) {
        return new MqttPublish(getName(), mqttMessage);
    }

    public String getName() {
        return this.b;
    }

    public MqttDeliveryToken publish(byte[] bArr, int i, boolean z) throws MqttException, MqttPersistenceException {
        MqttMessage mqttMessage = new MqttMessage(bArr);
        mqttMessage.setQos(i);
        mqttMessage.setRetained(z);
        return publish(mqttMessage);
    }

    public String toString() {
        return getName();
    }

    public MqttDeliveryToken publish(MqttMessage mqttMessage) throws MqttException, MqttPersistenceException {
        MqttDeliveryToken mqttDeliveryToken = new MqttDeliveryToken(this.f15439a.getClient().getClientId());
        mqttDeliveryToken.setMessage(mqttMessage);
        this.f15439a.sendNoWait(a(mqttMessage), mqttDeliveryToken);
        mqttDeliveryToken.internalTok.waitUntilSent();
        return mqttDeliveryToken;
    }
}
