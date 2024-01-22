package com.google.iot.cbor;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes10.dex */
public interface k {
    static k b(InputStream inputStream) {
        return new l(inputStream);
    }

    short a() throws IOException;

    long bytesParsed();

    long c() throws IOException;

    default void d(byte[] bArr) throws IOException {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = get();
        }
    }

    boolean e() throws IOException;

    int f() throws IOException;

    byte get() throws IOException;

    int peek() throws IOException;
}
