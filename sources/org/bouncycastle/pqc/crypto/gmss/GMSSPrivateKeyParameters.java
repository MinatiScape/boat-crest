package org.bouncycastle.pqc.crypto.gmss;

import com.clevertap.android.sdk.Constants;
import java.lang.reflect.Array;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class GMSSPrivateKeyParameters extends GMSSKeyParameters {
    public byte[][] A;
    public GMSSRootCalc[] B;
    public byte[][] C;
    public GMSSRootSig[] D;
    public GMSSDigestProvider E;
    public boolean F;
    public int[] G;
    public int[] H;
    public int[] I;
    public int J;
    public Digest K;
    public int L;
    public GMSSRandom M;
    public int[] N;
    public int[] j;
    public byte[][] k;
    public byte[][] l;
    public byte[][][] m;
    public byte[][][] n;
    public Treehash[][] o;
    public Treehash[][] p;
    public Vector[] q;
    public Vector[] r;
    public Vector[][] s;
    public Vector[][] t;
    public byte[][][] u;
    public GMSSLeaf[] v;
    public GMSSLeaf[] w;
    public GMSSLeaf[] x;
    public int[] y;
    public GMSSParameters z;

    public GMSSPrivateKeyParameters(GMSSPrivateKeyParameters gMSSPrivateKeyParameters) {
        super(true, gMSSPrivateKeyParameters.getParameters());
        this.F = false;
        this.j = Arrays.clone(gMSSPrivateKeyParameters.j);
        this.k = Arrays.clone(gMSSPrivateKeyParameters.k);
        this.l = Arrays.clone(gMSSPrivateKeyParameters.l);
        this.m = Arrays.clone(gMSSPrivateKeyParameters.m);
        this.n = Arrays.clone(gMSSPrivateKeyParameters.n);
        this.o = gMSSPrivateKeyParameters.o;
        this.p = gMSSPrivateKeyParameters.p;
        this.q = gMSSPrivateKeyParameters.q;
        this.r = gMSSPrivateKeyParameters.r;
        this.s = gMSSPrivateKeyParameters.s;
        this.t = gMSSPrivateKeyParameters.t;
        this.u = Arrays.clone(gMSSPrivateKeyParameters.u);
        this.v = gMSSPrivateKeyParameters.v;
        this.w = gMSSPrivateKeyParameters.w;
        this.x = gMSSPrivateKeyParameters.x;
        this.y = gMSSPrivateKeyParameters.y;
        this.z = gMSSPrivateKeyParameters.z;
        this.A = Arrays.clone(gMSSPrivateKeyParameters.A);
        this.B = gMSSPrivateKeyParameters.B;
        this.C = gMSSPrivateKeyParameters.C;
        this.D = gMSSPrivateKeyParameters.D;
        this.E = gMSSPrivateKeyParameters.E;
        this.G = gMSSPrivateKeyParameters.G;
        this.H = gMSSPrivateKeyParameters.H;
        this.I = gMSSPrivateKeyParameters.I;
        this.J = gMSSPrivateKeyParameters.J;
        this.K = gMSSPrivateKeyParameters.K;
        this.L = gMSSPrivateKeyParameters.L;
        this.M = gMSSPrivateKeyParameters.M;
        this.N = gMSSPrivateKeyParameters.N;
    }

    public GMSSPrivateKeyParameters(int[] iArr, byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, byte[][][] bArr5, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, GMSSLeaf[] gMSSLeafArr, GMSSLeaf[] gMSSLeafArr2, GMSSLeaf[] gMSSLeafArr3, int[] iArr2, byte[][] bArr6, GMSSRootCalc[] gMSSRootCalcArr, byte[][] bArr7, GMSSRootSig[] gMSSRootSigArr, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        super(true, gMSSParameters);
        this.F = false;
        Digest digest = gMSSDigestProvider.get();
        this.K = digest;
        this.L = digest.getDigestSize();
        this.z = gMSSParameters;
        this.H = gMSSParameters.getWinternitzParameter();
        this.I = gMSSParameters.getK();
        this.G = gMSSParameters.getHeightOfTrees();
        int numOfLayers = this.z.getNumOfLayers();
        this.J = numOfLayers;
        if (iArr == null) {
            this.j = new int[numOfLayers];
            for (int i = 0; i < this.J; i++) {
                this.j[i] = 0;
            }
        } else {
            this.j = iArr;
        }
        this.k = bArr;
        this.l = bArr2;
        this.m = bArr3;
        this.n = bArr4;
        int i2 = 2;
        if (bArr5 == null) {
            this.u = new byte[this.J][];
            int i3 = 0;
            while (i3 < this.J) {
                this.u[i3] = (byte[][]) Array.newInstance(byte.class, (int) Math.floor(this.G[i3] / i2), this.L);
                i3++;
                i2 = 2;
            }
        } else {
            this.u = bArr5;
        }
        if (vectorArr == null) {
            this.q = new Vector[this.J];
            for (int i4 = 0; i4 < this.J; i4++) {
                this.q[i4] = new Vector();
            }
        } else {
            this.q = vectorArr;
        }
        if (vectorArr2 == null) {
            this.r = new Vector[this.J - 1];
            int i5 = 0;
            for (int i6 = 1; i5 < this.J - i6; i6 = 1) {
                this.r[i5] = new Vector();
                i5++;
            }
        } else {
            this.r = vectorArr2;
        }
        this.o = treehashArr;
        this.p = treehashArr2;
        this.s = vectorArr3;
        this.t = vectorArr4;
        this.A = bArr6;
        this.E = gMSSDigestProvider;
        if (gMSSRootCalcArr == null) {
            this.B = new GMSSRootCalc[this.J - 1];
            int i7 = 0;
            for (int i8 = 1; i7 < this.J - i8; i8 = 1) {
                int i9 = i7 + 1;
                this.B[i7] = new GMSSRootCalc(this.G[i9], this.I[i9], this.E);
                i7 = i9;
            }
        } else {
            this.B = gMSSRootCalcArr;
        }
        this.C = bArr7;
        this.N = new int[this.J];
        for (int i10 = 0; i10 < this.J; i10++) {
            this.N[i10] = 1 << this.G[i10];
        }
        this.M = new GMSSRandom(this.K);
        int i11 = this.J;
        if (i11 <= 1) {
            this.v = new GMSSLeaf[0];
        } else if (gMSSLeafArr == null) {
            this.v = new GMSSLeaf[i11 - 2];
            int i12 = 0;
            while (i12 < this.J - 2) {
                int i13 = i12 + 1;
                this.v[i12] = new GMSSLeaf(gMSSDigestProvider.get(), this.H[i13], this.N[i12 + 2], this.l[i12]);
                i12 = i13;
            }
        } else {
            this.v = gMSSLeafArr;
        }
        if (gMSSLeafArr2 == null) {
            this.w = new GMSSLeaf[this.J - 1];
            int i14 = 0;
            for (int i15 = 1; i14 < this.J - i15; i15 = 1) {
                int i16 = i14 + 1;
                this.w[i14] = new GMSSLeaf(gMSSDigestProvider.get(), this.H[i14], this.N[i16], this.k[i14]);
                i14 = i16;
            }
        } else {
            this.w = gMSSLeafArr2;
        }
        if (gMSSLeafArr3 == null) {
            this.x = new GMSSLeaf[this.J - 1];
            int i17 = 0;
            for (int i18 = 1; i17 < this.J - i18; i18 = 1) {
                int i19 = i17 + 1;
                this.x[i17] = new GMSSLeaf(gMSSDigestProvider.get(), this.H[i17], this.N[i19]);
                i17 = i19;
            }
        } else {
            this.x = gMSSLeafArr3;
        }
        if (iArr2 == null) {
            this.y = new int[this.J - 1];
            int i20 = 0;
            for (int i21 = 1; i20 < this.J - i21; i21 = 1) {
                this.y[i20] = -1;
                i20++;
            }
        } else {
            this.y = iArr2;
        }
        int i22 = this.L;
        byte[] bArr8 = new byte[i22];
        byte[] bArr9 = new byte[i22];
        if (gMSSRootSigArr != null) {
            this.D = gMSSRootSigArr;
            return;
        }
        this.D = new GMSSRootSig[this.J - 1];
        int i23 = 0;
        while (i23 < this.J - 1) {
            System.arraycopy(bArr[i23], 0, bArr8, 0, this.L);
            this.M.nextSeed(bArr8);
            byte[] nextSeed = this.M.nextSeed(bArr8);
            int i24 = i23 + 1;
            this.D[i23] = new GMSSRootSig(gMSSDigestProvider.get(), this.H[i23], this.G[i24]);
            this.D[i23].initSign(nextSeed, bArr6[i23]);
            i23 = i24;
        }
    }

    public GMSSPrivateKeyParameters(byte[][] bArr, byte[][] bArr2, byte[][][] bArr3, byte[][][] bArr4, Treehash[][] treehashArr, Treehash[][] treehashArr2, Vector[] vectorArr, Vector[] vectorArr2, Vector[][] vectorArr3, Vector[][] vectorArr4, byte[][] bArr5, byte[][] bArr6, GMSSParameters gMSSParameters, GMSSDigestProvider gMSSDigestProvider) {
        this(null, bArr, bArr2, bArr3, bArr4, null, treehashArr, treehashArr2, vectorArr, vectorArr2, vectorArr3, vectorArr4, null, null, null, null, bArr5, null, bArr6, null, gMSSParameters, gMSSDigestProvider);
    }

    public final void a(int i) {
        int i2;
        int i3;
        byte[] bArr;
        int i4 = this.j[i];
        int i5 = this.G[i];
        int i6 = this.I[i];
        int i7 = 0;
        while (true) {
            i2 = i5 - i6;
            if (i7 >= i2) {
                break;
            }
            this.o[i][i7].updateNextSeed(this.M);
            i7++;
        }
        int c = c(i4);
        byte[] bArr2 = new byte[this.L];
        byte[] nextSeed = this.M.nextSeed(this.k[i]);
        int i8 = (i4 >>> (c + 1)) & 1;
        int i9 = this.L;
        byte[] bArr3 = new byte[i9];
        int i10 = i5 - 1;
        if (c < i10 && i8 == 0) {
            System.arraycopy(this.m[i][c], 0, bArr3, 0, i9);
        }
        int i11 = this.L;
        byte[] bArr4 = new byte[i11];
        if (c == 0) {
            if (i == this.J - 1) {
                bArr = new WinternitzOTSignature(nextSeed, this.E.get(), this.H[i]).getPublicKey();
            } else {
                byte[] bArr5 = new byte[i11];
                System.arraycopy(this.k[i], 0, bArr5, 0, i11);
                this.M.nextSeed(bArr5);
                byte[] leaf = this.w[i].getLeaf();
                this.w[i].b(bArr5);
                bArr = leaf;
            }
            System.arraycopy(bArr, 0, this.m[i][0], 0, this.L);
        } else {
            int i12 = i11 << 1;
            byte[] bArr6 = new byte[i12];
            System.arraycopy(this.m[i][c - 1], 0, bArr6, 0, i11);
            byte[] bArr7 = this.u[i][(int) Math.floor(i3 / 2)];
            int i13 = this.L;
            System.arraycopy(bArr7, 0, bArr6, i13, i13);
            this.K.update(bArr6, 0, i12);
            this.m[i][c] = new byte[this.K.getDigestSize()];
            this.K.doFinal(this.m[i][c], 0);
            for (int i14 = 0; i14 < c; i14++) {
                if (i14 < i2) {
                    if (this.o[i][i14].wasFinished()) {
                        System.arraycopy(this.o[i][i14].getFirstNode(), 0, this.m[i][i14], 0, this.L);
                        this.o[i][i14].destroy();
                    } else {
                        System.err.println("Treehash (" + i + Constants.SEPARATOR_COMMA + i14 + ") not finished when needed in AuthPathComputation");
                    }
                }
                if (i14 < i10 && i14 >= i2) {
                    int i15 = i14 - i2;
                    if (this.s[i][i15].size() > 0) {
                        System.arraycopy(this.s[i][i15].lastElement(), 0, this.m[i][i14], 0, this.L);
                        Vector[][] vectorArr = this.s;
                        vectorArr[i][i15].removeElementAt(vectorArr[i][i15].size() - 1);
                    }
                }
                if (i14 < i2 && ((1 << i14) * 3) + i4 < this.N[i]) {
                    this.o[i][i14].initialize();
                }
            }
        }
        if (c < i10 && i8 == 0) {
            System.arraycopy(bArr3, 0, this.u[i][(int) Math.floor(c / 2)], 0, this.L);
        }
        if (i != this.J - 1) {
            this.y[i] = b(i);
            return;
        }
        for (int i16 = 1; i16 <= i2 / 2; i16++) {
            int b = b(i);
            if (b >= 0) {
                try {
                    byte[] bArr8 = new byte[this.L];
                    System.arraycopy(this.o[i][b].getSeedActive(), 0, bArr8, 0, this.L);
                    this.o[i][b].update(this.M, new WinternitzOTSignature(this.M.nextSeed(bArr8), this.E.get(), this.H[i]).getPublicKey());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public final int b(int i) {
        int i2 = -1;
        for (int i3 = 0; i3 < this.G[i] - this.I[i]; i3++) {
            if (this.o[i][i3].wasInitialized() && !this.o[i][i3].wasFinished() && (i2 == -1 || this.o[i][i3].getLowestNodeHeight() < this.o[i][i2].getLowestNodeHeight())) {
                i2 = i3;
            }
        }
        return i2;
    }

    public final int c(int i) {
        if (i == 0) {
            return -1;
        }
        int i2 = 0;
        int i3 = 1;
        while (i % i3 == 0) {
            i3 *= 2;
            i2++;
        }
        return i2 - 1;
    }

    public final void d(int i) {
        int i2 = this.J;
        if (i == i2 - 1) {
            int[] iArr = this.j;
            iArr[i] = iArr[i] + 1;
        }
        if (this.j[i] != this.N[i]) {
            f(i);
        } else if (i2 != 1) {
            e(i);
            this.j[i] = 0;
        }
    }

    public final void e(int i) {
        if (i > 0) {
            int[] iArr = this.j;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
            int i3 = i;
            boolean z = true;
            do {
                i3--;
                if (this.j[i3] < this.N[i3]) {
                    z = false;
                }
                if (!z) {
                    break;
                }
            } while (i3 > 0);
            if (z) {
                return;
            }
            this.M.nextSeed(this.k[i]);
            this.D[i2].updateSign();
            if (i > 1) {
                GMSSLeaf[] gMSSLeafArr = this.v;
                int i4 = i2 - 1;
                gMSSLeafArr[i4] = gMSSLeafArr[i4].c();
            }
            GMSSLeaf[] gMSSLeafArr2 = this.w;
            gMSSLeafArr2[i2] = gMSSLeafArr2[i2].c();
            if (this.y[i2] >= 0) {
                GMSSLeaf[] gMSSLeafArr3 = this.x;
                gMSSLeafArr3[i2] = gMSSLeafArr3[i2].c();
                try {
                    this.o[i2][this.y[i2]].update(this.M, this.x[i2].getLeaf());
                    this.o[i2][this.y[i2]].wasFinished();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            g(i);
            this.C[i2] = this.D[i2].getSig();
            for (int i5 = 0; i5 < this.G[i] - this.I[i]; i5++) {
                Treehash[] treehashArr = this.o[i];
                Treehash[][] treehashArr2 = this.p;
                treehashArr[i5] = treehashArr2[i2][i5];
                treehashArr2[i2][i5] = this.B[i2].getTreehash()[i5];
            }
            for (int i6 = 0; i6 < this.G[i]; i6++) {
                System.arraycopy(this.n[i2][i6], 0, this.m[i][i6], 0, this.L);
                System.arraycopy(this.B[i2].getAuthPath()[i6], 0, this.n[i2][i6], 0, this.L);
            }
            for (int i7 = 0; i7 < this.I[i] - 1; i7++) {
                Vector[] vectorArr = this.s[i];
                Vector[][] vectorArr2 = this.t;
                vectorArr[i7] = vectorArr2[i2][i7];
                vectorArr2[i2][i7] = this.B[i2].getRetain()[i7];
            }
            Vector[] vectorArr3 = this.q;
            Vector[] vectorArr4 = this.r;
            vectorArr3[i] = vectorArr4[i2];
            vectorArr4[i2] = this.B[i2].getStack();
            this.A[i2] = this.B[i2].getRoot();
            int i8 = this.L;
            byte[] bArr = new byte[i8];
            byte[] bArr2 = new byte[i8];
            System.arraycopy(this.k[i2], 0, bArr2, 0, i8);
            this.M.nextSeed(bArr2);
            this.M.nextSeed(bArr2);
            this.D[i2].initSign(this.M.nextSeed(bArr2), this.A[i2]);
            d(i2);
        }
    }

    public final void f(int i) {
        a(i);
        if (i > 0) {
            if (i > 1) {
                GMSSLeaf[] gMSSLeafArr = this.v;
                int i2 = (i - 1) - 1;
                gMSSLeafArr[i2] = gMSSLeafArr[i2].c();
            }
            GMSSLeaf[] gMSSLeafArr2 = this.w;
            int i3 = i - 1;
            gMSSLeafArr2[i3] = gMSSLeafArr2[i3].c();
            int floor = (int) Math.floor((getNumLeafs(i) * 2) / (this.G[i3] - this.I[i3]));
            int[] iArr = this.j;
            if (iArr[i] % floor == 1) {
                if (iArr[i] > 1 && this.y[i3] >= 0) {
                    try {
                        this.o[i3][this.y[i3]].update(this.M, this.x[i3].getLeaf());
                        this.o[i3][this.y[i3]].wasFinished();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                this.y[i3] = b(i3);
                int[] iArr2 = this.y;
                if (iArr2[i3] >= 0) {
                    this.x[i3] = new GMSSLeaf(this.E.get(), this.H[i3], floor, this.o[i3][iArr2[i3]].getSeedActive());
                    GMSSLeaf[] gMSSLeafArr3 = this.x;
                    gMSSLeafArr3[i3] = gMSSLeafArr3[i3].c();
                }
            } else if (this.y[i3] >= 0) {
                GMSSLeaf[] gMSSLeafArr4 = this.x;
                gMSSLeafArr4[i3] = gMSSLeafArr4[i3].c();
            }
            this.D[i3].updateSign();
            if (this.j[i] == 1) {
                this.B[i3].initialize(new Vector());
            }
            g(i);
        }
    }

    public final void g(int i) {
        byte[] bArr = new byte[this.L];
        int i2 = i - 1;
        byte[] nextSeed = this.M.nextSeed(this.l[i2]);
        if (i == this.J - 1) {
            this.B[i2].update(this.l[i2], new WinternitzOTSignature(nextSeed, this.E.get(), this.H[i]).getPublicKey());
            return;
        }
        this.B[i2].update(this.l[i2], this.v[i2].getLeaf());
        this.v[i2].b(this.l[i2]);
    }

    public byte[][][] getCurrentAuthPaths() {
        return Arrays.clone(this.m);
    }

    public byte[][] getCurrentSeeds() {
        return Arrays.clone(this.k);
    }

    public int getIndex(int i) {
        return this.j[i];
    }

    public int[] getIndex() {
        return this.j;
    }

    public GMSSDigestProvider getName() {
        return this.E;
    }

    public int getNumLeafs(int i) {
        return this.N[i];
    }

    public byte[] getSubtreeRootSig(int i) {
        return this.C[i];
    }

    public boolean isUsed() {
        return this.F;
    }

    public void markUsed() {
        this.F = true;
    }

    public GMSSPrivateKeyParameters nextKey() {
        GMSSPrivateKeyParameters gMSSPrivateKeyParameters = new GMSSPrivateKeyParameters(this);
        gMSSPrivateKeyParameters.d(this.z.getNumOfLayers() - 1);
        return gMSSPrivateKeyParameters;
    }
}
