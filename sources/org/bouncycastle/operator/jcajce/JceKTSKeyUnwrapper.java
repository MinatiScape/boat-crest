package org.bouncycastle.operator.jcajce;

import java.security.PrivateKey;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.cms.GenericHybridParameters;
import org.bouncycastle.asn1.cms.RsaKemParameters;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.util.DEROtherInfo;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyUnwrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class JceKTSKeyUnwrapper extends AsymmetricKeyUnwrapper {
    public a b;
    public Map c;
    public PrivateKey d;
    public byte[] e;
    public byte[] f;

    public JceKTSKeyUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] bArr, byte[] bArr2) {
        super(algorithmIdentifier);
        this.b = new a(new DefaultJcaJceHelper());
        this.c = new HashMap();
        this.d = privateKey;
        this.e = Arrays.clone(bArr);
        this.f = Arrays.clone(bArr2);
    }

    @Override // org.bouncycastle.operator.KeyUnwrapper
    public GenericKey generateUnwrappedKey(AlgorithmIdentifier algorithmIdentifier, byte[] bArr) throws OperatorException {
        GenericHybridParameters genericHybridParameters = GenericHybridParameters.getInstance(getAlgorithmIdentifier().getParameters());
        Cipher d = this.b.d(getAlgorithmIdentifier().getAlgorithm(), this.c);
        String l = this.b.l(genericHybridParameters.getDem().getAlgorithm());
        RsaKemParameters rsaKemParameters = RsaKemParameters.getInstance(genericHybridParameters.getKem().getParameters());
        try {
            d.init(4, this.d, new KTSParameterSpec.Builder(l, rsaKemParameters.getKeyLength().intValue() * 8, new DEROtherInfo.Builder(genericHybridParameters.getDem(), this.e, this.f).build().getEncoded()).withKdfAlgorithm(rsaKemParameters.getKeyDerivationFunction()).build());
            return new JceGenericKey(algorithmIdentifier, d.unwrap(bArr, this.b.j(algorithmIdentifier.getAlgorithm()), 3));
        } catch (Exception e) {
            throw new OperatorException("Unable to unwrap contents key: " + e.getMessage(), e);
        }
    }

    public JceKTSKeyUnwrapper setProvider(String str) {
        this.b = new a(new NamedJcaJceHelper(str));
        return this;
    }

    public JceKTSKeyUnwrapper setProvider(Provider provider) {
        this.b = new a(new ProviderJcaJceHelper(provider));
        return this;
    }
}
