package org.bouncycastle.cert.crmf.jcajce;

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
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cert.crmf.CRMFException;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DefaultSecretKeySizeProvider;
import org.bouncycastle.operator.GenericKey;
import org.bouncycastle.operator.OutputEncryptor;
import org.bouncycastle.operator.SecretKeySizeProvider;
import org.bouncycastle.operator.jcajce.JceGenericKey;
/* loaded from: classes12.dex */
public class JceCRMFEncryptorBuilder {
    public static final SecretKeySizeProvider e = DefaultSecretKeySizeProvider.INSTANCE;

    /* renamed from: a  reason: collision with root package name */
    public final ASN1ObjectIdentifier f14472a;
    public final int b;
    public org.bouncycastle.cert.crmf.jcajce.a c;
    public SecureRandom d;

    /* loaded from: classes12.dex */
    public class a implements OutputEncryptor {

        /* renamed from: a  reason: collision with root package name */
        public SecretKey f14473a;
        public AlgorithmIdentifier b;
        public Cipher c;

        public a(JceCRMFEncryptorBuilder jceCRMFEncryptorBuilder, ASN1ObjectIdentifier aSN1ObjectIdentifier, int i, SecureRandom secureRandom) throws CRMFException {
            KeyGenerator g = jceCRMFEncryptorBuilder.c.g(aSN1ObjectIdentifier);
            secureRandom = secureRandom == null ? new SecureRandom() : secureRandom;
            i = i < 0 ? JceCRMFEncryptorBuilder.e.getKeySize(aSN1ObjectIdentifier) : i;
            if (i < 0) {
                g.init(secureRandom);
            } else {
                g.init(i, secureRandom);
            }
            this.c = jceCRMFEncryptorBuilder.c.c(aSN1ObjectIdentifier);
            this.f14473a = g.generateKey();
            AlgorithmParameters j = jceCRMFEncryptorBuilder.c.j(aSN1ObjectIdentifier, this.f14473a, secureRandom);
            try {
                this.c.init(1, this.f14473a, j, secureRandom);
                this.b = jceCRMFEncryptorBuilder.c.k(aSN1ObjectIdentifier, j == null ? this.c.getParameters() : j);
            } catch (GeneralSecurityException e) {
                throw new CRMFException("unable to initialize cipher: " + e.getMessage(), e);
            }
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public AlgorithmIdentifier getAlgorithmIdentifier() {
            return this.b;
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public GenericKey getKey() {
            return new JceGenericKey(this.b, this.f14473a);
        }

        @Override // org.bouncycastle.operator.OutputEncryptor
        public OutputStream getOutputStream(OutputStream outputStream) {
            return new CipherOutputStream(outputStream, this.c);
        }
    }

    public JceCRMFEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this(aSN1ObjectIdentifier, -1);
    }

    public JceCRMFEncryptorBuilder(ASN1ObjectIdentifier aSN1ObjectIdentifier, int i) {
        this.c = new org.bouncycastle.cert.crmf.jcajce.a(new DefaultJcaJceHelper());
        this.f14472a = aSN1ObjectIdentifier;
        this.b = i;
    }

    public OutputEncryptor build() throws CRMFException {
        return new a(this, this.f14472a, this.b, this.d);
    }

    public JceCRMFEncryptorBuilder setProvider(String str) {
        this.c = new org.bouncycastle.cert.crmf.jcajce.a(new NamedJcaJceHelper(str));
        return this;
    }

    public JceCRMFEncryptorBuilder setProvider(Provider provider) {
        this.c = new org.bouncycastle.cert.crmf.jcajce.a(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JceCRMFEncryptorBuilder setSecureRandom(SecureRandom secureRandom) {
        this.d = secureRandom;
        return this;
    }
}
