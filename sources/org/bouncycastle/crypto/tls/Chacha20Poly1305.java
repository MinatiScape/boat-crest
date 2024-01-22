package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.engines.ChaCha7539Engine;
import org.bouncycastle.crypto.macs.Poly1305;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class Chacha20Poly1305 implements TlsCipher {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f14847a = new byte[15];
    public TlsContext context;
    public ChaCha7539Engine decryptCipher;
    public byte[] decryptIV;
    public ChaCha7539Engine encryptCipher;
    public byte[] encryptIV;

    public Chacha20Poly1305(TlsContext tlsContext) throws IOException {
        if (!TlsUtils.isTLSv12(tlsContext)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.context = tlsContext;
        byte[] b = TlsUtils.b(tlsContext, 88);
        KeyParameter keyParameter = new KeyParameter(b, 0, 32);
        KeyParameter keyParameter2 = new KeyParameter(b, 32, 32);
        byte[] copyOfRange = Arrays.copyOfRange(b, 64, 76);
        byte[] copyOfRange2 = Arrays.copyOfRange(b, 76, 88);
        this.encryptCipher = new ChaCha7539Engine();
        this.decryptCipher = new ChaCha7539Engine();
        if (tlsContext.isServer()) {
            this.encryptIV = copyOfRange2;
            this.decryptIV = copyOfRange;
            keyParameter2 = keyParameter;
            keyParameter = keyParameter2;
        } else {
            this.encryptIV = copyOfRange;
            this.decryptIV = copyOfRange2;
        }
        this.encryptCipher.init(true, new ParametersWithIV(keyParameter, this.encryptIV));
        this.decryptCipher.init(false, new ParametersWithIV(keyParameter2, this.decryptIV));
    }

    public byte[] calculateNonce(long j, byte[] bArr) {
        byte[] bArr2 = new byte[12];
        TlsUtils.writeUint64(j, bArr2, 4);
        for (int i = 0; i < 12; i++) {
            bArr2[i] = (byte) (bArr2[i] ^ bArr[i]);
        }
        return bArr2;
    }

    public byte[] calculateRecordMAC(KeyParameter keyParameter, byte[] bArr, byte[] bArr2, int i, int i2) {
        Poly1305 poly1305 = new Poly1305();
        poly1305.init(keyParameter);
        updateRecordMACText(poly1305, bArr, 0, bArr.length);
        updateRecordMACText(poly1305, bArr2, i, i2);
        updateRecordMACLength(poly1305, bArr.length);
        updateRecordMACLength(poly1305, i2);
        byte[] bArr3 = new byte[poly1305.getMacSize()];
        poly1305.doFinal(bArr3, 0);
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        if (getPlaintextLimit(i2) >= 0) {
            int i3 = i2 - 16;
            if (Arrays.constantTimeAreEqual(calculateRecordMAC(initRecord(this.decryptCipher, false, j, this.decryptIV), getAdditionalData(j, s, i3), bArr, i, i3), Arrays.copyOfRange(bArr, i + i3, i + i2))) {
                byte[] bArr2 = new byte[i3];
                this.decryptCipher.processBytes(bArr, i, i3, bArr2, 0);
                return bArr2;
            }
            throw new TlsFatalAlert((short) 20);
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        KeyParameter initRecord = initRecord(this.encryptCipher, true, j, this.encryptIV);
        byte[] bArr2 = new byte[i2 + 16];
        this.encryptCipher.processBytes(bArr, i, i2, bArr2, 0);
        byte[] calculateRecordMAC = calculateRecordMAC(initRecord, getAdditionalData(j, s, i2), bArr2, 0, i2);
        System.arraycopy(calculateRecordMAC, 0, bArr2, i2, calculateRecordMAC.length);
        return bArr2;
    }

    public KeyParameter generateRecordMACKey(StreamCipher streamCipher) {
        byte[] bArr = new byte[64];
        streamCipher.processBytes(bArr, 0, 64, bArr, 0);
        KeyParameter keyParameter = new KeyParameter(bArr, 0, 32);
        Arrays.fill(bArr, (byte) 0);
        return keyParameter;
    }

    public byte[] getAdditionalData(long j, short s, int i) throws IOException {
        byte[] bArr = new byte[13];
        TlsUtils.writeUint64(j, bArr, 0);
        TlsUtils.writeUint8(s, bArr, 8);
        TlsUtils.writeVersion(this.context.getServerVersion(), bArr, 9);
        TlsUtils.writeUint16(i, bArr, 11);
        return bArr;
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i) {
        return i - 16;
    }

    public KeyParameter initRecord(StreamCipher streamCipher, boolean z, long j, byte[] bArr) {
        streamCipher.init(z, new ParametersWithIV(null, calculateNonce(j, bArr)));
        return generateRecordMACKey(streamCipher);
    }

    public void updateRecordMACLength(Mac mac, int i) {
        byte[] longToLittleEndian = Pack.longToLittleEndian(i & 4294967295L);
        mac.update(longToLittleEndian, 0, longToLittleEndian.length);
    }

    public void updateRecordMACText(Mac mac, byte[] bArr, int i, int i2) {
        mac.update(bArr, i, i2);
        int i3 = i2 % 16;
        if (i3 != 0) {
            mac.update(f14847a, 0, 16 - i3);
        }
    }
}
