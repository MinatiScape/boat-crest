package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;
/* loaded from: classes13.dex */
public class Base64 {

    /* renamed from: a  reason: collision with root package name */
    public static final Base64 f15456a;
    public static final Base64Encoder b;

    /* loaded from: classes13.dex */
    public class Base64Encoder extends AbstractPreferences {

        /* renamed from: a  reason: collision with root package name */
        public String f15457a;

        public Base64Encoder(Base64 base64) {
            super(null, "");
            this.f15457a = null;
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

        public String getBase64String() {
            return this.f15457a;
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
            this.f15457a = str2;
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
        Base64 base64 = new Base64();
        f15456a = base64;
        b = new Base64Encoder(base64);
    }

    public static String encode(String str) {
        Base64Encoder base64Encoder = b;
        base64Encoder.putByteArray("akey", str.getBytes());
        return base64Encoder.getBase64String();
    }

    public static String encodeBytes(byte[] bArr) {
        Base64Encoder base64Encoder = b;
        base64Encoder.putByteArray("aKey", bArr);
        return base64Encoder.getBase64String();
    }
}
