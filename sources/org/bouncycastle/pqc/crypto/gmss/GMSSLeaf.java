package org.bouncycastle.pqc.crypto.gmss;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes13.dex */
public class GMSSLeaf {

    /* renamed from: a  reason: collision with root package name */
    public Digest f15288a;
    public int b;
    public int c;
    public GMSSRandom d;
    public byte[] e;
    public byte[] f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public byte[] l;
    public byte[] m;

    public GMSSLeaf(Digest digest, int i, int i2) {
        int digestSize;
        int ceil;
        int ceil2;
        int i3;
        this.j = i;
        this.f15288a = digest;
        this.d = new GMSSRandom(digest);
        this.b = this.f15288a.getDigestSize();
        double d = i;
        this.c = ((int) Math.ceil((digestSize << 3) / d)) + ((int) Math.ceil(a((ceil << i) + 1) / d));
        this.i = 1 << i;
        this.k = (int) Math.ceil(((((i3 - 1) * ceil2) + 1) + ceil2) / i2);
        int i4 = this.b;
        this.l = new byte[i4];
        this.e = new byte[i4];
        this.m = new byte[i4];
        this.f = new byte[i4 * this.c];
    }

    public GMSSLeaf(Digest digest, int i, int i2, byte[] bArr) {
        int digestSize;
        int ceil;
        int ceil2;
        int i3;
        this.j = i;
        this.f15288a = digest;
        this.d = new GMSSRandom(digest);
        this.b = this.f15288a.getDigestSize();
        double d = i;
        this.c = ((int) Math.ceil((digestSize << 3) / d)) + ((int) Math.ceil(a((ceil << i) + 1) / d));
        this.i = 1 << i;
        this.k = (int) Math.ceil(((((i3 - 1) * ceil2) + 1) + ceil2) / i2);
        int i4 = this.b;
        this.l = new byte[i4];
        this.e = new byte[i4];
        this.m = new byte[i4];
        this.f = new byte[i4 * this.c];
        b(bArr);
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] iArr) {
        this.g = iArr[0];
        this.h = iArr[1];
        this.k = iArr[2];
        this.j = iArr[3];
        this.f15288a = digest;
        this.d = new GMSSRandom(digest);
        int digestSize = this.f15288a.getDigestSize();
        this.b = digestSize;
        int ceil = (int) Math.ceil((digestSize << 3) / this.j);
        this.c = ceil + ((int) Math.ceil(a((ceil << this.j) + 1) / this.j));
        this.i = 1 << this.j;
        this.m = bArr[0];
        this.l = bArr[1];
        this.f = bArr[2];
        this.e = bArr[3];
    }

    public GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.f15288a = gMSSLeaf.f15288a;
        this.b = gMSSLeaf.b;
        this.c = gMSSLeaf.c;
        this.d = gMSSLeaf.d;
        this.e = Arrays.clone(gMSSLeaf.e);
        this.f = Arrays.clone(gMSSLeaf.f);
        this.g = gMSSLeaf.g;
        this.h = gMSSLeaf.h;
        this.i = gMSSLeaf.i;
        this.j = gMSSLeaf.j;
        this.k = gMSSLeaf.k;
        this.l = Arrays.clone(gMSSLeaf.l);
        this.m = Arrays.clone(gMSSLeaf.m);
    }

    public final int a(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public void b(byte[] bArr) {
        this.g = 0;
        this.h = 0;
        byte[] bArr2 = new byte[this.b];
        System.arraycopy(bArr, 0, bArr2, 0, this.l.length);
        this.l = this.d.nextSeed(bArr2);
    }

    public GMSSLeaf c() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.d();
        return gMSSLeaf;
    }

    public final void d() {
        byte[] bArr = new byte[this.f15288a.getDigestSize()];
        for (int i = 0; i < this.k + 10000; i++) {
            int i2 = this.g;
            if (i2 == this.c && this.h == this.i - 1) {
                Digest digest = this.f15288a;
                byte[] bArr2 = this.f;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.f15288a.getDigestSize()];
                this.e = bArr3;
                this.f15288a.doFinal(bArr3, 0);
                return;
            }
            if (i2 == 0 || this.h == this.i - 1) {
                this.g = i2 + 1;
                this.h = 0;
                this.m = this.d.nextSeed(this.l);
            } else {
                Digest digest2 = this.f15288a;
                byte[] bArr4 = this.m;
                digest2.update(bArr4, 0, bArr4.length);
                this.m = bArr;
                this.f15288a.doFinal(bArr, 0);
                int i3 = this.h + 1;
                this.h = i3;
                if (i3 == this.i - 1) {
                    byte[] bArr5 = this.m;
                    byte[] bArr6 = this.f;
                    int i4 = this.b;
                    System.arraycopy(bArr5, 0, bArr6, (this.g - 1) * i4, i4);
                }
            }
        }
        throw new IllegalStateException("unable to updateLeaf in steps: " + this.k + HexStringBuilder.DEFAULT_SEPARATOR + this.g + HexStringBuilder.DEFAULT_SEPARATOR + this.h);
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.e);
    }

    public byte[][] getStatByte() {
        int i = this.b;
        byte[][] bArr = {new byte[i], new byte[i], new byte[this.c * i], new byte[i]};
        bArr[0] = this.m;
        bArr[1] = this.l;
        bArr[2] = this.f;
        bArr[3] = this.e;
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.g, this.h, this.k, this.j};
    }

    public String toString() {
        StringBuilder sb;
        String str = "";
        for (int i = 0; i < 4; i++) {
            str = str + getStatInt()[i] + HexStringBuilder.DEFAULT_SEPARATOR;
        }
        String str2 = str + HexStringBuilder.DEFAULT_SEPARATOR + this.b + HexStringBuilder.DEFAULT_SEPARATOR + this.c + HexStringBuilder.DEFAULT_SEPARATOR + this.i + HexStringBuilder.DEFAULT_SEPARATOR;
        byte[][] statByte = getStatByte();
        for (int i2 = 0; i2 < 4; i2++) {
            if (statByte[i2] != null) {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append(new String(Hex.encode(statByte[i2])));
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            } else {
                sb = new StringBuilder();
                sb.append(str2);
                sb.append("null ");
            }
            str2 = sb.toString();
        }
        return str2;
    }
}
