package org.bouncycastle.pqc.crypto.gmss;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSVerify;
import org.bouncycastle.pqc.crypto.gmss.util.WinternitzOTSignature;
/* loaded from: classes13.dex */
public class GMSSKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.3";

    /* renamed from: a  reason: collision with root package name */
    public GMSSRandom f15287a;
    public Digest b;
    public byte[][] c;
    public byte[][] d;
    public byte[][] e;
    public GMSSDigestProvider f;
    public int g;
    public int h;
    public boolean i = false;
    public GMSSParameters j;
    public int[] k;
    public int[] l;
    public int[] m;
    public GMSSKeyGenerationParameters n;

    public GMSSKeyPairGenerator(GMSSDigestProvider gMSSDigestProvider) {
        this.f = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.b = digest;
        this.g = digest.getDigestSize();
        this.f15287a = new GMSSRandom(this.b);
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0127 A[LOOP:3: B:36:0x0121->B:38:0x0127, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final org.bouncycastle.crypto.AsymmetricCipherKeyPair a() {
        /*
            Method dump skipped, instructions count: 471
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.gmss.GMSSKeyPairGenerator.a():org.bouncycastle.crypto.AsymmetricCipherKeyPair");
    }

    public final GMSSRootCalc b(byte[] bArr, Vector vector, byte[] bArr2, int i) {
        byte[] Verify;
        int i2 = this.g;
        byte[] bArr3 = new byte[i2];
        byte[] bArr4 = new byte[i2];
        byte[] nextSeed = this.f15287a.nextSeed(bArr2);
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.k[i], this.m[i], this.f);
        gMSSRootCalc.initialize(vector);
        if (i == this.h - 1) {
            Verify = new WinternitzOTSignature(nextSeed, this.f.get(), this.l[i]).getPublicKey();
        } else {
            this.e[i] = new WinternitzOTSignature(nextSeed, this.f.get(), this.l[i]).getSignature(bArr);
            Verify = new WinternitzOTSVerify(this.f.get(), this.l[i]).Verify(bArr, this.e[i]);
        }
        gMSSRootCalc.update(Verify);
        int i3 = 3;
        int i4 = 0;
        int i5 = 1;
        while (true) {
            int[] iArr = this.k;
            if (i5 >= (1 << iArr[i])) {
                break;
            }
            if (i5 == i3 && i4 < iArr[i] - this.m[i]) {
                gMSSRootCalc.initializeTreehashSeed(bArr2, i4);
                i3 *= 2;
                i4++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.f15287a.nextSeed(bArr2), this.f.get(), this.l[i]).getPublicKey());
            i5++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    public final GMSSRootCalc c(Vector vector, byte[] bArr, int i) {
        byte[] bArr2 = new byte[this.h];
        GMSSRootCalc gMSSRootCalc = new GMSSRootCalc(this.k[i], this.m[i], this.f);
        gMSSRootCalc.initialize(vector);
        int i2 = 0;
        int i3 = 3;
        int i4 = 0;
        while (true) {
            int[] iArr = this.k;
            if (i2 >= (1 << iArr[i])) {
                break;
            }
            if (i2 == i3 && i4 < iArr[i] - this.m[i]) {
                gMSSRootCalc.initializeTreehashSeed(bArr, i4);
                i3 *= 2;
                i4++;
            }
            gMSSRootCalc.update(new WinternitzOTSignature(this.f15287a.nextSeed(bArr), this.f.get(), this.l[i]).getPublicKey());
            i2++;
        }
        if (gMSSRootCalc.wasFinished()) {
            return gMSSRootCalc;
        }
        System.err.println("N�chster Baum noch nicht fertig konstruiert!!!");
        return null;
    }

    public final void d() {
        initialize(new GMSSKeyGenerationParameters(new SecureRandom(), new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{3, 3, 3, 3}, new int[]{2, 2, 2, 2})));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return a();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }

    public void initialize(int i, SecureRandom secureRandom) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters;
        if (i <= 10) {
            gMSSKeyGenerationParameters = new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(1, new int[]{10}, new int[]{3}, new int[]{2}));
        } else {
            gMSSKeyGenerationParameters = i <= 20 ? new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(2, new int[]{10, 10}, new int[]{5, 4}, new int[]{2, 2})) : new GMSSKeyGenerationParameters(secureRandom, new GMSSParameters(4, new int[]{10, 10, 10, 10}, new int[]{9, 9, 9, 3}, new int[]{2, 2, 2, 2}));
        }
        initialize(gMSSKeyGenerationParameters);
    }

    public void initialize(KeyGenerationParameters keyGenerationParameters) {
        GMSSKeyGenerationParameters gMSSKeyGenerationParameters = (GMSSKeyGenerationParameters) keyGenerationParameters;
        this.n = gMSSKeyGenerationParameters;
        GMSSParameters gMSSParameters = new GMSSParameters(gMSSKeyGenerationParameters.getParameters().getNumOfLayers(), this.n.getParameters().getHeightOfTrees(), this.n.getParameters().getWinternitzParameter(), this.n.getParameters().getK());
        this.j = gMSSParameters;
        this.h = gMSSParameters.getNumOfLayers();
        this.k = this.j.getHeightOfTrees();
        this.l = this.j.getWinternitzParameter();
        this.m = this.j.getK();
        this.c = (byte[][]) Array.newInstance(byte.class, this.h, this.g);
        this.d = (byte[][]) Array.newInstance(byte.class, this.h - 1, this.g);
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < this.h; i++) {
            secureRandom.nextBytes(this.c[i]);
            this.f15287a.nextSeed(this.c[i]);
        }
        this.i = true;
    }
}
