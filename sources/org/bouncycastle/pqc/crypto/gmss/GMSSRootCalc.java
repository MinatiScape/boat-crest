package org.bouncycastle.pqc.crypto.gmss;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.Array;
import java.util.Enumeration;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes13.dex */
public class GMSSRootCalc {

    /* renamed from: a  reason: collision with root package name */
    public int f15290a;
    public int b;
    public Treehash[] c;
    public Vector[] d;
    public byte[] e;
    public byte[][] f;
    public int g;
    public Vector h;
    public Vector i;
    public Digest j;
    public GMSSDigestProvider k;
    public int[] l;
    public boolean m;
    public boolean n;
    public int o;
    public int p;

    public GMSSRootCalc(int i, int i2, GMSSDigestProvider gMSSDigestProvider) {
        this.f15290a = i;
        this.k = gMSSDigestProvider;
        Digest digest = gMSSDigestProvider.get();
        this.j = digest;
        int digestSize = digest.getDigestSize();
        this.b = digestSize;
        this.g = i2;
        this.l = new int[i];
        int[] iArr = {i, digestSize};
        this.f = (byte[][]) Array.newInstance(byte.class, iArr);
        this.e = new byte[this.b];
        this.d = new Vector[this.g - 1];
        for (int i3 = 0; i3 < i2 - 1; i3++) {
            this.d[i3] = new Vector();
        }
    }

    public GMSSRootCalc(Digest digest, byte[][] bArr, int[] iArr, Treehash[] treehashArr, Vector[] vectorArr) {
        this.j = this.k.get();
        this.k = this.k;
        int i = iArr[0];
        this.f15290a = i;
        this.b = iArr[1];
        this.g = iArr[2];
        this.o = iArr[3];
        this.p = iArr[4];
        if (iArr[5] == 1) {
            this.n = true;
        } else {
            this.n = false;
        }
        if (iArr[6] == 1) {
            this.m = true;
        } else {
            this.m = false;
        }
        int i2 = iArr[7];
        this.l = new int[i];
        for (int i3 = 0; i3 < this.f15290a; i3++) {
            this.l[i3] = iArr[i3 + 8];
        }
        this.i = new Vector();
        for (int i4 = 0; i4 < i2; i4++) {
            this.i.addElement(Integers.valueOf(iArr[this.f15290a + 8 + i4]));
        }
        this.e = bArr[0];
        this.f = (byte[][]) Array.newInstance(byte.class, this.f15290a, this.b);
        int i5 = 0;
        while (i5 < this.f15290a) {
            int i6 = i5 + 1;
            this.f[i5] = bArr[i6];
            i5 = i6;
        }
        this.h = new Vector();
        for (int i7 = 0; i7 < i2; i7++) {
            this.h.addElement(bArr[this.f15290a + 1 + i7]);
        }
        this.c = a.b(treehashArr);
        this.d = a.a(vectorArr);
    }

    public byte[][] getAuthPath() {
        return a.c(this.f);
    }

    public Vector[] getRetain() {
        return a.a(this.d);
    }

    public byte[] getRoot() {
        return Arrays.clone(this.e);
    }

    public Vector getStack() {
        Vector vector = new Vector();
        Enumeration elements = this.h.elements();
        while (elements.hasMoreElements()) {
            vector.addElement(elements.nextElement());
        }
        return vector;
    }

    public byte[][] getStatByte() {
        Vector vector = this.h;
        int size = vector == null ? 0 : vector.size();
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, this.f15290a + 1 + size, 64);
        bArr[0] = this.e;
        int i = 0;
        while (i < this.f15290a) {
            int i2 = i + 1;
            bArr[i2] = this.f[i];
            i = i2;
        }
        for (int i3 = 0; i3 < size; i3++) {
            bArr[this.f15290a + 1 + i3] = (byte[]) this.h.elementAt(i3);
        }
        return bArr;
    }

    public int[] getStatInt() {
        Vector vector = this.h;
        int size = vector == null ? 0 : vector.size();
        int i = this.f15290a;
        int[] iArr = new int[i + 8 + size];
        iArr[0] = i;
        iArr[1] = this.b;
        iArr[2] = this.g;
        iArr[3] = this.o;
        iArr[4] = this.p;
        if (this.n) {
            iArr[5] = 1;
        } else {
            iArr[5] = 0;
        }
        if (this.m) {
            iArr[6] = 1;
        } else {
            iArr[6] = 0;
        }
        iArr[7] = size;
        for (int i2 = 0; i2 < this.f15290a; i2++) {
            iArr[i2 + 8] = this.l[i2];
        }
        for (int i3 = 0; i3 < size; i3++) {
            iArr[this.f15290a + 8 + i3] = ((Integer) this.i.elementAt(i3)).intValue();
        }
        return iArr;
    }

    public Treehash[] getTreehash() {
        return a.b(this.c);
    }

    public void initialize(Vector vector) {
        int i;
        this.c = new Treehash[this.f15290a - this.g];
        int i2 = 0;
        while (true) {
            i = this.f15290a;
            if (i2 >= i - this.g) {
                break;
            }
            this.c[i2] = new Treehash(vector, i2, this.k.get());
            i2++;
        }
        this.l = new int[i];
        this.f = (byte[][]) Array.newInstance(byte.class, i, this.b);
        this.e = new byte[this.b];
        this.h = new Vector();
        this.i = new Vector();
        this.m = true;
        this.n = false;
        for (int i3 = 0; i3 < this.f15290a; i3++) {
            this.l[i3] = -1;
        }
        this.d = new Vector[this.g - 1];
        for (int i4 = 0; i4 < this.g - 1; i4++) {
            this.d[i4] = new Vector();
        }
        this.o = 3;
        this.p = 0;
    }

    public void initializeTreehashSeed(byte[] bArr, int i) {
        this.c[i].initializeSeed(bArr);
    }

    public String toString() {
        Vector vector = this.h;
        int size = vector == null ? 0 : vector.size();
        String str = "";
        for (int i = 0; i < this.f15290a + 8 + size; i++) {
            str = str + getStatInt()[i] + HexStringBuilder.DEFAULT_SEPARATOR;
        }
        for (int i2 = 0; i2 < this.f15290a + 1 + size; i2++) {
            str = str + new String(Hex.encode(getStatByte()[i2])) + HexStringBuilder.DEFAULT_SEPARATOR;
        }
        return str + "  " + this.k.get().getDigestSize();
    }

    public void update(byte[] bArr) {
        if (this.n) {
            System.out.print("Too much updates for Tree!!");
        } else if (!this.m) {
            System.err.println("GMSSRootCalc not initialized!");
        } else {
            int[] iArr = this.l;
            iArr[0] = iArr[0] + 1;
            if (iArr[0] == 1) {
                System.arraycopy(bArr, 0, this.f[0], 0, this.b);
            } else if (iArr[0] == 3 && this.f15290a > this.g) {
                this.c[0].setFirstNode(bArr);
            }
            int[] iArr2 = this.l;
            if ((iArr2[0] - 3) % 2 == 0 && iArr2[0] >= 3 && this.f15290a == this.g) {
                this.d[0].insertElementAt(bArr, 0);
            }
            if (this.l[0] == 0) {
                this.h.addElement(bArr);
                this.i.addElement(Integers.valueOf(0));
                return;
            }
            int i = this.b;
            byte[] bArr2 = new byte[i];
            int i2 = i << 1;
            byte[] bArr3 = new byte[i2];
            System.arraycopy(bArr, 0, bArr2, 0, i);
            int i3 = 0;
            while (this.h.size() > 0 && i3 == ((Integer) this.i.lastElement()).intValue()) {
                System.arraycopy(this.h.lastElement(), 0, bArr3, 0, this.b);
                Vector vector = this.h;
                vector.removeElementAt(vector.size() - 1);
                Vector vector2 = this.i;
                vector2.removeElementAt(vector2.size() - 1);
                int i4 = this.b;
                System.arraycopy(bArr2, 0, bArr3, i4, i4);
                this.j.update(bArr3, 0, i2);
                bArr2 = new byte[this.j.getDigestSize()];
                this.j.doFinal(bArr2, 0);
                i3++;
                if (i3 < this.f15290a) {
                    int[] iArr3 = this.l;
                    iArr3[i3] = iArr3[i3] + 1;
                    if (iArr3[i3] == 1) {
                        System.arraycopy(bArr2, 0, this.f[i3], 0, this.b);
                    }
                    if (i3 >= this.f15290a - this.g) {
                        if (i3 == 0) {
                            System.out.println("M���P");
                        }
                        int[] iArr4 = this.l;
                        if ((iArr4[i3] - 3) % 2 == 0 && iArr4[i3] >= 3) {
                            this.d[i3 - (this.f15290a - this.g)].insertElementAt(bArr2, 0);
                        }
                    } else if (this.l[i3] == 3) {
                        this.c[i3].setFirstNode(bArr2);
                    }
                }
            }
            this.h.addElement(bArr2);
            this.i.addElement(Integers.valueOf(i3));
            if (i3 == this.f15290a) {
                this.n = true;
                this.m = false;
                this.e = (byte[]) this.h.lastElement();
            }
        }
    }

    public void update(byte[] bArr, byte[] bArr2) {
        int i = this.p;
        if (i < this.f15290a - this.g && this.o - 2 == this.l[0]) {
            initializeTreehashSeed(bArr, i);
            this.p++;
            this.o *= 2;
        }
        update(bArr2);
    }

    public boolean wasFinished() {
        return this.n;
    }

    public boolean wasInitialized() {
        return this.m;
    }
}
