package org.bouncycastle.openssl;

import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes13.dex */
public interface PEMDecryptorProvider {
    PEMDecryptor get(String str) throws OperatorCreationException;
}
