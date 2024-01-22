package com.google.iot.cbor;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes10.dex */
public interface CborReader {
    static CborReader createFromByteArray(byte[] bArr, int i, int i2) {
        return new g(bArr, i, i2);
    }

    static CborReader createFromInputStream(InputStream inputStream, int i) {
        return new g(inputStream, i);
    }

    long bytesParsed();

    boolean hasRemainingDataItems();

    CborObject readDataItem() throws CborParseException, IOException;

    static CborReader createFromByteArray(byte[] bArr, int i) {
        return createFromByteArray(bArr, i, -1);
    }

    static CborReader createFromInputStream(InputStream inputStream) {
        return createFromInputStream(inputStream, -1);
    }

    static CborReader createFromByteArray(byte[] bArr) {
        return createFromByteArray(bArr, 0);
    }
}
