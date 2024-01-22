package org.bouncycastle.operator.jcajce;

import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.cms.GenericHybridParameters;
import org.bouncycastle.asn1.cms.RsaKemParameters;
import org.bouncycastle.asn1.iso.ISOIECObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.crypto.util.DEROtherInfo;
import org.bouncycastle.jcajce.spec.KTSParameterSpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class JceKTSKeyWrapper extends AsymmetricKeyWrapper {
    public final String b;
    public final int c;
    public final byte[] d;
    public final byte[] e;
    public a f;
    public PublicKey g;
    public SecureRandom h;

    public JceKTSKeyWrapper(PublicKey publicKey, String str, int i, byte[] bArr, byte[] bArr2) {
        super(new AlgorithmIdentifier(PKCSObjectIdentifiers.id_rsa_KEM, new GenericHybridParameters(new AlgorithmIdentifier(ISOIECObjectIdentifiers.id_kem_rsa, new RsaKemParameters(new AlgorithmIdentifier(X9ObjectIdentifiers.id_kdf_kdf3, new AlgorithmIdentifier(NISTObjectIdentifiers.id_sha256)), (i + 7) / 8)), JceSymmetricKeyWrapper.a(str, i))));
        this.f = new a(new DefaultJcaJceHelper());
        this.g = publicKey;
        this.b = str;
        this.c = i;
        this.d = Arrays.clone(bArr);
        this.e = Arrays.clone(bArr2);
    }

    public JceKTSKeyWrapper(X509Certificate x509Certificate, String str, int i, byte[] bArr, byte[] bArr2) {
        this(x509Certificate.getPublicKey(), str, i, bArr, bArr2);
    }

    @Override // org.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        Cipher d = this.f.d(getAlgorithmIdentifier().getAlgorithm(), new HashMap());
        try {
            d.init(3, this.g, new KTSParameterSpec.Builder(this.b, this.c, new DEROtherInfo.Builder(JceSymmetricKeyWrapper.a(this.b, this.c), this.d, this.e).build().getEncoded()).build(), this.h);
            return d.wrap(b.a(genericKey));
        } catch (Exception e) {
            throw new OperatorException("Unable to wrap contents key: " + e.getMessage(), e);
        }
    }

    public JceKTSKeyWrapper setProvider(String str) {
        this.f = new a(new NamedJcaJceHelper(str));
        return this;
    }

    public JceKTSKeyWrapper setProvider(Provider provider) {
        this.f = new a(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceKTSKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.h = secureRandom;
        return this;
    }
}
