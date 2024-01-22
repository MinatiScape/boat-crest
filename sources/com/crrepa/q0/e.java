package com.crrepa.q0;

import com.crrepa.n0.o;
import com.crrepa.n0.r;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;
/* loaded from: classes9.dex */
public final class e extends com.crrepa.t0.a {
    public static final Reader B = new a();
    public static final Object C = new Object();
    public int[] A;
    public Object[] x;
    public int y;
    public String[] z;

    /* loaded from: classes9.dex */
    public static class a extends Reader {
        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            throw new AssertionError();
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new AssertionError();
        }
    }

    public e(com.crrepa.n0.l lVar) {
        super(B);
        this.x = new Object[32];
        this.y = 0;
        this.z = new String[32];
        this.A = new int[32];
        H(lVar);
    }

    private String v() {
        return " at path " + h();
    }

    public void C() throws IOException {
        G(com.crrepa.t0.c.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) E()).next();
        H(entry.getValue());
        H(new r((String) entry.getKey()));
    }

    public final Object E() {
        return this.x[this.y - 1];
    }

    public final Object F() {
        Object[] objArr = this.x;
        int i = this.y - 1;
        this.y = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public final void G(com.crrepa.t0.c cVar) throws IOException {
        if (t() == cVar) {
            return;
        }
        throw new IllegalStateException("Expected " + cVar + " but was " + t() + v());
    }

    public final void H(Object obj) {
        int i = this.y;
        Object[] objArr = this.x;
        if (i == objArr.length) {
            int i2 = i * 2;
            Object[] objArr2 = new Object[i2];
            int[] iArr = new int[i2];
            String[] strArr = new String[i2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(this.A, 0, iArr, 0, this.y);
            System.arraycopy(this.z, 0, strArr, 0, this.y);
            this.x = objArr2;
            this.A = iArr;
            this.z = strArr;
        }
        Object[] objArr3 = this.x;
        int i3 = this.y;
        this.y = i3 + 1;
        objArr3[i3] = obj;
    }

    @Override // com.crrepa.t0.a
    public void a() throws IOException {
        G(com.crrepa.t0.c.BEGIN_ARRAY);
        H(((com.crrepa.n0.i) E()).iterator());
        this.A[this.y - 1] = 0;
    }

    @Override // com.crrepa.t0.a
    public void b() throws IOException {
        G(com.crrepa.t0.c.BEGIN_OBJECT);
        H(((o) E()).x().iterator());
    }

    @Override // com.crrepa.t0.a, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.x = new Object[]{C};
        this.y = 1;
    }

    @Override // com.crrepa.t0.a
    public void f() throws IOException {
        G(com.crrepa.t0.c.END_ARRAY);
        F();
        F();
        int i = this.y;
        if (i > 0) {
            int[] iArr = this.A;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.crrepa.t0.a
    public void g() throws IOException {
        G(com.crrepa.t0.c.END_OBJECT);
        F();
        F();
        int i = this.y;
        if (i > 0) {
            int[] iArr = this.A;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.crrepa.t0.a
    public String h() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        int i = 0;
        while (i < this.y) {
            Object[] objArr = this.x;
            if (objArr[i] instanceof com.crrepa.n0.i) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.A[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof o) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.z;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // com.crrepa.t0.a
    public boolean i() throws IOException {
        com.crrepa.t0.c t = t();
        return (t == com.crrepa.t0.c.END_OBJECT || t == com.crrepa.t0.c.END_ARRAY) ? false : true;
    }

    @Override // com.crrepa.t0.a
    public boolean l() throws IOException {
        G(com.crrepa.t0.c.BOOLEAN);
        boolean d = ((r) F()).d();
        int i = this.y;
        if (i > 0) {
            int[] iArr = this.A;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return d;
    }

    @Override // com.crrepa.t0.a
    public double m() throws IOException {
        com.crrepa.t0.c t = t();
        com.crrepa.t0.c cVar = com.crrepa.t0.c.NUMBER;
        if (t != cVar && t != com.crrepa.t0.c.STRING) {
            throw new IllegalStateException("Expected " + cVar + " but was " + t + v());
        }
        double h = ((r) E()).h();
        if (!j() && (Double.isNaN(h) || Double.isInfinite(h))) {
            throw new NumberFormatException("JSON forbids NaN and infinities: " + h);
        }
        F();
        int i = this.y;
        if (i > 0) {
            int[] iArr = this.A;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return h;
    }

    @Override // com.crrepa.t0.a
    public int n() throws IOException {
        com.crrepa.t0.c t = t();
        com.crrepa.t0.c cVar = com.crrepa.t0.c.NUMBER;
        if (t != cVar && t != com.crrepa.t0.c.STRING) {
            throw new IllegalStateException("Expected " + cVar + " but was " + t + v());
        }
        int j = ((r) E()).j();
        F();
        int i = this.y;
        if (i > 0) {
            int[] iArr = this.A;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return j;
    }

    @Override // com.crrepa.t0.a
    public long o() throws IOException {
        com.crrepa.t0.c t = t();
        com.crrepa.t0.c cVar = com.crrepa.t0.c.NUMBER;
        if (t != cVar && t != com.crrepa.t0.c.STRING) {
            throw new IllegalStateException("Expected " + cVar + " but was " + t + v());
        }
        long o = ((r) E()).o();
        F();
        int i = this.y;
        if (i > 0) {
            int[] iArr = this.A;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return o;
    }

    @Override // com.crrepa.t0.a
    public String p() throws IOException {
        G(com.crrepa.t0.c.NAME);
        Map.Entry entry = (Map.Entry) ((Iterator) E()).next();
        String str = (String) entry.getKey();
        this.z[this.y - 1] = str;
        H(entry.getValue());
        return str;
    }

    @Override // com.crrepa.t0.a
    public void q() throws IOException {
        G(com.crrepa.t0.c.NULL);
        F();
        int i = this.y;
        if (i > 0) {
            int[] iArr = this.A;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // com.crrepa.t0.a
    public String r() throws IOException {
        com.crrepa.t0.c t = t();
        com.crrepa.t0.c cVar = com.crrepa.t0.c.STRING;
        if (t == cVar || t == com.crrepa.t0.c.NUMBER) {
            String r = ((r) F()).r();
            int i = this.y;
            if (i > 0) {
                int[] iArr = this.A;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return r;
        }
        throw new IllegalStateException("Expected " + cVar + " but was " + t + v());
    }

    @Override // com.crrepa.t0.a
    public com.crrepa.t0.c t() throws IOException {
        if (this.y == 0) {
            return com.crrepa.t0.c.END_DOCUMENT;
        }
        Object E = E();
        if (E instanceof Iterator) {
            boolean z = this.x[this.y - 2] instanceof o;
            Iterator it = (Iterator) E;
            if (!it.hasNext()) {
                return z ? com.crrepa.t0.c.END_OBJECT : com.crrepa.t0.c.END_ARRAY;
            } else if (z) {
                return com.crrepa.t0.c.NAME;
            } else {
                H(it.next());
                return t();
            }
        } else if (E instanceof o) {
            return com.crrepa.t0.c.BEGIN_OBJECT;
        } else {
            if (E instanceof com.crrepa.n0.i) {
                return com.crrepa.t0.c.BEGIN_ARRAY;
            }
            if (!(E instanceof r)) {
                if (E instanceof com.crrepa.n0.n) {
                    return com.crrepa.t0.c.NULL;
                }
                if (E == C) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
            r rVar = (r) E;
            if (rVar.z()) {
                return com.crrepa.t0.c.STRING;
            }
            if (rVar.x()) {
                return com.crrepa.t0.c.BOOLEAN;
            }
            if (rVar.y()) {
                return com.crrepa.t0.c.NUMBER;
            }
            throw new AssertionError();
        }
    }

    @Override // com.crrepa.t0.a
    public String toString() {
        return e.class.getSimpleName();
    }

    @Override // com.crrepa.t0.a
    public void z() throws IOException {
        if (t() == com.crrepa.t0.c.NAME) {
            p();
            this.z[this.y - 2] = "null";
        } else {
            F();
            this.z[this.y - 1] = "null";
        }
        int[] iArr = this.A;
        int i = this.y - 1;
        iArr[i] = iArr[i] + 1;
    }
}
