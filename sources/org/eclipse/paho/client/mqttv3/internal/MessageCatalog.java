package org.eclipse.paho.client.mqttv3.internal;
/* loaded from: classes13.dex */
public abstract class MessageCatalog {

    /* renamed from: a  reason: collision with root package name */
    public static MessageCatalog f15449a;

    public static final String getMessage(int i) {
        if (f15449a == null) {
            if (ExceptionHelper.isClassAvailable("java.util.ResourceBundle")) {
                try {
                    f15449a = (MessageCatalog) ResourceBundleCatalog.class.newInstance();
                } catch (Exception unused) {
                    return "";
                }
            } else if (ExceptionHelper.isClassAvailable("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog")) {
                try {
                    f15449a = (MessageCatalog) Class.forName("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog").newInstance();
                } catch (Exception unused2) {
                    return "";
                }
            }
        }
        return f15449a.getLocalizedMessage(i);
    }

    public abstract String getLocalizedMessage(int i);
}
