package org.bouncycastle.operator.jcajce;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
/* loaded from: classes13.dex */
public class JceSymmetricKeyUnwrapper extends SymmetricKeyUnwrapper {
    public a b;
    public SecretKey c;

    public JceSymmetricKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        super(algorithmIdentifier);
        this.b = new a(new DefaultJcaJceHelper());
        this.c = secretKey;
    }

    @Override // org.bouncycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException {
        try {
            Cipher h = this.b.h(getAlgorithmIdentifier().getAlgorithm());
            h.init(4, this.c);
            return new JceGenericKey(algorithmIdentifier, h.unwrap(bArr, this.b.j(algorithmIdentifier.getAlgorithm()), 3));
        } catch (InvalidKeyException e) {
            throw new OperatorException("key invalid in message.", e);
        } catch (NoSuchAlgorithmException e2) {
            throw new OperatorException("can't find algorithm.", e2);
        }
    }

    public JceSymmetricKeyUnwrapper setProvider(String str) {
        this.b = new a(new NamedJcaJceHelper(str));
        return this;
    }

    public JceSymmetricKeyUnwrapper setProvider(Provider provider) {
        this.b = new a(new ProviderJcaJceHelper(provider));
        return this;
    }
}
