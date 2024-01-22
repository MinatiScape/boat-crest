package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.blankj.utilcode.constant.CacheConstants;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.text.Typography;
import okio.internal._BufferKt;
/* loaded from: classes.dex */
public final class JSONLexer {
    public static final char[] CA;
    public static final int END = 4;
    public static final char EOI = 26;
    public static final int NOT_MATCH = -1;
    public static final int NOT_MATCH_NAME = -2;
    public static final int UNKNOWN = 0;
    public static final int VALUE = 3;

    /* renamed from: a  reason: collision with root package name */
    public static boolean f2117a;
    public static final ThreadLocal<char[]> b;
    public static final int[] c;
    public static final int[] digits;
    public static final boolean[] firstIdentifierFlags;
    public static final boolean[] identifierFlags;
    public int bp;
    public Calendar calendar;
    public char ch;
    public boolean disableCircularReferenceDetect;
    public int eofPos;
    public boolean exp;
    public int features;
    public long fieldHash;
    public boolean hasSpecial;
    public boolean isDouble;
    public final int len;
    public Locale locale;
    public int matchStat;
    public int np;
    public int pos;
    public char[] sbuf;
    public int sp;
    public String stringDefaultValue;
    public final String text;
    public TimeZone timeZone;
    public int token;

    static {
        int i;
        try {
            i = Class.forName("android.os.Build$VERSION").getField("SDK_INT").getInt(null);
        } catch (Exception unused) {
            i = -1;
        }
        char c2 = 0;
        f2117a = i >= 23;
        b = new ThreadLocal<>();
        digits = new int[103];
        for (int i2 = 48; i2 <= 57; i2++) {
            digits[i2] = i2 - 48;
        }
        for (int i3 = 97; i3 <= 102; i3++) {
            digits[i3] = (i3 - 97) + 10;
        }
        for (int i4 = 65; i4 <= 70; i4++) {
            digits[i4] = (i4 - 65) + 10;
        }
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        CA = charArray;
        int[] iArr = new int[256];
        c = iArr;
        Arrays.fill(iArr, -1);
        int length = charArray.length;
        for (int i5 = 0; i5 < length; i5++) {
            c[CA[i5]] = i5;
        }
        c[61] = 0;
        firstIdentifierFlags = new boolean[256];
        char c3 = 0;
        while (true) {
            boolean[] zArr = firstIdentifierFlags;
            if (c3 >= zArr.length) {
                break;
            }
            if (c3 >= 'A' && c3 <= 'Z') {
                zArr[c3] = true;
            } else if (c3 >= 'a' && c3 <= 'z') {
                zArr[c3] = true;
            } else if (c3 == '_') {
                zArr[c3] = true;
            }
            c3 = (char) (c3 + 1);
        }
        identifierFlags = new boolean[256];
        while (true) {
            boolean[] zArr2 = identifierFlags;
            if (c2 >= zArr2.length) {
                return;
            }
            if (c2 >= 'A' && c2 <= 'Z') {
                zArr2[c2] = true;
            } else if (c2 >= 'a' && c2 <= 'z') {
                zArr2[c2] = true;
            } else if (c2 == '_') {
                zArr2[c2] = true;
            } else if (c2 >= '0' && c2 <= '9') {
                zArr2[c2] = true;
            }
            c2 = (char) (c2 + 1);
        }
    }

    public JSONLexer(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public static boolean a(char c2, char c3, char c4, char c5, char c6, char c7, int i, int i2) {
        if (c2 >= '1' && c2 <= '3' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9') {
            if (c6 == '0') {
                if (c7 < '1' || c7 > '9') {
                    return false;
                }
            } else if (c6 != '1' || (c7 != '0' && c7 != '1' && c7 != '2')) {
                return false;
            }
            if (i == 48) {
                return i2 >= 49 && i2 <= 57;
            } else if (i != 49 && i != 50) {
                return i == 51 && (i2 == 48 || i2 == 49);
            } else if (i2 >= 48 && i2 <= 57) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x001d, code lost:
        if (r5 <= '4') goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(char r4, char r5, char r6, char r7, char r8, char r9) {
        /*
            r0 = 57
            r1 = 0
            r2 = 48
            if (r4 != r2) goto Lc
            if (r5 < r2) goto Lb
            if (r5 <= r0) goto L20
        Lb:
            return r1
        Lc:
            r3 = 49
            if (r4 != r3) goto L15
            if (r5 < r2) goto L14
            if (r5 <= r0) goto L20
        L14:
            return r1
        L15:
            r3 = 50
            if (r4 != r3) goto L42
            if (r5 < r2) goto L42
            r4 = 52
            if (r5 <= r4) goto L20
            goto L42
        L20:
            r4 = 53
            r5 = 54
            if (r6 < r2) goto L2d
            if (r6 > r4) goto L2d
            if (r7 < r2) goto L2c
            if (r7 <= r0) goto L32
        L2c:
            return r1
        L2d:
            if (r6 != r5) goto L42
            if (r7 == r2) goto L32
            return r1
        L32:
            if (r8 < r2) goto L3b
            if (r8 > r4) goto L3b
            if (r9 < r2) goto L3a
            if (r9 <= r0) goto L40
        L3a:
            return r1
        L3b:
            if (r8 != r5) goto L42
            if (r9 == r2) goto L40
            return r1
        L40:
            r4 = 1
            return r4
        L42:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.b(char, char, char, char, char, char):boolean");
    }

    public static String d(char[] cArr, int i) {
        int i2;
        char[] cArr2 = new char[i];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            char c2 = cArr[i3];
            if (c2 != '\\') {
                cArr2[i4] = c2;
                i4++;
            } else {
                i3++;
                char c3 = cArr[i3];
                if (c3 == '\"') {
                    i2 = i4 + 1;
                    cArr2[i4] = Typography.quote;
                } else if (c3 != '\'') {
                    if (c3 != 'F') {
                        if (c3 == '\\') {
                            i2 = i4 + 1;
                            cArr2[i4] = '\\';
                        } else if (c3 == 'b') {
                            i2 = i4 + 1;
                            cArr2[i4] = '\b';
                        } else if (c3 != 'f') {
                            if (c3 == 'n') {
                                i2 = i4 + 1;
                                cArr2[i4] = '\n';
                            } else if (c3 == 'r') {
                                i2 = i4 + 1;
                                cArr2[i4] = '\r';
                            } else if (c3 != 'x') {
                                switch (c3) {
                                    case '/':
                                        i2 = i4 + 1;
                                        cArr2[i4] = '/';
                                        break;
                                    case '0':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 0;
                                        break;
                                    case '1':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 1;
                                        break;
                                    case '2':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 2;
                                        break;
                                    case '3':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 3;
                                        break;
                                    case '4':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 4;
                                        break;
                                    case '5':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 5;
                                        break;
                                    case '6':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 6;
                                        break;
                                    case '7':
                                        i2 = i4 + 1;
                                        cArr2[i4] = 7;
                                        break;
                                    default:
                                        switch (c3) {
                                            case 't':
                                                i2 = i4 + 1;
                                                cArr2[i4] = '\t';
                                                break;
                                            case 'u':
                                                i2 = i4 + 1;
                                                int i5 = i3 + 1;
                                                int i6 = i5 + 1;
                                                int i7 = i6 + 1;
                                                i3 = i7 + 1;
                                                cArr2[i4] = (char) Integer.parseInt(new String(new char[]{cArr[i5], cArr[i6], cArr[i7], cArr[i3]}), 16);
                                                break;
                                            case 'v':
                                                i2 = i4 + 1;
                                                cArr2[i4] = 11;
                                                break;
                                            default:
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                }
                            } else {
                                i2 = i4 + 1;
                                int[] iArr = digits;
                                int i8 = i3 + 1;
                                i3 = i8 + 1;
                                cArr2[i4] = (char) ((iArr[cArr[i8]] * 16) + iArr[cArr[i3]]);
                            }
                        }
                    }
                    i2 = i4 + 1;
                    cArr2[i4] = '\f';
                } else {
                    i2 = i4 + 1;
                    cArr2[i4] = '\'';
                }
                i4 = i2;
            }
            i3++;
        }
        return new String(cArr2, 0, i4);
    }

    public static final byte[] decodeFast(String str, int i, int i2) {
        int i3;
        int i4 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i5 = (i + i2) - 1;
        while (i < i5 && c[str.charAt(i)] < 0) {
            i++;
        }
        while (i5 > 0 && c[str.charAt(i5)] < 0) {
            i5--;
        }
        int i6 = str.charAt(i5) == '=' ? str.charAt(i5 + (-1)) == '=' ? 2 : 1 : 0;
        int i7 = (i5 - i) + 1;
        if (i2 > 76) {
            i3 = (str.charAt(76) == '\r' ? i7 / 78 : 0) << 1;
        } else {
            i3 = 0;
        }
        int i8 = (((i7 - i3) * 6) >> 3) - i6;
        byte[] bArr = new byte[i8];
        int i9 = (i8 / 3) * 3;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i9) {
            int[] iArr = c;
            int i12 = i + 1;
            int i13 = i12 + 1;
            int i14 = (iArr[str.charAt(i)] << 18) | (iArr[str.charAt(i12)] << 12);
            int i15 = i13 + 1;
            int i16 = i14 | (iArr[str.charAt(i13)] << 6);
            int i17 = i15 + 1;
            int i18 = i16 | iArr[str.charAt(i15)];
            int i19 = i10 + 1;
            bArr[i10] = (byte) (i18 >> 16);
            int i20 = i19 + 1;
            bArr[i19] = (byte) (i18 >> 8);
            int i21 = i20 + 1;
            bArr[i20] = (byte) i18;
            if (i3 > 0 && (i11 = i11 + 1) == 19) {
                i17 += 2;
                i11 = 0;
            }
            i = i17;
            i10 = i21;
        }
        if (i10 < i8) {
            int i22 = 0;
            while (i <= i5 - i6) {
                i4 |= c[str.charAt(i)] << (18 - (i22 * 6));
                i22++;
                i++;
            }
            int i23 = 16;
            while (i10 < i8) {
                bArr[i10] = (byte) (i4 >> i23);
                i23 -= 8;
                i10++;
            }
        }
        return bArr;
    }

    public byte[] bytesValue() {
        return decodeFast(this.text, this.np + 1, this.sp);
    }

    public final int c(long j) {
        char c2 = this.ch;
        int i = 1;
        while (c2 != '\"' && c2 != '\'') {
            if (c2 != ' ' && c2 != '\n' && c2 != '\r' && c2 != '\t' && c2 != '\f' && c2 != '\b') {
                this.fieldHash = 0L;
                this.matchStat = -2;
                return 0;
            }
            int i2 = i + 1;
            int i3 = this.bp + i;
            c2 = i3 >= this.len ? EOI : this.text.charAt(i3);
            i = i2;
        }
        long j2 = -3750763034362895579L;
        int i4 = this.bp + i;
        while (true) {
            if (i4 >= this.len) {
                break;
            }
            char charAt = this.text.charAt(i4);
            if (charAt == c2) {
                i += (i4 - this.bp) - i;
                break;
            }
            j2 = 1099511628211L * (charAt ^ j2);
            i4++;
        }
        if (j2 != j) {
            this.fieldHash = j2;
            this.matchStat = -2;
            return 0;
        }
        int i5 = i + 1;
        int i6 = this.bp + i5;
        char charAt2 = i6 >= this.len ? EOI : this.text.charAt(i6);
        while (charAt2 != ':') {
            if (charAt2 <= ' ' && (charAt2 == ' ' || charAt2 == '\n' || charAt2 == '\r' || charAt2 == '\t' || charAt2 == '\f' || charAt2 == '\b')) {
                int i7 = i5 + 1;
                int i8 = this.bp + i5;
                charAt2 = i8 >= this.len ? EOI : this.text.charAt(i8);
                i5 = i7;
            } else {
                throw new JSONException("match feild error expect ':'");
            }
        }
        return i5 + 1;
    }

    public char charAt(int i) {
        return i >= this.len ? EOI : this.text.charAt(i);
    }

    public void close() {
        char[] cArr = this.sbuf;
        if (cArr.length <= 8196) {
            b.set(cArr);
        }
        this.sbuf = null;
    }

    public final void config(Feature feature, boolean z) {
        if (z) {
            this.features |= feature.mask;
        } else {
            this.features &= ~feature.mask;
        }
        if (feature == Feature.InitStringFieldAsEmpty) {
            this.stringDefaultValue = z ? "" : null;
        }
        this.disableCircularReferenceDetect = (this.features & Feature.DisableCircularReferenceDetect.mask) != 0;
    }

    public final Number decimalValue(boolean z) {
        char[] cArr;
        boolean z2;
        int i = (this.np + this.sp) - 1;
        char charAt = i >= this.len ? EOI : this.text.charAt(i);
        try {
            if (charAt == 'F') {
                return Float.valueOf(Float.parseFloat(numberString()));
            }
            if (charAt == 'D') {
                return Double.valueOf(Double.parseDouble(numberString()));
            }
            if (z) {
                return decimalValue();
            }
            char charAt2 = this.text.charAt((this.np + this.sp) - 1);
            int i2 = this.sp;
            if (charAt2 == 'L' || charAt2 == 'S' || charAt2 == 'B' || charAt2 == 'F' || charAt2 == 'D') {
                i2--;
            }
            int i3 = this.np;
            char[] cArr2 = this.sbuf;
            int i4 = 0;
            if (i2 < cArr2.length) {
                this.text.getChars(i3, i3 + i2, cArr2, 0);
                cArr = this.sbuf;
            } else {
                char[] cArr3 = new char[i2];
                this.text.getChars(i3, i3 + i2, cArr3, 0);
                cArr = cArr3;
            }
            if (i2 <= 9 && !this.exp) {
                char c2 = cArr[0];
                int i5 = 2;
                if (c2 == '-') {
                    c2 = cArr[1];
                    z2 = true;
                } else {
                    if (c2 == '+') {
                        c2 = cArr[1];
                    } else {
                        i5 = 1;
                    }
                    z2 = false;
                }
                int i6 = c2 - '0';
                while (i5 < i2) {
                    char c3 = cArr[i5];
                    if (c3 == '.') {
                        i4 = 1;
                    } else {
                        i6 = (i6 * 10) + (c3 - '0');
                        if (i4 != 0) {
                            i4 *= 10;
                        }
                    }
                    i5++;
                }
                double d = i6 / i4;
                if (z2) {
                    d = -d;
                }
                return Double.valueOf(d);
            }
            return Double.valueOf(Double.parseDouble(new String(cArr, 0, i2)));
        } catch (NumberFormatException e) {
            throw new JSONException(e.getMessage() + ", " + info());
        }
    }

    public final void e() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String stringVal = stringVal();
        if (stringVal.equals("null")) {
            this.token = 8;
        } else if (stringVal.equals("true")) {
            this.token = 6;
        } else if (stringVal.equals("false")) {
            this.token = 7;
        } else if (stringVal.equals(AppSettingsData.STATUS_NEW)) {
            this.token = 9;
        } else if (stringVal.equals("undefined")) {
            this.token = 23;
        } else if (stringVal.equals("Set")) {
            this.token = 21;
        } else if (stringVal.equals("TreeSet")) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    public final void f(char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        Calendar calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar = calendar;
        calendar.set(1, ((c2 - '0') * 1000) + ((c3 - '0') * 100) + ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(2, (((c6 - '0') * 10) + (c7 - '0')) - 1);
        this.calendar.set(5, ((c8 - '0') * 10) + (c9 - '0'));
    }

    public final void g() {
        while (true) {
            char c2 = this.ch;
            if (c2 > '/') {
                return;
            }
            if (c2 == ' ' || c2 == '\r' || c2 == '\n' || c2 == '\t' || c2 == '\f' || c2 == '\b') {
                next();
            } else if (c2 != '/') {
                return;
            } else {
                skipComment();
            }
        }
    }

    public final String h(int i, int i2) {
        char[] cArr = this.sbuf;
        if (i2 < cArr.length) {
            this.text.getChars(i, i + i2, cArr, 0);
            return new String(this.sbuf, 0, i2);
        }
        char[] cArr2 = new char[i2];
        this.text.getChars(i, i2 + i, cArr2, 0);
        return new String(cArr2);
    }

    public final char[] i(int i, int i2) {
        char[] cArr = this.sbuf;
        if (i2 < cArr.length) {
            this.text.getChars(i, i2 + i, cArr, 0);
            return this.sbuf;
        }
        char[] cArr2 = new char[i2];
        this.sbuf = cArr2;
        this.text.getChars(i, i2 + i, cArr2, 0);
        return cArr2;
    }

    public String info() {
        StringBuilder sb = new StringBuilder();
        sb.append("pos ");
        sb.append(this.bp);
        sb.append(", json : ");
        sb.append(this.len < 65536 ? this.text : this.text.substring(0, 65536));
        return sb.toString();
    }

    public final int intValue() {
        int i;
        boolean z;
        int i2 = this.np;
        int i3 = this.sp + i2;
        int i4 = 0;
        if ((i2 >= this.len ? (char) 26 : this.text.charAt(i2)) == '-') {
            i = Integer.MIN_VALUE;
            i2++;
            z = true;
        } else {
            i = -2147483647;
            z = false;
        }
        if (i2 < i3) {
            i4 = -((i2 >= this.len ? (char) 26 : this.text.charAt(i2)) - '0');
            i2++;
        }
        while (i2 < i3) {
            int i5 = i2 + 1;
            char charAt = i2 >= this.len ? (char) 26 : this.text.charAt(i2);
            if (charAt == 'L' || charAt == 'S' || charAt == 'B') {
                i2 = i5;
                break;
            }
            int i6 = charAt - '0';
            if (i4 < -214748364) {
                throw new NumberFormatException(numberString());
            }
            int i7 = i4 * 10;
            if (i7 < i + i6) {
                throw new NumberFormatException(numberString());
            }
            i4 = i7 - i6;
            i2 = i5;
        }
        if (z) {
            if (i2 > this.np + 1) {
                return i4;
            }
            throw new NumberFormatException(numberString());
        }
        return -i4;
    }

    public final Number integerValue() throws NumberFormatException {
        char c2;
        long j;
        boolean z;
        long j2;
        int i = this.np;
        int i2 = this.sp + i;
        int i3 = i2 - 1;
        char charAt = i3 >= this.len ? EOI : this.text.charAt(i3);
        if (charAt == 'B') {
            i2--;
            c2 = 'B';
        } else if (charAt == 'L') {
            i2--;
            c2 = 'L';
        } else if (charAt != 'S') {
            c2 = ' ';
        } else {
            i2--;
            c2 = 'S';
        }
        int i4 = this.np;
        if ((i4 >= this.len ? EOI : this.text.charAt(i4)) == '-') {
            j = Long.MIN_VALUE;
            i++;
            z = true;
        } else {
            j = -9223372036854775807L;
            z = false;
        }
        if (i < i2) {
            j2 = -((i >= this.len ? EOI : this.text.charAt(i)) - '0');
            i++;
        } else {
            j2 = 0;
        }
        while (i < i2) {
            int i5 = i + 1;
            int charAt2 = (i >= this.len ? EOI : this.text.charAt(i)) - '0';
            if (j2 < _BufferKt.OVERFLOW_ZONE) {
                return new BigInteger(numberString());
            }
            long j3 = j2 * 10;
            long j4 = charAt2;
            if (j3 < j + j4) {
                return new BigInteger(numberString());
            }
            j2 = j3 - j4;
            i = i5;
        }
        if (!z) {
            long j5 = -j2;
            if (j5 > 2147483647L || c2 == 'L') {
                return Long.valueOf(j5);
            }
            if (c2 == 'S') {
                return Short.valueOf((short) j5);
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) j5);
            }
            return Integer.valueOf((int) j5);
        } else if (i > this.np + 1) {
            if (j2 < -2147483648L || c2 == 'L') {
                return Long.valueOf(j2);
            }
            if (c2 == 'S') {
                return Short.valueOf((short) j2);
            }
            if (c2 == 'B') {
                return Byte.valueOf((byte) j2);
            }
            return Integer.valueOf((int) j2);
        } else {
            throw new NumberFormatException(numberString());
        }
    }

    public final boolean isBlankInput() {
        int i = 0;
        while (true) {
            char charAt = charAt(i);
            boolean z = true;
            if (charAt == 26) {
                return true;
            }
            if (charAt > ' ' || (charAt != ' ' && charAt != '\n' && charAt != '\r' && charAt != '\t' && charAt != '\f' && charAt != '\b')) {
                z = false;
            }
            if (!z) {
                return false;
            }
            i++;
        }
    }

    public final boolean isEnabled(Feature feature) {
        return (feature.mask & this.features) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0086  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x005d -> B:8:0x0026). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long longValue() throws java.lang.NumberFormatException {
        /*
            r13 = this;
            int r0 = r13.np
            int r1 = r13.sp
            int r1 = r1 + r0
            char r2 = r13.charAt(r0)
            r3 = 1
            r4 = 45
            if (r2 != r4) goto L14
            r4 = -9223372036854775808
            int r0 = r0 + 1
            r2 = r3
            goto L1a
        L14:
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r2 = 0
        L1a:
            if (r0 >= r1) goto L28
            int r6 = r0 + 1
            char r0 = r13.charAt(r0)
            int r0 = r0 + (-48)
            int r0 = -r0
            long r7 = (long) r0
        L26:
            r0 = r6
            goto L2a
        L28:
            r7 = 0
        L2a:
            if (r0 >= r1) goto L74
            int r6 = r0 + 1
            int r9 = r13.len
            if (r0 < r9) goto L35
            r0 = 26
            goto L3b
        L35:
            java.lang.String r9 = r13.text
            char r0 = r9.charAt(r0)
        L3b:
            r9 = 76
            if (r0 == r9) goto L73
            r9 = 83
            if (r0 == r9) goto L73
            r9 = 66
            if (r0 != r9) goto L48
            goto L73
        L48:
            int r0 = r0 + (-48)
            r9 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 < 0) goto L69
            r9 = 10
            long r7 = r7 * r9
            long r9 = (long) r0
            long r11 = r4 + r9
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 < 0) goto L5f
            long r7 = r7 - r9
            goto L26
        L5f:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L69:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L73:
            r0 = r6
        L74:
            if (r2 == 0) goto L86
            int r1 = r13.np
            int r1 = r1 + r3
            if (r0 <= r1) goto L7c
            return r7
        L7c:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L86:
            long r0 = -r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.longValue():long");
    }

    public boolean matchField(long j) {
        char c2 = this.ch;
        int i = this.bp + 1;
        int i2 = 1;
        while (c2 != '\"' && c2 != '\'') {
            if (c2 <= ' ' && (c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b')) {
                int i3 = i2 + 1;
                int i4 = this.bp + i2;
                c2 = i4 >= this.len ? EOI : this.text.charAt(i4);
                i2 = i3;
            } else {
                this.fieldHash = 0L;
                this.matchStat = -2;
                return false;
            }
        }
        int i5 = i;
        long j2 = -3750763034362895579L;
        while (true) {
            if (i5 >= this.len) {
                break;
            }
            char charAt = this.text.charAt(i5);
            if (charAt == c2) {
                i2 += (i5 - i) + 1;
                break;
            }
            j2 = 1099511628211L * (j2 ^ charAt);
            i5++;
        }
        if (j2 != j) {
            this.matchStat = -2;
            this.fieldHash = j2;
            return false;
        }
        int i6 = i2 + 1;
        int i7 = this.bp + i2;
        char charAt2 = i7 >= this.len ? EOI : this.text.charAt(i7);
        while (charAt2 != ':') {
            if (charAt2 <= ' ' && (charAt2 == ' ' || charAt2 == '\n' || charAt2 == '\r' || charAt2 == '\t' || charAt2 == '\f' || charAt2 == '\b')) {
                int i8 = i6 + 1;
                int i9 = this.bp + i6;
                charAt2 = i9 >= this.len ? EOI : this.text.charAt(i9);
                i6 = i8;
            } else {
                throw new JSONException("match feild error expect ':'");
            }
        }
        int i10 = this.bp + i6;
        char charAt3 = i10 >= this.len ? EOI : this.text.charAt(i10);
        if (charAt3 == '{') {
            int i11 = i10 + 1;
            this.bp = i11;
            this.ch = i11 >= this.len ? EOI : this.text.charAt(i11);
            this.token = 12;
        } else if (charAt3 == '[') {
            int i12 = i10 + 1;
            this.bp = i12;
            this.ch = i12 >= this.len ? EOI : this.text.charAt(i12);
            this.token = 14;
        } else {
            this.bp = i10;
            this.ch = i10 >= this.len ? EOI : this.text.charAt(i10);
            nextToken();
        }
        return true;
    }

    public char next() {
        int i = this.bp + 1;
        this.bp = i;
        char charAt = i >= this.len ? EOI : this.text.charAt(i);
        this.ch = charAt;
        return charAt;
    }

    public final void nextIdent() {
        char c2;
        while (true) {
            c2 = this.ch;
            if (!(c2 <= ' ' && (c2 == ' ' || c2 == '\n' || c2 == '\r' || c2 == '\t' || c2 == '\f' || c2 == '\b'))) {
                break;
            }
            next();
        }
        if (c2 != '_' && !Character.isLetter(c2)) {
            nextToken();
        } else {
            e();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0027, code lost:
        scanNumber();
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002a, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x0103, code lost:
        e();
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x0106, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void nextToken() {
        /*
            Method dump skipped, instructions count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.nextToken():void");
    }

    public final void nextTokenWithChar(char c2) {
        this.sp = 0;
        while (true) {
            char c3 = this.ch;
            if (c3 == c2) {
                int i = this.bp + 1;
                this.bp = i;
                this.ch = i >= this.len ? EOI : this.text.charAt(i);
                nextToken();
                return;
            } else if (c3 != ' ' && c3 != '\n' && c3 != '\r' && c3 != '\t' && c3 != '\f' && c3 != '\b') {
                throw new JSONException("not match " + c2 + " - " + this.ch);
            } else {
                next();
            }
        }
    }

    public final String numberString() {
        char charAt = this.text.charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        return h(this.np, i);
    }

    public boolean scanBoolean() {
        boolean z = false;
        int i = 1;
        if (this.text.startsWith("false", this.bp)) {
            i = 5;
        } else if (this.text.startsWith("true", this.bp)) {
            z = true;
            i = 4;
        } else {
            char c2 = this.ch;
            if (c2 == '1') {
                z = true;
            } else if (c2 != '0') {
                this.matchStat = -1;
                return false;
            }
        }
        int i2 = this.bp + i;
        this.bp = i2;
        this.ch = charAt(i2);
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00c0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean scanFieldBoolean(long r13) {
        /*
            Method dump skipped, instructions count: 361
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldBoolean(long):boolean");
    }

    public Date scanFieldDate(long j) {
        int i;
        char charAt;
        char c2;
        int i2;
        Date date;
        this.matchStat = 0;
        int c3 = c(j);
        if (c3 == 0) {
            return null;
        }
        int i3 = this.bp;
        char c4 = this.ch;
        int i4 = c3 + 1;
        int i5 = c3 + i3;
        int i6 = this.len;
        char c5 = EOI;
        char charAt2 = i5 >= i6 ? (char) 26 : this.text.charAt(i5);
        if (charAt2 == '\"') {
            int i7 = this.bp;
            int i8 = i7 + i4;
            int i9 = i4 + 1;
            int i10 = i7 + i4;
            if (i10 < this.len) {
                this.text.charAt(i10);
            }
            int indexOf = this.text.indexOf(34, this.bp + i9);
            if (indexOf != -1) {
                int i11 = indexOf - i8;
                this.bp = i8;
                if (scanISO8601DateIfMatch(false, i11)) {
                    date = this.calendar.getTime();
                    int i12 = i9 + i11;
                    i2 = i12 + 1;
                    c2 = charAt(i12 + i3);
                    this.bp = i3;
                } else {
                    this.bp = i3;
                    this.matchStat = -1;
                    return null;
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return null;
        } else {
            long j2 = charAt2 - '0';
            while (true) {
                i = i4 + 1;
                int i13 = this.bp + i4;
                charAt = i13 >= this.len ? (char) 26 : this.text.charAt(i13);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                j2 = (j2 * 10) + (charAt - '0');
                i4 = i;
            }
            if (charAt == '.') {
                this.matchStat = -1;
                return null;
            }
            if (charAt == '\"') {
                int i14 = i + 1;
                int i15 = this.bp + i;
                c2 = i15 >= this.len ? (char) 26 : this.text.charAt(i15);
                i2 = i14;
            } else {
                c2 = charAt;
                i2 = i;
            }
            if (j2 < 0) {
                this.matchStat = -1;
                return null;
            }
            date = new Date(j2);
        }
        if (c2 == ',') {
            int i16 = this.bp + (i2 - 1);
            this.bp = i16;
            int i17 = i16 + 1;
            this.bp = i17;
            if (i17 < this.len) {
                c5 = this.text.charAt(i17);
            }
            this.ch = c5;
            this.matchStat = 3;
            this.token = 16;
            return date;
        } else if (c2 == '}') {
            int i18 = i2 + 1;
            char charAt3 = charAt(this.bp + i2);
            if (charAt3 == ',') {
                this.token = 16;
                int i19 = this.bp + (i18 - 1);
                this.bp = i19;
                int i20 = i19 + 1;
                this.bp = i20;
                if (i20 < this.len) {
                    c5 = this.text.charAt(i20);
                }
                this.ch = c5;
            } else if (charAt3 == ']') {
                this.token = 15;
                int i21 = this.bp + (i18 - 1);
                this.bp = i21;
                int i22 = i21 + 1;
                this.bp = i22;
                if (i22 < this.len) {
                    c5 = this.text.charAt(i22);
                }
                this.ch = c5;
            } else if (charAt3 == '}') {
                this.token = 13;
                int i23 = this.bp + (i18 - 1);
                this.bp = i23;
                int i24 = i23 + 1;
                this.bp = i24;
                if (i24 < this.len) {
                    c5 = this.text.charAt(i24);
                }
                this.ch = c5;
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i18 - 1;
                this.ch = EOI;
            } else {
                this.bp = i3;
                this.ch = c4;
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        } else {
            this.bp = i3;
            this.ch = c4;
            this.matchStat = -1;
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00bb A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x00b8 -> B:53:0x00b9). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final double scanFieldDouble(long r19) {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDouble(long):double");
    }

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0219, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x021b, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d2, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00d4, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:86:0x011a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0127  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x0115 -> B:85:0x0118). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final double[] scanFieldDoubleArray(long r20) {
        /*
            Method dump skipped, instructions count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDoubleArray(long):double[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:165:0x028c, code lost:
        r12 = r3;
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x028f, code lost:
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e5, code lost:
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00e7, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:89:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x013c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x0129 -> B:88:0x012b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final double[][] scanFieldDoubleArray2(long r21) {
        /*
            Method dump skipped, instructions count: 659
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldDoubleArray2(long):double[][]");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00b3 A[ADDED_TO_REGION] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:50:0x00b0 -> B:51:0x00b1). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float scanFieldFloat(long r18) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloat(long):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:142:0x0219, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x021b, code lost:
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00d2, code lost:
        r19.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00d4, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:86:0x011a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0127  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x0115 -> B:85:0x0118). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float[] scanFieldFloatArray(long r20) {
        /*
            Method dump skipped, instructions count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloatArray(long):float[]");
    }

    /* JADX WARN: Code restructure failed: missing block: B:165:0x028c, code lost:
        r12 = r3;
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x028f, code lost:
        return r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e5, code lost:
        r20.matchStat = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00e7, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:89:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x013c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x0129 -> B:88:0x012b). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float[][] scanFieldFloatArray2(long r21) {
        /*
            Method dump skipped, instructions count: 659
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanFieldFloatArray2(long):float[][]");
    }

    public int scanFieldInt(long j) {
        int i;
        char charAt;
        this.matchStat = 0;
        int c2 = c(j);
        if (c2 == 0) {
            return 0;
        }
        int i2 = c2 + 1;
        int i3 = this.bp + c2;
        int i4 = this.len;
        char c3 = EOI;
        char charAt2 = i3 >= i4 ? (char) 26 : this.text.charAt(i3);
        boolean z = charAt2 == '\"';
        if (z) {
            int i5 = i2 + 1;
            int i6 = this.bp + i2;
            charAt2 = i6 >= this.len ? (char) 26 : this.text.charAt(i6);
            i2 = i5;
            z = true;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            int i7 = i2 + 1;
            int i8 = this.bp + i2;
            charAt2 = i8 >= this.len ? (char) 26 : this.text.charAt(i8);
            i2 = i7;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i9 = charAt2 - '0';
        while (true) {
            i = i2 + 1;
            int i10 = this.bp + i2;
            charAt = i10 >= this.len ? (char) 26 : this.text.charAt(i10);
            if (charAt < '0' || charAt > '9') {
                break;
            }
            i9 = (i9 * 10) + (charAt - '0');
            i2 = i;
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (charAt == '\"') {
            if (!z) {
                this.matchStat = -1;
                return 0;
            }
            int i11 = i + 1;
            int i12 = this.bp + i;
            i = i11;
            charAt = i12 >= this.len ? (char) 26 : this.text.charAt(i12);
        }
        if (i9 < 0) {
            this.matchStat = -1;
            return 0;
        }
        while (charAt != ',') {
            if (charAt > ' ' || !(charAt == ' ' || charAt == '\n' || charAt == '\r' || charAt == '\t' || charAt == '\f' || charAt == '\b')) {
                if (charAt == '}') {
                    int i13 = i + 1;
                    char charAt3 = charAt(this.bp + i);
                    if (charAt3 == ',') {
                        this.token = 16;
                        int i14 = this.bp + (i13 - 1);
                        this.bp = i14;
                        int i15 = i14 + 1;
                        this.bp = i15;
                        if (i15 < this.len) {
                            c3 = this.text.charAt(i15);
                        }
                        this.ch = c3;
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        int i16 = this.bp + (i13 - 1);
                        this.bp = i16;
                        int i17 = i16 + 1;
                        this.bp = i17;
                        if (i17 < this.len) {
                            c3 = this.text.charAt(i17);
                        }
                        this.ch = c3;
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        int i18 = this.bp + (i13 - 1);
                        this.bp = i18;
                        int i19 = i18 + 1;
                        this.bp = i19;
                        if (i19 < this.len) {
                            c3 = this.text.charAt(i19);
                        }
                        this.ch = c3;
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i13 - 1;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                    this.matchStat = 4;
                    return z2 ? -i9 : i9;
                }
                this.matchStat = -1;
                return 0;
            }
            int i20 = i + 1;
            int i21 = this.bp + i;
            i = i20;
            charAt = i21 >= this.len ? (char) 26 : this.text.charAt(i21);
        }
        int i22 = this.bp + (i - 1);
        this.bp = i22;
        int i23 = i22 + 1;
        this.bp = i23;
        if (i23 < this.len) {
            c3 = this.text.charAt(i23);
        }
        this.ch = c3;
        this.matchStat = 3;
        this.token = 16;
        return z2 ? -i9 : i9;
    }

    public final int[] scanFieldIntArray(long j) {
        boolean z;
        int i;
        int[] iArr;
        int i2;
        char charAt;
        int i3;
        int i4;
        char charAt2;
        int i5;
        int[] iArr2;
        this.matchStat = 0;
        int c2 = c(j);
        int[] iArr3 = null;
        if (c2 == 0) {
            return null;
        }
        int i6 = c2 + 1;
        int i7 = this.bp + c2;
        int i8 = -1;
        if ((i7 >= this.len ? (char) 26 : this.text.charAt(i7)) != '[') {
            this.matchStat = -1;
            return null;
        }
        int i9 = i6 + 1;
        int i10 = this.bp + i6;
        char charAt3 = i10 >= this.len ? (char) 26 : this.text.charAt(i10);
        int[] iArr4 = new int[16];
        if (charAt3 == ']') {
            i4 = i9 + 1;
            int i11 = this.bp + i9;
            charAt2 = i11 >= this.len ? (char) 26 : this.text.charAt(i11);
            i3 = 0;
        } else {
            int i12 = 0;
            while (true) {
                if (charAt3 == '-') {
                    int i13 = i9 + 1;
                    int i14 = this.bp + i9;
                    charAt3 = i14 >= this.len ? (char) 26 : this.text.charAt(i14);
                    i9 = i13;
                    z = true;
                } else {
                    z = false;
                }
                if (charAt3 < '0') {
                    i = i8;
                    iArr = iArr3;
                    break;
                } else if (charAt3 > '9') {
                    i = i8;
                    iArr = null;
                    break;
                } else {
                    int i15 = charAt3 - '0';
                    while (true) {
                        i2 = i9 + 1;
                        int i16 = this.bp + i9;
                        charAt = i16 >= this.len ? (char) 26 : this.text.charAt(i16);
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i15 = (i15 * 10) + (charAt - '0');
                        i9 = i2;
                    }
                    if (i12 >= iArr4.length) {
                        int[] iArr5 = new int[(iArr4.length * 3) / 2];
                        System.arraycopy(iArr4, 0, iArr5, 0, i12);
                        iArr4 = iArr5;
                    }
                    i3 = i12 + 1;
                    if (z) {
                        i15 = -i15;
                    }
                    iArr4[i12] = i15;
                    if (charAt == ',') {
                        int i17 = i2 + 1;
                        int i18 = this.bp + i2;
                        i2 = i17;
                        iArr2 = null;
                        charAt = i18 >= this.len ? (char) 26 : this.text.charAt(i18);
                        i5 = -1;
                    } else if (charAt == ']') {
                        i4 = i2 + 1;
                        int i19 = this.bp + i2;
                        charAt2 = i19 >= this.len ? (char) 26 : this.text.charAt(i19);
                    } else {
                        i5 = -1;
                        iArr2 = null;
                    }
                    i12 = i3;
                    iArr3 = iArr2;
                    i8 = i5;
                    charAt3 = charAt;
                    i9 = i2;
                }
            }
            this.matchStat = i;
            return iArr;
        }
        if (i3 != iArr4.length) {
            int[] iArr6 = new int[i3];
            System.arraycopy(iArr4, 0, iArr6, 0, i3);
            iArr4 = iArr6;
        }
        if (charAt2 == ',') {
            this.bp += i4 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return iArr4;
        } else if (charAt2 == '}') {
            int i20 = i4 + 1;
            char charAt4 = charAt(this.bp + i4);
            if (charAt4 == ',') {
                this.token = 16;
                this.bp += i20 - 1;
                next();
            } else if (charAt4 == ']') {
                this.token = 15;
                this.bp += i20 - 1;
                next();
            } else if (charAt4 == '}') {
                this.token = 13;
                this.bp += i20 - 1;
                next();
            } else if (charAt4 == 26) {
                this.bp += i20 - 1;
                this.token = 20;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return iArr4;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public long scanFieldLong(long j) {
        int i;
        char charAt;
        this.matchStat = 0;
        int c2 = c(j);
        if (c2 == 0) {
            return 0L;
        }
        int i2 = c2 + 1;
        int i3 = this.bp + c2;
        char charAt2 = i3 >= this.len ? EOI : this.text.charAt(i3);
        boolean z = charAt2 == '\"';
        if (z) {
            int i4 = i2 + 1;
            int i5 = this.bp + i2;
            charAt2 = i5 >= this.len ? EOI : this.text.charAt(i5);
            i2 = i4;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            int i6 = i2 + 1;
            int i7 = this.bp + i2;
            charAt2 = i7 >= this.len ? EOI : this.text.charAt(i7);
            i2 = i6;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0L;
        }
        long j2 = charAt2 - '0';
        while (true) {
            i = i2 + 1;
            int i8 = this.bp + i2;
            charAt = i8 >= this.len ? EOI : this.text.charAt(i8);
            if (charAt < '0' || charAt > '9') {
                break;
            }
            j2 = (j2 * 10) + (charAt - '0');
            i2 = i;
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0L;
        }
        if (charAt == '\"') {
            if (!z) {
                this.matchStat = -1;
                return 0L;
            }
            int i9 = i + 1;
            int i10 = this.bp + i;
            charAt = i10 >= this.len ? EOI : this.text.charAt(i10);
            i = i9;
        }
        if (j2 < 0) {
            this.matchStat = -1;
            return 0L;
        } else if (charAt == ',') {
            int i11 = this.bp + (i - 1);
            this.bp = i11;
            int i12 = i11 + 1;
            this.bp = i12;
            this.ch = i12 >= this.len ? EOI : this.text.charAt(i12);
            this.matchStat = 3;
            this.token = 16;
            return z2 ? -j2 : j2;
        } else if (charAt == '}') {
            int i13 = i + 1;
            char charAt3 = charAt(this.bp + i);
            if (charAt3 == ',') {
                this.token = 16;
                int i14 = this.bp + (i13 - 1);
                this.bp = i14;
                int i15 = i14 + 1;
                this.bp = i15;
                this.ch = i15 >= this.len ? EOI : this.text.charAt(i15);
            } else if (charAt3 == ']') {
                this.token = 15;
                int i16 = this.bp + (i13 - 1);
                this.bp = i16;
                int i17 = i16 + 1;
                this.bp = i17;
                this.ch = i17 >= this.len ? EOI : this.text.charAt(i17);
            } else if (charAt3 == '}') {
                this.token = 13;
                int i18 = this.bp + (i13 - 1);
                this.bp = i18;
                int i19 = i18 + 1;
                this.bp = i19;
                this.ch = i19 >= this.len ? EOI : this.text.charAt(i19);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i13 - 1;
                this.ch = EOI;
            } else {
                this.matchStat = -1;
                return 0L;
            }
            this.matchStat = 4;
            return z2 ? -j2 : j2;
        } else {
            this.matchStat = -1;
            return 0L;
        }
    }

    public String scanFieldString(long j) {
        String str;
        this.matchStat = 0;
        int c2 = c(j);
        if (c2 == 0) {
            return null;
        }
        int i = c2 + 1;
        int i2 = this.bp + c2;
        if (i2 < this.len) {
            if (this.text.charAt(i2) != '\"') {
                this.matchStat = -1;
                return this.stringDefaultValue;
            }
            int i3 = this.bp + i;
            int indexOf = this.text.indexOf(34, i3);
            if (indexOf != -1) {
                if (f2117a) {
                    str = this.text.substring(i3, indexOf);
                } else {
                    int i4 = indexOf - i3;
                    str = new String(i(this.bp + i, i4), 0, i4);
                }
                if (str.indexOf(92) != -1) {
                    boolean z = false;
                    while (true) {
                        int i5 = indexOf - 1;
                        int i6 = 0;
                        while (i5 >= 0 && this.text.charAt(i5) == '\\') {
                            i6++;
                            i5--;
                            z = true;
                        }
                        if (i6 % 2 == 0) {
                            break;
                        }
                        indexOf = this.text.indexOf(34, indexOf + 1);
                    }
                    int i7 = indexOf - i3;
                    char[] i8 = i(this.bp + i, i7);
                    if (z) {
                        str = d(i8, i7);
                    } else {
                        str = new String(i8, 0, i7);
                        if (str.indexOf(92) != -1) {
                            str = d(i8, i7);
                        }
                    }
                }
                int i9 = indexOf + 1;
                int i10 = this.len;
                char c3 = EOI;
                char charAt = i9 >= i10 ? (char) 26 : this.text.charAt(i9);
                if (charAt == ',') {
                    this.bp = i9;
                    int i11 = i9 + 1;
                    this.bp = i11;
                    if (i11 < this.len) {
                        c3 = this.text.charAt(i11);
                    }
                    this.ch = c3;
                    this.matchStat = 3;
                    this.token = 16;
                    return str;
                } else if (charAt == '}') {
                    int i12 = i9 + 1;
                    char charAt2 = i12 >= this.len ? (char) 26 : this.text.charAt(i12);
                    if (charAt2 == ',') {
                        this.token = 16;
                        this.bp = i12;
                        next();
                    } else if (charAt2 == ']') {
                        this.token = 15;
                        this.bp = i12;
                        next();
                    } else if (charAt2 == '}') {
                        this.token = 13;
                        this.bp = i12;
                        next();
                    } else if (charAt2 == 26) {
                        this.token = 20;
                        this.bp = i12;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return this.stringDefaultValue;
                    }
                    this.matchStat = 4;
                    return str;
                } else {
                    this.matchStat = -1;
                    return this.stringDefaultValue;
                }
            }
            throw new JSONException("unclosed str, " + info());
        }
        throw new JSONException("unclosed str, " + info());
    }

    public long scanFieldSymbol(long j) {
        this.matchStat = 0;
        int c2 = c(j);
        if (c2 == 0) {
            return 0L;
        }
        int i = c2 + 1;
        int i2 = this.bp + c2;
        int i3 = this.len;
        char c3 = EOI;
        if ((i2 >= i3 ? (char) 26 : this.text.charAt(i2)) != '\"') {
            this.matchStat = -1;
            return 0L;
        }
        long j2 = -3750763034362895579L;
        while (true) {
            int i4 = i + 1;
            int i5 = this.bp + i;
            char charAt = i5 >= this.len ? (char) 26 : this.text.charAt(i5);
            if (charAt == '\"') {
                int i6 = i4 + 1;
                int i7 = this.bp + i4;
                char charAt2 = i7 >= this.len ? (char) 26 : this.text.charAt(i7);
                if (charAt2 == ',') {
                    int i8 = this.bp + (i6 - 1);
                    this.bp = i8;
                    int i9 = i8 + 1;
                    this.bp = i9;
                    if (i9 < this.len) {
                        c3 = this.text.charAt(i9);
                    }
                    this.ch = c3;
                    this.matchStat = 3;
                    return j2;
                } else if (charAt2 == '}') {
                    int i10 = i6 + 1;
                    int i11 = this.bp + i6;
                    char charAt3 = i11 >= this.len ? (char) 26 : this.text.charAt(i11);
                    if (charAt3 == ',') {
                        this.token = 16;
                        this.bp += i10 - 1;
                        next();
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        this.bp += i10 - 1;
                        next();
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        this.bp += i10 - 1;
                        next();
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i10 - 1;
                        this.ch = EOI;
                    } else {
                        this.matchStat = -1;
                        return 0L;
                    }
                    this.matchStat = 4;
                    return j2;
                } else {
                    this.matchStat = -1;
                    return 0L;
                }
            }
            j2 = (j2 ^ charAt) * 1099511628211L;
            if (charAt == '\\') {
                this.matchStat = -1;
                return 0L;
            }
            i = i4;
        }
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00c2, code lost:
        if (r0 != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00c5, code lost:
        return -r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
        return r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long scanLongValue() {
        /*
            r14 = this;
            r0 = 0
            r14.np = r0
            char r1 = r14.ch
            r2 = 1
            r3 = 45
            if (r1 != r3) goto L3f
            r0 = -9223372036854775808
            r3 = 0
            int r3 = r3 + r2
            r14.np = r3
            int r3 = r14.bp
            int r3 = r3 + r2
            r14.bp = r3
            int r4 = r14.len
            if (r3 >= r4) goto L24
            java.lang.String r4 = r14.text
            char r3 = r4.charAt(r3)
            r14.ch = r3
            r3 = r0
            r0 = r2
            goto L44
        L24:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "syntax error, "
            r1.append(r2)
            java.lang.String r2 = r14.info()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L3f:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L44:
            r5 = 0
        L46:
            char r1 = r14.ch
            r7 = 48
            if (r1 < r7) goto Lc2
            r7 = 57
            if (r1 > r7) goto Lc2
            int r1 = r1 + (-48)
            r7 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            java.lang.String r8 = ", "
            java.lang.String r9 = "error long value, "
            if (r7 < 0) goto La3
            r10 = 10
            long r5 = r5 * r10
            long r10 = (long) r1
            long r12 = r3 + r10
            int r1 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r1 < 0) goto L84
            long r5 = r5 - r10
            int r1 = r14.np
            int r1 = r1 + r2
            r14.np = r1
            int r1 = r14.bp
            int r1 = r1 + r2
            r14.bp = r1
            int r7 = r14.len
            if (r1 < r7) goto L7b
            r1 = 26
            goto L81
        L7b:
            java.lang.String r7 = r14.text
            char r1 = r7.charAt(r1)
        L81:
            r14.ch = r1
            goto L46
        L84:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            r1.append(r5)
            r1.append(r8)
            java.lang.String r2 = r14.info()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        La3:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r9)
            r1.append(r5)
            r1.append(r8)
            java.lang.String r2 = r14.info()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        Lc2:
            if (r0 != 0) goto Lc5
            long r5 = -r5
        Lc5:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanLongValue():long");
    }

    public final void scanNumber() {
        char c2;
        char c3;
        int i = this.bp;
        this.np = i;
        this.exp = false;
        if (this.ch == '-') {
            this.sp++;
            int i2 = i + 1;
            this.bp = i2;
            this.ch = i2 >= this.len ? (char) 26 : this.text.charAt(i2);
        }
        while (true) {
            c2 = this.ch;
            if (c2 < '0' || c2 > '9') {
                break;
            }
            this.sp++;
            int i3 = this.bp + 1;
            this.bp = i3;
            this.ch = i3 >= this.len ? (char) 26 : this.text.charAt(i3);
        }
        this.isDouble = false;
        if (c2 == '.') {
            this.sp++;
            int i4 = this.bp + 1;
            this.bp = i4;
            this.ch = i4 >= this.len ? (char) 26 : this.text.charAt(i4);
            this.isDouble = true;
            while (true) {
                char c4 = this.ch;
                if (c4 < '0' || c4 > '9') {
                    break;
                }
                this.sp++;
                int i5 = this.bp + 1;
                this.bp = i5;
                this.ch = i5 >= this.len ? (char) 26 : this.text.charAt(i5);
            }
        }
        char c5 = this.ch;
        if (c5 == 'L') {
            this.sp++;
            next();
        } else if (c5 == 'S') {
            this.sp++;
            next();
        } else if (c5 == 'B') {
            this.sp++;
            next();
        } else if (c5 == 'F') {
            this.sp++;
            next();
            this.isDouble = true;
        } else if (c5 == 'D') {
            this.sp++;
            next();
            this.isDouble = true;
        } else if (c5 == 'e' || c5 == 'E') {
            this.sp++;
            int i6 = this.bp + 1;
            this.bp = i6;
            char charAt = i6 >= this.len ? (char) 26 : this.text.charAt(i6);
            this.ch = charAt;
            if (charAt == '+' || charAt == '-') {
                this.sp++;
                int i7 = this.bp + 1;
                this.bp = i7;
                this.ch = i7 >= this.len ? (char) 26 : this.text.charAt(i7);
            }
            while (true) {
                c3 = this.ch;
                if (c3 < '0' || c3 > '9') {
                    break;
                }
                this.sp++;
                int i8 = this.bp + 1;
                this.bp = i8;
                this.ch = i8 >= this.len ? (char) 26 : this.text.charAt(i8);
            }
            if (c3 == 'D' || c3 == 'F') {
                this.sp++;
                next();
            }
            this.exp = true;
            this.isDouble = true;
        }
        if (this.isDouble) {
            this.token = 3;
        } else {
            this.token = 2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x0286 A[Catch: NumberFormatException -> 0x02c8, TryCatch #0 {NumberFormatException -> 0x02c8, blocks: (B:144:0x026a, B:150:0x0281, B:152:0x0286, B:158:0x0297, B:155:0x028e, B:157:0x0295, B:160:0x029c, B:163:0x02a2, B:165:0x02a7, B:168:0x02ad, B:149:0x0277, B:170:0x02b2, B:172:0x02ba, B:173:0x02bf), top: B:178:0x0266 }] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x029c A[Catch: NumberFormatException -> 0x02c8, TryCatch #0 {NumberFormatException -> 0x02c8, blocks: (B:144:0x026a, B:150:0x0281, B:152:0x0286, B:158:0x0297, B:155:0x028e, B:157:0x0295, B:160:0x029c, B:163:0x02a2, B:165:0x02a7, B:168:0x02ad, B:149:0x0277, B:170:0x02b2, B:172:0x02ba, B:173:0x02bf), top: B:178:0x0266 }] */
    /* JADX WARN: Removed duplicated region for block: B:165:0x02a7 A[Catch: NumberFormatException -> 0x02c8, TryCatch #0 {NumberFormatException -> 0x02c8, blocks: (B:144:0x026a, B:150:0x0281, B:152:0x0286, B:158:0x0297, B:155:0x028e, B:157:0x0295, B:160:0x029c, B:163:0x02a2, B:165:0x02a7, B:168:0x02ad, B:149:0x0277, B:170:0x02b2, B:172:0x02ba, B:173:0x02bf), top: B:178:0x0266 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Number scanNumberValue() {
        /*
            Method dump skipped, instructions count: 747
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanNumberValue():java.lang.Number");
    }

    public final void scanString() {
        char c2 = this.ch;
        int i = this.bp + 1;
        int indexOf = this.text.indexOf(c2, i);
        if (indexOf != -1) {
            int i2 = indexOf - i;
            char[] i3 = i(this.bp + 1, i2);
            boolean z = false;
            while (i2 > 0 && i3[i2 - 1] == '\\') {
                int i4 = 1;
                for (int i5 = i2 - 2; i5 >= 0 && i3[i5] == '\\'; i5--) {
                    i4++;
                }
                if (i4 % 2 == 0) {
                    break;
                }
                int indexOf2 = this.text.indexOf(c2, indexOf + 1);
                int i6 = (indexOf2 - indexOf) + i2;
                if (i6 >= i3.length) {
                    int length = (i3.length * 3) / 2;
                    if (length < i6) {
                        length = i6;
                    }
                    char[] cArr = new char[length];
                    System.arraycopy(i3, 0, cArr, 0, i3.length);
                    i3 = cArr;
                }
                this.text.getChars(indexOf, indexOf2, i3, i2);
                indexOf = indexOf2;
                i2 = i6;
                z = true;
            }
            if (!z) {
                for (int i7 = 0; i7 < i2; i7++) {
                    if (i3[i7] == '\\') {
                        z = true;
                    }
                }
            }
            this.sbuf = i3;
            this.sp = i2;
            this.np = this.bp;
            this.hasSpecial = z;
            int i8 = indexOf + 1;
            this.bp = i8;
            this.ch = i8 >= this.len ? EOI : this.text.charAt(i8);
            this.token = 4;
            return;
        }
        throw new JSONException("unclosed str, " + info());
    }

    public String scanStringValue(char c2) {
        String str;
        int i = this.bp + 1;
        int indexOf = this.text.indexOf(c2, i);
        if (indexOf != -1) {
            if (f2117a) {
                str = this.text.substring(i, indexOf);
            } else {
                int i2 = indexOf - i;
                str = new String(i(this.bp + 1, i2), 0, i2);
            }
            if (str.indexOf(92) != -1) {
                while (true) {
                    int i3 = 0;
                    for (int i4 = indexOf - 1; i4 >= 0 && this.text.charAt(i4) == '\\'; i4--) {
                        i3++;
                    }
                    if (i3 % 2 == 0) {
                        break;
                    }
                    indexOf = this.text.indexOf(c2, indexOf + 1);
                }
                int i5 = indexOf - i;
                str = d(i(this.bp + 1, i5), i5);
            }
            int i6 = indexOf + 1;
            this.bp = i6;
            this.ch = i6 >= this.len ? EOI : this.text.charAt(i6);
            return str;
        }
        throw new JSONException("unclosed str, " + info());
    }

    public final String scanSymbol(SymbolTable symbolTable) {
        char c2;
        while (true) {
            c2 = this.ch;
            if (c2 != ' ' && c2 != '\n' && c2 != '\r' && c2 != '\t' && c2 != '\f' && c2 != '\b') {
                break;
            }
            next();
        }
        if (c2 == '\"') {
            return scanSymbol(symbolTable, Typography.quote);
        }
        if (c2 == '\'') {
            return scanSymbol(symbolTable, '\'');
        }
        if (c2 == '}') {
            next();
            this.token = 13;
            return null;
        } else if (c2 == ',') {
            next();
            this.token = 16;
            return null;
        } else if (c2 == 26) {
            this.token = 20;
            return null;
        } else {
            return scanSymbolUnQuoted(symbolTable);
        }
    }

    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        int i = this.ch;
        boolean[] zArr = firstIdentifierFlags;
        if (i >= zArr.length || zArr[i]) {
            this.np = this.bp;
            this.sp = 1;
            while (true) {
                char next = next();
                boolean[] zArr2 = identifierFlags;
                if (next < zArr2.length && !zArr2[next]) {
                    break;
                }
                i = (i * 31) + next;
                this.sp++;
            }
            this.ch = charAt(this.bp);
            this.token = 18;
            if (this.sp == 4 && this.text.startsWith("null", this.np)) {
                return null;
            }
            return symbolTable.addSymbol(this.text, this.np, this.sp, i);
        }
        throw new JSONException("illegal identifier : " + this.ch + ", " + info());
    }

    public void setTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        this.calendar.set(11, ((c2 - '0') * 10) + (c3 - '0'));
        this.calendar.set(12, ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(13, ((c6 - '0') * 10) + (c7 - '0'));
    }

    public void setTimeZone(char c2, char c3, char c4) {
        int i = (((c3 - '0') * 10) + (c4 - '0')) * CacheConstants.HOUR * 1000;
        if (c2 == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            String[] availableIDs = TimeZone.getAvailableIDs(i);
            if (availableIDs.length > 0) {
                this.calendar.setTimeZone(TimeZone.getTimeZone(availableIDs[0]));
            }
        }
    }

    public void skipComment() {
        next();
        char c2 = this.ch;
        if (c2 == '/') {
            do {
                next();
            } while (this.ch != '\n');
            next();
        } else if (c2 == '*') {
            next();
            while (true) {
                char c3 = this.ch;
                if (c3 == 26) {
                    return;
                }
                if (c3 == '*') {
                    next();
                    if (this.ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    public final String stringVal() {
        if (this.hasSpecial) {
            return d(this.sbuf, this.sp);
        }
        return h(this.np + 1, this.sp);
    }

    public final int token() {
        return this.token;
    }

    public JSONLexer(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0209 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x020c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean scanISO8601DateIfMatch(boolean r36, int r37) {
        /*
            Method dump skipped, instructions count: 1614
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexer.scanISO8601DateIfMatch(boolean, int):boolean");
    }

    public JSONLexer(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    public JSONLexer(String str, int i) {
        this.features = JSON.DEFAULT_PARSER_FEATURE;
        this.exp = false;
        this.isDouble = false;
        this.timeZone = JSON.defaultTimeZone;
        this.locale = JSON.defaultLocale;
        this.calendar = null;
        this.matchStat = 0;
        char[] cArr = b.get();
        this.sbuf = cArr;
        if (cArr == null) {
            this.sbuf = new char[512];
        }
        this.features = i;
        this.text = str;
        int length = str.length();
        this.len = length;
        this.bp = -1;
        int i2 = (-1) + 1;
        this.bp = i2;
        char charAt = i2 >= length ? EOI : str.charAt(i2);
        this.ch = charAt;
        if (charAt == 65279) {
            next();
        }
        this.stringDefaultValue = (Feature.InitStringFieldAsEmpty.mask & i) != 0 ? "" : null;
        this.disableCircularReferenceDetect = (Feature.DisableCircularReferenceDetect.mask & i) != 0;
    }

    public String scanSymbol(SymbolTable symbolTable, char c2) {
        String d;
        int i = this.bp + 1;
        int indexOf = this.text.indexOf(c2, i);
        if (indexOf != -1) {
            int i2 = indexOf - i;
            char[] i3 = i(this.bp + 1, i2);
            boolean z = false;
            while (i2 > 0 && i3[i2 - 1] == '\\') {
                int i4 = 1;
                for (int i5 = i2 - 2; i5 >= 0 && i3[i5] == '\\'; i5--) {
                    i4++;
                }
                if (i4 % 2 == 0) {
                    break;
                }
                int indexOf2 = this.text.indexOf(c2, indexOf + 1);
                int i6 = (indexOf2 - indexOf) + i2;
                if (i6 >= i3.length) {
                    int length = (i3.length * 3) / 2;
                    if (length < i6) {
                        length = i6;
                    }
                    char[] cArr = new char[length];
                    System.arraycopy(i3, 0, cArr, 0, i3.length);
                    i3 = cArr;
                }
                this.text.getChars(indexOf, indexOf2, i3, i2);
                indexOf = indexOf2;
                i2 = i6;
                z = true;
            }
            if (z) {
                d = d(i3, i2);
            } else {
                int i7 = 0;
                for (int i8 = 0; i8 < i2; i8++) {
                    char c3 = i3[i8];
                    i7 = (i7 * 31) + c3;
                    if (c3 == '\\') {
                        z = true;
                    }
                }
                if (z) {
                    d = d(i3, i2);
                } else {
                    d = i2 < 20 ? symbolTable.addSymbol(i3, 0, i2, i7) : new String(i3, 0, i2);
                }
            }
            int i9 = indexOf + 1;
            this.bp = i9;
            this.ch = i9 >= this.len ? EOI : this.text.charAt(i9);
            return d;
        }
        throw new JSONException("unclosed str, " + info());
    }

    public final BigDecimal decimalValue() {
        char charAt = this.text.charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        int i2 = this.np;
        char[] cArr = this.sbuf;
        if (i < cArr.length) {
            this.text.getChars(i2, i2 + i, cArr, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr2 = new char[i];
        this.text.getChars(i2, i + i2, cArr2, 0);
        return new BigDecimal(cArr2);
    }

    public final void nextToken(int i) {
        this.sp = 0;
        while (true) {
            if (i != 2) {
                char c2 = EOI;
                if (i == 4) {
                    char c3 = this.ch;
                    if (c3 == '\"') {
                        this.pos = this.bp;
                        scanString();
                        return;
                    } else if (c3 >= '0' && c3 <= '9') {
                        this.pos = this.bp;
                        scanNumber();
                        return;
                    } else if (c3 == '{') {
                        this.token = 12;
                        int i2 = this.bp + 1;
                        this.bp = i2;
                        if (i2 < this.len) {
                            c2 = this.text.charAt(i2);
                        }
                        this.ch = c2;
                        return;
                    }
                } else if (i == 12) {
                    char c4 = this.ch;
                    if (c4 == '{') {
                        this.token = 12;
                        int i3 = this.bp + 1;
                        this.bp = i3;
                        if (i3 < this.len) {
                            c2 = this.text.charAt(i3);
                        }
                        this.ch = c2;
                        return;
                    } else if (c4 == '[') {
                        this.token = 14;
                        int i4 = this.bp + 1;
                        this.bp = i4;
                        if (i4 < this.len) {
                            c2 = this.text.charAt(i4);
                        }
                        this.ch = c2;
                        return;
                    }
                } else if (i != 18) {
                    if (i != 20) {
                        switch (i) {
                            case 14:
                                char c5 = this.ch;
                                if (c5 == '[') {
                                    this.token = 14;
                                    next();
                                    return;
                                } else if (c5 == '{') {
                                    this.token = 12;
                                    next();
                                    return;
                                }
                                break;
                            case 15:
                                if (this.ch == ']') {
                                    this.token = 15;
                                    next();
                                    return;
                                }
                                break;
                            case 16:
                                char c6 = this.ch;
                                if (c6 == ',') {
                                    this.token = 16;
                                    int i5 = this.bp + 1;
                                    this.bp = i5;
                                    if (i5 < this.len) {
                                        c2 = this.text.charAt(i5);
                                    }
                                    this.ch = c2;
                                    return;
                                } else if (c6 == '}') {
                                    this.token = 13;
                                    int i6 = this.bp + 1;
                                    this.bp = i6;
                                    if (i6 < this.len) {
                                        c2 = this.text.charAt(i6);
                                    }
                                    this.ch = c2;
                                    return;
                                } else if (c6 == ']') {
                                    this.token = 15;
                                    int i7 = this.bp + 1;
                                    this.bp = i7;
                                    if (i7 < this.len) {
                                        c2 = this.text.charAt(i7);
                                    }
                                    this.ch = c2;
                                    return;
                                } else if (c6 == 26) {
                                    this.token = 20;
                                    return;
                                }
                                break;
                        }
                    }
                    if (this.ch == 26) {
                        this.token = 20;
                        return;
                    }
                } else {
                    nextIdent();
                    return;
                }
            } else {
                char c7 = this.ch;
                if (c7 >= '0' && c7 <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                } else if (c7 == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                } else if (c7 == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (c7 == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            }
            char c8 = this.ch;
            if (c8 != ' ' && c8 != '\n' && c8 != '\r' && c8 != '\t' && c8 != '\f' && c8 != '\b') {
                nextToken();
                return;
            }
            next();
        }
    }
}
