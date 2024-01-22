package org.apache.commons.codec.language;

import java.util.Locale;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
/* loaded from: classes12.dex */
public class ColognePhonetic implements StringEncoder {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f14347a = {'A', 'E', 'I', 'J', 'O', Matrix.MATRIX_TYPE_RANDOM_UT, 'Y'};
    public static final char[] b = {'C', 'S', Matrix.MATRIX_TYPE_ZERO};
    public static final char[] c = {'F', 'P', 'V', 'W'};
    public static final char[] d = {'G', 'K', 'Q'};
    public static final char[] e = {'C', 'K', 'Q'};
    public static final char[] f = {'A', 'H', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'O', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, Matrix.MATRIX_TYPE_RANDOM_UT, 'X'};
    public static final char[] g = {'S', Matrix.MATRIX_TYPE_ZERO};
    public static final char[] h = {'A', 'H', 'K', 'O', 'Q', Matrix.MATRIX_TYPE_RANDOM_UT, 'X'};
    public static final char[] i = {'D', 'T', 'X'};

    /* loaded from: classes12.dex */
    public class b extends a {
        public b(ColognePhonetic colognePhonetic, char[] cArr) {
            super(colognePhonetic, cArr);
        }

        @Override // org.apache.commons.codec.language.ColognePhonetic.a
        public char[] a(int i, int i2) {
            char[] cArr = new char[i2];
            char[] cArr2 = this.f14348a;
            System.arraycopy(cArr2, (cArr2.length - this.b) + i, cArr, 0, i2);
            return cArr;
        }

        public char c() {
            return this.f14348a[d()];
        }

        public int d() {
            return this.f14348a.length - this.b;
        }

        public char e() {
            this.b--;
            return c();
        }
    }

    /* loaded from: classes12.dex */
    public class c extends a {
        public char c;

        public c(ColognePhonetic colognePhonetic, int i) {
            super(colognePhonetic, i);
            this.c = '/';
        }

        @Override // org.apache.commons.codec.language.ColognePhonetic.a
        public char[] a(int i, int i2) {
            char[] cArr = new char[i2];
            System.arraycopy(this.f14348a, i, cArr, 0, i2);
            return cArr;
        }

        public void c(char c) {
            if (c != '-' && this.c != c && (c != '0' || this.b == 0)) {
                char[] cArr = this.f14348a;
                int i = this.b;
                cArr[i] = c;
                this.b = i + 1;
            }
            this.c = c;
        }
    }

    public static boolean a(char[] cArr, char c2) {
        for (char c3 : cArr) {
            if (c3 == c2) {
                return true;
            }
        }
        return false;
    }

    public final char[] b(String str) {
        char[] charArray = str.toUpperCase(Locale.GERMAN).toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            char c2 = charArray[i2];
            if (c2 == 196) {
                charArray[i2] = 'A';
            } else if (c2 == 214) {
                charArray[i2] = 'O';
            } else if (c2 == 220) {
                charArray[i2] = Matrix.MATRIX_TYPE_RANDOM_UT;
            }
        }
        return charArray;
    }

    public String colognePhonetic(String str) {
        if (str == null) {
            return null;
        }
        b bVar = new b(this, b(str));
        c cVar = new c(this, bVar.b() * 2);
        char c2 = '-';
        while (bVar.b() > 0) {
            char e2 = bVar.e();
            char c3 = bVar.b() > 0 ? bVar.c() : '-';
            if (e2 >= 'A' && e2 <= 'Z') {
                if (a(f14347a, e2)) {
                    cVar.c('0');
                } else if (e2 != 'B' && (e2 != 'P' || c3 == 'H')) {
                    if ((e2 == 'D' || e2 == 'T') && !a(b, c3)) {
                        cVar.c('2');
                    } else if (a(c, e2)) {
                        cVar.c('3');
                    } else if (a(d, e2)) {
                        cVar.c('4');
                    } else if (e2 == 'X' && !a(e, c2)) {
                        cVar.c('4');
                        cVar.c('8');
                    } else if (e2 == 'S' || e2 == 'Z') {
                        cVar.c('8');
                    } else if (e2 == 'C') {
                        if (cVar.b() == 0) {
                            if (a(f, c3)) {
                                cVar.c('4');
                            } else {
                                cVar.c('8');
                            }
                        } else if (!a(g, c2) && a(h, c3)) {
                            cVar.c('4');
                        } else {
                            cVar.c('8');
                        }
                    } else if (a(i, e2)) {
                        cVar.c('8');
                    } else if (e2 == 'R') {
                        cVar.c('7');
                    } else if (e2 == 'L') {
                        cVar.c('5');
                    } else if (e2 == 'M' || e2 == 'N') {
                        cVar.c('6');
                    } else if (e2 == 'H') {
                        cVar.c(Soundex.SILENT_MARKER);
                    }
                } else {
                    cVar.c('1');
                }
                c2 = e2;
            }
        }
        return cVar.toString();
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("This method's parameter was expected to be of the type " + String.class.getName() + ". But actually it was of the type " + obj.getClass().getName() + ".");
    }

    public boolean isEncodeEqual(String str, String str2) {
        return colognePhonetic(str).equals(colognePhonetic(str2));
    }

    /* loaded from: classes12.dex */
    public abstract class a {

        /* renamed from: a  reason: collision with root package name */
        public final char[] f14348a;
        public int b;

        public a(ColognePhonetic colognePhonetic, char[] cArr) {
            this.b = 0;
            this.f14348a = cArr;
            this.b = cArr.length;
        }

        public abstract char[] a(int i, int i2);

        public int b() {
            return this.b;
        }

        public String toString() {
            return new String(a(0, this.b));
        }

        public a(ColognePhonetic colognePhonetic, int i) {
            this.b = 0;
            this.f14348a = new char[i];
            this.b = 0;
        }
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        return colognePhonetic(str);
    }
}
