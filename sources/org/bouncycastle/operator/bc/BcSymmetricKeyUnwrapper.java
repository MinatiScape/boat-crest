package org.bouncycastle.operator.bc;

import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
/* loaded from: classes13.dex */
public class BcSymmetricKeyUnwrapper extends SymmetricKeyUnwrapper {
    public Wrapper b;
    public KeyParameter c;

    public BcSymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, Wrapper wrapper, KeyParameter keyParameter) {
        super(algorithmIdentifier);
        this.b = wrapper;
        this.c = keyParameter;
    }

    @Override // org.bouncycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException {
        this.b.init(false, this.c);
        try {
            return new GenericKey(algorithmIdentifier, this.b.unwrap(bArr, 0, bArr.length));
        } catch (InvalidCipherTextException e) {
            throw new OperatorException("unable to unwrap key: " + e.getMessage(), e);
        }
    }

    public BcSymmetricKeyUnwrapper setSecureRandom(SecureRandom secureRandom) {
        return this;
    }
}
