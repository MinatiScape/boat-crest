package com.abupdate.mqtt_libs.mqttv3.a;
/* loaded from: classes.dex */
public abstract class l {

    /* renamed from: a  reason: collision with root package name */
    public static l f1956a;

    public static final String a(int i) {
        if (f1956a == null) {
            if (i.a("java.util.ResourceBundle")) {
                try {
                    f1956a = (l) o.class.newInstance();
                } catch (Exception unused) {
                    return "";
                }
            } else if (i.a("com.abupdate.mqtt_libs.mqttv3.internal.MIDPCatalog")) {
                try {
                    f1956a = (l) Class.forName("com.abupdate.mqtt_libs.mqttv3.internal.MIDPCatalog").newInstance();
                } catch (Exception unused2) {
                    return "";
                }
            }
        }
        return f1956a.b(i);
    }

    public abstract String b(int i);
}
