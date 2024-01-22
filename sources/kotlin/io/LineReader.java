package kotlin.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class LineReader {
    @NotNull
    public static final LineReader INSTANCE = new LineReader();

    /* renamed from: a  reason: collision with root package name */
    public static CharsetDecoder f14069a;
    public static boolean b;
    @NotNull
    public static final byte[] c;
    @NotNull
    public static final char[] d;
    @NotNull
    public static final ByteBuffer e;
    @NotNull
    public static final CharBuffer f;
    @NotNull
    public static final StringBuilder g;

    static {
        byte[] bArr = new byte[32];
        c = bArr;
        char[] cArr = new char[32];
        d = cArr;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkNotNullExpressionValue(wrap, "wrap(bytes)");
        e = wrap;
        CharBuffer wrap2 = CharBuffer.wrap(cArr);
        Intrinsics.checkNotNullExpressionValue(wrap2, "wrap(chars)");
        f = wrap2;
        g = new StringBuilder();
    }

    public final int a() {
        ByteBuffer byteBuffer = e;
        byteBuffer.compact();
        int position = byteBuffer.position();
        byteBuffer.position(0);
        return position;
    }

    public final int b(boolean z) {
        while (true) {
            CharsetDecoder charsetDecoder = f14069a;
            if (charsetDecoder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("decoder");
                charsetDecoder = null;
            }
            ByteBuffer byteBuffer = e;
            CharBuffer charBuffer = f;
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, z);
            Intrinsics.checkNotNullExpressionValue(decode, "decoder.decode(byteBuf, charBuf, endOfInput)");
            if (decode.isError()) {
                d();
                decode.throwException();
            }
            int position = charBuffer.position();
            if (!decode.isOverflow()) {
                return position;
            }
            StringBuilder sb = g;
            char[] cArr = d;
            int i = position - 1;
            sb.append(cArr, 0, i);
            charBuffer.position(0);
            charBuffer.limit(32);
            charBuffer.put(cArr[i]);
        }
    }

    public final int c(int i, int i2) {
        ByteBuffer byteBuffer = e;
        byteBuffer.limit(i);
        f.position(i2);
        int b2 = b(true);
        CharsetDecoder charsetDecoder = f14069a;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder = null;
        }
        charsetDecoder.reset();
        byteBuffer.position(0);
        return b2;
    }

    public final void d() {
        CharsetDecoder charsetDecoder = f14069a;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder = null;
        }
        charsetDecoder.reset();
        e.position(0);
        g.setLength(0);
    }

    public final void e() {
        StringBuilder sb = g;
        sb.setLength(32);
        sb.trimToSize();
    }

    public final void f(Charset charset) {
        CharsetDecoder newDecoder = charset.newDecoder();
        Intrinsics.checkNotNullExpressionValue(newDecoder, "charset.newDecoder()");
        f14069a = newDecoder;
        ByteBuffer byteBuffer = e;
        byteBuffer.clear();
        CharBuffer charBuffer = f;
        charBuffer.clear();
        byteBuffer.put((byte) 10);
        byteBuffer.flip();
        CharsetDecoder charsetDecoder = f14069a;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder = null;
        }
        boolean z = false;
        charsetDecoder.decode(byteBuffer, charBuffer, false);
        if (charBuffer.position() == 1 && charBuffer.get(0) == '\n') {
            z = true;
        }
        b = z;
        d();
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r0.charset(), r12) == false) goto L67;
     */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final synchronized java.lang.String readLine(@org.jetbrains.annotations.NotNull java.io.InputStream r11, @org.jetbrains.annotations.NotNull java.nio.charset.Charset r12) {
        /*
            r10 = this;
            monitor-enter(r10)
            java.lang.String r0 = "inputStream"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)     // Catch: java.lang.Throwable -> Lc9
            java.nio.charset.CharsetDecoder r0 = kotlin.io.LineReader.f14069a     // Catch: java.lang.Throwable -> Lc9
            r1 = 0
            if (r0 == 0) goto L22
            if (r0 != 0) goto L18
            java.lang.String r0 = "decoder"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)     // Catch: java.lang.Throwable -> Lc9
            r0 = r1
        L18:
            java.nio.charset.Charset r0 = r0.charset()     // Catch: java.lang.Throwable -> Lc9
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r12)     // Catch: java.lang.Throwable -> Lc9
            if (r0 != 0) goto L25
        L22:
            r10.f(r12)     // Catch: java.lang.Throwable -> Lc9
        L25:
            r12 = 0
            r0 = r12
            r2 = r0
        L28:
            int r3 = r11.read()     // Catch: java.lang.Throwable -> Lc9
            r4 = 32
            r5 = 10
            r6 = -1
            r7 = 1
            if (r3 != r6) goto L4c
            java.lang.StringBuilder r11 = kotlin.io.LineReader.g     // Catch: java.lang.Throwable -> Lc9
            int r11 = r11.length()     // Catch: java.lang.Throwable -> Lc9
            if (r11 != 0) goto L3e
            r11 = r7
            goto L3f
        L3e:
            r11 = r12
        L3f:
            if (r11 == 0) goto L47
            if (r0 != 0) goto L47
            if (r2 != 0) goto L47
            monitor-exit(r10)
            return r1
        L47:
            int r11 = r10.c(r0, r2)     // Catch: java.lang.Throwable -> Lc9
            goto L7a
        L4c:
            byte[] r6 = kotlin.io.LineReader.c     // Catch: java.lang.Throwable -> Lc9
            int r8 = r0 + 1
            byte r9 = (byte) r3     // Catch: java.lang.Throwable -> Lc9
            r6[r0] = r9     // Catch: java.lang.Throwable -> Lc9
            if (r3 == r5) goto L5e
            if (r8 == r4) goto L5e
            boolean r0 = kotlin.io.LineReader.b     // Catch: java.lang.Throwable -> Lc9
            if (r0 != 0) goto L5c
            goto L5e
        L5c:
            r0 = r8
            goto L28
        L5e:
            java.nio.ByteBuffer r0 = kotlin.io.LineReader.e     // Catch: java.lang.Throwable -> Lc9
            r0.limit(r8)     // Catch: java.lang.Throwable -> Lc9
            java.nio.CharBuffer r3 = kotlin.io.LineReader.f     // Catch: java.lang.Throwable -> Lc9
            r3.position(r2)     // Catch: java.lang.Throwable -> Lc9
            int r2 = r10.b(r12)     // Catch: java.lang.Throwable -> Lc9
            if (r2 <= 0) goto Lc3
            char[] r3 = kotlin.io.LineReader.d     // Catch: java.lang.Throwable -> Lc9
            int r6 = r2 + (-1)
            char r3 = r3[r6]     // Catch: java.lang.Throwable -> Lc9
            if (r3 != r5) goto Lc3
            r0.position(r12)     // Catch: java.lang.Throwable -> Lc9
            r11 = r2
        L7a:
            if (r11 <= 0) goto L92
            char[] r0 = kotlin.io.LineReader.d     // Catch: java.lang.Throwable -> Lc9
            int r1 = r11 + (-1)
            char r1 = r0[r1]     // Catch: java.lang.Throwable -> Lc9
            if (r1 != r5) goto L92
            int r11 = r11 + (-1)
            if (r11 <= 0) goto L92
            int r1 = r11 + (-1)
            char r0 = r0[r1]     // Catch: java.lang.Throwable -> Lc9
            r1 = 13
            if (r0 != r1) goto L92
            int r11 = r11 + (-1)
        L92:
            java.lang.StringBuilder r0 = kotlin.io.LineReader.g     // Catch: java.lang.Throwable -> Lc9
            int r1 = r0.length()     // Catch: java.lang.Throwable -> Lc9
            if (r1 != 0) goto L9b
            goto L9c
        L9b:
            r7 = r12
        L9c:
            if (r7 == 0) goto La7
            java.lang.String r0 = new java.lang.String     // Catch: java.lang.Throwable -> Lc9
            char[] r1 = kotlin.io.LineReader.d     // Catch: java.lang.Throwable -> Lc9
            r0.<init>(r1, r12, r11)     // Catch: java.lang.Throwable -> Lc9
            monitor-exit(r10)
            return r0
        La7:
            char[] r1 = kotlin.io.LineReader.d     // Catch: java.lang.Throwable -> Lc9
            r0.append(r1, r12, r11)     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r11 = r0.toString()     // Catch: java.lang.Throwable -> Lc9
            java.lang.String r1 = "sb.toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r1)     // Catch: java.lang.Throwable -> Lc9
            int r1 = r0.length()     // Catch: java.lang.Throwable -> Lc9
            if (r1 <= r4) goto Lbe
            r10.e()     // Catch: java.lang.Throwable -> Lc9
        Lbe:
            r0.setLength(r12)     // Catch: java.lang.Throwable -> Lc9
            monitor-exit(r10)
            return r11
        Lc3:
            int r0 = r10.a()     // Catch: java.lang.Throwable -> Lc9
            goto L28
        Lc9:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.LineReader.readLine(java.io.InputStream, java.nio.charset.Charset):java.lang.String");
    }
}
