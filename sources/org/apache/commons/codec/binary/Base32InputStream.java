package org.apache.commons.codec.binary;

import java.io.InputStream;
import org.apache.commons.codec.CodecPolicy;
/* loaded from: classes12.dex */
public class Base32InputStream extends BaseNCodecInputStream {
    public Base32InputStream(InputStream inputStream) {
        this(inputStream, false);
    }

    public Base32InputStream(InputStream inputStream, boolean z) {
        super(inputStream, new Base32(false), z);
    }

    public Base32InputStream(InputStream inputStream, boolean z, int i, byte[] bArr) {
        super(inputStream, new Base32(i, bArr), z);
    }

    public Base32InputStream(InputStream inputStream, boolean z, int i, byte[] bArr, CodecPolicy codecPolicy) {
        super(inputStream, new Base32(i, bArr, false, (byte) 61, codecPolicy), z);
    }
}
