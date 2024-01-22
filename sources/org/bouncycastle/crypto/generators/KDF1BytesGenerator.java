package org.bouncycastle.crypto.generators;

import org.bouncycastle.crypto.Digest;
/* loaded from: classes12.dex */
public class KDF1BytesGenerator extends BaseKDFBytesGenerator {
    public KDF1BytesGenerator(Digest digest) {
        super(0, digest);
    }
}
