package com.crrepa.t0;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
/* loaded from: classes9.dex */
public class d implements Closeable, Flushable {
    public static final String[] q = new String[128];
    public static final String[] r;
    public final Writer h;
    public int[] i = new int[32];
    public int j = 0;
    public String k;
    public String l;
    public boolean m;
    public boolean n;
    public String o;
    public boolean p;

    static {
        for (int i = 0; i <= 31; i++) {
            q[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = q;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        r = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public d(Writer writer) {
        k(6);
        this.l = ":";
        this.p = true;
        Objects.requireNonNull(writer, "out == null");
        this.h = writer;
    }

    public d a(double d) throws IOException {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        q();
        l();
        this.h.append((CharSequence) Double.toString(d));
        return this;
    }

    public d a(long j) throws IOException {
        q();
        l();
        this.h.write(Long.toString(j));
        return this;
    }

    public d a(Boolean bool) throws IOException {
        if (bool == null) {
            return k();
        }
        q();
        l();
        this.h.write(bool.booleanValue() ? "true" : "false");
        return this;
    }

    public d a(Number number) throws IOException {
        if (number == null) {
            return k();
        }
        q();
        String obj = number.toString();
        if (this.m || !(obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            l();
            this.h.append((CharSequence) obj);
            return this;
        }
        throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
    }

    public d a(String str) throws IOException {
        if (str == null) {
            return k();
        }
        q();
        l();
        this.h.append((CharSequence) str);
        return this;
    }

    public final void a(boolean z) {
        this.n = z;
    }

    public final d b(int i, int i2, String str) throws IOException {
        int p = p();
        if (p == i2 || p == i) {
            if (this.o != null) {
                throw new IllegalStateException("Dangling name: " + this.o);
            }
            this.j--;
            if (p == i2) {
                o();
            }
            this.h.write(str);
            return this;
        }
        throw new IllegalStateException("Nesting problem.");
    }

    public d b(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.o == null) {
            if (this.j != 0) {
                this.o = str;
                return this;
            }
            throw new IllegalStateException("JsonWriter is closed.");
        }
        throw new IllegalStateException();
    }

    public final void b(boolean z) {
        this.m = z;
    }

    public d c() throws IOException {
        q();
        return c(1, "[");
    }

    public final d c(int i, String str) throws IOException {
        l();
        k(i);
        this.h.write(str);
        return this;
    }

    public final void c(String str) {
        String str2;
        if (str.length() == 0) {
            this.k = null;
            str2 = ":";
        } else {
            this.k = str;
            str2 = ": ";
        }
        this.l = str2;
    }

    public final void c(boolean z) {
        this.p = z;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.h.close();
        int i = this.j;
        if (i > 1 || (i == 1 && this.i[i - 1] != 7)) {
            throw new IOException("Incomplete document");
        }
        this.j = 0;
    }

    public d d() throws IOException {
        q();
        return c(3, "{");
    }

    public d d(boolean z) throws IOException {
        q();
        l();
        this.h.write(z ? "true" : "false");
        return this;
    }

    public d e() throws IOException {
        return b(1, 2, "]");
    }

    public d e(String str) throws IOException {
        if (str == null) {
            return k();
        }
        q();
        l();
        n(str);
        return this;
    }

    public d f() throws IOException {
        return b(3, 5, "}");
    }

    public void flush() throws IOException {
        if (this.j == 0) {
            throw new IllegalStateException("JsonWriter is closed.");
        }
        this.h.flush();
    }

    public final boolean g() {
        return this.p;
    }

    public final boolean h() {
        return this.n;
    }

    public boolean i() {
        return this.m;
    }

    public final void j() throws IOException {
        int p = p();
        if (p == 5) {
            this.h.write(44);
        } else if (p != 3) {
            throw new IllegalStateException("Nesting problem.");
        }
        o();
        m(4);
    }

    public d k() throws IOException {
        if (this.o != null) {
            if (!this.p) {
                this.o = null;
                return this;
            }
            q();
        }
        l();
        this.h.write("null");
        return this;
    }

    public final void k(int i) {
        int i2 = this.j;
        int[] iArr = this.i;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 * 2];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.i = iArr2;
        }
        int[] iArr3 = this.i;
        int i3 = this.j;
        this.j = i3 + 1;
        iArr3[i3] = i;
    }

    public final void l() throws IOException {
        int p = p();
        if (p == 1) {
            m(2);
        } else if (p != 2) {
            if (p == 4) {
                this.h.append((CharSequence) this.l);
                m(5);
                return;
            }
            if (p != 6) {
                if (p != 7) {
                    throw new IllegalStateException("Nesting problem.");
                }
                if (!this.m) {
                    throw new IllegalStateException("JSON must have only one top-level value.");
                }
            }
            m(7);
            return;
        } else {
            this.h.append(',');
        }
        o();
    }

    public final void m(int i) {
        this.i[this.j - 1] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void n(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            boolean r0 = r8.n
            if (r0 == 0) goto L7
            java.lang.String[] r0 = com.crrepa.t0.d.r
            goto L9
        L7:
            java.lang.String[] r0 = com.crrepa.t0.d.q
        L9:
            java.io.Writer r1 = r8.h
            java.lang.String r2 = "\""
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = r3
        L16:
            if (r3 >= r1) goto L45
            char r5 = r9.charAt(r3)
            r6 = 128(0x80, float:1.794E-43)
            if (r5 >= r6) goto L25
            r5 = r0[r5]
            if (r5 != 0) goto L32
            goto L42
        L25:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L2c
            java.lang.String r5 = "\\u2028"
            goto L32
        L2c:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L42
            java.lang.String r5 = "\\u2029"
        L32:
            if (r4 >= r3) goto L3b
            java.io.Writer r6 = r8.h
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L3b:
            java.io.Writer r4 = r8.h
            r4.write(r5)
            int r4 = r3 + 1
        L42:
            int r3 = r3 + 1
            goto L16
        L45:
            if (r4 >= r1) goto L4d
            java.io.Writer r0 = r8.h
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L4d:
            java.io.Writer r9 = r8.h
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.t0.d.n(java.lang.String):void");
    }

    public final void o() throws IOException {
        if (this.k == null) {
            return;
        }
        this.h.write("\n");
        int i = this.j;
        for (int i2 = 1; i2 < i; i2++) {
            this.h.write(this.k);
        }
    }

    public final int p() {
        int i = this.j;
        if (i != 0) {
            return this.i[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void q() throws IOException {
        if (this.o != null) {
            j();
            n(this.o);
            this.o = null;
        }
    }
}
