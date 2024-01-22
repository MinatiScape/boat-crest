package org.bouncycastle.cert.crmf.jcajce;

import java.io.IOException;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidParameterSpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Null;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.iana.IANAObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.cms.CMSAlgorithm;
import org.bouncycastle.jcajce.util.AlgorithmParametersUtils;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.jose4j.keys.AesKey;
/* loaded from: classes12.dex */
public class a {
    public static final Map b;
    public static final Map c;
    public static final Map d;
    public static final Map e;
    public static final Map f;

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f14475a;

    /* renamed from: org.bouncycastle.cert.crmf.jcajce.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public class C0899a implements b {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f14476a;
        public final /* synthetic */ Key b;

        public C0899a(AlgorithmIdentifier algorithmIdentifier, Key key) {
            this.f14476a = algorithmIdentifier;
            this.b = key;
        }

        @Override // org.bouncycastle.cert.crmf.jcajce.a.b
        public Object a() throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException {
            Cipher c = a.this.c(this.f14476a.getAlgorithm());
            ASN1Primitive aSN1Primitive = (ASN1Primitive) this.f14476a.getParameters();
            ASN1ObjectIdentifier algorithm = this.f14476a.getAlgorithm();
            if (aSN1Primitive != null && !(aSN1Primitive instanceof ASN1Null)) {
                try {
                    AlgorithmParameters b = a.this.b(this.f14476a.getAlgorithm());
                    try {
                        AlgorithmParametersUtils.loadParameters(b, aSN1Primitive);
                        c.init(2, this.b, b);
                    } catch (IOException e) {
                        throw new CRMFException("error decoding algorithm parameters.", e);
                    }
                } catch (NoSuchAlgorithmException e2) {
                    if (!algorithm.equals(CMSAlgorithm.DES_EDE3_CBC) && !algorithm.equals(CMSAlgorithm.IDEA_CBC) && !algorithm.equals(CMSAlgorithm.AES128_CBC) && !algorithm.equals(CMSAlgorithm.AES192_CBC) && !algorithm.equals(CMSAlgorithm.AES256_CBC)) {
                        throw e2;
                    }
                    c.init(2, this.b, new IvParameterSpec(ASN1OctetString.getInstance(aSN1Primitive).getOctets()));
                }
            } else if (algorithm.equals(CMSAlgorithm.DES_EDE3_CBC) || algorithm.equals(CMSAlgorithm.IDEA_CBC) || algorithm.equals(CMSAlgorithm.CAST5_CBC)) {
                c.init(2, this.b, new IvParameterSpec(new byte[8]));
            } else {
                c.init(2, this.b);
            }
            return c;
        }
    }

    /* loaded from: classes12.dex */
    public interface b {
        Object a() throws CRMFException, InvalidAlgorithmParameterException, InvalidKeyException, InvalidParameterSpecException, NoSuchAlgorithmException, NoSuchPaddingException, NoSuchProviderException;
    }

    static {
        HashMap hashMap = new HashMap();
        b = hashMap;
        HashMap hashMap2 = new HashMap();
        c = hashMap2;
        HashMap hashMap3 = new HashMap();
        d = hashMap3;
        HashMap hashMap4 = new HashMap();
        e = hashMap4;
        HashMap hashMap5 = new HashMap();
        f = hashMap5;
        hashMap.put(PKCSObjectIdentifiers.des_EDE3_CBC, "DESEDE");
        hashMap.put(NISTObjectIdentifiers.id_aes128_CBC, AesKey.ALGORITHM);
        hashMap.put(NISTObjectIdentifiers.id_aes192_CBC, AesKey.ALGORITHM);
        hashMap.put(NISTObjectIdentifiers.id_aes256_CBC, AesKey.ALGORITHM);
        hashMap2.put(CMSAlgorithm.DES_EDE3_CBC, "DESEDE/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES128_CBC, "AES/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES192_CBC, "AES/CBC/PKCS5Padding");
        hashMap2.put(CMSAlgorithm.AES256_CBC, "AES/CBC/PKCS5Padding");
        ASN1ObjectIdentifier aSN1ObjectIdentifier = PKCSObjectIdentifiers.rsaEncryption;
        hashMap2.put(new ASN1ObjectIdentifier(aSN1ObjectIdentifier.getId()), "RSA/ECB/PKCS1Padding");
        hashMap3.put(OIWObjectIdentifiers.idSHA1, "SHA1");
        hashMap3.put(NISTObjectIdentifiers.id_sha224, "SHA224");
        hashMap3.put(NISTObjectIdentifiers.id_sha256, "SHA256");
        hashMap3.put(NISTObjectIdentifiers.id_sha384, "SHA384");
        hashMap3.put(NISTObjectIdentifiers.id_sha512, "SHA512");
        hashMap5.put(IANAObjectIdentifiers.hmacSHA1, "HMACSHA1");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "HMACSHA1");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "HMACSHA224");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "HMACSHA256");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "HMACSHA384");
        hashMap5.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "HMACSHA512");
        hashMap4.put(aSN1ObjectIdentifier, "RSA");
        hashMap4.put(X9ObjectIdentifiers.id_dsa, "DSA");
    }

    public a(JcaJceHelper jcaJceHelper) {
        this.f14475a = jcaJceHelper;
    }

    public static Object i(b bVar) throws CRMFException {
        try {
            return bVar.a();
        } catch (InvalidAlgorithmParameterException e2) {
            throw new CRMFException("algorithm parameters invalid.", e2);
        } catch (InvalidKeyException e3) {
            throw new CRMFException("key invalid in message.", e3);
        } catch (NoSuchAlgorithmException e4) {
            throw new CRMFException("can't find algorithm.", e4);
        } catch (NoSuchProviderException e5) {
            throw new CRMFException("can't find provider.", e5);
        } catch (InvalidParameterSpecException e6) {
            throw new CRMFException("MAC algorithm parameter spec invalid.", e6);
        } catch (NoSuchPaddingException e7) {
            throw new CRMFException("required padding not supported.", e7);
        }
    }

    public AlgorithmParameterGenerator a(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws GeneralSecurityException {
        String str = (String) b.get(aSN1ObjectIdentifier);
        if (str != null) {
            try {
                return this.f14475a.createAlgorithmParameterGenerator(str);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return this.f14475a.createAlgorithmParameterGenerator(aSN1ObjectIdentifier.getId());
    }

    public AlgorithmParameters b(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws NoSuchAlgorithmException, NoSuchProviderException {
        String str = (String) b.get(aSN1ObjectIdentifier);
        if (str != null) {
            try {
                return this.f14475a.createAlgorithmParameters(str);
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        return this.f14475a.createAlgorithmParameters(aSN1ObjectIdentifier.getId());
    }

    public Cipher c(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
        try {
            String str = (String) c.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.f14475a.createCipher(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.f14475a.createCipher(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    public Cipher d(Key key, AlgorithmIdentifier algorithmIdentifier) throws CRMFException {
        return (Cipher) i(new C0899a(algorithmIdentifier, key));
    }

    public MessageDigest e(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
        try {
            String str = (String) d.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.f14475a.createDigest(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.f14475a.createDigest(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    public KeyFactory f(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
        try {
            String str = (String) e.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.f14475a.createKeyFactory(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.f14475a.createKeyFactory(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create cipher: " + e2.getMessage(), e2);
        }
    }

    public KeyGenerator g(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
        try {
            String str = (String) b.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.f14475a.createKeyGenerator(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.f14475a.createKeyGenerator(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create key generator: " + e2.getMessage(), e2);
        }
    }

    public Mac h(ASN1ObjectIdentifier aSN1ObjectIdentifier) throws CRMFException {
        try {
            String str = (String) f.get(aSN1ObjectIdentifier);
            if (str != null) {
                try {
                    return this.f14475a.createMac(str);
                } catch (NoSuchAlgorithmException unused) {
                }
            }
            return this.f14475a.createMac(aSN1ObjectIdentifier.getId());
        } catch (GeneralSecurityException e2) {
            throw new CRMFException("cannot create mac: " + e2.getMessage(), e2);
        }
    }

    public AlgorithmParameters j(ASN1ObjectIdentifier aSN1ObjectIdentifier, SecretKey secretKey, SecureRandom secureRandom) throws CRMFException {
        try {
            AlgorithmParameterGenerator a2 = a(aSN1ObjectIdentifier);
            if (aSN1ObjectIdentifier.equals(CMSAlgorithm.RC2_CBC)) {
                byte[] bArr = new byte[8];
                secureRandom.nextBytes(bArr);
                try {
                    a2.init(new RC2ParameterSpec(secretKey.getEncoded().length * 8, bArr), secureRandom);
                } catch (InvalidAlgorithmParameterException e2) {
                    throw new CRMFException("parameters generation error: " + e2, e2);
                }
            }
            return a2.generateParameters();
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (GeneralSecurityException e3) {
            throw new CRMFException("exception creating algorithm parameter generator: " + e3, e3);
        }
    }

    public AlgorithmIdentifier k(ASN1ObjectIdentifier aSN1ObjectIdentifier, AlgorithmParameters algorithmParameters) throws CRMFException {
        ASN1Encodable extractParameters;
        if (algorithmParameters != null) {
            try {
                extractParameters = AlgorithmParametersUtils.extractParameters(algorithmParameters);
            } catch (IOException e2) {
                throw new CRMFException("cannot encode parameters: " + e2.getMessage(), e2);
            }
        } else {
            extractParameters = DERNull.INSTANCE;
        }
        return new AlgorithmIdentifier(aSN1ObjectIdentifier, extractParameters);
    }

    public PublicKey l(SubjectPublicKeyInfo subjectPublicKeyInfo) throws CRMFException {
        try {
            return f(subjectPublicKeyInfo.getAlgorithm().getAlgorithm()).generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        } catch (Exception e2) {
            throw new CRMFException("invalid key: " + e2.getMessage(), e2);
        }
    }
}
