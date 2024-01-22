package org.bouncycastle.openssl.jcajce;

import java.io.IOException;
import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.Provider;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.CharToByteConverter;
import org.bouncycastle.jcajce.PBKDF1KeyWithParameters;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMException;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Strings;
/* loaded from: classes13.dex */
public class JceOpenSSLPKCS8DecryptorProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15208a;

    /* loaded from: classes13.dex */
    public class a implements InputDecryptorProvider {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ char[] f15209a;

        /* renamed from: org.bouncycastle.openssl.jcajce.JceOpenSSLPKCS8DecryptorProviderBuilder$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0907a implements CharToByteConverter {
            public C0907a(a aVar) {
            }

            @Override // org.bouncycastle.crypto.CharToByteConverter
            public byte[] convert(char[] cArr) {
                return Strings.toByteArray(cArr);
            }

            @Override // org.bouncycastle.crypto.CharToByteConverter
            public String getType() {
                return "ASCII";
            }
        }

        /* loaded from: classes13.dex */
        public class b implements InputDecryptor {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ AlgorithmIdentifier f15210a;
            public final /* synthetic */ Cipher b;

            public b(a aVar, AlgorithmIdentifier algorithmIdentifier, Cipher cipher) {
                this.f15210a = algorithmIdentifier;
                this.b = cipher;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return this.f15210a;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, this.b);
            }
        }

        public a(char[] cArr) {
            this.f15209a = cArr;
        }

        @Override // org.bouncycastle.operator.InputDecryptorProvider
        public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            Cipher createCipher;
            Key pBKDF1KeyWithParameters;
            try {
                if (org.bouncycastle.openssl.jcajce.a.k(algorithmIdentifier.getAlgorithm())) {
                    PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                    KeyDerivationFunc keyDerivationFunc = pBES2Parameters.getKeyDerivationFunc();
                    EncryptionScheme encryptionScheme = pBES2Parameters.getEncryptionScheme();
                    PBKDF2Params pBKDF2Params = (PBKDF2Params) keyDerivationFunc.getParameters();
                    int intValue = pBKDF2Params.getIterationCount().intValue();
                    byte[] salt = pBKDF2Params.getSalt();
                    String id = encryptionScheme.getAlgorithm().getId();
                    SecretKey b2 = org.bouncycastle.openssl.jcajce.a.h(pBKDF2Params.getPrf()) ? org.bouncycastle.openssl.jcajce.a.b(JceOpenSSLPKCS8DecryptorProviderBuilder.this.f15208a, id, this.f15209a, salt, intValue) : org.bouncycastle.openssl.jcajce.a.c(JceOpenSSLPKCS8DecryptorProviderBuilder.this.f15208a, id, this.f15209a, salt, intValue, pBKDF2Params.getPrf());
                    createCipher = JceOpenSSLPKCS8DecryptorProviderBuilder.this.f15208a.createCipher(id);
                    AlgorithmParameters createAlgorithmParameters = JceOpenSSLPKCS8DecryptorProviderBuilder.this.f15208a.createAlgorithmParameters(id);
                    createAlgorithmParameters.init(encryptionScheme.getParameters().toASN1Primitive().getEncoded());
                    createCipher.init(2, b2, createAlgorithmParameters);
                } else {
                    if (org.bouncycastle.openssl.jcajce.a.i(algorithmIdentifier.getAlgorithm())) {
                        PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                        createCipher = JceOpenSSLPKCS8DecryptorProviderBuilder.this.f15208a.createCipher(algorithmIdentifier.getAlgorithm().getId());
                        pBKDF1KeyWithParameters = new PKCS12KeyWithParameters(this.f15209a, pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue());
                    } else if (!org.bouncycastle.openssl.jcajce.a.j(algorithmIdentifier.getAlgorithm())) {
                        throw new PEMException("Unknown algorithm: " + algorithmIdentifier.getAlgorithm());
                    } else {
                        PBEParameter pBEParameter = PBEParameter.getInstance(algorithmIdentifier.getParameters());
                        createCipher = JceOpenSSLPKCS8DecryptorProviderBuilder.this.f15208a.createCipher(algorithmIdentifier.getAlgorithm().getId());
                        pBKDF1KeyWithParameters = new PBKDF1KeyWithParameters(this.f15209a, new C0907a(this), pBEParameter.getSalt(), pBEParameter.getIterationCount().intValue());
                    }
                    createCipher.init(2, pBKDF1KeyWithParameters);
                }
                return new b(this, algorithmIdentifier, createCipher);
            } catch (IOException e) {
                throw new OperatorCreationException(algorithmIdentifier.getAlgorithm() + " not available: " + e.getMessage(), e);
            } catch (GeneralSecurityException e2) {
                throw new OperatorCreationException(algorithmIdentifier.getAlgorithm() + " not available: " + e2.getMessage(), e2);
            }
        }
    }

    public JceOpenSSLPKCS8DecryptorProviderBuilder() {
        this.f15208a = new DefaultJcaJceHelper();
        this.f15208a = new DefaultJcaJceHelper();
    }

    public InputDecryptorProvider build(char[] cArr) throws OperatorCreationException {
        return new a(cArr);
    }

    public JceOpenSSLPKCS8DecryptorProviderBuilder setProvider(String str) {
        this.f15208a = new NamedJcaJceHelper(str);
        return this;
    }

    public JceOpenSSLPKCS8DecryptorProviderBuilder setProvider(Provider provider) {
        this.f15208a = new ProviderJcaJceHelper(provider);
        return this;
    }
}
