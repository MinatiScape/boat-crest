package org.bouncycastle.crypto.engines;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.bouncycastle.crypto.BasicAgreement;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.EphemeralKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.KeyParser;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.generators.EphemeralKeyPairGenerator;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.IESParameters;
import org.bouncycastle.crypto.params.IESWithCipherParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class IESEngine {

    /* renamed from: a  reason: collision with root package name */
    public BasicAgreement f14686a;
    public DerivationFunction b;
    public Mac c;
    public BufferedBlockCipher d;
    public boolean e;
    public CipherParameters f;
    public CipherParameters g;
    public IESParameters h;
    public byte[] i;
    public EphemeralKeyPairGenerator j;
    public KeyParser k;
    public byte[] l;

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac) {
        this.f14686a = basicAgreement;
        this.b = derivationFunction;
        this.c = mac;
        byte[] bArr = new byte[mac.getMacSize()];
        this.d = null;
    }

    public IESEngine(BasicAgreement basicAgreement, DerivationFunction derivationFunction, Mac mac, BufferedBlockCipher bufferedBlockCipher) {
        this.f14686a = basicAgreement;
        this.b = derivationFunction;
        this.c = mac;
        byte[] bArr = new byte[mac.getMacSize()];
        this.d = bufferedBlockCipher;
    }

    public final byte[] a(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] bArr2;
        byte[] bArr3;
        int processBytes;
        if (i2 >= this.i.length + this.c.getMacSize()) {
            if (this.d == null) {
                int length = (i2 - this.i.length) - this.c.getMacSize();
                byte[] bArr4 = new byte[length];
                int macKeySize = this.h.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize];
                int i3 = length + macKeySize;
                byte[] bArr5 = new byte[i3];
                this.b.generateBytes(bArr5, 0, i3);
                if (this.i.length != 0) {
                    System.arraycopy(bArr5, 0, bArr2, 0, macKeySize);
                    System.arraycopy(bArr5, macKeySize, bArr4, 0, length);
                } else {
                    System.arraycopy(bArr5, 0, bArr4, 0, length);
                    System.arraycopy(bArr5, length, bArr2, 0, macKeySize);
                }
                bArr3 = new byte[length];
                for (int i4 = 0; i4 != length; i4++) {
                    bArr3[i4] = (byte) (bArr[(this.i.length + i) + i4] ^ bArr4[i4]);
                }
                processBytes = 0;
            } else {
                int cipherKeySize = ((IESWithCipherParameters) this.h).getCipherKeySize() / 8;
                byte[] bArr6 = new byte[cipherKeySize];
                int macKeySize2 = this.h.getMacKeySize() / 8;
                bArr2 = new byte[macKeySize2];
                int i5 = cipherKeySize + macKeySize2;
                byte[] bArr7 = new byte[i5];
                this.b.generateBytes(bArr7, 0, i5);
                System.arraycopy(bArr7, 0, bArr6, 0, cipherKeySize);
                System.arraycopy(bArr7, cipherKeySize, bArr2, 0, macKeySize2);
                CipherParameters keyParameter = new KeyParameter(bArr6);
                byte[] bArr8 = this.l;
                if (bArr8 != null) {
                    keyParameter = new ParametersWithIV(keyParameter, bArr8);
                }
                this.d.init(false, keyParameter);
                bArr3 = new byte[this.d.getOutputSize((i2 - this.i.length) - this.c.getMacSize())];
                BufferedBlockCipher bufferedBlockCipher = this.d;
                byte[] bArr9 = this.i;
                processBytes = bufferedBlockCipher.processBytes(bArr, i + bArr9.length, (i2 - bArr9.length) - this.c.getMacSize(), bArr3, 0);
            }
            byte[] encodingV = this.h.getEncodingV();
            byte[] lengthTag = this.i.length != 0 ? getLengthTag(encodingV) : null;
            int i6 = i + i2;
            byte[] copyOfRange = Arrays.copyOfRange(bArr, i6 - this.c.getMacSize(), i6);
            int length2 = copyOfRange.length;
            byte[] bArr10 = new byte[length2];
            this.c.init(new KeyParameter(bArr2));
            Mac mac = this.c;
            byte[] bArr11 = this.i;
            mac.update(bArr, i + bArr11.length, (i2 - bArr11.length) - length2);
            if (encodingV != null) {
                this.c.update(encodingV, 0, encodingV.length);
            }
            if (this.i.length != 0) {
                this.c.update(lengthTag, 0, lengthTag.length);
            }
            this.c.doFinal(bArr10, 0);
            if (Arrays.constantTimeAreEqual(copyOfRange, bArr10)) {
                BufferedBlockCipher bufferedBlockCipher2 = this.d;
                return bufferedBlockCipher2 == null ? bArr3 : Arrays.copyOfRange(bArr3, 0, processBytes + bufferedBlockCipher2.doFinal(bArr3, processBytes));
            }
            throw new InvalidCipherTextException("invalid MAC");
        }
        throw new InvalidCipherTextException("Length of input must be greater than the MAC and V combined");
    }

    public final byte[] b(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        BufferedBlockCipher bufferedBlockCipher;
        CipherParameters keyParameter;
        byte[] bArr2;
        byte[] bArr3;
        if (this.d == null) {
            byte[] bArr4 = new byte[i2];
            int macKeySize = this.h.getMacKeySize() / 8;
            bArr3 = new byte[macKeySize];
            int i3 = i2 + macKeySize;
            byte[] bArr5 = new byte[i3];
            this.b.generateBytes(bArr5, 0, i3);
            if (this.i.length != 0) {
                System.arraycopy(bArr5, 0, bArr3, 0, macKeySize);
                System.arraycopy(bArr5, macKeySize, bArr4, 0, i2);
            } else {
                System.arraycopy(bArr5, 0, bArr4, 0, i2);
                System.arraycopy(bArr5, i2, bArr3, 0, macKeySize);
            }
            bArr2 = new byte[i2];
            for (int i4 = 0; i4 != i2; i4++) {
                bArr2[i4] = (byte) (bArr[i + i4] ^ bArr4[i4]);
            }
        } else {
            int cipherKeySize = ((IESWithCipherParameters) this.h).getCipherKeySize() / 8;
            byte[] bArr6 = new byte[cipherKeySize];
            int macKeySize2 = this.h.getMacKeySize() / 8;
            byte[] bArr7 = new byte[macKeySize2];
            int i5 = cipherKeySize + macKeySize2;
            byte[] bArr8 = new byte[i5];
            this.b.generateBytes(bArr8, 0, i5);
            System.arraycopy(bArr8, 0, bArr6, 0, cipherKeySize);
            System.arraycopy(bArr8, cipherKeySize, bArr7, 0, macKeySize2);
            if (this.l != null) {
                bufferedBlockCipher = this.d;
                keyParameter = new ParametersWithIV(new KeyParameter(bArr6), this.l);
            } else {
                bufferedBlockCipher = this.d;
                keyParameter = new KeyParameter(bArr6);
            }
            bufferedBlockCipher.init(true, keyParameter);
            bArr2 = new byte[this.d.getOutputSize(i2)];
            int processBytes = this.d.processBytes(bArr, i, i2, bArr2, 0);
            i2 = processBytes + this.d.doFinal(bArr2, processBytes);
            bArr3 = bArr7;
        }
        byte[] encodingV = this.h.getEncodingV();
        byte[] lengthTag = this.i.length != 0 ? getLengthTag(encodingV) : null;
        int macSize = this.c.getMacSize();
        byte[] bArr9 = new byte[macSize];
        this.c.init(new KeyParameter(bArr3));
        this.c.update(bArr2, 0, bArr2.length);
        if (encodingV != null) {
            this.c.update(encodingV, 0, encodingV.length);
        }
        if (this.i.length != 0) {
            this.c.update(lengthTag, 0, lengthTag.length);
        }
        this.c.doFinal(bArr9, 0);
        byte[] bArr10 = this.i;
        byte[] bArr11 = new byte[bArr10.length + i2 + macSize];
        System.arraycopy(bArr10, 0, bArr11, 0, bArr10.length);
        System.arraycopy(bArr2, 0, bArr11, this.i.length, i2);
        System.arraycopy(bArr9, 0, bArr11, this.i.length + i2, macSize);
        return bArr11;
    }

    public final void c(CipherParameters cipherParameters) {
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.l = parametersWithIV.getIV();
            cipherParameters = parametersWithIV.getParameters();
        } else {
            this.l = null;
        }
        this.h = (IESParameters) cipherParameters;
    }

    public BufferedBlockCipher getCipher() {
        return this.d;
    }

    public byte[] getLengthTag(byte[] bArr) {
        byte[] bArr2 = new byte[8];
        if (bArr != null) {
            Pack.longToBigEndian(bArr.length * 8, bArr2, 0);
        }
        return bArr2;
    }

    public Mac getMac() {
        return this.c;
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, KeyParser keyParser) {
        this.e = false;
        this.f = asymmetricKeyParameter;
        this.k = keyParser;
        c(cipherParameters);
    }

    public void init(AsymmetricKeyParameter asymmetricKeyParameter, CipherParameters cipherParameters, EphemeralKeyPairGenerator ephemeralKeyPairGenerator) {
        this.e = true;
        this.g = asymmetricKeyParameter;
        this.j = ephemeralKeyPairGenerator;
        c(cipherParameters);
    }

    public void init(boolean z, CipherParameters cipherParameters, CipherParameters cipherParameters2, CipherParameters cipherParameters3) {
        this.e = z;
        this.f = cipherParameters;
        this.g = cipherParameters2;
        this.i = new byte[0];
        c(cipherParameters3);
    }

    public byte[] processBlock(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.e) {
            EphemeralKeyPairGenerator ephemeralKeyPairGenerator = this.j;
            if (ephemeralKeyPairGenerator != null) {
                EphemeralKeyPair generate = ephemeralKeyPairGenerator.generate();
                this.f = generate.getKeyPair().getPrivate();
                this.i = generate.getEncodedPublicKey();
            }
        } else if (this.k != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, i, i2);
            try {
                this.g = this.k.readKey(byteArrayInputStream);
                this.i = Arrays.copyOfRange(bArr, i, (i2 - byteArrayInputStream.available()) + i);
            } catch (IOException e) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e.getMessage(), e);
            } catch (IllegalArgumentException e2) {
                throw new InvalidCipherTextException("unable to recover ephemeral public key: " + e2.getMessage(), e2);
            }
        }
        this.f14686a.init(this.f);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.f14686a.getFieldSize(), this.f14686a.calculateAgreement(this.g));
        byte[] bArr2 = this.i;
        if (bArr2.length != 0) {
            byte[] concatenate = Arrays.concatenate(bArr2, asUnsignedByteArray);
            Arrays.fill(asUnsignedByteArray, (byte) 0);
            asUnsignedByteArray = concatenate;
        }
        try {
            this.b.init(new KDFParameters(asUnsignedByteArray, this.h.getDerivationV()));
            return this.e ? b(bArr, i, i2) : a(bArr, i, i2);
        } finally {
            Arrays.fill(asUnsignedByteArray, (byte) 0);
        }
    }
}
