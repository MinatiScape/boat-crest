package com.google.android.gms.internal.firebase_ml;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class zzte implements Closeable, Flushable {
    public static final String[] p = new String[128];
    public static final String[] q;
    public final Writer h;
    public int[] i = new int[32];
    public int j = 0;
    public String k;
    public String l;
    public boolean m;
    public String n;
    public boolean o;

    static {
        for (int i = 0; i <= 31; i++) {
            p[i] = String.format("\\u%04x", Integer.valueOf(i));
        }
        String[] strArr = p;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        String[] strArr2 = (String[]) strArr.clone();
        q = strArr2;
        strArr2[60] = "\\u003c";
        strArr2[62] = "\\u003e";
        strArr2[38] = "\\u0026";
        strArr2[61] = "\\u003d";
        strArr2[39] = "\\u0027";
    }

    public zzte(Writer writer) {
        d(6);
        this.l = ":";
        this.o = true;
        this.h = writer;
    }

    public final int a() {
        int i = this.j;
        if (i != 0) {
            return this.i[i - 1];
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final zzte b(int i, String str) throws IOException {
        i();
        d(i);
        this.h.write(str);
        return this;
    }

    public final zzte c(int i, int i2, String str) throws IOException {
        int a2 = a();
        if (a2 != i2 && a2 != i) {
            throw new IllegalStateException("Nesting problem.");
        }
        if (this.n == null) {
            this.j--;
            if (a2 == i2) {
                h();
            }
            this.h.write(str);
            return this;
        }
        throw new IllegalStateException("Dangling name: " + this.n);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.h.close();
        int i = this.j;
        if (i <= 1 && (i != 1 || this.i[i - 1] == 7)) {
            this.j = 0;
            return;
        }
        throw new IOException("Incomplete document");
    }

    public final void d(int i) {
        int i2 = this.j;
        int[] iArr = this.i;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            this.i = iArr2;
        }
        int[] iArr3 = this.i;
        int i3 = this.j;
        this.j = i3 + 1;
        iArr3[i3] = i;
    }

    public final void e(int i) {
        this.i[this.j - 1] = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f(java.lang.String r9) throws java.io.IOException {
        /*
            r8 = this;
            java.lang.String[] r0 = com.google.android.gms.internal.firebase_ml.zzte.p
            java.io.Writer r1 = r8.h
            java.lang.String r2 = "\""
            r1.write(r2)
            int r1 = r9.length()
            r3 = 0
            r4 = r3
        Lf:
            if (r3 >= r1) goto L3e
            char r5 = r9.charAt(r3)
            r6 = 128(0x80, float:1.794E-43)
            if (r5 >= r6) goto L1e
            r5 = r0[r5]
            if (r5 != 0) goto L2b
            goto L3b
        L1e:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L25
            java.lang.String r5 = "\\u2028"
            goto L2b
        L25:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L3b
            java.lang.String r5 = "\\u2029"
        L2b:
            if (r4 >= r3) goto L34
            java.io.Writer r6 = r8.h
            int r7 = r3 - r4
            r6.write(r9, r4, r7)
        L34:
            java.io.Writer r4 = r8.h
            r4.write(r5)
            int r4 = r3 + 1
        L3b:
            int r3 = r3 + 1
            goto Lf
        L3e:
            if (r4 >= r1) goto L46
            java.io.Writer r0 = r8.h
            int r1 = r1 - r4
            r0.write(r9, r4, r1)
        L46:
            java.io.Writer r9 = r8.h
            r9.write(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzte.f(java.lang.String):void");
    }

    @Override // java.io.Flushable
    public final void flush() throws IOException {
        if (this.j != 0) {
            this.h.flush();
            return;
        }
        throw new IllegalStateException("JsonWriter is closed.");
    }

    public final void g() throws IOException {
        if (this.n != null) {
            int a2 = a();
            if (a2 == 5) {
                this.h.write(44);
            } else if (a2 != 3) {
                throw new IllegalStateException("Nesting problem.");
            }
            h();
            e(4);
            f(this.n);
            this.n = null;
        }
    }

    public final void h() throws IOException {
        if (this.k == null) {
            return;
        }
        this.h.write("\n");
        int i = this.j;
        for (int i2 = 1; i2 < i; i2++) {
            this.h.write(this.k);
        }
    }

    public final void i() throws IOException {
        int a2 = a();
        if (a2 == 1) {
            e(2);
            h();
        } else if (a2 == 2) {
            this.h.append(',');
            h();
        } else if (a2 != 4) {
            if (a2 != 6) {
                if (a2 == 7) {
                    if (!this.m) {
                        throw new IllegalStateException("JSON must have only one top-level value.");
                    }
                } else {
                    throw new IllegalStateException("Nesting problem.");
                }
            }
            e(7);
        } else {
            this.h.append((CharSequence) this.l);
            e(5);
        }
    }

    public final void setIndent(String str) {
        if (str.length() == 0) {
            this.k = null;
            this.l = ":";
            return;
        }
        this.k = str;
        this.l = ": ";
    }

    public final void setLenient(boolean z) {
        this.m = true;
    }

    public final zzte zza(Number number) throws IOException {
        if (number == null) {
            return zzrn();
        }
        g();
        String obj = number.toString();
        if (!this.m && (obj.equals("-Infinity") || obj.equals("Infinity") || obj.equals("NaN"))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + number);
        }
        i();
        this.h.append((CharSequence) obj);
        return this;
    }

    public final zzte zzaw(boolean z) throws IOException {
        g();
        i();
        this.h.write(z ? "true" : "false");
        return this;
    }

    public final zzte zzb(double d) throws IOException {
        g();
        if (!this.m && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new IllegalArgumentException("Numeric values must be finite, but was " + d);
        }
        i();
        this.h.append((CharSequence) Double.toString(d));
        return this;
    }

    public final zzte zzcf(String str) throws IOException {
        Objects.requireNonNull(str, "name == null");
        if (this.n == null) {
            if (this.j != 0) {
                this.n = str;
                return this;
            }
            throw new IllegalStateException("JsonWriter is closed.");
        }
        throw new IllegalStateException();
    }

    public final zzte zzcg(String str) throws IOException {
        if (str == null) {
            return zzrn();
        }
        g();
        i();
        f(str);
        return this;
    }

    public final zzte zzri() throws IOException {
        g();
        return b(1, "[");
    }

    public final zzte zzrj() throws IOException {
        return c(1, 2, "]");
    }

    public final zzte zzrk() throws IOException {
        g();
        return b(3, "{");
    }

    public final zzte zzrl() throws IOException {
        return c(3, 5, "}");
    }

    public final zzte zzrn() throws IOException {
        if (this.n != null) {
            if (this.o) {
                g();
            } else {
                this.n = null;
                return this;
            }
        }
        i();
        this.h.write("null");
        return this;
    }

    public final zzte zzu(long j) throws IOException {
        g();
        i();
        this.h.write(Long.toString(j));
        return this;
    }
}
