package org.bouncycastle.cms.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.cms.KeyAgreeRecipientIdentifier;
import org.bouncycastle.asn1.cms.OriginatorPublicKey;
import org.bouncycastle.asn1.cms.RecipientEncryptedKey;
import org.bouncycastle.asn1.cms.RecipientKeyIdentifier;
import org.bouncycastle.asn1.cms.ecc.MQVuserKeyingMaterial;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.cryptopro.Gost2814789EncryptedKey;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KeyAgreeRecipientInfoGenerator;
import org.bouncycastle.jcajce.spec.GOST28147WrapParameterSpec;
import org.bouncycastle.jcajce.spec.MQVParameterSpec;
import org.bouncycastle.jcajce.spec.UserKeyingMaterialSpec;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class JceKeyAgreeRecipientInfoGenerator extends KeyAgreeRecipientInfoGenerator {
    public static d m = new g();
    public SecretKeySizeProvider d;
    public List e;
    public List f;
    public PublicKey g;
    public PrivateKey h;
    public EnvelopedDataHelper i;
    public SecureRandom j;
    public KeyPair k;
    public byte[] l;

    public JceKeyAgreeRecipientInfoGenerator(ASN1ObjectIdentifier aSN1ObjectIdentifier, PrivateKey privateKey, PublicKey publicKey, ASN1ObjectIdentifier aSN1ObjectIdentifier2) {
        super(aSN1ObjectIdentifier, SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()), aSN1ObjectIdentifier2);
        this.d = new DefaultSecretKeySizeProvider();
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.i = new EnvelopedDataHelper(new b());
        this.g = publicKey;
        this.h = privateKey;
    }

    public final void a(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CMSException {
        if (this.j == null) {
            this.j = new SecureRandom();
        }
        if (a.h(aSN1ObjectIdentifier) && this.k == null) {
            try {
                SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(this.g.getEncoded());
                AlgorithmParameters c = this.i.c(aSN1ObjectIdentifier);
                c.init(subjectPublicKeyInfo.getAlgorithm().getParameters().toASN1Primitive().getEncoded());
                KeyPairGenerator g = this.i.g(aSN1ObjectIdentifier);
                g.initialize(c.getParameterSpec(AlgorithmParameterSpec.class), this.j);
                this.k = g.generateKeyPair();
            } catch (Exception e) {
                throw new CMSException("cannot determine MQV ephemeral key pair parameters from public key: " + e, e);
            }
        }
    }

    public JceKeyAgreeRecipientInfoGenerator addRecipient(X509Certificate x509Certificate) throws CertificateEncodingException {
        this.e.add(new KeyAgreeRecipientIdentifier(a.d(x509Certificate)));
        this.f.add(x509Certificate.getPublicKey());
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator addRecipient(byte[] bArr, PublicKey publicKey) throws CertificateEncodingException {
        this.e.add(new KeyAgreeRecipientIdentifier(new RecipientKeyIdentifier(bArr)));
        this.f.add(publicKey);
        return this;
    }

    @Override // org.bouncycastle.cms.KeyAgreeRecipientInfoGenerator
    public ASN1Sequence generateRecipientEncryptedKeys(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, GenericKey genericKey) throws CMSException {
        UserKeyingMaterialSpec userKeyingMaterialSpec;
        AlgorithmParameterSpec algorithmParameterSpec;
        DEROctetString dEROctetString;
        if (this.e.isEmpty()) {
            throw new CMSException("No recipients associated with generator - use addRecipient()");
        }
        a(algorithmIdentifier.getAlgorithm());
        PrivateKey privateKey = this.h;
        ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        for (int i = 0; i != this.e.size(); i++) {
            PublicKey publicKey = (PublicKey) this.f.get(i);
            KeyAgreeRecipientIdentifier keyAgreeRecipientIdentifier = (KeyAgreeRecipientIdentifier) this.e.get(i);
            try {
                ASN1ObjectIdentifier algorithm2 = algorithmIdentifier2.getAlgorithm();
                if (a.h(algorithm)) {
                    algorithmParameterSpec = new MQVParameterSpec(this.k, publicKey, this.l);
                } else {
                    if (a.f(algorithm)) {
                        userKeyingMaterialSpec = new UserKeyingMaterialSpec(m.a(algorithmIdentifier2, this.d.getKeySize(algorithm2), this.l));
                    } else if (a.i(algorithm)) {
                        byte[] bArr = this.l;
                        if (bArr != null) {
                            userKeyingMaterialSpec = new UserKeyingMaterialSpec(bArr);
                        } else if (algorithm.equals(PKCSObjectIdentifiers.id_alg_SSDH)) {
                            throw new CMSException("User keying material must be set for static keys.");
                        } else {
                            algorithmParameterSpec = null;
                        }
                    } else if (!a.g(algorithm)) {
                        throw new CMSException("Unknown key agreement algorithm: " + algorithm);
                    } else {
                        byte[] bArr2 = this.l;
                        if (bArr2 == null) {
                            throw new CMSException("User keying material must be set for static keys.");
                        }
                        userKeyingMaterialSpec = new UserKeyingMaterialSpec(bArr2);
                    }
                    algorithmParameterSpec = userKeyingMaterialSpec;
                }
                KeyAgreement f = this.i.f(algorithm);
                f.init(privateKey, algorithmParameterSpec, this.j);
                f.doPhase(publicKey, true);
                SecretKey generateSecret = f.generateSecret(algorithm2.getId());
                Cipher d = this.i.d(algorithm2);
                if (!algorithm2.equals(CryptoProObjectIdentifiers.id_Gost28147_89_None_KeyWrap) && !algorithm2.equals(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_KeyWrap)) {
                    d.init(3, generateSecret, this.j);
                    dEROctetString = new DEROctetString(d.wrap(this.i.n(genericKey)));
                    aSN1EncodableVector.add(new RecipientEncryptedKey(keyAgreeRecipientIdentifier, dEROctetString));
                }
                d.init(3, generateSecret, new GOST28147WrapParameterSpec(CryptoProObjectIdentifiers.id_Gost28147_89_CryptoPro_A_ParamSet, this.l));
                byte[] wrap = d.wrap(this.i.n(genericKey));
                dEROctetString = new DEROctetString(new Gost2814789EncryptedKey(Arrays.copyOfRange(wrap, 0, wrap.length - 4), Arrays.copyOfRange(wrap, wrap.length - 4, wrap.length)).getEncoded(ASN1Encoding.DER));
                aSN1EncodableVector.add(new RecipientEncryptedKey(keyAgreeRecipientIdentifier, dEROctetString));
            } catch (IOException e) {
                throw new CMSException("unable to encode wrapped key: " + e.getMessage(), e);
            } catch (GeneralSecurityException e2) {
                throw new CMSException("cannot perform agreement step: " + e2.getMessage(), e2);
            }
        }
        return new DERSequence(aSN1EncodableVector);
    }

    @Override // org.bouncycastle.cms.KeyAgreeRecipientInfoGenerator
    public byte[] getUserKeyingMaterial(AlgorithmIdentifier algorithmIdentifier) throws CMSException {
        a(algorithmIdentifier.getAlgorithm());
        KeyPair keyPair = this.k;
        if (keyPair != null) {
            OriginatorPublicKey createOriginatorPublicKey = createOriginatorPublicKey(SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded()));
            try {
                byte[] bArr = this.l;
                return bArr != null ? new MQVuserKeyingMaterial(createOriginatorPublicKey, new DEROctetString(bArr)).getEncoded() : new MQVuserKeyingMaterial(createOriginatorPublicKey, null).getEncoded();
            } catch (IOException e) {
                throw new CMSException("unable to encode user keying material: " + e.getMessage(), e);
            }
        }
        return this.l;
    }

    public JceKeyAgreeRecipientInfoGenerator setProvider(String str) {
        this.i = new EnvelopedDataHelper(new e(str));
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setProvider(Provider provider) {
        this.i = new EnvelopedDataHelper(new f(provider));
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setSecureRandom(SecureRandom secureRandom) {
        this.j = secureRandom;
        return this;
    }

    public JceKeyAgreeRecipientInfoGenerator setUserKeyingMaterial(byte[] bArr) {
        this.l = Arrays.clone(bArr);
        return this;
    }
}
