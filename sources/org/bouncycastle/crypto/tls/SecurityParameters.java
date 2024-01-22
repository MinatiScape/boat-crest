package org.bouncycastle.crypto.tls;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class SecurityParameters {

    /* renamed from: a  reason: collision with root package name */
    public int f14853a = -1;
    public int b = -1;
    public short c = 0;
    public int d = -1;
    public int e = -1;
    public byte[] f = null;
    public byte[] g = null;
    public byte[] h = null;
    public byte[] i = null;
    public byte[] j = null;
    public byte[] k = null;
    public short l = -1;
    public boolean m = false;
    public boolean n = false;
    public boolean o = false;

    public void a() {
        byte[] bArr = this.f;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            this.f = null;
        }
    }

    public int getCipherSuite() {
        return this.b;
    }

    public byte[] getClientRandom() {
        return this.g;
    }

    public short getCompressionAlgorithm() {
        return this.c;
    }

    public int getEntity() {
        return this.f14853a;
    }

    public byte[] getMasterSecret() {
        return this.f;
    }

    public byte[] getPSKIdentity() {
        return this.j;
    }

    public int getPrfAlgorithm() {
        return this.d;
    }

    public byte[] getPskIdentity() {
        return this.j;
    }

    public byte[] getSRPIdentity() {
        return this.k;
    }

    public byte[] getServerRandom() {
        return this.h;
    }

    public byte[] getSessionHash() {
        return this.i;
    }

    public int getVerifyDataLength() {
        return this.e;
    }
}
