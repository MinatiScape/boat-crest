package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class TlsNullCipher implements TlsCipher {
    public TlsContext context;
    public TlsMac readMac;
    public TlsMac writeMac;

    public TlsNullCipher(TlsContext tlsContext) {
        this.context = tlsContext;
        this.writeMac = null;
        this.readMac = null;
    }

    public TlsNullCipher(TlsContext tlsContext, Digest digest, Digest digest2) throws IOException {
        TlsMac tlsMac;
        if ((digest == null) != (digest2 == null)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.context = tlsContext;
        TlsMac tlsMac2 = null;
        if (digest != null) {
            int digestSize = digest.getDigestSize() + digest2.getDigestSize();
            byte[] b = TlsUtils.b(tlsContext, digestSize);
            TlsMac tlsMac3 = new TlsMac(tlsContext, digest, b, 0, digest.getDigestSize());
            int digestSize2 = digest.getDigestSize() + 0;
            tlsMac = new TlsMac(tlsContext, digest2, b, digestSize2, digest2.getDigestSize());
            if (digestSize2 + digest2.getDigestSize() != digestSize) {
                throw new TlsFatalAlert((short) 80);
            }
            tlsMac2 = tlsMac3;
        } else {
            tlsMac = null;
        }
        if (tlsContext.isServer()) {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
            return;
        }
        this.writeMac = tlsMac2;
        this.readMac = tlsMac;
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        TlsMac tlsMac = this.readMac;
        if (tlsMac == null) {
            return Arrays.copyOfRange(bArr, i, i2 + i);
        }
        int size = tlsMac.getSize();
        if (i2 >= size) {
            int i3 = i2 - size;
            int i4 = i + i3;
            if (Arrays.constantTimeAreEqual(Arrays.copyOfRange(bArr, i4, i2 + i), this.readMac.calculateMac(j, s, bArr, i, i3))) {
                return Arrays.copyOfRange(bArr, i, i4);
            }
            throw new TlsFatalAlert((short) 20);
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        TlsMac tlsMac = this.writeMac;
        if (tlsMac == null) {
            return Arrays.copyOfRange(bArr, i, i2 + i);
        }
        byte[] calculateMac = tlsMac.calculateMac(j, s, bArr, i, i2);
        byte[] bArr2 = new byte[calculateMac.length + i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        System.arraycopy(calculateMac, 0, bArr2, i2, calculateMac.length);
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i) {
        TlsMac tlsMac = this.writeMac;
        return tlsMac != null ? i - tlsMac.getSize() : i;
    }
}
