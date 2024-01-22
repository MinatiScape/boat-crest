package com.crrepa.t0;

import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import kotlin.text.Typography;
/* loaded from: classes9.dex */
public class a implements Closeable {
    public static final char[] w = ")]}'\n".toCharArray();
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
    public String r;
    public int[] s;
    public int t;
    public String[] u;
    public int[] v;

    /* renamed from: com.crrepa.t0.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public static class C0357a extends com.crrepa.p0.e {
        @Override // com.crrepa.p0.e
        public void a(a aVar) throws IOException {
            int i;
            if (aVar instanceof com.crrepa.q0.e) {
                ((com.crrepa.q0.e) aVar).C();
                return;
            }
            int i2 = aVar.o;
            if (i2 == 0) {
                i2 = aVar.u();
            }
            if (i2 == 13) {
                i = 9;
            } else if (i2 == 12) {
                i = 8;
            } else if (i2 != 14) {
                throw new IllegalStateException("Expected a name but was " + aVar.t() + aVar.v());
            } else {
                i = 10;
            }
            aVar.o = i;
        }
    }

    static {
        com.crrepa.p0.e.f7803a = new C0357a();
    }

    public a(Reader reader) {
        int[] iArr = new int[32];
        this.s = iArr;
        this.t = 0;
        this.t = 1;
        iArr[0] = 6;
        this.u = new String[32];
        this.v = new int[32];
        Objects.requireNonNull(reader, "in == null");
        this.h = reader;
    }

    public final char A() throws IOException {
        int i;
        int i2;
        if (this.k != this.l || d(1)) {
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
                                        if (i4 + 4 <= this.l || d(4)) {
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
                                        throw f("Unterminated escape sequence");
                                    }
                                    throw f("Invalid escape sequence");
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
        throw f("Unterminated escape sequence");
    }

    public final void B() throws IOException {
        char c;
        do {
            if (this.k >= this.l && !d(1)) {
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

    public final void D() throws IOException {
        do {
            int i = 0;
            while (true) {
                int i2 = this.k + i;
                if (i2 < this.l) {
                    char c = this.j[i2];
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
                                                    i++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    this.k = i2;
                }
            }
            k();
            this.k += i;
            return;
        } while (d(1));
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0053, code lost:
        if (r1 != '/') goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0055, code lost:
        r7.k = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0058, code lost:
        if (r4 != r2) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005a, code lost:
        r7.k = r4 - 1;
        r2 = d(2);
        r7.k++;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0067, code lost:
        if (r2 != false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0069, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x006a, code lost:
        k();
        r2 = r7.k;
        r3 = r0[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0073, code lost:
        if (r3 == '*') goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0075, code lost:
        if (r3 == '/') goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0077, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0078, code lost:
        r7.k = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007d, code lost:
        r7.k = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0087, code lost:
        if (e("*\/") == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0094, code lost:
        throw f("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0095, code lost:
        r7.k = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0099, code lost:
        if (r1 != '#') goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x009b, code lost:
        k();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a3, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int a(boolean r8) throws java.io.IOException {
        /*
            r7 = this;
            char[] r0 = r7.j
        L2:
            int r1 = r7.k
        L4:
            int r2 = r7.l
        L6:
            r3 = 1
            if (r1 != r2) goto L34
            r7.k = r1
            boolean r1 = r7.d(r3)
            if (r1 != 0) goto L30
            if (r8 != 0) goto L15
            r8 = -1
            return r8
        L15:
            java.io.EOFException r8 = new java.io.EOFException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "End of input"
            r0.append(r1)
            java.lang.String r1 = r7.v()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.<init>(r0)
            throw r8
        L30:
            int r1 = r7.k
            int r2 = r7.l
        L34:
            int r4 = r1 + 1
            char r1 = r0[r1]
            r5 = 10
            if (r1 != r5) goto L44
            int r1 = r7.m
            int r1 = r1 + r3
            r7.m = r1
            r7.n = r4
            goto La4
        L44:
            r5 = 32
            if (r1 == r5) goto La4
            r5 = 13
            if (r1 == r5) goto La4
            r5 = 9
            if (r1 != r5) goto L51
            goto La4
        L51:
            r5 = 47
            if (r1 != r5) goto L95
            r7.k = r4
            r6 = 2
            if (r4 != r2) goto L6a
            int r4 = r4 + (-1)
            r7.k = r4
            boolean r2 = r7.d(r6)
            int r4 = r7.k
            int r4 = r4 + r3
            r7.k = r4
            if (r2 != 0) goto L6a
            return r1
        L6a:
            r7.k()
            int r2 = r7.k
            char r3 = r0[r2]
            r4 = 42
            if (r3 == r4) goto L7d
            if (r3 == r5) goto L78
            return r1
        L78:
            int r2 = r2 + 1
            r7.k = r2
            goto L9e
        L7d:
            int r2 = r2 + 1
            r7.k = r2
        */
        //  java.lang.String r1 = "*/"
        /*
            boolean r1 = r7.e(r1)
            if (r1 == 0) goto L8e
            int r1 = r7.k
            int r1 = r1 + r6
            goto L4
        L8e:
            java.lang.String r8 = "Unterminated comment"
            java.io.IOException r8 = r7.f(r8)
            throw r8
        L95:
            r2 = 35
            r7.k = r4
            if (r1 != r2) goto La3
            r7.k()
        L9e:
            r7.B()
            goto L2
        La3:
            return r1
        La4:
            r1 = r4
            goto L6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.t0.a.a(boolean):int");
    }

    public void a() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 3) {
            h(1);
            this.v[this.t - 1] = 0;
            this.o = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_ARRAY but was " + t() + v());
    }

    public void b() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 1) {
            h(3);
            this.o = 0;
            return;
        }
        throw new IllegalStateException("Expected BEGIN_OBJECT but was " + t() + v());
    }

    public final void b(boolean z) {
        this.i = z;
    }

    public final boolean c(char c) throws IOException {
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
        k();
        return false;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.o = 0;
        this.s[0] = 8;
        this.t = 1;
        this.h.close();
    }

    public final boolean d(int i) throws IOException {
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

    public final boolean e(String str) throws IOException {
        while (true) {
            if (this.k + str.length() > this.l && !d(str.length())) {
                return false;
            }
            char[] cArr = this.j;
            int i = this.k;
            if (cArr[i] != '\n') {
                for (int i2 = 0; i2 < str.length(); i2++) {
                    if (this.j[this.k + i2] != str.charAt(i2)) {
                        break;
                    }
                }
                return true;
            }
            this.m++;
            this.n = i + 1;
            this.k++;
        }
    }

    public final IOException f(String str) throws IOException {
        throw new e(str + v());
    }

    public void f() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + t() + v());
        }
        int i2 = this.t - 1;
        this.t = i2;
        int[] iArr = this.v;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.o = 0;
    }

    public final String g(char c) throws IOException {
        char[] cArr = this.j;
        StringBuilder sb = new StringBuilder();
        do {
            int i = this.k;
            int i2 = this.l;
            int i3 = i;
            while (i < i2) {
                int i4 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.k = i4;
                    sb.append(cArr, i3, (i4 - i3) - 1);
                    return sb.toString();
                } else if (c2 == '\\') {
                    this.k = i4;
                    sb.append(cArr, i3, (i4 - i3) - 1);
                    sb.append(A());
                    i3 = this.k;
                    i2 = this.l;
                    i = i3;
                } else {
                    if (c2 == '\n') {
                        this.m++;
                        this.n = i4;
                    }
                    i = i4;
                }
            }
            sb.append(cArr, i3, i - i3);
            this.k = i;
        } while (d(1));
        throw f("Unterminated string");
    }

    public void g() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + t() + v());
        }
        int i2 = this.t - 1;
        this.t = i2;
        this.u[i2] = null;
        int[] iArr = this.v;
        int i3 = i2 - 1;
        iArr[i3] = iArr[i3] + 1;
        this.o = 0;
    }

    public String h() {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.dollar);
        int i = this.t;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.s[i2];
            if (i3 == 1 || i3 == 2) {
                sb.append('[');
                sb.append(this.v[i2]);
                sb.append(']');
            } else if (i3 == 3 || i3 == 4 || i3 == 5) {
                sb.append('.');
                String[] strArr = this.u;
                if (strArr[i2] != null) {
                    sb.append(strArr[i2]);
                }
            }
        }
        return sb.toString();
    }

    public final void h(int i) {
        int i2 = this.t;
        int[] iArr = this.s;
        if (i2 == iArr.length) {
            int i3 = i2 * 2;
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            String[] strArr = new String[i3];
            System.arraycopy(iArr, 0, iArr2, 0, i2);
            System.arraycopy(this.v, 0, iArr3, 0, this.t);
            System.arraycopy(this.u, 0, strArr, 0, this.t);
            this.s = iArr2;
            this.v = iArr3;
            this.u = strArr;
        }
        int[] iArr4 = this.s;
        int i4 = this.t;
        this.t = i4 + 1;
        iArr4[i4] = i;
    }

    public boolean i() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    public final boolean j() {
        return this.i;
    }

    public final void k() throws IOException {
        if (!this.i) {
            throw f("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    public final void l(char c) throws IOException {
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
                        A();
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
                    if (!d(1)) {
                        throw f("Unterminated string");
                    }
                }
            }
        }
    }

    public boolean l() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 5) {
            this.o = 0;
            int[] iArr = this.v;
            int i2 = this.t - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.o = 0;
            int[] iArr2 = this.v;
            int i3 = this.t - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new IllegalStateException("Expected a boolean but was " + t() + v());
        }
    }

    public double m() throws IOException {
        String g;
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 15) {
            this.o = 0;
            int[] iArr = this.v;
            int i2 = this.t - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.p;
        }
        if (i == 16) {
            this.r = new String(this.j, this.k, this.q);
            this.k += this.q;
        } else {
            if (i == 8 || i == 9) {
                g = g(i == 8 ? '\'' : Typography.quote);
            } else if (i == 10) {
                g = w();
            } else if (i != 11) {
                throw new IllegalStateException("Expected a double but was " + t() + v());
            }
            this.r = g;
        }
        this.o = 11;
        double parseDouble = Double.parseDouble(this.r);
        if (!this.i && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            throw new e("JSON forbids NaN and infinities: " + parseDouble + v());
        }
        this.r = null;
        this.o = 0;
        int[] iArr2 = this.v;
        int i3 = this.t - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseDouble;
    }

    public int n() throws IOException {
        String g;
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 15) {
            long j = this.p;
            int i2 = (int) j;
            if (j == i2) {
                this.o = 0;
                int[] iArr = this.v;
                int i3 = this.t - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new NumberFormatException("Expected an int but was " + this.p + v());
        }
        if (i == 16) {
            this.r = new String(this.j, this.k, this.q);
            this.k += this.q;
        } else if (i != 8 && i != 9 && i != 10) {
            throw new IllegalStateException("Expected an int but was " + t() + v());
        } else {
            if (i == 10) {
                g = w();
            } else {
                g = g(i == 8 ? '\'' : Typography.quote);
            }
            this.r = g;
            try {
                int parseInt = Integer.parseInt(this.r);
                this.o = 0;
                int[] iArr2 = this.v;
                int i4 = this.t - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        }
        this.o = 11;
        double parseDouble = Double.parseDouble(this.r);
        int i5 = (int) parseDouble;
        if (i5 != parseDouble) {
            throw new NumberFormatException("Expected an int but was " + this.r + v());
        }
        this.r = null;
        this.o = 0;
        int[] iArr3 = this.v;
        int i6 = this.t - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public long o() throws IOException {
        String g;
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 15) {
            this.o = 0;
            int[] iArr = this.v;
            int i2 = this.t - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.p;
        }
        if (i == 16) {
            this.r = new String(this.j, this.k, this.q);
            this.k += this.q;
        } else if (i != 8 && i != 9 && i != 10) {
            throw new IllegalStateException("Expected a long but was " + t() + v());
        } else {
            if (i == 10) {
                g = w();
            } else {
                g = g(i == 8 ? '\'' : Typography.quote);
            }
            this.r = g;
            try {
                long parseLong = Long.parseLong(this.r);
                this.o = 0;
                int[] iArr2 = this.v;
                int i3 = this.t - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        this.o = 11;
        double parseDouble = Double.parseDouble(this.r);
        long j = (long) parseDouble;
        if (j != parseDouble) {
            throw new NumberFormatException("Expected a long but was " + this.r + v());
        }
        this.r = null;
        this.o = 0;
        int[] iArr3 = this.v;
        int i4 = this.t - 1;
        iArr3[i4] = iArr3[i4] + 1;
        return j;
    }

    public String p() throws IOException {
        char c;
        String g;
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 14) {
            g = w();
        } else {
            if (i == 12) {
                c = '\'';
            } else if (i != 13) {
                throw new IllegalStateException("Expected a name but was " + t() + v());
            } else {
                c = Typography.quote;
            }
            g = g(c);
        }
        this.o = 0;
        this.u[this.t - 1] = g;
        return g;
    }

    public void q() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 7) {
            this.o = 0;
            int[] iArr = this.v;
            int i2 = this.t - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException("Expected null but was " + t() + v());
    }

    public String r() throws IOException {
        String str;
        char c;
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        if (i == 10) {
            str = w();
        } else {
            if (i == 8) {
                c = '\'';
            } else if (i == 9) {
                c = Typography.quote;
            } else if (i == 11) {
                str = this.r;
                this.r = null;
            } else if (i == 15) {
                str = Long.toString(this.p);
            } else if (i != 16) {
                throw new IllegalStateException("Expected a string but was " + t() + v());
            } else {
                str = new String(this.j, this.k, this.q);
                this.k += this.q;
            }
            str = g(c);
        }
        this.o = 0;
        int[] iArr = this.v;
        int i2 = this.t - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public final void s() throws IOException {
        a(true);
        int i = this.k - 1;
        this.k = i;
        char[] cArr = w;
        if (i + cArr.length > this.l && !d(cArr.length)) {
            return;
        }
        int i2 = 0;
        while (true) {
            char[] cArr2 = w;
            if (i2 >= cArr2.length) {
                this.k += cArr2.length;
                return;
            } else if (this.j[this.k + i2] != cArr2[i2]) {
                return;
            } else {
                i2++;
            }
        }
    }

    public c t() throws IOException {
        int i = this.o;
        if (i == 0) {
            i = u();
        }
        switch (i) {
            case 1:
                return c.BEGIN_OBJECT;
            case 2:
                return c.END_OBJECT;
            case 3:
                return c.BEGIN_ARRAY;
            case 4:
                return c.END_ARRAY;
            case 5:
            case 6:
                return c.BOOLEAN;
            case 7:
                return c.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return c.STRING;
            case 12:
            case 13:
            case 14:
                return c.NAME;
            case 15:
            case 16:
                return c.NUMBER;
            case 17:
                return c.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + v();
    }

    public int u() throws IOException {
        int i;
        int a2;
        int[] iArr = this.s;
        int i2 = this.t - 1;
        int i3 = iArr[i2];
        if (i3 == 1) {
            iArr[i2] = 2;
        } else if (i3 != 2) {
            if (i3 == 3 || i3 == 5) {
                iArr[i2] = 4;
                if (i3 == 5 && (a2 = a(true)) != 44) {
                    if (a2 != 59) {
                        if (a2 == 125) {
                            this.o = 2;
                            return 2;
                        }
                        throw f("Unterminated object");
                    }
                    k();
                }
                int a3 = a(true);
                if (a3 == 34) {
                    i = 13;
                } else if (a3 == 39) {
                    k();
                    i = 12;
                } else if (a3 == 125) {
                    if (i3 != 5) {
                        this.o = 2;
                        return 2;
                    }
                    throw f("Expected name");
                } else {
                    k();
                    this.k--;
                    if (!c((char) a3)) {
                        throw f("Expected name");
                    }
                    i = 14;
                }
            } else if (i3 == 4) {
                iArr[i2] = 5;
                int a4 = a(true);
                if (a4 != 58) {
                    if (a4 != 61) {
                        throw f("Expected ':'");
                    }
                    k();
                    if (this.k < this.l || d(1)) {
                        char[] cArr = this.j;
                        int i4 = this.k;
                        if (cArr[i4] == '>') {
                            this.k = i4 + 1;
                        }
                    }
                }
            } else if (i3 == 6) {
                if (this.i) {
                    s();
                }
                this.s[this.t - 1] = 7;
            } else if (i3 == 7) {
                if (a(false) == -1) {
                    i = 17;
                } else {
                    k();
                    this.k--;
                }
            } else if (i3 == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
            this.o = i;
            return i;
        } else {
            int a5 = a(true);
            if (a5 != 44) {
                if (a5 != 59) {
                    if (a5 == 93) {
                        this.o = 4;
                        return 4;
                    }
                    throw f("Unterminated array");
                }
                k();
            }
        }
        int a6 = a(true);
        if (a6 != 34) {
            if (a6 == 39) {
                k();
                this.o = 8;
                return 8;
            }
            if (a6 != 44 && a6 != 59) {
                if (a6 == 91) {
                    this.o = 3;
                    return 3;
                } else if (a6 != 93) {
                    if (a6 == 123) {
                        this.o = 1;
                        return 1;
                    }
                    this.k--;
                    int x = x();
                    if (x != 0) {
                        return x;
                    }
                    int y = y();
                    if (y != 0) {
                        return y;
                    }
                    if (!c(this.j[this.k])) {
                        throw f("Expected value");
                    }
                    k();
                    i = 10;
                } else if (i3 == 1) {
                    this.o = 4;
                    return 4;
                }
            }
            if (i3 == 1 || i3 == 2) {
                k();
                this.k--;
                this.o = 7;
                return 7;
            }
            throw f("Unexpected value");
        }
        i = 9;
        this.o = i;
        return i;
    }

    public final String v() {
        return " at line " + (this.m + 1) + " column " + ((this.k - this.n) + 1) + " path " + h();
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0048, code lost:
        k();
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String w() throws java.io.IOException {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
        L2:
            r2 = r0
        L3:
            int r3 = r5.k
            int r3 = r3 + r2
            int r4 = r5.l
            if (r3 >= r4) goto L4c
            char[] r4 = r5.j
            char r3 = r4[r3]
            r4 = 9
            if (r3 == r4) goto L5a
            r4 = 10
            if (r3 == r4) goto L5a
            r4 = 12
            if (r3 == r4) goto L5a
            r4 = 13
            if (r3 == r4) goto L5a
            r4 = 32
            if (r3 == r4) goto L5a
            r4 = 35
            if (r3 == r4) goto L48
            r4 = 44
            if (r3 == r4) goto L5a
            r4 = 47
            if (r3 == r4) goto L48
            r4 = 61
            if (r3 == r4) goto L48
            r4 = 123(0x7b, float:1.72E-43)
            if (r3 == r4) goto L5a
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 == r4) goto L5a
            r4 = 58
            if (r3 == r4) goto L5a
            r4 = 59
            if (r3 == r4) goto L48
            switch(r3) {
                case 91: goto L5a;
                case 92: goto L48;
                case 93: goto L5a;
                default: goto L45;
            }
        L45:
            int r2 = r2 + 1
            goto L3
        L48:
            r5.k()
            goto L5a
        L4c:
            char[] r3 = r5.j
            int r3 = r3.length
            if (r2 >= r3) goto L5c
            int r3 = r2 + 1
            boolean r3 = r5.d(r3)
            if (r3 == 0) goto L5a
            goto L3
        L5a:
            r0 = r2
            goto L76
        L5c:
            if (r1 != 0) goto L63
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
        L63:
            char[] r3 = r5.j
            int r4 = r5.k
            r1.append(r3, r4, r2)
            int r3 = r5.k
            int r3 = r3 + r2
            r5.k = r3
            r2 = 1
            boolean r2 = r5.d(r2)
            if (r2 != 0) goto L2
        L76:
            if (r1 != 0) goto L82
            java.lang.String r1 = new java.lang.String
            char[] r2 = r5.j
            int r3 = r5.k
            r1.<init>(r2, r3, r0)
            goto L8d
        L82:
            char[] r2 = r5.j
            int r3 = r5.k
            r1.append(r2, r3, r0)
            java.lang.String r1 = r1.toString()
        L8d:
            int r2 = r5.k
            int r2 = r2 + r0
            r5.k = r2
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.t0.a.w():java.lang.String");
    }

    public final int x() throws IOException {
        int i;
        String str;
        String str2;
        char c = this.j[this.k];
        if (c == 't' || c == 'T') {
            i = 5;
            str = "true";
            str2 = "TRUE";
        } else if (c == 'f' || c == 'F') {
            i = 6;
            str = "false";
            str2 = "FALSE";
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            i = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.k + i2 >= this.l && !d(i2 + 1)) {
                return 0;
            }
            char c2 = this.j[this.k + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.k + length < this.l || d(length + 1)) && c(this.j[this.k + length])) {
            return 0;
        }
        this.k += length;
        this.o = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:54:0x0093, code lost:
        if (c(r14) != false) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0095, code lost:
        if (r9 != 2) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0097, code lost:
        if (r13 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x009d, code lost:
        if (r10 != Long.MIN_VALUE) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009f, code lost:
        if (r12 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a1, code lost:
        if (r12 == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00a4, code lost:
        r10 = -r10;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00a5, code lost:
        r18.p = r10;
        r18.k += r8;
        r1 = 15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ae, code lost:
        r18.o = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b0, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00b1, code lost:
        if (r9 == 2) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b4, code lost:
        if (r9 == 4) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b7, code lost:
        if (r9 != 7) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x00ba, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00bc, code lost:
        r18.q = r8;
        r1 = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x00c1, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int y() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 239
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.crrepa.t0.a.y():int");
    }

    public void z() throws IOException {
        char c;
        int i = 0;
        do {
            int i2 = this.o;
            if (i2 == 0) {
                i2 = u();
            }
            if (i2 == 3) {
                h(1);
            } else if (i2 == 1) {
                h(3);
            } else {
                if (i2 == 4 || i2 == 2) {
                    this.t--;
                    i--;
                } else if (i2 == 14 || i2 == 10) {
                    D();
                } else {
                    if (i2 == 8 || i2 == 12) {
                        c = '\'';
                    } else if (i2 == 9 || i2 == 13) {
                        c = Typography.quote;
                    } else if (i2 == 16) {
                        this.k += this.q;
                    }
                    l(c);
                }
                this.o = 0;
            }
            i++;
            this.o = 0;
        } while (i != 0);
        int[] iArr = this.v;
        int i3 = this.t - 1;
        iArr[i3] = iArr[i3] + 1;
        this.u[i3] = "null";
    }
}
