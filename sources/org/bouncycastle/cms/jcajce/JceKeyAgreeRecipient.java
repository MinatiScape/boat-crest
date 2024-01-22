package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.cms.ecc.ECCCMSSharedInfo;
import org.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey;
import org.bouncycastle.asn1.cryptopro.Gost2814789KeyWrapParameters;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyAgreeRecipient;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.spec.MQVParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public abstract class JceKeyAgreeRecipient implements KeyAgreeRecipient {
    public static final Set c;
    public static d d;
    public static d e;

    /* renamed from: a  reason: collision with root package name */
    public PrivateKey f14593a;
    public SecretKeySizeProvider b;
    public EnvelopedDataHelper contentHelper;
    public EnvelopedDataHelper helper;

    /* loaded from: classes12.dex */
    public static class a implements d {
        @Override // org.bouncycastle.cms.jcajce.d
        public byte[] a(AlgorithmIdentifier algorithmIdentifier, int i, byte[] bArr) {
            try {
                return new ECCCMSSharedInfo(new AlgorithmIdentifier(algorithmIdentifier.getAlgorithm(), DERNull.INSTANCE), bArr, Pack.intToBigEndian(i)).getEncoded(ASN1Encoding.DER);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to create KDF material: " + e);
            }
        }
    }

    static {
        HashSet hashSet = new HashSet();
        c = hashSet;
        hashSet.add(X9ObjectIdentifiers.dhSinglePass_stdDH_sha1kdf_scheme);
        hashSet.add(X9ObjectIdentifiers.mqvSinglePass_sha1kdf_scheme);
        d = new a();
        e = new g();
    }

    public JceKeyAgreeRecipient(PrivateKey privateKey) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new b());
        this.helper = envelopedDataHelper;
        this.contentHelper = envelopedDataHelper;
        this.b = new DefaultSecretKeySizeProvider();
        this.f14593a = privateKey;
    }

    public final SecretKey a(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, PublicKey publicKey, ASN1OctetString aSN1OctetString, PrivateKey privateKey, d dVar) throws CMSException, GeneralSecurityException, IOException {
        UserKeyingMaterialSpec userKeyingMaterialSpec = null;
        userKeyingMaterialSpec = null;
        if (org.bouncycastle.cms.jcajce.a.h(algorithmIdentifier.getAlgorithm())) {
            MQVuserKeyingMaterial mQVuserKeyingMaterial = MQVuserKeyingMaterial.getInstance(aSN1OctetString.getOctets());
            PublicKey generatePublic = this.helper.createKeyFactory(algorithmIdentifier.getAlgorithm()).generatePublic(new X509EncodedKeySpec(new SubjectPublicKeyInfo(getPrivateKeyAlgorithmIdentifier(), mQVuserKeyingMaterial.getEphemeralPublicKey().getPublicKey().getBytes()).getEncoded()));
            KeyAgreement f = this.helper.f(algorithmIdentifier.getAlgorithm());
            byte[] octets = mQVuserKeyingMaterial.getAddedukm() != null ? mQVuserKeyingMaterial.getAddedukm().getOctets() : null;
            d dVar2 = d;
            if (dVar == dVar2) {
                octets = dVar2.a(algorithmIdentifier2, this.b.getKeySize(algorithmIdentifier2), octets);
            }
            f.init(privateKey, new MQVParameterSpec(privateKey, generatePublic, octets));
            f.doPhase(publicKey, true);
            return f.generateSecret(algorithmIdentifier2.getAlgorithm().getId());
        }
        KeyAgreement f2 = this.helper.f(algorithmIdentifier.getAlgorithm());
        if (org.bouncycastle.cms.jcajce.a.f(algorithmIdentifier.getAlgorithm())) {
            int keySize = this.b.getKeySize(algorithmIdentifier2);
            userKeyingMaterialSpec = aSN1OctetString != null ? new UserKeyingMaterialSpec(dVar.a(algorithmIdentifier2, keySize, aSN1OctetString.getOctets())) : new UserKeyingMaterialSpec(dVar.a(algorithmIdentifier2, keySize, null));
        } else if (org.bouncycastle.cms.jcajce.a.i(algorithmIdentifier.getAlgorithm())) {
            if (aSN1OctetString != null) {
                userKeyingMaterialSpec = new UserKeyingMaterialSpec(aSN1OctetString.getOctets());
            }
        } else if (!org.bouncycastle.cms.jcajce.a.g(algorithmIdentifier.getAlgorithm())) {
            throw new CMSException("Unknown key agreement algorithm: " + algorithmIdentifier.getAlgorithm());
        } else if (aSN1OctetString != null) {
            userKeyingMaterialSpec = new UserKeyingMaterialSpec(aSN1OctetString.getOctets());
        }
        f2.init(privateKey, userKeyingMaterialSpec);
        f2.doPhase(publicKey, true);
        return f2.generateSecret(algorithmIdentifier2.getAlgorithm().getId());
    }

    public final Key b(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2, byte[] bArr) throws CMSException, InvalidKeyException, NoSuchAlgorithmException {
        Cipher d2 = this.helper.d(aSN1ObjectIdentifier);
        d2.init(4, secretKey);
        return d2.unwrap(bArr, this.helper.m(aSN1ObjectIdentifier2), 3);
    }

    public Key extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, SubjectPublicKeyInfo subjectPublicKeyInfo, ASN1OctetString aSN1OctetString, byte[] bArr) throws CMSException {
        try {
            try {
                AlgorithmIdentifier algorithmIdentifier3 = AlgorithmIdentifier.getInstance(algorithmIdentifier.getParameters());
                PublicKey generatePublic = this.helper.createKeyFactory(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
                try {
                    SecretKey a2 = a(algorithmIdentifier, algorithmIdentifier3, generatePublic, aSN1OctetString, this.f14593a, e);
                    if (!algorithmIdentifier3.getAlgorithm().equals(CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap) && !algorithmIdentifier3.getAlgorithm().equals(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap)) {
                        return b(algorithmIdentifier3.getAlgorithm(), a2, algorithmIdentifier2.getAlgorithm(), bArr);
                    }
                    Gost2814789EncryptedKey gost2814789EncryptedKey = Gost2814789EncryptedKey.getInstance(bArr);
                    Gost2814789KeyWrapParameters gost2814789KeyWrapParameters = Gost2814789KeyWrapParameters.getInstance(algorithmIdentifier3.getParameters());
                    Cipher d2 = this.helper.d(algorithmIdentifier3.getAlgorithm());
                    d2.init(4, a2, new GOST28147WrapParameterSpec(gost2814789KeyWrapParameters.getEncryptionParamSet(), aSN1OctetString.getOctets()));
                    return d2.unwrap(Arrays.concatenate(gost2814789EncryptedKey.getEncryptedKey(), gost2814789EncryptedKey.getMacKey()), this.helper.m(algorithmIdentifier2.getAlgorithm()), 3);
                } catch (InvalidKeyException e2) {
                    if (c.contains(algorithmIdentifier.getAlgorithm())) {
                        return b(algorithmIdentifier3.getAlgorithm(), a(algorithmIdentifier, algorithmIdentifier3, generatePublic, aSN1OctetString, this.f14593a, d), algorithmIdentifier2.getAlgorithm(), bArr);
                    }
                    throw e2;
                }
            } catch (InvalidKeyException e3) {
                throw new CMSException("key invalid in message.", e3);
            }
        } catch (NoSuchAlgorithmException e4) {
            throw new CMSException("can't find algorithm.", e4);
        } catch (InvalidKeySpecException e5) {
            throw new CMSException("originator key spec invalid.", e5);
        } catch (NoSuchPaddingException e6) {
            throw new CMSException("required padding not supported.", e6);
        } catch (Exception e7) {
            throw new CMSException("originator key invalid.", e7);
        }
    }

    @Override // org.bouncycastle.cms.KeyAgreeRecipient
    public AlgorithmIdentifier getPrivateKeyAlgorithmIdentifier() {
        return PrivateKeyInfo.getInstance(this.f14593a.getEncoded()).getPrivateKeyAlgorithm();
    }

    public JceKeyAgreeRecipient setContentProvider(String str) {
        this.contentHelper = org.bouncycastle.cms.jcajce.a.a(str);
        return this;
    }

    public JceKeyAgreeRecipient setContentProvider(Provider provider) {
        this.contentHelper = org.bouncycastle.cms.jcajce.a.b(provider);
        return this;
    }

    public JceKeyAgreeRecipient setProvider(String str) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new e(str));
        this.helper = envelopedDataHelper;
        this.contentHelper = envelopedDataHelper;
        return this;
    }

    public JceKeyAgreeRecipient setProvider(Provider provider) {
        EnvelopedDataHelper envelopedDataHelper = new EnvelopedDataHelper(new f(provider));
        this.helper = envelopedDataHelper;
        this.contentHelper = envelopedDataHelper;
        return this;
    }
}
