package org.bouncycastle.pkcs.jcajce;

import java.io.OutputStream;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.bc.BCObjectIdentifiers;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.ScryptParams;
import org.bouncycastle.asn1.pkcs.EncryptionScheme;
import org.bouncycastle.asn1.pkcs.KeyDerivationFunc;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.util.PBKDF2Config;
import org.bouncycastle.crypto.util.PBKDFConfig;
import org.bouncycastle.crypto.util.ScryptConfig;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.spec.ScryptKeySpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
/* loaded from: classes13.dex */
public class JcePKCSPBEOutputEncryptorBuilder {

    /* renamed from: a  reason: collision with root package name */
    public final PBKDFConfig f15281a;
    public JcaJceHelper b;
    public ASN1ObjectIdentifier c;
    public ASN1ObjectIdentifier d;
    public SecureRandom e;
    public SecretKeySizeProvider f;
    public int g;
    public PBKDF2Config.Builder h;

    /* loaded from: classes13.dex */
    public class a implements OutputEncryptor {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ AlgorithmIdentifier f15282a;
        public final /* synthetic */ Cipher b;
        public final /* synthetic */ char[] c;

        public a(AlgorithmIdentifier algorithmIdentifier, Cipher cipher, char[] cArr) {
            this.f15282a = algorithmIdentifier;
            this.b = cipher;
            this.c = cArr;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.f15282a;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return JcePKCSPBEOutputEncryptorBuilder.this.f(this.f15282a.getAlgorithm()) ? new GenericKey(this.f15282a, JcePKCSPBEOutputEncryptorBuilder.a(this.c)) : new GenericKey(this.f15282a, JcePKCSPBEOutputEncryptorBuilder.b(this.c));
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, this.b);
        }
    }

    public JcePKCSPBEOutputEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.b = new DefaultJcaJceHelper();
        this.f = DefaultSecretKeySizeProvider.INSTANCE;
        this.g = 1024;
        this.h = new PBKDF2Config.Builder();
        this.f15281a = null;
        if (f(aSN1ObjectIdentifier)) {
            this.c = aSN1ObjectIdentifier;
        } else {
            this.c = PKCSObjectIdentifiers.id_PBES2;
        }
        this.d = aSN1ObjectIdentifier;
    }

    public JcePKCSPBEOutputEncryptorBuilder(PBKDFConfig pBKDFConfig, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.b = new DefaultJcaJceHelper();
        this.f = DefaultSecretKeySizeProvider.INSTANCE;
        this.g = 1024;
        this.h = new PBKDF2Config.Builder();
        this.c = PKCSObjectIdentifiers.id_PBES2;
        this.f15281a = pBKDFConfig;
        this.d = aSN1ObjectIdentifier;
    }

    public static byte[] a(char[] cArr) {
        if (cArr == null || cArr.length <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[(cArr.length + 1) * 2];
        for (int i = 0; i != cArr.length; i++) {
            int i2 = i * 2;
            bArr[i2] = (byte) (cArr[i] >>> '\b');
            bArr[i2 + 1] = (byte) cArr[i];
        }
        return bArr;
    }

    public static byte[] b(char[] cArr) {
        if (cArr != null) {
            int length = cArr.length;
            byte[] bArr = new byte[length];
            for (int i = 0; i != length; i++) {
                bArr[i] = (byte) cArr[i];
            }
            return bArr;
        }
        return new byte[0];
    }

    public OutputEncryptor build(char[] cArr) throws OperatorCreationException {
        Cipher createCipher;
        AlgorithmIdentifier algorithmIdentifier;
        Cipher cipher;
        if (this.e == null) {
            this.e = new SecureRandom();
        }
        try {
            if (f(this.c)) {
                byte[] bArr = new byte[20];
                this.e.nextBytes(bArr);
                cipher = this.b.createCipher(this.c.getId());
                cipher.init(1, new PKCS12KeyWithParameters(cArr, bArr, this.g));
                algorithmIdentifier = new AlgorithmIdentifier(this.c, new PKCS12PBEParams(bArr, this.g));
            } else if (!this.c.equals(PKCSObjectIdentifiers.id_PBES2)) {
                throw new OperatorCreationException("unrecognised algorithm");
            } else {
                PBKDFConfig pBKDFConfig = this.f15281a;
                if (pBKDFConfig == null) {
                    pBKDFConfig = this.h.build();
                }
                ASN1ObjectIdentifier aSN1ObjectIdentifier = MiscObjectIdentifiers.id_scrypt;
                if (aSN1ObjectIdentifier.equals(pBKDFConfig.getAlgorithm())) {
                    ScryptConfig scryptConfig = (ScryptConfig) pBKDFConfig;
                    byte[] bArr2 = new byte[scryptConfig.getSaltLength()];
                    this.e.nextBytes(bArr2);
                    ScryptParams scryptParams = new ScryptParams(bArr2, scryptConfig.getCostParameter(), scryptConfig.getBlockSize(), scryptConfig.getParallelizationParameter());
                    SecretKey generateSecret = this.b.createSecretKeyFactory("SCRYPT").generateSecret(new ScryptKeySpec(cArr, bArr2, scryptConfig.getCostParameter(), scryptConfig.getBlockSize(), scryptConfig.getParallelizationParameter(), this.f.getKeySize(new AlgorithmIdentifier(this.d))));
                    createCipher = this.b.createCipher(this.d.getId());
                    createCipher.init(1, generateSecret, this.e);
                    algorithmIdentifier = new AlgorithmIdentifier(this.c, new PBES2Parameters(new KeyDerivationFunc(aSN1ObjectIdentifier, scryptParams), new EncryptionScheme(this.d, ASN1Primitive.fromByteArray(createCipher.getParameters().getEncoded()))));
                } else {
                    PBKDF2Config pBKDF2Config = (PBKDF2Config) pBKDFConfig;
                    byte[] bArr3 = new byte[pBKDF2Config.getSaltLength()];
                    this.e.nextBytes(bArr3);
                    SecretKey generateSecret2 = this.b.createSecretKeyFactory(org.bouncycastle.pkcs.jcajce.a.a(pBKDF2Config.getPRF().getAlgorithm())).generateSecret(new PBEKeySpec(cArr, bArr3, pBKDF2Config.getIterationCount(), this.f.getKeySize(new AlgorithmIdentifier(this.d))));
                    createCipher = this.b.createCipher(this.d.getId());
                    createCipher.init(1, generateSecret2, this.e);
                    algorithmIdentifier = new AlgorithmIdentifier(this.c, new PBES2Parameters(new KeyDerivationFunc(PKCSObjectIdentifiers.id_PBKDF2, new PBKDF2Params(bArr3, pBKDF2Config.getIterationCount(), pBKDF2Config.getPRF())), new EncryptionScheme(this.d, ASN1Primitive.fromByteArray(createCipher.getParameters().getEncoded()))));
                }
                cipher = createCipher;
            }
            return new a(algorithmIdentifier, cipher, cArr);
        } catch (Exception e) {
            throw new OperatorCreationException("unable to create OutputEncryptor: " + e.getMessage(), e);
        }
    }

    public final boolean f(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        return aSN1ObjectIdentifier.on(PKCSObjectIdentifiers.pkcs_12PbeIds) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha1_pkcs12) || aSN1ObjectIdentifier.on(BCObjectIdentifiers.bc_pbe_sha256_pkcs12);
    }

    public JcePKCSPBEOutputEncryptorBuilder setIterationCount(int i) {
        if (this.f15281a == null) {
            this.g = i;
            this.h.withIterationCount(i);
            return this;
        }
        throw new IllegalStateException("set iteration count using PBKDFDef");
    }

    public JcePKCSPBEOutputEncryptorBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.f = secretKeySizeProvider;
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setPRF(AlgorithmIdentifier algorithmIdentifier) {
        if (this.f15281a == null) {
            this.h.withPRF(algorithmIdentifier);
            return this;
        }
        throw new IllegalStateException("set PRF count using PBKDFDef");
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(String str) {
        this.b = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setProvider(Provider provider) {
        this.b = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEOutputEncryptorBuilder setRandom(SecureRandom secureRandom) {
        this.e = secureRandom;
        return this;
    }
}
