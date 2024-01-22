package org.bouncycastle.openssl.jcajce;

import java.io.IOException;
import java.io.OutputStream;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.jcajce.JceGenericKey;
/* loaded from: classes13.dex */
public class JceOpenSSLPKCS8EncryptorBuilder {
    public AlgorithmParameters b;
    public ASN1ObjectIdentifier c;
    public byte[] d;
    public Cipher f;
    public SecureRandom g;
    public AlgorithmParameterGenerator h;
    public char[] i;
    public SecretKey j;
    public static final String AES_128_CBC = NISTObjectIdentifiers.id_aes128_CBC.getId();
    public static final String AES_192_CBC = NISTObjectIdentifiers.id_aes192_CBC.getId();
    public static final String AES_256_CBC = NISTObjectIdentifiers.id_aes256_CBC.getId();
    public static final String DES3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC.getId();
    public static final String PBE_SHA1_RC4_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4.getId();
    public static final String PBE_SHA1_RC4_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4.getId();
    public static final String PBE_SHA1_3DES = PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC.getId();
    public static final String PBE_SHA1_2DES = PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC.getId();
    public static final String PBE_SHA1_RC2_128 = PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC.getId();
    public static final String PBE_SHA1_RC2_40 = PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC.getId();

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15211a = new DefaultJcaJceHelper();
    public AlgorithmIdentifier k = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_hmacWithSHA1, DERNull.INSTANCE);
    public int e = 2048;

    /* loaded from: classes13.dex */
    public class a implements OutputEncryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f15212a;

        public a(AlgorithmIdentifier algorithmIdentifier) {
            this.f15212a = algorithmIdentifier;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f15212a;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new JceGenericKey(this.f15212a, JceOpenSSLPKCS8EncryptorBuilder.this.j);
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, JceOpenSSLPKCS8EncryptorBuilder.this.f);
        }
    }

    public JceOpenSSLPKCS8EncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.c = aSN1ObjectIdentifier;
    }

    public OutputEncryptor build() throws OperatorCreationException {
        AlgorithmIdentifier algorithmIdentifier;
        if (this.g == null) {
            this.g = new SecureRandom();
        }
        try {
            this.f = this.f15211a.createCipher(this.c.getId());
            if (org.bouncycastle.openssl.jcajce.a.k(this.c)) {
                this.h = this.f15211a.createAlgorithmParameterGenerator(this.c.getId());
            }
            if (org.bouncycastle.openssl.jcajce.a.k(this.c)) {
                byte[] bArr = new byte[org.bouncycastle.openssl.jcajce.a.g(this.k.getAlgorithm())];
                this.d = bArr;
                this.g.nextBytes(bArr);
                AlgorithmParameters generateParameters = this.h.generateParameters();
                this.b = generateParameters;
                try {
                    EncryptionScheme encryptionScheme = new EncryptionScheme(this.c, ASN1Primitive.fromByteArray(generateParameters.getEncoded()));
                    KeyDerivationFunc keyDerivationFunc = new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(this.d, this.e, this.k));
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    aSN1EncodableVector.add(keyDerivationFunc);
                    aSN1EncodableVector.add(encryptionScheme);
                    algorithmIdentifier = new AlgorithmIdentifier(PKCSObjectIdentifiers.id_PBES2, PBES2Parameters.getInstance(new DERSequence(aSN1EncodableVector)));
                    try {
                        this.j = org.bouncycastle.openssl.jcajce.a.h(this.k) ? org.bouncycastle.openssl.jcajce.a.b(this.f15211a, this.c.getId(), this.i, this.d, this.e) : org.bouncycastle.openssl.jcajce.a.c(this.f15211a, this.c.getId(), this.i, this.d, this.e, this.k);
                        this.f.init(1, this.j, this.b);
                    } catch (GeneralSecurityException e) {
                        throw new OperatorCreationException(e.getMessage(), e);
                    }
                } catch (IOException e2) {
                    throw new OperatorCreationException(e2.getMessage(), e2);
                }
            } else if (!org.bouncycastle.openssl.jcajce.a.i(this.c)) {
                throw new OperatorCreationException("unknown algorithm: " + this.c, null);
            } else {
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                byte[] bArr2 = new byte[20];
                this.d = bArr2;
                this.g.nextBytes(bArr2);
                aSN1EncodableVector2.add(new DEROctetString(this.d));
                aSN1EncodableVector2.add(new ASN1Integer(this.e));
                AlgorithmIdentifier algorithmIdentifier2 = new AlgorithmIdentifier(this.c, PKCS12PBEParams.getInstance(new DERSequence(aSN1EncodableVector2)));
                try {
                    this.f.init(1, new PKCS12KeyWithParameters(this.i, this.d, this.e));
                    algorithmIdentifier = algorithmIdentifier2;
                } catch (GeneralSecurityException e3) {
                    throw new OperatorCreationException(e3.getMessage(), e3);
                }
            }
            return new a(algorithmIdentifier);
        } catch (GeneralSecurityException e4) {
            throw new OperatorCreationException(this.c + " not available: " + e4.getMessage(), e4);
        }
    }

    public JceOpenSSLPKCS8EncryptorBuilder setIterationCount(int i) {
        this.e = i;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setPRF(AlgorithmIdentifier algorithmIdentifier) {
        this.k = algorithmIdentifier;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setPasssword(char[] cArr) {
        this.i = cArr;
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setProvider(String str) {
        this.f15211a = new NamedJcaJceHelper(str);
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setProvider(Provider provider) {
        this.f15211a = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JceOpenSSLPKCS8EncryptorBuilder setRandom(SecureRandom secureRandom) {
        this.g = secureRandom;
        return this;
    }
}
