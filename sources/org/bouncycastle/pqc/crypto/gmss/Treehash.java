package org.bouncycastle.pqc.crypto.gmss;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.Vector;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.Integers;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes13.dex */
public class Treehash {

    /* renamed from: a  reason: collision with root package name */
    public int f15295a;
    public Vector b;
    public Vector c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public int g;
    public int h;
    public boolean i;
    public boolean j;
    public boolean k;
    public Digest l;

    public Treehash(Vector vector, int i, Digest digest) {
        this.b = vector;
        this.f15295a = i;
        this.d = null;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = digest;
        this.f = new byte[digest.getDigestSize()];
        this.e = new byte[this.l.getDigestSize()];
    }

    public Treehash(Digest digest, byte[][] bArr, int[] iArr) {
        this.l = digest;
        this.f15295a = iArr[0];
        this.g = iArr[1];
        this.h = iArr[2];
        if (iArr[3] == 1) {
            this.j = true;
        } else {
            this.j = false;
        }
        if (iArr[4] == 1) {
            this.i = true;
        } else {
            this.i = false;
        }
        if (iArr[5] == 1) {
            this.k = true;
        } else {
            this.k = false;
        }
        this.c = new Vector();
        for (int i = 0; i < this.g; i++) {
            this.c.addElement(Integers.valueOf(iArr[i + 6]));
        }
        this.d = bArr[0];
        this.e = bArr[1];
        this.f = bArr[2];
        this.b = new Vector();
        for (int i2 = 0; i2 < this.g; i2++) {
            this.b.addElement(bArr[i2 + 3]);
        }
    }

    public void destroy() {
        this.i = false;
        this.j = false;
        this.d = null;
        this.g = 0;
        this.h = -1;
    }

    public byte[] getFirstNode() {
        return this.d;
    }

    public int getFirstNodeHeight() {
        return this.d == null ? this.f15295a : this.h;
    }

    public int getLowestNodeHeight() {
        return this.d == null ? this.f15295a : this.g == 0 ? this.h : Math.min(this.h, ((Integer) this.c.lastElement()).intValue());
    }

    public byte[] getSeedActive() {
        return this.e;
    }

    public byte[][] getStatByte() {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, this.g + 3, this.l.getDigestSize());
        bArr[0] = this.d;
        bArr[1] = this.e;
        bArr[2] = this.f;
        for (int i = 0; i < this.g; i++) {
            bArr[i + 3] = (byte[]) this.b.elementAt(i);
        }
        return bArr;
    }

    public int[] getStatInt() {
        int i = this.g;
        int[] iArr = new int[i + 6];
        iArr[0] = this.f15295a;
        iArr[1] = i;
        iArr[2] = this.h;
        if (this.j) {
            iArr[3] = 1;
        } else {
            iArr[3] = 0;
        }
        if (this.i) {
            iArr[4] = 1;
        } else {
            iArr[4] = 0;
        }
        if (this.k) {
            iArr[5] = 1;
        } else {
            iArr[5] = 0;
        }
        for (int i2 = 0; i2 < this.g; i2++) {
            iArr[i2 + 6] = ((Integer) this.c.elementAt(i2)).intValue();
        }
        return iArr;
    }

    public Vector getTailStack() {
        return this.b;
    }

    public void initialize() {
        if (this.k) {
            this.c = new Vector();
            this.g = 0;
            this.d = null;
            this.h = -1;
            this.i = true;
            System.arraycopy(this.f, 0, this.e, 0, this.l.getDigestSize());
            return;
        }
        PrintStream printStream = System.err;
        printStream.println("Seed " + this.f15295a + " not initialized");
    }

    public void initializeSeed(byte[] bArr) {
        System.arraycopy(bArr, 0, this.f, 0, this.l.getDigestSize());
        this.k = true;
    }

    public void setFirstNode(byte[] bArr) {
        if (!this.i) {
            initialize();
        }
        this.d = bArr;
        this.h = this.f15295a;
        this.j = true;
    }

    public String toString() {
        StringBuilder sb;
        String str = "Treehash    : ";
        for (int i = 0; i < this.g + 6; i++) {
            str = str + getStatInt()[i] + HexStringBuilder.DEFAULT_SEPARATOR;
        }
        for (int i2 = 0; i2 < this.g + 3; i2++) {
            if (getStatByte()[i2] != null) {
                sb = new StringBuilder();
                sb.append(str);
                sb.append(new String(Hex.encode(getStatByte()[i2])));
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            } else {
                sb = new StringBuilder();
                sb.append(str);
                sb.append("null ");
            }
            str = sb.toString();
        }
        return str + "  " + this.l.getDigestSize();
    }

    public void update(GMSSRandom gMSSRandom, byte[] bArr) {
        PrintStream printStream;
        String str;
        if (this.j) {
            printStream = System.err;
            str = "No more update possible for treehash instance!";
        } else if (this.i) {
            byte[] bArr2 = new byte[this.l.getDigestSize()];
            gMSSRandom.nextSeed(this.e);
            if (this.d == null) {
                this.d = bArr;
                this.h = 0;
            } else {
                int i = 0;
                while (this.g > 0 && i == ((Integer) this.c.lastElement()).intValue()) {
                    int digestSize = this.l.getDigestSize() << 1;
                    byte[] bArr3 = new byte[digestSize];
                    System.arraycopy(this.b.lastElement(), 0, bArr3, 0, this.l.getDigestSize());
                    Vector vector = this.b;
                    vector.removeElementAt(vector.size() - 1);
                    Vector vector2 = this.c;
                    vector2.removeElementAt(vector2.size() - 1);
                    System.arraycopy(bArr, 0, bArr3, this.l.getDigestSize(), this.l.getDigestSize());
                    this.l.update(bArr3, 0, digestSize);
                    bArr = new byte[this.l.getDigestSize()];
                    this.l.doFinal(bArr, 0);
                    i++;
                    this.g--;
                }
                this.b.addElement(bArr);
                this.c.addElement(Integers.valueOf(i));
                this.g++;
                if (((Integer) this.c.lastElement()).intValue() == this.h) {
                    int digestSize2 = this.l.getDigestSize() << 1;
                    byte[] bArr4 = new byte[digestSize2];
                    System.arraycopy(this.d, 0, bArr4, 0, this.l.getDigestSize());
                    System.arraycopy(this.b.lastElement(), 0, bArr4, this.l.getDigestSize(), this.l.getDigestSize());
                    Vector vector3 = this.b;
                    vector3.removeElementAt(vector3.size() - 1);
                    Vector vector4 = this.c;
                    vector4.removeElementAt(vector4.size() - 1);
                    this.l.update(bArr4, 0, digestSize2);
                    byte[] bArr5 = new byte[this.l.getDigestSize()];
                    this.d = bArr5;
                    this.l.doFinal(bArr5, 0);
                    this.h++;
                    this.g = 0;
                }
            }
            if (this.h == this.f15295a) {
                this.j = true;
                return;
            }
            return;
        } else {
            printStream = System.err;
            str = "Treehash instance not initialized before update";
        }
        printStream.println(str);
    }

    public void updateNextSeed(GMSSRandom gMSSRandom) {
        gMSSRandom.nextSeed(this.f);
    }

    public boolean wasFinished() {
        return this.j;
    }

    public boolean wasInitialized() {
        return this.i;
    }
}
