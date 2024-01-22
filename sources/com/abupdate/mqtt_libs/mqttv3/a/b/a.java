package com.abupdate.mqtt_libs.mqttv3.a.b;

import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static final a f1946a;
    public static final C0199a b;

    /* renamed from: com.abupdate.mqtt_libs.mqttv3.a.b.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0199a extends AbstractPreferences {

        /* renamed from: a  reason: collision with root package name */
        public String f1947a;

        public C0199a(a aVar) {
            super(null, "");
            this.f1947a = null;
        }

        public String a() {
            return this.f1947a;
        }

        @Override // java.util.prefs.AbstractPreferences
        public AbstractPreferences childSpi(String str) {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        public String[] childrenNamesSpi() throws BackingStoreException {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        public void flushSpi() throws BackingStoreException {
        }

        @Override // java.util.prefs.AbstractPreferences
        public String getSpi(String str) {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        public String[] keysSpi() throws BackingStoreException {
            return null;
        }

        @Override // java.util.prefs.AbstractPreferences
        public void putSpi(String str, String str2) {
            this.f1947a = str2;
        }

        @Override // java.util.prefs.AbstractPreferences
        public void removeNodeSpi() throws BackingStoreException {
        }

        @Override // java.util.prefs.AbstractPreferences
        public void removeSpi(String str) {
        }

        @Override // java.util.prefs.AbstractPreferences
        public void syncSpi() throws BackingStoreException {
        }
    }

    static {
        a aVar = new a();
        f1946a = aVar;
        b = new C0199a(aVar);
    }

    public static String a(String str) {
        C0199a c0199a = b;
        c0199a.putByteArray("akey", str.getBytes());
        return c0199a.a();
    }

    public static String a(byte[] bArr) {
        C0199a c0199a = b;
        c0199a.putByteArray("aKey", bArr);
        return c0199a.a();
    }
}
