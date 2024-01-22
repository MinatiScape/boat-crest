package com.google.common.io;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Ascii;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.RoundingMode;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public abstract class BaseEncoding {

    /* renamed from: a  reason: collision with root package name */
    public static final BaseEncoding f10661a = new h("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    public static final BaseEncoding b = new h("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    public static final BaseEncoding c = new j("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    public static final BaseEncoding d = new j("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    public static final BaseEncoding e = new g("base16()", "0123456789ABCDEF");

    /* loaded from: classes10.dex */
    public static final class DecodingException extends IOException {
        public DecodingException(String str) {
            super(str);
        }

        public DecodingException(Throwable th) {
            super(th);
        }
    }

    /* loaded from: classes10.dex */
    public class a extends ByteSink {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CharSink f10662a;

        public a(CharSink charSink) {
            this.f10662a = charSink;
        }

        @Override // com.google.common.io.ByteSink
        public OutputStream openStream() throws IOException {
            return BaseEncoding.this.encodingStream(this.f10662a.openStream());
        }
    }

    /* loaded from: classes10.dex */
    public class b extends ByteSource {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ CharSource f10663a;

        public b(CharSource charSource) {
            this.f10663a = charSource;
        }

        @Override // com.google.common.io.ByteSource
        public InputStream openStream() throws IOException {
            return BaseEncoding.this.decodingStream(this.f10663a.openStream());
        }
    }

    /* loaded from: classes10.dex */
    public class e extends Writer {
        public final /* synthetic */ Appendable h;
        public final /* synthetic */ Writer i;

        public e(Appendable appendable, Writer writer) {
            this.h = appendable;
            this.i = writer;
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.i.close();
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            this.i.flush();
        }

        @Override // java.io.Writer
        public void write(int i) throws IOException {
            this.h.append((char) i);
        }

        @Override // java.io.Writer
        public void write(char[] cArr, int i, int i2) throws IOException {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes10.dex */
    public static final class f {

        /* renamed from: a  reason: collision with root package name */
        public final String f10664a;
        public final char[] b;
        public final int c;
        public final int d;
        public final int e;
        public final int f;
        public final byte[] g;
        public final boolean[] h;

        public f(String str, char[] cArr) {
            this.f10664a = (String) Preconditions.checkNotNull(str);
            this.b = (char[]) Preconditions.checkNotNull(cArr);
            try {
                int log2 = IntMath.log2(cArr.length, RoundingMode.UNNECESSARY);
                this.d = log2;
                int min = Math.min(8, Integer.lowestOneBit(log2));
                try {
                    this.e = 8 / min;
                    this.f = log2 / min;
                    this.c = cArr.length - 1;
                    byte[] bArr = new byte[128];
                    Arrays.fill(bArr, (byte) -1);
                    for (int i = 0; i < cArr.length; i++) {
                        char c = cArr[i];
                        Preconditions.checkArgument(c < 128, "Non-ASCII character: %s", c);
                        Preconditions.checkArgument(bArr[c] == -1, "Duplicate character: %s", c);
                        bArr[c] = (byte) i;
                    }
                    this.g = bArr;
                    boolean[] zArr = new boolean[this.e];
                    for (int i2 = 0; i2 < this.f; i2++) {
                        zArr[IntMath.divide(i2 * 8, this.d, RoundingMode.CEILING)] = true;
                    }
                    this.h = zArr;
                } catch (ArithmeticException e) {
                    String str2 = new String(cArr);
                    throw new IllegalArgumentException(str2.length() != 0 ? "Illegal alphabet ".concat(str2) : new String("Illegal alphabet "), e);
                }
            } catch (ArithmeticException e2) {
                int length = cArr.length;
                StringBuilder sb = new StringBuilder(35);
                sb.append("Illegal alphabet length ");
                sb.append(length);
                throw new IllegalArgumentException(sb.toString(), e2);
            }
        }

        public boolean b(char c) {
            return c <= 127 && this.g[c] != -1;
        }

        public int c(char c) throws DecodingException {
            if (c > 127) {
                String valueOf = String.valueOf(Integer.toHexString(c));
                throw new DecodingException(valueOf.length() != 0 ? "Unrecognized character: 0x".concat(valueOf) : new String("Unrecognized character: 0x"));
            }
            byte b = this.g[c];
            if (b == -1) {
                if (c > ' ' && c != 127) {
                    StringBuilder sb = new StringBuilder(25);
                    sb.append("Unrecognized character: ");
                    sb.append(c);
                    throw new DecodingException(sb.toString());
                }
                String valueOf2 = String.valueOf(Integer.toHexString(c));
                throw new DecodingException(valueOf2.length() != 0 ? "Unrecognized character: 0x".concat(valueOf2) : new String("Unrecognized character: 0x"));
            }
            return b;
        }

        public char d(int i) {
            return this.b[i];
        }

        public final boolean e() {
            for (char c : this.b) {
                if (Ascii.isLowerCase(c)) {
                    return true;
                }
            }
            return false;
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof f) {
                return Arrays.equals(this.b, ((f) obj).b);
            }
            return false;
        }

        public final boolean f() {
            for (char c : this.b) {
                if (Ascii.isUpperCase(c)) {
                    return true;
                }
            }
            return false;
        }

        public boolean g(int i) {
            return this.h[i % this.e];
        }

        public f h() {
            if (!f()) {
                return this;
            }
            Preconditions.checkState(!e(), "Cannot call lowerCase() on a mixed-case alphabet");
            char[] cArr = new char[this.b.length];
            int i = 0;
            while (true) {
                char[] cArr2 = this.b;
                if (i < cArr2.length) {
                    cArr[i] = Ascii.toLowerCase(cArr2[i]);
                    i++;
                } else {
                    return new f(String.valueOf(this.f10664a).concat(".lowerCase()"), cArr);
                }
            }
        }

        public int hashCode() {
            return Arrays.hashCode(this.b);
        }

        public boolean i(char c) {
            byte[] bArr = this.g;
            return c < bArr.length && bArr[c] != -1;
        }

        public f j() {
            if (!e()) {
                return this;
            }
            Preconditions.checkState(!f(), "Cannot call upperCase() on a mixed-case alphabet");
            char[] cArr = new char[this.b.length];
            int i = 0;
            while (true) {
                char[] cArr2 = this.b;
                if (i < cArr2.length) {
                    cArr[i] = Ascii.toUpperCase(cArr2[i]);
                    i++;
                } else {
                    return new f(String.valueOf(this.f10664a).concat(".upperCase()"), cArr);
                }
            }
        }

        public String toString() {
            return this.f10664a;
        }
    }

    /* loaded from: classes10.dex */
    public static final class g extends j {
        public final char[] j;

        public g(String str, String str2) {
            this(new f(str, str2.toCharArray()));
        }

        @Override // com.google.common.io.BaseEncoding.j, com.google.common.io.BaseEncoding
        public int b(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            if (charSequence.length() % 2 != 1) {
                int i = 0;
                int i2 = 0;
                while (i < charSequence.length()) {
                    bArr[i2] = (byte) ((this.f.c(charSequence.charAt(i)) << 4) | this.f.c(charSequence.charAt(i + 1)));
                    i += 2;
                    i2++;
                }
                return i2;
            }
            int length = charSequence.length();
            StringBuilder sb = new StringBuilder(32);
            sb.append("Invalid input length ");
            sb.append(length);
            throw new DecodingException(sb.toString());
        }

        @Override // com.google.common.io.BaseEncoding.j, com.google.common.io.BaseEncoding
        public void c(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
            for (int i3 = 0; i3 < i2; i3++) {
                int i4 = bArr[i + i3] & 255;
                appendable.append(this.j[i4]);
                appendable.append(this.j[i4 | 256]);
            }
        }

        @Override // com.google.common.io.BaseEncoding.j
        public BaseEncoding l(f fVar, @NullableDecl Character ch) {
            return new g(fVar);
        }

        public g(f fVar) {
            super(fVar, null);
            this.j = new char[512];
            Preconditions.checkArgument(fVar.b.length == 16);
            for (int i = 0; i < 256; i++) {
                this.j[i] = fVar.d(i >>> 4);
                this.j[i | 256] = fVar.d(i & 15);
            }
        }
    }

    /* loaded from: classes10.dex */
    public static final class h extends j {
        public h(String str, String str2, @NullableDecl Character ch) {
            this(new f(str, str2.toCharArray()), ch);
        }

        @Override // com.google.common.io.BaseEncoding.j, com.google.common.io.BaseEncoding
        public int b(byte[] bArr, CharSequence charSequence) throws DecodingException {
            Preconditions.checkNotNull(bArr);
            CharSequence j = j(charSequence);
            if (this.f.g(j.length())) {
                int i = 0;
                int i2 = 0;
                while (i < j.length()) {
                    int i3 = i + 1;
                    int i4 = i3 + 1;
                    int c = (this.f.c(j.charAt(i)) << 18) | (this.f.c(j.charAt(i3)) << 12);
                    int i5 = i2 + 1;
                    bArr[i2] = (byte) (c >>> 16);
                    if (i4 < j.length()) {
                        int i6 = i4 + 1;
                        int c2 = c | (this.f.c(j.charAt(i4)) << 6);
                        i2 = i5 + 1;
                        bArr[i5] = (byte) ((c2 >>> 8) & 255);
                        if (i6 < j.length()) {
                            i4 = i6 + 1;
                            i5 = i2 + 1;
                            bArr[i2] = (byte) ((c2 | this.f.c(j.charAt(i6))) & 255);
                        } else {
                            i = i6;
                        }
                    }
                    i2 = i5;
                    i = i4;
                }
                return i2;
            }
            int length = j.length();
            StringBuilder sb = new StringBuilder(32);
            sb.append("Invalid input length ");
            sb.append(length);
            throw new DecodingException(sb.toString());
        }

        @Override // com.google.common.io.BaseEncoding.j, com.google.common.io.BaseEncoding
        public void c(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
            Preconditions.checkNotNull(appendable);
            int i3 = i + i2;
            Preconditions.checkPositionIndexes(i, i3, bArr.length);
            while (i2 >= 3) {
                int i4 = i + 1;
                int i5 = i4 + 1;
                int i6 = ((bArr[i] & 255) << 16) | ((bArr[i4] & 255) << 8) | (bArr[i5] & 255);
                appendable.append(this.f.d(i6 >>> 18));
                appendable.append(this.f.d((i6 >>> 12) & 63));
                appendable.append(this.f.d((i6 >>> 6) & 63));
                appendable.append(this.f.d(i6 & 63));
                i2 -= 3;
                i = i5 + 1;
            }
            if (i < i3) {
                k(appendable, bArr, i, i3 - i);
            }
        }

        @Override // com.google.common.io.BaseEncoding.j
        public BaseEncoding l(f fVar, @NullableDecl Character ch) {
            return new h(fVar, ch);
        }

        public h(f fVar, @NullableDecl Character ch) {
            super(fVar, ch);
            Preconditions.checkArgument(fVar.b.length == 64);
        }
    }

    /* loaded from: classes10.dex */
    public static final class i extends BaseEncoding {
        public final BaseEncoding f;
        public final String g;
        public final int h;

        public i(BaseEncoding baseEncoding, String str, int i) {
            this.f = (BaseEncoding) Preconditions.checkNotNull(baseEncoding);
            this.g = (String) Preconditions.checkNotNull(str);
            this.h = i;
            Preconditions.checkArgument(i > 0, "Cannot add a separator after every %s chars", i);
        }

        @Override // com.google.common.io.BaseEncoding
        public int b(byte[] bArr, CharSequence charSequence) throws DecodingException {
            StringBuilder sb = new StringBuilder(charSequence.length());
            for (int i = 0; i < charSequence.length(); i++) {
                char charAt = charSequence.charAt(i);
                if (this.g.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.f.b(bArr, sb);
        }

        @Override // com.google.common.io.BaseEncoding
        public void c(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
            this.f.c(BaseEncoding.h(appendable, this.g, this.h), bArr, i, i2);
        }

        @Override // com.google.common.io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < charSequence.length(); i++) {
                char charAt = charSequence.charAt(i);
                if (this.g.indexOf(charAt) < 0) {
                    sb.append(charAt);
                }
            }
            return this.f.canDecode(sb);
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public InputStream decodingStream(Reader reader) {
            return this.f.decodingStream(BaseEncoding.e(reader, this.g));
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public OutputStream encodingStream(Writer writer) {
            return this.f.encodingStream(BaseEncoding.i(writer, this.g, this.h));
        }

        @Override // com.google.common.io.BaseEncoding
        public int f(int i) {
            return this.f.f(i);
        }

        @Override // com.google.common.io.BaseEncoding
        public int g(int i) {
            int g = this.f.g(i);
            return g + (this.g.length() * IntMath.divide(Math.max(0, g - 1), this.h, RoundingMode.FLOOR));
        }

        @Override // com.google.common.io.BaseEncoding
        public CharSequence j(CharSequence charSequence) {
            return this.f.j(charSequence);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            return this.f.lowerCase().withSeparator(this.g, this.h);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.f.omitPadding().withSeparator(this.g, this.h);
        }

        public String toString() {
            String valueOf = String.valueOf(this.f);
            String str = this.g;
            int i = this.h;
            StringBuilder sb = new StringBuilder(valueOf.length() + 31 + String.valueOf(str).length());
            sb.append(valueOf);
            sb.append(".withSeparator(\"");
            sb.append(str);
            sb.append("\", ");
            sb.append(i);
            sb.append(")");
            return sb.toString();
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            return this.f.upperCase().withSeparator(this.g, this.h);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char c) {
            return this.f.withPadChar(c).withSeparator(this.g, this.h);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String str, int i) {
            throw new UnsupportedOperationException("Already have a separator");
        }
    }

    /* loaded from: classes10.dex */
    public static class j extends BaseEncoding {
        public final f f;
        @NullableDecl
        public final Character g;
        @NullableDecl
        @LazyInit
        public transient BaseEncoding h;
        @NullableDecl
        @LazyInit
        public transient BaseEncoding i;

        /* loaded from: classes10.dex */
        public class a extends OutputStream {
            public int h = 0;
            public int i = 0;
            public int j = 0;
            public final /* synthetic */ Writer k;

            public a(Writer writer) {
                this.k = writer;
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                int i = this.i;
                if (i > 0) {
                    int i2 = this.h;
                    f fVar = j.this.f;
                    this.k.write(fVar.d((i2 << (fVar.d - i)) & fVar.c));
                    this.j++;
                    if (j.this.g != null) {
                        while (true) {
                            int i3 = this.j;
                            j jVar = j.this;
                            if (i3 % jVar.f.e == 0) {
                                break;
                            }
                            this.k.write(jVar.g.charValue());
                            this.j++;
                        }
                    }
                }
                this.k.close();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                this.k.flush();
            }

            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                int i2 = this.h << 8;
                this.h = i2;
                this.h = (i & 255) | i2;
                this.i += 8;
                while (true) {
                    int i3 = this.i;
                    f fVar = j.this.f;
                    int i4 = fVar.d;
                    if (i3 < i4) {
                        return;
                    }
                    this.k.write(fVar.d((this.h >> (i3 - i4)) & fVar.c));
                    this.j++;
                    this.i -= j.this.f.d;
                }
            }
        }

        public j(String str, String str2, @NullableDecl Character ch) {
            this(new f(str, str2.toCharArray()), ch);
        }

        @Override // com.google.common.io.BaseEncoding
        public int b(byte[] bArr, CharSequence charSequence) throws DecodingException {
            f fVar;
            Preconditions.checkNotNull(bArr);
            CharSequence j = j(charSequence);
            if (this.f.g(j.length())) {
                int i = 0;
                int i2 = 0;
                while (i < j.length()) {
                    long j2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    while (true) {
                        fVar = this.f;
                        if (i3 >= fVar.e) {
                            break;
                        }
                        j2 <<= fVar.d;
                        if (i + i3 < j.length()) {
                            j2 |= this.f.c(j.charAt(i4 + i));
                            i4++;
                        }
                        i3++;
                    }
                    int i5 = fVar.f;
                    int i6 = (i5 * 8) - (i4 * fVar.d);
                    int i7 = (i5 - 1) * 8;
                    while (i7 >= i6) {
                        bArr[i2] = (byte) ((j2 >>> i7) & 255);
                        i7 -= 8;
                        i2++;
                    }
                    i += this.f.e;
                }
                return i2;
            }
            int length = j.length();
            StringBuilder sb = new StringBuilder(32);
            sb.append("Invalid input length ");
            sb.append(length);
            throw new DecodingException(sb.toString());
        }

        @Override // com.google.common.io.BaseEncoding
        public void c(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
            int i3 = 0;
            while (i3 < i2) {
                k(appendable, bArr, i + i3, Math.min(this.f.f, i2 - i3));
                i3 += this.f.f;
            }
        }

        @Override // com.google.common.io.BaseEncoding
        public boolean canDecode(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            CharSequence j = j(charSequence);
            if (this.f.g(j.length())) {
                for (int i = 0; i < j.length(); i++) {
                    if (!this.f.b(j.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public InputStream decodingStream(Reader reader) {
            Preconditions.checkNotNull(reader);
            return new b(reader);
        }

        @Override // com.google.common.io.BaseEncoding
        @GwtIncompatible
        public OutputStream encodingStream(Writer writer) {
            Preconditions.checkNotNull(writer);
            return new a(writer);
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof j) {
                j jVar = (j) obj;
                return this.f.equals(jVar.f) && Objects.equal(this.g, jVar.g);
            }
            return false;
        }

        @Override // com.google.common.io.BaseEncoding
        public int f(int i) {
            return (int) (((this.f.d * i) + 7) / 8);
        }

        @Override // com.google.common.io.BaseEncoding
        public int g(int i) {
            f fVar = this.f;
            return fVar.e * IntMath.divide(i, fVar.f, RoundingMode.CEILING);
        }

        public int hashCode() {
            return this.f.hashCode() ^ Objects.hashCode(this.g);
        }

        @Override // com.google.common.io.BaseEncoding
        public CharSequence j(CharSequence charSequence) {
            Preconditions.checkNotNull(charSequence);
            Character ch = this.g;
            if (ch == null) {
                return charSequence;
            }
            char charValue = ch.charValue();
            int length = charSequence.length() - 1;
            while (length >= 0 && charSequence.charAt(length) == charValue) {
                length--;
            }
            return charSequence.subSequence(0, length + 1);
        }

        public void k(Appendable appendable, byte[] bArr, int i, int i2) throws IOException {
            Preconditions.checkNotNull(appendable);
            Preconditions.checkPositionIndexes(i, i + i2, bArr.length);
            int i3 = 0;
            Preconditions.checkArgument(i2 <= this.f.f);
            long j = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                j = (j | (bArr[i + i4] & 255)) << 8;
            }
            int i5 = ((i2 + 1) * 8) - this.f.d;
            while (i3 < i2 * 8) {
                f fVar = this.f;
                appendable.append(fVar.d(((int) (j >>> (i5 - i3))) & fVar.c));
                i3 += this.f.d;
            }
            if (this.g != null) {
                while (i3 < this.f.f * 8) {
                    appendable.append(this.g.charValue());
                    i3 += this.f.d;
                }
            }
        }

        public BaseEncoding l(f fVar, @NullableDecl Character ch) {
            return new j(fVar, ch);
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding lowerCase() {
            BaseEncoding baseEncoding = this.i;
            if (baseEncoding == null) {
                f h = this.f.h();
                baseEncoding = h == this.f ? this : l(h, this.g);
                this.i = baseEncoding;
            }
            return baseEncoding;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding omitPadding() {
            return this.g == null ? this : l(this.f, null);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("BaseEncoding.");
            sb.append(this.f.toString());
            if (8 % this.f.d != 0) {
                if (this.g == null) {
                    sb.append(".omitPadding()");
                } else {
                    sb.append(".withPadChar('");
                    sb.append(this.g);
                    sb.append("')");
                }
            }
            return sb.toString();
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding upperCase() {
            BaseEncoding baseEncoding = this.h;
            if (baseEncoding == null) {
                f j = this.f.j();
                baseEncoding = j == this.f ? this : l(j, this.g);
                this.h = baseEncoding;
            }
            return baseEncoding;
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withPadChar(char c) {
            Character ch;
            return (8 % this.f.d == 0 || ((ch = this.g) != null && ch.charValue() == c)) ? this : l(this.f, Character.valueOf(c));
        }

        @Override // com.google.common.io.BaseEncoding
        public BaseEncoding withSeparator(String str, int i) {
            for (int i2 = 0; i2 < str.length(); i2++) {
                Preconditions.checkArgument(!this.f.i(str.charAt(i2)), "Separator (%s) cannot contain alphabet characters", str);
            }
            Character ch = this.g;
            if (ch != null) {
                Preconditions.checkArgument(str.indexOf(ch.charValue()) < 0, "Separator (%s) cannot contain padding character", str);
            }
            return new i(this, str, i);
        }

        public j(f fVar, @NullableDecl Character ch) {
            this.f = (f) Preconditions.checkNotNull(fVar);
            Preconditions.checkArgument(ch == null || !fVar.i(ch.charValue()), "Padding character %s was already in alphabet", ch);
            this.g = ch;
        }

        /* loaded from: classes10.dex */
        public class b extends InputStream {
            public int h = 0;
            public int i = 0;
            public int j = 0;
            public boolean k = false;
            public final /* synthetic */ Reader l;

            public b(Reader reader) {
                this.l = reader;
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                this.l.close();
            }

            /* JADX WARN: Code restructure failed: missing block: B:23:0x005e, code lost:
                r1 = r5.j;
                r3 = new java.lang.StringBuilder(41);
                r3.append("Padding cannot start at index ");
                r3.append(r1);
             */
            /* JADX WARN: Code restructure failed: missing block: B:24:0x0078, code lost:
                throw new com.google.common.io.BaseEncoding.DecodingException(r3.toString());
             */
            @Override // java.io.InputStream
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public int read() throws java.io.IOException {
                /*
                    r5 = this;
                L0:
                    java.io.Reader r0 = r5.l
                    int r0 = r0.read()
                    r1 = -1
                    if (r0 != r1) goto L36
                    boolean r0 = r5.k
                    if (r0 != 0) goto L35
                    com.google.common.io.BaseEncoding$j r0 = com.google.common.io.BaseEncoding.j.this
                    com.google.common.io.BaseEncoding$f r0 = r0.f
                    int r2 = r5.j
                    boolean r0 = r0.g(r2)
                    if (r0 == 0) goto L1a
                    goto L35
                L1a:
                    com.google.common.io.BaseEncoding$DecodingException r0 = new com.google.common.io.BaseEncoding$DecodingException
                    int r1 = r5.j
                    r2 = 32
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>(r2)
                    java.lang.String r2 = "Invalid input length "
                    r3.append(r2)
                    r3.append(r1)
                    java.lang.String r1 = r3.toString()
                    r0.<init>(r1)
                    throw r0
                L35:
                    return r1
                L36:
                    int r1 = r5.j
                    r2 = 1
                    int r1 = r1 + r2
                    r5.j = r1
                    char r0 = (char) r0
                    com.google.common.io.BaseEncoding$j r1 = com.google.common.io.BaseEncoding.j.this
                    java.lang.Character r1 = r1.g
                    if (r1 == 0) goto L7c
                    char r1 = r1.charValue()
                    if (r1 != r0) goto L7c
                    boolean r0 = r5.k
                    if (r0 != 0) goto L79
                    int r0 = r5.j
                    if (r0 == r2) goto L5e
                    com.google.common.io.BaseEncoding$j r1 = com.google.common.io.BaseEncoding.j.this
                    com.google.common.io.BaseEncoding$f r1 = r1.f
                    int r0 = r0 + (-1)
                    boolean r0 = r1.g(r0)
                    if (r0 == 0) goto L5e
                    goto L79
                L5e:
                    com.google.common.io.BaseEncoding$DecodingException r0 = new com.google.common.io.BaseEncoding$DecodingException
                    int r1 = r5.j
                    r2 = 41
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>(r2)
                    java.lang.String r2 = "Padding cannot start at index "
                    r3.append(r2)
                    r3.append(r1)
                    java.lang.String r1 = r3.toString()
                    r0.<init>(r1)
                    throw r0
                L79:
                    r5.k = r2
                    goto L0
                L7c:
                    boolean r1 = r5.k
                    if (r1 != 0) goto La8
                    int r1 = r5.h
                    com.google.common.io.BaseEncoding$j r2 = com.google.common.io.BaseEncoding.j.this
                    com.google.common.io.BaseEncoding$f r2 = r2.f
                    int r3 = r2.d
                    int r1 = r1 << r3
                    r5.h = r1
                    int r0 = r2.c(r0)
                    r0 = r0 | r1
                    r5.h = r0
                    int r1 = r5.i
                    com.google.common.io.BaseEncoding$j r2 = com.google.common.io.BaseEncoding.j.this
                    com.google.common.io.BaseEncoding$f r2 = r2.f
                    int r2 = r2.d
                    int r1 = r1 + r2
                    r5.i = r1
                    r2 = 8
                    if (r1 < r2) goto L0
                    int r1 = r1 - r2
                    r5.i = r1
                    int r0 = r0 >> r1
                    r0 = r0 & 255(0xff, float:3.57E-43)
                    return r0
                La8:
                    com.google.common.io.BaseEncoding$DecodingException r1 = new com.google.common.io.BaseEncoding$DecodingException
                    int r2 = r5.j
                    r3 = 61
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>(r3)
                    java.lang.String r3 = "Expected padding character but found '"
                    r4.append(r3)
                    r4.append(r0)
                    java.lang.String r0 = "' at index "
                    r4.append(r0)
                    r4.append(r2)
                    java.lang.String r0 = r4.toString()
                    r1.<init>(r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.common.io.BaseEncoding.j.b.read():int");
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                int i3 = i2 + i;
                Preconditions.checkPositionIndexes(i, i3, bArr.length);
                int i4 = i;
                while (i4 < i3) {
                    int read = read();
                    if (read == -1) {
                        int i5 = i4 - i;
                        if (i5 == 0) {
                            return -1;
                        }
                        return i5;
                    }
                    bArr[i4] = (byte) read;
                    i4++;
                }
                return i4 - i;
            }
        }
    }

    public static BaseEncoding base16() {
        return e;
    }

    public static BaseEncoding base32() {
        return c;
    }

    public static BaseEncoding base32Hex() {
        return d;
    }

    public static BaseEncoding base64() {
        return f10661a;
    }

    public static BaseEncoding base64Url() {
        return b;
    }

    public static byte[] d(byte[] bArr, int i2) {
        if (i2 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    @GwtIncompatible
    public static Reader e(Reader reader, String str) {
        Preconditions.checkNotNull(reader);
        Preconditions.checkNotNull(str);
        return new c(reader, str);
    }

    public static Appendable h(Appendable appendable, String str, int i2) {
        Preconditions.checkNotNull(appendable);
        Preconditions.checkNotNull(str);
        Preconditions.checkArgument(i2 > 0);
        return new d(i2, appendable, str);
    }

    @GwtIncompatible
    public static Writer i(Writer writer, String str, int i2) {
        return new e(h(writer, str, i2), writer);
    }

    public final byte[] a(CharSequence charSequence) throws DecodingException {
        CharSequence j2 = j(charSequence);
        byte[] bArr = new byte[f(j2.length())];
        return d(bArr, b(bArr, j2));
    }

    public abstract int b(byte[] bArr, CharSequence charSequence) throws DecodingException;

    public abstract void c(Appendable appendable, byte[] bArr, int i2, int i3) throws IOException;

    public abstract boolean canDecode(CharSequence charSequence);

    public final byte[] decode(CharSequence charSequence) {
        try {
            return a(charSequence);
        } catch (DecodingException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    @GwtIncompatible
    public final ByteSource decodingSource(CharSource charSource) {
        Preconditions.checkNotNull(charSource);
        return new b(charSource);
    }

    @GwtIncompatible
    public abstract InputStream decodingStream(Reader reader);

    public String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    @GwtIncompatible
    public final ByteSink encodingSink(CharSink charSink) {
        Preconditions.checkNotNull(charSink);
        return new a(charSink);
    }

    @GwtIncompatible
    public abstract OutputStream encodingStream(Writer writer);

    public abstract int f(int i2);

    public abstract int g(int i2);

    public CharSequence j(CharSequence charSequence) {
        return (CharSequence) Preconditions.checkNotNull(charSequence);
    }

    public abstract BaseEncoding lowerCase();

    public abstract BaseEncoding omitPadding();

    public abstract BaseEncoding upperCase();

    public abstract BaseEncoding withPadChar(char c2);

    public abstract BaseEncoding withSeparator(String str, int i2);

    /* loaded from: classes10.dex */
    public class c extends Reader {
        public final /* synthetic */ Reader h;
        public final /* synthetic */ String i;

        public c(Reader reader, String str) {
            this.h = reader;
            this.i = str;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.h.close();
        }

        @Override // java.io.Reader
        public int read() throws IOException {
            int read;
            do {
                read = this.h.read();
                if (read == -1) {
                    break;
                }
            } while (this.i.indexOf((char) read) >= 0);
            return read;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            throw new UnsupportedOperationException();
        }
    }

    public final String encode(byte[] bArr, int i2, int i3) {
        Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
        StringBuilder sb = new StringBuilder(g(i3));
        try {
            c(sb, bArr, i2, i3);
            return sb.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }

    /* loaded from: classes10.dex */
    public class d implements Appendable {
        public int h;
        public final /* synthetic */ int i;
        public final /* synthetic */ Appendable j;
        public final /* synthetic */ String k;

        public d(int i, Appendable appendable, String str) {
            this.i = i;
            this.j = appendable;
            this.k = str;
            this.h = i;
        }

        @Override // java.lang.Appendable
        public Appendable append(char c) throws IOException {
            if (this.h == 0) {
                this.j.append(this.k);
                this.h = this.i;
            }
            this.j.append(c);
            this.h--;
            return this;
        }

        @Override // java.lang.Appendable
        public Appendable append(@NullableDecl CharSequence charSequence, int i, int i2) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // java.lang.Appendable
        public Appendable append(@NullableDecl CharSequence charSequence) throws IOException {
            throw new UnsupportedOperationException();
        }
    }
}
