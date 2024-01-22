package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public final class SessionParameters {

    /* renamed from: a  reason: collision with root package name */
    public int f14854a;
    public short b;
    public byte[] c;
    public Certificate d;
    public byte[] e;
    public byte[] f;
    public byte[] g;

    /* loaded from: classes13.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f14855a = -1;
        public short b = -1;
        public byte[] c = null;
        public Certificate d = null;
        public byte[] e = null;
        public byte[] f = null;
        public byte[] g = null;

        public final void a(boolean z, String str) {
            if (z) {
                return;
            }
            throw new IllegalStateException("Required session parameter '" + str + "' not configured");
        }

        public SessionParameters build() {
            a(this.f14855a >= 0, "cipherSuite");
            a(this.b >= 0, "compressionAlgorithm");
            a(this.c != null, "masterSecret");
            return new SessionParameters(this.f14855a, this.b, this.c, this.d, this.e, this.f, this.g);
        }

        public Builder setCipherSuite(int i) {
            this.f14855a = i;
            return this;
        }

        public Builder setCompressionAlgorithm(short s) {
            this.b = s;
            return this;
        }

        public Builder setMasterSecret(byte[] bArr) {
            this.c = bArr;
            return this;
        }

        public Builder setPSKIdentity(byte[] bArr) {
            this.e = bArr;
            return this;
        }

        public Builder setPeerCertificate(Certificate certificate) {
            this.d = certificate;
            return this;
        }

        public Builder setPskIdentity(byte[] bArr) {
            this.e = bArr;
            return this;
        }

        public Builder setSRPIdentity(byte[] bArr) {
            this.f = bArr;
            return this;
        }

        public Builder setServerExtensions(Hashtable hashtable) throws IOException {
            if (hashtable == null) {
                this.g = null;
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                TlsProtocol.writeExtensions(byteArrayOutputStream, hashtable);
                this.g = byteArrayOutputStream.toByteArray();
            }
            return this;
        }
    }

    public SessionParameters(int i, short s, byte[] bArr, Certificate certificate, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        this.e = null;
        this.f = null;
        this.f14854a = i;
        this.b = s;
        this.c = Arrays.clone(bArr);
        this.d = certificate;
        this.e = Arrays.clone(bArr2);
        this.f = Arrays.clone(bArr3);
        this.g = bArr4;
    }

    public void clear() {
        byte[] bArr = this.c;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    public SessionParameters copy() {
        return new SessionParameters(this.f14854a, this.b, this.c, this.d, this.e, this.f, this.g);
    }

    public int getCipherSuite() {
        return this.f14854a;
    }

    public short getCompressionAlgorithm() {
        return this.b;
    }

    public byte[] getMasterSecret() {
        return this.c;
    }

    public byte[] getPSKIdentity() {
        return this.e;
    }

    public Certificate getPeerCertificate() {
        return this.d;
    }

    public byte[] getPskIdentity() {
        return this.e;
    }

    public byte[] getSRPIdentity() {
        return this.f;
    }

    public Hashtable readServerExtensions() throws IOException {
        if (this.g == null) {
            return null;
        }
        return TlsProtocol.readExtensions(new ByteArrayInputStream(this.g));
    }
}
