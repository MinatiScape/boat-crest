package org.bouncycastle.operator.bc;

import java.security.SecureRandom;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyWrapper;
/* loaded from: classes13.dex */
public class BcSymmetricKeyWrapper extends SymmetricKeyWrapper {
    public SecureRandom b;
    public Wrapper c;
    public KeyParameter d;

    public BcSymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier, Wrapper wrapper, KeyParameter keyParameter) {
        super(algorithmIdentifier);
        this.c = wrapper;
        this.d = keyParameter;
    }

    @Override // org.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        byte[] a2 = b.a(genericKey);
        SecureRandom secureRandom = this.b;
        if (secureRandom == null) {
            this.c.init(true, this.d);
        } else {
            this.c.init(true, new ParametersWithRandom(this.d, secureRandom));
        }
        return this.c.wrap(a2, 0, a2.length);
    }

    public BcSymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.b = secureRandom;
        return this;
    }
}
