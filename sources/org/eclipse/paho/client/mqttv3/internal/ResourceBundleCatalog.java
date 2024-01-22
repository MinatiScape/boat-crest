package org.eclipse.paho.client.mqttv3.internal;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/* loaded from: classes13.dex */
public class ResourceBundleCatalog extends MessageCatalog {
    public ResourceBundle b = ResourceBundle.getBundle("org.eclipse.paho.client.mqttv3.internal.nls.messages");

    @Override // org.eclipse.paho.client.mqttv3.internal.MessageCatalog
    public String getLocalizedMessage(int i) {
        try {
            return this.b.getString(Integer.toString(i));
        } catch (MissingResourceException unused) {
            return "MqttException";
        }
    }
}
