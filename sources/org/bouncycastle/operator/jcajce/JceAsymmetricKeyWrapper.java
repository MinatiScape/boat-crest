package org.bouncycastle.operator.jcajce;

import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.ProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.RSAESOAEPparams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.AsymmetricKeyWrapper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
/* loaded from: classes13.dex */
public class JceAsymmetricKeyWrapper extends AsymmetricKeyWrapper {
    public static final Map f;
    public a b;
    public Map c;
    public PublicKey d;
    public SecureRandom e;

    static {
        HashMap hashMap = new HashMap();
        f = hashMap;
        ASN1ObjectIdentifier aSN1ObjectIdentifier = OIWObjectIdentifiers.idSHA1;
        DERNull dERNull = DERNull.INSTANCE;
        hashMap.put("SHA-1", new AlgorithmIdentifier(aSN1ObjectIdentifier, dERNull));
        hashMap.put("SHA-1", new AlgorithmIdentifier(aSN1ObjectIdentifier, dERNull));
        ASN1ObjectIdentifier aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_sha224;
        hashMap.put("SHA224", new AlgorithmIdentifier(aSN1ObjectIdentifier2, dERNull));
        hashMap.put("SHA-224", new AlgorithmIdentifier(aSN1ObjectIdentifier2, dERNull));
        ASN1ObjectIdentifier aSN1ObjectIdentifier3 = NISTObjectIdentifiers.id_sha256;
        hashMap.put("SHA256", new AlgorithmIdentifier(aSN1ObjectIdentifier3, dERNull));
        hashMap.put("SHA-256", new AlgorithmIdentifier(aSN1ObjectIdentifier3, dERNull));
        ASN1ObjectIdentifier aSN1ObjectIdentifier4 = NISTObjectIdentifiers.id_sha384;
        hashMap.put("SHA384", new AlgorithmIdentifier(aSN1ObjectIdentifier4, dERNull));
        hashMap.put("SHA-384", new AlgorithmIdentifier(aSN1ObjectIdentifier4, dERNull));
        ASN1ObjectIdentifier aSN1ObjectIdentifier5 = NISTObjectIdentifiers.id_sha512;
        hashMap.put("SHA512", new AlgorithmIdentifier(aSN1ObjectIdentifier5, dERNull));
        hashMap.put("SHA-512", new AlgorithmIdentifier(aSN1ObjectIdentifier5, dERNull));
        ASN1ObjectIdentifier aSN1ObjectIdentifier6 = NISTObjectIdentifiers.id_sha512_224;
        hashMap.put("SHA512/224", new AlgorithmIdentifier(aSN1ObjectIdentifier6, dERNull));
        hashMap.put(MessageDigestAlgorithms.SHA_512_224, new AlgorithmIdentifier(aSN1ObjectIdentifier6, dERNull));
        hashMap.put("SHA-512(224)", new AlgorithmIdentifier(aSN1ObjectIdentifier6, dERNull));
        ASN1ObjectIdentifier aSN1ObjectIdentifier7 = NISTObjectIdentifiers.id_sha512_256;
        hashMap.put("SHA512/256", new AlgorithmIdentifier(aSN1ObjectIdentifier7, dERNull));
        hashMap.put(MessageDigestAlgorithms.SHA_512_256, new AlgorithmIdentifier(aSN1ObjectIdentifier7, dERNull));
        hashMap.put("SHA-512(256)", new AlgorithmIdentifier(aSN1ObjectIdentifier7, dERNull));
    }

    public JceAsymmetricKeyWrapper(PublicKey publicKey) {
        super(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()).getAlgorithm());
        this.b = new a(new DefaultJcaJceHelper());
        this.c = new HashMap();
        this.d = publicKey;
    }

    public JceAsymmetricKeyWrapper(X509Certificate x509Certificate) {
        this(x509Certificate.getPublicKey());
    }

    public JceAsymmetricKeyWrapper(AlgorithmParameterSpec algorithmParameterSpec, PublicKey publicKey) {
        super(a(algorithmParameterSpec));
        this.b = new a(new DefaultJcaJceHelper());
        this.c = new HashMap();
        this.d = publicKey;
    }

    public JceAsymmetricKeyWrapper(AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        super(algorithmIdentifier);
        this.b = new a(new DefaultJcaJceHelper());
        this.c = new HashMap();
        this.d = publicKey;
    }

    public static AlgorithmIdentifier a(AlgorithmParameterSpec algorithmParameterSpec) {
        if (!(algorithmParameterSpec instanceof OAEPParameterSpec)) {
            throw new IllegalArgumentException("unknown spec: " + algorithmParameterSpec.getClass().getName());
        }
        OAEPParameterSpec oAEPParameterSpec = (OAEPParameterSpec) algorithmParameterSpec;
        if (!oAEPParameterSpec.getMGFAlgorithm().equals(OAEPParameterSpec.DEFAULT.getMGFAlgorithm())) {
            throw new IllegalArgumentException("unknown MGF: " + oAEPParameterSpec.getMGFAlgorithm());
        } else if (oAEPParameterSpec.getPSource() instanceof PSource.PSpecified) {
            return new AlgorithmIdentifier(PKCSObjectIdentifiers.id_RSAES_OAEP, new RSAESOAEPparams(b(oAEPParameterSpec.getDigestAlgorithm()), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_mgf1, b(((MGF1ParameterSpec) oAEPParameterSpec.getMGFParameters()).getDigestAlgorithm())), new AlgorithmIdentifier(PKCSObjectIdentifiers.id_pSpecified, new DEROctetString(((PSource.PSpecified) oAEPParameterSpec.getPSource()).getValue()))));
        } else {
            throw new IllegalArgumentException("unknown PSource: " + oAEPParameterSpec.getPSource().getAlgorithm());
        }
    }

    public static AlgorithmIdentifier b(String str) {
        AlgorithmIdentifier algorithmIdentifier = (AlgorithmIdentifier) f.get(str);
        if (algorithmIdentifier != null) {
            return algorithmIdentifier;
        }
        throw new IllegalArgumentException("unknown digest name: " + str);
    }

    @Override // org.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        byte[] bArr;
        Cipher d = this.b.d(getAlgorithmIdentifier().getAlgorithm(), this.c);
        AlgorithmParameters c = this.b.c(getAlgorithmIdentifier());
        try {
            if (c != null) {
                d.init(3, this.d, c, this.e);
            } else {
                d.init(3, this.d, this.e);
            }
            bArr = d.wrap(b.a(genericKey));
        } catch (IllegalStateException | UnsupportedOperationException | InvalidKeyException | GeneralSecurityException | ProviderException unused) {
            bArr = null;
        }
        if (bArr == null) {
            try {
                d.init(1, this.d, this.e);
                return d.doFinal(b.a(genericKey).getEncoded());
            } catch (InvalidKeyException e) {
                throw new OperatorException("unable to encrypt contents key", e);
            } catch (GeneralSecurityException e2) {
                throw new OperatorException("unable to encrypt contents key", e2);
            }
        }
        return bArr;
    }

    public JceAsymmetricKeyWrapper setAlgorithmMapping(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        this.c.put(aSN1ObjectIdentifier, str);
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(String str) {
        this.b = new a(new NamedJcaJceHelper(str));
        return this;
    }

    public JceAsymmetricKeyWrapper setProvider(Provider provider) {
        this.b = new a(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceAsymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.e = secureRandom;
        return this;
    }
}
