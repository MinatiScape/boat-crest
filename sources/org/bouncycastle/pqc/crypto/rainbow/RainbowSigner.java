package org.bouncycastle.pqc.crypto.rainbow;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.rainbow.util.ComputeInField;
import org.bouncycastle.pqc.crypto.rainbow.util.GF2Field;
/* loaded from: classes13.dex */
public class RainbowSigner implements MessageSigner {

    /* renamed from: a  reason: collision with root package name */
    public SecureRandom f15314a;
    public int b;
    public short[] c;
    public ComputeInField d = new ComputeInField();
    public RainbowKeyParameters e;

    public final short[] a(Layer[] layerArr, short[] sArr) {
        short[] sArr2 = new short[sArr.length];
        short[] multiplyMatrix = this.d.multiplyMatrix(((RainbowPrivateKeyParameters) this.e).getInvA1(), this.d.addVect(((RainbowPrivateKeyParameters) this.e).getB1(), sArr));
        for (int i = 0; i < layerArr[0].getVi(); i++) {
            this.c[i] = (short) this.f15314a.nextInt();
            short[] sArr3 = this.c;
            sArr3[i] = (short) (sArr3[i] & 255);
        }
        return multiplyMatrix;
    }

    public final short[] b(byte[] bArr) {
        int i = this.b;
        short[] sArr = new short[i];
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            sArr[i2] = bArr[i3];
            sArr[i2] = (short) (sArr[i2] & 255);
            i3++;
            i2++;
            if (i2 >= i) {
                break;
            }
        }
        return sArr;
    }

    public final short[] c(short[] sArr) {
        short[][] coeffQuadratic = ((RainbowPublicKeyParameters) this.e).getCoeffQuadratic();
        short[][] coeffSingular = ((RainbowPublicKeyParameters) this.e).getCoeffSingular();
        short[] coeffScalar = ((RainbowPublicKeyParameters) this.e).getCoeffScalar();
        short[] sArr2 = new short[coeffQuadratic.length];
        int length = coeffSingular[0].length;
        for (int i = 0; i < coeffQuadratic.length; i++) {
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                for (int i4 = i3; i4 < length; i4++) {
                    sArr2[i] = GF2Field.addElem(sArr2[i], GF2Field.multElem(coeffQuadratic[i][i2], GF2Field.multElem(sArr[i3], sArr[i4])));
                    i2++;
                }
                sArr2[i] = GF2Field.addElem(sArr2[i], GF2Field.multElem(coeffSingular[i][i3], sArr[i3]));
            }
            sArr2[i] = GF2Field.addElem(sArr2[i], coeffScalar[i]);
        }
        return sArr2;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        boolean z;
        Layer[] layers = ((RainbowPrivateKeyParameters) this.e).getLayers();
        int length = layers.length;
        this.c = new short[((RainbowPrivateKeyParameters) this.e).getInvA2().length];
        int viNext = layers[length - 1].getViNext();
        byte[] bArr2 = new byte[viNext];
        short[] b = b(bArr);
        int i = 0;
        do {
            try {
                short[] a2 = a(layers, b);
                int i2 = 0;
                for (int i3 = 0; i3 < length; i3++) {
                    short[] sArr = new short[layers[i3].getOi()];
                    short[] sArr2 = new short[layers[i3].getOi()];
                    for (int i4 = 0; i4 < layers[i3].getOi(); i4++) {
                        sArr[i4] = a2[i2];
                        i2++;
                    }
                    short[] solveEquation = this.d.solveEquation(layers[i3].plugInVinegars(this.c), sArr);
                    if (solveEquation == null) {
                        throw new Exception("LES is not solveable!");
                        break;
                    }
                    for (int i5 = 0; i5 < solveEquation.length; i5++) {
                        this.c[layers[i3].getVi() + i5] = solveEquation[i5];
                    }
                }
                short[] multiplyMatrix = this.d.multiplyMatrix(((RainbowPrivateKeyParameters) this.e).getInvA2(), this.d.addVect(((RainbowPrivateKeyParameters) this.e).getB2(), this.c));
                for (int i6 = 0; i6 < viNext; i6++) {
                    bArr2[i6] = (byte) multiplyMatrix[i6];
                }
                z = true;
            } catch (Exception unused) {
                z = false;
            }
            if (z) {
                break;
            }
            i++;
        } while (i < 65536);
        if (i != 65536) {
            return bArr2;
        }
        throw new IllegalStateException("unable to generate signature - LES not solvable");
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        RainbowKeyParameters rainbowKeyParameters;
        if (!z) {
            rainbowKeyParameters = (RainbowPublicKeyParameters) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f15314a = parametersWithRandom.getRandom();
            this.e = (RainbowPrivateKeyParameters) parametersWithRandom.getParameters();
            this.b = this.e.getDocLength();
        } else {
            this.f15314a = new SecureRandom();
            rainbowKeyParameters = (RainbowPrivateKeyParameters) cipherParameters;
        }
        this.e = rainbowKeyParameters;
        this.b = this.e.getDocLength();
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        short[] sArr = new short[bArr2.length];
        for (int i = 0; i < bArr2.length; i++) {
            sArr[i] = (short) (bArr2[i] & 255);
        }
        short[] b = b(bArr);
        short[] c = c(sArr);
        if (b.length != c.length) {
            return false;
        }
        boolean z = true;
        for (int i2 = 0; i2 < b.length; i2++) {
            z = z && b[i2] == c[i2];
        }
        return z;
    }
}
