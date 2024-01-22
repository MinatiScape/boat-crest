package org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import kotlin.time.DurationKt;
/* loaded from: classes13.dex */
public class JSONTokener {

    /* renamed from: a  reason: collision with root package name */
    public long f15563a;
    public boolean b;
    public long c;
    public long d;
    public char e;
    public final Reader f;
    public boolean g;
    public long h;

    public JSONTokener(Reader reader) {
        this.f = reader.markSupported() ? reader : new BufferedReader(reader);
        this.b = false;
        this.g = false;
        this.e = (char) 0;
        this.c = 0L;
        this.f15563a = 1L;
        this.h = 0L;
        this.d = 1L;
    }

    public static int dehexchar(char c) {
        if (c < '0' || c > '9') {
            if (c < 'A' || c > 'F') {
                if (c < 'a' || c > 'f') {
                    return -1;
                }
                return c - 'W';
            }
            return c - '7';
        }
        return c - '0';
    }

    public final void a() {
        this.c--;
        char c = this.e;
        if (c != '\r' && c != '\n') {
            long j = this.f15563a;
            if (j > 0) {
                this.f15563a = j - 1;
                return;
            }
            return;
        }
        this.d--;
        this.f15563a = this.h;
    }

    public final void b(int i) {
        if (i > 0) {
            this.c++;
            if (i == 13) {
                this.d++;
                this.h = this.f15563a;
                this.f15563a = 0L;
            } else if (i == 10) {
                if (this.e != '\r') {
                    this.d++;
                    this.h = this.f15563a;
                }
                this.f15563a = 0L;
            } else {
                this.f15563a++;
            }
        }
    }

    public void back() throws JSONException {
        if (!this.g && this.c > 0) {
            a();
            this.g = true;
            this.b = false;
            return;
        }
        throw new JSONException("Stepping back two steps is not supported");
    }

    public boolean end() {
        return this.b && !this.g;
    }

    public boolean more() throws JSONException {
        if (this.g) {
            return true;
        }
        try {
            this.f.mark(1);
            try {
                if (this.f.read() <= 0) {
                    this.b = true;
                    return false;
                }
                this.f.reset();
                return true;
            } catch (IOException e) {
                throw new JSONException("Unable to read the next character from the stream", e);
            }
        } catch (IOException e2) {
            throw new JSONException("Unable to preserve stream position", e2);
        }
    }

    public char next() throws JSONException {
        int read;
        if (this.g) {
            this.g = false;
            read = this.e;
        } else {
            try {
                read = this.f.read();
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        if (read <= 0) {
            this.b = true;
            return (char) 0;
        }
        b(read);
        char c = (char) read;
        this.e = c;
        return c;
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == 0) {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x008e, code lost:
        throw syntaxError("Unterminated string");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String nextString(char r7) throws org.json.JSONException {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            char r1 = r6.next()
            if (r1 == 0) goto L88
            r2 = 10
            if (r1 == r2) goto L88
            r3 = 13
            if (r1 == r3) goto L88
            r4 = 92
            if (r1 == r4) goto L22
            if (r1 != r7) goto L1e
            java.lang.String r7 = r0.toString()
            return r7
        L1e:
            r0.append(r1)
            goto L5
        L22:
            char r1 = r6.next()
            r5 = 34
            if (r1 == r5) goto L83
            r5 = 39
            if (r1 == r5) goto L83
            r5 = 47
            if (r1 == r5) goto L83
            if (r1 == r4) goto L83
            r4 = 98
            if (r1 == r4) goto L7d
            r4 = 102(0x66, float:1.43E-43)
            if (r1 == r4) goto L77
            r4 = 110(0x6e, float:1.54E-43)
            if (r1 == r4) goto L73
            r2 = 114(0x72, float:1.6E-43)
            if (r1 == r2) goto L6f
            r2 = 116(0x74, float:1.63E-43)
            if (r1 == r2) goto L69
            r2 = 117(0x75, float:1.64E-43)
            java.lang.String r3 = "Illegal escape."
            if (r1 != r2) goto L64
            r1 = 4
            java.lang.String r1 = r6.next(r1)     // Catch: java.lang.NumberFormatException -> L5e
            r2 = 16
            int r1 = java.lang.Integer.parseInt(r1, r2)     // Catch: java.lang.NumberFormatException -> L5e
            char r1 = (char) r1     // Catch: java.lang.NumberFormatException -> L5e
            r0.append(r1)     // Catch: java.lang.NumberFormatException -> L5e
            goto L5
        L5e:
            r7 = move-exception
            org.json.JSONException r7 = r6.syntaxError(r3, r7)
            throw r7
        L64:
            org.json.JSONException r7 = r6.syntaxError(r3)
            throw r7
        L69:
            r1 = 9
            r0.append(r1)
            goto L5
        L6f:
            r0.append(r3)
            goto L5
        L73:
            r0.append(r2)
            goto L5
        L77:
            r1 = 12
            r0.append(r1)
            goto L5
        L7d:
            r1 = 8
            r0.append(r1)
            goto L5
        L83:
            r0.append(r1)
            goto L5
        L88:
            java.lang.String r7 = "Unterminated string"
            org.json.JSONException r7 = r6.syntaxError(r7)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextString(char):java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001c, code lost:
        back();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String nextTo(char r4) throws org.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            char r1 = r3.next()
            if (r1 == r4) goto L1a
            if (r1 == 0) goto L1a
            r2 = 10
            if (r1 == r2) goto L1a
            r2 = 13
            if (r1 != r2) goto L16
            goto L1a
        L16:
            r0.append(r1)
            goto L5
        L1a:
            if (r1 == 0) goto L1f
            r3.back()
        L1f:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.trim()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextTo(char):java.lang.String");
    }

    public Object nextValue() throws JSONException {
        char nextClean = nextClean();
        if (nextClean == '\"' || nextClean == '\'') {
            return nextString(nextClean);
        }
        if (nextClean == '[') {
            back();
            return new JSONArray(this);
        } else if (nextClean != '{') {
            StringBuilder sb = new StringBuilder();
            while (nextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(nextClean) < 0) {
                sb.append(nextClean);
                nextClean = next();
            }
            back();
            String trim = sb.toString().trim();
            if (!"".equals(trim)) {
                return JSONObject.stringToValue(trim);
            }
            throw syntaxError("Missing value");
        } else {
            back();
            return new JSONObject(this);
        }
    }

    public char skipTo(char c) throws JSONException {
        char next;
        try {
            long j = this.c;
            long j2 = this.f15563a;
            long j3 = this.d;
            this.f.mark(DurationKt.NANOS_IN_MILLIS);
            do {
                next = next();
                if (next == 0) {
                    this.f.reset();
                    this.c = j;
                    this.f15563a = j2;
                    this.d = j3;
                    return (char) 0;
                }
            } while (next != c);
            this.f.mark(1);
            back();
            return next;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public JSONException syntaxError(String str) {
        return new JSONException(str + toString());
    }

    public String toString() {
        return " at " + this.c + " [character " + this.f15563a + " line " + this.d + "]";
    }

    public JSONException syntaxError(String str, Throwable th) {
        return new JSONException(str + toString(), th);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0020, code lost:
        back();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String nextTo(java.lang.String r4) throws org.json.JSONException {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            char r1 = r3.next()
            int r2 = r4.indexOf(r1)
            if (r2 >= 0) goto L1e
            if (r1 == 0) goto L1e
            r2 = 10
            if (r1 == r2) goto L1e
            r2 = 13
            if (r1 != r2) goto L1a
            goto L1e
        L1a:
            r0.append(r1)
            goto L5
        L1e:
            if (r1 == 0) goto L23
            r3.back()
        L23:
            java.lang.String r4 = r0.toString()
            java.lang.String r4 = r4.trim()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONTokener.nextTo(java.lang.String):java.lang.String");
    }

    public char next(char c) throws JSONException {
        char next = next();
        if (next != c) {
            if (next > 0) {
                throw syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
            }
            throw syntaxError("Expected '" + c + "' and instead saw ''");
        }
        return next;
    }

    public JSONTokener(InputStream inputStream) {
        this(new InputStreamReader(inputStream));
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }
}
