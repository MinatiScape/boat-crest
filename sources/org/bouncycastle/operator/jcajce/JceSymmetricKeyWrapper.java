package org.bouncycastle.operator.jcajce;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.kisa.KISAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyWrapper;
import org.jose4j.keys.AesKey;
/* loaded from: classes13.dex */
public class JceSymmetricKeyWrapper extends SymmetricKeyWrapper {
    public a b;
    public SecureRandom c;
    public SecretKey d;

    public JceSymmetricKeyWrapper(SecretKey secretKey) {
        super(b(secretKey));
        this.b = new a(new DefaultJcaJceHelper());
        this.d = secretKey;
    }

    public static AlgorithmIdentifier a(String str, int i) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        ASN1ObjectIdentifier aSN1ObjectIdentifier2;
        if (str.startsWith("DES") || str.startsWith("TripleDES")) {
            return new AlgorithmIdentifier(PKCSObjectIdentifiers.id_alg_CMS3DESwrap, DERNull.INSTANCE);
        }
        if (str.startsWith("RC2")) {
            return new AlgorithmIdentifier(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.3.7"), new ASN1Integer(58L));
        }
        if (str.startsWith(AesKey.ALGORITHM)) {
            if (i == 128) {
                aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes128_wrap;
            } else if (i == 192) {
                aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes192_wrap;
            } else if (i != 256) {
                throw new IllegalArgumentException("illegal keysize in AES");
            } else {
                aSN1ObjectIdentifier2 = NISTObjectIdentifiers.id_aes256_wrap;
            }
            return new AlgorithmIdentifier(aSN1ObjectIdentifier2);
        } else if (str.startsWith("SEED")) {
            return new AlgorithmIdentifier(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap);
        } else {
            if (str.startsWith("Camellia")) {
                if (i == 128) {
                    aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia128_wrap;
                } else if (i == 192) {
                    aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia192_wrap;
                } else if (i != 256) {
                    throw new IllegalArgumentException("illegal keysize in Camellia");
                } else {
                    aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia256_wrap;
                }
                return new AlgorithmIdentifier(aSN1ObjectIdentifier);
            }
            throw new IllegalArgumentException("unknown algorithm");
        }
    }

    public static AlgorithmIdentifier b(SecretKey secretKey) {
        return a(secretKey.getAlgorithm(), secretKey.getEncoded().length * 8);
    }

    @Override // org.bouncycastle.operator.KeyWrapper
    public byte[] generateWrappedKey(GenericKey genericKey) throws OperatorException {
        Key a2 = b.a(genericKey);
        Cipher h = this.b.h(getAlgorithmIdentifier().getAlgorithm());
        try {
            h.init(3, this.d, this.c);
            return h.wrap(a2);
        } catch (GeneralSecurityException e) {
            throw new OperatorException("cannot wrap key: " + e.getMessage(), e);
        }
    }

    public JceSymmetricKeyWrapper setProvider(String str) {
        this.b = new a(new NamedJcaJceHelper(str));
        return this;
    }

    public JceSymmetricKeyWrapper setProvider(Provider provider) {
        this.b = new a(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceSymmetricKeyWrapper setSecureRandom(SecureRandom secureRandom) {
        this.c = secureRandom;
        return this;
    }
}
