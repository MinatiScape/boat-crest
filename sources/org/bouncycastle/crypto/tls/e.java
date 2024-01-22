package org.bouncycastle.crypto.tls;

import java.util.Vector;
/* loaded from: classes13.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    public short f14864a;
    public byte[] b;
    public Vector c;

    /* loaded from: classes13.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f14865a;
        public int b;

        public a(int i, int i2) {
            this.f14865a = i;
            this.b = i2;
        }

        public int a() {
            return this.b;
        }

        public int b() {
            return this.f14865a;
        }

        public void c(int i) {
            this.b = i;
        }

        public void d(int i) {
            this.f14865a = i;
        }
    }

    public e(short s, int i) {
        Vector vector = new Vector();
        this.c = vector;
        this.f14864a = s;
        this.b = new byte[i];
        vector.addElement(new a(0, i));
    }

    public void a(short s, int i, byte[] bArr, int i2, int i3, int i4) {
        int i5 = i3 + i4;
        if (this.f14864a == s && this.b.length == i && i5 <= i) {
            int i6 = 0;
            if (i4 == 0) {
                if (i3 == 0 && !this.c.isEmpty() && ((a) this.c.firstElement()).a() == 0) {
                    this.c.removeElementAt(0);
                    return;
                }
                return;
            }
            while (i6 < this.c.size()) {
                a aVar = (a) this.c.elementAt(i6);
                if (aVar.b() >= i5) {
                    return;
                }
                if (aVar.a() > i3) {
                    int max = Math.max(aVar.b(), i3);
                    int min = Math.min(aVar.a(), i5);
                    System.arraycopy(bArr, (i2 + max) - i3, this.b, max, min - max);
                    if (max != aVar.b()) {
                        if (min != aVar.a()) {
                            i6++;
                            this.c.insertElementAt(new a(min, aVar.a()), i6);
                        }
                        aVar.c(max);
                    } else if (min == aVar.a()) {
                        this.c.removeElementAt(i6);
                        i6--;
                    } else {
                        aVar.d(min);
                    }
                }
                i6++;
            }
        }
    }

    public byte[] b() {
        if (this.c.isEmpty()) {
            return this.b;
        }
        return null;
    }

    public short c() {
        return this.f14864a;
    }

    public void d() {
        this.c.removeAllElements();
        this.c.addElement(new a(0, this.b.length));
    }
}
