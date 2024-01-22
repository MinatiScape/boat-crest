package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.Utf8Old;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;
/* loaded from: classes.dex */
public class Utf8Old extends Utf8 {
    public static final ThreadLocal<a> b = ThreadLocal.withInitial(new Supplier() { // from class: androidx.emoji2.text.flatbuffer.c
        @Override // java.util.function.Supplier
        public final Object get() {
            Utf8Old.a b2;
            b2 = Utf8Old.b();
            return b2;
        }
    });

    /* loaded from: classes.dex */
    public static class a {
        public CharSequence c = null;
        public ByteBuffer d = null;

        /* renamed from: a  reason: collision with root package name */
        public final CharsetEncoder f1278a = StandardCharsets.UTF_8.newEncoder();
        public final CharsetDecoder b = StandardCharsets.UTF_8.newDecoder();
    }

    public static /* synthetic */ a b() {
        return new a();
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) {
        CharsetDecoder charsetDecoder = b.get().b;
        charsetDecoder.reset();
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.position(i);
        duplicate.limit(i + i2);
        try {
            return charsetDecoder.decode(duplicate).toString();
        } catch (CharacterCodingException e) {
            throw new IllegalArgumentException("Bad encoding", e);
        }
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        a aVar = b.get();
        if (aVar.c != charSequence) {
            encodedLength(charSequence);
        }
        byteBuffer.put(aVar.d);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public int encodedLength(CharSequence charSequence) {
        a aVar = b.get();
        int length = (int) (charSequence.length() * aVar.f1278a.maxBytesPerChar());
        ByteBuffer byteBuffer = aVar.d;
        if (byteBuffer == null || byteBuffer.capacity() < length) {
            aVar.d = ByteBuffer.allocate(Math.max(128, length));
        }
        aVar.d.clear();
        aVar.c = charSequence;
        CoderResult encode = aVar.f1278a.encode(charSequence instanceof CharBuffer ? (CharBuffer) charSequence : CharBuffer.wrap(charSequence), aVar.d, true);
        if (encode.isError()) {
            try {
                encode.throwException();
            } catch (CharacterCodingException e) {
                throw new IllegalArgumentException("bad character encoding", e);
            }
        }
        aVar.d.flip();
        return aVar.d.remaining();
    }
}
