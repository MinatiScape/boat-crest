package org.bouncycastle.cms.jcajce;

import java.io.OutputStream;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.operator.jcajce.JceGenericKey;
/* loaded from: classes12.dex */
public class JceCMSContentEncryptorBuilder {
    public static final SecretKeySizeProvider f = DefaultSecretKeySizeProvider.INSTANCE;

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14581a;
    public final int b;
    public EnvelopedDataHelper c;
    public SecureRandom d;
    public AlgorithmParameters e;

    /* loaded from: classes12.dex */
    public class a implements OutputEncryptor {

        /* renamed from: a  reason: collision with root package name */
        public SecretKey f14582a;
        public AlgorithmIdentifier b;
        public Cipher c;

        public a(JceCMSContentEncryptorBuilder jceCMSContentEncryptorBuilder, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, AlgorithmParameters algorithmParameters, SecureRandom secureRandom) throws CMSException {
            KeyGenerator createKeyGenerator = jceCMSContentEncryptorBuilder.c.createKeyGenerator(aSN1ObjectIdentifier);
            secureRandom = secureRandom == null ? new SecureRandom() : secureRandom;
            if (i < 0) {
                createKeyGenerator.init(secureRandom);
            } else {
                createKeyGenerator.init(i, secureRandom);
            }
            this.c = jceCMSContentEncryptorBuilder.c.d(aSN1ObjectIdentifier);
            this.f14582a = createKeyGenerator.generateKey();
            algorithmParameters = algorithmParameters == null ? jceCMSContentEncryptorBuilder.c.k(aSN1ObjectIdentifier, this.f14582a, secureRandom) : algorithmParameters;
            try {
                this.c.init(1, this.f14582a, algorithmParameters, secureRandom);
                this.b = jceCMSContentEncryptorBuilder.c.l(aSN1ObjectIdentifier, algorithmParameters == null ? this.c.getParameters() : algorithmParameters);
            } catch (GeneralSecurityException e) {
                throw new CMSException("unable to initialize cipher: " + e.getMessage(), e);
            }
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.b;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new JceGenericKey(this.b, this.f14582a);
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, this.c);
        }
    }

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, f.getKeySize(aSN1ObjectIdentifier));
    }

    public JceCMSContentEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        int i2;
        this.c = new EnvelopedDataHelper(new b());
        this.f14581a = aSN1ObjectIdentifier;
        int keySize = f.getKeySize(aSN1ObjectIdentifier);
        if (aSN1ObjectIdentifier.equals(PKCSObjectIdentifiers.des_EDE3_CBC)) {
            i2 = 168;
            if (i != 168 && i != keySize) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
        } else if (!aSN1ObjectIdentifier.equals(OIWObjectIdentifiers.desCBC)) {
            if (keySize > 0 && keySize != i) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
            this.b = i;
            return;
        } else {
            i2 = 56;
            if (i != 56 && i != keySize) {
                throw new IllegalArgumentException("incorrect keySize for encryptionOID passed to builder.");
            }
        }
        this.b = i2;
    }

    public OutputEncryptor build() throws CMSException {
        return new a(this, this.f14581a, this.b, this.e, this.d);
    }

    public JceCMSContentEncryptorBuilder setAlgorithmParameters(AlgorithmParameters algorithmParameters) {
        this.e = algorithmParameters;
        return this;
    }

    public JceCMSContentEncryptorBuilder setProvider(String str) {
        this.c = new EnvelopedDataHelper(new e(str));
        return this;
    }

    public JceCMSContentEncryptorBuilder setProvider(Provider provider) {
        this.c = new EnvelopedDataHelper(new f(provider));
        return this;
    }

    public JceCMSContentEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.d = secureRandom;
        return this;
    }
}
