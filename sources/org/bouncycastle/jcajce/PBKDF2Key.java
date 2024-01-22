package org.bouncycastle.jcajce;

import org.bouncycastle.crypto.CharToByteConverter;
import org.bouncycastle.util.Arrays;
import org.jose4j.keys.PbkdfKey;
/* loaded from: classes13.dex */
public class PBKDF2Key implements PBKDFKey {
    private final CharToByteConverter converter;
    private final char[] password;

    public PBKDF2Key(char[] cArr, CharToByteConverter charToByteConverter) {
        this.password = Arrays.clone(cArr);
        this.converter = charToByteConverter;
    }

    @Override // java.security.Key
    public String getAlgorithm() {
        return PbkdfKey.ALGORITHM;
    }

    @Override // java.security.Key
    public byte[] getEncoded() {
        return this.converter.convert(this.password);
    }

    @Override // java.security.Key
    public String getFormat() {
        return this.converter.getType();
    }

    public char[] getPassword() {
        return this.password;
    }
}
