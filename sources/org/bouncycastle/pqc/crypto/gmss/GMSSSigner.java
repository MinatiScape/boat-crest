package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSUtil;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class GMSSSigner implements MessageSigner {

    /* renamed from: a  reason: collision with root package name */
    public GMSSUtil f15292a = new GMSSUtil();
    public byte[] b;
    public Digest c;
    public int d;
    public int e;
    public Digest f;
    public WinternitzOTSignature g;
    public GMSSDigestProvider h;
    public int[] i;
    public byte[][][] j;
    public byte[][] k;
    public GMSSParameters l;
    public GMSSRandom m;
    public GMSSKeyParameters n;

    public GMSSSigner(GMSSDigestProvider gMSSDigestProvider) {
        this.h = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.c = digest;
        this.f = digest;
        this.d = digest.getDigestSize();
        this.m = new GMSSRandom(this.c);
    }

    public final void a() {
        int i;
        this.c.reset();
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = (GMSSPrivateKeyParameters) this.n;
        if (gMSSPrivateKeyParameters.isUsed()) {
            throw new IllegalStateException("Private key already used");
        }
        if (gMSSPrivateKeyParameters.getIndex(0) >= gMSSPrivateKeyParameters.getNumLeafs(0)) {
            throw new IllegalStateException("No more signatures can be generated");
        }
        GMSSParameters parameters = gMSSPrivateKeyParameters.getParameters();
        this.l = parameters;
        this.e = parameters.getNumOfLayers();
        byte[] bArr = gMSSPrivateKeyParameters.getCurrentSeeds()[this.e - 1];
        int i2 = this.d;
        byte[] bArr2 = new byte[i2];
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr, 0, bArr3, 0, i2);
        this.g = new WinternitzOTSignature(this.m.nextSeed(bArr3), this.h.get(), this.l.getWinternitzParameter()[this.e - 1]);
        byte[][][] currentAuthPaths = gMSSPrivateKeyParameters.getCurrentAuthPaths();
        this.j = new byte[this.e][];
        int i3 = 0;
        while (true) {
            i = this.e;
            if (i3 >= i) {
                break;
            }
            this.j[i3] = (byte[][]) Array.newInstance(byte.class, currentAuthPaths[i3].length, this.d);
            for (int i4 = 0; i4 < currentAuthPaths[i3].length; i4++) {
                System.arraycopy(currentAuthPaths[i3][i4], 0, this.j[i3][i4], 0, this.d);
            }
            i3++;
        }
        this.i = new int[i];
        System.arraycopy(gMSSPrivateKeyParameters.getIndex(), 0, this.i, 0, this.e);
        this.k = new byte[this.e - 1];
        for (int i5 = 0; i5 < this.e - 1; i5++) {
            byte[] subtreeRootSig = gMSSPrivateKeyParameters.getSubtreeRootSig(i5);
            byte[][] bArr4 = this.k;
            bArr4[i5] = new byte[subtreeRootSig.length];
            System.arraycopy(subtreeRootSig, 0, bArr4[i5], 0, subtreeRootSig.length);
        }
        gMSSPrivateKeyParameters.markUsed();
    }

    public final void b() {
        this.c.reset();
        GMSSPublicKeyParameters gMSSPublicKeyParameters = (GMSSPublicKeyParameters) this.n;
        this.b = gMSSPublicKeyParameters.getPublicKey();
        GMSSParameters parameters = gMSSPublicKeyParameters.getParameters();
        this.l = parameters;
        this.e = parameters.getNumOfLayers();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] bArr2 = new byte[this.d];
        byte[] signature = this.g.getSignature(bArr);
        byte[] concatenateArray = this.f15292a.concatenateArray(this.j[this.e - 1]);
        byte[] intToBytesLittleEndian = this.f15292a.intToBytesLittleEndian(this.i[this.e - 1]);
        int length = intToBytesLittleEndian.length + signature.length + concatenateArray.length;
        byte[] bArr3 = new byte[length];
        System.arraycopy(intToBytesLittleEndian, 0, bArr3, 0, intToBytesLittleEndian.length);
        System.arraycopy(signature, 0, bArr3, intToBytesLittleEndian.length, signature.length);
        System.arraycopy(concatenateArray, 0, bArr3, intToBytesLittleEndian.length + signature.length, concatenateArray.length);
        byte[] bArr4 = new byte[0];
        for (int i = (this.e - 1) - 1; i >= 0; i--) {
            byte[] concatenateArray2 = this.f15292a.concatenateArray(this.j[i]);
            byte[] intToBytesLittleEndian2 = this.f15292a.intToBytesLittleEndian(this.i[i]);
            int length2 = bArr4.length;
            byte[] bArr5 = new byte[length2];
            System.arraycopy(bArr4, 0, bArr5, 0, bArr4.length);
            bArr4 = new byte[intToBytesLittleEndian2.length + length2 + this.k[i].length + concatenateArray2.length];
            System.arraycopy(bArr5, 0, bArr4, 0, length2);
            System.arraycopy(intToBytesLittleEndian2, 0, bArr4, length2, intToBytesLittleEndian2.length);
            byte[][] bArr6 = this.k;
            System.arraycopy(bArr6[i], 0, bArr4, intToBytesLittleEndian2.length + length2, bArr6[i].length);
            System.arraycopy(concatenateArray2, 0, bArr4, length2 + intToBytesLittleEndian2.length + this.k[i].length, concatenateArray2.length);
        }
        byte[] bArr7 = new byte[bArr4.length + length];
        System.arraycopy(bArr3, 0, bArr7, 0, length);
        System.arraycopy(bArr4, 0, bArr7, length, bArr4.length);
        return bArr7;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!z) {
            this.n = (GMSSPublicKeyParameters) cipherParameters;
            b();
            return;
        }
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            parametersWithRandom.getRandom();
            this.n = (GMSSPrivateKeyParameters) parametersWithRandom.getParameters();
        } else {
            new SecureRandom();
            this.n = (GMSSPrivateKeyParameters) cipherParameters;
        }
        a();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        this.f.reset();
        int i = 0;
        for (int i2 = this.e - 1; i2 >= 0; i2--) {
            WinternitzOTSVerify winternitzOTSVerify = new WinternitzOTSVerify(this.h.get(), this.l.getWinternitzParameter()[i2]);
            int signatureLength = winternitzOTSVerify.getSignatureLength();
            int bytesToIntLittleEndian = this.f15292a.bytesToIntLittleEndian(bArr2, i);
            int i3 = i + 4;
            byte[] bArr3 = new byte[signatureLength];
            System.arraycopy(bArr2, i3, bArr3, 0, signatureLength);
            i = i3 + signatureLength;
            bArr = winternitzOTSVerify.Verify(bArr, bArr3);
            if (bArr == null) {
                System.err.println("OTS Public Key is null in GMSSSignature.verify");
                return false;
            }
            byte[][] bArr4 = (byte[][]) Array.newInstance(byte.class, this.l.getHeightOfTrees()[i2], this.d);
            for (byte[] bArr5 : bArr4) {
                System.arraycopy(bArr2, i, bArr5, 0, this.d);
                i += this.d;
            }
            byte[] bArr6 = new byte[this.d];
            int length = (1 << bArr4.length) + bytesToIntLittleEndian;
            for (int i4 = 0; i4 < bArr4.length; i4++) {
                int i5 = this.d;
                int i6 = i5 << 1;
                byte[] bArr7 = new byte[i6];
                if (length % 2 == 0) {
                    System.arraycopy(bArr, 0, bArr7, 0, i5);
                    byte[] bArr8 = bArr4[i4];
                    int i7 = this.d;
                    System.arraycopy(bArr8, 0, bArr7, i7, i7);
                    length /= 2;
                } else {
                    System.arraycopy(bArr4[i4], 0, bArr7, 0, i5);
                    System.arraycopy(bArr, 0, bArr7, this.d, bArr.length);
                    length = (length - 1) / 2;
                }
                this.c.update(bArr7, 0, i6);
                bArr = new byte[this.c.getDigestSize()];
                this.c.doFinal(bArr, 0);
            }
        }
        return Arrays.areEqual(this.b, bArr);
    }
}
