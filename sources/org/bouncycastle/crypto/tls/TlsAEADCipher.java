package org.bouncycastle.crypto.tls;

import java.io.IOException;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class TlsAEADCipher implements TlsCipher {
    public static final int NONCE_RFC5288 = 1;
    public TlsContext context;
    public AEADBlockCipher decryptCipher;
    public byte[] decryptImplicitNonce;
    public AEADBlockCipher encryptCipher;
    public byte[] encryptImplicitNonce;
    public int macSize;
    public int nonceMode;
    public int record_iv_length;

    public TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int i, int i2) throws IOException {
        this(tlsContext, aEADBlockCipher, aEADBlockCipher2, i, i2, 1);
    }

    public TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int i, int i2, int i3) throws IOException {
        int i4;
        if (!TlsUtils.isTLSv12(tlsContext)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.nonceMode = i3;
        if (i3 == 1) {
            i4 = 4;
            this.record_iv_length = 8;
        } else if (i3 != 2) {
            throw new TlsFatalAlert((short) 80);
        } else {
            i4 = 12;
            this.record_iv_length = 0;
        }
        this.context = tlsContext;
        this.macSize = i2;
        int i5 = (i * 2) + (i4 * 2);
        byte[] b = TlsUtils.b(tlsContext, i5);
        KeyParameter keyParameter = new KeyParameter(b, 0, i);
        int i6 = i + 0;
        KeyParameter keyParameter2 = new KeyParameter(b, i6, i);
        int i7 = i6 + i;
        int i8 = i7 + i4;
        byte[] copyOfRange = Arrays.copyOfRange(b, i7, i8);
        int i9 = i8 + i4;
        byte[] copyOfRange2 = Arrays.copyOfRange(b, i8, i9);
        if (i9 != i5) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsContext.isServer()) {
            this.encryptCipher = aEADBlockCipher2;
            this.decryptCipher = aEADBlockCipher;
            this.encryptImplicitNonce = copyOfRange2;
            this.decryptImplicitNonce = copyOfRange;
            keyParameter2 = keyParameter;
            keyParameter = keyParameter2;
        } else {
            this.encryptCipher = aEADBlockCipher;
            this.decryptCipher = aEADBlockCipher2;
            this.encryptImplicitNonce = copyOfRange;
            this.decryptImplicitNonce = copyOfRange2;
        }
        byte[] bArr = new byte[i4 + this.record_iv_length];
        int i10 = i2 * 8;
        this.encryptCipher.init(true, new AEADParameters(keyParameter, i10, bArr));
        this.decryptCipher.init(false, new AEADParameters(keyParameter2, i10, bArr));
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        if (getPlaintextLimit(i2) >= 0) {
            byte[] bArr2 = this.decryptImplicitNonce;
            int length = bArr2.length + this.record_iv_length;
            byte[] bArr3 = new byte[length];
            int i3 = this.nonceMode;
            if (i3 == 1) {
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                int i4 = this.record_iv_length;
                System.arraycopy(bArr, i, bArr3, length - i4, i4);
            } else if (i3 != 2) {
                throw new TlsFatalAlert((short) 80);
            } else {
                TlsUtils.writeUint64(j, bArr3, length - 8);
                int i5 = 0;
                while (true) {
                    byte[] bArr4 = this.decryptImplicitNonce;
                    if (i5 >= bArr4.length) {
                        break;
                    }
                    bArr3[i5] = (byte) (bArr4[i5] ^ bArr3[i5]);
                    i5++;
                }
            }
            int i6 = this.record_iv_length;
            int i7 = i + i6;
            int i8 = i2 - i6;
            int outputSize = this.decryptCipher.getOutputSize(i8);
            byte[] bArr5 = new byte[outputSize];
            try {
                this.decryptCipher.init(false, new AEADParameters(null, this.macSize * 8, bArr3, getAdditionalData(j, s, outputSize)));
                int processBytes = this.decryptCipher.processBytes(bArr, i7, i8, bArr5, 0) + 0;
                if (processBytes + this.decryptCipher.doFinal(bArr5, processBytes) == outputSize) {
                    return bArr5;
                }
                throw new TlsFatalAlert((short) 80);
            } catch (Exception e) {
                throw new TlsFatalAlert((short) 20, e);
            }
        }
        throw new TlsFatalAlert((short) 50);
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        byte[] bArr2 = this.encryptImplicitNonce;
        int length = bArr2.length + this.record_iv_length;
        byte[] bArr3 = new byte[length];
        int i3 = this.nonceMode;
        if (i3 == 1) {
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            TlsUtils.writeUint64(j, bArr3, this.encryptImplicitNonce.length);
        } else if (i3 != 2) {
            throw new TlsFatalAlert((short) 80);
        } else {
            TlsUtils.writeUint64(j, bArr3, length - 8);
            int i4 = 0;
            while (true) {
                byte[] bArr4 = this.encryptImplicitNonce;
                if (i4 >= bArr4.length) {
                    break;
                }
                bArr3[i4] = (byte) (bArr4[i4] ^ bArr3[i4]);
                i4++;
            }
        }
        int outputSize = this.encryptCipher.getOutputSize(i2);
        int i5 = this.record_iv_length;
        int i6 = i5 + outputSize;
        byte[] bArr5 = new byte[i6];
        if (i5 != 0) {
            System.arraycopy(bArr3, length - i5, bArr5, 0, i5);
        }
        int i7 = this.record_iv_length;
        try {
            this.encryptCipher.init(true, new AEADParameters(null, this.macSize * 8, bArr3, getAdditionalData(j, s, i2)));
            int processBytes = i7 + this.encryptCipher.processBytes(bArr, i, i2, bArr5, i7);
            if (processBytes + this.encryptCipher.doFinal(bArr5, processBytes) == i6) {
                return bArr5;
            }
            throw new TlsFatalAlert((short) 80);
        } catch (Exception e) {
            throw new TlsFatalAlert((short) 80, e);
        }
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
        return (i - this.macSize) - this.record_iv_length;
    }
}
