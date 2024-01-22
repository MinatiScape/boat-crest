package com.airbnb.lottie.parser.moshi;

import androidx.annotation.Nullable;
import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.Objects;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
/* loaded from: classes.dex */
public final class d extends JsonReader {
    public static final ByteString u = ByteString.encodeUtf8("'\\");
    public static final ByteString v = ByteString.encodeUtf8("\"\\");
    public static final ByteString w = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    public static final ByteString x = ByteString.encodeUtf8("\n\r");
    public static final ByteString y = ByteString.encodeUtf8("*/");
    public final BufferedSource o;
    public final Buffer p;
    public int q = 0;
    public long r;
    public int s;
    @Nullable
    public String t;

    public d(BufferedSource bufferedSource) {
        Objects.requireNonNull(bufferedSource, "source == null");
        this.o = bufferedSource;
        this.p = bufferedSource.buffer();
        b(6);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginArray() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 3) {
            b(1);
            this.k[this.h - 1] = 0;
            this.q = 0;
            return;
        }
        throw new a("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void beginObject() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 1) {
            b(3);
            this.q = 0;
            return;
        }
        throw new a("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPath());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.q = 0;
        this.i[0] = 8;
        this.h = 1;
        this.p.clear();
        this.o.close();
    }

    public final void e() throws IOException {
        if (!this.l) {
            throw d("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void endArray() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 4) {
            int i2 = this.h - 1;
            this.h = i2;
            int[] iArr = this.k;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.q = 0;
            return;
        }
        throw new a("Expected END_ARRAY but was " + peek() + " at path " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void endObject() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 2) {
            int i2 = this.h - 1;
            this.h = i2;
            this.j[i2] = null;
            int[] iArr = this.k;
            int i3 = i2 - 1;
            iArr[i3] = iArr[i3] + 1;
            this.q = 0;
            return;
        }
        throw new a("Expected END_OBJECT but was " + peek() + " at path " + getPath());
    }

    public final int f() throws IOException {
        int[] iArr = this.i;
        int i = this.h;
        int i2 = iArr[i - 1];
        if (i2 == 1) {
            iArr[i - 1] = 2;
        } else if (i2 == 2) {
            int i3 = i(true);
            this.p.readByte();
            if (i3 != 44) {
                if (i3 != 59) {
                    if (i3 == 93) {
                        this.q = 4;
                        return 4;
                    }
                    throw d("Unterminated array");
                }
                e();
            }
        } else if (i2 == 3 || i2 == 5) {
            iArr[i - 1] = 4;
            if (i2 == 5) {
                int i4 = i(true);
                this.p.readByte();
                if (i4 != 44) {
                    if (i4 != 59) {
                        if (i4 == 125) {
                            this.q = 2;
                            return 2;
                        }
                        throw d("Unterminated object");
                    }
                    e();
                }
            }
            int i5 = i(true);
            if (i5 == 34) {
                this.p.readByte();
                this.q = 13;
                return 13;
            } else if (i5 == 39) {
                this.p.readByte();
                e();
                this.q = 12;
                return 12;
            } else if (i5 != 125) {
                e();
                if (h((char) i5)) {
                    this.q = 14;
                    return 14;
                }
                throw d("Expected name");
            } else if (i2 != 5) {
                this.p.readByte();
                this.q = 2;
                return 2;
            } else {
                throw d("Expected name");
            }
        } else if (i2 == 4) {
            iArr[i - 1] = 5;
            int i6 = i(true);
            this.p.readByte();
            if (i6 != 58) {
                if (i6 == 61) {
                    e();
                    if (this.o.request(1L) && this.p.getByte(0L) == 62) {
                        this.p.readByte();
                    }
                } else {
                    throw d("Expected ':'");
                }
            }
        } else if (i2 == 6) {
            iArr[i - 1] = 7;
        } else if (i2 == 7) {
            if (i(false) == -1) {
                this.q = 18;
                return 18;
            }
            e();
        } else if (i2 == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int i7 = i(true);
        if (i7 == 34) {
            this.p.readByte();
            this.q = 9;
            return 9;
        } else if (i7 == 39) {
            e();
            this.p.readByte();
            this.q = 8;
            return 8;
        } else {
            if (i7 != 44 && i7 != 59) {
                if (i7 == 91) {
                    this.p.readByte();
                    this.q = 3;
                    return 3;
                } else if (i7 != 93) {
                    if (i7 != 123) {
                        int l = l();
                        if (l != 0) {
                            return l;
                        }
                        int m = m();
                        if (m != 0) {
                            return m;
                        }
                        if (h(this.p.getByte(0L))) {
                            e();
                            this.q = 10;
                            return 10;
                        }
                        throw d("Expected value");
                    }
                    this.p.readByte();
                    this.q = 1;
                    return 1;
                } else if (i2 == 1) {
                    this.p.readByte();
                    this.q = 4;
                    return 4;
                }
            }
            if (i2 != 1 && i2 != 2) {
                throw d("Unexpected value");
            }
            e();
            this.q = 7;
            return 7;
        }
    }

    public final int g(String str, JsonReader.Options options) {
        int length = options.f2085a.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(options.f2085a[i])) {
                this.q = 0;
                this.j[this.h - 1] = str;
                return i;
            }
        }
        return -1;
    }

    public final boolean h(int i) throws IOException {
        if (i == 9 || i == 10 || i == 12 || i == 13 || i == 32) {
            return false;
        }
        if (i != 35) {
            if (i == 44) {
                return false;
            }
            if (i != 47 && i != 61) {
                if (i == 123 || i == 125 || i == 58) {
                    return false;
                }
                if (i != 59) {
                    switch (i) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        e();
        return false;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean hasNext() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        return (i == 2 || i == 4 || i == 18) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0025, code lost:
        r6.p.skip(r3 - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002f, code lost:
        if (r1 != 47) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        if (r6.o.request(2) != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003b, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003c, code lost:
        e();
        r3 = r6.p.getByte(1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0049, code lost:
        if (r3 == 42) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004b, code lost:
        if (r3 == 47) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x004d, code lost:
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004e, code lost:
        r6.p.readByte();
        r6.p.readByte();
        q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x005c, code lost:
        r6.p.readByte();
        r6.p.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
        if (p() == false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0073, code lost:
        throw d("Unterminated comment");
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0076, code lost:
        if (r1 != 35) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
        e();
        q();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x007f, code lost:
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int i(boolean r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
        L1:
            r1 = r0
        L2:
            okio.BufferedSource r2 = r6.o
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.request(r4)
            if (r2 == 0) goto L82
            okio.Buffer r2 = r6.p
            long r4 = (long) r1
            byte r1 = r2.getByte(r4)
            r2 = 10
            if (r1 == r2) goto L80
            r2 = 32
            if (r1 == r2) goto L80
            r2 = 13
            if (r1 == r2) goto L80
            r2 = 9
            if (r1 != r2) goto L25
            goto L80
        L25:
            okio.Buffer r2 = r6.p
            int r3 = r3 + (-1)
            long r3 = (long) r3
            r2.skip(r3)
            r2 = 47
            if (r1 != r2) goto L74
            okio.BufferedSource r3 = r6.o
            r4 = 2
            boolean r3 = r3.request(r4)
            if (r3 != 0) goto L3c
            return r1
        L3c:
            r6.e()
            okio.Buffer r3 = r6.p
            r4 = 1
            byte r3 = r3.getByte(r4)
            r4 = 42
            if (r3 == r4) goto L5c
            if (r3 == r2) goto L4e
            return r1
        L4e:
            okio.Buffer r1 = r6.p
            r1.readByte()
            okio.Buffer r1 = r6.p
            r1.readByte()
            r6.q()
            goto L1
        L5c:
            okio.Buffer r1 = r6.p
            r1.readByte()
            okio.Buffer r1 = r6.p
            r1.readByte()
            boolean r1 = r6.p()
            if (r1 == 0) goto L6d
            goto L1
        L6d:
            java.lang.String r7 = "Unterminated comment"
            com.airbnb.lottie.parser.moshi.b r7 = r6.d(r7)
            throw r7
        L74:
            r2 = 35
            if (r1 != r2) goto L7f
            r6.e()
            r6.q()
            goto L1
        L7f:
            return r1
        L80:
            r1 = r3
            goto L2
        L82:
            if (r7 != 0) goto L86
            r7 = -1
            return r7
        L86:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.d.i(boolean):int");
    }

    public final String j(ByteString byteString) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long indexOfElement = this.o.indexOfElement(byteString);
            if (indexOfElement != -1) {
                if (this.p.getByte(indexOfElement) != 92) {
                    if (sb == null) {
                        String readUtf8 = this.p.readUtf8(indexOfElement);
                        this.p.readByte();
                        return readUtf8;
                    }
                    sb.append(this.p.readUtf8(indexOfElement));
                    this.p.readByte();
                    return sb.toString();
                }
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.p.readUtf8(indexOfElement));
                this.p.readByte();
                sb.append(n());
            } else {
                throw d("Unterminated string");
            }
        }
    }

    public final String k() throws IOException {
        long indexOfElement = this.o.indexOfElement(w);
        return indexOfElement != -1 ? this.p.readUtf8(indexOfElement) : this.p.readUtf8();
    }

    public final int l() throws IOException {
        int i;
        String str;
        String str2;
        byte b = this.p.getByte(0L);
        if (b == 116 || b == 84) {
            i = 5;
            str = "true";
            str2 = "TRUE";
        } else if (b == 102 || b == 70) {
            i = 6;
            str = "false";
            str2 = "FALSE";
        } else if (b != 110 && b != 78) {
            return 0;
        } else {
            i = 7;
            str = "null";
            str2 = "NULL";
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (!this.o.request(i3)) {
                return 0;
            }
            byte b2 = this.p.getByte(i2);
            if (b2 != str.charAt(i2) && b2 != str2.charAt(i2)) {
                return 0;
            }
            i2 = i3;
        }
        if (this.o.request(length + 1) && h(this.p.getByte(length))) {
            return 0;
        }
        this.p.skip(length);
        this.q = i;
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0082, code lost:
        if (h(r11) != false) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0084, code lost:
        if (r6 != 2) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0086, code lost:
        if (r7 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x008c, code lost:
        if (r8 != Long.MIN_VALUE) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x008e, code lost:
        if (r10 == false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0092, code lost:
        if (r8 != 0) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x0094, code lost:
        if (r10 != false) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0096, code lost:
        if (r10 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0099, code lost:
        r8 = -r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x009a, code lost:
        r16.r = r8;
        r16.p.skip(r5);
        r16.q = 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00a6, code lost:
        return 16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a7, code lost:
        if (r6 == 2) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x00aa, code lost:
        if (r6 == 4) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00ad, code lost:
        if (r6 != 7) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b0, code lost:
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b2, code lost:
        r16.s = r5;
        r16.q = 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00b8, code lost:
        return 17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00b9, code lost:
        return 0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int m() throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.d.m():int");
    }

    public final char n() throws IOException {
        int i;
        int i2;
        if (this.o.request(1L)) {
            byte readByte = this.p.readByte();
            if (readByte == 10 || readByte == 34 || readByte == 39 || readByte == 47 || readByte == 92) {
                return (char) readByte;
            }
            if (readByte != 98) {
                if (readByte != 102) {
                    if (readByte != 110) {
                        if (readByte != 114) {
                            if (readByte != 116) {
                                if (readByte != 117) {
                                    if (this.l) {
                                        return (char) readByte;
                                    }
                                    throw d("Invalid escape sequence: \\" + ((char) readByte));
                                } else if (this.o.request(4L)) {
                                    char c = 0;
                                    for (int i3 = 0; i3 < 4; i3++) {
                                        byte b = this.p.getByte(i3);
                                        char c2 = (char) (c << 4);
                                        if (b < 48 || b > 57) {
                                            if (b >= 97 && b <= 102) {
                                                i = b - 97;
                                            } else if (b < 65 || b > 70) {
                                                throw d("\\u" + this.p.readUtf8(4L));
                                            } else {
                                                i = b - 65;
                                            }
                                            i2 = i + 10;
                                        } else {
                                            i2 = b - 48;
                                        }
                                        c = (char) (c2 + i2);
                                    }
                                    this.p.skip(4L);
                                    return c;
                                } else {
                                    throw new EOFException("Unterminated escape sequence at path " + getPath());
                                }
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
        throw d("Unterminated escape sequence");
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public boolean nextBoolean() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 5) {
            this.q = 0;
            int[] iArr = this.k;
            int i2 = this.h - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.q = 0;
            int[] iArr2 = this.k;
            int i3 = this.h - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new a("Expected a boolean but was " + peek() + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public double nextDouble() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 16) {
            this.q = 0;
            int[] iArr = this.k;
            int i2 = this.h - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.r;
        }
        if (i == 17) {
            this.t = this.p.readUtf8(this.s);
        } else if (i == 9) {
            this.t = j(v);
        } else if (i == 8) {
            this.t = j(u);
        } else if (i == 10) {
            this.t = k();
        } else if (i != 11) {
            throw new a("Expected a double but was " + peek() + " at path " + getPath());
        }
        this.q = 11;
        try {
            double parseDouble = Double.parseDouble(this.t);
            if (!this.l && (Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
                throw new b("JSON forbids NaN and infinities: " + parseDouble + " at path " + getPath());
            }
            this.t = null;
            this.q = 0;
            int[] iArr2 = this.k;
            int i3 = this.h - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        } catch (NumberFormatException unused) {
            throw new a("Expected a double but was " + this.t + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int nextInt() throws IOException {
        String j;
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 16) {
            long j2 = this.r;
            int i2 = (int) j2;
            if (j2 == i2) {
                this.q = 0;
                int[] iArr = this.k;
                int i3 = this.h - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new a("Expected an int but was " + this.r + " at path " + getPath());
        }
        if (i == 17) {
            this.t = this.p.readUtf8(this.s);
        } else if (i == 9 || i == 8) {
            if (i == 9) {
                j = j(v);
            } else {
                j = j(u);
            }
            this.t = j;
            try {
                int parseInt = Integer.parseInt(j);
                this.q = 0;
                int[] iArr2 = this.k;
                int i4 = this.h - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (i != 11) {
            throw new a("Expected an int but was " + peek() + " at path " + getPath());
        }
        this.q = 11;
        try {
            double parseDouble = Double.parseDouble(this.t);
            int i5 = (int) parseDouble;
            if (i5 == parseDouble) {
                this.t = null;
                this.q = 0;
                int[] iArr3 = this.k;
                int i6 = this.h - 1;
                iArr3[i6] = iArr3[i6] + 1;
                return i5;
            }
            throw new a("Expected an int but was " + this.t + " at path " + getPath());
        } catch (NumberFormatException unused2) {
            throw new a("Expected an int but was " + this.t + " at path " + getPath());
        }
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextName() throws IOException {
        String str;
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 14) {
            str = k();
        } else if (i == 13) {
            str = j(v);
        } else if (i == 12) {
            str = j(u);
        } else if (i == 15) {
            str = this.t;
        } else {
            throw new a("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.q = 0;
        this.j[this.h - 1] = str;
        return str;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public String nextString() throws IOException {
        String readUtf8;
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i == 10) {
            readUtf8 = k();
        } else if (i == 9) {
            readUtf8 = j(v);
        } else if (i == 8) {
            readUtf8 = j(u);
        } else if (i == 11) {
            readUtf8 = this.t;
            this.t = null;
        } else if (i == 16) {
            readUtf8 = Long.toString(this.r);
        } else if (i == 17) {
            readUtf8 = this.p.readUtf8(this.s);
        } else {
            throw new a("Expected a string but was " + peek() + " at path " + getPath());
        }
        this.q = 0;
        int[] iArr = this.k;
        int i2 = this.h - 1;
        iArr[i2] = iArr[i2] + 1;
        return readUtf8;
    }

    public final void o(ByteString byteString) throws IOException {
        while (true) {
            long indexOfElement = this.o.indexOfElement(byteString);
            if (indexOfElement != -1) {
                if (this.p.getByte(indexOfElement) == 92) {
                    this.p.skip(indexOfElement + 1);
                    n();
                } else {
                    this.p.skip(indexOfElement + 1);
                    return;
                }
            } else {
                throw d("Unterminated string");
            }
        }
    }

    public final boolean p() throws IOException {
        BufferedSource bufferedSource = this.o;
        ByteString byteString = y;
        long indexOf = bufferedSource.indexOf(byteString);
        boolean z = indexOf != -1;
        Buffer buffer = this.p;
        buffer.skip(z ? indexOf + byteString.size() : buffer.size());
        return z;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public JsonReader.Token peek() throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        switch (i) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case 17:
                return JsonReader.Token.NUMBER;
            case 18:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void q() throws IOException {
        long indexOfElement = this.o.indexOfElement(x);
        Buffer buffer = this.p;
        buffer.skip(indexOfElement != -1 ? indexOfElement + 1 : buffer.size());
    }

    public final void r() throws IOException {
        long indexOfElement = this.o.indexOfElement(w);
        Buffer buffer = this.p;
        if (indexOfElement == -1) {
            indexOfElement = buffer.size();
        }
        buffer.skip(indexOfElement);
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public int selectName(JsonReader.Options options) throws IOException {
        int i = this.q;
        if (i == 0) {
            i = f();
        }
        if (i < 12 || i > 15) {
            return -1;
        }
        if (i == 15) {
            return g(this.t, options);
        }
        int select = this.o.select(options.b);
        if (select != -1) {
            this.q = 0;
            this.j[this.h - 1] = options.f2085a[select];
            return select;
        }
        String str = this.j[this.h - 1];
        String nextName = nextName();
        int g = g(nextName, options);
        if (g == -1) {
            this.q = 15;
            this.t = nextName;
            this.j[this.h - 1] = str;
        }
        return g;
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipName() throws IOException {
        if (!this.m) {
            int i = this.q;
            if (i == 0) {
                i = f();
            }
            if (i == 14) {
                r();
            } else if (i == 13) {
                o(v);
            } else if (i == 12) {
                o(u);
            } else if (i != 15) {
                throw new a("Expected a name but was " + peek() + " at path " + getPath());
            }
            this.q = 0;
            this.j[this.h - 1] = "null";
            return;
        }
        throw new a("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    @Override // com.airbnb.lottie.parser.moshi.JsonReader
    public void skipValue() throws IOException {
        if (!this.m) {
            int i = 0;
            do {
                int i2 = this.q;
                if (i2 == 0) {
                    i2 = f();
                }
                if (i2 == 3) {
                    b(1);
                } else if (i2 == 1) {
                    b(3);
                } else {
                    if (i2 == 4) {
                        i--;
                        if (i >= 0) {
                            this.h--;
                        } else {
                            throw new a("Expected a value but was " + peek() + " at path " + getPath());
                        }
                    } else if (i2 == 2) {
                        i--;
                        if (i >= 0) {
                            this.h--;
                        } else {
                            throw new a("Expected a value but was " + peek() + " at path " + getPath());
                        }
                    } else if (i2 == 14 || i2 == 10) {
                        r();
                    } else if (i2 == 9 || i2 == 13) {
                        o(v);
                    } else if (i2 == 8 || i2 == 12) {
                        o(u);
                    } else if (i2 == 17) {
                        this.p.skip(this.s);
                    } else if (i2 == 18) {
                        throw new a("Expected a value but was " + peek() + " at path " + getPath());
                    }
                    this.q = 0;
                }
                i++;
                this.q = 0;
            } while (i != 0);
            int[] iArr = this.k;
            int i3 = this.h;
            int i4 = i3 - 1;
            iArr[i4] = iArr[i4] + 1;
            this.j[i3 - 1] = "null";
            return;
        }
        throw new a("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    public String toString() {
        return "JsonReader(" + this.o + ")";
    }
}
