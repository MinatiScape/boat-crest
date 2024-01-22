package com.google.iot.cbor;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public interface m {
    static m a(ByteBuffer byteBuffer) {
        return new n(byteBuffer);
    }

    static m create() {
        return new o();
    }

    static m d(OutputStream outputStream) {
        return new p(outputStream);
    }

    @CanIgnoreReturnValue
    default m b(byte[] bArr) throws IOException {
        for (byte b : bArr) {
            put(b);
        }
        return this;
    }

    @CanIgnoreReturnValue
    default m c(float f) throws IOException {
        return putShort(q.a(f));
    }

    int length();

    @CanIgnoreReturnValue
    m put(byte b) throws IOException;

    @CanIgnoreReturnValue
    default m putDouble(double d) throws IOException {
        return putLong(Double.doubleToRawLongBits(d));
    }

    @CanIgnoreReturnValue
    default m putFloat(float f) throws IOException {
        return putInt(Float.floatToRawIntBits(f));
    }

    @CanIgnoreReturnValue
    m putInt(int i) throws IOException;

    @CanIgnoreReturnValue
    m putLong(long j) throws IOException;

    @CanIgnoreReturnValue
    m putShort(short s) throws IOException;
}
