package org.jose4j.jwe;

import java.security.SecureRandom;
import org.jose4j.lang.ByteUtil;
/* loaded from: classes13.dex */
public class InitializationVectorHelp {
    public static byte[] a(int i, byte[] bArr, SecureRandom secureRandom) {
        return bArr == null ? ByteUtil.randomBytes(i, secureRandom) : bArr;
    }
}
