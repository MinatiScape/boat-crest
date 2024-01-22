package org.jose4j.json.internal.json_simple.parser;

import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class a {
    public static final int[] l = {0, 0, 1, 1};
    public static final char[] m = m("\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016ﾂ\u0000");
    public static final int[] n = j();
    public static final int[] o = o();
    public static final int[] p = {2, 2, 3, 4, 2, 2, 2, 5, 2, 6, 2, 2, 7, 8, 2, 9, 2, 2, 2, 2, 2, 10, 11, 12, 13, 14, 15, 16, 16, 16, 16, 16, 16, 16, 16, 17, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, 19, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 16, 16, 16, 16, 16, 16, 16, 16, -1, -1, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, -1, -1, -1, -1, -1, -1, -1, -1, 24, 25, 26, 27, 28, 29, 30, 31, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, 35, -1, -1, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 39, -1, 39, -1, 39, -1, -1, -1, -1, -1, 39, 39, -1, -1, -1, -1, 39, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 42, -1, 42, -1, 42, -1, -1, -1, -1, -1, 42, 42, -1, -1, -1, -1, 42, 42, -1, -1, -1, -1, -1, -1, -1, -1, -1, 43, -1, 43, -1, 43, -1, -1, -1, -1, -1, 43, 43, -1, -1, -1, -1, 43, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1, 44, -1, 44, -1, 44, -1, -1, -1, -1, -1, 44, 44, -1, -1, -1, -1, 44, 44, -1, -1, -1, -1, -1, -1, -1, -1};
    public static final String[] q = {"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
    public static final int[] r = l();

    /* renamed from: a  reason: collision with root package name */
    public Reader f15506a;
    public int b;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public boolean j;
    public int c = 0;
    public char[] d = new char[16384];
    public StringBuilder k = new StringBuilder();

    public a(Reader reader) {
        this.f15506a = reader;
    }

    public static int i(String str, int i, int[] iArr) {
        int i2;
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            int charAt = str.charAt(i3);
            int i5 = i4 + 1;
            char charAt2 = str.charAt(i4);
            while (true) {
                i2 = i + 1;
                iArr[i] = charAt2;
                charAt--;
                if (charAt <= 0) {
                    break;
                }
                i = i2;
            }
            i3 = i5;
            i = i2;
        }
        return i;
    }

    public static int[] j() {
        int[] iArr = new int[45];
        i("\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018", 0, iArr);
        return iArr;
    }

    public static int k(String str, int i, int[] iArr) {
        int i2;
        int length = str.length();
        int i3 = 0;
        while (i3 < length) {
            int i4 = i3 + 1;
            int charAt = str.charAt(i3);
            int i5 = i4 + 1;
            char charAt2 = str.charAt(i4);
            while (true) {
                i2 = i + 1;
                iArr[i] = charAt2;
                charAt--;
                if (charAt <= 0) {
                    break;
                }
                i = i2;
            }
            i3 = i5;
            i = i2;
        }
        return i;
    }

    public static int[] l() {
        int[] iArr = new int[45];
        k("\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t", 0, iArr);
        return iArr;
    }

    public static char[] m(String str) {
        int i;
        char[] cArr = new char[65536];
        int i2 = 0;
        int i3 = 0;
        while (i2 < 90) {
            int i4 = i2 + 1;
            int charAt = str.charAt(i2);
            int i5 = i4 + 1;
            char charAt2 = str.charAt(i4);
            while (true) {
                i = i3 + 1;
                cArr[i3] = charAt2;
                charAt--;
                if (charAt <= 0) {
                    break;
                }
                i3 = i;
            }
            i2 = i5;
            i3 = i;
        }
        return cArr;
    }

    public static int n(String str, int i, int[] iArr) {
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            int i3 = i2 + 1;
            iArr[i] = (str.charAt(i2) << 16) | str.charAt(i3);
            i++;
            i2 = i3 + 1;
        }
        return i;
    }

    public static int[] o() {
        int[] iArr = new int[45];
        n("\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000¢\u0000½\u0000Ø\u00006\u00006\u00006\u00006\u00006\u00006\u0000ó\u0000Ď\u00006\u0000ĩ\u0000ń\u0000ş\u0000ź\u0000ƕ\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000ư\u0000ǋ\u0000Ǧ\u0000Ǧ\u0000ȁ\u0000Ȝ\u0000ȷ\u0000ɒ\u00006\u00006\u0000ɭ\u0000ʈ\u00006", 0, iArr);
        return iArr;
    }

    public int a() {
        return this.i;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final char c(int i) {
        return this.d[this.g + i];
    }

    public Yytoken d() throws IOException, ParseException {
        char c;
        int i;
        int i2 = this.h;
        char[] cArr = this.d;
        char[] cArr2 = m;
        int[] iArr = p;
        int[] iArr2 = o;
        int[] iArr3 = r;
        while (true) {
            int i3 = this.e;
            this.i += i3 - this.g;
            this.g = i3;
            this.f = i3;
            this.b = l[this.c];
            char c2 = 65535;
            int i4 = i3;
            int i5 = -1;
            while (true) {
                if (i3 < i2) {
                    i = i3 + 1;
                    c = cArr[i3];
                } else if (this.j) {
                    c = c2;
                } else {
                    this.f = i3;
                    this.e = i4;
                    boolean g = g();
                    int i6 = this.f;
                    i4 = this.e;
                    char[] cArr3 = this.d;
                    int i7 = this.h;
                    if (g) {
                        cArr = cArr3;
                        c = c2;
                        i2 = i7;
                    } else {
                        i = i6 + 1;
                        i2 = i7;
                        c = cArr3[i6];
                        cArr = cArr3;
                    }
                }
                int i8 = iArr[iArr2[this.b] + cArr2[c]];
                if (i8 != c2) {
                    this.b = i8;
                    int i9 = iArr3[i8];
                    if ((i9 & 1) != 1) {
                        c2 = 65535;
                    } else if ((i9 & 8) == 8) {
                        i4 = i;
                        i5 = i8;
                    } else {
                        c2 = 65535;
                        i4 = i;
                        i5 = i8;
                    }
                    i3 = i;
                }
            }
            this.e = i4;
            if (i5 >= 0) {
                i5 = n[i5];
            }
            switch (i5) {
                case 1:
                    throw new ParseException(this.i, 0, new Character(c(0)));
                case 2:
                    String f = f();
                    try {
                        return new Yytoken(0, Long.valueOf(f));
                    } catch (NumberFormatException unused) {
                        return new Yytoken(0, new BigInteger(f));
                    }
                case 3:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                    break;
                case 4:
                    this.k = null;
                    this.k = new StringBuilder();
                    b(2);
                    break;
                case 5:
                    return new Yytoken(1, null);
                case 6:
                    return new Yytoken(2, null);
                case 7:
                    return new Yytoken(3, null);
                case 8:
                    return new Yytoken(4, null);
                case 9:
                    return new Yytoken(5, null);
                case 10:
                    return new Yytoken(6, null);
                case 11:
                    this.k.append(f());
                    break;
                case 12:
                    this.k.append('\\');
                    break;
                case 13:
                    b(0);
                    return new Yytoken(0, this.k.toString());
                case 14:
                    this.k.append(Typography.quote);
                    break;
                case 15:
                    this.k.append('/');
                    break;
                case 16:
                    this.k.append('\b');
                    break;
                case 17:
                    this.k.append('\f');
                    break;
                case 18:
                    this.k.append('\n');
                    break;
                case 19:
                    this.k.append('\r');
                    break;
                case 20:
                    this.k.append('\t');
                    break;
                case 21:
                    return new Yytoken(0, Double.valueOf(f()));
                case 22:
                    return new Yytoken(0, null);
                case 23:
                    return new Yytoken(0, Boolean.valueOf(f()));
                case 24:
                    try {
                        this.k.append((char) Integer.parseInt(f().substring(2), 16));
                        break;
                    } catch (Exception e) {
                        throw new ParseException(this.i, 2, e);
                    }
                default:
                    if (c == 65535 && this.g == this.f) {
                        this.j = true;
                        return null;
                    }
                    h(1);
                    break;
                    break;
            }
        }
    }

    public final void e(Reader reader) {
        this.f15506a = reader;
        this.j = false;
        this.g = 0;
        this.h = 0;
        this.e = 0;
        this.f = 0;
        this.i = 0;
        this.c = 0;
    }

    public final String f() {
        char[] cArr = this.d;
        int i = this.g;
        return new String(cArr, i, this.e - i);
    }

    public final boolean g() throws IOException {
        int read;
        int i = this.g;
        if (i > 0) {
            char[] cArr = this.d;
            System.arraycopy(cArr, i, cArr, 0, this.h - i);
            int i2 = this.h;
            int i3 = this.g;
            this.h = i2 - i3;
            this.f -= i3;
            this.e -= i3;
            this.g = 0;
        }
        int i4 = this.f;
        char[] cArr2 = this.d;
        if (i4 >= cArr2.length) {
            char[] cArr3 = new char[i4 * 2];
            System.arraycopy(cArr2, 0, cArr3, 0, cArr2.length);
            this.d = cArr3;
        }
        Reader reader = this.f15506a;
        char[] cArr4 = this.d;
        int i5 = this.h;
        int read2 = reader.read(cArr4, i5, cArr4.length - i5);
        if (read2 > 0) {
            this.h += read2;
            return false;
        } else if (read2 != 0 || (read = this.f15506a.read()) == -1) {
            return true;
        } else {
            char[] cArr5 = this.d;
            int i6 = this.h;
            this.h = i6 + 1;
            cArr5[i6] = (char) read;
            return false;
        }
    }

    public final void h(int i) {
        String str;
        try {
            str = q[i];
        } catch (ArrayIndexOutOfBoundsException unused) {
            str = q[0];
        }
        throw new Error(str);
    }
}
