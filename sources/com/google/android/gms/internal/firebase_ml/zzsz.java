package com.google.android.gms.internal.firebase_ml;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import kotlin.text.Typography;
/* loaded from: classes7.dex */
public final class zzsz implements Closeable {
    public static final char[] v = ")]}'\n".toCharArray();
    public final Reader h;
    public boolean i = false;
    public final char[] j = new char[1024];
    public int k = 0;
    public int l = 0;
    public int m = 0;
    public int n = 0;
    public int o = 0;
    public long p;
    public int q;
    public int[] r;
    public int s;
    public String[] t;
    public int[] u;

    static {
        zzta.zzbvs = new i5();
    }

    public zzsz(Reader reader) {
        int[] iArr = new int[32];
        this.r = iArr;
        this.s = 0;
        this.s = 0 + 1;
        iArr[0] = 6;
        this.t = new String[32];
        this.u = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.h = reader;
    }

    public final int a(boolean z) throws IOException {
        int i;
        char[] cArr = this.j;
        int i2 = this.k;
        int i3 = this.l;
        while (true) {
            boolean z2 = true;
            if (i2 == i3) {
                this.k = i2;
                if (!c(1)) {
                    if (z) {
                        throw new EOFException("End of input" + l());
                    }
                    return -1;
                }
                i2 = this.k;
                i3 = this.l;
            }
            int i4 = i2 + 1;
            char c = cArr[i2];
            if (c == '\n') {
                this.m++;
                this.n = i4;
            } else if (c != ' ' && c != '\r' && c != '\t') {
                if (c == '/') {
                    this.k = i4;
                    if (i4 == i3) {
                        this.k = i4 - 1;
                        boolean c2 = c(2);
                        this.k++;
                        if (!c2) {
                            return c;
                        }
                    }
                    j();
                    int i5 = this.k;
                    char c3 = cArr[i5];
                    if (c3 == '*') {
                        this.k = i5 + 1;
                        while (true) {
                            if (this.k + 2 > this.l && !c(2)) {
                                z2 = false;
                                break;
                            }
                            char[] cArr2 = this.j;
                            int i6 = this.k;
                            if (cArr2[i6] != '\n') {
                                while (i < 2) {
                                    i = this.j[this.k + i] == "*/".charAt(i) ? i + 1 : 0;
                                }
                                break;
                            }
                            this.m++;
                            this.n = i6 + 1;
                            this.k++;
                        }
                        if (z2) {
                            i2 = this.k + 2;
                            i3 = this.l;
                        } else {
                            throw d("Unterminated comment");
                        }
                    } else if (c3 != '/') {
                        return c;
                    } else {
                        this.k = i5 + 1;
                        k();
                        i2 = this.k;
                        i3 = this.l;
                    }
                } else if (c == '#') {
                    this.k = i4;
                    j();
                    k();
                    i2 = this.k;
                    i3 = this.l;
                } else {
                    this.k = i4;
                    return c;
                }
            }
            i2 = i4;
        }
    }

    public final void b(int i) {
        int i2 = this.s;
        int[] iArr = this.r;
        if (i2 == iArr.length) {
            int[] iArr2 = new int[i2 << 1];
            int[] iArr3 = new int[i2 << 1];
            String[] strArr = new String[i2 << 1];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.u, 0, iArr3, 0, this.s);
            System.arraycopy(this.t, 0, strArr, 0, this.s);
            this.r = iArr2;
            this.u = iArr3;
            this.t = strArr;
        }
        int[] iArr4 = this.r;
        int i3 = this.s;
        this.s = i3 + 1;
        iArr4[i3] = i;
    }

    public final void beginArray() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 3) {
            b(1);
            this.u[this.s - 1] = 0;
            this.o = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + zzrb() + l());
    }

    public final void beginObject() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 1) {
            b(3);
            this.o = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + zzrb() + l());
    }

    public final boolean c(int i) throws IOException {
        int i2;
        int i3;
        char[] cArr = this.j;
        int i4 = this.n;
        int i5 = this.k;
        this.n = i4 - i5;
        int i6 = this.l;
        if (i6 != i5) {
            int i7 = i6 - i5;
            this.l = i7;
            System.arraycopy(cArr, i5, cArr, 0, i7);
        } else {
            this.l = 0;
        }
        this.k = 0;
        do {
            Reader reader = this.h;
            int i8 = this.l;
            int read = reader.read(cArr, i8, cArr.length - i8);
            if (read == -1) {
                return false;
            }
            i2 = this.l + read;
            this.l = i2;
            if (this.m == 0 && (i3 = this.n) == 0 && i2 > 0 && cArr[0] == 65279) {
                this.k++;
                this.n = i3 + 1;
                i++;
                continue;
            }
        } while (i2 < i);
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.o = 0;
        this.r[0] = 8;
        this.s = 1;
        this.h.close();
    }

    public final IOException d(String str) throws IOException {
        throw new zztd(str + l());
    }

    public final boolean e(char c) throws IOException {
        if (c == '\t' || c == '\n' || c == '\f' || c == '\r' || c == ' ') {
            return false;
        }
        if (c != '#') {
            if (c == ',') {
                return false;
            }
            if (c != '/' && c != '=') {
                if (c == '{' || c == '}' || c == ':') {
                    return false;
                }
                if (c != ';') {
                    switch (c) {
                        case '[':
                        case ']':
                            return false;
                        case '\\':
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        j();
        return false;
    }

    public final void endArray() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 4) {
            int i2 = this.s - 1;
            this.s = i2;
            int[] iArr = this.u;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.o = 0;
            return;
        }
        throw new IllegalStateException("Expected END_ARRAY but was " + zzrb() + l());
    }

    public final void endObject() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 2) {
            int i2 = this.s - 1;
            this.s = i2;
            this.t[i2] = null;
            int[] iArr = this.u;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.o = 0;
            return;
        }
        throw new IllegalStateException("Expected END_OBJECT but was " + zzrb() + l());
    }

    public final String f(char c) throws IOException {
        char[] cArr = this.j;
        StringBuilder sb = null;
        while (true) {
            int i = this.k;
            int i2 = this.l;
            int i3 = i;
            while (true) {
                if (i3 < i2) {
                    int i4 = i3 + 1;
                    char c2 = cArr[i3];
                    if (c2 == c) {
                        this.k = i4;
                        int i5 = (i4 - i) - 1;
                        if (sb == null) {
                            return new String(cArr, i, i5);
                        }
                        sb.append(cArr, i, i5);
                        return sb.toString();
                    } else if (c2 == '\\') {
                        this.k = i4;
                        int i6 = (i4 - i) - 1;
                        if (sb == null) {
                            sb = new StringBuilder(Math.max((i6 + 1) << 1, 16));
                        }
                        sb.append(cArr, i, i6);
                        sb.append(m());
                    } else {
                        if (c2 == '\n') {
                            this.m++;
                            this.n = i4;
                        }
                        i3 = i4;
                    }
                } else {
                    if (sb == null) {
                        sb = new StringBuilder(Math.max((i3 - i) << 1, 16));
                    }
                    sb.append(cArr, i, i3 - i);
                    this.k = i3;
                    if (!c(1)) {
                        throw d("Unterminated string");
                    }
                }
            }
        }
    }

    public final void g(char c) throws IOException {
        char[] cArr = this.j;
        while (true) {
            int i = this.k;
            int i2 = this.l;
            while (true) {
                if (i < i2) {
                    int i3 = i + 1;
                    char c2 = cArr[i];
                    if (c2 == c) {
                        this.k = i3;
                        return;
                    } else if (c2 == '\\') {
                        this.k = i3;
                        m();
                        break;
                    } else {
                        if (c2 == '\n') {
                            this.m++;
                            this.n = i3;
                        }
                        i = i3;
                    }
                } else {
                    this.k = i;
                    if (!c(1)) {
                        throw d("Unterminated string");
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:152:0x01fb, code lost:
        if (e(r5) == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x01fe, code lost:
        if (r4 != 2) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0200, code lost:
        if (r11 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x0206, code lost:
        if (r12 != Long.MIN_VALUE) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0208, code lost:
        if (r16 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x020e, code lost:
        if (r12 != 0) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x0210, code lost:
        if (r16 != false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0212, code lost:
        if (r16 == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0215, code lost:
        r12 = -r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0216, code lost:
        r19.p = r12;
        r19.k += r9;
        r5 = 15;
        r19.o = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0222, code lost:
        if (r4 == 2) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x0225, code lost:
        if (r4 == 4) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:170:0x0228, code lost:
        if (r4 != 7) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x022d, code lost:
        r19.q = r9;
        r5 = 16;
        r19.o = 16;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0173 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x025a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:194:0x025b  */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int h() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 789
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsz.h():int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x004a, code lost:
        j();
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String i() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r6.k
            int r4 = r3 + r2
            int r5 = r6.l
            if (r4 >= r5) goto L4e
            char[] r4 = r6.j
            int r3 = r3 + r2
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5c
            r4 = 10
            if (r3 == r4) goto L5c
            r4 = 12
            if (r3 == r4) goto L5c
            r4 = 13
            if (r3 == r4) goto L5c
            r4 = 32
            if (r3 == r4) goto L5c
            r4 = 35
            if (r3 == r4) goto L4a
            r4 = 44
            if (r3 == r4) goto L5c
            r4 = 47
            if (r3 == r4) goto L4a
            r4 = 61
            if (r3 == r4) goto L4a
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5c
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5c
            r4 = 58
            if (r3 == r4) goto L5c
            r4 = 59
            if (r3 == r4) goto L4a
            switch(r3) {
                case 91: goto L5c;
                case 92: goto L4a;
                case 93: goto L5c;
                default: goto L47;
            }
        L47:
            int r2 = r2 + 1
            goto L3
        L4a:
            r6.j()
            goto L5c
        L4e:
            char[] r3 = r6.j
            int r3 = r3.length
            if (r2 >= r3) goto L5e
            int r3 = r2 + 1
            boolean r3 = r6.c(r3)
            if (r3 == 0) goto L5c
            goto L3
        L5c:
            r0 = r2
            goto L7e
        L5e:
            if (r1 != 0) goto L6b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r3 = 16
            int r3 = java.lang.Math.max(r2, r3)
            r1.<init>(r3)
        L6b:
            char[] r3 = r6.j
            int r4 = r6.k
            r1.append(r3, r4, r2)
            int r3 = r6.k
            int r3 = r3 + r2
            r6.k = r3
            r2 = 1
            boolean r2 = r6.c(r2)
            if (r2 != 0) goto L2
        L7e:
            if (r1 != 0) goto L8a
            java.lang.String r1 = new java.lang.String
            char[] r2 = r6.j
            int r3 = r6.k
            r1.<init>(r2, r3, r0)
            goto L95
        L8a:
            char[] r2 = r6.j
            int r3 = r6.k
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L95:
            int r2 = r6.k
            int r2 = r2 + r0
            r6.k = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.firebase_ml.zzsz.i():java.lang.String");
    }

    public final void j() throws IOException {
        if (!this.i) {
            throw d("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final void k() throws IOException {
        char c;
        do {
            if (this.k >= this.l && !c(1)) {
                return;
            }
            char[] cArr = this.j;
            int i = this.k;
            int i2 = i + 1;
            this.k = i2;
            c = cArr[i];
            if (c == '\n') {
                this.m++;
                this.n = i2;
                return;
            }
        } while (c != '\r');
    }

    public final String l() {
        StringBuilder sb = new StringBuilder(" at line ");
        sb.append(this.m + 1);
        sb.append(" column ");
        sb.append((this.k - this.n) + 1);
        sb.append(" path ");
        StringBuilder sb2 = new StringBuilder("$");
        int i = this.s;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.r[i2];
            if (i3 == 1 || i3 == 2) {
                sb2.append('[');
                sb2.append(this.u[i2]);
                sb2.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb2.append('.');
                String[] strArr = this.t;
                if (strArr[i2] != null) {
                    sb2.append(strArr[i2]);
                }
            }
        }
        sb.append(sb2.toString());
        return sb.toString();
    }

    public final char m() throws IOException {
        int i;
        int i2;
        if (this.k == this.l && !c(1)) {
            throw d("Unterminated escape sequence");
        }
        char[] cArr = this.j;
        int i3 = this.k;
        int i4 = i3 + 1;
        this.k = i4;
        char c = cArr[i3];
        if (c == '\n') {
            this.m++;
            this.n = i4;
        } else if (c != '\"' && c != '\'' && c != '/' && c != '\\') {
            if (c != 'b') {
                if (c != 'f') {
                    if (c != 'n') {
                        if (c != 'r') {
                            if (c != 't') {
                                if (c == 'u') {
                                    if (i4 + 4 > this.l && !c(4)) {
                                        throw d("Unterminated escape sequence");
                                    }
                                    char c2 = 0;
                                    int i5 = this.k;
                                    int i6 = i5 + 4;
                                    while (i5 < i6) {
                                        char c3 = this.j[i5];
                                        char c4 = (char) (c2 << 4);
                                        if (c3 < '0' || c3 > '9') {
                                            if (c3 >= 'a' && c3 <= 'f') {
                                                i = c3 - 'a';
                                            } else if (c3 < 'A' || c3 > 'F') {
                                                throw new NumberFormatException("\\u" + new String(this.j, this.k, 4));
                                            } else {
                                                i = c3 - 'A';
                                            }
                                            i2 = i + 10;
                                        } else {
                                            i2 = c3 - '0';
                                        }
                                        c2 = (char) (c4 + i2);
                                        i5++;
                                    }
                                    this.k += 4;
                                    return c2;
                                }
                                throw d("Invalid escape sequence");
                            }
                            return '\t';
                        }
                        return '\r';
                    }
                    return '\n';
                }
                return '\f';
            }
            return '\b';
        }
        return c;
    }

    public final boolean nextBoolean() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 5) {
            this.o = 0;
            int[] iArr = this.u;
            int i2 = this.s - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.o = 0;
            int[] iArr2 = this.u;
            int i3 = this.s - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + zzrb() + l());
        }
    }

    public final String nextName() throws IOException {
        String f;
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 14) {
            f = i();
        } else if (i == 12) {
            f = f('\'');
        } else if (i == 13) {
            f = f(Typography.quote);
        } else {
            throw new IllegalStateException("Expected a name but was " + zzrb() + l());
        }
        this.o = 0;
        this.t[this.s - 1] = f;
        return f;
    }

    public final void nextNull() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 7) {
            this.o = 0;
            int[] iArr = this.u;
            int i2 = this.s - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + zzrb() + l());
    }

    public final String nextString() throws IOException {
        String str;
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        if (i == 10) {
            str = i();
        } else if (i == 8) {
            str = f('\'');
        } else if (i == 9) {
            str = f(Typography.quote);
        } else if (i == 11) {
            str = null;
        } else if (i == 15) {
            str = Long.toString(this.p);
        } else if (i == 16) {
            str = new String(this.j, this.k, this.q);
            this.k += this.q;
        } else {
            throw new IllegalStateException("Expected a string but was " + zzrb() + l());
        }
        this.o = 0;
        int[] iArr = this.u;
        int i2 = this.s - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final void setLenient(boolean z) {
        this.i = true;
    }

    public final void skipValue() throws IOException {
        int i = 0;
        do {
            int i2 = this.o;
            if (i2 == 0) {
                i2 = h();
            }
            if (i2 == 3) {
                b(1);
            } else if (i2 == 1) {
                b(3);
            } else {
                if (i2 == 4) {
                    this.s--;
                } else if (i2 == 2) {
                    this.s--;
                } else {
                    if (i2 == 14 || i2 == 10) {
                        do {
                            int i3 = 0;
                            while (true) {
                                int i4 = this.k;
                                if (i4 + i3 < this.l) {
                                    char c = this.j[i4 + i3];
                                    if (c != '\t' && c != '\n' && c != '\f' && c != '\r' && c != ' ') {
                                        if (c != '#') {
                                            if (c != ',') {
                                                if (c != '/' && c != '=') {
                                                    if (c != '{' && c != '}' && c != ':') {
                                                        if (c != ';') {
                                                            switch (c) {
                                                                case '[':
                                                                case ']':
                                                                    break;
                                                                case '\\':
                                                                    break;
                                                                default:
                                                                    i3++;
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                } else {
                                    this.k = i4 + i3;
                                }
                            }
                            j();
                            this.k += i3;
                        } while (c(1));
                    } else if (i2 == 8 || i2 == 12) {
                        g('\'');
                    } else if (i2 == 9 || i2 == 13) {
                        g(Typography.quote);
                    } else if (i2 == 16) {
                        this.k += this.q;
                    }
                    this.o = 0;
                }
                i--;
                this.o = 0;
            }
            i++;
            this.o = 0;
        } while (i != 0);
        int[] iArr = this.u;
        int i5 = this.s;
        int i6 = i5 - 1;
        iArr[i6] = iArr[i6] + 1;
        this.t[i5 - 1] = "null";
    }

    public final String toString() {
        return zzsz.class.getSimpleName() + l();
    }

    public final zztb zzrb() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = h();
        }
        switch (i) {
            case 1:
                return zztb.BEGIN_OBJECT;
            case 2:
                return zztb.END_OBJECT;
            case 3:
                return zztb.BEGIN_ARRAY;
            case 4:
                return zztb.END_ARRAY;
            case 5:
            case 6:
                return zztb.BOOLEAN;
            case 7:
                return zztb.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return zztb.STRING;
            case 12:
            case 13:
            case 14:
                return zztb.NAME;
            case 15:
            case 16:
                return zztb.NUMBER;
            case 17:
                return zztb.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }
}
