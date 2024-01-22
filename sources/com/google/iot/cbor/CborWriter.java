package com.google.iot.cbor;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes10.dex */
public interface CborWriter {
    static CborWriter createFromByteBuffer(ByteBuffer byteBuffer) {
        return new j(byteBuffer);
    }

    static CborWriter createFromOutputStream(OutputStream outputStream) {
        return new j(outputStream);
    }

    static int length(CborObject cborObject) {
        return j.length(cborObject);
    }

    @CanIgnoreReturnValue
    CborWriter writeDataItem(CborObject cborObject) throws IOException;

    @CanIgnoreReturnValue
    CborWriter writeTag(int i) throws IOException;
}
