package org.bouncycastle.crypto.tls;

import com.touchgui.sdk.TGEventListener;
import java.io.IOException;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public final class ProtocolVersion {

    /* renamed from: a  reason: collision with root package name */
    public int f14851a;
    public String b;
    public static final ProtocolVersion SSLv3 = new ProtocolVersion(768, "SSL 3.0");
    public static final ProtocolVersion TLSv10 = new ProtocolVersion(TGEventListener.ALARM_UPDATED, "TLS 1.0");
    public static final ProtocolVersion TLSv11 = new ProtocolVersion(TGEventListener.REQUEST_UPDATE_WEATHER, "TLS 1.1");
    public static final ProtocolVersion TLSv12 = new ProtocolVersion(TGEventListener.AWAKE_SCREEN_UPDATED, "TLS 1.2");
    public static final ProtocolVersion DTLSv10 = new ProtocolVersion(65279, "DTLS 1.0");
    public static final ProtocolVersion DTLSv12 = new ProtocolVersion(65277, "DTLS 1.2");

    public ProtocolVersion(int i, String str) {
        this.f14851a = i & 65535;
        this.b = str;
    }

    public static ProtocolVersion a(int i, int i2, String str) throws IOException {
        TlsUtils.checkUint8(i);
        TlsUtils.checkUint8(i2);
        int i3 = (i << 8) | i2;
        String upperCase = Strings.toUpperCase(Integer.toHexString(65536 | i3).substring(1));
        return new ProtocolVersion(i3, str + " 0x" + upperCase);
    }

    public static ProtocolVersion get(int i, int i2) throws IOException {
        String str;
        if (i != 3) {
            if (i == 254) {
                switch (i2) {
                    case 253:
                        return DTLSv12;
                    case 254:
                        throw new TlsFatalAlert((short) 47);
                    case 255:
                        return DTLSv10;
                    default:
                        str = "DTLS";
                        break;
                }
            } else {
                throw new TlsFatalAlert((short) 47);
            }
        } else if (i2 == 0) {
            return SSLv3;
        } else {
            if (i2 == 1) {
                return TLSv10;
            }
            if (i2 == 2) {
                return TLSv11;
            }
            if (i2 == 3) {
                return TLSv12;
            }
            str = "TLS";
        }
        return a(i, i2, str);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ProtocolVersion) && equals((ProtocolVersion) obj));
    }

    public boolean equals(ProtocolVersion protocolVersion) {
        return protocolVersion != null && this.f14851a == protocolVersion.f14851a;
    }

    public ProtocolVersion getEquivalentTLSVersion() {
        return !isDTLS() ? this : this == DTLSv10 ? TLSv11 : TLSv12;
    }

    public int getFullVersion() {
        return this.f14851a;
    }

    public int getMajorVersion() {
        return this.f14851a >> 8;
    }

    public int getMinorVersion() {
        return this.f14851a & 255;
    }

    public int hashCode() {
        return this.f14851a;
    }

    public boolean isDTLS() {
        return getMajorVersion() == 254;
    }

    public boolean isEqualOrEarlierVersionOf(ProtocolVersion protocolVersion) {
        if (getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = protocolVersion.getMinorVersion() - getMinorVersion();
        if (isDTLS()) {
            if (minorVersion > 0) {
                return false;
            }
        } else if (minorVersion < 0) {
            return false;
        }
        return true;
    }

    public boolean isLaterVersionOf(ProtocolVersion protocolVersion) {
        if (getMajorVersion() != protocolVersion.getMajorVersion()) {
            return false;
        }
        int minorVersion = protocolVersion.getMinorVersion() - getMinorVersion();
        if (isDTLS()) {
            if (minorVersion <= 0) {
                return false;
            }
        } else if (minorVersion >= 0) {
            return false;
        }
        return true;
    }

    public boolean isSSL() {
        return this == SSLv3;
    }

    public boolean isTLS() {
        return getMajorVersion() == 3;
    }

    public String toString() {
        return this.b;
    }
}
