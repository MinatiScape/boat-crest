package org.bouncycastle.pkcs.jcajce;

import java.io.InputStream;
import java.security.Provider;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.cryptopro.GOST28147Parameters;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.ScryptParams;
import org.bouncycastle.asn1.pkcs.PBEParameter;
import org.bouncycastle.asn1.pkcs.PBES2Parameters;
import org.bouncycastle.asn1.pkcs.PBKDF2Params;
import org.bouncycastle.asn1.pkcs.PKCS12PBEParams;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.PasswordConverter;
import org.bouncycastle.jcajce.PBKDF1Key;
import org.bouncycastle.jcajce.PKCS12KeyWithParameters;
import org.bouncycastle.jcajce.spec.GOST28147ParameterSpec;
import org.bouncycastle.jcajce.spec.PBKDF2KeySpec;
import org.bouncycastle.jcajce.spec.ScryptKeySpec;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.InputDecryptor;
import org.bouncycastle.operator.InputDecryptorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.SecretKeySizeProvider;
/* loaded from: classes13.dex */
public class JcePKCSPBEInputDecryptorProviderBuilder {

    /* renamed from: a  reason: collision with root package name */
    public JcaJceHelper f15278a = new DefaultJcaJceHelper();
    public boolean b = false;
    public SecretKeySizeProvider c = DefaultSecretKeySizeProvider.INSTANCE;

    /* loaded from: classes13.dex */
    public class a implements InputDecryptorProvider {

        /* renamed from: a  reason: collision with root package name */
        public Cipher f15279a;
        public AlgorithmIdentifier b;
        public final /* synthetic */ char[] c;

        /* renamed from: org.bouncycastle.pkcs.jcajce.JcePKCSPBEInputDecryptorProviderBuilder$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0915a implements InputDecryptor {
            public C0915a() {
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return a.this.b;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, a.this.f15279a);
            }
        }

        public a(char[] cArr) {
            this.c = cArr;
        }

        @Override // org.bouncycastle.operator.InputDecryptorProvider
        public InputDecryptor get(AlgorithmIdentifier algorithmIdentifier) throws OperatorCreationException {
            SecretKey generateSecret;
            Cipher cipher;
            AlgorithmParameterSpec gOST28147ParameterSpec;
            ASN1ObjectIdentifier algorithm = algorithmIdentifier.getAlgorithm();
            try {
                if (algorithm.on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                    PKCS12PBEParams pKCS12PBEParams = PKCS12PBEParams.getInstance(algorithmIdentifier.getParameters());
                    Cipher createCipher = JcePKCSPBEInputDecryptorProviderBuilder.this.f15278a.createCipher(algorithm.getId());
                    this.f15279a = createCipher;
                    createCipher.init(2, new PKCS12KeyWithParameters(this.c, JcePKCSPBEInputDecryptorProviderBuilder.this.b, pKCS12PBEParams.getIV(), pKCS12PBEParams.getIterations().intValue()));
                    this.b = algorithmIdentifier;
                } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                    PBES2Parameters pBES2Parameters = PBES2Parameters.getInstance(algorithmIdentifier.getParameters());
                    if (MiscObjectIdentifiers.id_scrypt.equals(pBES2Parameters.getKeyDerivationFunc().getAlgorithm())) {
                        ScryptParams scryptParams = ScryptParams.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
                        generateSecret = JcePKCSPBEInputDecryptorProviderBuilder.this.f15278a.createSecretKeyFactory("SCRYPT").generateSecret(new ScryptKeySpec(this.c, scryptParams.getSalt(), scryptParams.getCostParameter().intValue(), scryptParams.getBlockSize().intValue(), scryptParams.getParallelizationParameter().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.c.getKeySize(AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme()))));
                    } else {
                        SecretKeyFactory createSecretKeyFactory = JcePKCSPBEInputDecryptorProviderBuilder.this.f15278a.createSecretKeyFactory(pBES2Parameters.getKeyDerivationFunc().getAlgorithm().getId());
                        PBKDF2Params pBKDF2Params = PBKDF2Params.getInstance(pBES2Parameters.getKeyDerivationFunc().getParameters());
                        AlgorithmIdentifier algorithmIdentifier2 = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                        generateSecret = pBKDF2Params.isDefaultPrf() ? createSecretKeyFactory.generateSecret(new PBEKeySpec(this.c, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.c.getKeySize(algorithmIdentifier2))) : createSecretKeyFactory.generateSecret(new PBKDF2KeySpec(this.c, pBKDF2Params.getSalt(), pBKDF2Params.getIterationCount().intValue(), JcePKCSPBEInputDecryptorProviderBuilder.this.c.getKeySize(algorithmIdentifier2), pBKDF2Params.getPrf()));
                    }
                    this.f15279a = JcePKCSPBEInputDecryptorProviderBuilder.this.f15278a.createCipher(pBES2Parameters.getEncryptionScheme().getAlgorithm().getId());
                    this.b = AlgorithmIdentifier.getInstance(pBES2Parameters.getEncryptionScheme());
                    ASN1Encodable parameters = pBES2Parameters.getEncryptionScheme().getParameters();
                    if (parameters instanceof ASN1OctetString) {
                        cipher = this.f15279a;
                        gOST28147ParameterSpec = new IvParameterSpec(ASN1OctetString.getInstance(parameters).getOctets());
                    } else {
                        GOST28147Parameters gOST28147Parameters = GOST28147Parameters.getInstance(parameters);
                        cipher = this.f15279a;
                        gOST28147ParameterSpec = new GOST28147ParameterSpec(gOST28147Parameters.getEncryptionParamSet(), gOST28147Parameters.getIV());
                    }
                    cipher.init(2, generateSecret, gOST28147ParameterSpec);
                } else {
                    if (!algorithm.equals(PKCSObjectIdentifiers.pbeWithMD5AndDES_CBC) && !algorithm.equals(PKCSObjectIdentifiers.pbeWithSHA1AndDES_CBC)) {
                        throw new OperatorCreationException("unable to create InputDecryptor: algorithm " + algorithm + " unknown.");
                    }
                    PBEParameter pBEParameter = PBEParameter.getInstance(algorithmIdentifier.getParameters());
                    Cipher createCipher2 = JcePKCSPBEInputDecryptorProviderBuilder.this.f15278a.createCipher(algorithm.getId());
                    this.f15279a = createCipher2;
                    createCipher2.init(2, new PBKDF1Key(this.c, PasswordConverter.ASCII), new PBEParameterSpec(pBEParameter.getSalt(), pBEParameter.getIterationCount().intValue()));
                }
                return new C0915a();
            } catch (Exception e) {
                throw new OperatorCreationException("unable to create InputDecryptor: " + e.getMessage(), e);
            }
        }
    }

    public InputDecryptorProvider build(char[] cArr) {
        return new a(cArr);
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setKeySizeProvider(SecretKeySizeProvider secretKeySizeProvider) {
        this.c = secretKeySizeProvider;
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(String str) {
        this.f15278a = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setProvider(Provider provider) {
        this.f15278a = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePKCSPBEInputDecryptorProviderBuilder setTryWrongPKCS12Zero(boolean z) {
        this.b = z;
        return this;
    }
}
