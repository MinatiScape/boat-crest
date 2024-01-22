package org.bouncycastle.openssl;

import java.io.IOException;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes13.dex */
public class PEMEncryptedKeyPair {

    /* renamed from: a  reason: collision with root package name */
    public final String f15198a;
    public final byte[] b;
    public final byte[] c;
    public final a d;

    public PEMEncryptedKeyPair(String str, byte[] bArr, byte[] bArr2, a aVar) {
        this.f15198a = str;
        this.b = bArr;
        this.c = bArr2;
        this.d = aVar;
    }

    public PEMKeyPair decryptKeyPair(PEMDecryptorProvider pEMDecryptorProvider) throws IOException {
        try {
            return this.d.a(pEMDecryptorProvider.get(this.f15198a).decrypt(this.c, this.b));
        } catch (IOException e) {
            throw e;
        } catch (OperatorCreationException e2) {
            throw new PEMException("cannot create extraction operator: " + e2.getMessage(), e2);
        } catch (Exception e3) {
            throw new PEMException("exception processing key pair: " + e3.getMessage(), e3);
        }
    }
}
