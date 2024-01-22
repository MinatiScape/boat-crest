package com.google.android.gms.common.server.response;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import kotlin.text.Typography;
import okio.internal._BufferKt;
@ShowFirstParty
@KeepForSdk
/* loaded from: classes6.dex */
public class FastParser<T extends FastJsonResponse> {
    public static final char[] g = {'u', Constants.INAPP_POSITION_LEFT, Constants.INAPP_POSITION_LEFT};
    public static final char[] h = {Constants.INAPP_POSITION_RIGHT, 'u', 'e'};
    public static final char[] i = {Constants.INAPP_POSITION_RIGHT, 'u', 'e', Typography.quote};
    public static final char[] j = {'a', Constants.INAPP_POSITION_LEFT, 's', 'e'};
    public static final char[] k = {'a', Constants.INAPP_POSITION_LEFT, 's', 'e', Typography.quote};
    public static final char[] l = {'\n'};
    public static final i m = new a();
    public static final i n = new b();
    public static final i o = new c();
    public static final i p = new d();
    public static final i q = new e();
    public static final i r = new f();
    public static final i s = new g();
    public static final i t = new h();

    /* renamed from: a  reason: collision with root package name */
    public final char[] f8367a = new char[1];
    public final char[] b = new char[32];
    public final char[] c = new char[1024];
    public final StringBuilder d = new StringBuilder(32);
    public final StringBuilder e = new StringBuilder(1024);
    public final Stack f = new Stack();

    @ShowFirstParty
    @KeepForSdk
    /* loaded from: classes6.dex */
    public static class ParseException extends Exception {
        public ParseException(@NonNull String str) {
            super(str);
        }

        public ParseException(@NonNull String str, @NonNull Throwable th) {
            super("Error instantiating inner object", th);
        }

        public ParseException(@NonNull Throwable th) {
            super(th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0030, code lost:
        throw new com.google.android.gms.common.server.response.FastParser.ParseException("Unexpected control character while reading string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.String a(java.io.BufferedReader r8, char[] r9, java.lang.StringBuilder r10, @androidx.annotation.Nullable char[] r11) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            r0 = 0
            r10.setLength(r0)
            int r1 = r9.length
            r8.mark(r1)
            r1 = r0
            r2 = r1
        La:
            int r3 = r8.read(r9)
            r4 = -1
            if (r3 == r4) goto L68
            r4 = r0
        L12:
            if (r4 >= r3) goto L60
            char r5 = r9[r4]
            boolean r6 = java.lang.Character.isISOControl(r5)
            if (r6 == 0) goto L31
            if (r11 == 0) goto L29
            r6 = r0
        L1f:
            if (r6 > 0) goto L29
            char r7 = r11[r6]
            if (r7 != r5) goto L26
            goto L31
        L26:
            int r6 = r6 + 1
            goto L1f
        L29:
            com.google.android.gms.common.server.response.FastParser$ParseException r8 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r9 = "Unexpected control character while reading string"
            r8.<init>(r9)
            throw r8
        L31:
            r6 = 34
            r7 = 1
            if (r5 != r6) goto L53
            if (r2 != 0) goto L5c
            r10.append(r9, r0, r4)
            r8.reset()
            int r4 = r4 + r7
            long r2 = (long) r4
            r8.skip(r2)
            if (r1 == 0) goto L4e
            java.lang.String r8 = r10.toString()
            java.lang.String r8 = com.google.android.gms.common.util.JsonUtils.unescapeString(r8)
            return r8
        L4e:
            java.lang.String r8 = r10.toString()
            return r8
        L53:
            r6 = 92
            if (r5 != r6) goto L5c
            r1 = r2 ^ 1
            r2 = r1
            r1 = r7
            goto L5d
        L5c:
            r2 = r0
        L5d:
            int r4 = r4 + 1
            goto L12
        L60:
            r10.append(r9, r0, r3)
            int r3 = r9.length
            r8.mark(r3)
            goto La
        L68:
            com.google.android.gms.common.server.response.FastParser$ParseException r8 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r9 = "Unexpected EOF while parsing string"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.a(java.io.BufferedReader, char[], java.lang.StringBuilder, char[]):java.lang.String");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean A(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        int i2;
        HashMap hashMap;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String r2 = r(bufferedReader);
        if (r2 != null) {
            while (r2 != null) {
                FastJsonResponse.Field<?, ?> field = fieldMappings.get(r2);
                if (field == null) {
                    r2 = s(bufferedReader);
                } else {
                    this.f.push(4);
                    int i3 = field.zaa;
                    switch (i3) {
                        case 0:
                            if (field.zab) {
                                fastJsonResponse.zav(field, v(bufferedReader, m));
                            } else {
                                fastJsonResponse.zau(field, m(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 1:
                            if (field.zab) {
                                fastJsonResponse.zag(field, v(bufferedReader, s));
                            } else {
                                fastJsonResponse.zae(field, u(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 2:
                            if (field.zab) {
                                fastJsonResponse.zay(field, v(bufferedReader, n));
                            } else {
                                fastJsonResponse.zax(field, o(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 3:
                            if (field.zab) {
                                fastJsonResponse.zas(field, v(bufferedReader, o));
                            } else {
                                fastJsonResponse.zaq(field, l(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 4:
                            if (field.zab) {
                                fastJsonResponse.zao(field, v(bufferedReader, p));
                            } else {
                                fastJsonResponse.zam(field, k(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 5:
                            if (field.zab) {
                                fastJsonResponse.zac(field, v(bufferedReader, t));
                            } else {
                                fastJsonResponse.zaa(field, t(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 6:
                            if (field.zab) {
                                fastJsonResponse.zaj(field, v(bufferedReader, q));
                            } else {
                                fastJsonResponse.zai(field, z(bufferedReader, false));
                            }
                            i2 = 4;
                            break;
                        case 7:
                            if (field.zab) {
                                fastJsonResponse.zaC(field, v(bufferedReader, r));
                            } else {
                                fastJsonResponse.zaA(field, p(bufferedReader));
                            }
                            i2 = 4;
                            break;
                        case 8:
                            fastJsonResponse.zal(field, Base64Utils.decode(q(bufferedReader, this.c, this.e, l)));
                            i2 = 4;
                            break;
                        case 9:
                            fastJsonResponse.zal(field, Base64Utils.decodeUrlSafe(q(bufferedReader, this.c, this.e, l)));
                            i2 = 4;
                            break;
                        case 10:
                            char j2 = j(bufferedReader);
                            if (j2 == 'n') {
                                y(bufferedReader, g);
                                hashMap = null;
                            } else if (j2 == '{') {
                                this.f.push(1);
                                hashMap = new HashMap();
                                while (true) {
                                    char j3 = j(bufferedReader);
                                    if (j3 != 0) {
                                        if (j3 == '\"') {
                                            String a2 = a(bufferedReader, this.b, this.d, null);
                                            if (j(bufferedReader) == ':') {
                                                if (j(bufferedReader) == '\"') {
                                                    hashMap.put(a2, a(bufferedReader, this.b, this.d, null));
                                                    char j4 = j(bufferedReader);
                                                    if (j4 != ',') {
                                                        if (j4 == '}') {
                                                            x(1);
                                                        } else {
                                                            throw new ParseException("Unexpected character while parsing string map: " + j4);
                                                        }
                                                    }
                                                } else {
                                                    throw new ParseException("Expected String value for key ".concat(String.valueOf(a2)));
                                                }
                                            } else {
                                                throw new ParseException("No map value found for key ".concat(String.valueOf(a2)));
                                            }
                                        } else if (j3 == '}') {
                                            x(1);
                                        }
                                        i2 = 4;
                                        break;
                                    } else {
                                        throw new ParseException("Unexpected EOF");
                                    }
                                }
                            } else {
                                throw new ParseException("Expected start of a map object");
                            }
                            fastJsonResponse.zaB(field, hashMap);
                            i2 = 4;
                        case 11:
                            if (field.zab) {
                                char j5 = j(bufferedReader);
                                if (j5 == 'n') {
                                    y(bufferedReader, g);
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, null);
                                } else {
                                    this.f.push(5);
                                    if (j5 == '[') {
                                        fastJsonResponse.addConcreteTypeArrayInternal(field, field.zae, w(bufferedReader, field));
                                    } else {
                                        throw new ParseException("Expected array start");
                                    }
                                }
                            } else {
                                char j6 = j(bufferedReader);
                                if (j6 == 'n') {
                                    y(bufferedReader, g);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.zae, null);
                                } else {
                                    this.f.push(1);
                                    if (j6 == '{') {
                                        try {
                                            FastJsonResponse zad = field.zad();
                                            A(bufferedReader, zad);
                                            fastJsonResponse.addConcreteTypeInternal(field, field.zae, zad);
                                        } catch (IllegalAccessException e) {
                                            throw new ParseException("Error instantiating inner object", e);
                                        } catch (InstantiationException e2) {
                                            throw new ParseException("Error instantiating inner object", e2);
                                        }
                                    } else {
                                        throw new ParseException("Expected start of object");
                                    }
                                }
                            }
                            i2 = 4;
                            break;
                        default:
                            throw new ParseException("Invalid field type " + i3);
                    }
                    x(i2);
                    x(2);
                    char j7 = j(bufferedReader);
                    if (j7 == ',') {
                        r2 = r(bufferedReader);
                    } else if (j7 != '}') {
                        throw new ParseException("Expected end of object or field separator, but found: " + j7);
                    } else {
                        r2 = null;
                    }
                }
            }
            x(1);
            return true;
        }
        x(1);
        return false;
    }

    public final char j(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.f8367a) != -1) {
            while (Character.isWhitespace(this.f8367a[0])) {
                if (bufferedReader.read(this.f8367a) == -1) {
                    return (char) 0;
                }
            }
            return this.f8367a[0];
        }
        return (char) 0;
    }

    public final double k(BufferedReader bufferedReader) throws ParseException, IOException {
        int n2 = n(bufferedReader, this.c);
        if (n2 == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.c, 0, n2));
    }

    public final float l(BufferedReader bufferedReader) throws ParseException, IOException {
        int n2 = n(bufferedReader, this.c);
        if (n2 == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.c, 0, n2));
    }

    public final int m(BufferedReader bufferedReader) throws ParseException, IOException {
        int i2;
        int i3;
        int n2 = n(bufferedReader, this.c);
        if (n2 == 0) {
            return 0;
        }
        char[] cArr = this.c;
        if (n2 > 0) {
            char c = cArr[0];
            int i4 = c == '-' ? Integer.MIN_VALUE : -2147483647;
            int i5 = c == '-' ? 1 : 0;
            if (i5 < n2) {
                i3 = i5 + 1;
                int digit = Character.digit(cArr[i5], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                i2 = -digit;
            } else {
                i2 = 0;
                i3 = i5;
            }
            while (i3 < n2) {
                int i6 = i3 + 1;
                int digit2 = Character.digit(cArr[i3], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (i2 < -214748364) {
                    throw new ParseException("Number too large");
                }
                int i7 = i2 * 10;
                if (i7 < i4 + digit2) {
                    throw new ParseException("Number too large");
                }
                i2 = i7 - digit2;
                i3 = i6;
            }
            if (i5 != 0) {
                if (i3 > 1) {
                    return i2;
                }
                throw new ParseException("No digits to parse");
            }
            return -i2;
        }
        throw new ParseException("No number to parse");
    }

    public final int n(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i2;
        char j2 = j(bufferedReader);
        if (j2 != 0) {
            if (j2 != ',') {
                if (j2 == 'n') {
                    y(bufferedReader, g);
                    return 0;
                }
                bufferedReader.mark(1024);
                if (j2 == '\"') {
                    i2 = 0;
                    boolean z = false;
                    while (i2 < 1024 && bufferedReader.read(cArr, i2, 1) != -1) {
                        char c = cArr[i2];
                        if (Character.isISOControl(c)) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                        if (c == '\"') {
                            if (!z) {
                                bufferedReader.reset();
                                bufferedReader.skip(i2 + 1);
                                return i2;
                            }
                        } else if (c == '\\') {
                            z = !z;
                            i2++;
                        }
                        z = false;
                        i2++;
                    }
                } else {
                    cArr[0] = j2;
                    i2 = 1;
                    while (i2 < 1024 && bufferedReader.read(cArr, i2, 1) != -1) {
                        char c2 = cArr[i2];
                        if (c2 == '}' || c2 == ',' || Character.isWhitespace(c2) || cArr[i2] == ']') {
                            bufferedReader.reset();
                            bufferedReader.skip(i2 - 1);
                            cArr[i2] = 0;
                            return i2;
                        }
                        i2++;
                    }
                }
                if (i2 == 1024) {
                    throw new ParseException("Absurdly long value");
                }
                throw new ParseException("Unexpected EOF");
            }
            throw new ParseException("Missing value");
        }
        throw new ParseException("Unexpected EOF");
    }

    public final long o(BufferedReader bufferedReader) throws ParseException, IOException {
        long j2;
        int i2;
        int n2 = n(bufferedReader, this.c);
        if (n2 == 0) {
            return 0L;
        }
        char[] cArr = this.c;
        if (n2 > 0) {
            char c = cArr[0];
            long j3 = c == '-' ? Long.MIN_VALUE : -9223372036854775807L;
            int i3 = c == '-' ? 1 : 0;
            if (i3 < n2) {
                i2 = i3 + 1;
                int digit = Character.digit(cArr[i3], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                j2 = -digit;
            } else {
                j2 = 0;
                i2 = i3;
            }
            while (i2 < n2) {
                int i4 = i2 + 1;
                int digit2 = Character.digit(cArr[i2], 10);
                if (digit2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                if (j2 < _BufferKt.OVERFLOW_ZONE) {
                    throw new ParseException("Number too large");
                }
                long j4 = j2 * 10;
                int i5 = n2;
                long j5 = digit2;
                if (j4 < j3 + j5) {
                    throw new ParseException("Number too large");
                }
                j2 = j4 - j5;
                n2 = i5;
                i2 = i4;
            }
            if (i3 != 0) {
                if (i2 > 1) {
                    return j2;
                }
                throw new ParseException("No digits to parse");
            }
            return -j2;
        }
        throw new ParseException("No number to parse");
    }

    @Nullable
    public final String p(BufferedReader bufferedReader) throws ParseException, IOException {
        return q(bufferedReader, this.b, this.d, null);
    }

    @KeepForSdk
    public void parse(@NonNull InputStream inputStream, @NonNull T t2) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.f.push(0);
                char j2 = j(bufferedReader);
                if (j2 != 0) {
                    if (j2 == '[') {
                        this.f.push(5);
                        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t2.getFieldMappings();
                        if (fieldMappings.size() == 1) {
                            FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                            t2.addConcreteTypeArrayInternal(value, value.zae, w(bufferedReader, value));
                        } else {
                            throw new ParseException("Object array response class must have a single Field");
                        }
                    } else if (j2 == '{') {
                        this.f.push(1);
                        A(bufferedReader, t2);
                    } else {
                        throw new ParseException("Unexpected token: " + j2);
                    }
                    x(0);
                    try {
                        bufferedReader.close();
                        return;
                    } catch (IOException unused) {
                        Log.w("FastParser", "Failed to close reader while parsing.");
                        return;
                    }
                }
                throw new ParseException("No data to parse");
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    @Nullable
    public final String q(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, @Nullable char[] cArr2) throws ParseException, IOException {
        char j2 = j(bufferedReader);
        if (j2 != '\"') {
            if (j2 == 'n') {
                y(bufferedReader, g);
                return null;
            }
            throw new ParseException("Expected string");
        }
        return a(bufferedReader, cArr, sb, cArr2);
    }

    @Nullable
    public final String r(BufferedReader bufferedReader) throws ParseException, IOException {
        this.f.push(2);
        char j2 = j(bufferedReader);
        if (j2 == '\"') {
            this.f.push(3);
            String a2 = a(bufferedReader, this.b, this.d, null);
            x(3);
            if (j(bufferedReader) == ':') {
                return a2;
            }
            throw new ParseException("Expected key/value separator");
        } else if (j2 == ']') {
            x(2);
            x(1);
            x(5);
            return null;
        } else if (j2 == '}') {
            x(2);
            return null;
        } else {
            throw new ParseException("Unexpected token: " + j2);
        }
    }

    @Nullable
    public final String s(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char j2 = j(bufferedReader);
        int i2 = 1;
        if (j2 == '\"') {
            if (bufferedReader.read(this.f8367a) != -1) {
                char c = this.f8367a[0];
                boolean z = false;
                do {
                    if (c == '\"') {
                        if (z) {
                            c = '\"';
                            z = true;
                        }
                    }
                    z = c == '\\' ? !z : false;
                    if (bufferedReader.read(this.f8367a) != -1) {
                        c = this.f8367a[0];
                    } else {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                } while (!Character.isISOControl(c));
                throw new ParseException("Unexpected control character while reading string");
            }
            throw new ParseException("Unexpected EOF while parsing string");
        } else if (j2 == ',') {
            throw new ParseException("Missing value");
        } else {
            if (j2 == '[') {
                this.f.push(5);
                bufferedReader.mark(32);
                if (j(bufferedReader) == ']') {
                    x(5);
                } else {
                    bufferedReader.reset();
                    boolean z2 = false;
                    boolean z3 = false;
                    while (i2 > 0) {
                        char j3 = j(bufferedReader);
                        if (j3 != 0) {
                            if (Character.isISOControl(j3)) {
                                throw new ParseException("Unexpected control character while reading array");
                            }
                            if (j3 == '\"') {
                                if (!z3) {
                                    z2 = !z2;
                                }
                                j3 = '\"';
                            }
                            if (j3 == '[') {
                                if (!z2) {
                                    i2++;
                                }
                                j3 = '[';
                            }
                            if (j3 == ']' && !z2) {
                                i2--;
                            }
                            z3 = (j3 == '\\' && z2) ? !z3 : false;
                        } else {
                            throw new ParseException("Unexpected EOF while parsing array");
                        }
                    }
                    x(5);
                }
            } else if (j2 != '{') {
                bufferedReader.reset();
                n(bufferedReader, this.c);
            } else {
                this.f.push(1);
                bufferedReader.mark(32);
                char j4 = j(bufferedReader);
                if (j4 == '}') {
                    x(1);
                } else if (j4 == '\"') {
                    bufferedReader.reset();
                    r(bufferedReader);
                    do {
                    } while (s(bufferedReader) != null);
                    x(1);
                } else {
                    throw new ParseException("Unexpected token " + j4);
                }
            }
        }
        char j5 = j(bufferedReader);
        if (j5 == ',') {
            x(2);
            return r(bufferedReader);
        } else if (j5 == '}') {
            x(2);
            return null;
        } else {
            throw new ParseException("Unexpected token " + j5);
        }
    }

    @Nullable
    public final BigDecimal t(BufferedReader bufferedReader) throws ParseException, IOException {
        int n2 = n(bufferedReader, this.c);
        if (n2 == 0) {
            return null;
        }
        return new BigDecimal(new String(this.c, 0, n2));
    }

    @Nullable
    public final BigInteger u(BufferedReader bufferedReader) throws ParseException, IOException {
        int n2 = n(bufferedReader, this.c);
        if (n2 == 0) {
            return null;
        }
        return new BigInteger(new String(this.c, 0, n2));
    }

    @Nullable
    public final ArrayList v(BufferedReader bufferedReader, i iVar) throws ParseException, IOException {
        char j2 = j(bufferedReader);
        if (j2 == 'n') {
            y(bufferedReader, g);
            return null;
        } else if (j2 == '[') {
            this.f.push(5);
            ArrayList arrayList = new ArrayList();
            while (true) {
                bufferedReader.mark(1024);
                char j3 = j(bufferedReader);
                if (j3 == 0) {
                    throw new ParseException("Unexpected EOF");
                }
                if (j3 != ',') {
                    if (j3 != ']') {
                        bufferedReader.reset();
                        arrayList.add(iVar.a(this, bufferedReader));
                    } else {
                        x(5);
                        return arrayList;
                    }
                }
            }
        } else {
            throw new ParseException("Expected start of array");
        }
    }

    @Nullable
    public final ArrayList w(BufferedReader bufferedReader, FastJsonResponse.Field field) throws ParseException, IOException {
        ArrayList arrayList = new ArrayList();
        char j2 = j(bufferedReader);
        if (j2 == ']') {
            x(5);
            return arrayList;
        } else if (j2 == 'n') {
            y(bufferedReader, g);
            x(5);
            return null;
        } else if (j2 == '{') {
            this.f.push(1);
            while (true) {
                try {
                    FastJsonResponse zad = field.zad();
                    if (!A(bufferedReader, zad)) {
                        return arrayList;
                    }
                    arrayList.add(zad);
                    char j3 = j(bufferedReader);
                    if (j3 != ',') {
                        if (j3 == ']') {
                            x(5);
                            return arrayList;
                        }
                        throw new ParseException("Unexpected token: " + j3);
                    } else if (j(bufferedReader) == '{') {
                        this.f.push(1);
                    } else {
                        throw new ParseException("Expected start of next object in array");
                    }
                } catch (IllegalAccessException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (InstantiationException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        } else {
            throw new ParseException("Unexpected token: " + j2);
        }
    }

    public final void x(int i2) throws ParseException {
        if (!this.f.isEmpty()) {
            int intValue = ((Integer) this.f.pop()).intValue();
            if (intValue == i2) {
                return;
            }
            throw new ParseException("Expected state " + i2 + " but had " + intValue);
        }
        throw new ParseException("Expected state " + i2 + " but had empty stack");
    }

    public final void y(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i2 = 0;
        while (true) {
            int length = cArr.length;
            if (i2 >= length) {
                return;
            }
            int read = bufferedReader.read(this.b, 0, length - i2);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i3 = 0; i3 < read; i3++) {
                if (cArr[i3 + i2] != this.b[i3]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i2 += read;
        }
    }

    public final boolean z(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        char[] cArr;
        char j2 = j(bufferedReader);
        if (j2 == '\"') {
            if (!z) {
                return z(bufferedReader, true);
            }
            throw new ParseException("No boolean value found in string");
        } else if (j2 == 'f') {
            if (z) {
                cArr = k;
            } else {
                cArr = j;
            }
            y(bufferedReader, cArr);
            return false;
        } else if (j2 == 'n') {
            y(bufferedReader, g);
            return false;
        } else if (j2 == 't') {
            y(bufferedReader, z ? i : h);
            return true;
        } else {
            throw new ParseException("Unexpected token: " + j2);
        }
    }
}
